/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import Gerenciamento.GestaoUsuarios;
import Gerenciamento.GestaoClientes;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author intalo
 */
public class OrdemServico extends Modelo{
    private static int contador = 0;
    
    private String idCliente;
    private String idBarbeiro;
    private ArrayList<Modelo> produtosUtilizados = new ArrayList<>();
    private String observacoes;    
    private ArrayList<Modelo> servicosRealizados = new ArrayList<>();    
    private LocalDate dataExecucao;

    public OrdemServico(String nomeCliente, String nomeBarbeiro, String observacoes, LocalDate dataExecucao) {
        this.idCliente = nomeCliente;
        this.idBarbeiro = nomeBarbeiro;
        this.observacoes = observacoes;
        this.dataExecucao = dataExecucao;
    }
    
    public OrdemServico(String nomeCliente, String nomeBarbeiro, LocalDate dataExecucao)
    {
        this(nomeCliente, nomeBarbeiro, "---", dataExecucao); // sobrecarrega o construtor para ser possivel criar OS sem observaçoes
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

    public ArrayList<Modelo> getProdutosUtilizados() {
        return produtosUtilizados;
    }
    

    public void setProdutosUtilizados(ArrayList<Modelo> produtosUtilizados) {
        this.produtosUtilizados = produtosUtilizados;
    }

    public ArrayList<Modelo> getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(ArrayList<Modelo> servicosRealizados) {
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
        return getNomesItens(this.servicosRealizados);
    }
    
    // retorna uma String com a lista dos nomes dos produtos utilizados
    public String getListaProdutos(){
        return getNomesItens(this.produtosUtilizados);
    }
    
    // COLOCAR ESSE METODO NA CLASSE MODELO!
    public String getNomesItens(ArrayList<Modelo> lista) {
        String nomesItensLista = String.format("%n");
        
        // se a lista estiver vazia toString retorna uma indicacao
        if(lista.isEmpty())
            return String.format("---%n");
     
        for(Modelo item: lista)
            nomesItensLista += String.format("%s%n", item.getNome());
        
        return nomesItensLista;
    }    
   
    
    public String getNomeBarbeiro(String id){
        // usa o padrao singleton para acessar a lista de clientes e procurar o nome do cliente como id especificado        
        ArrayList<Usuario> listaBarbeiros = GestaoUsuarios.getInstancia().getLista();
                
        return GestaoUsuarios.getInstancia().getNomeItem(listaBarbeiros, id);
    }
    
    public String getNomeCliente(String id){
        // usa o padrao singleton para acessar a lista de clientes e procurar o nome do cliente como id especificado
        ArrayList<Cliente> listaClientes = GestaoClientes.getInstancia().getLista();
                
        return GestaoClientes.getInstancia().getNomeItem(listaClientes, id);
    }    

    // adiciona servicos
    public void adicionarServico(Servico servico){
        super.adicionar(servicosRealizados, servico);
    }
    

    // adiciona produtos
    public void adicionarProduto(Produto produto){
        super.adicionar(produtosUtilizados, produto);
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
