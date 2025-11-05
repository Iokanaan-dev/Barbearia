/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author intalo
 */
public class OrdemServico extends Modelo{
    private static int contador = 0;
    
    private String idCliente;
    private String idBarbeiro;
    private StatusAtendimento status;
    private LocalDate dataExecucao;
    private String observacoes;   
    
    private final ArrayList<Modelo> produtosUtilizados = new ArrayList();
    private final ArrayList<Agendamento> agendamentos = new ArrayList();
    private final Map<String, Integer> produtosVendidos = new HashMap();
    
    private double valorTotalProdutos;
    private double valorTotalServicos;
    private double valorTaxaEncaixe;
    private double valorAdiantado_50pct;
    private double valorTaxaCancelamento_35pct;

public OrdemServico(Cliente cliente, Barbeiro barbeiro, LocalDate dataExecucao, String observacoes) {
        
        this.idCliente = cliente.getId();
        this.idBarbeiro = barbeiro.getId();
        this.dataExecucao = dataExecucao;
        this.observacoes = observacoes;
        
        this.status = StatusAtendimento.ABERTO;
        this.valorTotalProdutos = 0.0;
        this.valorTotalServicos = 0.0;
        this.valorTaxaEncaixe = 0.0;
        this.valorAdiantado_50pct = 0.0;
        this.valorTaxaCancelamento_35pct = 0.0;
    }
    
    public OrdemServico(Cliente cliente, Barbeiro barbeiro, LocalDate dataExecucao) {
            this(cliente, barbeiro, dataExecucao, "---");
    }
    
    public void adicionarProdutoVendido(Produto produto, int quantidade) {
        String produtoId = produto.getId();
        int qtdAtual = this.produtosVendidos.getOrDefault(produtoId, 0);
        this.produtosVendidos.put(produtoId, qtdAtual + quantidade);
    }
    
    public Map<String, Integer> getProdutosVendidos() {
        return this.produtosVendidos;
    }
    public void setValorTotalProdutos(double valor) {
        this.valorTotalProdutos = valor;
    }

    public void setValorTotalServicos(double valorTotalServicos) {
        this.valorTotalServicos = valorTotalServicos;
    }

    public void setValorTaxaEncaixe(double valorTaxaEncaixe) {
        this.valorTaxaEncaixe = valorTaxaEncaixe;
    }
    
    public void adicionarAgendamento(Agendamento ag) {
        this.agendamentos.add(ag);
    }
    
    public void adicionarProdutoUtilizado(Produto produto) {
        this.produtosUtilizados.add(produto);
    }
  

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdBarbeiro() {
        return idBarbeiro;
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public double getValorTotalServicos() {
        return valorTotalServicos;
    }

    public double getValorTaxaEncaixe() {
        return valorTaxaEncaixe;
    }

    public double getValorAdiantado_50pct() {
        return valorAdiantado_50pct;
    }

    public double getValorTaxaCancelamento_35pct() {
        return valorTaxaCancelamento_35pct;
    }
    
    public ArrayList<Modelo> getProdutosUtilizados() {
        return produtosUtilizados;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public void setValorAdiantado_50pct(double valorAdiantado_50pct) {
        this.valorAdiantado_50pct = valorAdiantado_50pct;
    }

    public void setValorTaxaCancelamento_35pct(double valorTaxaCancelamento_35pct) {
        this.valorTaxaCancelamento_35pct = valorTaxaCancelamento_35pct;
    }
    
    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(LocalDate dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }
    
    public double getValorTotalAPagar() {
        return this.valorTotalServicos + this.valorTaxaEncaixe + this.valorTotalProdutos;
    }
    
    public double getValorPendente() {
        return getValorTotalAPagar() - this.valorAdiantado_50pct + this.valorTaxaCancelamento_35pct;
    }
   
    // adiciona produtos
    public void adicionarProduto(Produto produto){
        this.produtosUtilizados.add(produto);
    }

    @Override
    public String gerarId()
    {
        return "OS" + ++contador;
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "idCliente=" + idCliente + ", idBarbeiro=" + idBarbeiro + ", status=" + status + ", dataExecucao=" + dataExecucao + ", observacoes=" + observacoes + ", produtosUtilizados=" + produtosUtilizados + ", agendamentos=" + agendamentos + ", valorTotalServicos=" + valorTotalServicos + ", valorTaxaEncaixe=" + valorTaxaEncaixe + ", valorAdiantado_50pct=" + valorAdiantado_50pct + ", valorTaxaCancelamento_35pct=" + valorTaxaCancelamento_35pct + '}';
    }

    
    
}
