/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

import com.mycompany.barbearia.individuo.Individuo; // Importa a classe pai
import java.util.ArrayList;

/**
 * Uma classe genérica para gerenciar listas de qualquer tipo que herde de Individuo.
 * Usa Generics (<T>) para ser reutilizável com Clientes, Funcionarios, etc.
 * @param <T> O tipo de indivíduo que a lista irá armazenar.
 */
public class ListaGenerica<T extends Individuo> {

    private final ArrayList<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        this.itens.add(item);
    }

    public boolean remover(String id) {
        // Se o id for o mesmo de algum item na nossa lista, remova
        return this.itens.removeIf(item -> item.getId().equals(id));
    }

    public T buscaPorId(String id) {
        for (T item : this.itens) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<T> buscaPorNome(String parteDoNome) {
        ArrayList<T> encontrados = new ArrayList<>();
        for (T item : this.itens) {
            if (item.getNome().toLowerCase().contains(parteDoNome.toLowerCase())) {
                encontrados.add(item);
            }
        }
        return encontrados;
    }

    // Retorna uma cópia da lista para manter o encapsulamento.
    public ArrayList<T> getItens() {
        return new ArrayList<>(this.itens);
    }
}
