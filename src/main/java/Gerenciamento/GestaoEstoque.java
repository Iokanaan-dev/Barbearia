/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.Estoque;
import com.mycompany.barbearia.modelos.Produto;
import Listas.ListaGenerica;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    
    public Map<Produto, Integer> getPrdouto_Quantidade(){
        Map<Produto, Integer> mapa = new LinkedHashMap<>(); //Ainda é um mapa, mas contem uma listaligada dentro da sua estrutura que armazena a sequencia de itens adicionados (nesse caso), assim os itens irão ficar na sequencia que foram adicionados
        Map<String, Integer> itens = estoque.getEstoque();
        
        if(itens == null){
            return mapa;
        }
        
        for(Map.Entry<String, Integer> pares : itens.entrySet()){ //Map.Entry vai fazer com que cada "pares" seja um par de <string e Integer>. itens.entrySet vai nos dar o nosso atual estoque em formato de pares também. Assim o for vai passar de par em par. 
            String id = pares.getKey(); //Retonra o id do par atual 
            Integer quantidade = pares.getValue(); // retorna a quantidade do par atual
            
            if(quantidade == null || quantidade <= 0){
                continue;
            }
            
            Produto produto = this.buscarProdutoID(id);
            
            if(produto != null){
                mapa.put(produto, quantidade); //adicionamos ao nosso mapa, o produto e a sua quantidade caso ele exista na nossa lista de produtos (pode ser que ele exista no estoque, mas não exista na lista de produtos)
            } else {
                System.err.println("O produto com id: " + id + " não existe!");
            }
        }
        return mapa;
    }

    @Override
    public String toString() {
        return "GestaoEstoque{" + "estoque=" + estoque + ", produtos=" + produtos + '}';
    }
}
