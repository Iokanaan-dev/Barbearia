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
        System.out.printf("Adicionado com sucesso %nID: %s%n", modelo.getId());
    }

    /**
     * Busca um modelo pelo ID.
     * @param lista
     * @param id
     * @return M
     */
    public M buscarPorId(ArrayList<M> lista, String id) {
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
    public ArrayList<M> buscarPorNome(ArrayList<M> lista, String nome) {
        ArrayList<M> encontrados = new ArrayList<>();

        for (M modelo : lista) {
            if (modelo.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(modelo);
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("Nada foi encontrado.");
        }

        return encontrados;
    }
    
    /**
     * Retorna um nome de um modelo com base no seu ID
     * @param lista
     * @param Id
     * @return String
     */
    public String getNomeItem(ArrayList<M> lista, String Id) 
    {
        return buscarPorId(lista, Id).getNome();
    }

    /**
     * Remove um modelo pelo ID.
     * @param lista
     * @param id
     */
    public void remover(ArrayList<M> lista, String id) {
        boolean removeu = lista.removeIf(modelo -> Objects.equals(modelo.getId(), id));

        if (removeu)
            System.out.println("Remoção bem-sucedida.");
        else
            System.out.println("Não foi possível remover.");
    }
    
    /**
     * Imprime a lista de modelos passada
     * @param modelos
     */
    public void printLista(ArrayList<M> modelos){
        if(modelos != null){
        for (Modelo m : modelos)
            System.out.println(m);
        }
        else
            System.out.println("Lista vazia.");
            
        System.out.println();   

    }
    
    /**
     * Imprime a representaçao string de um modelo
     * @param modelo
     */
    public void printItem(M modelo)
    {
        System.out.println(modelo.toString());
    }          
}
