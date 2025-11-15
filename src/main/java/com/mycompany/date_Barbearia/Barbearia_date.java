/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.date_Barbearia;

import com.mycompany.barbearia.modelos.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Aqui aglomeramos todos os dados, esse sera o arquivo json com todos os dados da barbearia
 * @author italo
 */
public class Barbearia_date {
    
    private static Barbearia_date instancia;
    
    public ArrayList<Cliente> listaClientes = new ArrayList<>();  
    public ArrayList<Gerente> listaGerentes = new ArrayList<>();
    public ArrayList<Barbeiro> listaBarbeiros = new ArrayList<>();
    public ArrayList<Atendente> listaAtendentes = new ArrayList<>();
    public ArrayList<Produto> listaProdutos = new ArrayList<>();
    public ArrayList<Servico> listaServicos = new ArrayList<>(); 
    public ArrayList<Despesa> listaDespesas = new ArrayList<>();
    private ArrayList<RelatorioFinanceiro> listaRelatorios = new ArrayList<>();
    
    public Estoque estoque = new Estoque();
    public Queue<VagaListaEspera> FilaEspera = new LinkedList<>();
    
    public ArrayList<Agendamento> listaAgendamentos = new ArrayList<>();
    public ArrayList<OrdemServico> listaOrdensServico = new ArrayList<>();
    public TabelaPonto tabelaPonto;
    
    
    public static Barbearia_date getInstancia() {
    if (instancia == null) {
        instancia = GerenciadorDeArquivos.carregar();
        if (instancia == null) {
            instancia = new Barbearia_date();
        }
    }
    return instancia;
    }

    
    
    public Barbearia_date(){
        this.listaClientes = new ArrayList<>();
        this.listaGerentes = new ArrayList<>();
        this.listaBarbeiros = new ArrayList<>();
        this.listaAtendentes = new ArrayList<>();
        this.listaProdutos = new ArrayList<>();
        this.listaServicos = new ArrayList<>();
        this.listaDespesas = new ArrayList<>();
        this.listaAgendamentos = new ArrayList<>();
        this.listaOrdensServico = new ArrayList<>();
        this.estoque = new Estoque();
        this.FilaEspera = new LinkedList();
        this.tabelaPonto = new TabelaPonto();
    }
    
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Gerente> getListaGerentes() {
        return listaGerentes;
    }

    public void setListaGerentes(ArrayList<Gerente> listaGerentes) {
        this.listaGerentes = listaGerentes;
    }

    public ArrayList<Barbeiro> getListaBarbeiros() {
        return listaBarbeiros;
    }

    public void setListaBarbeiros(ArrayList<Barbeiro> listaBarbeiros) {
        this.listaBarbeiros = listaBarbeiros;
    }

    public ArrayList<Atendente> getListaAtendentes() {
        return listaAtendentes;
    }

    public void setListaAtendentes(ArrayList<Atendente> listaAtendentes) {
        this.listaAtendentes = listaAtendentes;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ArrayList<Servico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(ArrayList<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    public ArrayList<Despesa> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(ArrayList<Despesa> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public ArrayList<RelatorioFinanceiro> getListaRelatorios() {
        return listaRelatorios;
    }

    public void setListaRelatorios(ArrayList<RelatorioFinanceiro> listaRelatorios) {
        this.listaRelatorios = listaRelatorios;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Queue<VagaListaEspera> getFilaEspera() {
        return FilaEspera;
    }

    public void setFilaEspera(Queue<VagaListaEspera> FilaEspera) {
        this.FilaEspera = FilaEspera;
    }

    public ArrayList<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

    public void setListaAgendamentos(ArrayList<Agendamento> listaAgendamentos) {
        this.listaAgendamentos = listaAgendamentos;
    }

    public ArrayList<OrdemServico> getListaOrdensServico() {
        return listaOrdensServico;
    }

    public void setListaOrdensServico(ArrayList<OrdemServico> listaOrdensServico) {
        this.listaOrdensServico = listaOrdensServico;
    }

    public TabelaPonto getTabelaPonto() {
        return tabelaPonto;
    }

    public void setTabelaPonto(TabelaPonto tabelaPonto) {
        this.tabelaPonto = tabelaPonto;
    }
    
    public void salvar() {
        GerenciadorDeArquivos.salvar(this);
    }
}
