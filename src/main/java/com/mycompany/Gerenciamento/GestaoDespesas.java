/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Despesa;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.Utilidades.TipoDespesa;
import com.mycompany.barbearia.modelos.Produto;
import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Classe que gerencia as despesas
 * @author italo
 */
public class GestaoDespesas extends Gestao<Despesa> {
    
    private static GestaoDespesas instancia;
    private Barbearia_date dados;  
    
    GestaoDespesas(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.getListaDespesas();
    }
    
    /**
     * Inicializa os dados
     * @param dados
     */
    public static void inicializa(Barbearia_date dados) {
            if (instancia == null) {
            instancia = new GestaoDespesas(dados);
        }
    }
   
    /**
     * Obtem a instancia singleton
     * @return
     */
    public static GestaoDespesas getInstancia(){
        return instancia;
    }
    
    /**
     * Construtor privado
     */
     private GestaoDespesas(){}
    
    /**
     * Adiciona a despesa na lista de despesas
     * @param nome da despesa
     * @param valor da despesa
     * @param data da despesa
     * @param tipo da despesa
     * @param obs a respeito da despesa
     * @param user que sera validado para lancar a despesa
     * @throws Exception
     */
    public void lancarDespesas(String nome, Double valor, LocalDate data, TipoDespesa tipo, String obs, Usuario user) throws Exception {
       
        if (!(user instanceof Gerente)) {
            throw new Exception("Acesso negado, só gerentes podem lancar despesas");
        }
        
        Despesa novaDespesa = new Despesa(nome, valor, data, tipo, obs);
        super.adicionar(novaDespesa);
    }
    
    /**
     * Retorna a lista de despesas. Funcao ADM.
     * @param user que sera validado para lancar a despesa
     * @return uma copia da lista de despesas
     * @throws Exception
     */
    public ArrayList<Despesa> getList(Usuario user) throws Exception {
        if(!(user instanceof Gerente)) {
            throw new Exception("Acesso negado");
        }
        return new ArrayList(this.listaModelo);
    }
    
    /**
     * Lança uma despesa de "Custo de Mercadoria Usada" 
     * com base em uma lista de produtos consumidos gerada 
     * em'getRelatorioProdutosUsados' da GestaoFinanceira). Funçao ADM
     * @param produtosConsumidos por um barbeiro
     * @param data 
     * @param observacoes
     * @param ator 
     * @throws java.lang.Exception
     */
    public void lancarDespesaDeConsumo(ArrayList<Produto> produtosConsumidos, LocalDate data, String observacoes, Usuario ator) throws Exception {
        
        if (!(ator instanceof Gerente)) {
            throw new Exception("Acesso negado.");
        }
        
        if (produtosConsumidos == null || produtosConsumidos.isEmpty()) {
            throw new Exception("Lista de produtos consumidos está vazia.");
        }

         //Calcula o custo total 
        double custoTotal = 0.0;
        for (Produto p : produtosConsumidos) {
            custoTotal += p.getCusto(); 
        }
        
        String nomeDespesa = "Custo de Consumíveis - " + data.format(DateTimeFormatter.ofPattern("MM/yyyy"));
        
        // chama lançar despesas com o calculo dos produtos já feito 
        this.lancarDespesas(nomeDespesa, custoTotal, data, TipoDespesa.CONSUMIVEIS, observacoes, ator);
    }
}