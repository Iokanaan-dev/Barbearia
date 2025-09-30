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
public class Barbeiro extends Funcionario{

    public Barbeiro(String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(senha, nome, cpf, telefone, data_nascimento);
    }
    
}
