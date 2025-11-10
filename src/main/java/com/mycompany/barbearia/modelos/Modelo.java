/*
 * Esta classe é a base de TODOS os modelos de dados (POJOs).
 * Ela só é responsável pelo seu próprio estado (id e nome).
 */
package com.mycompany.barbearia.modelos;

import com.mycompany.Utilidades.IdGerador;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Objects;

/**
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
        this.id = this.gerarId(); // A subclasse (ex: Cliente) implementa gerarId()
    }

    /**
     * Construtor para modelos que NÃO TÊM um nome (Agendamento, OrdemServico).
     */
    public Modelo() {
       // this.id = this.gerarId(); // A subclasse (ex: Agendamento) implementa gerarId() //comentei pra usar o json daquele naipe 
    }
    
 
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Campo Vazio!");
    }
    
    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.id = id;
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
        
        return String.format("Nome: %s | ID: %s |", getNome(), getId());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Modelo outro = (Modelo) obj;
        return Objects.equals(this.getId(), outro.getId());
}

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
    
    

}