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
    
    private final String pin_segunraca; //usar depois no cadastro de novos usuarios
    
    /**
     *
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     * @param pin_seguranca
     */
    public Gerente(String username, String senha, String nome, String cpf, String telefone, LocalDate data_nascimento, String pin_seguranca) {
        super(username,senha, nome, cpf, telefone, data_nascimento);
        
        validarPIN(pin_seguranca);
        this.pin_segunraca = pin_seguranca;
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
        return this.pin_segunraca.equals(pin);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String gerarId() {
        return "G" + (++contador);
    }
}
