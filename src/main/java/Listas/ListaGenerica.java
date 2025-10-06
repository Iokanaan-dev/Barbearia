/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

import com.mycompany.barbearia.modelos.Modelo; 
import java.util.ArrayList;
import java.util.Objects;


/**
 * Uma classe genérica para gerenciar listas de qualquer tipo que herde de Individuo.
 * Usa Generics (<G>) para ser reutilizável com Clientes, Usuarios, etc.
 * @param <G> O tipo de indivíduo que a lista irá armazenar.
 */
public class ListaGenerica<G extends Modelo> {

    private final ArrayList<G> lista = new ArrayList();

    public void adicionar(G objeto) {
        this.lista.add(objeto);
    }

    public boolean remover(String id) {
        // Se o id for o mesmo de algum objeto na nossa lista, remova
        return this.lista.removeIf(objeto -> Objects.equals(objeto.getId(), id)); //removeIf vai passar por cada elemento da nossa lista fazendo uma verificação: o id desse objeto é igual ao id que eu passei na função? se for ele remove esse objeto.
    }

    public G buscaPorId(String id) {
        for (G objeto : lista) {
            if (objeto.getId().equals(id)) {
                return objeto;
            }
        }
        return null;    
    }

    public ArrayList<G> buscaPorNome(String Nome) {
        ArrayList<G> encontrados = new ArrayList<>();
        for (G objeto : lista) {
            if (objeto.getNome().toLowerCase().contains(Nome.toLowerCase())) {
                encontrados.add(objeto);
            }
        }
        return encontrados;
    }

    // Retorna uma cópia da lista para manter o encapsulamento.
    public ArrayList<G> getLista() {
        return new ArrayList<>(this.lista);
    }

    @Override
    public String toString() {
        return "ListaGenerica{" + "lista=" + lista + '}';
    }
}
