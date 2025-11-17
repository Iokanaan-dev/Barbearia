/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.util.UUID;
/**
 * Representa um usuario atendente
 * @author italo
 */

public class Atendente extends Usuario{

    
    /**
     * Construtor completo
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     */
    public Atendente(String username,String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(username, senha, nome, cpf, telefone, data_nascimento);
    }
    
    /**
     * Construtor sem parametros
     */
    public Atendente(){
        
    }
    
    /**
     * Gera o ID
     * @return
     */
    @Override
    public String gerarId() {
        return "AT-" + UUID.randomUUID().toString().substring(0, 10);
    }
    
    /**
     * Obtem a representaçao em string
     * @return 
     */
    @Override
    public String toString(){
        return String.format("%nAtendente %s%n%s", getId(), super.toString()); 
    }
}
