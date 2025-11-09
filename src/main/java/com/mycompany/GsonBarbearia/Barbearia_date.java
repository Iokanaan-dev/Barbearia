/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.GsonBarbearia;

import com.mycompany.barbearia.modelos.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
/**
 *
 * @author italo
 */
public class Barbearia_date {
    
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Produto> listaProdutos = new ArrayList<>();
    public ArrayList<Servico> listaServicos = new ArrayList<>(); //vai na fé 
    public ArrayList<Despesa> listaDespesas = new ArrayList<>();
    
    public Map<String, Integer> estoque = new HashMap<>();
    public Stack<ListaEspera> listaDeEspera = new Stack<>();
    
    public ArrayList<Agendamento_date> agendamento_date = new ArrayList();
    public ArrayList<OrdemServico_date> OrdemServico_date = new ArrayList();
   
}
