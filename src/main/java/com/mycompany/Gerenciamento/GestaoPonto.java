/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Usuario;
import com.mycompany.barbearia.modelos.ParBatida;
import com.mycompany.barbearia.modelos.TabelaPonto;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 *
 * @author intalo
 */
public class GestaoPonto extends Gestao<Usuario>{
    
    private TabelaPonto tabelaPontos; // A chave é o ID do Usuario (String), e a quantidade é (Double).
    //ArrayList listaUsuarios = GestaoUsuarios.getInstancia().getLista();
    //ArrayList<ParBatida> listaParBatida = new ArrayList<>(); // ACHO QUE NAO VOU PRECISAR DISSO IREI VER DEPOIS
    
     private static GestaoPonto instancia;
     private Barbearia_date dados;
     
     
    private GestaoPonto(Barbearia_date dados) {
        this.dados = dados;
        this.tabelaPontos = dados.tabelaPonto;
        this.listaModelo = new ArrayList<>();
        this.listaModelo.addAll(GestaoUsuarios.getInstancia().getLista());
    }

    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoPonto(dados);
        }
    }

    public static GestaoPonto getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestaoPonto ainda não foi inicializada");
        }
        return instancia;
    } 
    
    /**
     * Usa o id do usuario e o tipo da batida para bater o ponto
     * @param idUsuario
     * @param tipoBatida
     */
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

    /**
     * Metodo que retorna o horario atual do sistema
     * @return
     */
    private LocalDateTime pegarHorarioAtual(){ // private pois so vai ser usado aqui dentro da classe
        return LocalDateTime.now();
    }
    
    private ParBatida construirParBatida(){ // private pois so vai ser usado aqui dentro da classe
            ParBatida parBatida = new ParBatida(pegarHorarioAtual()); //ja constroi e adiciona a entrada
            return parBatida;
    }
    
    /**
     * Metodo que registra uma batida de ponto do tipo "entrada"
     * @param idUsuario
     * @param parBatida
     */
    public void registrarEntrada(String idUsuario, ParBatida parBatida){ // esse metodo serve para fazer a batida de Entrada
        if(!existeNaTabelaPonto(idUsuario))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        
        this.tabelaPontos.getListaParBatida(idUsuario).add(parBatida); // acessa a lista de batidas de ponto do usuario e adiciona a batida passada como argumento
    }    
    
    /**
     * Metodo que registra uma batida de ponto do tipo "saida"
     * @param idUsuario
     */
    public void registrarSaida(String idUsuario){ // esse metodo serve para fazer a batida de saida
        if(!existeNaTabelaPonto(idUsuario))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        
        this.tabelaPontos.getListaParBatida(idUsuario).get(this.tabelaPontos.getListaParBatida(idUsuario).size()-1).setSaida(pegarHorarioAtual()); //Pega a lista de batidas do usuário, acessa o último `ParBatida` e atualiza seu campo de saída com o horário atual.

        
        
    }     
    
    /**
     * Verifica se um usuario existe no sistema
     * @param idUsuario
     * @return
     */
    public boolean existeNoSistema(String idUsuario){
        return (GestaoUsuarios.getInstancia().buscarPorId(idUsuario) != null);      
    } 
    
    /**
     * Verifica se o usuario ja foi adicionado na tabela de pontos
     * @param id
     * @return
     */
    public boolean existeNaTabelaPonto(String id){
        return tabelaPontos.contemUsuario(id);
    }
        
    /**
     * adiciona um par (usuario, lista de batidas) a tabela de pontos
     * @param idUsuario
     */
    public void adicionarATabelaPonto(String idUsuario){
        if (!existeNoSistema(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");
        
        tabelaPontos.setListaDePontos(idUsuario);
    }
    
    /**
     * Imprime todos os pares de ponto (entrada, saida) de um usuario especificado pelo id
     * @param idUsuario
     */
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
    
    public TabelaPonto getTabela() {
        return this.tabelaPontos;
    }
    
    public void carregarTabela(TabelaPonto tabelaCarregada) {
        if (tabelaCarregada != null) {
            this.tabelaPontos = tabelaCarregada;
        }
    }
    
    /**
     * Imprime as horas trabalhadas por um funcionado especificado pelo id 
     * @param idUsuario
     */
    public void printHorasUsuario(String idUsuario){
        System.out.printf("Usuario: %s | Nome: %s | Horas: %f" , idUsuario, GestaoUsuarios.getInstancia().buscarPorId(idUsuario).getNome(), calcularHorasUsuario(idUsuario));
    }
    
}
