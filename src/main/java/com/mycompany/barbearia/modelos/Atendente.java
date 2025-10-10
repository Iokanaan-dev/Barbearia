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
public class Atendente extends Usuario{
    private static int contador = 0;
    
    /**
     *
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     */
    public Atendente(String username,String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(username,senha, nome, cpf, telefone, data_nascimento);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String gerarId() {
        return "AT" + (++contador);
    }
}
