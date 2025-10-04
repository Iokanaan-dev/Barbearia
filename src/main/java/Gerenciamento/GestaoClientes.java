/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Cliente;
import java.time.LocalDate;
import Listas.ListaGenerica;

/**
 *
 * @author italo
 */
public class GestaoClientes {   
    
    // cria uma lista de clientes que sera gerenciada
    private final ListaGenerica<Cliente> listaClientes = new ListaGenerica();
    
    /**
     * Utiliza ArrayList para alocaçao dinamica dos clientes. Testa cada cliente passado para ver se seu nome corresponde ao buscado.
     * 
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param nome 
     */ 
    
    // metodo que coleta os dados do cliente para o construtor e passa para adicionar cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, LocalDate dataNascimento){
        Cliente novoCliente = new Cliente(nome, cpf, telefone, dataNascimento);
        this.listaClientes.adicionar(novoCliente);
    }
    
    public ArrayList<Cliente> verificarClienteCadastrado(String nome){
      return this.listaClientes.buscaPorNome(nome);
    }
    
    public Cliente buscarID(String ID){
       return this.listaClientes.buscaPorId(ID);
    }
    
    public void editarCliente(Cliente objeto, String nome, String cpf, String telefone, LocalDate dataNascimento){
      objeto.setNome(nome);
      objeto.setCpf(cpf);
      objeto.setTelefone(telefone);
      objeto.setData_nascimento(dataNascimento);
    }
    
    public ArrayList<Cliente> exibirListaClientes(){
        return this.listaClientes.getLista();
    }

    public boolean removerCliente(String idCliente){
        return this.listaClientes.remover(idCliente);
    }

    @Override
    public String toString() {
        return "GestaoClientes{" + "listaClientes=" + listaClientes + '}';
    }
}

