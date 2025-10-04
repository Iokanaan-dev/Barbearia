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
public abstract class Usuario extends Individuo{
    
    private String username;
    private String senha;

    public Usuario(String username, String senha, String nome, String cpf, String telefone, LocalDate data_nascimento) {
        super(nome, cpf, telefone, data_nascimento);
        
        validarUsername(username);
        this.username = username;
        validarSenha(senha);
        this.senha = senha;
    }
    
    private void validarUsername(String userName){
        if (userName == null || userName.trim().length() < 6){
            throw new IllegalArgumentException("Username invalido!");
        }
    }
    
    public void validarSenha(String senha){
        if(senha == null || senha.trim().length() < 8){
            throw new IllegalArgumentException("A senha não pode ser vazia, ou com menos de 8 caracteres!");
        }
    }

    public String getUsername() {
        return username;
    }
    
    public boolean verificarUsername(String username){
        return username != null && this.username.equals(username);
    }
    
    public boolean verificarSenha(String senha){
        return senha != null && this.senha.equals(senha);
    }
    
    
    
    public void mudarUsername(String usernameAtual, String usernameNovo) {
        if(usernameAtual == null || !verificarUsername(usernameAtual)){
            throw new IllegalArgumentException("usuario invalido!");
        }
        validarUsername(usernameNovo);
        this.username = usernameNovo;
    }
    

    public void mudarSenha(String senhaAtual, String senhaNova) {
        if(senhaAtual == null || !verificarSenha(senhaAtual)){
           throw new IllegalArgumentException("Sennha invalido!");
        }
        validarSenha(senhaNova);
        this.senha = senhaNova;
    }
    
    @Override
    public String toString() {
        return String.format(" Username: " + this.username) + super.toString(); 
    }
}
    