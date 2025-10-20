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
    private String discricao;
    private static int cont;
    
    public Estacao(String nome, String discricao){
        super(nome);
        this.discricao = discricao;
    }
    
    @Override
    protected String gerarId(){
        return "ES" + (++cont);

    }

    public String getDiscricao() {
        return discricao;
    }

    public void setDiscricao(String discricao) {
        this.discricao = discricao;
    }

    @Override
    public String toString() {
        return "Estacao{" + super.toString() + "discricao=" + discricao + '}';
    }
    
    

}
