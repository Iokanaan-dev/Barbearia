/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 * coisa de bobo
 * @author italo
 */
public abstract class Modelo {
    private String nome;
    private final String id;

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
    
    protected abstract String gerarId();    
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    } 

    @Override
    public String toString() {
        return "Modelo{" + "nome=" + nome + ", id=" + id + '}';
    }
}




