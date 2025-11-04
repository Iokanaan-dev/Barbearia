/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import java.util.ArrayList;
import Utilidades.Login;
import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Atendente;
import java.time.LocalDate;
import java.util.Scanner;


/**
 *
 * @author italo
 */
public class GestaoUsuarios extends Gestao<Usuario> implements Login {
    private final ArrayList<Usuario> listaUsuarios = new ArrayList();
    
    private static GestaoUsuarios instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoUsuarios
     */
    public static GestaoUsuarios getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoUsuarios();
        
        return instancia;
    }
    
    /**
     * Cadastra um novo usuario nao gerente na lista de usuarios
     * @param username
     * @param senha
     * @param nome
     * @param funcao
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public void cadastrar(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, String funcao){
        // se ja existe um usuario com o username cadastrado retorna uma indicaçao de erro
        if (buscarPorUsername(username) != null)
            System.out.println("Usuario existente!");
        
        Usuario novoUsuario;

        if("Barbeiro".equals(funcao))
            novoUsuario = new Barbeiro(username, senha, nome, cpf, telefone, dataNascimento);
        
        else if("Atendente".equals(funcao))
            novoUsuario = new Atendente(username, senha, nome, cpf, telefone, dataNascimento);
        else
            novoUsuario = null; // SUBSTITUIR ISSO POR UM LANÇAMENTO DE EXCEÇAO
        
        super.adicionar(listaUsuarios, novoUsuario);
    }
    
    /**
     * Cadastra um novo usuario gerente na lista de usuarios
     * @param username
     * @param senha
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param funçao
     * @param pin
     */
    public void cadastrar(String username, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, String funçao, String pin){
        Usuario novoUsuario = new Gerente(username, senha, nome, cpf, telefone, dataNascimento, pin);
        super.adicionar(listaUsuarios, novoUsuario);        
    }
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return ArrayList<>
     */
    public ArrayList<Usuario> getLista() {
        return listaUsuarios;
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public ArrayList<Usuario> buscarPorNome(String nome){
        return super.procurandoNome(listaUsuarios, nome);
    } 
    
    /**
     * Imprime todos os usuarios com um certo nome
     * @param nome
     */
    public void printPorNome(String nome){
        super.printLista(buscarPorNome(nome));
    }     
    
    /**
     * Busca um usuario na lista de clientes usando o id
     * @param id
     * @return
     */
    public Usuario buscarPorId(String id){
        return super.procurandoID(listaUsuarios, id);
    } 
    
    /**
     * Imprime o clientes com um certo ID
     * @param id
     */
    public void printPorId(String id){
        super.printItem(buscarPorId(id));
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
        
        if(login(nameuserGerente, senhaGerente)){
            Usuario gerente = buscarPorUsername(nameuserGerente);
            
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
    
    /**
     *
     * @param id
     */
    public void remover(String id){
        super.remover(listaUsuarios, id);
    }  
    
    /**
     * Imprime a lista de usuarios atual
     */
    public void printLista(){
        super.printLista(listaUsuarios);
    }    
    
    /*
     *
     * @param userName
     * @return
     */
    private Usuario buscarPorUsername(String userName){ // private pois so sera usado aqui dentro da classe
        for(Usuario usuario : listaUsuarios){
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
    public void editarUsuarioLogin(Usuario objeto, String username, String senha, String usernameNovo ,String senhaNova){
      if(objeto instanceof Gerente gerente){
            Scanner entrada = new Scanner(System.in);
            System.out.println("Digite o pin: ");
            String pin = entrada.nextLine();
        
        if(!gerente.verficarPinADM(pin)) {
           throw new IllegalArgumentException("PIN incorreto!"); 
      }
      objeto.mudarUsername(username, usernameNovo);
      objeto.mudarSenha(senha, senhaNova);
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
                for (Usuario usuario : listaUsuarios) {
                    if(usuario instanceof Gerente)
                        listaUsuariosSelecionados.add(usuario);
                }   break;
            case 1:
                for (Usuario usuario : listaUsuarios) {
                    if(usuario instanceof Barbeiro)
                        listaUsuariosSelecionados.add(usuario);
                }   break;
            case 2:
                for (Usuario usuario : listaUsuarios) {
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
    public void printListaFuncao(String funcao){
        
        ArrayList<Usuario> usuarios = null;
        switch(funcao){
            case "Gerente":
                usuarios = getListaGerentes();
                break;
            case "Barbeiros":
                usuarios = getListaBarbeiros();
                break;   
            case "Atendente":
                usuarios = getListaAtendentes();
                break;     
        }
        
        super.printLista(usuarios);
        
    }

    /**
     * Implementa a interface login
     * @param username
     * @param senha
     * @return
     */
    @Override
    public boolean login(String username, String senha){
        Usuario usuario = buscarPorUsername(username);
        
        return (usuario.verificarUsername(username) && usuario.verificarSenha(senha));
    }   
}