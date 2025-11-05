/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import Utilidades.TipoEstacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class ListaEspera {
    
    private final Cliente cliente;
    private final ArrayList<Servico> servicosDesejados;
    private final LocalDate dataDesejada;
    private final Barbeiro barbeiroPreferencial; 
    private final LocalDateTime dataDaSolicitacao; 

    public ListaEspera(Cliente cliente, ArrayList<Servico> servicos, LocalDate data, Barbeiro preferencia) {
        this.cliente = cliente;
        this.servicosDesejados = servicos;
        this.dataDesejada = data;
        this.barbeiroPreferencial = preferencia;
        this.dataDaSolicitacao = LocalDateTime.now(); 
    }


    public Cliente getCliente() { 
        return cliente; 
    }
    
    public ArrayList<Servico> getServicos() { 
        return servicosDesejados; 
    }
    
    public LocalDate getDataDesejada() { 
        return dataDesejada; 
    }
    
    public Barbeiro getBarbeiroPreferencial() { 
        return barbeiroPreferencial; 
    }
    
    public LocalDateTime getDataDaSolicitacao() { 
        return dataDaSolicitacao; 
    }


    public int getDuracaoTotalMinutos() {
        return this.servicosDesejados.stream().mapToInt(Servico::getTempoEmMinutos10).sum();
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