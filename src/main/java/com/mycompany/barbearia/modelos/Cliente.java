/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Classe que representa um Cliente
 * @author italo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente extends Individuo{
    
    private String email;
    protected static int contadorInstancia; 
    private static int contador = 0;
    
    /**
     * Construtor de cliente que inicializa todas as variaveis
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
        Cliente.contadorInstancia++;
    }
    
    /**
     * Construtor sem parametros
     */
    public Cliente(){
        super();
    }
    
   private void validarEmail(String email) {
        if (email == null || email.length() < 8 || !(email.contains("@")) || !(email.contains(".com")))
            throw new IllegalArgumentException("Email inválido!");
    }

    /**
     * Obtem o email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Obtem o contador da classe
     * @param contador
     */
    public static void setContador(int contador) {
        Cliente.contador = contador;
    }
   
    /**
     * Obtem o contador da classe
     * @return
     */
    public int getContador() {
        return contador;
    }
      
    /**
     * Gera o Id de um cliente
     * @return
     */
    @Override
    public String gerarId() {
        return "CL-" + UUID.randomUUID().toString().substring(0, 10);
    }

    /**
     * Obtem a representaçao em String de um Cliente
     * @return
     */
    @Override
    public String toString() {
        return String.format("%nCliente %s%n%sE-mail: %s", getId(), super.toString(), getEmail());
    }
}

/*
Usando private com métodos get e set, aplicamos corretamente o princípio do encapsulamento, garantindo que o atributo só possa ser acessado e modificado de maneira controlada. 
Isso aumenta a segurança e a facilidade de manutenção do código, pois evita alterações indevidas e permite inserir validações nos métodos de acesso.
A principal desvantagem dessa abordagem é a necessidade de criar os métodos get e set e sempre utilizá-los para manipular o atributo.

Por outro lado, ao usar o modificador protected, o acesso ao atributo é mais direto e simples, já que ele pode ser acessado sem os métodos get e set pelas classes do mesmo pacote ou por subclasses. 
Mas, isso implica em menor segurança, pois o atributo fica mais exposto e sujeito a modificações indevidas.
*/
