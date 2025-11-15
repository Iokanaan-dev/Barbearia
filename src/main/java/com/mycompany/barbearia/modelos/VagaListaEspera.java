/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.Utilidades.TipoEstacao;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representa uma vaga na lista de espera.
 * @author italo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VagaListaEspera {
    
    private Cliente cliente;
    private ArrayList<Servico> servicosDesejados;
    private Barbeiro barbeiroPreferencial; 
    private LocalDateTime dataDaSolicitacao; 

    /**
     * Construtor completo de uma vaga na lista de espera
     * @param cliente
     * @param servicos
     * @param preferencia
     */
    public VagaListaEspera(Cliente cliente, ArrayList<Servico> servicos, Barbeiro preferencia) {
        this.cliente = cliente;
        this.servicosDesejados = servicos;
        this.barbeiroPreferencial = preferencia;
        this.dataDaSolicitacao = LocalDateTime.now(); 
    }
    
    /**
     * Construtor sem parametros
     */
    public VagaListaEspera(){}

    /**
     * Obtem o cliente da vaga a lista de espera
     * @return
     */
    public Cliente getCliente() { 
        return cliente; 
    }
    
    /**
     * Obtem os servicos da vaga na lista de espera
     * @return
     */
    public ArrayList<Servico> getServicos() { 
        return servicosDesejados; 
    }

    /**
     * Obtem os serviços desejados da vaga na lista de espera
     * @return
     */
    public ArrayList<Servico> getServicosDesejados() {
        return servicosDesejados;
    }

    /**
     * Define o cliente
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Define os servicos desejados
     * @param servicosDesejados
     */
    public void setServicosDesejados(ArrayList<Servico> servicosDesejados) {
        this.servicosDesejados = servicosDesejados;
    }

    /**
     * Define o barbeiro
     * @param barbeiroPreferencial
     */
    public void setBarbeiroPreferencial(Barbeiro barbeiroPreferencial) {
        this.barbeiroPreferencial = barbeiroPreferencial;
    }

    /**
     * Define a data da solicitaçao
     * @param dataDaSolicitacao
     */
    public void setDataDaSolicitacao(LocalDateTime dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }
    
    /**
     * Obtem o barbeiro
     * @return
     */
    public Barbeiro getBarbeiroPreferencial() { 
        return barbeiroPreferencial; 
    }
    
    /**
     * Obtem a data da solicitaçao
     * @return
     */
    public LocalDateTime getDataDaSolicitacao() { 
        return dataDaSolicitacao; 
    }

    /**
     * Obtem a duraçao total em minutos
     * @return
     */
    public int getDuracaoTotalMinutos() {
        return this.servicosDesejados.stream().mapToInt(Servico::getTempoEmMinutos).sum();
    }
    
    /**
     * Obtem tipo da estaçao
     * @return
     */
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