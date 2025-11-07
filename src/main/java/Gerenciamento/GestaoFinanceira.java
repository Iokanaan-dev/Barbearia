/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Utilidades.StatusAtendimento;
import com.mycompany.barbearia.modelos.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author italo
 */
public class GestaoFinanceira {
    
    private static GestaoFinanceira instancia;
    
    private final GestaoOrdemServico gestaoOS;
    private final GestaoDespesas gestaoDespesas;
    
    private GestaoFinanceira() {
        this.gestaoDespesas = GestaoDespesas.getInstancia();
        this.gestaoOS = GestaoOrdemServico.getInstancia();
    }
    
    public static GestaoFinanceira getInstancia(){
        if(instancia == null) {
            instancia = new GestaoFinanceira();
        }
        return instancia;
    }
    
    public String gerarBalancoMensal(int mes, int ano, Usuario user) throws Exception {
        
        if(!(user instanceof Gerente)) {
            throw new Exception("Somente gerentes podem gerar balanço mensal");
        }
        
        double totalReceitas = 0.0;
        
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista();
 
        for(OrdemServico os : todasOrdens) {
            if (os.getDataExecucao() == null) continue;
            
            if(os.getStatus() == StatusAtendimento.PAGO) {    
                totalReceitas += os.getValorTotalAPagar();
            }
            else if(os.getStatus() == StatusAtendimento.CANCELADO){
                totalReceitas += os.getValorTaxaCancelamento_35pct();
            }
        }
        
        double totalDespesas = 0.0;
        ArrayList<Despesa>  todasDespesas = gestaoDespesas.getList(user);
        
        for (Despesa despesa : todasDespesas) {
            if (despesa.getDataPagamento() == null && despesa.getDataPagamento().getMonthValue() == mes &&
                despesa.getDataPagamento().getYear() == ano) continue; 
            if(despesa.getDataPagamento().getMonthValue() == mes && despesa.getDataPagamento().getYear() == ano){
                totalDespesas += despesa.getValor();
            }
        }
        
    double balancoFinal = totalReceitas - totalDespesas;
        
      
        String relatorio = String.format(
            "%n--- BALANÇO MENSAL: %02d/%d ---%n" +
            "Receita Total (Ordens Pagas):... R$ %.2f%n" +
            "Despesa Total (Custos):........ R$ %.2f%n" +
            "------------------------------------%n" +
            "Balanço Final:................... R$ %.2f%n",
            mes, ano,
            totalReceitas,
            totalDespesas,
            balancoFinal
        );
        
        return relatorio;
    }
    
    public String gerarNotaCliente(Cliente cliente){
        ArrayList<OrdemServico> ordensCLiente = gestaoOS.buscarOSCliente(cliente.getId());
        
        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasCancelamento = 0.0;
        double totalEncaixe = 0.0;
        double totalPagar = 0.0;
        
        for (OrdemServico os : ordensCLiente) {
            if (os.getStatus() == StatusAtendimento.PAGO || os.getStatus() == StatusAtendimento.CANCELADO){
                
                totalServicos += os.getValorTotalServicos();
                totalProdutos += os.getValorTotalProdutos();
                totalTaxasCancelamento += os.getValorTaxaCancelamento_35pct();
                totalEncaixe += os.getValorTaxaEncaixe();
                totalPagar += os.getValorTotalAPagar() + os.getValorTaxaCancelamento_35pct(); 
         
            }
        }
        
        String relatorio = String.format(
            "--- Nota cliente: %s ---\n" +
            "Total Serviços (Ordens Pagas):... R$ %.2f \n" +
            "Total Produtos (Custos):........ R$ %.2f \n"+
            "Total taxa de cancelamento (Custos):........ R$ %.2f \n" +
            "Total encaixe (Custos):........ R$ %.2f \n" +
            "------------------------------------\n" +
            "Total a pagar:................... R$ %.2f \n",
            
            cliente.getNome(),
            totalServicos,
            totalProdutos,
            totalTaxasCancelamento,
            totalEncaixe,
            totalPagar
        );
        
        return relatorio;
    }  
    
    public String gerarRelatorioVendasDiario(LocalDate dia) {
        
   
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista(); 
        
        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasEncaixe = 0.0;
        double totalTaxasCancelamento = 0.0;
        
  
        for (OrdemServico os : todasOrdens) {
            if (os.getDataExecucao().isEqual(dia)) {
                
                // Filtra pelo status financeiro
                // (Contamos o que foi PAGO ou o que foi CANCELADO e gerou multa)
                if (os.getStatus() == StatusAtendimento.PAGO || os.getStatus() == StatusAtendimento.CANCELADO) {
                    
                    // soma os valores
                    totalServicos += os.getValorTotalServicos();
                    totalProdutos += os.getValorTotalProdutos();
                    totalTaxasEncaixe += os.getValorTaxaEncaixe();
                    totalTaxasCancelamento += os.getValorTaxaCancelamento_35pct();
                }
            }
        }
        
        double receitaBruta = totalServicos + totalProdutos + totalTaxasEncaixe;
        double receitaLiquida = receitaBruta + totalTaxasCancelamento; // (Taxa de cancelamento é receita pura)

        //Formata a String de retorno
        return String.format(
            "%n--- RELATÓRIO DE VENDAS E SERVIÇOS: %s ---%n" +
            "Receita de Serviços (Base):.... R$ %.2f%n" +
            "Receita de Produtos (Loja):.... R$ %.2f%n" +
            "Receita de Taxas (Encaixe):.... R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA BRUTA (Serviços + Vendas):.. R$ %.2f%n" +
            "Multas (Taxas de Cancelamento):.. R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA TOTAL DO DIA:........... R$ %.2f%n",
            dia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            totalServicos,
            totalProdutos,
            totalTaxasEncaixe,
            receitaBruta,
            totalTaxasCancelamento,
            receitaLiquida
        );
    } 
    
    public String gerarRelatorioVendasMensal(int mes, int ano) {
        
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista();
        
        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasEncaixe = 0.0;
        double totalTaxasCancelamento = 0.0;
        
        //  Itera e filtra as OSs
        for (OrdemServico os : todasOrdens) {
            // Filtra pelo Mês e Ano
            if (os.getDataExecucao().getMonthValue() == mes && os.getDataExecucao().getYear() == ano) {
                
                if (os.getStatus() == StatusAtendimento.PAGO || os.getStatus() == StatusAtendimento.CANCELADO) {
                    totalServicos += os.getValorTotalServicos();
                    totalProdutos += os.getValorTotalProdutos();
                    totalTaxasEncaixe += os.getValorTaxaEncaixe();
                    totalTaxasCancelamento += os.getValorTaxaCancelamento_35pct();
                }
            }
        }
        
        double receitaBruta = totalServicos + totalProdutos + totalTaxasEncaixe;
        double receitaLiquida = receitaBruta + totalTaxasCancelamento;

        //  Formata a String de retorno
        return String.format(
            "%n--- RELATÓRIO DE VENDAS E SERVIÇOS: %02d/%d ---%n" +
            "Receita de Serviços (Base):.... R$ %.2f%n" +
            "Receita de Produtos (Loja):.... R$ %.2f%n" +
            "Receita de Taxas (Encaixe):.... R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA BRUTA (Serviços + Vendas):.. R$ %.2f%n" +
            "Multas (Taxas de Cancelamento):.. R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA TOTAL DO MÊS:........... R$ %.2f%n",
            mes, ano,
            totalServicos,
            totalProdutos,
            totalTaxasEncaixe,
            receitaBruta,
            totalTaxasCancelamento,
            receitaLiquida
        );
    }
    
}
