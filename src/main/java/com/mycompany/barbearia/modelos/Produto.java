/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.UUID;

/**
 * Representa um Produto no Sistema
 * @author italo
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Produto extends Modelo{
    private double custo; //custo para comprar do fornecedor
    private double preco; //venda para o cliente
    private String descricao;

    /**
     * Construtor completo de Produto
     * @param nome
     * @param custo
     * @param preco
     * @param descricao
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
    
    /**
     * Construtor sem parametros
     */
    public Produto(){}
    
    /**
     * Gera o ID
     * @return
     */
    @Override
    public String gerarId(){
        return "PO-" + UUID.randomUUID().toString().substring(0, 10);
    }
   

    /**
     * Obtem o preco do produto
     * @return
     */
    public double getPreco() {
        return preco;
    }
   
    /**
     * Define o preco do produto
     * @param preco
     */
    public void setPreco(double preco){
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        this.preco = preco;
    }

    /**
     * Obtem a descricao do produto
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descricao do produto
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtem o custo do produto
     * @return
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Define o custo
     * @param custo
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Obtem a representacao em String de um Produto
     * @return
     */
    
    @Override
    public String toString() {
        return String.format("%nProduto %s%n%sPreço: %s%nDescriçao: %s", getId(), super.toString(), getPreco(), getDescricao());
    }        
}
