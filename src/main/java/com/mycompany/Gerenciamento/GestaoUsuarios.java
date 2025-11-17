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
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Gerencia os usuarios
 * @author italos
 */
public class GestaoUsuarios extends Gestao<Usuario> implements Login {
 //   private final ArrayList<Usuario> listaUsuarios = new ArrayList();
    
    private static GestaoUsuarios instancia;
    private Barbearia_date dados;

    private GestaoUsuarios(Barbearia_date dados) {
        this.dados = dados;
        this.listaModelo = new ArrayList<>();

        // Adiciona usuários existentes do Barbearia_date
        if (dados.getListaGerentes() != null)
            this.listaModelo.addAll(dados.getListaGerentes());

        if (dados.getListaBarbeiros() != null)
            this.listaModelo.addAll(dados.getListaBarbeiros());

        if (dados.getListaAtendentes() != null)
            this.listaModelo.addAll(dados.getListaAtendentes());
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
        //usernameSendoUsado(username);
    
        Usuario novoUsuario = construirUsuario(username, senha, nome, cpf, telefone, dataNascimento, funcao);
        this.adicionar(novoUsuario);
        
        GestaoPonto.getInstancia().adicionarATabelaPonto(novoUsuario.getId());
    }
    
    public void cadastrar(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, TipoUsuario funcao, String pin) {
        funcaoValida(funcao);
        usernameSendoUsado(username);
        
        Usuario novoUsuario = construirUsuario(username, senha, nome, cpf, telefone, dataNascimento, funcao , pin);
        this.adicionar(novoUsuario);
        
        GestaoPonto.getInstancia().adicionarATabelaPonto(novoUsuario.getId());
    }    
    
    public void cadastrar(Usuario usuario){
        usernameSendoUsado(usuario.getUsername());
        this.adicionar(usuario);
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
     * @param nameuser
     * @param senha
     * @param id
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public void editar(String nameuser, String senha, String id, String nome, String cpf, String telefone, LocalDate dataNascimento) {
        if (!validarLogin(nameuser, senha)) {
            throw new IllegalArgumentException("ACesso negado!");
        }

        Usuario usuario = this.buscarPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        // Atualiza apenas os campos que foram fornecidos
        if (nome != null && !nome.isBlank()) {
            usuario.setNome(nome);
        }
        if (cpf != null && !cpf.isBlank()) {
            usuario.setCpf(cpf);
        }
        if (telefone != null && !telefone.isBlank()) {
            usuario.setTelefone(telefone);
        }
        if (dataNascimento != null) {
            usuario.setDataNascimento(dataNascimento);
        }
    }

    /*
     *
     * @param userName
     * @return
     */
    
    public Usuario buscarUsername(String userName){ 
        for(Usuario usuario : listaModelo){
            if (usuario == null || usuario.getUsername() == null) continue; // proteger contra null
            if (usuario.getUsername().equalsIgnoreCase(userName))
                return usuario;
        }
        return null;
    }
    
    /**
     *
     * @param usuario
     * @param cpf
     * @param usernameNovo
     * @param senhaNova
     */
    public void editarUsuarioLogin(Usuario usuario, String cpf, String usernameNovo, String senhaNova) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo!");
        }

        // Verifica se o CPF fornecido confere com o do usuário
        if (!usuario.getCpf().equals(cpf)) {
            throw new IllegalArgumentException("CPF incorreto! Acesso negado.");
        }

        // Atualiza username se fornecido e diferente do atual
        if (usernameNovo != null && !usernameNovo.isBlank() && !usernameNovo.equals(usuario.getUsername())) {
            usernameSendoUsado(usernameNovo); 
            usuario.mudarUsername(usuario.getUsername(), usernameNovo);
        }

        // Atualiza senha se fornecida e diferente da atual
        if (senhaNova != null && !senhaNova.isBlank() && !senhaNova.equals(usuario.getSenha())) {
            usuario.mudarSenha(usuario.getSenha(), senhaNova);
        }
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
    public boolean validarLogin(String username, String senha) {
        Usuario usuario = buscarUsername(username);
        if (usuario == null) return false;
        return usuario.verificarSenha(senha);
    }
 
    
    // Método para remover usuário, se necessário
    public void remover(Usuario u) {
        if (u == null) return;

        this.listaModelo.remove(u);

        if (u instanceof Gerente && dados.getListaGerentes() != null)
            dados.getListaGerentes().remove(u);
        else if (u instanceof Barbeiro && dados.getListaBarbeiros() != null)
            dados.getListaBarbeiros().remove(u);
        else if (u instanceof Atendente && dados.getListaAtendentes() != null)
            dados.getListaAtendentes().remove(u);
    }
    
    private void validarPIM(String pin, Usuario user) throws Exception{ //tenho que usar depois nas verificações de usuario
        
        Gerente gerente = (Gerente) user;
        if (!gerente.verficarPinADM(pin)) {
            throw new Exception("PIN incorreto!");
        }
    }
    
    private void verificarInstancia(String pin, Usuario user) throws Exception{
        if (!(user instanceof Gerente)) {
            throw new Exception("Somente gerentes podem gerar balanço mensal");
        }
    }
    
        public Usuario buscarCPF(String cpf) {
        verificarCampoNulo(cpf);
        
        for(Usuario u : this.listaModelo) {
            if (u.getCpf().equals(cpf)) 
                return u;
        }
        throw new NoSuchElementException("Nenhum item encontrado com o CPF: " + cpf);
    }
        
        

    private Usuario buscarPorCpfInterno(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null; 
        }
        
       
        for (Usuario u : this.listaModelo) { 
            if (u.getCpf().equals(cpf)) {
                return u; 
            }
        }
        return null; 
    }
    
     // Sobrescreve método de adicionar usuário
    @Override
    public void adicionar(Usuario u) {
        if (u == null) return;

        this.listaModelo.add(u);

        // Sincroniza com Barbearia_date para persistência
        if (u instanceof Gerente) {
            if (dados.getListaGerentes() == null) dados.setListaGerentes(new ArrayList<>());
            dados.getListaGerentes().add((Gerente) u);
        } else if (u instanceof Barbeiro) {
            if (dados.getListaBarbeiros() == null) dados.setListaBarbeiros(new ArrayList<>());
            dados.getListaBarbeiros().add((Barbeiro) u);
        } else if (u instanceof Atendente) {
            if (dados.getListaAtendentes() == null) dados.setListaAtendentes(new ArrayList<>());
            dados.getListaAtendentes().add((Atendente) u);
        }
    }
    
}