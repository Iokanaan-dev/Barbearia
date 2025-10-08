/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.Estoque;
import com.mycompany.barbearia.modelos.Produto;
import Listas.ListaGenerica;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author italo
 */
public class GestaoEstoque {
    private final Estoque estoque = new Estoque();
    private final ListaGenerica<Produto> produtos = new ListaGenerica();
    
    public void cadastrarNovoProduto(String nome, double preco){    
        for (Produto p : this.produtos.getLista()) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("O produto já existe!");
            }
        }
        Produto novoProduto = new Produto(nome, preco);
        this.produtos.adicionar(novoProduto);
        this.estoque.setQuantidade(novoProduto.getId(), 0);        
    }
    
    public void adicionarProdutoEstoque(String id, int quantidade){

        if (buscarProdutoID(id) == null) {
            System.out.println("Esse produto não existe");
        }
        if (quantidade <= 0) {
            System.out.println("Não é possível adicionar 0 produtos");
        }
        int quantidadeAtual = this.estoque.getQuantidade(id);
        this.estoque.setQuantidade(id, quantidadeAtual + quantidade);
    }
    
    public Produto buscarProdutoID(String ID){
        return this.produtos.buscaPorId(ID);
    }
    
    public ArrayList<Produto> buscaProdutoNome(String nome){
        ArrayList<Produto> selecionados = this.produtos.buscaPorNome(nome);
        return selecionados;
    }   
    
    public void removerProdutoEstoque(String id, int quantidade){
        if (buscarProdutoID(id) == null) {
            System.out.println("Esse produto não existe");
        }
        if (quantidade <= 0) {
            System.out.println("Não é possível adicionar 0 produtos");
        }
        int quantidadeAtual = this.estoque.getQuantidade(id);
        if(quantidadeAtual < quantidade){
            System.out.println("Não há produtos o suficiente");
        }        
        this.estoque.setQuantidade(id, quantidadeAtual - quantidade);
    }
    
    

    @Override
    public String toString() {
        return "GestaoEstoque{" + "estoque=" + estoque + ", produtos=" + produtos + '}';
    }
}
