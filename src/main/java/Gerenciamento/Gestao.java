package Gerenciamento;

import com.mycompany.barbearia.modelos.Modelo;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Essa classe serve para representar todos os métodos genéricos de gestão.
 * @author intalo
 * @param <M>
 */
public abstract class Gestao<M extends Modelo>{
    
    ArrayList<M> listaModelo = new ArrayList<>();

    // PARTE POLIMORIFCA DE GESTAO
    
    public ArrayList<M> getLista(){
        return listaModelo;
    }

    /**
     * Adiciona um modelo à listaModelo após verificar se o objeto é válido.
     *
     * @param modelo objeto do tipo M a ser adicionado.
     * @throws IllegalArgumentException caso o modelo seja nulo.
     */
    public void adicionar(M modelo){
        verificarModeloNulo(modelo);
        listaModelo.add(modelo);
    }

    /**
     * Busca um modelo na lista com base em seu ID.
     * O método valida o ID antes de realizar a busca.
     *
     * @param id identificador do modelo que deseja localizar.
     * @return o modelo encontrado ou null caso não exista (a validação anterior garante existência).
     * @throws IllegalArgumentException caso o ID seja nulo.
     * @throws NoSuchElementException caso nenhum item com o ID seja encontrado.
     */
    public M buscarPorId(String id){
        verificarId(id);

        for (M modelo : listaModelo) {
            if (modelo.getId().equals(id)) 
                return modelo;
        }
        return null;
    }

    /**
     * Verifica se o ID é válido (não nulo e existente na listaModelo).
     *
     * @param id identificador a ser verificado.
     * @throws IllegalArgumentException caso o ID seja nulo.
     * @throws NoSuchElementException caso nenhum item com o ID seja encontrado.
     */
    private void verificarId(String id){

        verificarCampoNulo(id);

        for (M modelo : listaModelo) {
            if (modelo.getId().equals(id))
                return;
        }
        throw new NoSuchElementException("Nenhum item encontrado com o ID: " + id);
    } 

    /**
     * Busca todos os modelos cujo nome contenha o texto informado.
     *
     * @param nome trecho do nome desejado.
     * @return listaModelo contendo todos os modelos encontrados.
     * @throws IllegalArgumentException caso o nome seja nulo.
     */
    public ArrayList<M> buscarPorNome(String nome) {
        verificarCampoNulo(nome);
        ArrayList<M> nomesEncontrados = new ArrayList<>();

        for (M modelo : listaModelo) {
            if (modelo.getNome().toLowerCase().contains(nome.toLowerCase())) {
                nomesEncontrados.add(modelo);
            }
        }
        return nomesEncontrados;
    } 

    /**
     * Retorna uma string contendo o nome de todos os itens da listaModelo, cada um em uma linha.
     *
     * @return string contendo os nomes dos itens ou "---" caso a listaModelo esteja vazia.
     */
    public String getNomesItens() {
        String nomesItensLista = String.format("%n");
        if(listaModelo.isEmpty())
            return String.format("---%n");

        for(Modelo item: listaModelo)
            nomesItensLista += String.format("%s%n", item.getNome());

        return nomesItensLista;
    }  

    /**
     * Retorna o nome do item com base no ID informado.
     *
     * @param Id identificador do item.
     * @return nome do item ou "não encontrado" caso não exista.
     * @throws IllegalArgumentException caso o ID seja nulo.
     * @throws NoSuchElementException caso nenhum item com o ID seja encontrado.
     */
    public String getNomeItem(String Id) {

        this.verificarId(Id);

        M modelo = buscarPorId(Id);

        if(modelo != null) {
            return modelo.getNome();
        }
        return "não encontrado";
    }  

    /**
     * Remove da listaModelo um item baseado no ID informado.
     *
     * @param id identificador do item a ser removido.
     * @throws IllegalArgumentException caso o ID seja nulo.
     * @throws NoSuchElementException caso nenhum item com o ID seja encontrado.
     */
    public void remover(String id) {
        verificarId(id);

        for (int i = 0; i < listaModelo.size(); i++) {
            if (Objects.equals(listaModelo.get(i).getId(), id))
                listaModelo.remove(i);   
        }
    }  

    /**
     * Imprime todos os itens da listaModelo usando o método toString().
     */
    public void printLista(){

        for (Modelo m : listaModelo)
            System.out.println(m);

        System.out.println();   
    }

    /**
     * Imprime um item específico baseado no ID informado.
     *
     * @param id identificador do item a ser impresso.
     * @throws IllegalArgumentException caso o ID seja nulo.
     * @throws NoSuchElementException caso nenhum item com o ID seja encontrado.
     */
    public void printPorId(String id) {
        this.verificarId(id);

        M item = this.buscarPorId(id);

        System.out.println(item.toString());        
    } 
    
    public void printPorNome(String nome){
        verificarCampoNulo(nome);
        
        for(M item: listaModelo){
            if (item.getNome().equals(nome))
                System.out.println(item.getNome());
        }
    }

    /**
     * Verifica se um campo string é nulo.
     *
     * @param campo string a ser validada.
     * @throws IllegalArgumentException caso o campo seja nulo.
     */
    public void verificarCampoNulo(String campo){
        if(campo == null)
            throw new IllegalArgumentException("O campo não pode ser nulo.");
    }  

    /**
     * Verifica se o modelo é nulo.
     *
     * @param modelo objeto a ser validado.
     * @throws IllegalArgumentException caso o modelo seja nulo.
     */
    public void verificarModeloNulo(M modelo){
        if(modelo == null)
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
    }
   
  
    // PARTE NAO POLIMORFICA=================================================

    /**
     * Adiciona um modelo à listaModelo.
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
    public M buscarPorId(ArrayList<M> lista, String id) {
        verificarId(id);
        
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
        return encontrados;
    }
    
    /**
     *
     * @param lista
     * @return
     */
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
        M modelo = buscarPorId(lista, Id);
        
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
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        boolean encontrou = false;

        for (int i = 0; i < lista.size(); i++) {
            if (Objects.equals(lista.get(i).getId(), id)) {
                lista.remove(i);
                encontrou = true;
                break; // remove só 1, tire se quiser remover todos
            }
        }

        if (!encontrou) {
            throw new NoSuchElementException("Nenhum item encontrado com o ID: " + id);
        }
    }


    
    /**
     * Imprime a listaModelo de modelos passada
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
