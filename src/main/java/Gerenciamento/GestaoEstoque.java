/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.Estoque;
import com.mycompany.barbearia.modelos.Produto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author italo
 */
public class GestaoEstoque extends Gestao<Produto> {
    private final Estoque estoque = new Estoque();
    private final ArrayList<Produto> listaProdutosEstoque = new ArrayList<>();
    
    private static GestaoEstoque instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoEstoque
     */
    public static GestaoEstoque getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoEstoque();
        
        return instancia;
    }   
    
    public void adicionarAoEstoque(String id, int quantidade) {  
        if (!existeNoSistema(id)) {
            throw new IllegalArgumentException("Produto não existe no sistema.");
        }

        if (existeEmEstoque(id)) {
            throw new IllegalStateException("Produto já cadastrado no estoque.");
        }

        listaProdutosEstoque.add(GestaoProdutos.getInstancia().buscarPorId(id));
        estoque.setQuantidade(id, quantidade);
    }

    /**
     * Verifica se o produto já foi cadastrado no sistema.
     * @param id
     * @return 
     */
    public boolean existeNoSistema(String id) {
        return GestaoProdutos.getInstancia().buscarPorId(id) != null;      
    }

    /**
     * Verifica se o produto já foi cadastrado no estoque.
     * @param id
     * @return 
     */
    public boolean existeEmEstoque(String id) {
        return estoque.contemProduto(id);      
    }
  
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return ArrayList<>
     */
    public ArrayList<Produto> getLista() {
        return listaProdutosEstoque;
    }     
    
    /**
     * Busca produtos na lista de produtos do estoque usando o nome
     * @param nome
     * @return ArrayList<>
    */
    public ArrayList<Produto> buscarPorNome(String nome){
        return super.procurandoNome(this.listaProdutosEstoque, nome);
    }
     
    /**
     * Imprime todos os produtos em estoque com um certo nome
     * @param nome
     */
    public void printPorNome(String nome){
        super.printLista(buscarPorNome(nome));
    }  
    
    /**
     * Busca um prdtudo na lista do estoque de clientes usando o id
     * @param id
     * 
     * @return Cliente
     */
    public Produto buscarPorId(String id){
        return super.procurandoID(listaProdutosEstoque, id);
    }
    
    /**
     * Imprime o clientes com um certo ID
     * @param id
     */
    public void printPorId(String id){
        super.printItem(buscarPorId(id));
    }
    
    /**
      Remove um produto  do estoque com base no ID informado
     * @param id
     */
    public void remover(String id){
        super.remover(listaProdutosEstoque, id);
    }
            
    public boolean verificacaoQuantidade(String id, int quantidade) {
        if (!estoque.contemProduto(id)) {
            throw new IllegalArgumentException("O produto com o ID " + id + " não existe no estoque.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        return true;
    }
    
    public void aumentarQuantidade(String id, int quantidade){

        if (verificacaoQuantidade(id, quantidade)) {
            int quantidadeAtual = estoque.getQuantidade(id);
            estoque.setQuantidade(id, quantidadeAtual + quantidade); 
        }
    }    
    
    public void reduzirQuantidade(String id, int quantidade){
        
        if (verificacaoQuantidade(id, quantidade)) {
            int quantidadeAtual = estoque.getQuantidade(id);
            
            if(quantidadeAtual < quantidade){
                System.out.println("Não há produtos o suficiente");
                return;
            }  
            
        estoque.setQuantidade(id, quantidadeAtual - quantidade);
        }
    }
    
    public Map<Produto, Integer> getListaQuantidades(){
        Map<Produto, Integer> mapa = new LinkedHashMap<>(); //Ainda é um mapa, mas contem uma listaligada dentro da sua estrutura que armazena a sequencia de itens adicionados (nesse caso), assim os itens irão ficar na sequencia que foram adicionados
        Map<String, Integer> itens = estoque.getTabelaEstoque();
        
        if(itens == null){
            return mapa;
        }
        
        for(Map.Entry<String, Integer> pares : itens.entrySet()){ //Map.Entry vai fazer com que cada "pares" seja um par de <string e Integer>. itens.entrySet vai nos dar o nosso atual estoque em formato de pares também. Assim o for vai passar de par em par. 
            String id = pares.getKey(); // retorna o id do par atual 
            Integer quantidade = pares.getValue(); // retorna a quantidade do par atual
            
            if(quantidade == null || quantidade <= 0)
                continue;
            
            Produto produto = buscarPorId(id);
            
            if(produto != null){
                mapa.put(produto, quantidade); //adicionamos ao nosso mapa, o produto e a sua quantidade caso ele exista na nossa lista de listaProdutosEstoque (pode ser que ele exista no estoque, mas não exista na lista de listaProdutosEstoque)
            } else {
                System.err.println("O produto com id: " + id + " não existe!");
            }
        }
        return mapa;
    }
    
    public void printListaQuantidade() {
        Map<Produto, Integer> mapa = getListaQuantidades(); // pega o mapa produto→quantidade

        for (Map.Entry<Produto, Integer> par : mapa.entrySet()) {
            Produto produto = par.getKey();
            Integer quantidade = par.getValue();
            System.out.println(produto.getNome() + " — Quantidade: " + quantidade);
        }
    }
}
