/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Utilidades;

/**
 * Obriga a gerar ID
 * @author intalo
 */
public interface IdGerador {
    String gerarId(); // força todas as classes concretas que precisam ter ID a implementar sua propria logica de gerar ID
}
