/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author italo
 */
public class Agendamento extends Modelo{
    
    private static int contador = 0;
    
    private final Cliente cliente;
    private final Barbeiro barbeiro;
    private final Atendente atendente;
    private final Estacao estacao;
    private final ArrayList<Servico> servico;
    
    private final LocalDateTime dataHoraInicioAgendamento;
    private final LocalDateTime dataHoraFimAgendameto;
    
    private StatusAgendamento status;
    private final double valorTotal;
    private final double valorRetido;

    public Agendamento(Cliente cliente, Barbeiro barbeiro, Atendente atendente, Estacao estacao, ArrayList<Servico> servico, LocalDateTime dataHoraInicioAgendamento, LocalDateTime dataHoraFimAgendameto, double valorTotal, double valorRetido, String nome, StatusAgendamento status) {
        
        
    }
    
    @Override
    protected String gerarId() {
        return "AGE" + (++contador);
    }
}
