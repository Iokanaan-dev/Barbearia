/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 *
 * @author italo
 */
public class Produto extends Modelo{
    private double preco;
    private static int contador = 0;

    /**
     *
     * @param nome
     * @param preco
     */
    public Produto(String nome, double preco){
        super(nome);
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
    protected String gerarId(){
        return "PO" + (++contador);
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
    public void setPreco(double preco){
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
    public String toString() {
        return String.format("%sPreco: %s", super.toString(),preco);
    }        
    
}
