/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import com.mycompany.barbearia.individuo.*;
import Gerenciamento.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class Barbearia {

    public static void main(String[] args) {
        
        GestaoClientes gestaoC = new GestaoClientes(); // talvez valha a pena criar uma classe com nome de Sistema
                                           //para ser instanciada ao inves de instanciar item a item do pacote gestao
        
        // teste que instancia clientes e adicina a um arrayList
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);

        // instancia 4 clientes para testes
        Cliente cliente1 = new Cliente("Italo", "11111111111", "99999999", data1);
        Cliente cliente2 = new Cliente("Zeca", "22222222222", "728294729", data1);
        Cliente cliente3 = new Cliente("Joao", "33333333333", "387382389", data1);
        Cliente cliente4 = new Cliente("Italo", "44444444444", "000820483", data1);

        
        // instancia o ArrayList e o preenche com 4 clientes para testes
        ArrayList<Cliente> clientesAdcionados = new ArrayList<>();
        
        clientesAdcionados.add(cliente1);
        clientesAdcionados.add(cliente2);
        clientesAdcionados.add(cliente3);
        clientesAdcionados.add(cliente4);
        

        // GestaoClientes: teste para metodo de busca via nome ====================
        
        //instancia um ArrayList para ser usado nos testes de buscas
        ArrayList<Cliente> clientesSelecionados = new ArrayList<>();
        
        clientesSelecionados = gestaoC.buscaPorNome(clientesAdcionados, "Italo");
        
        System.out.println("Clientes selecionados por nome 'Italo': ");
        printArrayList(clientesSelecionados);
        
        // Aqui o metodo busca por ID retorna um cliente que eh o que tem o ID buscado
        System.out.printf("Cliente selecionado por ID '1': %n%s%n", gestaoC.buscaPorId(clientesAdcionados, 1));
        System.out.println();
                
        // GestaoClientes:: teste para metodo de cadastro do cliente=================
        
        gestaoC.cadastrarCliente("Italo", "55555555555", "018999999", data1);
        gestaoC.cadastrarCliente("Luiz", "66666666666", "873847397", data1);
        
        System.out.println("Clientes inseridos via cadastrarCliente:");
        gestaoC.exibirListaClientes();

    }
    
    // metodo para testar os ArrayLists, percorre todo o ArrayList e printa seus valores
    public static void printArrayList(ArrayList<Cliente> arrayList)
    {
        for(Cliente item: arrayList)
            System.out.println(item);
        
        System.out.println();

    }
            
            
       
}
