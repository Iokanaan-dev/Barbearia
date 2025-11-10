/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Despesa;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.Utilidades.TipoDespesa;
import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
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
        listaModelo = dados.listaDespesas;
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
}
    