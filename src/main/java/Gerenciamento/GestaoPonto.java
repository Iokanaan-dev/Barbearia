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
        
        if(!usuarioExiste(idUsuario))
            throw new IllegalArgumentException("Usuario não existe no sistema.");
        
        if(verificarBatida(tipoBatida)){
            registrarEntrada(idUsuario, construirParBatida());
            return;
        }
                
        registrarSaida(idUsuario);
    }
    
    private boolean verificarBatida(String tipoBatida) {
        return "Entrada".equals(tipoBatida);
    }

    public LocalDateTime pegarHorarioAtual(){
        return LocalDateTime.now();
    }
    
    private ParBatida construirParBatida(){ // private pois so vai ser usado aqui dentro da classe
            ParBatida parBatida = new ParBatida(pegarHorarioAtual());
            return parBatida;
    }
    
    public void registrarEntrada(String id, ParBatida parBatida){ // esse metodo serve para fazer a batida de Entrada
        if(!this.tabelaPontos.contemUsuario(id))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        ArrayList<ParBatida>listaPontoUsuarios = tabelaPontos.getTabelaPontos().get(id);
        
        if(listaPontoUsuarios.isEmpty())
            
        this.tabelaPontos.getTabelaPontos().get(id).add(parBatida);
    }    
    
    public void registrarSaida(String id){ // esse metodo serve para fazer a batida de saida
        if(!this.tabelaPontos.contemUsuario(id))
            throw new IllegalArgumentException("Usuario não existe na tabela de ponto."); 
        
        ArrayList<ParBatida>listaPontoUsuarios = tabelaPontos.getTabelaPontos().get(id);
        listaPontoUsuarios.get(listaPontoUsuarios.size()-1).setSaida(pegarHorarioAtual());
    }     
    
    public boolean usuarioExiste(String id){
        return (GestaoUsuarios.getInstancia().buscarPorId(id) != null);      
    } 
    
    // CUIDADO COM ESSAS EXCEÇOES DE ENTRADA E SAIDA, FALTA AINDA UM METODO PARA ADICIONAR O PAR <ID, ARRAYLISY>
}
