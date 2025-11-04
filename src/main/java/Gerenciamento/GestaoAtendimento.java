/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.Atendimento;
import com.mycompany.barbearia.modelos.Agendamento;
import com.mycompany.barbearia.modelos.Cliente;
import java.util.ArrayList;
 

public class GestaoAtendimento extends Gestao<Atendimento> {
    
    private final ArrayList<Atendimento> atendimentosAbertos = new ArrayList<>();
    private static GestaoAtendimento instancia;
    
    // Construtor Singleton
    private GestaoAtendimento() {}
    public static GestaoAtendimento getInstancia() {
        if (instancia == null) instancia = new GestaoAtendimento();
        return instancia;
    }
    
    public Atendimento criarNovoAtendimento(Cliente cliente) {
        Atendimento novoAtendimento = new Atendimento(cliente);
        super.adicionar(this.atendimentosAbertos, novoAtendimento);
        return novoAtendimento;
    }
    
    public void adicionarAgendamentoAoAtendimento(String atendimentoId, Agendamento agendamento) {
        Atendimento atd = super.procurandoID(this.atendimentosAbertos, atendimentoId);
        if (atd != null) {
            atd.adicionarAgendamento(agendamento);
        }
    }
    
    public Atendimento buscarAtendimentoPorId(String id) {
        return super.procurandoID(this.atendimentosAbertos, id);
    }
}
