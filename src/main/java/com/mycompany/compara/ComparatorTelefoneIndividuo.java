/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Individuo;
import java.util.Comparator;

/**
 * Classe que implementa o Comparator para o telefone de um individuo
 * @author intalo
 */
public class ComparatorTelefoneIndividuo implements Comparator<Individuo>{
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
     * Exibe quem tem menor nome
     * @param individuo1
     * @param individuo2
     * @return 
     */
    public boolean isTelefoneIgual(Individuo individuo1, Individuo individuo2){
        return 0 == compare(individuo1, individuo2);     
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


