/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mycompany.Gerenciamento.GestaoClientes;
import com.mycompany.Gerenciamento.GestaoUsuarios;
import com.mycompany.Utilidades.StatusAtendimento;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Representa uma Ordem de serviço no Sistema. Eh formada por agendamentos, 
 * clientes, usuarios, serviços feitos e produtos utilizados
 * @author intalo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdemServico extends Modelo{
    private static int contadorInstancias = 0;
    
    private String idCliente;
    private String idBarbeiro;
    private StatusAtendimento status;
    private LocalDate dataExecucao;
    private String observacoes;   
    
    private final ArrayList<Produto> produtosUtilizados = new ArrayList();
    private final ArrayList<Agendamento> agendamentos = new ArrayList();
    private final Map<String, Integer> produtosVendidos = new HashMap();
    
    private double valorTotalProdutos;
    private double valorTotalServicos;
    private double valorTaxaEncaixe;
    private double valorAdiantado_50pct;
    private double valorTaxaCancelamento_35pct;

    /**
     *
     * @param cliente
     * @param barbeiro
     * @param dataExecucao
     * @param observacoes
     */
    public OrdemServico(Cliente cliente, Barbeiro barbeiro, LocalDate dataExecucao, String observacoes) {
    
        super();
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
        
        ++contadorInstancias;
    }
    
    /**
     *
     * @param cliente
     * @param barbeiro
     * @param dataExecucao
     */
    public OrdemServico(Cliente cliente, Barbeiro barbeiro, LocalDate dataExecucao) {
            this(cliente, barbeiro, dataExecucao, "---");
            ++contadorInstancias;    
    }
    
    /**
     *
     * @param cliente
     * @param dataCompra
     */
    public OrdemServico(Cliente cliente, LocalDate dataCompra) {
        this.idCliente = cliente.getId();
        this.dataExecucao = dataCompra;
        ++contadorInstancias;    
    }
    
    /**
     *
     */
    public OrdemServico(){
        ++contadorInstancias;
    }
    
    /**
     *
     * @param produto
     * @param quantidade
     */
    public void adicionarProdutoVendido(Produto produto, int quantidade) {
        String produtoId = produto.getId();
        int qtdAtual = this.produtosVendidos.getOrDefault(produtoId, 0);
        this.produtosVendidos.put(produtoId, qtdAtual + quantidade);
    }
    
    /**
     *
     * @return
     */
    public Map<String, Integer> getProdutosVendidos() {
        return this.produtosVendidos;
    }

    /**
     *
     * @param valor
     */
    public void setValorTotalProdutos(double valor) {
        this.valorTotalProdutos = valor;
    }

    /**
     *
     * @param valorTotalServicos
     */
    public void setValorTotalServicos(double valorTotalServicos) {
        this.valorTotalServicos = valorTotalServicos;
    }

    /**
     *
     * @param valorTaxaEncaixe
     */
    public void setValorTaxaEncaixe(double valorTaxaEncaixe) {
        this.valorTaxaEncaixe = valorTaxaEncaixe;
    }
    
    /**
     *
     * @param ag
     */
    public void adicionarAgendamento(Agendamento ag) {
        this.agendamentos.add(ag);
    }
    
    /**
     *
     * @param produto
     */
    public void adicionarProdutoUtilizado(Produto produto) {
        this.produtosUtilizados.add(produto);
    }
  
    /**
     *
     * @return
     */
    public String getIdCliente() {
        return idCliente;
    }
    
    /**
     *
     * @return
     */
    public Cliente getCliente(){
        return GestaoClientes.getInstancia().buscarPorId(this.idCliente);
    }

    /**
     *
     * @return
     */
    public String getIdBarbeiro() {
        return idBarbeiro;
    }

    /**
     *
     * @param idCliente
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     *
     * @param idBarbeiro
     */
    public void setIdBarbeiro(String idBarbeiro) {
        this.idBarbeiro = idBarbeiro;
    }

    /**
     *
     * @return
     */
    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    /**
     *
     * @return
     */
    public StatusAtendimento getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public static int getContador() {
        return contadorInstancias;
    }

    /**
     *
     * @return
     */
    public double getValorTotalServicos() {
        return valorTotalServicos;
    }

    /**
     *
     * @return
     */
    public double getValorTaxaEncaixe() {
        return valorTaxaEncaixe;
    }

    /**
     *
     * @return
     */
    public double getValorAdiantado_50pct() {
        return valorAdiantado_50pct;
    }

    /**
     *
     * @return
     */
    public double getValorTaxaCancelamento_35pct() {
        return valorTaxaCancelamento_35pct;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Produto> getProdutosUtilizados() {
        return produtosUtilizados;
    }

    /**
     *
     * @param status
     */
    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    /**
     *
     * @param valorAdiantado_50pct
     */
    public void setValorAdiantado_50pct(double valorAdiantado_50pct) {
        this.valorAdiantado_50pct = valorAdiantado_50pct;
    }

    /**
     *
     * @param valorTaxaCancelamento_35pct
     */
    public void setValorTaxaCancelamento_35pct(double valorTaxaCancelamento_35pct) {
        this.valorTaxaCancelamento_35pct = valorTaxaCancelamento_35pct;
    }
    
    /**
     *
     * @return
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     *
     * @param observacoes
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     *
     * @return
     */
    public LocalDate getDataExecucao() {
        return dataExecucao;
    }

    /**
     *
     * @param dataExecucao
     */
    public void setDataExecucao(LocalDate dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    /**
     *
     * @return
     */
    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }
    
    /**
     *
     * @return
     */
    public double getValorTotalAPagar() {
        return (this.valorTotalServicos + this.valorTaxaEncaixe + this.valorTotalProdutos);
    }
    
    /**
     *
     * @return
     */
    public double getValorPendente() {
        return getValorTotalAPagar() - this.valorAdiantado_50pct + this.valorTaxaCancelamento_35pct;
    }
   
    /**
     *
     * @return
     */
    @Override
    public String gerarId()
    {
        return "OS-" + UUID.randomUUID().toString().substring(0, 10);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%nOrdem de Servico %s%nCliente: %s | Barbeiro %s | Status: %s | Data de Execucao: %s%nProdutos: %s%nTaxa de Encaixe: %s | Total %.2f%nOBS: %s", getId(), GestaoClientes.getInstancia().buscarPorId(idCliente).getNome(), GestaoUsuarios.getInstancia().buscarPorId(idBarbeiro).getNome(), getStatus(), getDataExecucao(), this.produtosVendidos, getValorTaxaEncaixe(), getValorTotalAPagar(), getObservacoes());
    }  
}
