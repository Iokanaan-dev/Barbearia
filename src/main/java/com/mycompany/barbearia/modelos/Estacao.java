/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 *
 * @author italo
 */
public class Estacao {
    private String tipo;
    private String ID;
    
    /**
     *
     * @param tipo
     * @param ID
     */
    public Estacao(String tipo, String ID){
     this.tipo = tipo;
     this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Estacao{" + "tipo=" + tipo + ", ID=" + ID + '}';
    }
}
