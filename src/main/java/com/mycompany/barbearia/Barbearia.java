package com.mycompany.barbearia;

import com.mycompany.date_Barbearia.GerenciadorDeArquivos;
import com.mycompany.date_Barbearia.Barbearia_date;
import com.mycompany.Gerenciamento.*;
import com.mycompany.Utilidades.*;
import com.mycompany.barbearia.modelos.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import com.mycompany.Gerenciamento.Sistema;
/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
    
    // (Seu método totalOrdensServico() está ótimo)
    
    public static void main(String[] args) throws Exception {
        //  QUESTOES=========================================================   
        
        //14 - parte I ======================================================

        // 🔹 Carrega dados existentes ou cria novos
        try {
            Barbearia_date dados = GerenciadorDeArquivos.carregar();

            GestaoClientes.inicializar(dados);
            GestaoUsuarios.inicializar(dados);
            GestaoServicos.inicializa(dados);
            GestaoDespesas.inicializa(dados);
            GestaoProdutos.inicializa(dados);
            GestaoEstoque.inicializa(dados);
            GestaoAgendamentos.inicializa(dados);
            GestaoOrdemServico.inicializar(dados);
            GestaoListaEspera.inicializar(dados);
            GestaoPonto.inicializar(dados);
            GestaoFinanceira.inicializar(dados);
            
        } catch (Exception e) {
            System.out.println("Erro ao inicializar os dados: " + e.getMessage());
            e.printStackTrace();
        }
     

        try {
            Sistema sistema = new Sistema();

            //06 ===========================================================
            //sistema.questao06();

            //07 ===========================================================
            //sistema.questao07();

            //08 ===========================================================
            //sistema.questao08();        

            //11 ===========================================================
            //sistema.questao11();

            //12 ===========================================================
            //sistema.questao12();
            
            //13 ==========================================================
            //sistema.questao13();
            
            //15P2 ========================================================
            //sistema.questao15P2();
            
            //16P2 ======================================================== 
            sistema.questao16P2();
            
            //17P2 ======================================================== 
            sistema.questao17P2();
            
            
        } catch (Exception e) {
            System.out.println("Erro ao executar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
      



    
 
                
        // 14 - Parte II ====================================================
        //GerenciadorDeArquivos.salvar(dados); todos esses dados devem ser carregados dentro das gestões em seus metodos de cadastro 
    
        }
}
