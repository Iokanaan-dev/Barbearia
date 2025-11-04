/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import Utilidades.IdGerador;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author italo
 */
public abstract class Modelo implements IdGerador{
    private String nome;
    private final String id;

    /**
     *
     * @param nome
     */
    public Modelo(String nome) {
        validarNome(nome);
        this.nome = nome;
        this.id = this.gerarId();
    }

    // construtor para classes que herdam de Modelo mas nao tem nome, como OrdemServico ou Agendamento
    public Modelo() {
         this.id = this.gerarId();
    }
    
    
    
    private static void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Campo Vazio!");
    }   
        
    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }
    
    /**
     * Adiciona um modelo à lista.
     * @param lista
     * @param modelo
     */
    public void adicionar(ArrayList<Modelo> lista, Modelo modelo) {
        lista.add(modelo);
        System.out.printf("Adicionado com sucesso %nID: %s%n", modelo.getId());
    }
    
    public void remover(ArrayList<Modelo> lista, String id) {
        boolean removeu = lista.removeIf(modelo -> Objects.equals(modelo.getId(), id));

        if (removeu)
            System.out.println("Remoção bem-sucedida.");
        else
            System.out.println("Não foi possível remover.");
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        // lida com nome Modelos com nomes nulos
        if (nome == null)
            return String.format("ID: %s%n", getId());
        
        return String.format("Nome: %s%nID: %s%n", getNome(), getId());
    }
}




