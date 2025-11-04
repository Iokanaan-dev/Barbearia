/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Cliente;
import java.time.LocalDate;

/**
 * Essa classe cria uma lista de Clientes e a gerencia.
 * @author italo
 */
public class GestaoClientes extends Gestao<Cliente> {   
    
    // cria uma lista de clientes que sera gerenciada (por enquanto static para ser acessivel por outras gestoes, mas quem sabe usemos singleton em breve)
    private final ArrayList<Cliente> listaClientes = new ArrayList();
    
    private static GestaoClientes instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoClientes getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoClientes();
        
        return instancia;
    }
  
    /**
     * Cadastra um novo cliente na lista de clientes
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param email
     */
    public void cadastrar(String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
        Cliente novoCliente = new Cliente(nome, cpf, telefone, dataNascimento, email);
        super.adicionar(listaClientes, novoCliente);
    }
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return ArrayList<>
     */
    public ArrayList<Cliente> getLista() {
        return listaClientes;
    }
    
    /**
     * Busca clientes na lista de clientes usando o nome
     * @param nome
     * @return ArrayList<>
    */
    public ArrayList<Cliente> buscarPorNome(String nome){
        return super.procurandoNome(listaClientes, nome);
    }
     
    /**
     * Imprime todos os clientes com um certo nome
     * @param nome
     */
    public void printPorNome(String nome){
        super.printLista(buscarPorNome(nome));
    }
    
    /**
     * Busca um cliente na lista de clientes usando o id
     * @param id
     * 
     * @return Cliente
     */
    public Cliente buscarPorId(String id){
        return super.procurandoID(listaClientes, id);
    }
    
    /**
     * Imprime o clientes com um certo ID
     * @param id
     */
    public void printPorId(String id){
        super.printItem(buscarPorId(id));
    }
    public Cliente buscarCPF(String CPF) {
       
        
        for(Cliente c : this.listaClientes) {
            if (c.getCpf().equals(CPF)) {
                return c;
            }
        }
        return null;
    }    

    /**
     * Permite a edicao de informacoes do cliente
     * @param id
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param email
     */
    public void editar(String id, String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
      
        //verifcar id nulo
        
      Cliente cliente = this.buscarPorId(id);
        
      cliente.setNome(nome);
      cliente.setCpf(cpf);
      cliente.setTelefone(telefone);
      cliente.setDataNascimento(dataNascimento);
      cliente.setEmail(email);
      
    }

    /**
      Remove um cliente com base no ID informado
     * @param id
     */
    public void remover(String id){
        super.remover(listaClientes, id);
    }
    
    /**
     * Imprime a lista de clientes atual
     */
    public void printLista(){
        super.printLista(listaClientes);
    }
}

