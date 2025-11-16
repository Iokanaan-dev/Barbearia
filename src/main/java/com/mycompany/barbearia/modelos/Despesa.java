/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mycompany.Utilidades.TipoDespesa;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Classe que representa uma despesa
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Despesa extends Modelo{
   
    private double valor;
    private LocalDate dataPagamento;
    private TipoDespesa tipo;
    private String observacoes;
    
    /**
     * Construtor completo de despesa
     * @param nome
     * @param valor
     * @param dataPagamento
     * @param tipo
     * @param observacoes
     */
    public Despesa(String nome, double valor, LocalDate dataPagamento ,TipoDespesa tipo, String observacoes) {
        super(nome);
        
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.tipo = tipo;
        this.observacoes = observacoes;
    }
    
    /**
     * Construtor sem parametros
     */
    public Despesa(){

    }

    /**
     * Obtem o valor
     * @return
     */
    public double getValor() {
        return valor;
    }
    
    /**
     * Obtem a data de pagamento
     * @return
     */
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Define o valor
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Define a data de pagamento
     * @param dataPagamento
     */
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * Define o tipo da despesa
     * @param tipo
     */
    public void setTipo(TipoDespesa tipo) {
        this.tipo = tipo;
    }

    /**
     * Define as observaçoes
     * @param observacoes
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    /**
     * Obtem o tipo da despesa
     * @return
     */
    public TipoDespesa getTipo() {
        return tipo;
    }

    /**
     * Obtem as observaçoes
     * @return
     */
    public String getObservacoes() {
        return observacoes;
    }
    
    /**
     * Gera o ID
     * @return
     */
    @Override
    public String gerarId() {
        return "DESP-" + UUID.randomUUID().toString().substring(0, 10);
    }
}
