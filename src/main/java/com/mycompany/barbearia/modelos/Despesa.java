/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import Utilidades.TipoDespesa;
import java.time.LocalDate;

/**
 *
 * @author italo
 */
public class Despesa extends Modelo{
    
    private static int contador = 0;
    
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

    public double getValor() {
        return valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public TipoDespesa getTipo() {
        return tipo;
    }

    public String getObservacoes() {
        return observacoes;
    }
    
    @Override
    public String gerarId() {
        return "DESP" + (++contador);
    }
}
