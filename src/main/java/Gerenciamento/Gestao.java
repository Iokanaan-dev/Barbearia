/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Listas.ListaGenerica;
import com.mycompany.barbearia.modelos.Modelo;
import com.mycompany.barbearia.modelos.Produto;
import java.util.ArrayList;

/**
 *
 * @author intalo
 */
//IDEIA PARA DEPOIS QUE AS COISAS ESTIVEREM FUNCIONANDO!
/*
// acho que todos os metodos de buscar, cadastrar adicionar, remover e etc podem ter origem aqui, so precisamos deixar nas subclasses dessa classe os metodos de cadastrar (que ainda assim pode ser abstract)
public abstract class Gestao { 
    
    // algo como isso para todos, exceto cadastrar e alguns especificos
    public Modelo buscarId(ListaGenerica<Modelo> listaModelo, String Id){
        Modelo modeloSelecionado = listaModelo.buscaPorId(Id); 
       
        if(modeloSelecionado == null)
              System.out.println("Nada foi encontrado.");
        return modeloSelecionado;
   }
    
    // retorna todos os Modelos na lista respectiva com o nome especificado
    public ArrayList<Modelo> buscarNome(ListaGenerica<Modelo> listaModelo, String nome){
        ArrayList<Modelo> clientesSelecionados;
      
        clientesSelecionados = listaModelo.buscaPorNome(nome);
      
         if (clientesSelecionados.isEmpty()) // se a lista retornada eh vazia o imprime uma mensagem de avisoé
            System.out.println("Nada foi encontrado encontrado.");
         
         return clientesSelecionados;
    }
        
    public void remover(ListaGenerica<Modelo> listaModelo, String Id){
        // armazena o retorno do metodo remover, remover() retorna true apenas se um objeto foi encontrado e removido
        boolean removeu = listaModelo.remover(Id);
  
        if(removeu)
            System.out.println("Remoção bem-sucedida.");
        else
            System.out.println("Não foi possivel remover");
    }    

}
*/