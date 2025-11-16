/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.*;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe que gerencia a lista de espera dos clientes (modo FIFO)
 * O primeiro cliente a entrar é o primeiro a ser atendido.
 * 
 * @author italo
 */
public class GestaoListaEspera {

    private Queue<VagaListaEspera> filaEspera = new LinkedList<>();
    private static GestaoListaEspera instancia;
    private final Barbearia_date dados;

    //  Construtor privado (Singleton)
    private GestaoListaEspera(Barbearia_date dados) {
        this.dados = dados;
        this.filaEspera = dados.getFilaEspera(); 
    }

    //  Inicializa o Singleton

    /**
     * Incializa com os dados
     * @param dados
     */
    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoListaEspera(dados);
        }
    }

    //  Retorna a instância global

    /**
     * Obtem a instancia
     * @return
     */
    public static GestaoListaEspera getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestaoListaEspera não foi inicializada. Chame inicializar(dados) primeiro.");
        }
        return instancia;
    }

    /**
     * Adiciona um cliente à fila de espera.
     * 
     * @param cliente
     * @param servicos
     * @param preferencial
     * @throws Exception
     */
    public void adicionarClienteEspera(Cliente cliente, ArrayList<Servico> servicos, Barbeiro preferencial) throws Exception {
        if (clienteJaNaEspera(cliente.getId())) {
            throw new Exception("O cliente " + cliente.getNome() + " já está na lista de espera.");
        }

        VagaListaEspera novaEspera = new VagaListaEspera(cliente, servicos, preferencial);

        if (novaEspera.getTipoEstacaoRequerido() == null) {
            throw new Exception("Não é possível adicionar serviços com tipos de estação misturados.");
        }

        this.filaEspera.offer(novaEspera); // adiciona ao final da fila (FIFO)
        System.out.println("Cliente " + cliente.getNome() + " adicionado ao fim da fila de espera (FIFO).");
    }

    /**
     * Consulta o próximo cliente da fila (sem removê-lo)
     * 
     * @return VagaListaEspera do próximo cliente ou null se vazia
     */
    public VagaListaEspera consultarProximoFila() {
        return filaEspera.peek(); // retorna o primeiro da fila sem remover
    }

    /**
     * Remove e retorna o próximo cliente da fila
     * 
     * @return VagaListaEspera removido ou null se a fila estiver vazia
     */
    public VagaListaEspera removerProximoFila() {
        return filaEspera.poll(); // remove o primeiro da fila
    }

    /**
     * Retorna uma cópia da fila atual
     * @return 
     */
    public Queue<VagaListaEspera> getFilaEspera() {
        return new LinkedList<>(this.filaEspera);
    }

    /**
     * Recarrega a fila (usado para persistência)
     * @param filaCarregada
     */
    public void carregarFila(Queue<VagaListaEspera> filaCarregada) {
        if (filaCarregada != null) {
            this.filaEspera.clear();
            this.filaEspera.addAll(filaCarregada);
        }
    }

    /**
     * Verifica se a lista de espera esta vazia
     * @return
     */
    public boolean isVazia() {
        return filaEspera.isEmpty();
    }

    private boolean clienteJaNaEspera(String idCliente) {
        for (VagaListaEspera espera : filaEspera) {
            if (espera.getCliente().getId().equals(idCliente)) {
                return true;
            }
        }
        return false;
    }
}

