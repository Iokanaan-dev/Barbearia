package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import Utilidades.Prototype;
import Utilidades.TipoDespesa;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {

    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO SISTEMA DE BARBEARIA (MODO DE TESTE) ---");
        GestaoServico gestaoS = GestaoServico.getInstancia();
      
        System.out.println("\n--- Teste do Padrão Prototype (Clonar Servico) ---");
        
      // cria um objeto
      Servico servico01 = new Servico("Corte Social Maquina", 20.00, "Corte social padrao", 2, TipoEstacao.CORRIQUEIRA);

      gestaoS.cadastrar(servico01);
      System.out.println("Lista se serviços inicial");
      gestaoS.printLista();
      
      // cria uma variavel que armazenara o clone
      Servico cloneServico01;
      
      
      // atribui o clone de servico01 a servico01Clone
      cloneServico01 = servico01.clone();
      System.out.println("---Serviço e Seu clone---");
      System.out.printf("Servico original:%n%s%nClone:%n%s%n", servico01, cloneServico01);      
      
      // cadastra o clone
      gestaoS.cadastrar(cloneServico01);
      
      // edita os valores necessarios do clone
      cloneServico01.editarClone("Corte Social Tesoura", 30.00, 3);
      
      System.out.println("\n---Lista servicos inicial e clone editado---");            
      gestaoS.printLista();

    } 
}