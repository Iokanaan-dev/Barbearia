/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 *
 * @author italo
 */
public class Produto {
    private String nome;
    private String id;
    private double preco;
    private static int cont;

    /**
     *
     * @param nome
     * @param preco
     */
    public Produto(String nome, double preco){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        
        this.nome = nome;
        this.id = ("PROD-" + ++cont);
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }
   
    public void mudarPreco(double preco){
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", id=" + id + ", preco=" + preco + '}';
    }
}
