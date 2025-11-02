/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import Gerenciamento.GestaoUsuarios;
import Gerenciamento.GestaoClientes;
import Listas.ListaGenerica;
import java.time.LocalDate;

/**
 *
 * @author intalo
 */
public class OrdemServico extends Modelo implements Utilidades.IdGerador {
    private static int contador = 0;
    
    private String idCliente;
    private String idBarbeiro;
    private ListaGenerica<Produto> produtosUtilizados = new ListaGenerica<>();
    private String observacoes;    
    private ListaGenerica<Servico> servicosRealizados = new ListaGenerica<>();    
    private LocalDate dataExecucao;

    public OrdemServico(String nomeCliente, String nomeBarbeiro, String observacoes, LocalDate dataExecucao) {
        this.idCliente = nomeCliente;
        this.idBarbeiro = nomeBarbeiro;
        this.observacoes = observacoes;
        this.dataExecucao = dataExecucao;
    }
    
    // torna as observacoes opcionais
    public OrdemServico(String nomeCliente, String nomeBarbeiro, LocalDate dataExecucao) {
        // chama o construtor de quatro parametros, economizando linhas de codigo
        this(nomeCliente, nomeBarbeiro, null, dataExecucao);
    }
    
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String nomeCliente) {
        this.idCliente = nomeCliente;
    }

    public String getIdBarbeiro() {
        return idBarbeiro;
    }

    public void setIdBarbeiro(String nomeBarbeiro) {
        this.idBarbeiro = nomeBarbeiro;
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
    
    // retorna uma String com a lista dos nomes dos servicos realizados
    public String getListaServicos(){
        return this.servicosRealizados.getNomesItens();
    }
    
    // retorna uma String com a lista dos nomes dos produtos utilizados
    public String getListaProdutos(){
        return this.produtosUtilizados.getNomesItens();
    }
   
    
    public String getNomeBarbeiro(String id)
    {
        ListaGenerica<Usuario> barbeiroSelecionado = GestaoUsuarios.getListaUsuario();
                
        return barbeiroSelecionado.getNomeItem(id);
        //PERGUNTAR SE POSSO DEIXAR O ARRAY LIST DENTRO DE GESTAO BARBEIRO COMO STATIC
        //POIS ACHO QUE ISSO ME PERMITIRIA REFERENCIAR ELE AQUI DENTRO VISTO QUE EH DA CLASSE.
        //OUTRA DUVIDA: NO TEXTO, SER DINAMICO EH SER EM TEMPO DE EXECUCAO, 
        //O ARRAY SER STATIC DENTRO DA GESTAO O TORNA NAO DINAMICO?
    }
    
    public String getNomeCliente(String id)
    {
        ListaGenerica<Cliente> clienteSelecionado = GestaoClientes.getListaCliente();
                
        return clienteSelecionado.getNomeItem(id);   
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
        return "OS" + ++contador;
    }
    
    @Override
    public String toString() {
        return String.format("%nOrdem de Servico %s%n%sCliente: %s%nBarbeiro: %s%nServicos: %sProdutos: %sObservacoes: %s", getId(), super.toString(), getNomeCliente(this.idCliente), getNomeBarbeiro(this.idBarbeiro), getListaServicos(), getListaProdutos(), getObservacoes());
    }
}
