/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import java.util.ArrayList;
import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Atendente;
import Listas.ListaGenerica;
import java.time.LocalDate;
import java.util.Scanner;


/**
 *
 * @author italo
 */
public class GestaoUsuarios {
    private final ListaGenerica<Usuario> listaUsuario = new ListaGenerica();
    
    /**
     *
     * @param novoUsuario
     */
    public void cadastrarUsuario(Usuario novoUsuario){
        if (buscarUsuario(novoUsuario.getUsername()) != null){
            System.out.println("Usuario existente!");
        }
        this.listaUsuario.adicionar(novoUsuario);
    }
    
    /*
     *
     * @param userName
     * @return
     */
    public Usuario buscarUsuario(String userName){
        for(Usuario user : listaUsuario.getLista()){
            if (user.getUsername().equalsIgnoreCase(userName)){
                return user;
            }
        }
        return null;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public boolean removerUser(String id){
       return listaUsuario.remover(id);
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public ArrayList<Usuario> buscaPorNome(String nome){
        return this.listaUsuario.buscaPorNome(nome);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Usuario buscaPorId(String id){
        return this.listaUsuario.buscaPorId(id);
    }
    
    /**
     *
     * @param objeto
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     */
    public void editarUsuarioAtributos(Usuario objeto, String nome, String cpf, String telefone, LocalDate dataNascimento){
      if(objeto instanceof Gerente gerente){
            Scanner entrada = new Scanner(System.in);
            System.out.println("Digite o pin: ");
            String pin = entrada.nextLine();
        
        if(!gerente.verficarPinADM(pin)) {
           throw new IllegalArgumentException("PIN incorreto!"); 
      }
      objeto.setNome(nome);
      objeto.setCpf(cpf);
      objeto.setTelefone(telefone);
      objeto.setDataNascimento(dataNascimento);
        }
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
    public ArrayList<Usuario> getListaUsuarios(){
        return this.listaUsuario.getLista();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Barbeiro> exibirListaBarbeiro(){
        ArrayList<Barbeiro> barbeiros = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getLista()) {
            if(usuarios instanceof Barbeiro barbeiro) {
                barbeiros.add(barbeiro);
            }
        }
        return barbeiros;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Gerente> exibirListGerente(){
        ArrayList<Gerente> gerentes = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getLista()){
            if (usuarios instanceof Gerente gerente) {
                gerentes.add(gerente);
            }
        }
        return gerentes;
    }
    
    /**
     *
     * @return
     */
    public ArrayList exibirListaAtendentes(){
        ArrayList<Atendente> atendentes = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getLista()){
            if (usuarios instanceof Atendente atendente){
                atendentes.add(atendente);
            }
        }
        return atendentes;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "GestaoUsuarios{" + "listaUsuario=" + listaUsuario + '}';
    }
    
} //talvez criar um metodo para filtrar o tipo 