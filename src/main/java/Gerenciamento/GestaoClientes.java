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
    
    // metodo que coleta os dados do cliente para o construtor e passa para adicionar cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
        Cliente novoCliente = new Cliente(nome, cpf, telefone, dataNascimento, email);
        this.listaClientes.adicionar(novoCliente);
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public ArrayList<Cliente> buscarNome(String nome){
      ArrayList<Cliente> clientesSelecionados;
      
      clientesSelecionados = this.listaClientes.buscaPorNome(nome);
      
      if (clientesSelecionados.isEmpty()) // se a lista retornada eh vazia o imprime uma mensagem de avisoé
              System.out.println("Nenhum cliente encontrado.");
      return clientesSelecionados;
    }
    
    /**
     *
     * @param ID
     * @return
     */
    public Cliente buscarID(String ID){
       Cliente clienteSelecionado;
       clienteSelecionado = this.listaClientes.buscaPorId(ID);
       
       if(clienteSelecionado == null)
              System.out.println("Nenhum cliente encontrado.");
       return clienteSelecionado;
    }
    
    /**
     *
     * @param cliente
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param email
     */
    public void editarCliente(Cliente cliente, String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
      // poder ser interessante validar isso antes de usar os metodos set
        
      cliente.setNome(nome);
      cliente.setCpf(cpf);
      cliente.setTelefone(telefone);
      cliente.setDataNascimento(dataNascimento);
      cliente.setEmail(email);
      
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes.getLista();
    }

    /**
     *
     * @param idCliente
     */
    public void removerCliente(String idCliente){
        // armazena o retorno do metodo remover, remover() retorna true apenas se um objeto foi encontrado e removido
        boolean removeu = this.listaClientes.remover(idCliente);
  
        if(removeu)
            System.out.println("Remoção bem-sucedida.");
        else
            System.out.println("Não foi possivel remover");
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "GestaoClientes{" + "listaClientes=" + listaClientes + '}';
    }
}

