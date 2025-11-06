package Gerenciamento;

import com.mycompany.barbearia.modelos.Modelo;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Essa classe serve para representar todos os métodos genéricos de gestão.
 * @author intalo
 * @param <M>
 */
public abstract class Gestao<M extends Modelo>{

    /**
     * Adiciona um modelo à lista.
     * @param lista
     * @param modelo
     */
    public void adicionar(ArrayList<M> lista, M modelo) {
        lista.add(modelo);
    }

    /**
     * Busca um modelo pelo ID.
     * @param lista
     * @param id
     * @return M
     */
    public M procurandoID(ArrayList<M> lista, String id) {
        for (M modelo : lista) {
            if (modelo.getId().equals(id)) {
                return modelo;
            }
        }
        return null;
    }

    /**
     * Busca modelos pelo nome (case-insensitive).
     * @param lista
     * @param nome
     * @return ArrayList<>
     */
    public ArrayList<M> procurandoNome(ArrayList<M> lista, String nome) {
        ArrayList<M> encontrados = new ArrayList<>();

        for (M modelo : lista) {
            if (modelo.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(modelo);
            }
        }
        return encontrados;
    }
    public String getNomesItens(ArrayList<M> lista) {
        String nomesItensLista = String.format("%n");
        if(lista.isEmpty())
            return String.format("---%n");
      
        for(Modelo item: lista)
            nomesItensLista += String.format("%s%n", item.getNome());
        
        return nomesItensLista;
    }
    
    /**
     * Retorna um nome de um modelo com base no seu ID
     * @param lista
     * @param Id
     * @return String
     */
    public String getNomeItem(ArrayList<M> lista, String Id) {
        M modelo = procurandoID(lista, Id);
        
        if(modelo != null) {
            return modelo.getNome();
        }
        return "não encontrado";
    }

    /**
     * Remove um modelo pelo ID.
     * @param lista
     * @param id
     */
    public void remover(ArrayList<M> lista, String id) {
        lista.removeIf(modelo -> Objects.equals(modelo.getId(), id));
    }
    
    /**
     * Imprime a lista de modelos passada
     * @param modelos
     */
    public void printLista(ArrayList<M> modelos){
        if(modelos == null || modelos.isEmpty()){
            throw new IllegalArgumentException("Argumento invalido para print.");
        }
        else{
            for (Modelo m : modelos)
                System.out.println(m);
        }
        System.out.println();   
    }
    
    /**
     * Imprime a representaçao string de um modelo
     * @param modelo
     */
    public void printItem(M modelo) {
        if (modelo == null) {
            System.out.println("Item não encontrado");
        } 
        else {
            System.out.println(modelo.toString());
        }
    }          
}
