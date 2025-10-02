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

/**
 *
 * @author italo
 */
public class GestaoUsuarios {
    private ListaGenerica<Usuario> listaUsuario = new ListaGenerica();
    
    
    public void cadastrarUsuario(Usuario novoUsuario){
        if (buscarUsuario(novoUsuario.getUsername()) != null){
            throw new IllegalArgumentException("usuario existente!");
        }
        this.listaUsuario.adicionar(novoUsuario);
    }
    
    public Usuario buscarUsuario(String userName){
        for(Usuario user : listaUsuario.getItens()){
            if (user.getUsername().equalsIgnoreCase(userName)){
                return user;
            }
        }
        return null;
    }
    
    public boolean removerUser(String id){
       return listaUsuario.remover(id);
    }
    
    public ArrayList<Usuario> buscaPorNome(String nome){
        return this.listaUsuario.buscaPorNome(nome);
    }
    
    public Usuario buscaPorId(String id){
        return this.listaUsuario.buscaPorId(id);
    }
    
    public ArrayList<Usuario> exibirListaUsuarios(){
        return this.listaUsuario.getItens();
    }
    
    public ArrayList<Barbeiro> exibirListaBarbeiro(){
        ArrayList<Barbeiro> barbeiros = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getItens()) {
            if(usuarios instanceof Barbeiro) {
                barbeiros.add((Barbeiro) usuarios);
            }
        }
        return barbeiros;
    }
    
    public ArrayList<Gerente> exibirListGerente(){
        ArrayList<Gerente> gerentes = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getItens()){
            if (usuarios instanceof Gerente) {
                gerentes.add((Gerente) usuarios);
            }
        }
        return gerentes;
    }
    
    public ArrayList exibirListaAtendentes(){
        ArrayList<Atendente> atendentes = new ArrayList();
        for (Usuario usuarios : this.listaUsuario.getItens()){
            if (usuarios instanceof Atendente){
                atendentes.add((Atendente) usuarios);
            }
        }
        return atendentes;
    }
} //talvez criar um metodo para filtrar o tipo 