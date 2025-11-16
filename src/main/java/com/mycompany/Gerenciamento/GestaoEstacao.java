/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;
import com.mycompany.barbearia.modelos.Estacao;
import com.mycompany.Utilidades.TipoEstacao;


/**
 *
 * @author italo
 */
public class GestaoEstacao extends Gestao<Estacao>{
    private static final int NUM = 3;
    private final Estacao[] estacoes = new Estacao[NUM];
    
    private static GestaoEstacao instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoEstacao
     */
    public static GestaoEstacao getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoEstacao();
        
        return instancia;
    }
    
    /**
     * Construtor que inicializa o array de estacoes
     */
    public GestaoEstacao(){
        estacoes[0] = new Estacao("Lavar e sacar", "Estação destinada apenas para lavar e secar o cabelo de nossos clientes!", TipoEstacao.LAVAGEM);
        estacoes[1] = new Estacao("Corriqueira 1", "Estação destinada apenas para atividades corriqueiras da barbearia", TipoEstacao.CORRIQUEIRA);
        estacoes[2] = new Estacao("Corriqueira 2", "Estação destinada apenas para atividades corriqueiras da barbearia", TipoEstacao.CORRIQUEIRA);
    }
    
    /**
     * Obtem o Array de estacoes
     * @return
     */
    public Estacao[] getEstacoes() {
        return estacoes.clone();
    }
    
    /**
     * Obtem o indice
     * @param indice
     * @return
     */
    public Estacao getEstacao(int indice){
        if (indice < 0 || indice >= NUM){
            System.out.println("O indice não pode ser menor que 0, ou maior que " + NUM + "!");
        }
        //return estacoes[--indice]; PARECE QUE ESTA ERRADO, MAS VOCE USOU A LOGICA DO INDICE DE ESTACAO COMECAR A PARTIR DE DE 1 EM ALGUM OUTRO LUGAR?
        return estacoes[indice];
        
    }
}
    