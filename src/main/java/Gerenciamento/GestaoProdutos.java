/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.Produto;
import java.util.ArrayList;

/**
 *
 * @author intalo
 */
public class GestaoProdutos extends Gestao<Produto>{
        
    private static GestaoProdutos instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoProdutos
     */
    public static GestaoProdutos getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoProdutos();
        
        return instancia;
    }    
    
    /**
     * Cadastra um novo produto na lista de produtos
     * @param nome
     * @param preco
     * @param descricao
     */
    public void cadastrar(String nome, double preco, String descricao){
        Produto novoProduto = new Produto(nome, preco, descricao);
        super.adicionar(novoProduto);
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