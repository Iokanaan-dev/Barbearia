/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Produto;
import com.mycompany.date_Barbearia.Barbearia_date;


/**
 *
 * @author intalo
 */
public class GestaoProdutos extends Gestao<Produto>{
        
    private static GestaoProdutos instancia;
    private Barbearia_date dados;
    
    GestaoProdutos(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.listaProdutos;
    }
    
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
     * @param nome
     * @param preco
     * @param descricao
     */
    public void cadastrar(String nome, double preco, String descricao) throws Exception{
        verificarprodutoExiste(nome);
        
        Produto novoProduto = construirProduto(nome, preco, descricao);
        super.adicionar(novoProduto);
    }
    
    public void cadastrar(Produto produto) throws Exception{
        verificarprodutoExiste(produto.getNome());
        super.adicionar(produto);
    }
    
    private Produto construirProduto(String nome, double preco, String descricao){
        return new Produto(nome, preco, descricao);
    }
        
    
    private void verificarprodutoExiste(String nome) throws Exception{
        if (buscarPorNomeExato(nome) != null)
            throw new Exception("Já existe um produto cadastrado com o nome: " + nome);       
    }
    
    /**
     * Permite a edicao de informacoes do produto
     * @param id
     * @param nome
     * @param preco
     * @param descricao
     */
    public void editar(String id, String nome, double preco, String descricao){

        Produto produto = this.buscarPorId(id);
        
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
    }    
}