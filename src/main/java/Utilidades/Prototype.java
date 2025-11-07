/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utilidades;

import com.mycompany.barbearia.modelos.Modelo;

/**
 * Interface que força implementaçao do padrao de projeto. Usa uma classe 
 * generica para permitir que qualquer subclasse de modelo possa implementarã
 * @author intalo
 * @param <M>
 */
public interface Prototype <M extends Modelo> {
    M clone();
}
