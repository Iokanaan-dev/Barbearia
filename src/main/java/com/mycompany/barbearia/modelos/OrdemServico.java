/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import Listas.ListaGenerica;
import java.time.LocalDate;

/**
 *
 * @author intalo
 */
public class OrdemServico extends Modelo implements Utilidades.IdGerador {
    private static int contador = 0;
    
    private String nomeCliente;
    private String nomeBarbeiro;
    private ListaGenerica<Produto> produtosUtilizados;
    private ListaGenerica<Servico> servicosRealizados;    
    private String observacoes;
    private LocalDate dataExecucao;

    public OrdemServico(String nomeCliente, String nomeBarbeiro, ListaGenerica<Produto> produtosUtilizados, ListaGenerica<Servico> servicosRealizados, String observacoes, LocalDate dataExecucao) {
        this.nomeCliente = nomeCliente;
        this.nomeBarbeiro = nomeBarbeiro;
        this.produtosUtilizados = produtosUtilizados;
        this.servicosRealizados = servicosRealizados;
        this.observacoes = observacoes;
        this.dataExecucao = dataExecucao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro;
    }

    public ListaGenerica<Produto> getProdutosUtilizados() {
        return produtosUtilizados;
    }

    public void setProdutosUtilizados(ListaGenerica<Produto> produtosUtilizados) {
        this.produtosUtilizados = produtosUtilizados;
    }

    public ListaGenerica<Servico> getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(ListaGenerica<Servico> servicosRealizados) {
        this.servicosRealizados = servicosRealizados;
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
    
    // adiciona servicos
    public void adicionarServico(Servico servico){
        this.servicosRealizados.adicionar(servico);
    }
    
    // adiciona produtos
    public void adicionarProduto(Produto produto){
        this.produtosUtilizados.adicionar(produto);
    }

    @Override
    public String gerarId()
    {
        return "SO" + ++contador;
    }
}
