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
 * Classe que gerencia agendamentos
 * @author italo
 */
public class GestaoAgendamento extends Gestao<Agendamento> {
     
    private final GestaoUsuarios gestaoUsuarios;
    private final GestaoEstacao gestaoEstacao;
    
    private static GestaoAgendamento instancia;

    // --- Constantes ---
    private static final int PRE_AGENDAMENTO = 14;
    private static final LocalTime HORA_INICIO_ESPEDIENTE = LocalTime.of(8, 0);
    private static final LocalTime HORA_FINAL_ESPEDIENTE = LocalTime.of(18, 30);
    private static final int SLOT_MINUTOS = 10;

    // 🔹 Construtor privado
    private GestaoAgendamento(Barbearia_date dados) {
        this.listaModelo = dados.getListaAgendamentos();
        this.gestaoUsuarios = GestaoUsuarios.getInstancia();
        this.gestaoEstacao = GestaoEstacao.getInstancia();
    }

    // 🔹 Inicializa o Singleton (chama no main)

    /**
     *
     * @param dados
     */
    public static void inicializa(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoAgendamento(dados);
        }
    }

    // 🔹 Acesso global

    /**
     *
     * @return
     */
    public static GestaoAgendamento getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestaoAgendamento não foi inicializada. Chame inicializa(dados) primeiro.");
        }
        return instancia;
    }
    
    /**
     * Verifica se um horario esta ocupado
     * @return
     */    
    private boolean horarioOcupado(Modelo recurso, LocalDateTime inicio, LocalDateTime fim) {
        
        // verifica cada agendamento na lista para obter seu status
        for (Agendamento agendamentoExistente : this.listaModelo) {  
            if (agendamentoExistente.getStatus() == StatusAgendamento.CANCELADO) continue;
            
            // variavel de controle
            boolean colisaoRecurso = false;
            
            if (recurso instanceof Barbeiro && agendamentoExistente.getBarbeiro().getId().equals(recurso.getId())) 
                colisaoRecurso = true;

            else if (recurso instanceof Estacao && agendamentoExistente.getEstacao().getId().equals(recurso.getId()))
                colisaoRecurso = true;
     
            // verifica se o recurso ja esa sendo utilizado
            if (colisaoRecurso) {
                
                LocalDateTime inicioExistente = agendamentoExistente.getDataHoraInicioAgendamento();  
                LocalDateTime fimExistente = agendamentoExistente.getDataHoraFimAgendamento();
                
                // verifica se os horarios da utilizaçao recurso vao bater com horarios ja agendados  
                if (inicio.isBefore(fimExistente) && fim.isAfter(inicioExistente)) 
                    return true;
            }
        }
        return false; // indica que ha horarios livres e sem colisoes de recursos
    }
    
    /**
     * Cria agendamentos
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
    public Agendamento x(Cliente cliente, Barbeiro barbeiro, Estacao estacao, Usuario atendente, ArrayList<Servico> servicos, LocalDateTime dataInicio, boolean isEncaixe, String associado_Ordem_Servico) throws Exception {
        
        validarListaServicos(servicos);
        validarHorario(dataInicio);
        validarEstacao(servicos, estacao);

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
        
        Agendamento novoAgendamento = new Agendamento(cliente, barbeiro, atendente, estacao, servicos, dataInicio, statusInicial, isEncaixe, associado_Ordem_Servico);
        super.adicionar(novoAgendamento);
        return novoAgendamento;
    }
    
    /**
     * Valida uma lista de Servicos
     * @param Agendamento
     * @return
     * @throws Exception
    **/     
    private void validarListaServicos(ArrayList<Servico> servicos) throws Exception{
        if (servicos == null || servicos.isEmpty())
            throw new Exception("Sem serviço"); 
    }

    /**
     * Valida um agendamento
     * @param Agendamento
     * @return
     * @throws Exception
    **/     
    private void validarHorario(LocalDateTime dataInicio) throws Exception{
        if (dataInicio.isBefore(LocalDateTime.now())) 
            throw new Exception("Não pode voltar no tempo");
    }
    
    /**
     * Valida um agendamento
     * @param Agendamento
     * @return
     * @throws Exception
    **/     
    private void validarEstacao(ArrayList<Servico> servicos, Estacao estacao) throws Exception{
        for (Servico s : servicos) {
            if (s.getTipoEstacaoRequerido() != estacao.getTipo())
                throw new Exception("A estação não é compativel com o serviço " + s.getNome());
        }        
    }
    
    /**
     * Valida um agendamento
     * @param Agendamento
     * @return
     * @throws Exception
    **/ 
    private void validarAgendamento(Agendamento agendamento) throws Exception{
        
        if (agendamento == null)
            throw new Exception("Esse agendamento não existe!");
    }
    
    /**
     * Valida o Status de um agendamento
     * @param Agendamento
     * @return
     * @throws Exception
    **/ 
    private void validarStatusAgendamento(Agendamento agendamento) throws Exception{
        StatusAgendamento statusAtual = agendamento.getStatus(); 
        if (statusAtual == StatusAgendamento.CANCELADO || statusAtual == StatusAgendamento.FINALIZADO || statusAtual == StatusAgendamento.EM_ANDAMENTO)
            throw new Exception("Esse agendamento já foi finalizado, está em andamento ou já foi cancelado.");
    }
    
    /**
     * Cancela um agendamento
     * @param ID
     * @return
     * @throws Exception
    **/ 
    public Agendamento cancelarAgendamento(String ID) throws Exception {

        Agendamento agendamento = super.buscarPorId(ID); 
        
        validarAgendamento(agendamento);
        validarStatusAgendamento(agendamento);
        
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamento;
    }
    
    /**
     * Atualiza o status de um agendamento
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
     * Busca um horario vago na lsita de agendamento
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
     * Obtem os agendamentos
     * @return
     */
    public ArrayList<Agendamento> getAgendamentos() {
        return new ArrayList<>(this.listaModelo); 
    }
    
    /**
     * Obtem todos os agendamentos associados a um certo Barbeiro
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
     * Obtem todos os agendamentos associados a um certo Cliente
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