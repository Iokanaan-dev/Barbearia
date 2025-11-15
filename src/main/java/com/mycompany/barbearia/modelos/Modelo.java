/*
 * Esta classe é a base de TODOS os modelos de dados (POJOs).
 * Ela só é responsável pelo seu próprio estado (id e nome).
 */
package com.mycompany.barbearia.modelos;

import com.mycompany.Utilidades.IdGerador;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Classe que representa um Modelo de qualquer entidade do sistema. Utilizado
 * principalmente para facilitar o tornar as gestoes genericas
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Modelo implements IdGerador { 

    private String nome;
    private String id;

    /**
     * Construtor para modelos que TÊM um nome (Cliente, Produto, Servico).
     * @param nome
     */
    public Modelo(String nome) {
        validarNome(nome);
        this.nome = nome;
        if (this.id == null) {
        this.id = this.gerarId();
        }
    }

    /**
     * Construtor para modelos que NÃO TÊM um nome (Agendamento, OrdemServico).
     */
    public Modelo() {
       this.id = this.gerarId(); // A subclasse (ex: Agendamento) implementa gerarId() //comentei pra usar o json daquele naipe 
    }
    
    /**
     * Valida o nome de um Modelo
     * @param nome 
     */
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Campo Vazio!");
    }
    
    /**
     * Obtem o nome
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
   
    /**
     * Define o nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Obtem o Id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna a representaçao em String de um Modelo
     * @return
     */
    @Override
    public String toString() {
        if (nome == null)
            return String.format("ID: %s", getId());
        
        return String.format("Nome: %s | ID: %s |", getNome(), getId());
    }
}