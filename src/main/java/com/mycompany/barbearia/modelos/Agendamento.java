/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mycompany.Utilidades.StatusAgendamento;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Representa um agendamento: Associa cliente, barbeiro, estaçao, a lista de 
 * serviços, os horarios de inicio e termino, o valor. Alem disso tambem 
 * armazena um atributo status que indica o status do serviço.
 * 
 * @author italo
 */
@JsonIgnoreProperties(ignoreUnknown = true) // PARA IGNORAR UM CAMPO ANTIGO QUE NÃO ESTA MAIS NO CODIGO DA CLASSE
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Agendamento extends Modelo{
     
    private boolean isEncaixe;
    
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Usuario atendente;
    private Estacao estacao;
    private ArrayList<Servico> servicos;
    private double valorDosServicosCongelado;
    
    private LocalDateTime dataHoraInicioAgendamento;
    private LocalDateTime dataHoraFimAgendamento;
    private StatusAgendamento status;
    
    private String associado_Ordem_Servico = null;
    
    /**
     * Construtor de agendamento que inicizaliza todos os atributos necessarios
     * para controle do mesmo
     * @param cliente
     * @param barbeiro
     * @param atendente
     * @param estacao
     * @param servicos
     * @param dataHoraInicioAgendamento
     * @param status
     * @param isEncaixe
     */
    public Agendamento(Cliente cliente, Barbeiro barbeiro, Usuario atendente, Estacao estacao, ArrayList<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, StatusAgendamento status, boolean isEncaixe, String associado_Ordem_Servico) {
        super();
        
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.atendente = atendente;
        this.estacao = estacao;
        this.servicos = servicos;
        this.dataHoraInicioAgendamento = dataHoraInicioAgendamento;
        this.status = status;
        
        int duracaoTotal = calcularDuracaoTotal();
        this.dataHoraFimAgendamento =  dataHoraInicioAgendamento.plusMinutes(duracaoTotal);
        
        this.isEncaixe = isEncaixe;
        
        this.valorDosServicosCongelado = this.servicos.stream().mapToDouble(Servico::getPreco).sum(); 
    }
    
    /**
     * Construtor sem parametros de Agendamento. Usado principalmente para o 
     * JSON.
     */
    public Agendamento(){}
    
    /**
     * Calcula a duracao to tempo para um atendimento
     * @return o valor em int da duraçao
     */
    private int calcularDuracaoTotal(){
        int total = 0;
        for(Servico s : this.servicos){
            total += s.getTempoEmMinutos();
        }
        return total;
    }

    /**
     * Retorna o Cliente do agendamento
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o valor dos serviços no momento do agendamento
     * @param valorDosServicosCongelado
     */
    public void setValorDosServicosCongelado(double valorDosServicosCongelado) {
        this.valorDosServicosCongelado = valorDosServicosCongelado;
    }

    /**
     * Obtem o Barbeiro
     * @return
     */
    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    /**
     * Obtem o Usuario (Atendente)
     * @return
     */
    public Usuario getAtendente() {
        return atendente;
    }

    /**
     * Obtem a resposta de ha encaixe
     * @return booleano que indica se eh possivel encaixar
     */
    public boolean isEncaixe() {
        return isEncaixe;
    }

    /**
     * Obtem a resposta de ha encaixe
     * @return booleano que indica se eh possivel encaixar
     */
    public boolean isIsEncaixe() {
        return isEncaixe;
    }
    
    /**
     * Obtem a Estacao que sera utilizada para os serviços
     * @return
     */
    public Estacao getEstacao() {
        return estacao;
    }

    /**
     * Obtem a lista com todos os serviços do atendimento
     * @return
     */
    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    /**
     * Obtem a data de inicio do agendamento
     * @return
     */
    public LocalDateTime getDataHoraInicioAgendamento() {
        return dataHoraInicioAgendamento;
    }

    /**
     * Obtem a data de termino do agendamento
     * @return
     */
    public LocalDateTime getDataHoraFimAgendamento() {
        return dataHoraFimAgendamento;
    }

    /**
     * Obtem o status atual do atendimento
     * @return 
     */
    public StatusAgendamento getStatus() {
        return status;
    }

    /**
     * Define o status atual do Agendamento
     * @param status
     */
    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    /**
     * Obtem o valor dos serviços no momento do agendamento
     * @return
     */
    public double getValorDosServicos() {
        return this.valorDosServicosCongelado;
    }

    public double getValorDosServicosCongelado() {
        return valorDosServicosCongelado;
    }

    public String getAssociado_Ordem_Servico() {
        return associado_Ordem_Servico;
    }

    public void setAssociado_Ordem_Servico(String associado_Ordem_Servico) {
        this.associado_Ordem_Servico = associado_Ordem_Servico;
    }
    
    /**
     * Gera o id do objeto
     * @return
     */
    @Override
    public String gerarId() {
        return "AGE-" + UUID.randomUUID().toString().substring(0, 10);
    }
}
