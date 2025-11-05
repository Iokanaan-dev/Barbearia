/*
 * Esta classe é a base de TODOS os modelos de dados (POJOs).
 * Ela só é responsável pelo seu próprio estado (id e nome).
 */
package com.mycompany.barbearia.modelos;

import Utilidades.IdGerador;


/**
 * @author italo
 */
public abstract class Modelo implements IdGerador {
    private String nome;
    private final String id;

    /**
     * Construtor para modelos que TÊM um nome (Cliente, Produto, Servico).
     */
    public Modelo(String nome) {
        validarNome(nome);
        this.nome = nome;
        this.id = this.gerarId(); // A subclasse (ex: Cliente) implementa gerarId()
    }

    /**
     * Construtor para modelos que NÃO TÊM um nome (Agendamento, OrdemServico).
     */
    public Modelo() {
        this.id = this.gerarId(); // A subclasse (ex: Agendamento) implementa gerarId()
    }
    
 
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Campo Vazio!");
    }   
    
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
        if (nome == null)
            return String.format("ID: %s", getId());
        
        return String.format("Nome: %s | ID: %s", getNome(), getId());
    }
}