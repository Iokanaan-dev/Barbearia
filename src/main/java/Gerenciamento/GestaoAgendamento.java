/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.*;
import Listas.ListaGenerica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
/**
 *
 * @author italo
 */
public class GestaoAgendamento {
    
    private final ListaGenerica<Agendamento> agendamentos = new ListaGenerica();
    
    private static final int PRE_AGENDAMENTO = 14;
    private static final int CANCELAMENTO_SEM_TAXA = 7;
    private static final double TAXA_CANCELAMENTO = 0.35;
    
    
    private static final LocalTime HORA_INICIO_ESPEDIENTE = LocalTime.of(8, 0);
    private static final LocalTime HORA_FINAL_ESPEDIENTE = LocalTime.of(18, 0); 
    private static final int SLOT_MINUTOS = 10;
    
    private boolean horarioOcupado(Modelo recurso, LocalDateTime inicio, LocalDateTime fim) {
        for (Agendamento agendamentoExistente : this.agendamentos.getLista()) {
            if (agendamentoExistente.getStatus() == StatusAgendamento.CANCELADO) continue;
            
            boolean colisaoRecurso = false;
            if (recurso instanceof Barbeiro && agendamentoExistente.getBarbeiro().getId().equals(recurso.getId())) {
                colisaoRecurso = true;
            }
            else if(recurso instanceof Estacao && agendamentoExistente.getEstacao().getId().equals(recurso.getId())){
                colisaoRecurso = true;
            }
            if (colisaoRecurso) {
                LocalDateTime inicio_agendamento_existente = agendamentoExistente.getDataHoraInicioAgendamento();
                LocalDateTime fim_agendamento_existente = agendamentoExistente.getDataHoraFimAgendameto();
                
                if (inicio.isBefore(fim_agendamento_existente) && fim.isAfter(inicio_agendamento_existente)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Agendamento criarAgendamento (Cliente cliente, Barbeiro barbeiro, Estacao estacao, Usuario atendente, ArrayList<Servico> servicos, LocalDateTime dataInicio) throws Exception{
        
        if (servicos == null || servicos.isEmpty()) {
            throw new Exception("Sem serviço = Sem agendamento pra você!");
        }
        
        if (dataInicio.isBefore(LocalDateTime.now())) {
            throw new Exception("Você por acaso consegue voltar no tempo?");
        }
        
        for (Servico s : servicos){
            if(s.getTipoEstacaoRequerido() != estacao.getTipo()) {
                throw new Exception("A estação não é compativel com o serviço " + s.getNome());
            }
        }
        
        int duracaoTotalEmMinutos = servicos.stream().mapToInt(Servico -> Servico.getTempoEmMinutos10()).sum(); //stream é uma lista mais simples de mexer com varios metodozinhos, nesse caso aqui usamos o mapToInt para retornar apenas os ints e assim usar Sum que é um metodo de mapToInt. 
        LocalDateTime dataFim = dataInicio.plusMinutes(duracaoTotalEmMinutos); //Aqui só usamos a data inicial para achar a data final
        
        if (horarioOcupado(barbeiro, dataInicio, dataFim)){
            throw new Exception("O horário não está disponivel para esse barbeiro!");
        }
        
        if(horarioOcupado(estacao, dataInicio, dataFim)){
            throw new Exception ("O horário não está disponivel para essa estação");
        }
        
        StatusAgendamento statusInicial;
        long diasDeAntecedencia =  ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), dataInicio.toLocalDate()); //calcula quantos dias de diferença entre a primeira e a segunda data 
        
        if (diasDeAntecedencia >= PRE_AGENDAMENTO) {
            statusInicial = StatusAgendamento.PRE_AGENDADO;
        }
        else {
            statusInicial = StatusAgendamento.CONFIRMADO;
        }
        
        Agendamento novoAgendamento = new Agendamento(cliente, barbeiro, atendente, estacao ,servicos, dataInicio, statusInicial);
        this.agendamentos.adicionar(novoAgendamento);
        return novoAgendamento;
    }
    
    public void cancelarAgendamento(String ID) throws Exception {
        Agendamento agendamento = this.agendamentos.buscaPorId(ID);
        
        if (agendamento == null) {
            throw new Exception("Esse agendamento não existe!");
        }
        
        StatusAgendamento statusAtual = agendamento.getStatus();
        if (statusAtual == StatusAgendamento.CANCELADO) {
            throw new Exception("Agendamento já cancelado");
        }
        
        if(statusAtual == StatusAgendamento.FINALIZADO) {
            throw new Exception("Esse agendamento já foi finalizado");
        }
        
        long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), agendamento.getDataHoraInicioAgendamento().toLocalDate());
        
        if(statusAtual == StatusAgendamento.PRE_AGENDADO || diasRestantes >= CANCELAMENTO_SEM_TAXA) {
            agendamento.setValorRetido(0.0);
        }
        else{
            double valorTaxa = agendamento.getValorTotal() * TAXA_CANCELAMENTO;
            agendamento.setValorRetido(valorTaxa);
        }
        agendamento.setStatus(StatusAgendamento.CANCELADO);
    }
    
    public ArrayList<Agendamento> buscarbarbeiroAgendamento(Barbeiro barbeiro, LocalDate data){
        
    }
    
    public ArrayList<Agendamento> getAgendamentos(){
        return this.agendamentos.getLista();
    }
    
}