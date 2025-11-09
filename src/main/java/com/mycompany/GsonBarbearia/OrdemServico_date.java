/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GsonBarbearia;

import com.mycompany.Utilidades.StatusAtendimento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author italo
 */
public class OrdemServico_date {
    String id;
    
    String ClienteID;
    String BarbeiroID;
    ArrayList<String> agendamentosID = new ArrayList<>(); 
    ArrayList<String> produtosUtilizadosID = new ArrayList<>();
    Map<String, Integer> produtosVendidos = new HashMap<>(); // id / quantidade
    
    StatusAtendimento status;
    LocalDate dataExecucao;
    String observacoes;
    double valorTotalServicos;
    double valorTaxaEncaixe;
    double valorAdiantado_50pct;
    double valorTaxaCancelamento_35pct;
    double valorTotalProdutos;
}
