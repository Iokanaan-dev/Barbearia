/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Atendente;
import com.mycompany.barbearia.modelos.Usuario;
import Gerenciamento.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author italo
 */
public class Barbearia {

    public static void main(String[] args) {
        
        GestaoClientes gestaoC = new GestaoClientes();
        GestaoUsuarios gestaoU = new GestaoUsuarios(); 
// talvez valha a pena criar uma classe com nome de Sistema (acho que vale muito kkkkkkkkkkkk)
//para ser instanciada ao inves de instanciar item a item do pacote gestao (tudo é instanciado por meio do metodo de cadastro agora)
        
        // teste DE instancia
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);

        // instancia 4 clientes para testes
        try {
        gestaoC.cadastrarCliente("Italo", "11111111111", "99999999", data1);
        gestaoC.cadastrarCliente("Zeca", "22222222222", "728294729", data1);
        gestaoC.cadastrarCliente("Joao", "33333333333", "387382389", data1);
        gestaoC.cadastrarCliente("Italo", "44444444444", "000820483", data1);
        
        
        Usuario novoUsuario = new Barbeiro("italod3ad", "123456789", "italo", "33333333333", "38998090957", data1);
        Usuario novoUsuario1 = new Atendente("italod3a", "123456789", "italo", "33333333333", "38998090957", data1);
        Usuario novoUsuario2 = new Gerente("italod3ab", "123456789", "italo", "33333333333", "38998090957", data1, "8181");
        gestaoU.cadastrarUsuario(novoUsuario);
        gestaoU.cadastrarUsuario(novoUsuario1);
        gestaoU.cadastrarUsuario(novoUsuario2);
        gestaoU.removerUser(novoUsuario.getId());
        
        //Exibindo todos os clientes
        
        System.out.println("\n lista de clientes");
        ArrayList<Cliente> todosClientes = gestaoC.exibirListaClientes();
        for (Cliente cliente : todosClientes) {
            System.out.println(cliente);    
        }

        // GestaoClientes: teste para metodo de busca via nome ====================
        
        //instancia um ArrayList para ser usado nos testes de buscas
        ArrayList<Cliente> clientesSelecionados = gestaoC.buscaPorNome("Italo");
        for (Cliente cliente : clientesSelecionados) {
            System.out.println(cliente);
        }

        // Aqui o metodo busca por ID retorna um cliente que eh o que tem o ID buscado
        
        Cliente clienteId1 = gestaoC.buscaPorId("C1");
        if (clienteId1 != null){
            System.out.println("Encontrado: " + clienteId1);
        } else {
            System.out.println("Cliente não encontrado");
        }
        
        System.out.println("\n--- Removendo cliente com ID 'C3' ---");
        boolean removeu = gestaoC.removerCliente("C3");
        if (removeu) {
            System.out.println("Remoção bem-sucedida.");
        }
        
        System.out.println("\n--- Lista Final de Clientes (após remover C3) ---");
        todosClientes = gestaoC.exibirListaClientes();
        for (Cliente cliente : todosClientes) {
            System.out.println(cliente);
        }
        
        }
        catch (Exception m){
                m.printStackTrace();
        }
        
    }   
}
