/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.mycompany.Utilidades.TipoEstacao;
import java.util.UUID;

/**
 *
 * @author italo
 */
public class Estacao extends Modelo{
    private String descricao;

    private TipoEstacao tipo;
    
    /**
     * Construtor completo de estacao
     * @param nome
     * @param descricao
     * @param tipo
     */
    public Estacao(String nome, String descricao, TipoEstacao tipo){
        super(nome);
        this.descricao = descricao;
        this.tipo = tipo;
    }
    
    /**
     * Construtpr sem parametros
     */
    public Estacao(){

    }
    
    /**
     * Gera o ID
     * @return
     */
    @Override
    public String gerarId(){
        return "ES-" + UUID.randomUUID().toString().substring(0, 10);

    }

    /**
     * Obtem o tipo da estacao
     * @return
     */
    public TipoEstacao getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da estacao
     * @param tipo
     */
    public void setTipo(TipoEstacao tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtem a descricao
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descricao
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Obtem a descricao
     * @return
     */
    public String getDiscricao() {
        return descricao;
    }

    /**
     * Define a descricao
     * @param discricao
     */
    public void setDiscricao(String discricao) {
        this.descricao = discricao;
    }

    /**
     * Obtem a representacao como string de uma estacao
     * @return
     */
    @Override
    public String toString() {
        return String.format("%nEstacao %s%n%s", getId(), super.toString());
    }
}
