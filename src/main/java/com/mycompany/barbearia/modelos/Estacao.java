/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 *
 * @author italo
 */
public class Estacao extends Modelo{
    private String descricao;
    private static int cont;
    
    public Estacao(String nome, String descricao){
        super(nome);
        this.descricao = descricao;
    }
    
    @Override
    public String gerarId(){
        return "ES" + (++cont);

    }

    public String getDiscricao() {
        return descricao;
    }

    public void setDiscricao(String discricao) {
        this.descricao = discricao;
    }

    @Override
    public String toString() {
        return "Estacao{" + super.toString() + "discricao=" + descricao + '}';
    }
}
