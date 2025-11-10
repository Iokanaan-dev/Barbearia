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
 */@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Gerente extends Usuario{
    
    private String PIN_SEGURANCA; //usar depois no cadastro de novos usuarios
    
    /**
     *
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public Gerente(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, String pinSeguranca) {
        super(username,senha, nome, cpf, telefone, dataNascimento);
        
        validarPIN(pinSeguranca);
        this.PIN_SEGURANCA = pinSeguranca;
    }
    
    public Gerente(){
        
    }
    
     private void validarPIN(String pin_seguranca){
        if(pin_seguranca == null || pin_seguranca.trim().length() < 4){
            throw new IllegalArgumentException("A senha não pode ser vazia, ou com menos de 8 caracteres!");
        }
    }
     
    /**
     *
     * @param pin
     * @return
     */
    public boolean verficarPinADM(String pin){
        return this.PIN_SEGURANCA.equals(pin);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String gerarId() {
        return "GE-" + UUID.randomUUID().toString().substring(0, 10);
    }
    
        @Override
    public String toString(){
        return String.format("%nGerente %s%n%s", getId(), super.toString()); 
    }
}
