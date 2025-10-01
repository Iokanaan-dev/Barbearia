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
 
    private static int count; // contador para gerar o id
    
    public Individuo(String nome, String cpf, String telefone, LocalDate data_nascimento){
        validarNome(nome);
        validarCpf(cpf);
        validarTelefone(telefone);
        validarDataNascimento(data_nascimento);

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.id = ++count; //incrementa o id
    }

    
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo Vazio!");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.length() < 8 || telefone.length() > 11) {
            throw new IllegalArgumentException("Telefone inválido!");
        }
    }

    private void validarDataNascimento(LocalDate data) {
        if (data == null || data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida!");
        }
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        validarDataNascimento(data_nascimento);
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo!");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Individuo{" + "nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", data_nascimento=" + data_nascimento + ", id=" + id + '}';
    }
    
    
}

