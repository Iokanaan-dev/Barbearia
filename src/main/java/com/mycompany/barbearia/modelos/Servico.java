/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.mycompany.Utilidades.TipoEstacao;
import java.util.UUID;

/**
 * A duração é medida em slots que duram 30 minutos. EX: 2 slots 1 hora
 * @author intalo
 */
public class Servico extends Modelo{
    private double preco;
    private String descricao;
    private  int tempoSlots;
    //private static int contador = 0;
    private TipoEstacao tipoEstacaoRequerido;

    /**
     *
     * @param nome
     * @param preco
     */
    public Servico(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido){
        super(nome);
        
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        
        if(temp <= 0){
            throw new IllegalArgumentException("O tempo deve ser maior que 0");
        }
        
        this.preco = preco;
        this.descricao = descricao;
        this.tempoSlots = temp;
        this.tipoEstacaoRequerido = tipoRequerido;
    }

    /**
     *
     * @return
     */
    public double getPreco() {
        return preco;
    }

    public TipoEstacao getTipoEstacaoRequerido() {
        return tipoEstacaoRequerido;
    }

    public void setTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
        this.tipoEstacaoRequerido = tipoEstacaoRequerido;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTempoEmMinutos10() {
        return tempoSlots * 10;
    }
    
    public void setTempoEmMinutos(int temp) {
        if(temp <= 0){
            throw new IllegalArgumentException("O tempo definido é invalido!");
        }        
        this.tempoSlots = temp;
    }
   
    /**
     *
     * @param preco
     */
    public void setPreco(double preco){
        if(preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
        this.preco = preco;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String gerarId() {
        return "SE-" + UUID.randomUUID().toString().substring(0, 10);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%sPreco: %s", super.toString(),preco);
    }
}