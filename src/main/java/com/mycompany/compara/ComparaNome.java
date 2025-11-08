/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;
import com.mycompany.barbearia.modelos.Cliente;
import java.util.Comparator;

/**
 *
 * @author italo
 */
public abstract class ComparaNome implements Comparator<Cliente>{
    @Override
    public int compare(Cliente cliente1, Cliente cliente2){
        return cliente1.getNome().compareTo(cliente2.getNome());
    }
}
