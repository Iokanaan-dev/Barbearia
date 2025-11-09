/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GsonBarbearia;

import com.mycompany.Utilidades.StatusAgendamento;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class Agendamento_date {
    String id;
    boolean isEncaixe;
    
    String clienteID;
    String barbeiroID;
    String atendenteID;
    String estacaoID;
    ArrayList<String> servicosID = new ArrayList<>();
    
    LocalDateTime dataHoraInicioAgendamento;
    LocalDateTime dataHoraFimAgendamento;
    StatusAgendamento status;
}
