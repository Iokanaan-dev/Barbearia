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

    public Individuo(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        super(nome); // chama o construtor de Modelo para inicializar a variavel nome
        
        validarCpf(cpf);
        validarTelefone(telefone);
        validarDataNascimento(dataNascimento);
        
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    
    public Individuo() {
        super();
    }
    
    //@Override //pensa depois se eh necessario existir essa sobreescrita aqui, as subclasses ja nao sobreescrevem por si? precisa disso para fazer a conexao entre MOdelo e subclasses de Individuo?
    //public abstract String gerarId();
    
    private static void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }
    }

    private static void validarTelefone(String telefone) {
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
    
    @JsonIgnore 
    public String getCpfAnonimizado() {
        return "*****" + cpf.substring(6); // pseudoanenomizado de forma precaria por hora
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate data_nascimento) {
        validarDataNascimento(data_nascimento);
        this.dataNascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return String.format("%sCPF: %s%nTelefone: %s%nData de Nascimento: %s%n", super.toString(), getCpfAnonimizado(), getTelefone(), getDataNascimento());
    }
    


}