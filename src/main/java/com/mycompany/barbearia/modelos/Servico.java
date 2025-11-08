/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import Utilidades.Prototype;
import Utilidades.TipoEstacao;
import Gerenciamento.GestaoServico;

/**
 * Representa um serviço da barbearia.
 * O tempo do serviço é definido em slots de 10 minutos.
 * Implementa Prototype para permitir clonagem do objeto.
 */
public class Servico extends Modelo implements Prototype<Servico>{
    private double preco;
    private String descricao;
    private  int tempoSlots;
    private static int contador = 0;
    private TipoEstacao tipoEstacaoRequerido;

    /**
     * Construtor principal do serviço.
     * @param nome nome do serviço
     * @param preco preço do serviço
     * @param descricao descrição do serviço
     * @param tempo tempo em slots (cada slot = 10 min)
     * @param tipoEstacaoRequerida tipo de estação necessária
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
     * Construtor alternativo onde a descrição é opcional.
     * @param nome nome do serviço
     * @param preco preço do serviço
     * @param tempo tempo em slots (cada slot = 10 min)
     * @param tipoEstacaoRequerida tipo de estação necessária
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

    /**
     *
     * @return
     */
    public TipoEstacao getTipoEstacaoRequerido() {
        return tipoEstacaoRequerido;
    }

    /**
     *
     * @param tipoEstacaoRequerido
     */
    public final void setTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
        validarTipoEstacaoRequerido(tipoEstacaoRequerido);
        this.tipoEstacaoRequerido = tipoEstacaoRequerido;
    }
    
    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public final void setDescricao(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    public int getTempoEmMinutos() {
        return tempoSlots * 10;
    }
   
    /**
     *
     * @return
     */
    public int getTempoEmSlots() {
        return tempoSlots;
    }    
    
    /**
     *
     * @param temp
     */
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

    /**
     * Valida o preco
     */    
    private void validarPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
        }
    }
    
    /**
     * Valida a descricao
     */    
    private void validarDescricao(String descricao) {
        if (descricao == null) {
            throw new IllegalArgumentException("A descricao não pode ser nula");
        }
    }    

    /**
     * Valida o tempo
     */    
    private void validarTempo(int temp) {
        if (temp <= 0) {
            throw new IllegalArgumentException("O tempo deve ser maior que 0");
        }
    }

    /**
     * Valida o o tipo de estacao
     */    
    private void validarTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
        if (tipoEstacaoRequerido == null) {
            throw new IllegalArgumentException("Estacao nao pode ser nula");
        }
    }    
    
    /**
     * ID gerado do serviço
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

    /**
     *
     * @return representação em String do serviço
     */
    @Override
    public Servico clone() {
        return new Servico(this);
    }

    /**
     * Construtor de clonagem.
     * @param servicoQueSeraClonado serviço base para clonagem
     */
    public Servico(Servico servicoQueSeraClonado) {
    validarServicoClonado(servicoQueSeraClonado);
    this.setNome(servicoQueSeraClonado.getNome()); 
    this.preco = servicoQueSeraClonado.getPreco();
    this.descricao = servicoQueSeraClonado.getDescricao();
    this.tempoSlots = servicoQueSeraClonado.getTempoEmSlots();
    this.tipoEstacaoRequerido = servicoQueSeraClonado.getTipoEstacaoRequerido();
}

    /**
     * Edita os dados de um serviço clonado.
     * @param nome novo nome
     * @param preco novo preço
     * @param tempo novo tempo em slots
     */
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