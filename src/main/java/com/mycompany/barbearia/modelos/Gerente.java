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

public class Gerente extends Usuario{
    
    private String PIN_SEGURANCA; //usar depois no cadastro de novos usuarios
    
    /**
     * Construtor com todos os atributos de gerente
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param pinSeguranca
     */
    public Gerente(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, String pinSeguranca) {
        super(username,senha, nome, cpf, telefone, dataNascimento);
        
        validarPIN(pinSeguranca);
        this.PIN_SEGURANCA = pinSeguranca;
    }
    
    /**
     * Construtor sem parametros de Gerente
     */
    public Gerente(){
        super();
    }   
    
    /**
     * Valida o PIN
     * @param pin_seguranca 
     */
     private void validarPIN(String pin_seguranca){
        if(pin_seguranca == null || pin_seguranca.trim().length() < 4){
            throw new IllegalArgumentException("A senha não pode ser vazia, ou com menos de 8 caracteres!");
        }
    }
     
    /**
     * Verifica o PIN. Eh usaod em outras classes para questoes de seguranca
     * @param pin
     * @return
     */
    public boolean verficarPinADM(String pin){
        return this.PIN_SEGURANCA.equals(pin);
    }

    public String getPIN_SEGURANCA() {
        return PIN_SEGURANCA;
    }

    public void setPIN_SEGURANCA(String PIN_SEGURANCA) {
        this.PIN_SEGURANCA = PIN_SEGURANCA;
    }
   
    /**
     * Gera o ID
     * @return
     */
    @Override
    public String gerarId() {
        return "GE-" + UUID.randomUUID().toString().substring(0, 10);
    }
    
    /**
     * Obtem a representaçao em String do Gerente
     * @return 
     */
    @Override
    public String toString(){
        return String.format("%nGerente %s%n%s", getId(), super.toString()); 
    }
    
}
