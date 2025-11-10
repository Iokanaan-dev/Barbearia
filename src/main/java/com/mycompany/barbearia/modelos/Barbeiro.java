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
 *
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Barbeiro extends Usuario{

     
    /**
     *
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     */
    public Barbeiro(String username, String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(username, senha, nome, cpf, telefone, data_nascimento);
    }
    
    public Barbeiro(){
     
    }
 
    /**
     *
     * @return
     */
    @Override
    public String gerarId() {
        return "BA-" + UUID.randomUUID().toString().substring(0, 10);
    }
    
    @Override
    public String toString(){
        return String.format("%nBarbeiro %s%n%s", getId(), super.toString()); 
    }
    
}
