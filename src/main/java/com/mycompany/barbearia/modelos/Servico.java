/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import Utilidades.Prototype;
import Utilidades.TipoEstacao;
import Gerenciamento.GestaoServico;

/**
 * A duração é medida em slots que duram 10 minutos. EX: 2 slots 20 minutos
 * @author intalo
 */
public class Servico extends Modelo implements Prototype<Servico>{
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
    public Servico(String nome, double preco, String descricao, int tempo, TipoEstacao tipoEstacaoRequerida){
        super(nome);
        
        validarPreco(preco);
        validarDescricao(descricao);        
        validarTempo(tempo);
        validarTipoEstacaoRequerido(tipoEstacaoRequerida);
        
        this.preco = preco;
        this.descricao = descricao;
        this.tempoSlots = tempo;
        this.tipoEstacaoRequerido = tipoEstacaoRequerida;
    }
    
    /**
     * Construtor que permite a descricao ser opcional
     * @param nome
     * @param preco
     * @param tempo
     * @param tipoEstacaoRequerida
     */
    public Servico(String nome, double preco, int tempo, TipoEstacao tipoEstacaoRequerida){
        this(nome, preco, "---", tempo, tipoEstacaoRequerida);
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

    public final void setTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
        validarTipoEstacaoRequerido(tipoEstacaoRequerido);
        this.tipoEstacaoRequerido = tipoEstacaoRequerido;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    public int getTempoEmMinutos() {
        return tempoSlots * 10;
    }
   
    public int getTempoEmSlots() {
        return tempoSlots;
    }    
    
    public void setTempoEmMinutos(int temp) {
        validarTempo(temp);
        this.tempoSlots = temp;
    }
   
    /**
     *
     * @param preco
     */
    public void setPreco(double preco){
        validarPreco(preco);
        this.preco = preco;
    }

    private void validarPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
    }
    
    private void validarDescricao(String descricao) {
        if (descricao == null) {
            throw new IllegalArgumentException("A descricao não pode ser nula");
        }
    }    

    private void validarTempo(int temp) {
        if (temp <= 0) {
            throw new IllegalArgumentException("O tempo deve ser maior que 0");
        }
    }

    private void validarTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
        if (!(tipoEstacaoRequerido == TipoEstacao.CORRIQUEIRA || tipoEstacaoRequerido == TipoEstacao.LAVAGEM)) {
            throw new IllegalArgumentException("Estacao deve pertencer aos tipos pre-definidos.");
        }
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
        return String.format("%sPreco: %s | Tempo: %s | %nDescricao: %s", super.toString(), getPreco(), getTempoEmMinutos(), getDescricao());
    }

    @Override
    public Servico clone() {
        return new Servico(this);
    }

public Servico(Servico servicoQueSeraClonado) {
    validarServicoClonado(servicoQueSeraClonado);
    this.setNome(servicoQueSeraClonado.getNome()); 
    this.preco = servicoQueSeraClonado.getPreco();
    this.descricao = servicoQueSeraClonado.getDescricao();
    this.tempoSlots = servicoQueSeraClonado.getTempoEmSlots();
    this.tipoEstacaoRequerido = servicoQueSeraClonado.getTipoEstacaoRequerido();
}

    
    public void editarClone(String nome, double preco, int tempo){
        super.validarNome(nome);
        validarPreco(preco);
        validarTempo(tempo);
        
        this.setNome(nome);
        this.setPreco(preco);
        this.setTempoEmMinutos(tempo);
    }
    
    private void validarServicoClonado(Servico servicoQueSeraClonado){
        if(servicoQueSeraClonado == null)
        throw new IllegalArgumentException("Nao se pode clonar um servico nulo.");
    }
}