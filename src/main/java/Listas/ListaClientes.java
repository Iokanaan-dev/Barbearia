/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;
import java.util.ArrayList;
import java.time.LocalDate;
import com.mycompany.barbearia.individuo.Cliente;

/**
 *
 * @author intalo
 */
public class ListaClientes { // [OBS: Isso pode ser um abstracao de lista para qualquer lista do projeto, talvez como classe pai herdar dessa]
    // ArrayList para armazenar os clientes
    private final ArrayList<Cliente> CLIENTES = new ArrayList<>(); 

    public ArrayList<Cliente> getClientes() {
        return CLIENTES;
    }
    
    public void adicionarCliente(Cliente cliente)
    {
        CLIENTES.add(cliente);
    }
    
    public void removerCliente(int idCliente)
    {
        
    }
    
    // [OBS: COLOCAR AS BUSCAS AQUI]
   
    //[OBS: Criei a classe generica de listas como vc falou, nela existem os metodos que utilizam as listas, para ver um exemplo de como implementar dessa forma é só ir na classe gestãodeclientes]
}
