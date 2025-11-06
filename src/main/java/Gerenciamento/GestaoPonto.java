/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.barbearia.modelos.ParBatida;
import com.mycompany.barbearia.modelos.TabelaPonto;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author intalo
 */
public class GestaoPonto extends Gestao<Usuario>{
    
    private final TabelaPonto tabelaPontos = new TabelaPonto(); // A chave é o ID do Usuario (String), e a quantidade é (Double).
    ArrayList listaUsuarios = GestaoUsuarios.getInstancia().getLista();
    ArrayList<ParBatida> listaParBatida = new ArrayList<>(); // ACHO QUE NAO VOU PRECISAR DISSO IREI VER DEPOIS
    
     private static GestaoPonto instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoEstoque
     */
    public static GestaoPonto getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoPonto();
        
        return instancia;
    }  
    
    public void baterPonto(String idUsuario, String tipoBatida){
        
        if(!existeNoSistema(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");
        
        if(!tipoBatidaValido(tipoBatida))
            throw new IllegalArgumentException("Tipo de batida invalida não existe no sistema.");
        
        switch(tipoBatida)
        {
            case "Entrada":
                registrarEntrada(idUsuario, construirParBatida());
                return;
            case "Saida":
                registrarSaida(idUsuario);                
        }           
    }
    
    private boolean tipoBatidaValido(String tipoBatida) {
        return "Entrada".equals(tipoBatida) || "Saida".equals(tipoBatida);
    }

    public LocalDateTime pegarHorarioAtual(){
        return LocalDateTime.now();
    }
    
    private ParBatida construirParBatida(){ // private pois so vai ser usado aqui dentro da classe
            ParBatida parBatida = new ParBatida(pegarHorarioAtual()); //ja constroi e adiciona a entrada
            return parBatida;
    }
    
    public void registrarEntrada(String idUsuario, ParBatida parBatida){ // esse metodo serve para fazer a batida de Entrada
        if(!existeNaTabelaPonto(idUsuario))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        
        //ArrayList<ParBatida>listaPontoUsuarios = tabelaPontos.getTabelaPontos().get(idUsuario);
                
        //this.tabelaPontos.getTabelaPontos().get(idUsuario).add(parBatida);
        this.tabelaPontos.getListaParBatida(idUsuario).add(parBatida);
    }    
    
    public void registrarSaida(String idUsuario){ // esse metodo serve para fazer a batida de saida
        if(!existeNaTabelaPonto(idUsuario))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        
        //ArrayList<ParBatida>listaPontoUsuarios = tabelaPontos.getTabelaPontos().get(id);
        //listaPontoUsuarios.get(listaPontoUsuarios.size()-1).setSaida(pegarHorarioAtual());
        
        this.tabelaPontos.getListaParBatida(idUsuario).get(this.tabelaPontos.getListaParBatida(idUsuario).size()-1).setSaida(pegarHorarioAtual()); //Define o horário de **saída** do **último registro de ponto** do usuário, ou seja, pega a lista de batidas do usuário, acessa o último `ParBatida` e atualiza seu campo de saída com o horário atual.

        
        
    }     
    
    public boolean existeNoSistema(String idUsuario){
        return (GestaoUsuarios.getInstancia().buscarPorId(idUsuario) != null);      
    } 
    
    public boolean existeNaTabelaPonto(String id){
        return tabelaPontos.contemUsuario(id);
    }
        
    public void adicionarATabelaPonto(String idUsuario){ // adiciona o usuario a tabela de pontos e cria sua lista de pontos
        if (!existeNoSistema(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");
        
        tabelaPontos.setListaDePontos(idUsuario);
    }
    
    public void printPontosUsuario(String idUsuario){
        if (!existeNoSistema(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");        
        ArrayList<ParBatida> listaParBatidasUsuario = this.tabelaPontos.getListaParBatida(idUsuario);
        
        System.out.printf("Usuario: %s | Nome: %s%n", idUsuario, GestaoUsuarios.getInstancia().buscarPorId(idUsuario).getNome());
        for(ParBatida par: listaParBatidasUsuario)
            System.out.print(par.toString());
    }
    
    private double calcularHorasUsuario(String idUsuario){
        if (!existeNoSistema(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");
        
        ArrayList<ParBatida> listaParBatidasUsuario = this.tabelaPontos.getListaParBatida(idUsuario);
        double horasUsuario = 0.0;
        
        for(ParBatida par: listaParBatidasUsuario)
            horasUsuario += par.horasSaidaEmDouble() - par.horasEntradaEmDouble();
        
        return horasUsuario / 3600;
    }
    
    public void printHorasUsuario(String idUsuario){
        System.out.printf("Usuario: %s | Nome: %s | Horas: %f" , idUsuario, GestaoUsuarios.getInstancia().buscarPorId(idUsuario).getNome(), calcularHorasUsuario(idUsuario));
    }
    
}
