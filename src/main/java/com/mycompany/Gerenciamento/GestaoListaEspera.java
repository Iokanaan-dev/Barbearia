/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author italo
 */
public class GestaoListaEspera {
    
    private final Stack<ListaEspera> pilhaEspera = new Stack();
    private static GestaoListaEspera instancia;
    
    private GestaoListaEspera(){}
    
    public static GestaoListaEspera getInstancia(){
        if(instancia == null){
            instancia = new GestaoListaEspera();
        }
        return instancia;
    }
    
    public void adicionarClienteEspera(Cliente cliente, ArrayList<Servico> servicos, LocalDate data, Barbeiro preferencia) throws Exception {
        
        ListaEspera novaEspera = new ListaEspera(cliente, servicos, data, preferencia);
        
        if(novaEspera.getTipoEstacaoRequerido() == null) {
            throw new Exception("Não é possivel adicionar a lista serviços com estações misturadas");
        }
        
        this.pilhaEspera.push(novaEspera);
        System.out.println("Cliente " + cliente.getNome() + " adicionado ao topo da lista de espera (LIFO).");
    }
    
    public ListaEspera consultaProximoPilha(){
        if(pilhaEspera.isEmpty()) {
            return null;
        }
        return pilhaEspera.peek(); //olha o item do topo
    }
    
    public ListaEspera removerProximoPilha(){
        if(pilhaEspera.isEmpty()) {
            return null;
        }
        return pilhaEspera.pop(); //remove item topo
    }

    public Stack<ListaEspera> getPilhaEspera() {
        return pilhaEspera;
    }
    
    
    public boolean isVazia() {
        return pilhaEspera.isEmpty();
    }
}
