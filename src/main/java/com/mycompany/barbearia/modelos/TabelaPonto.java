/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author intalo
 */
public class TabelaPonto {
    private final Map<String, ArrayList<ParBatida>> tabelaPonto = new HashMap(); // A chave é o ID do Usuario (String), e as horas sao um par de objetos de entrada e saida.
    
    /**
     *
     * @param id
     * @return 
     */
    public ArrayList<ParBatida> getParBatida(String id){
        return tabelaPonto.getOrDefault(id, null);
    }

    public Map<String, ArrayList<ParBatida>> getTabelaPontos() {
        return tabelaPonto;
    }
    
    public boolean contemUsuario(String id) {
        return tabelaPonto.containsKey(id);
    }
}
