/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Listas.ListaGenerica;
import com.mycompany.barbearia.modelos.Servico;
import java.util.ArrayList;

        
/**
 *
 * @author italo
 */
public class GestaoServico {
    
    private final ListaGenerica<Servico> Listaservicos = new ListaGenerica();
    
    public void cadastrarNovoServico(String nome, double preco, String descricao, int temp){
        Servico servico = new Servico(nome, preco, descricao, temp);
        this.Listaservicos.adicionar(servico);
    }
    
    public Servico buscarServicoID(String ID){
        Servico servicoSelecionado = this.Listaservicos.buscaPorId(ID);
        
        if(servicoSelecionado == null)
            System.out.println("Nenhum Serviço encontrado.");
        return servicoSelecionado;
    }
    
    public ArrayList<Servico> getServicos(){
        return this.Listaservicos.getLista();
    }
    
    public void removerServico(String ID){
        this.Listaservicos.remover(ID);
    }
    
    public void editarServico(Servico servico, String nome, double preco, String descricao, int temp ){
        //fazer verificação futuramente
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDescricao(descricao);
        servico.setTempoEmMinutos(temp);
    }
}
