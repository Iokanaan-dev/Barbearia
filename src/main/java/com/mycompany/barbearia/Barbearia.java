package com.mycompany.barbearia;

import com.mycompany.date_Barbearia.Barbearia_date;
import com.mycompany.Gerenciamento.*;
import com.mycompany.Gerenciamento.Sistema;
import InterfaceG.*;
/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
   
    
    public static void main(String[] args) throws Exception {
        //  QUESTOES=========================================================   
        
        //14 - parte I ======================================================

        //  Carrega dados existentes ou cria novos
        
        //try {
            Barbearia_date dados = Barbearia_date.getInstancia();

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
            
            /*
            GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
            
            
            
            boolean existeGerente = !gestaoU.getListaGerentes().isEmpty();
            
            if (!existeGerente) {
                // PRIMEIRA VEZ – não existe gerente salvo
                java.awt.EventQueue.invokeLater(() ->
                    new PrimeiroLoginSistema().setVisible(true)
                );
            } else {
                // já existe gerente → ir direto para tela de login
                java.awt.EventQueue.invokeLater(() ->
                    new TelaDeLogin().setVisible(true)
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao inicializar os dados: " + e.getMessage());
            e.printStackTrace();
        }
        */

        try {
            Sistema sistema = new Sistema();

            //03 ===========================================================
            //System.out.println("\nQUESTAO 03");
             //sistema.questao03();            
            
            //06 ===========================================================
            //System.out.println("\nQUESTAO 06");
             //sistema.questao06();

            //07 ===========================================================
           // System.out.println("\nQUESTAO 07");
            //sistema.questao07();

            //08 ===========================================================
           // System.out.println("\nQUESTAO 08");
             //sistema.questao08();  
            
            //10 ===========================================================
            //System.out.println("\nQUESTAO 10");
             //sistema.questao10();            

            //11 ===========================================================
           // System.out.println("\nQUESTAO 11");
             //sistema.questao11();

            //12 ===========================================================
            //System.out.println("\nQUESTAO 12");
             //sistema.questao12();  
            
            //13 ==========================================================
           // System.out.println("\nQUESTAO 13");
            //sistema.questao13();
            
            //14 ==========================================================
             //System.out.println("\nQUESTAO 14");
            //sistema.questao14();
            
            //15P2 ========================================================
           // System.out.println("\nQUESTAO 15P2");
            //sistema.questao15P2();
            
            //16P2 ======================================================== 
            //System.out.println("\nQUESTAO 16P2");
            //sistema.questao16P2();
            
            //17P2 ======================================================== 
            //System.out.println("\nQUESTAO 17P2");
            //sistema.questao17P2();
            
            
            
            

        } catch (Exception e) {
            System.out.println("Erro ao executar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
      



    
 
                
        // 14 - Parte II ====================================================
        //GerenciadorDeArquivos.salvar(dados); todos esses dados devem ser carregados dentro das gestões em seus metodos de cadastro 
    
        }
}
