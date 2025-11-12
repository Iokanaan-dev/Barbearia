/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia;

import com.mycompany.Gerenciamento.GestaoClientes;
import com.mycompany.Gerenciamento.GestaoUsuarios;

/**
 *
 * @author intalo
 */
public class SistemaBarbearia {
    private final GestaoClientes gestaoC = GestaoClientes.getInstancia();
    private final GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();

    public GestaoClientes getGestaoClientes() {
        return gestaoC;
    }

    public GestaoUsuarios getGestaoUsuarios() {
        return gestaoU;
    }
}


