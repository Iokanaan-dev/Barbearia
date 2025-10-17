/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.Estacao;

/**
 *
 * @author italo
 */
public class GestaoEstacao {
    private static final int num = 3;
    private final Estacao[] estacoes = new Estacao[num];
    
    public GestaoEstacao(){
        estacoes[0] = new Estacao("Lavar e sacar", "Estação destinada apenas para lavar e secar o cabelo de nossos clientes!");
        estacoes[1] = new Estacao("Corriqueira 1", "Estação destinada apenas para atividades corriqueiras da barbearia");
        estacoes[2] = new Estacao("Corriqueira 2", "Estação destinada apenas para atividades corriqueiras da barbearia");
    }

    public Estacao[] getEstacoes() {
        return estacoes.clone();
    }
    
    public Estacao getEstacao(int indice){
        if (indice < 0 || indice >= num){
            System.out.println("O indice não pode ser menor que 0, ou maior que " + num + "!");
        }
        return estacoes[indice];
    }
}
    