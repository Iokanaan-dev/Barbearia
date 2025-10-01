/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import java.util.ArrayList;
import com.mycompany.barbearia.individuo.Cliente;
import java.time.LocalDate;
import Listas.ListaClientes;

/**
 *
 * @author italo
 */
public class GestaoClientes {   
    
    // cria uma lista de clientes que sera gerenciada
    private ListaClientes listaClientes = new ListaClientes();
    
    /**
     * Utiliza ArrayList para alocaçao dinamica dos clientes. Testa cada cliente passado para ver se seu nome corresponde ao buscado.
     * 
     * @param clientes
     * @param nome
     * @return 
     */
    
    public ArrayList<Cliente> buscaPorNome(ArrayList<Cliente> clientes, String nome){
       
        ArrayList<Cliente> clientesSelecionados = new ArrayList<>(); 
        
        for(Cliente c: clientes){
            if(c.getNome() == nome)
                clientesSelecionados.add(c);
        }
        return clientesSelecionados; // retorna um ArrayList com todos os clientes que tem o mesmo nome que o buscado
    }
    
    public Cliente buscaPorId(ArrayList<Cliente> clientes, int idCliente){
       
        Cliente clienteSelecionado = null; 
        
        for(Cliente c: clientes){
            if(c.getId() == idCliente)
                clienteSelecionado = c;
        }
        return clienteSelecionado; // retorna um Cliente com o mesmo id buscado
    }    
    
    // metodo que coleta os dados do cliente para o construir e passa para adicionar cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, LocalDate dataNascimento)
    {
        Cliente novoCliente = new Cliente(nome, cpf, telefone, dataNascimento);
        listaClientes.adicionarCliente(novoCliente);
    }
    
    //
    public void exibirListaClientes()
    {
        for(Cliente cliente: listaClientes.getClientes())
            System.out.println(cliente);
    }


    
}

