/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Listas.ListaGenerica;
import com.mycompany.barbearia.modelos.Produto;
import com.mycompany.barbearia.modelos.Modelo;
import java.util.ArrayList;

/**
 *
 * @author intalo
 */
public class GestaoProdutos{
    
    private ListaGenerica<Produto> listaProdutos = new ListaGenerica();
    
    // metodo que coleta os dados do cliente para o construtor e passa para adicionar cliente
    public void cadastrarProduto(String nome, double preco, String descricao){
        Produto novoProduto = new Produto(nome, preco, descricao);
        this.listaProdutos.adicionar(novoProduto);
    }
    
    public void editarProduto(Produto produto, String nome, double preco, String descricao){

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
    }
    
    public Produto buscarId(String ID){
        Produto produtoSelecionado = this.listaProdutos.buscaPorId(ID);
        
        if(produtoSelecionado == null)
            System.out.println("Nenhum produto encontrado.");
        return produtoSelecionado;
    }    
    
    public ArrayList<Produto> getLista()
    {
        return this.listaProdutos.getLista();
    }
    
}

