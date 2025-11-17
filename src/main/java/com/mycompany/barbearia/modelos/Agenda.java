/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;
import java.time.LocalDateTime;
/**
 * Representa uma vaga de agenda com horariom barbeiro e estacao
 * @param horario
 * @param barbeiro
 * @param estacao
 * @author italo
 */
public record Agenda (LocalDateTime horario, Barbeiro barbeiro, Estacao estacao){}
