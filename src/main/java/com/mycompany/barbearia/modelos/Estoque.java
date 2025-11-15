/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa um estoque. Eh uma tabela que associa cada elemento a sua quantidade
 * utilizando o ID como chave
 * @author italo
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Estoque {
    private final Map<String, Integer> tabelaEstoque = new HashMap<>(); // A chave é o ID do Produto (String), e a quantidade é (Integer).
    
    /**
     * Construtor sem Parametros
     */
    public Estoque(){}
    /**
     * Retorna a quantidade de um produto na tabela estoque
     * @param id
     * @return 
     */
    public int getQuantidade(String id){
        return tabelaEstoque.getOrDefault(id, 0);
    }
    
    /**
     * Define a quantidade de um produto no estoque
     * @param produtoID
     * @param quantidade 
     */
    public void setQuantidade(String produtoID, int quantidade){
        tabelaEstoque.put(produtoID, quantidade);
    }
    
    /**
     * Retorna uma copia da tabela estoque
     * @return 
     */
    public Map<String, Integer> getTabelaEstoque(){
        return new HashMap<>(tabelaEstoque);
    }
    
    /**
     * Remove um item da tabela estoque
     * @param ID 
     */
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
    
    /**
     * Verifica se um produto eciste no estoque
     * @param id
     * @return 
     */
    public boolean contemProduto(String id) {
        return tabelaEstoque.containsKey(id);
    }
    
}   
