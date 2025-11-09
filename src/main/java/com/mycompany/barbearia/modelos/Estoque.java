/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author italo
 */
public class Estoque {
    private final Map<String, Integer> tabelaEstoque = new HashMap<>(); // A chave é o ID do Produto (String), e a quantidade é (Integer).
    
    /**
     *
     * @param produto
     * @param quantidade
     */
    
    public Estoque(){}
    
    //retorna a quatidade de um produto X na tabelaEstoque atualmente
    public int getQuantidade(String id){
        return tabelaEstoque.getOrDefault(id, 0);
    }
    
    //Define a quantidade de um produto 
    public void setQuantidade(String produtoID, int quantidade){
        tabelaEstoque.put(produtoID, quantidade);
    }
    
    public Map<String, Integer> getTabelaEstoque(){
        return new HashMap<>(tabelaEstoque);
    }
    
    public void remover(String ID){
        tabelaEstoque.remove(ID);
    }
    
    /**
     * Limpa o inventário atual e o substitui pelo mapa carregado do JSON.
     * (Este método é chamado pelo 'GestaoEstoque').
     * @param mapaCarregado O mapa (ID -> Quantidade) lido do arquivo.
     */
    public void carregarMapa(Map<String, Integer> mapaCarregado) {
        if (mapaCarregado != null) {
            this.tabelaEstoque.clear(); // Limpa o estado atual
            this.tabelaEstoque.putAll(mapaCarregado); // Adiciona todos os dados carregados
        }
    }
    
    
    public boolean contemProduto(String id) {
        return tabelaEstoque.containsKey(id);
    }
    
}   
