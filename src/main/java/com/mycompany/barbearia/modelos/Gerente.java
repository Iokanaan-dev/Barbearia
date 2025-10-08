/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import java.time.LocalDate;
/**
 *
 * @author italo
 */
public class Gerente extends Usuario{
    private static int contador = 0;
    
    private final String PIN_SEGURANCA; //usar depois no cadastro de novos usuarios
    
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
    protected String gerarId() {
        return "GE" + (++contador);
    }
}
