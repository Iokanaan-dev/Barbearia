/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.StatusAtendimento;
import com.mycompany.Utilidades.TipoRelatorio;
import com.mycompany.barbearia.modelos.*;
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
    private final Barbearia_date dados;

    private final GestaoOrdemServico gestaoOS;
    private final GestaoDespesas gestaoDespesas;

    private GestaoFinanceira(Barbearia_date dados) {
        this.gestaoDespesas = GestaoDespesas.getInstancia();
        this.gestaoOS = GestaoOrdemServico.getInstancia();
        this.dados = dados;
    }

    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoFinanceira(dados);
        }
    }

    public static GestaoFinanceira getInstancia() {
        return instancia;
    }

    public String gerarBalancoMensal(int mes, int ano, Usuario user) throws Exception {

        if (!(user instanceof Gerente)) {
            throw new Exception("Somente gerentes podem gerar balanço mensal");
        }

        double totalReceitas = 0.0;

        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista();

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

        // 🔹 Salva no JSON
        RelatorioFinanceiro registro = new RelatorioFinanceiro(
            TipoRelatorio.BALANÇO_MENSAL,
            String.format("%02d/%d", mes, ano),
            relatorio
        );
        dados.getListaRelatorios().add(registro);
        dados.salvar();

        return relatorio;
    }

    public String gerarNotaCliente(Cliente cliente) {
        ArrayList<OrdemServico> ordensCliente = gestaoOS.buscarOSCliente(cliente.getId());

        double totalServicos = 0.0;
        double totalProdutos = 0.0;
        double totalTaxasCancelamento = 0.0;
        double totalEncaixe = 0.0;
        double totalPagar = 0.0;

        for (OrdemServico os : ordensCliente) {
            if (os.getStatus() == StatusAtendimento.PAGO || os.getStatus() == StatusAtendimento.CANCELADO) {
                totalServicos += os.getValorTotalServicos();
                totalProdutos += os.getValorTotalProdutos();
                totalTaxasCancelamento += os.getValorTaxaCancelamento_35pct();
                totalEncaixe += os.getValorTaxaEncaixe();
                totalPagar += os.getValorTotalAPagar() + os.getValorTaxaCancelamento_35pct();
            }
        }

        return String.format(
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
    }

    public String gerarRelatorioVendasDiario(LocalDate dia) {
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista();

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

        // 🔹 Salva no JSON
        RelatorioFinanceiro registro = new RelatorioFinanceiro(
            TipoRelatorio.RELATORIO_DIARIO,
            dia.toString(),
            relatorio
        );
        dados.getListaRelatorios().add(registro);
        dados.salvar();

        return relatorio;
    }

    public String gerarRelatorioVendasMensal(int mes, int ano) {
        ArrayList<OrdemServico> todasOrdens = gestaoOS.getLista();

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

        // 🔹 Salva no JSON
        RelatorioFinanceiro registro = new RelatorioFinanceiro(
            TipoRelatorio.RELATORIO_MENSAL,
            String.format("%02d/%d", mes, ano),
            relatorio
        );
        dados.getListaRelatorios().add(registro);
        dados.salvar();

        return relatorio;
    }
}
