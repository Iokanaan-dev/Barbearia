/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.date_Barbearia;

import com.mycompany.barbearia.modelos.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

/**
 * Aqui aglomeramos todos os dados, esse sera o arquivo json com todos os dados da barbearia
 * @author italo
 */
public class Barbearia_date {
    
    public ListaUnica<Cliente> listaClientes = new ListaUnica<>();
    public ListaUnica<Gerente> listaGerentes = new ListaUnica<>();
    public ListaUnica<Barbeiro> listaBarbeiros = new ListaUnica<>();
    public ListaUnica<Atendente> listaAtendentes = new ListaUnica<>();
    public ListaUnica<Produto> listaProdutos = new ListaUnica<>();
    public ListaUnica<Servico> listaServicos = new ListaUnica<>(); 
    public ListaUnica<Despesa> listaDespesas = new ListaUnica<>();
    
    public Map<String, Integer> estoque = new HashMap<>();
    public Stack<ListaEspera> listaDeEspera = new Stack<>();
    
    public ListaUnica<Agendamento> listaAgendamentos = new ListaUnica<>();
    public ListaUnica<OrdemServico> listaOrdensServico = new ListaUnica<>();
    public TabelaPonto tabelaPonto;
    
    public Barbearia_date(){
        this.listaClientes = new ListaUnica<>();
        this.listaGerentes = new ListaUnica<>();
        this.listaBarbeiros = new ListaUnica<>();
        this.listaAtendentes = new ListaUnica<>();
        this.listaProdutos = new ListaUnica<>();
        this.listaServicos = new ListaUnica<>();
        this.listaDespesas = new ListaUnica<>();
        this.listaAgendamentos = new ListaUnica<>();
        this.listaOrdensServico = new ListaUnica<>();
        this.estoque = new HashMap<>();
        this.listaDeEspera = new Stack<>();
        this.tabelaPonto = new TabelaPonto();
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Gerente> getListaGerentes() {
        return listaGerentes;
    }

    public ArrayList<Barbeiro> getListaBarbeiros() {
        return listaBarbeiros;
    }

    public ArrayList<Atendente> getListaAtendentes() {
        return listaAtendentes;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public ArrayList<Servico> getListaServicos() {
        return listaServicos;
    }

    public ArrayList<Despesa> getListaDespesas() {
        return listaDespesas;
    }

    public Map<String, Integer> getEstoque() {
        return estoque;
    }

    public Stack<ListaEspera> getListaDeEspera() {
        return listaDeEspera;
    }

    public ArrayList<Agendamento> getListaAgendamentos() {
        return listaAgendamentos;
    }

    public ArrayList<OrdemServico> getListaOrdensServico() {
        return listaOrdensServico;
    }

    public TabelaPonto getTabelaPonto() {
        return tabelaPonto;
    }
}
