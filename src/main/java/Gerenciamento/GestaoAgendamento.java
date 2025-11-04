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
    
    private final GestaoUsuarios gestaoUsuarios;
    private final GestaoEstacao gestaoEstacao;
    
    private static final int PRE_AGENDAMENTO = 14;
    private static final int CANCELAMENTO_SEM_TAXA = 7;
    private static final double TAXA_CANCELAMENTO = 0.35;
    
    
    private static final LocalTime HORA_INICIO_ESPEDIENTE = LocalTime.of(8, 0);
    private static final LocalTime HORA_FINAL_ESPEDIENTE = LocalTime.of(18, 0); 
    private static final int SLOT_MINUTOS = 10;
    
    public GestaoAgendamento(GestaoUsuarios gestaoU, GestaoEstacao gestaoE) {
        this.gestaoUsuarios = gestaoU;
        this.gestaoEstacao = gestaoE;
    }
    
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
                LocalDateTime fim_agendamento_existente = agendamentoExistente.getDataHoraFimAgendamento();
                
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
    
    public void atualizarStatusAgendamento(){
        LocalDateTime agora = LocalDateTime.now();
        
        for(Agendamento ag : this.agendamentos.getLista()) {
            StatusAgendamento statusAtual = ag.getStatus();
            
            if (statusAtual == StatusAgendamento.PRE_AGENDADO) {
                long diasDeAntecedencia = ChronoUnit.DAYS.between(agora.toLocalDate(), ag.getDataHoraInicioAgendamento().toLocalDate());
                if (diasDeAntecedencia < PRE_AGENDAMENTO) {
                    ag.setStatus(StatusAgendamento.CONFIRMADO);
                    statusAtual = StatusAgendamento.CONFIRMADO;
                }
            }
            
            if (statusAtual ==  StatusAgendamento.CONFIRMADO) {
                if (agora.toLocalDate().isEqual(ag.getDataHoraInicioAgendamento().toLocalDate()) && agora.isBefore(ag.getDataHoraInicioAgendamento())) {
                    ag.setStatus(StatusAgendamento.EM_ESPERA);
                    statusAtual = StatusAgendamento.EM_ESPERA;
                }
            }
            
            if (statusAtual == StatusAgendamento.EM_ESPERA) {
                if(agora.isAfter(ag.getDataHoraInicioAgendamento()) || agora.isEqual(ag.getDataHoraInicioAgendamento())) {
                    ag.setStatus(StatusAgendamento.EM_ANDAMENTO);
                    statusAtual = StatusAgendamento.EM_ANDAMENTO;
                }
            }
            
            if (statusAtual == StatusAgendamento.EM_ANDAMENTO) {
                if(agora.isAfter(ag.getDataHoraFimAgendamento()) || agora.isEqual(ag.getDataHoraFimAgendamento())) {
                    ag.setStatus(StatusAgendamento.FINALIZADO);
                }
            }
        }
    }
    
    public ArrayList<Agenda> buscarHorarioVagoAgendamento(ArrayList<Servico> servicos, LocalDate data){
        ArrayList<Agenda> agenda = new ArrayList();
        
        if (servicos == null || servicos.isEmpty()) return agenda;
        
        ArrayList<Barbeiro> todosBarbeiros = this.gestaoUsuarios.listaBarbeiro();
        Estacao[] todasEstacao = this.gestaoEstacao.getEstacoes();
        
        TipoEstacao tipoRequerido = servicos.get(0).getTipoEstacaoRequerido();
        
        int duracaoTotalMinutos = servicos.stream().mapToInt(Servico -> Servico.getTempoEmMinutos10()).sum();
        boolean tiposMisturados = false;
        for (Servico s : servicos) {
            if (s.getTipoEstacaoRequerido() != tipoRequerido) {
                tiposMisturados = true;
                break;
            }
        }
        
        if(tiposMisturados) {
            return agenda;
        }
        
        LocalDateTime slotAtual = data.atTime(HORA_INICIO_ESPEDIENTE);
        while (slotAtual.toLocalTime().isBefore(HORA_FINAL_ESPEDIENTE)) {
            
            LocalDateTime inicio = slotAtual;
            LocalDateTime fim = inicio.plusMinutes(duracaoTotalMinutos);
            
            if(fim.toLocalTime().isAfter(HORA_FINAL_ESPEDIENTE) || inicio.isBefore(LocalDateTime.now())) {
                slotAtual = slotAtual.plusMinutes(SLOT_MINUTOS);
                continue;
            }
            
            for(Barbeiro barbeiro : todosBarbeiros) {
                if(!horarioOcupado(barbeiro, inicio, fim)) {
                    for(Estacao estacao : todasEstacao) {
                        if (estacao.getTipo() == tipoRequerido) {
                            if (!horarioOcupado(estacao, inicio, fim)) {
                                agenda.add(new Agenda(inicio, barbeiro, estacao));
                            }
                        }
                    }
                }
            }
            slotAtual = slotAtual.plusMinutes(SLOT_MINUTOS);
        }
        return agenda;
    } 
    
    public ArrayList<Agendamento> getAgendamentos(){
        return this.agendamentos.getLista();
    }
    
    public Agendamento buscarAgendamentoID(String ID){
        return this.agendamentos.buscaPorId(ID);
    }
    
    public ArrayList<Agendamento> buscarAgendamentoBarbeiro(Barbeiro barbeiro, LocalDate data) {
        ArrayList<Agendamento> resultados = new ArrayList();
        for (Agendamento ag : this.agendamentos.getLista()) {
            if(ag.getBarbeiro().getId().equals(barbeiro.getId()) && ag.getDataHoraInicioAgendamento().toLocalDate().isEqual(data)) {
                resultados.add(ag);
            }
        }
        return resultados;
    }
    
    //fazer um metodo para buscar agendamentos via clientes
    
}