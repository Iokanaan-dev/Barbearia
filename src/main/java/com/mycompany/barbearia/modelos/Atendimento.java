/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import java.util.ArrayList;

/**
 * Representa a "visita" ou "comanda" do cliente.
 * É o contêiner financeiro que agrupa todos os serviços e produtos.
 */
public class Atendimento extends Modelo {
    private static int contador = 0;
    
    private final Cliente cliente;
    
    // Um atendimento pode ter várias etapas (agendamentos)
    private final ArrayList<Agendamento> agendamentos = new ArrayList<>();
    
    // Futuramente loja:
    // private final ArrayList<ProdutoVendido> produtos = new ArrayList<>();
    
    private StatusAtendimento status; 
    
    private double valorTotal;
    private double valorRetidoCancelamento; // A soma de todas as taxas

    public Atendimento(Cliente cliente) {
        super("Atendimento " + cliente.getNome());
        this.cliente = cliente;
        this.status = StatusAtendimento.ABERTO;
        this.valorTotal = 0.0;
        this.valorRetidoCancelamento = 0.0;
    }
    
    @Override
    public String gerarId() {
        return "Atend" + ++contador;
    }
    
    /**
     * Adiciona um passo (agendamento) a esta visita e recalcula o valor total.
     */
    public void adicionarAgendamento(Agendamento ag) {
        this.agendamentos.add(ag);
        this.recalcularValorTotal();
    }
    
    public void recalcularValorTotal() {
        double total = 0.0;
        for (Agendamento ag : this.agendamentos) {
            total += ag.getValorDosServicos();
        }
        // (futuramente, somar produtos) 
        this.valorTotal = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorRetidoCancelamento() {
        return valorRetidoCancelamento;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorRetidoCancelamento(double valorRetidoCancelamento) {
        this.valorRetidoCancelamento = valorRetidoCancelamento;
    }
    
}


