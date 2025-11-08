/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;
import java.util.Comparator;
import com.mycompany.barbearia.modelos.Agendamento;

/**
 *
 * @author iarar
 */
public abstract class ComparaAgendamentoClienteID implements Comparator<Agendamento>{
    @Override
    public int compare(Agendamento clienteID, Agendamento clienteID2){
        return clienteID.getCliente().getId().compareTo(clienteID2.getCliente().getId());
    }
}
