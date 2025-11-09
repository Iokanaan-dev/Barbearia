
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//
package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import Gerenciamento.*;
import Utilidades.TipoDespesa;
import Utilidades.TipoUsuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
    
    public static int totalOrdensServico() {
        return OrdemServico.getContador();
    }
    
    
    
//
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        // CLIENTES        
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        gestaoC.cadastrar("Italo Souza", "12312312312", "999763827", LocalDate.MIN, "borabill@gmail.com");
        gestaoC.cadastrar("Mariana Alves", "11122233344", "999112233", LocalDate.MIN, "mariana.alves@gmail.com");
        gestaoC.cadastrar("Carlos Pereira", "22233344455", "998223344", LocalDate.MIN, "carlos.pereira@gmail.com");
        gestaoC.cadastrar("Ana Beatriz Lima", "33344455566", "997334455", LocalDate.MIN, "ana.lima@gmail.com");
        gestaoC.cadastrar("Ricardo Gomes", "44455566677", "996445566", LocalDate.MIN, "ricardo.gomes@gmail.com");
        gestaoC.cadastrar("Paula Rocha", "55566677788", "995556677", LocalDate.MIN, "paula.rocha@gmail.com");
        gestaoC.cadastrar("Fernando Costa", "66677788899", "994667788", LocalDate.MIN, "fernando.costa@gmail.com");
        gestaoC.cadastrar("Juliana Martins", "77788899900", "993778899", LocalDate.MIN, "juliana.martins@gmail.com");
        gestaoC.cadastrar("Lucas Fernandes", "88899900011", "992889900", LocalDate.MIN, "lucas.fernandes@gmail.com");
        gestaoC.cadastrar("Bianca Souza", "99900011122", "991990011", LocalDate.MIN, "bianca.souza@gmail.com");
        gestaoC.cadastrar("Thiago Nascimento", "00011122233", "999001122", LocalDate.MIN, "thiago.nascimento@gmail.com");
        
        gestaoC.remover(gestaoC.buscarPorNome("Italo Souza").get(0).getId());
        
        System.out.println(gestaoC.buscarPorNome("Mariana Alves").get(0));
        
        gestaoC.editar(gestaoC.buscarCPF("11122233344").getId(), "Zeca", "11111111111", "77777777", LocalDate.MIN, "mariana.alves@gmail.com");
        
        //gestaoC.printLista();
        gestaoC.printPorNome("Bianca");
        
        // USUARIOS
        
    GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();

    // Barbeiros (maioria)
    gestaoU.cadastrar("joao.silva",    "48201937", "João Silva",       "12311122233", "999111000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("pedro.santos",  "90123456", "Pedro Santos",     "12322233344", "999222000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("lucas.almeida", "77551234", "Lucas Almeida",    "12333344455", "999333000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("gustavo.lima",  "33019827", "Gustavo Lima",     "12344455566", "999444000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("felipe.costa",  "88776655", "Felipe Costa",     "12355566677", "999555000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("rafael.moreira","10293847", "Rafael Moreira",   "12366677788", "999666000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("diego.rocha",   "56473829", "Diego Rocha",      "12377788899", "999777000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("bruno.carvalho", "44332211","Bruno Carvalho",   "12388899900", "999888000", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("marcelo.oliveira","21987654","Marcelo Oliveira","12411122233", "999990001", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("andrew.souza",  "66778899", "Andrew Souza",     "12422233344", "999223344", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("mateus.freitas", "11223344","Mateus Freitas",   "12433344455", "999334455", LocalDate.MIN, TipoUsuario.BARBEIRO);
    gestaoU.cadastrar("henrique.nunes","99887766", "Henrique Nunes",   "12444455566", "999445566", LocalDate.MIN, TipoUsuario.BARBEIRO);

    // Atendentes
    gestaoU.cadastrar("marina.dias",   "55667788", "Marina Dias",      "32111122233", "988111000", LocalDate.MIN, TipoUsuario.ATENDENTE);
    gestaoU.cadastrar("larissa.souza", "33445566", "Larissa Souza",    "32122233344", "988222000", LocalDate.MIN, TipoUsuario.ATENDENTE);
    gestaoU.cadastrar("sophia.moraes", "77889900", "Sophia Moraes",    "32133344455", "988333000", LocalDate.MIN, TipoUsuario.ATENDENTE);

    // Gerente
    gestaoU.cadastrar("roberto.gerente","90011223","Roberto Manager",  "99911100022", "977111000", LocalDate.MIN, TipoUsuario.GERENTE, "12345");

    gestaoU.printLista();

    // Exemplo de remoção e edição
    gestaoU.remover(gestaoU.buscarPorNome("henrique nunes").get(0).getId()); 
    gestaoU.editar("roberto.gerente", "90011223", gestaoU.buscarUsername("marina.dias").getId(), "Pedro Santos", "12322233344", "988765432", LocalDate.MIN);

    // Impressões
    gestaoU.printLista();
    
    //PRODUTOS
    
    GestaoProdutos gestaoP = GestaoProdutos.getInstancia();

    gestaoP.cadastrar("Pomada Modeladora", 35.90, "Fixação alta, efeito matte");
    gestaoP.cadastrar("Gel Fixador", 19.90, "Brilho molhado e longa duração");
    gestaoP.cadastrar("Óleo para Barba", 29.90, "Hidratação e maciez");
    gestaoP.cadastrar("Cera de Cabelo", 27.50, "Fixação média, acabamento seco");
    gestaoP.cadastrar("Peitoral Descartável", 12.00, "Pacote com 10 unidades");
    gestaoP.cadastrar("Navalha Profissional", 89.90, "Aço inox, alta precisão");
    gestaoP.cadastrar("Lâminas Extra Sharp", 15.90, "Caixa com 20 unidades");
    gestaoP.cadastrar("Shampoo Anticaspa", 24.90, "Limpeza profunda do couro cabeludo");
    gestaoP.cadastrar("Tônico Capilar", 32.00, "Fortalece a raiz e estimula crescimento");
    gestaoP.cadastrar("Pente Profissional", 9.90, "Resistente ao calor");
    
    gestaoP.printLista();

    // Testes igual ao padrão dos usuários
    gestaoP.remover(gestaoP.buscarPorNome("Peitoral Descartável").get(0).getId()); // remove exemplo (depende do ID gerado no seu sistema)
    gestaoP.editar(gestaoP.buscarPorNome("Gel Fixador").get(0).getId(), "Gel Fixador Extra", 22.90, "Brilho molhado extra forte");

    //gestaoP.printLista();
    gestaoP.printPorNome("Cera");
    
    GestaoServico gestaoS = GestaoServico.getInstancia();

    gestaoS.cadastrar("Corte Social", 30.0, "Serviço de corte com acabamento profissional", 3, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Corte Degradê", 45.0, "Serviço de corte com acabamento profissional", 4, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Barba Completa", 35.0, "Serviço de barba com acabamento profissional", 3, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Barba Express", 25.0, "Serviço de barba com acabamento profissional", 2, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Sobrancelha", 15.0, "Serviço de design com acabamento profissional", 1, TipoEstacao.CORRIQUEIRA);

    gestaoS.cadastrar("Lavagem Simples", 10.0, "Serviço de lavagem com acabamento profissional", 1, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Lavagem + Hidratação", 25.0, "Serviço de lavagem com acabamento profissional", 3, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Lavagem Premium", 30.0, "Serviço de lavagem com acabamento profissional", 4, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Limpeza Facial", 50.0, "Serviço estético com acabamento profissional", 5, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Platinado Completo", 120.0, "Serviço químico com acabamento profissional", 6, TipoEstacao.LAVAGEM);

    gestaoS.cadastrar("Pezinho", 10.0, "Serviço de acabamento com padrão profissional", 2, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Corte Infantil", 28.0, "Serviço de corte com acabamento profissional", 3, TipoEstacao.CORRIQUEIRA);
    gestaoS.cadastrar("Tintura Capilar", 70.0, "Serviço químico com acabamento profissional", 4, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Relaxamento Capilar", 90.0, "Serviço químico com acabamento profissional", 5, TipoEstacao.LAVAGEM);
    gestaoS.cadastrar("Progressiva", 150.0, "Serviço químico com acabamento profissional", 6, TipoEstacao.LAVAGEM);

    // Testes
    gestaoS.remover(gestaoS.buscarPorNome("Corte Social").get(0).getId());
    gestaoS.editar(gestaoS.buscarPorNome("Progressiva").get(0).getId(), "Polimento de Careca", 30.0, "Passar pano na careca", 4);

    // gestaoS.printLista();
    gestaoS.printPorNome("Corte");
    
    
    //Cliente clinete1 = gestaoC.buscarCPF("12312312312");
    //Usuario useer1 = gestaoU.buscarPorNome("joao.silva").get(0);
    
    //clinete1.getContador();
    
    
    
    
    
    
    

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}