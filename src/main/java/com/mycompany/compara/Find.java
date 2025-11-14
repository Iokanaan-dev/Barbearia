/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;

import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.barbearia.modelos.Individuo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author intalo
 */
public class Find {
    
    ComparatorTelefoneIndividuo comparaTelefone = new ComparatorTelefoneIndividuo();
    
    public int findPorTelefone(ArrayList<Cliente> listaClientes, String telefone){
        int indice = 0;
        Iterator<Cliente> iterator = listaClientes.iterator();
        
        while(iterator.hasNext()){
            if(comparaTelefone.isTelefoneIgual(iterator.next().getTelefone(), telefone))
                return indice;
            indice++;
        }
        
        return -1;
    }
    
    public void printClienteComTelefone(ArrayList<Cliente> listaClientes, String telefone){
        int indiceClienteComTelefone = findPorTelefone(listaClientes, telefone);
        
        if(-1 == indiceClienteComTelefone)
            System.out.printf("Nao existe cliente com telefone %s", telefone);
        else
            System.out.printf("Cliente com indice %d possui o telefone %s", indiceClienteComTelefone, telefone);
    }
    
}
