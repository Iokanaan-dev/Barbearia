/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Listas.ListaGenerica;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.barbearia.modelos.TipoEstacao;
import java.util.ArrayList;

        
/**
 *
 * @author italo
 */
public class GestaoServico {
    
    private final ListaGenerica<Servico> Listaservicos = new ListaGenerica();
    
    private void cadastrarNovoServico(String nome, double preco, String descricao, int temp, TipoEstacao tipo){
        Servico servico = new Servico(nome, preco, descricao, temp, tipo);
        this.Listaservicos.adicionar(servico);
    }
    
    private Servico buscarServicoID(String ID){
        Servico servicoSelecionado = this.Listaservicos.buscaPorId(ID);
        
        if(servicoSelecionado == null)
            System.out.println("Nenhum Serviço encontrado.");
        return servicoSelecionado;
    }
    
    private ArrayList<Servico> getServicos(){
        return this.Listaservicos.getLista();
    }
    
    private void removerServico(String ID){
        this.Listaservicos.remover(ID);
    }
    
    private void editarServico(Servico servico, String nome, double preco, String descricao, int temp, TipoEstacao tipo){
        //fazer verificação futuramente
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDescricao(descricao);
        servico.setTempoEmMinutos(temp);
        servico.setTipoEstacaoRequerido(tipo);
    }
}
