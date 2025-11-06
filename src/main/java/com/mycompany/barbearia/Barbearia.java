package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
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
     
        System.out.println("Inicializando serviços de gestão...");
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        GestaoServico gestaoS = GestaoServico.getInstancia();
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoEstoque gestaoEstoque = GestaoEstoque.getInstancia();
        GestaoAgendamento gestaoAGE = GestaoAgendamento.getInstancia();
        GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        GestaoListaEspera gestaoLE = GestaoListaEspera.getInstancia(); 
        GestaoDespesas gestaoDES = GestaoDespesas.getInstancia();     
        GestaoFinanceira gestaoFIN = GestaoFinanceira.getInstancia();   
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        LocalDate hoje = LocalDate.now();
        
 
      
        System.out.println("\n--- Teste do Padrão Prototype (Clonar Cliente) ---");
    try {
        // 1. Pega o protótipo "Italo" (ID "CL1")
        gestaoC.cadastrar("Italo", "11111111111", "38998060657", data1, "italof5631@gmail.com");
        Cliente original = gestaoC.buscarPorId("CL1");
        System.out.println("Objeto Original: " + original);

        // 2. CHAMA O CONSTRUTOR DE CÓPIA (O Prototype)
        Cliente clone = new Cliente(original);
        System.out.println("\n\nObjeto Clonado:  " + clone);
        
        // 3. Prova de que é um objeto novo
        if (clone.getId().equals(original.getId())) {
             throw new Exception("Falha do Prototype: O ID do clone é o mesmo do original.");
        }
        System.out.println("SUCESSO: O clone tem um novo ID (" + clone.getId() + ").");

        // 4. Prova de que os dados foram copiados
        if (!clone.getCpf().equals(original.getCpf())) {
             throw new Exception("Falha do Prototype: O CPF não foi copiado.");
        }
        System.out.println("SUCESSO: Os dados (CPF: " + clone.getCpf() + ") foram copiados.");
        
        // 5. Prova de que são independentes
        
        System.out.println("\nNome do Original (não deve mudar): " + original.getNome());
        System.out.println("Nome do Clone (mudado):       " + clone.getNome());

    } catch (Exception e) {
        System.err.println("TESTE PROTOTYPE FALHOU: " + e.getMessage());
    }        
        
    } 
}