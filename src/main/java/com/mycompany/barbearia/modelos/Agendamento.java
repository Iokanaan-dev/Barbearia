/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import Utilidades.StatusAgendamento;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author italo
 */


public class Agendamento extends Modelo{
     
    private static int contador = 0;
    //String nomeAgendamento;
    
    private final boolean isEncaixe;
    
    private final Cliente cliente;
    private final Barbeiro barbeiro;
    private final Usuario atendente;
    private final Estacao estacao;
    private final ArrayList<Servico> servicos;
    
    private final LocalDateTime dataHoraInicioAgendamento;
    private final LocalDateTime dataHoraFimAgendamento;

    private StatusAgendamento status;


    public Agendamento(Cliente cliente, Barbeiro barbeiro, Usuario atendente, Estacao estacao, ArrayList<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, StatusAgendamento status, boolean isEncaixe) {
        super(cliente.getNome() + " // " + barbeiro.getNome() + " // " + estacao.getNome());
        
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        //this.nomeAgendamento = (cliente.getNome() + " // " + barbeiro.getNome() + " // " + estacao.getNome());
        this.atendente = atendente;
        this.estacao = estacao;
        this.servicos = servicos;
        this.dataHoraInicioAgendamento = dataHoraInicioAgendamento;
        this.status = status;
        
        int duracaoTotal = calcularDuracaoTotal();
        this.dataHoraFimAgendamento =  dataHoraInicioAgendamento.plusMinutes(duracaoTotal);
        
        this.isEncaixe = isEncaixe;
    }
    
    private int calcularDuracaoTotal(){
        int total = 0;
        for(Servico s : this.servicos){
            total += s.getTempoEmMinutos10();
        }
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }
    

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public boolean isEncaixe() {
        return isEncaixe;
    }
    
    
    public Estacao getEstacao() {
        return estacao;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public LocalDateTime getDataHoraInicioAgendamento() {
        return dataHoraInicioAgendamento;
    }

    public LocalDateTime getDataHoraFimAgendamento() {
        return dataHoraFimAgendamento;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public double getValorDosServicos() {
        return this.servicos.stream().mapToDouble(Servico::getPreco).sum();
    }
    
    /*
    public String getNome(){
        return nomeAgendamento;
    }
    */
    
    @Override
    public String gerarId() {
        return "AGE" + (++contador);
    }
}
