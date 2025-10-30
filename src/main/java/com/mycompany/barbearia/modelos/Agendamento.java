/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Utilidades.IdGerador;
import Listas.ListaGenerica;


/**
 *
 * @author italo
 */


public class Agendamento implements IdGerador{
     
    private static int contador = 0;
    
    // todos final pois nada podera ser alterado apos entrar no sistema, exceto pelo status, serviços e valor
    private final Cliente cliente;
    private final Barbeiro barbeiro;
    private final Atendente atendente;
    private final Estacao estacao;
    private ListaGenerica<Servico> servicos; // pode ser alterado para adicionar serviços
    
    private final LocalDateTime dataHoraInicioAgendamento;
    private LocalDateTime dataHoraFimAgendameto; // se um serviço for adicionado o horario final muda

    private StatusAgendamento status = StatusAgendamento.PRE_AGENDADO;
    private double valorTotal; // como eh calculado baseado nos servicos, se um servico for adcionado o valor ira alterar
    private double valorRetido; // como eh calculado baseado nos servicos, se um servico for adcionado o valor ira alterar

    public Agendamento(Cliente cliente, Barbeiro barbeiro, Atendente atendente, Estacao estacao, ListaGenerica<Servico> servicos, LocalDateTime dataHoraInicioAgendamento, LocalDateTime dataHoraFimAgendameto, double valorTotal, double valorRetido) {
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
