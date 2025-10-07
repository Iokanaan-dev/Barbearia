/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class Barbearia {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        // Instanciando gestoes
        GestaoClientes gestaoC = new GestaoClientes();
        GestaoUsuarios gestaoU = new GestaoUsuarios(); 
        // talvez valha a pena criar uma classe com nome de Sistema (acho que vale muito kkkkkkkkkkkk)
        //para ser instanciada ao inves de instanciar item a item do pacote gestao (tudo é instanciado por meio do metodo de cadastro agora)
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);

        try {
        
        // TESTES CLIENTES---------------------------------------------------
        
        // instancia 4 clientes para testes
        gestaoC.cadastrarCliente("Italo", "11111111111", "99999999", data1, "italo@picles.com");
        gestaoC.cadastrarCliente("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
        gestaoC.cadastrarCliente("Joao", "33333333333", "387382389", data1, "receba@melhordomundo.com");
        gestaoC.cadastrarCliente("Italo", "44444444444", "000820483", data1, "borabill@gmail.com");
       
        //Exibindo todos os clientes
        System.out.print("Lista de clientes inicial");
        ArrayList<Cliente> todosClientes = gestaoC.getListaClientes();
        printArrayList(todosClientes);

     /*
      // GestaoClientes: teste para metodo de busca via nome ====================
      
        System.out.println("Buscando clientes chamados 'Italo' \n");
       
        //instancia um ArrayList para ser usado nos testes de buscas
        ArrayList<Cliente> clientesSelecionados = gestaoC.buscarNome("Italo");
        printArrayList(clientesSelecionados);
        
        // busca com elemento inexistente para testar a exeçao NoSuchElementException
        System.out.println("\n Buscando clientes chamados 'XXX' \n");
        clientesSelecionados = gestaoC.buscarNome("XXX");
        printArrayList(clientesSelecionados);     
      
        
      // GestaoClientes: teste para metodo de busca via id ====================
       
        System.out.println("Buscando clientes com ID 'C3' \n");
       
        // instancia um cliente para ser usado no teste de busca por ID
        Cliente clienteSelecionado = gestaoC.buscarID("C3");
        System.out.println(clienteSelecionado);
        
        // busca com elemento inexistente para testar a exeçao NoSuchElementException
        System.out.print("\n Buscando clientes com ID 'XX' \n");
        clienteSelecionado = gestaoC.buscarID("XX");
        System.out.println(clienteSelecionado);
        
      
        System.out.println("Removendo cliente");
        gestaoC.removerCliente("C1"); 
        gestaoC.removerCliente("XX"); //tentativa de remocao de cliente que nao existe
        
        System.out.println("\n Lista de clientes apos remocao do cliente com ID C1");
        todosClientes = gestaoC.getListaClientes();
        printArrayList(todosClientes);                 
       */ 
     
        System.out.println("Tentando editar cliente C2");
        gestaoC.editarCliente(gestaoC.buscarID("C2"), "Cabuloso", "33333333333","38998090957", data1, "perebinha@gmail");
        printArrayList(todosClientes);
        
        // TESTES USUARIOS---------------------------------------------------
        /*
        Usuario novoUsuario = new Barbeiro("italod1ad", "123456789", "marcos", "33333333333", "38998090957", data1);
        Usuario novoUsuario1 = new Atendente("italod3ad", "123456789", "pedro", "33333333333", "38998090957", data1);
        Usuario novoUsuario2 = new Gerente("italod3ab", "123456789", "joão", "33333333333", "38998090957", data1, "8181");
        gestaoU.cadastrarUsuario(novoUsuario);
        gestaoU.cadastrarUsuario(novoUsuario1);
        gestaoU.cadastrarUsuario(novoUsuario2);
        //gestaoU.removerUser(novoUsuario.getId());
        System.out.println("\nDigite o ID do usuario\n");

        

        
        
        
        System.out.println("\n--- Lista Final de Clientes");
        todosClientes = gestaoC.getListaClientes();
        for (Cliente clientes : todosClientes) {
            System.out.println(clientes);
        }
        
        gestaoU.editarUsuarioAtributos(novoUsuario, "calabreso", "12755050667", "38998909068", data1);
        //gestaoU.editarUsuarioLogin(novoUsuario2, "italod3ab", "123456789", "carinhafeliz", "00000000");
        
        
        System.out.println("\nLista usuarios\n");
        ArrayList<Usuario> todosUsuarios = gestaoU.exibirListaUsuarios();
        for (Usuario usuarios : todosUsuarios){
            System.out.println(usuarios);
        } 
        */
        
      // TESTES PRODUTOS E SERVICOS---------------------------------------------------
      /*
        Produto produto1 = new Produto("Balm", 10.34);
        Servico servico1 = new Servico("Corte", 35.00);
        Servico servico2 = new Servico("Escova", 45.00);
        
        System.out.println(produto1);
        System.out.println(servico1);
        System.out.println(servico2);
        */
        
        
        }
        
        catch (Exception m)
        {
                m.printStackTrace();
        }
        
    }  
    
    // metodo para imprimir ArrayLists do tipo Modelo, serve para ajudar a testar
    public static void printArrayList(ArrayList< ? extends Modelo> Modelos)
    {
        for (Modelo m : Modelos)
            System.out.println(m);
    }

        
}
