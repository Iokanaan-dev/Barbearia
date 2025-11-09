/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.util.UUID;

/**
 *
 * @author italo
 */
public class Produto extends Modelo{
    private double preco;
    private String descricao;

    /**
     *
     * @param nome
     * @param preco
     */
    public Produto(String nome, double preco, String descricao){
        super(nome);
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        this.preco = preco;
        this.descricao = descricao;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String gerarId(){
        return "PO-" + UUID.randomUUID().toString().substring(0, 10);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    /**
     *
     * @return
     */
    
    @Override
    public String toString() {
        return String.format("%nProduto %s%n%sPreço: %s%nDescriçao: %s", getId(), super.toString(), getPreco(), getDescricao());
    }        
}
