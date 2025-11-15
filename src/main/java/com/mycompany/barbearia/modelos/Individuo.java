/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
/**
 *
 * @author italo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Individuo extends Modelo{
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    /**
     * Construitor que inicializa todos os atributos de Individuo
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public Individuo(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        super(nome); // chama o construtor de Modelo para inicializar a variavel nome
        
        validarCpf(cpf);
        validarTelefone(telefone);
        validarDataNascimento(dataNascimento);
        
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    
    /**
     * Construtor sem parametros
     */
    public Individuo() {
        super();
    }
    
    /**
     * Valida o cpf
     * @param cpf 
     */
    private static void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }
    }

    /**
     * Valida o telefone
     * @param telefone 
     */
    private static void validarTelefone(String telefone) {
        if (telefone == null || telefone.length() < 8 || telefone.length() > 11) {
            throw new IllegalArgumentException("Telefone inválido!");
        }
    }

    /**
     * Valida a data de nascimento
     * @param data 
     */
    private void validarDataNascimento(LocalDate data) {
        if (data == null || data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida!");
        }
    }
    
    /**
     * Obtem o cpf
     * @return
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * Obtem o cpt Anonimizado
     * @return
     */
    @JsonIgnore 
    public String getCpfAnonimizado() {
        return "*****" + cpf.substring(6); // pseudoanenomizado de forma precaria por hora
    }
    
    /**
     * Define o cpf 
     * @param cpf
     */
    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    /**
     * Obtem o telefone
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone
     * @param telefone
     */
    public void setTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone;
    }

    /**
     * Obtem a data de nascimento
     * @return
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento
     * @param dataNascimento
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        validarDataNascimento(dataNascimento);
        this.dataNascimento = dataNascimento;
    }
    
    /**
     * Retorna a representacao em String de um Individuo
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%sCPF: %s%nTelefone: %s%nData de Nascimento: %s%n", super.toString(), getCpfAnonimizado(), getTelefone(), getDataNascimento());
    }
}