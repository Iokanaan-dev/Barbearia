/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.JacksonBarbearia;

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
    
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Gerente> listaGerentes = new ArrayList<>();
    public ArrayList<Barbeiro> listaBarbeiros = new ArrayList<>();
    public ArrayList<Atendente> listaAtendentes = new ArrayList<>();
    public ArrayList<Produto> listaProdutos = new ArrayList<>();
    public ArrayList<Servico> listaServicos = new ArrayList<>(); 
    public ArrayList<Despesa> listaDespesas = new ArrayList<>();
    
    public Map<String, Integer> estoque = new HashMap<>();
    public Stack<ListaEspera> listaDeEspera = new Stack<>();
    
    public ArrayList<Agendamento> listaAgendamentos = new ArrayList<>();
    public ArrayList<OrdemServico> listaOrdensServico = new ArrayList<>();
    public TabelaPonto tabelaPonto;
    
    public Barbearia_date(){}
   
}
