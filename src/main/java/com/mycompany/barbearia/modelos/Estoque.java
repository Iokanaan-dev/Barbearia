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
    
    public void adicionarAoEstoque(Produto produto, int quantidade){
        if (quantidade < 0){
            throw new IllegalArgumentException("A quantidade não pode ser negativa!");
        }
        
        int quantidadeAtual = estoque.getOrDefault(produto.getId(), 0); // getOrDefault busca o valor atual. Se não existir, retorna 0.
        estoque.put(produto.getId(), quantidade + quantidadeAtual);
    }   
}
