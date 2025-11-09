/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

//import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Cliente;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * Essa classe cria uma listaModelo de Clientes e a gerencia.
 * @author italo
 */
public class GestaoClientes extends Gestao<Cliente> {   
    
    private static GestaoClientes instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da listaModelo dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoClientes getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoClientes();
        
        return instancia;
    }
  
    /**
     * Cadastra um novo cliente na listaModelo de clientes
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param email
     */
    public void cadastrar(String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
        Cliente novoCliente = new Cliente(nome, cpf, telefone, dataNascimento, email);
        super.adicionar(novoCliente);
    }
    
    public Cliente buscarCPF(String cpf) {
        verificarCampoNulo(cpf);
        
        for(Cliente c : this.listaModelo) {
            if (c.getCpf().equals(cpf)) 
                return c;
        }
        throw new NoSuchElementException("Nenhum item encontrado com o CPF: " + cpf);
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
        
      Cliente cliente = buscarPorId(id);
        
      cliente.setNome(nome);
      cliente.setCpf(cpf);
      cliente.setTelefone(telefone);
      cliente.setDataNascimento(dataNascimento);
      cliente.setEmail(email);
      
    }
}

