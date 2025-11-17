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
 * Implementa o find usando telefone para individuos
 * @author intalo
 */
public class Find {
    
    // atributo que cria um objeto que compara telefones
    ComparatorTelefoneIndividuo comparaTelefone = new ComparatorTelefoneIndividuo();
    
    
    /**
     * Prodcura no ArrayList de cliente passado um certo numero de telefone.Retorna o indice se encontrar e -1 se nao encontrar
     * @param listaClientes
     * @param cliente
     * @return
     */
    public int findViaTelefone(ArrayList<Cliente> listaClientes, Cliente cliente){
        int indice = 0;
        
        //cria o iterator para um ArrayList de Cliente
        Iterator<Cliente> iterator = listaClientes.iterator();
        
        while(iterator.hasNext()){
            if(comparaTelefone.isTelefoneIgual(iterator.next(), cliente))
                return indice;
            indice++;
        }
        return -1; // se nao encontrar
    }
    
    /**
     * Metodo que imprime o indice do cliente com o numero de cliente especificado
     * @param listaClientes
     * @param cliente
     */
    public void printClienteEncontrado(ArrayList<Cliente> listaClientes, Cliente cliente){
        int indiceCliente = findViaTelefone(listaClientes, cliente);
        
        if(-1 == indiceCliente)
            System.out.printf("Cliente nao existe%n");
        else
            System.out.printf("Cliente com indice %d%n", indiceCliente);
    }    
}
