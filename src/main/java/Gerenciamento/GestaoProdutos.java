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
    
    private final ArrayList<Produto> listaProdutos = new ArrayList();
    
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
        super.adicionar(listaProdutos, novoProduto);
    }
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return ArrayList
     */
    public ArrayList<Produto> getLista() {
        return listaProdutos;
    }

    /**
     * Busca produtos na lista de produtos usando o nome
     * @param nome
     * @return
     */
    public ArrayList<Produto> buscarPorNome(String nome){
        return super.buscarPorNome(listaProdutos, nome);
    }

    /**
     * Imprime todos os produtos com um certo nome
     * @param nome
     */
    public void printPorNome(String nome){
        super.printLista(buscarPorNome(nome));
    }    
    
    /**
     * Busca um cliente na lista de clientes usando o id
     * @param id
     * @return
     */
    public Produto buscarPorId(String id){
        return super.buscarPorId(listaProdutos, id);
    } 
    
    /**
     * Imprime o produto com um certo ID
     * @param id
     */
    public void printPorId(String id){
        super.printItem(buscarPorId(id));
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
    
    /**
      Remove um produto com base no ID informado
     * @param id
     */
    public void remover(String id){
        super.remover(listaProdutos, id);
    }   
    
    /**
     * Imprime a lista de produtos atual
     */
    public void printLista(){
        super.printLista(listaProdutos);
    }      
}