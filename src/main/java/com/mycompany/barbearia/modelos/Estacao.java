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
    private static int cont;
    
    public Estacao(String nome){
        super(nome);
    }
    
    @Override
    protected String gerarId(){
        return "ES" + (++cont);

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    } 
}
