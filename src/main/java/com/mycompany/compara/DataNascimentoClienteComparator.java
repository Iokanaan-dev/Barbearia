/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Cliente;
import java.util.Comparator;

/**
 * Classe que compara data de nascimento de clientes
 * @author intalo
 */
public class DataNascimentoClienteComparator implements Comparator<Cliente> {

    /**
     * Sobreescreve compare 
     * @param cliente1
     * @param cliente2
     * @return
     */
    @Override
    public int compare(Cliente cliente1, Cliente cliente2){
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
    public void quemNasceuPrimeiro(Cliente cliente1, Cliente cliente2){
            int resultadoCompare = compare(cliente1, cliente2);
            
            if(resultadoCompare > 0)
                System.out.printf("O cliente %s nasceu antes do cliente %s%n", cliente2.getNome(), cliente1.getNome());
            
            else if(resultadoCompare < 0)
                System.out.printf("O cliente %s nasceu antes do cliente %s%n", cliente1.getNome(), cliente2.getNome());
            
            else
                System.out.printf("Os clientes %s e %s nasceram no mesmo dia%n", cliente1.getNome(), cliente2.getNome());
    }
}
