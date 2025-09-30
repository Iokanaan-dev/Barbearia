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
public class Individuo {
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate data_nascimento;
    private int id;
    
    public Individuo(String nome, String cpf, String telefone, LocalDate data_nascimento){
        if (cpf == null || cpf.length() != 11){
            throw new IllegalArgumentException("CPF inválido!");
        }
        
        if (telefone == null || telefone.length() != 8) {
            throw new IllegalArgumentException("Telefone Inválido!");
        }
        
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11){
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
         if (telefone == null || telefone.length() != 8) {
            throw new IllegalArgumentException("Telefone Inválido!");
        }
        this.telefone = telefone;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
