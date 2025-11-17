/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Individuo;
import java.util.Comparator;

/**
 * Classe de teste
 * @author intalo
 */
public class Teste2 implements Comparator<Individuo>{
            /**
     * Sobreescreve compare 
     * @param individuo1
     * @param individuo2
     * @return
     */
    @Override
    public int compare(Individuo individuo1, Individuo individuo2){
        
        int comparaTelefone = converterTelefone(individuo1.getTelefone()) - converterTelefone(individuo2.getTelefone());
        
        return comparaTelefone;        
    }
    
    /**
     * Converte um String cpf em um int
     * @param telefone
     * @return
     */
    public int converterTelefone(String telefone){
        
        return Integer.parseInt(telefone);
    }
}


