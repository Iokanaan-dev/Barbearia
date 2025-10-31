/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

import com.mycompany.barbearia.modelos.Modelo; 
import java.util.ArrayList;
import java.util.Objects;



/**
 * Uma classe genérica para gerenciar listas de qualquer tipo que herde de Modelo.Usa Generics (<G>) para ser reutilizável com Clientes, Usuarios, Produtos, etc.
 * @param <M> O tipo de indivíduo que a lista irá armazenar.
 */
public class ListaGenerica<M extends Modelo> {

    private final ArrayList<M> lista = new ArrayList();

    /**
     *
     * @param modelo
     */
    public void adicionar(M modelo) {
        this.lista.add(modelo);
    }

    /**
     * Utiliza o metodo removeIf para remover itens da lista baseado em seu numero de id
     * 
     * @param id
     * @return
     */
    public boolean remover(String id) {
        // Se o id for o mesmo de algum modelo na nossa lista, remova
        return this.lista.removeIf(modelo -> Objects.equals(modelo.getId(), id)); //removeIf vai passar por cada elemento da nossa lista fazendo uma verificação: o id desse modelo é igual ao id que eu passei na função? se for ele remove esse modelo.
    }

    /**
     * Usa um for aprimorado para buscar em todos os elementos do ArrayList o elemento com o ID especificado
     *
     * @param id
     * @return
     */
    public M buscaPorId(String id) {
        for (M modelo : lista) {
            if (modelo.getId().equals(id))
                return modelo;
        }
        return null; // retorna null se nao encontra elememento como ID
    }

    /**
     * Retorna todos os elementos com o nome especificado
     *
     * @param Nome
     * @return
     */
    public ArrayList<M> buscaPorNome(String Nome) {
        ArrayList<M> encontrados = new ArrayList<>();
        for (M modelo : lista) {
            if (modelo.getNome().toLowerCase().contains(Nome.toLowerCase())) {
                encontrados.add(modelo);
            }
        }
        return encontrados;
    }

    /**
     *
     * @return
     */
    public ArrayList<M> getLista() {
        return new ArrayList<>(this.lista);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String itensLista = null;
        
        // se a lista estiver vazia toString retorna uma indicacao
        if(this.lista.isEmpty())
            return "---";
     
        for(Modelo item: this.lista)
            itensLista += item.toString();
        
        return itensLista;
    }
}
