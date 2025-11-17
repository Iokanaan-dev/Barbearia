/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Produto;
import com.mycompany.date_Barbearia.Barbearia_date;


/**
 * Gerencia os produtos
 * @author intalo
 */
public class GestaoProdutos extends Gestao<Produto>{
        
    private static GestaoProdutos instancia;
    private Barbearia_date dados;
    
    GestaoProdutos(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.getListaProdutos();
    }
    
    /**
     * Iniciaçiza com os dados
     * @param dados
     */
    public static void inicializa(Barbearia_date dados) {
            if (instancia == null) {
            instancia = new GestaoProdutos(dados);
        }
    }    
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoProdutos
     */
    public static GestaoProdutos getInstancia(){
        return instancia;
    }    
    
    /**
     * Cadastra um novo produto na lista de produtos
     * @param nome do produto
     * @param custo de compra
     * @param preco de venda
     * @param descricao do produto
     * @throws java.lang.Exception
     */
    public void cadastrar(String nome, double custo, double preco, String descricao) throws Exception{
        verificarprodutoExiste(nome);
        
        Produto novoProduto = construirProduto(nome,custo ,preco, descricao);
        super.adicionar(novoProduto);
    }
    
    /**
     * Cadsstra um produto diretamente
     * @param produto
     * @throws Exception
     */
    public void cadastrar(Produto produto) throws Exception{
        verificarprodutoExiste(produto.getNome());
        super.adicionar(produto);
    }
    
    private Produto construirProduto(String nome,double custo ,double preco, String descricao){
        return new Produto(nome, custo, preco, descricao);
    }
        
    /**
     * Verifica s eum produto existe
     * @param nome do produto a ser verificado
     * @throws Exception 
     */
    private void verificarprodutoExiste(String nome) throws Exception{
        if (buscarPorNomeExato(nome) != null)
            throw new Exception("Já existe um produto cadastrado com o nome: " + nome);       
    }
    
    /**
     * Permite a edicao de informacoes do produto
     * @param produto a ser editado
     * @param nome novo
     * @param preco novo
     * @param descricao nova
     */
    public void editar(Produto produto, String nome, double preco, String descricao){

        if(nome!= null)
            produto.setNome(nome);
        
        if(preco > 0)
            produto.setPreco(preco);
        
        if(descricao != null)
            produto.setDescricao(descricao);
    }    
}