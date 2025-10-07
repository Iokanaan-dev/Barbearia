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
public abstract class Individuo extends Modelo{
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    public Individuo(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        super(nome);
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
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
        return dataNascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        validarDataNascimento(data_nascimento);
        this.dataNascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return String.format("%sCPF: %s%nTelefone:%s%nData de Nascimento: %s%n", super.toString(), cpf, telefone, dataNascimento);
    }
}