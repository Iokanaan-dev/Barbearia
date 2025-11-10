/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

//import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Essa classe cria uma listaModelo de Clientes e a gerencia.
 * @author italo
 */
public class GestaoClientes extends Gestao<Cliente> {   
   
    private static GestaoClientes instancia;
    private Barbearia_date dados;
    
    GestaoClientes(Barbearia_date dados) {
        this.dados = dados;
        this.listaModelo = dados.getListaClientes();
    }    
    
    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoClientes(dados);
        }
    }    
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da listaModelo dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoClientes getInstancia() {
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
    public void cadastrar(String nome, String cpf, String telefone, LocalDate dataNascimento, String email) throws Exception{
        
        try {
            Cliente clienteExistente = this.buscarCPF(cpf);
            
           
            // um cliente (clienteExistente != null), o que é um erro.
            if (clienteExistente != null) {
                throw new Exception("Erro de Duplicidade: Já existe um cliente cadastrado com o CPF: " + cpf);
            }
            
        } catch (NoSuchElementException e) {
        }
        
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

