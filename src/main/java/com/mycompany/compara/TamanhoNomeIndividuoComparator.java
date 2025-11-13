/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Individuo;
import com.mycompany.barbearia.modelos.Usuario;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author intalo
 */
public class TamanhoNomeIndividuoComparator implements Comparator<Individuo> {
        /**
     * Sobreescreve compare 
     * @param usuario1
     * @param usuario2
     * @return
     */
    @Override
    public int compare(Individuo usuario1, Individuo usuario2){
        
        // compara o tamanho do nome de ambas Strings nomes
        int comparaNome = usuario1.getNome().length() - usuario2.getNome().length();
        
        return comparaNome;
    }
    
    /**
     * Exibe quem tem menor nome
     * @param usuario1
     * @param usuario2
     */
    public void quemTemMenorNome(Individuo usuario1, Individuo usuario2){
            int resultadoCompare = compare(usuario1, usuario2);
            
            if(resultadoCompare > 0)
                System.out.printf("O Nome do individuo %s eh mais curto que o do individuo %s%n", usuario2.getNome(), usuario1.getNome());
            
            else if(resultadoCompare < 0)
                System.out.printf("O nome do individuo %s eh mais curto que o do individuo %s%n", usuario1.getNome(), usuario2.getNome());
            
            else
                System.out.printf("Os individuos %s e %s tem o mesmo tamanho de nome%n", usuario1.getNome(), usuario2.getNome());
    }
}
