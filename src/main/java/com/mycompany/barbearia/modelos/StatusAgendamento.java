/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 *
 * @author italo
 */
public enum StatusAgendamento {
PRE_AGENDADO,      // Marcado com >= 14 dias de antecedência
CONFIRMADO,        // Confirmado (marcado com < 14 dias ou transição de PRE_AGENDADO)
EM_ESPERA,         // No dia do agendamento, antes do horário de início
EM_ANDAMENTO,      // Durante o horário do agendamento
FINALIZADO,        // Após o horário de término
CANCELADO;         // Cancelado pelo cliente ou sistema  
}
