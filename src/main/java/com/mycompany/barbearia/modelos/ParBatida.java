/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;


import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Classe que representa um par de entrada e saida
 * @author intalo
 */

public class ParBatida {
    
    private LocalDateTime entrada;
    private LocalDateTime saida;

    /**
     * Construtor sem parametros. Nao utilizado
     */
    public ParBatida() {}

    /**
     * Construtor que recebe a entrada. Usado em GestaPonto para criar objetos
     * que representam pares de batida
     * @param entrada
     */
    public ParBatida(LocalDateTime entrada) {
        this.entrada = entrada;
    }
    
    /**
     * Obtem o valor da entrada
     * @return
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }
    
    

    /**
     * Altera o valor da entrada
     * @param entrada
     */
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    /**
     * Obtem o valor da saida
     * @return
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     *  Alterar o valor da saida
     * @param saida
     */
    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }
    
    /**
     * Retorna o valor de entrada em double. Retorna o valor em segundos desde 
     * 1970 ate o momento da batida. Usado no calculo do tempo que o funcionario
     * trabalhou

     * @return
     */
    public double horasEntradaEmDouble(){        
        return entrada.atZone(ZoneId.systemDefault()).toEpochSecond();
    }  
    
   /**
     * Retorna o valor de saida em double. Retorna o valor em segundos desde 
     * 1970 ate o momento da batida. Usado no calculo do tempo que o funcionario
     * trabalhou
     * @return
     */
    public double horasSaidaEmDouble(){
        return saida.atZone(ZoneId.systemDefault()).toEpochSecond();
    }     

    /**
     * Representaçao em String do objeto
     * @return
     */
    @Override
    public String toString() {
        return String.format("Entrada: %s - Saida: %s%n", getEntrada(), getSaida());
    }
    
    
}
