/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.individuo;
import java.time.LocalDate;
/**
 *
 * @author italo
 */
public class Gerente extends Funcionario{
    private static int contador = 0;
    
    private String pin_segunraca;
    
    public Gerente(String senha, String nome, String cpf, String telefone, LocalDate data_nascimento, String pin_seguranca) {
        super(senha, nome, cpf, telefone, data_nascimento);
        this.pin_segunraca = pin_seguranca;
    }
    
    @Override
    protected String gerarId() {
        return "G" + (++contador);
    }
}
