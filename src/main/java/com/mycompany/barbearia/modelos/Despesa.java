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
 *
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Despesa extends Modelo{
   
    private double valor;
    private LocalDate dataPagamento;
    private TipoDespesa tipo;
    private String observacoes;
    
    public Despesa(String nome, double valor, LocalDate dataPagamento ,TipoDespesa tipo, String observacoes) {
        super(nome);
        
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.tipo = tipo;
        this.observacoes = observacoes;
    }
    
    public Despesa(){}

    public double getValor() {
        return valor;
    }
    
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setTipo(TipoDespesa tipo) {
        this.tipo = tipo;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public TipoDespesa getTipo() {
        return tipo;
    }

    public String getObservacoes() {
        return observacoes;
    }
    
    @Override
    public String gerarId() {
        return "DESP-" + UUID.randomUUID().toString().substring(0, 10);
    }
}
