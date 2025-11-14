/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.barbearia.modelos.Individuo;
import java.util.Comparator;

/**
 * Classe que compara data de nascimento de clientes
 * @author intalo
 */
public class ComparatorDataNascimentoIndividuo implements Comparator<Individuo> {

    /**
     * Sobreescreve compare 
     * @param cliente1
     * @param cliente2
     * @return
     */
    @Override
    public int compare(Individuo cliente1, Individuo cliente2){
        int comparaAno = cliente1.getDataNascimento().getYear() - cliente2.getDataNascimento().getYear();
        
        // testa o ano primeiro
        if(comparaAno != 0)
            return comparaAno;
        
        // testa os meses
        int comparaMes = cliente1.getDataNascimento().getMonthValue() - cliente2.getDataNascimento().getMonthValue();
        if(comparaMes != 0)
            return comparaMes;
        
        // compara os dias
        int comparaDia = cliente1.getDataNascimento().getDayOfMonth() - cliente2.getDataNascimento().getDayOfMonth();
        return comparaDia;
    }
    
    /**
     * Retorna quem nasceu primeiro
     * @param cliente1
     * @param cliente2
     */
    public void quemNasceuPrimeiro(Individuo cliente1, Individuo cliente2){
            int resultadoCompare = compare(cliente1, cliente2);
            
            if(resultadoCompare > 0)
                System.out.printf("O individuo %s nasceu antes do individuo %s%n", cliente2.getNome(), cliente1.getNome());
            
            else if(resultadoCompare < 0)
                System.out.printf("O individuo %s nasceu antes do individuo %s%n", cliente1.getNome(), cliente2.getNome());
            
            else
                System.out.printf("Os individuos %s e %s nasceram no mesmo dia%n", cliente1.getNome(), cliente2.getNome());
    }
    
    /**
     * Retorna true se os individuos tiverem a mesma data de aniversario
     * @param individuo1
     * @param individuo2
     * @return
     */
    public boolean isIgual(Individuo individuo1, Individuo individuo2){
        return (0 == compare(individuo1, individuo2));
    }
}
