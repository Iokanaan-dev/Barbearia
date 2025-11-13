/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import java.util.ArrayList;
import com.mycompany.Utilidades.Login;
import com.mycompany.Utilidades.TipoUsuario;
import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Atendente;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;

/**
 *
 * @author italo
 */
public class GestaoUsuarios extends Gestao<Usuario> implements Login {
 //   private final ArrayList<Usuario> listaUsuarios = new ArrayList();
    
    private static GestaoUsuarios instancia;
    private Barbearia_date dados;

private GestaoUsuarios(Barbearia_date dados) {
    this.dados = dados;
    this.listaModelo = new ArrayList<>();
    if (dados.getListaGerentes() != null)
        this.listaModelo.addAll(dados.getListaGerentes().stream().filter(u -> u != null).toList());

    if (dados.getListaBarbeiros() != null)
        this.listaModelo.addAll(dados.getListaBarbeiros().stream().filter(u -> u != null).toList());

    if (dados.getListaAtendentes() != null)
        this.listaModelo.addAll(dados.getListaAtendentes().stream().filter(u -> u != null).toList());
}


    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoUsuarios(dados);
        }
    }

    public static GestaoUsuarios getInstancia() {
        return instancia;
    }
    
    public void cadastrar(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, TipoUsuario funcao) {
        funcaoValida(funcao);
        usernameSendoUsado(username);
    
        Usuario novoUsuario = construirUsuario(username, senha, nome, cpf, telefone, dataNascimento, funcao);
        super.adicionar(novoUsuario);
        
        GestaoPonto.getInstancia().adicionarATabelaPonto(novoUsuario.getId());
    }
    
    public void cadastrar(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, TipoUsuario funcao, String pin) {
        funcaoValida(funcao);
        usernameSendoUsado(username);
        
        Usuario novoUsuario = construirUsuario(username, senha, nome, cpf, telefone, dataNascimento, funcao , pin);
        super.adicionar(novoUsuario);
        
        GestaoPonto.getInstancia().adicionarATabelaPonto(novoUsuario.getId());
    }    
    
    public void cadastrar(Usuario usuario){
        usernameSendoUsado(usuario.getUsername());
        super.adicionar(usuario);
    }

    /**
    * Cria o objeto Usuario de acordo com a função.
    */
    private Usuario construirUsuario(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, TipoUsuario funcao) {
        Usuario novoUsuario;
        switch (funcao) {
            case BARBEIRO:
                novoUsuario = new Barbeiro(username, senha, nome, cpf, telefone, dataNascimento);
                return novoUsuario;

            case ATENDENTE:
                novoUsuario = new Atendente(username, senha, nome, cpf, telefone, dataNascimento);
                return novoUsuario;
                
            default:
            throw new IllegalArgumentException("Funçao invalida");
        }        
    }

    private Usuario construirUsuario(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, TipoUsuario funcao, String pin) {
        return new Gerente(username, senha, nome, cpf, telefone, dataNascimento, pin);      
    }    

    /**
     * Verifica se o usuário já existe.
     * @param username
     */
    public void usernameSendoUsado(String username) {
        if(buscarUsername(username) != null)
            throw new IllegalArgumentException("Username ja existe.");
    }

    /**
     * Verifica se a função informada é válida.
     */
    private void funcaoValida(TipoUsuario funcao) {
        if(funcao == null)
            throw new IllegalArgumentException("Função nao pode ser nula");
    }   
    
    /**
     * Permite ao gerente editar as informacoes de um usuario
     * @param nameuserGerente
     * @param senhaGerente
     * @param id
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public void editar(String nameuserGerente, String senhaGerente, String id, String nome, String cpf, String telefone, LocalDate dataNascimento){
        
        if(validarLogin(nameuserGerente, senhaGerente)){
            Usuario gerente = buscarUsername(nameuserGerente);
            
            if(gerente instanceof Gerente){
        /*
                // POR HORA ESTAMOS SEM VALIDACAO POR PIN, APENAS POR USERNAME, SENHA E O USUARIO DEVE SER ADM
                Scanner entrada = new Scanner(System.in);
                System.out.println("Digite o pin: ");
                String pin = entrada.nextLine();
        
            if(!gerente.verficarPinADM(pin))
                throw new IllegalArgumentException("PIN incorreto!"); 
           */
            Usuario usuario = this.buscarPorId(id);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setTelefone(telefone);
            usuario.setDataNascimento(dataNascimento);
            }
        }
        else
            throw new IllegalArgumentException("Usuario sem acesso suficiente!");  
    }    
    
    /*
     *
     * @param userName
     * @return
     */
    
    public Usuario buscarUsername(String userName){ 
        for(Usuario usuario : listaModelo){
            if (usuario == null) continue;
            if (usuario.getUsername().equalsIgnoreCase(userName))
                return usuario;
        }
        
        return null;
    }
    
    /**
     *
     * @param objeto
     * @param username
     * @param senha
     * @param usernameNovo
     * @param senhaNova
     */
    public void editarUsuarioLogin(String idUsuario, String username, String senha, String usernameNovo ,String senhaNova){

        usernameSendoUsado(usernameNovo);
        
        Usuario usuario = buscarUsername(username);
        usuario.mudarUsername(username, usernameNovo);
        usuario.mudarSenha(senha, senhaNova);
    }  
    
    /**
     *
     * @return
     */
    private ArrayList<Usuario> getListaUsuarios(int opcao){ // private pois so sera usado aqui dentro da classe
  
        ArrayList<Usuario> listaUsuariosSelecionados = new ArrayList();
        
        switch (opcao) {
            case 0:
                for (Usuario usuario : listaModelo) {
                    if(usuario instanceof Gerente)
                        listaUsuariosSelecionados.add(usuario);
                }   break;
            case 1:
                for (Usuario usuario : listaModelo) {
                    if(usuario instanceof Barbeiro)
                        listaUsuariosSelecionados.add(usuario);
                }   break;
            case 2:
                for (Usuario usuario : listaModelo) {
                    if(usuario instanceof Atendente)
                        listaUsuariosSelecionados.add(usuario);
                }   break;
            default:
                break;
        }
        
        return listaUsuariosSelecionados;
    }
    
    /**
     * Retorna a lista de usuarios que sao gerentes
     * @return ArrayList
     */
    public ArrayList<Usuario> getListaGerentes(){
        return getListaUsuarios(0);
    }

    /**
     * Retorna a lista de usuarios que sao barbeiros
     * @return ArrayList
     */
    public ArrayList<Usuario> getListaBarbeiros(){
        return getListaUsuarios(1);
    }

    /**
     * Retorna a lista de usuarios que sao atendentes
     * @return ArrayList
     */
    public ArrayList<Usuario> getListaAtendentes(){
        return getListaUsuarios(2);
    }  
    
    /**
     * Imprime uma lista de usuarios de uma certa funçao
     * @param funcao
     */
    public void printListaFuncao(TipoUsuario funcao){
        
        funcaoValida(funcao);
            
        ArrayList<Usuario> usuarios = null;
        switch(funcao){
            case GERENTE:
                usuarios = getListaGerentes();
                break;
            case BARBEIRO:
                usuarios = getListaBarbeiros();
                break;   
            case ATENDENTE:
                usuarios = getListaAtendentes();
                break;     
        }
        
        super.printLista(usuarios); 
    }

    /**
     * Implementa a interface validarLogin
     * @param username
     * @param senha
     * @return
     */
    @Override
    public boolean validarLogin(String username, String senha){
        Usuario usuario = buscarUsername(username);
        
        return (usuario.verificarUsername(username) && usuario.verificarSenha(senha));
    }   
}