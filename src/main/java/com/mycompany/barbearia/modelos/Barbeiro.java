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
public class Barbeiro extends Usuario{
    private static int contador = 0;
     
    public Barbeiro(String username, String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(username,senha, nome, cpf, telefone, data_nascimento);
    }
    
    @Override
    protected String gerarId() {
        return "B" + (++contador);
    }
    
}
