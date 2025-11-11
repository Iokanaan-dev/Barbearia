/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.date_Barbearia;

import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class ListaUnica<T> extends ArrayList<T> {
    @Override
    public boolean add(T elemento) {
        if (!this.contains(elemento)) {
            return super.add(elemento);
        }
        System.out.println("Já existe esse elemento no nosso JSON: " + elemento);
        return false;
    }
}

