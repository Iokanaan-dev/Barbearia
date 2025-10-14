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
    private final Map<String, Integer> estoque = new HashMap(); // A chave é o ID do Produto (String), e a quantidade é (Integer).
    
    /**
     *
     * @param produto
     * @param quantidade
     */
    
    //retorna a quatidade de um produto X no estoque atualmente
    public int getQuantidade(String produtoID){
        return estoque.getOrDefault(produtoID, 0);
    }
    
    //Define a quantidade de um produto 
    public void setQuantidade(String produtoID, int quantidade){
        estoque.put(produtoID, quantidade);
    }
    
    public Map<String, Integer> getEstoque(){
        return new HashMap<>(this.estoque);
    }
    
    public void remover(String ID){
        estoque.remove(ID);
    }
    
    public boolean isEmpty(){
        return this.estoque.isEmpty();
    }
}   
