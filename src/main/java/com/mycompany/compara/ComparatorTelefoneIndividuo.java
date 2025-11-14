/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Individuo;
import java.util.Comparator;

/**
 *
 * @author intalo
 */
public class ComparatorTelefoneIndividuo implements Comparator<String>{
            /**
     * Sobreescreve compare 
     * @param usuario1
     * @param usuario2
     * @return
     */
    @Override
    public int compare(String telefone1, String telefone2){
        
        int comparaTelefone = converterTelefone(telefone1) - converterTelefone(telefone2);
        
        return comparaTelefone;        
    }
    
    /**
     * Exibe quem tem menor nome
     * @param cpf1
     * @param cpf2
     * @return 
     */
    public boolean isTelefoneIgual(String cpf1, String cpf2){
        return 0 == compare(cpf1, cpf2);
            
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


