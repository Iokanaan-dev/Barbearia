/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.time.LocalDateTime;

/**
 * Classe que representa um par de entrada e saida
 * @author intalo
 */
public class ParBatida {
    
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public ParBatida() {
    }

    public ParBatida(LocalDateTime entrada) {
        this.entrada = entrada;
    }
    
    
    

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return String.format("%nEntrada: %s - Saida: %s%n", getEntrada(), getSaida());
    }
    
    
}
