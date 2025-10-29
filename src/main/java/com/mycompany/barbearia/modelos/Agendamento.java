/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Utilidades.IdGerador;


/**
 *
 * @author italo
 */


public class Agendamento implements IdGerador{
     
    private static int contador = 0;
    
    private final Cliente cliente;
    private final Barbeiro barbeiro;
    private final Atendente atendente;
    private final Estacao estacao;
    private final ArrayList<Servico> servicos;
    
    private final LocalDateTime dataHoraInicioAgendamento;
    private final LocalDateTime dataHoraFimAgendameto;

    private StatusAgendamento status = StatusAgendamento.PRE_AGENDADO;
    private final double valorTotal;
    private final double valorRetido;

    public Agendamento(Cliente cliente, Barbeiro barbeiro, Atendente atendente, Estacao estacao, ArrayList<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, LocalDateTime dataHoraFimAgendameto, double valorTotal, double valorRetido) {
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.atendente = atendente;
        this.estacao = estacao;
        this.servicos = servicos;
        this.dataHoraInicioAgendamento = dataHoraInicioAgendamento;
        this.dataHoraFimAgendameto = dataHoraFimAgendameto;
        this.valorTotal = valorTotal;
        this.valorRetido = valorRetido;        
        
    }

    @Override
    public String gerarId() {
        return "AGE" + (++contador);
    }
}
