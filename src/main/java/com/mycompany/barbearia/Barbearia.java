package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;

/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {

    public static void main(String[] args) {
        
      System.out.println("--- INICIANDO SISTEMA DE BARBEARIA (MODO DE TESTE) ---");
      GestaoServico gestaoS = GestaoServico.getInstancia();
      System.out.println("\n--- Teste do Padrão Prototype (Clonar Servico) ---");
      
      //======================================================================  
      
      // cria um objeto
      Servico servico01 = new Servico("Corte Social Maquina", 20.00, "Corte social padrao", 2, TipoEstacao.CORRIQUEIRA);

      gestaoS.cadastrar(servico01);
      
      // cria uma variavel que armazenara o clone
      Servico cloneServico01;
      
      // atribui o clone de servico01 a servico01Clone
      cloneServico01 = servico01.clone();
    
      // cadastra o clone
      gestaoS.cadastrar(cloneServico01);
      
      // edita os valores necessarios do clone
      cloneServico01.editarClone("Corte Social Tesoura", 30.00, 3);
    
    } 
}