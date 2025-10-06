/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 * Manda mensagem pra ela!
 * @author intalo
 */
public class Servico extends Modelo{
    private double preco;
    private static int contador;

    /**
     *
     * @param nome
     * @param preco
     */
    public Servico(String nome, double preco){
        super(nome);
        
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        
        this.preco = preco;
    }

    /**
     *
     * @return
     */
    public double getPreco() {
        return preco;
    }
   
    /**
     *
     * @param preco
     */
    public void mudarPreco(double preco){
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        this.preco = preco;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String gerarId() {
        return "SE" + (++contador);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Servico" + super.toString() + ", preco=" + this.getPreco() + "}";
    }
}