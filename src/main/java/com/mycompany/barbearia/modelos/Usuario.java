    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;

/**
 * // classe que representa um usuario do sistema, um funcionario padrao ou adm
 * @author italo
 */

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "tipoUsuario"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = Gerente.class, name = "Gerente"),
  @JsonSubTypes.Type(value = Barbeiro.class, name = "Barbeiro"),
  @JsonSubTypes.Type(value = Atendente.class, name = "Atendente")
})
// isso corrige um erro do jackson não saber qual classe atribuir em um agendamento onde o tipo requerido é "Usuario"


public abstract class Usuario extends Individuo{
    
    /**
     * Construtor sem parametros
     */
    public Usuario(){}

    private String username;
    private String senha;

    /**
     * Construtor com todos os parametros de Usuario
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param data_nascimento
     */
    public Usuario(String username, String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(nome, cpf, telefone, data_nascimento);
        
        validarUsername(username);
        this.username = username;
        validarSenha(senha);
        this.senha = senha;
    }
    
    /**
     * Valida o username
     * @param userName
     */
    private static void validarUsername(String userName){
        if (userName == null || userName.trim().length() < 6){
            throw new IllegalArgumentException("Username invalido!");
        }
    }
    
    /**
     * Valida a senha
     * @param senha
     */
    private static void validarSenha(String senha){
        if(senha == null || senha.trim().length() < 8){
            throw new IllegalArgumentException("A senha não pode ser vazia, ou com menos de 8 caracteres!");
        }
    }

    /**
     * Obtem o username
     * @return
     */
    public String getUsername() {
        return username;
    }
   

     // usado na interface
    public void setUsername(String username) {
        this.verificarUsername(username); 
        this.username = username;
    }
   
   /**
    * Define a senha
    * @param senha 
    */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    /**
     * Verifica o username
     * @param username
     * @return
     */
    public boolean verificarUsername(String username){
        return username != null && this.username != null && this.username.equals(username); //tem que fazer essa verificação para o json serealizar legal
    }
    
    /**
     * Verifica a senha
     * @param senha
     * @return
     */
    public boolean verificarSenha(String senha){
        return senha != null && this.senha.equals(senha);
    }
    
    /**
     * Muda o username
     * @param usernameAtual
     * @param usernameNovo
     */
    public void mudarUsername(String usernameAtual, String usernameNovo) {
        if(usernameAtual == null || !verificarUsername(usernameAtual)){
            throw new IllegalArgumentException("usuario invalido!");
        }
        validarUsername(usernameNovo);
        setUsername(usernameNovo);
    }
    
    /**
     * Muda a senha
     * @param senhaAtual
     * @param senhaNova
     */
    public void mudarSenha(String senhaAtual, String senhaNova) {
        if(senhaAtual == null || !verificarSenha(senhaAtual)){
           throw new IllegalArgumentException("Sennha invalido!");
        }
        validarSenha(senhaNova);
        setSenha(senhaNova);
    }
     
    
    
    /**
     * Obtem a representaçao em String de um Usuario
     * @return
     */
    @Override
    public String toString() {
        return String.format("%sUsername: %s",super.toString(), getUsername()); 
    }
}
    