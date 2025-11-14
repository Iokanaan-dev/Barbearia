/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.UUID;

/**
 *
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Produto extends Modelo{
    private double custo; //custo para comprar do fornecedor
    private double preco; //venda para o cliente
    private String descricao;

    /**
     *
     * @param nome
     * @param preco
     */
    public Produto(String nome, double custo ,double preco, String descricao){
        super(nome);
        if(preco <= 0 || custo <= 0) {
            throw new IllegalArgumentException("Valor e custo devem ser ambos maiores que 0!");
        }
        this.custo = custo;
        this.preco = preco;
        this.descricao = descricao;
    }
    
    public Produto(){}
    
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

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
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
