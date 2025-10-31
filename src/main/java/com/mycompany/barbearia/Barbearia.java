/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Listas.ListaGenerica;

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
        GestaoServico gestaoS = new GestaoServico();
        GestaoProdutos gestaoP = new GestaoProdutos();
        GestaoOrdemServico gestaoOS = new GestaoOrdemServico();
        
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


        
        System.out.println("Tentando editar cliente C2");
        gestaoC.editarCliente(gestaoC.buscarID("C2"), "Cabuloso", "33333333333","38998090957", data1, "perebinha@gmail");
        printArrayList(todosClientes);
        
        System.out.println("Tentando editar cliente CL2");
        gestaoC.editarCliente(gestaoC.buscarID("CL2"), "Cabuloso", "33333333333","38998090957", data1, "perebinha@gmail");
        printArrayList(todosClientes);
        
        */ 
     
        // TESTES USUARIOS---------------------------------------------------
        
        Usuario novoUsuario0 = new Barbeiro("italod1ad", "123456789", "marcos", "33333333333", "38998090957", data1);
        Usuario novoUsuario1 = new Atendente("italod3ad", "123456789", "pedro", "33333333333", "38998090957", data1);
        Usuario novoUsuario2 = new Gerente("italod3ab", "123456789", "joão", "33333333333", "38998090957", data1, "8181");
        
        gestaoU.cadastrarUsuario(novoUsuario0);
        gestaoU.cadastrarUsuario(novoUsuario1);
        gestaoU.cadastrarUsuario(novoUsuario2);
        
        //gestaoU.removerUser(novoUsuario0.getId());
        
        //System.out.println("\nDigite o ID do usuario\n");
        
        //gestaoU.editarUsuarioAtributos(novoUsuario0, "calabreso", "12755050667", "38998909068", data1);
        //gestaoU.editarUsuarioLogin(novoUsuario2, "italod3ab", "123456789", "carinhafeliz", "00000000");
        
        
        System.out.print("Lista de usuarios inicial");
        ArrayList<Usuario> todosUsuarios = gestaoU.getListaUsuarios();
        printArrayList(todosUsuarios);
        
        //gestaoU.removerUser(novoUsuario.getId());
        System.out.println("\nDigite o ID do usuario\n");
        
        //gestaoU.editarUsuarioAtributos(novoUsuario0, "calabreso", "12755050667", "38998909068", data1);
        //gestaoU.editarUsuarioLogin(novoUsuario2, "italod3ab", "123456789", "carinhafeliz", "00000000");
        
        
        
      // TESTES SERVICOS------------------------------------------------------
        
        gestaoS.cadastrarNovoServico("Pintada", 99, "Uma bela de uma pintada", 70);
        gestaoS.cadastrarNovoServico("Corte", 35.00, "Corte geral", 30);
        gestaoS.cadastrarNovoServico("Escova", 45.00, "Escova feminina", 10);

        System.out.print("Lista de servicos inicial");
        ArrayList<Servico> todosServicos = gestaoS.getServicos();
        printArrayList(todosServicos);
        
      // TESTES PRODUTOS------------------------------------------------------
        
        gestaoP.cadastrarProduto("Balm Madeira", 25.55, "Um Balm");
        gestaoP.cadastrarProduto("Pomada Cobre", 15.55, "Uma pomada");
        

        System.out.print("Lista de produtos inicial");
        ArrayList<Produto> todosProdutos = gestaoP.getLista();
        printArrayList(todosProdutos);        
        
        
      //TESTES ORDENS DE SERVICO
      
        
      
        }
        
        catch (Exception m)
        {
                m.printStackTrace();
        }
        
    }  
    
    // metodo para imprimir ArrayLists do tipo Modelo, serve para ajudar a testar
    // passe aqui qualquer arrayList que voce queira imprimir
    public static void printArrayList(ArrayList< ? extends Modelo> Modelos)
    {
        for (Modelo m : Modelos)
            System.out.println(m);
        System.out.println();
    } 
    
}
