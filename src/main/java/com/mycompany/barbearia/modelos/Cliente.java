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
public class Cliente extends Individuo{
    private String email;
    private static int contador = 0;
    
    /**
     *
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     * @param email
     */
    
    public Cliente(String nome, String cpf, String telefone, LocalDate data_nascimento, String email) {
        super(nome, cpf, telefone, data_nascimento);
        this.validarEmail(email);
        this.email = email;
    }
    
    public Cliente(Cliente prototipo){
        super(prototipo);
        this.email = prototipo.getEmail();
    }
    
   private void validarEmail(String email) {
        if (email == null || email.length() < 8 || !(email.contains("@")) || !(email.contains(".com")))
            throw new IllegalArgumentException("Email inválido!");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      
    /**
     *
     * @return
     */
    @Override
    public String gerarId() {
        return "CL" + (++contador);
    }

    @Override
    public String toString() {
        return String.format("%nCliente %s%n%sE-mail: %s", getId(), super.toString(), getEmail());
    }
}
