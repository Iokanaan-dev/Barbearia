/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.Utilidades.TipoEstacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaEspera {
    
    private Cliente cliente;
    private ArrayList<Servico> servicosDesejados;
    private Barbeiro barbeiroPreferencial; 
    private LocalDateTime dataDaSolicitacao; 

    public ListaEspera(Cliente cliente, ArrayList<Servico> servicos, Barbeiro preferencia) {
        this.cliente = cliente;
        this.servicosDesejados = servicos;
        this.barbeiroPreferencial = preferencia;
        this.dataDaSolicitacao = LocalDateTime.now(); 
    }
    
    public ListaEspera(){}

    public Cliente getCliente() { 
        return cliente; 
    }
    
    public ArrayList<Servico> getServicos() { 
        return servicosDesejados; 
    }

    public ArrayList<Servico> getServicosDesejados() {
        return servicosDesejados;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServicosDesejados(ArrayList<Servico> servicosDesejados) {
        this.servicosDesejados = servicosDesejados;
    }

    public void setBarbeiroPreferencial(Barbeiro barbeiroPreferencial) {
        this.barbeiroPreferencial = barbeiroPreferencial;
    }

    public void setDataDaSolicitacao(LocalDateTime dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }
    
    public Barbeiro getBarbeiroPreferencial() { 
        return barbeiroPreferencial; 
    }
    
    public LocalDateTime getDataDaSolicitacao() { 
        return dataDaSolicitacao; 
    }


    public int getDuracaoTotalMinutos() {
        return this.servicosDesejados.stream().mapToInt(Servico::getTempoEmMinutos).sum();
    }
    
    
    
    public TipoEstacao getTipoEstacaoRequerido() {
        
        if (servicosDesejados == null || servicosDesejados.isEmpty()) return null;
        
        TipoEstacao tipoRequerido = servicosDesejados.get(0).getTipoEstacaoRequerido();
        for (Servico s : servicosDesejados) {
            if (s.getTipoEstacaoRequerido() != tipoRequerido) {
                return null; 
            }
        }
        return tipoRequerido;
    }
}