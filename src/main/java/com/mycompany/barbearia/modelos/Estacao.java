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
    
    public Estacao(String nome, String descricao, TipoEstacao tipo){
        super(nome);
        this.descricao = descricao;
        this.tipo = tipo;
    }
    
    public Estacao(){
        super();
    }
    
    @Override
    public String gerarId(){
        return "ES-" + UUID.randomUUID().toString().substring(0, 10);

    }

    public TipoEstacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstacao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDiscricao() {
        return descricao;
    }

    public void setDiscricao(String discricao) {
        this.descricao = discricao;
    }

    @Override
    public String toString() {
        return String.format("%nEstacao %s%n%s", getId(), super.toString());
    }
}
