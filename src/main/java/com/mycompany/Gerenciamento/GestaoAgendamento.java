package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.Utilidades.StatusAgendamento;
import com.mycompany.barbearia.modelos.*;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class GestaoAgendamento extends Gestao<Agendamento> {
     
    private final GestaoUsuarios gestaoUsuarios;
    private final GestaoEstacao gestaoEstacao;
    
    private static GestaoAgendamento instancia;
    private Barbearia_date dados;

    // --- Constantes ---
    private static final int PRE_AGENDAMENTO = 14;
    private static final LocalTime HORA_INICIO_ESPEDIENTE = LocalTime.of(8, 0);
    private static final LocalTime HORA_FINAL_ESPEDIENTE = LocalTime.of(18, 30);
    private static final int SLOT_MINUTOS = 10;

        // 🔹 Construtor privado
    private GestaoAgendamento(Barbearia_date dados) {
        this.dados = dados;
        this.listaModelo = dados.getListaAgendamentos();
        this.gestaoUsuarios = GestaoUsuarios.getInstancia();
        this.gestaoEstacao = GestaoEstacao.getInstancia();
    }

    // 🔹 Inicializa o Singleton (chama no main)
    public static void inicializa(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoAgendamento(dados);
        }
    }

    // 🔹 Acesso global
    public static GestaoAgendamento getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestaoAgendamento não foi inicializada. Chame inicializa(dados) primeiro.");
        }
        return instancia;
    }
    
    /**
     * Retorna uma copia.
     * @return
     */
    public ArrayList<Agendamento> getLista() {
        return new ArrayList<>(this.listaModelo);
    }
    
    private boolean horarioOcupado(Modelo recurso, LocalDateTime inicio, LocalDateTime fim) {
        

        for (Agendamento agendamentoExistente : this.listaModelo) {  
            if (agendamentoExistente.getStatus() == StatusAgendamento.CANCELADO) continue;
            
            boolean colisaoRecurso = false;
            if (recurso instanceof Barbeiro && agendamentoExistente.getBarbeiro().getId().equals(recurso.getId())) {
                colisaoRecurso = true;
            } 

            else if (recurso instanceof Estacao && agendamentoExistente.getEstacao().getId().equals(recurso.getId())) { 
                colisaoRecurso = true;
            }
            
            if (colisaoRecurso) {

                LocalDateTime inicioExistente = agendamentoExistente.getDataHoraInicioAgendamento();  
                LocalDateTime fimExistente = agendamentoExistente.getDataHoraFimAgendamento();
                
                if (inicio.isBefore(fimExistente) && fim.isAfter(inicioExistente)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param cliente
     * @param barbeiro
     * @param estacao
     * @param atendente
     * @param servicos
     * @param dataInicio
     * @param isEncaixe
     * @return
     * @throws Exception
     */
    public Agendamento criarAgendamento(Cliente cliente, Barbeiro barbeiro, Estacao estacao, Usuario atendente, ArrayList<Servico> servicos, LocalDateTime dataInicio, boolean isEncaixe) throws Exception {
        
        if (servicos == null || servicos.isEmpty()) { throw new Exception("Sem serviço"); }
        if (dataInicio.isBefore(LocalDateTime.now())) { throw new Exception("Não pode voltar no tempo"); }
        
        for (Servico s : servicos) {
            if (s.getTipoEstacaoRequerido() != estacao.getTipo()) {
                throw new Exception("A estação não é compativel com o serviço " + s.getNome());
            }
        }
        

        int duracaoTotalEmMinutos = servicos.stream().mapToInt(Servico::getTempoEmMinutos10).sum();  
        LocalDateTime dataFim = dataInicio.plusMinutes(duracaoTotalEmMinutos);
        
        if (horarioOcupado(barbeiro, dataInicio, dataFim)) { throw new Exception("Horário indisponivel (barbeiro)"); }
        if (horarioOcupado(estacao, dataInicio, dataFim)) { throw new Exception("Horário indisponivel (estação)"); }
        
        StatusAgendamento statusInicial;
        long diasDeAntecedencia = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), dataInicio.toLocalDate());
        
        if (diasDeAntecedencia >= PRE_AGENDAMENTO) {
            statusInicial = StatusAgendamento.PRE_AGENDADO;
        } else {
            statusInicial = StatusAgendamento.AGUARDANDO_PAGAMENTO;
        }
        
     
        Agendamento novoAgendamento = new Agendamento(cliente, barbeiro, atendente, estacao, servicos, dataInicio, statusInicial, isEncaixe);
        super.adicionar(novoAgendamento);
        //super.adicionar(this.agendamentos, novoAgendamento);  
        return novoAgendamento;
    }
    
    /**
     *
     * @param ID
     * @return
     * @throws Exception
    **/ 
    public Agendamento cancelarAgendamento(String ID) throws Exception {

        Agendamento agendamento = super.buscarPorId(ID);  
        
        if (agendamento == null) {
            throw new Exception("Esse agendamento não existe!");
        }
        
        StatusAgendamento statusAtual = agendamento.getStatus();
        

        if (statusAtual == StatusAgendamento.CANCELADO || statusAtual == StatusAgendamento.FINALIZADO || statusAtual == StatusAgendamento.EM_ANDAMENTO) {
            throw new Exception("Esse agendamento já foi finalizado, está em andamento ou já foi cancelado.");
        }
        
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamento;
    }
    
    /**
     *
     */
    public void atualizarStatusAgendamento() {
        LocalDateTime agora = LocalDateTime.now();
        

        for (Agendamento ag : this.listaModelo) {  
            StatusAgendamento statusAtual = ag.getStatus();
            
            if (statusAtual == StatusAgendamento.PRE_AGENDADO) {
                long diasDeAntecedencia = ChronoUnit.DAYS.between(agora.toLocalDate(), ag.getDataHoraInicioAgendamento().toLocalDate());
                if (diasDeAntecedencia < PRE_AGENDAMENTO) {
                    ag.setStatus(StatusAgendamento.AGUARDANDO_PAGAMENTO);
                    statusAtual = StatusAgendamento.AGUARDANDO_PAGAMENTO;
                }
            }       
            
            if (statusAtual == StatusAgendamento.CONFIRMADO) {
                if (agora.toLocalDate().isEqual(ag.getDataHoraInicioAgendamento().toLocalDate()) && agora.isBefore(ag.getDataHoraInicioAgendamento())) {
                    ag.setStatus(StatusAgendamento.EM_ESPERA);
                    statusAtual = StatusAgendamento.EM_ESPERA;
                }
            }
            
            if (statusAtual == StatusAgendamento.EM_ESPERA) {
                if (agora.isAfter(ag.getDataHoraInicioAgendamento()) || agora.isEqual(ag.getDataHoraInicioAgendamento())) {
                    ag.setStatus(StatusAgendamento.EM_ANDAMENTO);
                    statusAtual = StatusAgendamento.EM_ANDAMENTO;
                }
            }
            
            if (statusAtual == StatusAgendamento.EM_ANDAMENTO) {
                if (agora.isAfter(ag.getDataHoraFimAgendamento()) || agora.isEqual(ag.getDataHoraFimAgendamento())) {
                    ag.setStatus(StatusAgendamento.FINALIZADO);
                }
            }
        }
    }
    
    /**
     *
     * @param servicos
     * @param data
     * @return
     */
    public ArrayList<Agenda> buscarHorarioVagoAgendamento(ArrayList<Servico> servicos, LocalDate data) {
        ArrayList<Agenda> agenda = new ArrayList<>();
        
        if (servicos == null || servicos.isEmpty()) return agenda;
        

        ArrayList<Usuario> todosBarbeiros = this.gestaoUsuarios.getListaBarbeiros(); 
        Estacao[] todasEstacao = this.gestaoEstacao.getEstacoes();
        
        TipoEstacao tipoRequerido = servicos.get(0).getTipoEstacaoRequerido();
        boolean tiposMisturados = false;
        for (Servico s : servicos) {
            if (s.getTipoEstacaoRequerido() != tipoRequerido) {
                tiposMisturados = true;
                break;
            }
        }
        if (tiposMisturados) {
            return agenda;
        }
        
        int duracaoTotalMinutos = servicos.stream().mapToInt(Servico::getTempoEmMinutos10).sum();
        
        LocalDateTime slotAtual = data.atTime(HORA_INICIO_ESPEDIENTE);
        while (slotAtual.toLocalTime().isBefore(HORA_FINAL_ESPEDIENTE)) {
            
            LocalDateTime inicio = slotAtual;
            LocalDateTime fim = inicio.plusMinutes(duracaoTotalMinutos);
            
            if (fim.toLocalTime().isAfter(HORA_FINAL_ESPEDIENTE) || inicio.isBefore(LocalDateTime.now())) {
                slotAtual = slotAtual.plusMinutes(SLOT_MINUTOS);
                continue;
            }
            

            for (Usuario barbeiro : todosBarbeiros) { 
                if (!horarioOcupado(barbeiro, inicio, fim)) {
                    for (Estacao estacao : todasEstacao) {
                        if (estacao.getTipo() == tipoRequerido) {
                            if (!horarioOcupado(estacao, inicio, fim)) {
                                agenda.add(new Agenda(inicio, (Barbeiro) barbeiro, estacao));
                            }
                        }
                    }
                }
            }
            slotAtual = slotAtual.plusMinutes(SLOT_MINUTOS);
        }
        return agenda;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Agendamento> getAgendamentos() {
        return new ArrayList<>(this.listaModelo); 
    }
    
    /**
     *
     * @param ID
     * @return
    **/ 
    public Agendamento buscarAgendamentoID(String ID) {
        return super.buscarPorId(ID); 
    }
    
    /**
     *
     * @param barbeiro
     * @param data
     * @return
     */
    public ArrayList<Agendamento> buscarAgendamentoBarbeiro(Barbeiro barbeiro, LocalDate data) {
        ArrayList<Agendamento> resultados = new ArrayList<>();
        for (Agendamento ag : this.listaModelo) { 
            if (ag.getBarbeiro().getId().equals(barbeiro.getId()) && ag.getDataHoraInicioAgendamento().toLocalDate().isEqual(data)) {
                resultados.add(ag);
            }
        }
        return resultados;
    }
    
    /**
     *
     * @param cliente
     * @return
     */
    public ArrayList<Agendamento> buscarAgendamentoPorCliente(Cliente cliente) {
        ArrayList<Agendamento> resultados = new ArrayList<>();
        for (Agendamento ag : this.listaModelo) {
            if (ag.getCliente().getId().equals(cliente.getId())) {
                resultados.add(ag);
            }
        }
        return resultados;
    }
}