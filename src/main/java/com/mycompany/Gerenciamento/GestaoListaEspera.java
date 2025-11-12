/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.*;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author italo
 */
public class GestaoListaEspera {
    
    private Stack<ListaEspera> pilhaEspera = new Stack<>();
    private static GestaoListaEspera instancia;
    public Barbearia_date dados;
    
    private GestaoListaEspera(Barbearia_date dados ){
        this.dados = dados;
        this.pilhaEspera = dados.getListaDeEspera();
    }
    
    public static void inicializar(Barbearia_date dados){
        if(instancia == null){
            instancia = new GestaoListaEspera(dados);
        }
    }
    
    public static GestaoListaEspera getInstancia(){
        return instancia;
    }
    
    public void adicionarClienteEspera(Cliente cliente, ArrayList<Servico> servicos, Barbeiro preferencia) throws Exception {
        
        if (clienteJaNaEspera(cliente.getId())) {
            throw new Exception("O cliente " + cliente.getNome() + " já está na lista de espera.");
        }
        
        ListaEspera novaEspera = new ListaEspera(cliente, servicos, preferencia);
        
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
        return (Stack<ListaEspera>) this.pilhaEspera.clone();
    }
    
    public void carregarPilha(Stack<ListaEspera> pilhaCarregada) {
        if (pilhaCarregada != null) {
            this.pilhaEspera.clear();
            this.pilhaEspera.addAll(pilhaCarregada);
        }
    }
    
    public boolean isVazia() {
        return pilhaEspera.isEmpty();
    }
    
    private boolean clienteJaNaEspera(String idCliente) {
    for (ListaEspera espera : pilhaEspera) {
        if (espera.getCliente().getId().equals(idCliente)) {
            return true;
        }
    }
    return false;
}
}
