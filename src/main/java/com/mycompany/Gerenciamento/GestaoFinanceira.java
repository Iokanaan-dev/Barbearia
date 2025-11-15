/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.StatusAtendimento;
import com.mycompany.Utilidades.TipoRelatorio;
import com.mycompany.barbearia.modelos.*;
import com.mycompany.Gerenciamento.GestaoClientes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.mycompany.date_Barbearia.*;

/**
 *
 * @author italo
 */
public class GestaoFinanceira {

    private static GestaoFinanceira instancia;
    
    Barbearia_date dados = Barbearia_date.getInstancia();

    private final GestaoOrdemServico gestaoOS;
    private final GestaoDespesas gestaoDespesas;

    private GestaoFinanceira() {
        this.gestaoDespesas = GestaoDespesas.getInstancia();
        this.gestaoOS = GestaoOrdemServico.getInstancia();
    

    }

    public static GestaoFinanceira getInstancia() {
        if (instancia == null) {
            instancia = new GestaoFinanceira();
        }
        return instancia;
    }
    
    /**
     * Gera um relatório de todos os produtos usados
     * em Ordens de Serviço pagas de um mês e ano especifico
     * O Gerente usa esta lista para fazer o cálculo de despesas
     *
     * @return Uma lista de Produtos que foram usados.
     */
    public ArrayList<Produto> getRelatorioProdutosUsados(int mes, int ano, Usuario user, String pin) throws Exception {
        
        this.verificarInstancia(pin, user);
        
        this.validarPIM(pin, user);
        
        ArrayList<Produto> produtosUsados = new ArrayList<>();
        ArrayList<OrdemServico> ordensDoMes = gestaoOS.getLista(); 
        
        for (OrdemServico os : ordensDoMes) {
            // Apenas de contas pagas naquele mês
            if (os.getStatus() == StatusAtendimento.PAGO &&
                os.getDataExecucao().getMonthValue() == mes &&
                os.getDataExecucao().getYear() == ano) {
                
                // Adiciona todos os 'produtosUtilizados' desta OS à lista principal
                produtosUsados.addAll(os.getProdutosUtilizados());
            }
        }
        return produtosUsados; // Retorna a lista
    }

    public String gerarBalancoMensal(int mes, int ano, Usuario user, String pin, boolean salvar) throws Exception {

        this.verificarInstancia(pin, user);
        
        this.validarPIM(pin, user);

        double totalReceitas = 0.0;

        ArrayList<OrdemServico> todasOrdens = gestaoOS.getListaCopia();

        for (OrdemServico os : todasOrdens) {
            if (os.getDataExecucao() == null) continue;

            if (os.getStatus() == StatusAtendimento.PAGO) {
                totalReceitas += os.getValorTotalAPagar();
            } else if (os.getStatus() == StatusAtendimento.CANCELADO) {
                totalReceitas += os.getValorTaxaCancelamento_35pct();
            }
        }

        double totalDespesas = 0.0;
        ArrayList<Despesa> todasDespesas = gestaoDespesas.getList(user);

        for (Despesa despesa : todasDespesas) {
            if (despesa.getDataPagamento() == null) continue;
            if (despesa.getDataPagamento().getMonthValue() == mes &&
                despesa.getDataPagamento().getYear() == ano) {
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

         // Salva no JSON
        if(salvar){RelatorioFinanceiro registro = new RelatorioFinanceiro(TipoRelatorio.BALANÇO_MENSAL, LocalDate.of(ano, mes, 1), relatorio);
        dados.getListaRelatorios().add(registro);
        dados.salvar();
        }
        return relatorio;
    }

    public String gerarNotaCliente(String cpf, boolean salvar) {
        Cliente cliente = GestaoClientes.getInstancia().buscarCPF(cpf);
        ArrayList<OrdemServico> ordensCliente = gestaoOS.buscarOSCliente(cliente.getId());

        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasCancelamento = 0.0;
        double totalEncaixe = 0.0;
        double totalPagar = 0.0;

        for (OrdemServico os : ordensCliente) {
            if (os.getStatus() == StatusAtendimento.PAGO    ) {
                totalServicos += os.getValorTotalServicos();
                totalProdutos += os.getValorTotalProdutos();
                totalEncaixe += os.getValorTaxaEncaixe();
                totalPagar += os.getValorTotalAPagar() + os.getValorTaxaCancelamento_35pct();
            }
            else if (os.getStatus() == StatusAtendimento.CANCELADO) {
                totalTaxasCancelamento += os.getValorTaxaCancelamento_35pct();
                totalPagar += os.getValorTaxaCancelamento_35pct();
            }
        }

        String nota = String.format(
            "--- Nota cliente: %s ---\n" +
            "Total Serviços (Ordens Pagas):... R$ %.2f \n" +
            "Total Produtos (Custos):........ R$ %.2f \n" +
            "Total taxa de cancelamento:..... R$ %.2f \n" +
            "Total encaixe:.................. R$ %.2f \n" +
            "------------------------------------\n" +
            "Total a pagar:.................. R$ %.2f \n",
            cliente.getNome(),
            totalServicos,
            totalProdutos,
            totalTaxasCancelamento,
            totalEncaixe,
            totalPagar
        );
        
        if(salvar){RelatorioFinanceiro registro = new RelatorioFinanceiro(TipoRelatorio.NOTA_CLIENTE,LocalDate.now(),nota);
        
        dados.getListaRelatorios().add(registro);
        dados.salvar();
        }
        return nota;
    }

    public String gerarRelatorioVendasDiario(LocalDate dia, Usuario user, String pin, boolean salvar) throws Exception {
        
        this.verificarInstancia(pin, user);
        
        this.validarPIM(pin, user);
        
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getListaCopia();

        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasEncaixe = 0.0;
        double totalTaxasCancelamento = 0.0;

        for (OrdemServico os : todasOrdens) {
            if (os.getDataExecucao() != null && os.getDataExecucao().isEqual(dia)) {
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

        String relatorio = String.format(
            "%n--- RELATÓRIO DE VENDAS DIÁRIO: %s ---%n" +
            "Receita de Serviços:............ R$ %.2f%n" +
            "Receita de Produtos:............ R$ %.2f%n" +
            "Taxas de Encaixe:............... R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA BRUTA:.................. R$ %.2f%n" +
            "Multas (Cancelamentos):......... R$ %.2f%n" +
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
        
        if(salvar){RelatorioFinanceiro registro = new RelatorioFinanceiro(TipoRelatorio.RELATORIO_DIARIO,dia,relatorio);
        
        dados.getListaRelatorios().add(registro);
        dados.salvar();
        }
        return relatorio;
    }

    public String gerarRelatorioVendasMensal(int mes, int ano, Usuario user, String pin, boolean salvar) throws Exception {
        
        this.verificarInstancia(pin, user);
        this.validarPIM(pin, user);
        
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getListaCopia();

        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasEncaixe = 0.0;
        double totalTaxasCancelamento = 0.0;

        for (OrdemServico os : todasOrdens) {
            if (os.getDataExecucao() != null &&
                os.getDataExecucao().getMonthValue() == mes &&
                os.getDataExecucao().getYear() == ano) {

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

        String relatorio = String.format(
            "%n--- RELATÓRIO DE VENDAS MENSAL: %02d/%d ---%n" +
            "Receita de Serviços:............ R$ %.2f%n" +
            "Receita de Produtos:............ R$ %.2f%n" +
            "Taxas de Encaixe:............... R$ %.2f%n" +
            "-------------------------------------------%n" +
            "RECEITA BRUTA:.................. R$ %.2f%n" +
            "Multas (Cancelamentos):......... R$ %.2f%n" +
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
        
        if(salvar){
        RelatorioFinanceiro registro = new RelatorioFinanceiro(TipoRelatorio.RELATORIO_MENSAL,LocalDate.of(ano, mes, 1),relatorio);
        
        dados.getListaRelatorios().add(registro);
        dados.salvar();}
        return relatorio;
        
    }
    
    private void validarPIM(String pin, Usuario user) throws Exception{
        
        Gerente gerente = (Gerente) user;
        
        if (!gerente.verficarPinADM(pin)) {
            throw new Exception("PIN incorreto!");
        }
    }
    
    private void verificarInstancia(String pin, Usuario user) throws Exception{
        if (!(user instanceof Gerente)) {
            throw new Exception("Somente gerentes podem gerar balanço mensal");
        }
    }
    
    public RelatorioFinanceiro gerarRegistroBalancoMensal(int mes, int ano, Usuario user, String pin, boolean s) throws Exception {
        String conteudo = gerarBalancoMensal(mes, ano, user, pin, s);
        return new RelatorioFinanceiro(TipoRelatorio.BALANÇO_MENSAL,LocalDate.of(ano, mes, 1), conteudo);
    }  
}
