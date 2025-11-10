    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;

/**
 * // classe que representa um usuario do sistema, um funcionario padrao ou adm
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Usuario extends Individuo{
    
    public Usuario(){
        
    }
    
    private String username;
    private String senha;

    /**
     *
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
     *
     * @param userName
     */
    private static void validarUsername(String userName){
        if (userName == null || userName.trim().length() < 6){
            throw new IllegalArgumentException("Username invalido!");
        }
    }
    
    /**
     *
     * @param senha
     */
    private static void validarSenha(String senha){
        if(senha == null || senha.trim().length() < 8){
            throw new IllegalArgumentException("A senha não pode ser vazia, ou com menos de 8 caracteres!");
        }
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
   
   // private para nao ser acessivel fora dessa classe
   private void setUsername(String username) {
        this.username = username;
    }
   
   // private para nao ser acessivel fora dessa classe
   private void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public boolean verificarUsername(String username){
        return username != null && this.username.equals(username);
    }
    
    /**
     *
     * @param senha
     * @return
     */
    public boolean verificarSenha(String senha){
        return senha != null && this.senha.equals(senha);
    }
    
    /**
     *
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
     *
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
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%sUsername: %s",super.toString(), getUsername()); 
    }
    
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario u = (Usuario) o;
    return username != null && username.equals(u.username);
}

@Override
public int hashCode() {
    return username == null ? 0 : username.hashCode();
}
}
    