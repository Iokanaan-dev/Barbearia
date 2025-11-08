/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compara;
import java.util.Comparator;
import com.mycompany.barbearia.modelos.Cliente;
/**
 *
 * @author iarar
 */
public class ComparaCPF {
    public abstract class Compara implements Comparator<Cliente>{
    @Override
    public int compare(Cliente cliente1, Cliente cliente2){
        return cliente1.getCpf().compareTo(cliente2.getCpf());
    }
}
}
