/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import Utilidades.IdGerador;

/**
 *
 * @author italo
 */
public abstract class Modelo implements IdGerador {
    private String nome;
    private final String id;

    /**
     *
     * @param nome
     */
    public Modelo(String nome) {
        validarNome(nome);
        this.nome = nome;
        this.id = this.gerarId();
    }
    
        private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo Vazio!");
        }
    }
    
    /**
     *
     * @return
     */
    //protected abstract String gerarId();    
        
    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    } 

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%nNome: %s%nID:%s%n", nome, id);
    }
}




