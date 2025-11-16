/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.Gerenciamento.GestaoAgendamentos;
import com.mycompany.barbearia.modelos.Agendamento;
import java.util.Comparator;

/**
 *
 * @author intalo
 */
public class ComparatorTempoServicosEmAgendamento implements Comparator<Agendamento> {
    /**
     * Sobreescreve compare 
     * @param agendamento1
     * @param agendamento2
     * @return
     */
    
    // usa a instancia do singleton para usar os metodos de GestaoAgendamento
    GestaoAgendamentos gestaoA = GestaoAgendamentos.getInstancia();
    
    /**
     * Sobreescre o compare usando o tempo de dois agendamentos
     * @param agendamento1
     * @param agendamento2
     * @return
     */
    @Override
    public int compare(Agendamento agendamento1, Agendamento agendamento2){
        int comparaTempo = gestaoA.calcularDuracaoTotal(agendamento1.getServicos()) - gestaoA.calcularDuracaoTotal(agendamento2.getServicos());
        return comparaTempo;
    }
    
    /**
     * Exibe qual agendamento dura menos
     * @param agendamento1
     * @param agendamento2
     */
    public void qualDuraMenos(Agendamento agendamento1, Agendamento agendamento2){
            int resultadoCompare = compare(agendamento1, agendamento2);
            
            if(resultadoCompare > 0)
                System.out.printf("O Agendamento 2: %s, dura menos que o Agendamento 1: %s%n", agendamento2.getId(), agendamento1.getId());
            
            else if(resultadoCompare < 0)
                System.out.printf("O Agendamento 1: %s, dura menos que o Agendamento 2: %s%n", agendamento1.getId(), agendamento2.getId());
            
            else
                System.out.println("Os dois agendamentos duram o mesmo tempo");
    }
}