package com.mycompany.Gerenciamento;

import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.barbearia.modelos.Modelo;
import com.mycompany.barbearia.modelos.Usuario;
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
        return new ArrayList(this.listaModelo);
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
    
    protected void printLista(ArrayList<M> listaFiltrada) {
        if (listaFiltrada == null || listaFiltrada.isEmpty()) {
            System.out.println("Nenhum item encontrado.");
        } else {
            for (Modelo m : listaFiltrada)
                System.out.println(m);
        }
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
    /**
     * Carrega uma lista de dados (vinda do JSON) para dentro
     * da lista interna 'listaModelo' desta gestão.
     * * Este método APAGA todos os dados atuais em memória e os SUBSTITUI
     * pelos dados carregados do arquivo.
     * (Este é o método que o ServicoDePersistencia chama)
     *
     * @param listaCarregada A lista de objetos lida do JSON.
     */
    
    public Modelo buscarPorNomeExato(String nome) {
        verificarCampoNulo(nome); 
        
        for (Modelo t : this.listaModelo) { 
            if (t.getNome().equalsIgnoreCase(nome)) {
                return t; 
            }
        }
        return null; 
    }
}
