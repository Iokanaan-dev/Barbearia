/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
//import Utilidades.IdGerador;
import Listas.ListaGenerica;



/**
 *
 * @author italo
 */


public class Agendamento extends Modelo{
     
    private static int contador = 0;
    //String nomeAgendamento;
    
    // todos final pois nada podera ser alterado apos entrar no sistema, exceto pelo status, serviços e valor
    private final Cliente cliente;
    private final Barbeiro barbeiro;
    private final Usuario atendente;
    private final Estacao estacao;
    private ListaGenerica<Servico> servicos; // pode ser alterado para adicionar serviços
    
    private final LocalDateTime dataHoraInicioAgendamento;
    private LocalDateTime dataHoraFimAgendameto; // se um serviço for adicionado o horario final muda


    private StatusAgendamento status = StatusAgendamento.PRE_AGENDADO;
    private double valorTotal; // como eh calculado baseado nos servicos, se um servico for adcionado o valor ira alterar
    private double valorRetido; // como eh calculado baseado nos servicos, se um servico for adcionado o valor ira alterar

    public Agendamento(Cliente cliente, Barbeiro barbeiro, Atendente atendente, Estacao estacao, ListaGenerica<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, LocalDateTime dataHoraFimAgendameto, double valorTotal, double valorRetido) {

    private StatusAgendamento status;
    private final double valorTotal;
    private double valorRetido;

    public Agendamento(Cliente cliente, Barbeiro barbeiro, Usuario atendente, Estacao estacao, ArrayList<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, StatusAgendamento status) {
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
        this.dataHoraFimAgendameto =  dataHoraInicioAgendamento.plusMinutes(duracaoTotal);
        
        this.valorTotal = calcularValorTotal();
        this.valorRetido = 0.0;        
    }
    
    private double calcularValorTotal(){
        double total = 0.0;
        for(Servico s : this.servicos){
            total += s.getPreco();
        }
        return total;
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
        return dataHoraFimAgendameto;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorRetido() {
        return valorRetido;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public void setValorRetido(double valorRetido) {
        this.valorRetido = valorRetido;
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
