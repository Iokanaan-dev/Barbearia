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
 *
 * @author italo
 */
public class GestaoDespesas extends Gestao<Despesa> {
    
    private static GestaoDespesas instancia;
    private Barbearia_date dados;  
    
    GestaoDespesas(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.getListaDespesas();
    }
    
    public static void inicializa(Barbearia_date dados) {
            if (instancia == null) {
            instancia = new GestaoDespesas(dados);
        }
    }
   
    public static GestaoDespesas getInstancia(){
        return instancia;
    }
    
     private GestaoDespesas(){}
    
    public void lancarDespesas(String nome, Double valor, LocalDate data, TipoDespesa tipo, String obs, Usuario user) throws Exception {
       
        if (!(user instanceof Gerente)) {
            throw new Exception("Acesso negado, só gerentes podem lancar despesas");
        }
        
        Despesa novaDespesa = new Despesa(nome, valor, data, tipo, obs);
        super.adicionar(novaDespesa);
    }
    
    public ArrayList<Despesa> getList(Usuario user) throws Exception {
        if(!(user instanceof Gerente)) {
            throw new Exception("Acesso negado");
        }
        return new ArrayList(this.listaModelo);
    }
    
    /**
     * Lança uma despesa de "Custo de Mercadoria Usada" 
     * com base em uma lista de produtos consumidos gerada em'getRelatorioProdutosUsados' da GestaoFinanceira).
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
    