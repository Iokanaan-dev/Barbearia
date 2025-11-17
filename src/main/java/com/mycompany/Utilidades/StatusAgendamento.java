/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Utilidades;

/**
 * Enumema os statis possiveis de agendamento
 * @author italo
 */
public enum StatusAgendamento {
    PRE_AGENDADO,      // Marcado com >= 14 dias de antecedência
    AGUARDANDO_PAGAMENTO, // <14 dias 
    CONFIRMADO,        // Confirmado (marcado com < 14 dias ou transição de PRE_AGENDADO)
    FINALIZADO,        // Após o horário de término
    CANCELADO;         // Cancelado pelo cliente ou sistema  
    
}
