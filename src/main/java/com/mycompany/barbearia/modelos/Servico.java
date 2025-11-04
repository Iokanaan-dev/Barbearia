/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

/**
 * A duração é medida em slots que duram 30 minutos. EX: 2 slots 1 hora
 * @author intalo
 */
public class Servico extends Modelo{
    private double preco;
    private String descricao;
    private  int tempoSlots;
    private static int contador = 0;
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
<<<<<<< HEAD
        this.tempoSlots = (int)(Math.ceil(temp/30.0)); // como pegamos o tempo em minutos do usuario devemos converte-lo para slots para uso no sistema
=======
        this.tempoSlots = temp;
        this.tipoEstacaoRequerido = tipoRequerido;
>>>>>>> feature/Gestao
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
        this.tempoSlots = (int)(Math.ceil(temp/30.0)); // como pegamos o tempo em minutos do usuario devemos converte-lo para slots para uso no sistema
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
        return "SE" + (++contador);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%nServiço %s%n%sPreço: %s%nDescriçao: %s%nTempo: %s", getId(), super.toString(), getPreco(), getDescricao(), getTempoEmMinutos());
    }
}