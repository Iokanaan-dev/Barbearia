/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

//import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * Essa classe cria uma listaModelo de Clientes e a gerencia.
 * @author italo
 */
public class GestaoClientes extends Gestao<Cliente> {   
   
    private static GestaoClientes instancia;
    private Barbearia_date dados;
    
    private GestaoClientes(Barbearia_date dados) {
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
        
        verificarCpfExiste(cpf);
        
        Cliente novoCliente = construirCliente(nome, cpf, telefone, dataNascimento, email);
        super.adicionar(novoCliente);
    }
    
    public void cadastrar(Cliente cliente) throws Exception{
        verificarCpfExiste(cliente.getCpf());
        super.adicionar(cliente);
    }
    
    private void verificarCpfExiste(String cpf) throws Exception{
        if (this.buscarPorCpfInterno(cpf) != null) {
            // Se NÃO for nulo, significa que o cliente já existe.
            throw new Exception("Já existe um cliente cadastrado com o CPF: " + cpf);
        }
    }
    
    public Cliente construirCliente(String nome, String cpf, String telefone, LocalDate dataNascimento, String email){
        return new Cliente(nome, cpf, telefone, dataNascimento, email);
    }
    
    
    public Cliente buscarCPF(String cpf) {
        verificarCampoNulo(cpf);
        
        for(Cliente c : this.listaModelo) {
            if (c.getCpf().equals(cpf)) 
                return c;
        }
        throw new NoSuchElementException("Nenhum item encontrado com o CPF: " + cpf);
    }

    private Cliente buscarPorCpfInterno(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null; 
        }
        
       
        for (Cliente c : this.listaModelo) { 
            if (c.getCpf().equals(cpf)) {
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
    public void editar(Cliente cliente, String nome, String cpf, String telefone, LocalDate dataNascimento, String email) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }

        if (nome != null) {
            cliente.setNome(nome);
        }
        if (cpf != null) {
            cliente.setCpf(cpf);
        }
        if (telefone != null) {
            cliente.setTelefone(telefone);
        }
        if (dataNascimento != null) {
            cliente.setDataNascimento(dataNascimento);
        }
        if (email != null) {
            cliente.setEmail(email);
        }
    }
}

