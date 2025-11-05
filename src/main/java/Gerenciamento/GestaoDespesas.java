/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.Despesa;
import com.mycompany.barbearia.modelos.Gerente;
import Utilidades.TipoDespesa;
import com.mycompany.barbearia.modelos.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author italo
 */
public class GestaoDespesas extends Gestao<Despesa> {
    
    private static GestaoDespesas instancia;
    private GestaoDespesas(){}
    public static GestaoDespesas getInstancia(){
        if (instancia == null) instancia = new GestaoDespesas();
        return instancia;
    }
    
    private final ArrayList<Despesa> listaDespesas = new ArrayList();
    
    
    public void lancarDespesas(String nome, Double valor, LocalDate data, TipoDespesa tipo, String obs, Usuario user) throws Exception {
       
        if (!(user instanceof Gerente)) {
            throw new Exception("Acesso negado, só gerentes podem lancar despesas");
        }
        
        Despesa novaDespesa = new Despesa(nome, valor, tipo, obs);
        super.adicionar(this.listaDespesas, novaDespesa);
    }
    
    public ArrayList<Despesa> getList(Usuario user) throws Exception {
        if(!(user instanceof Gerente)) {
            throw new Exception("Acesso negado");
        }
        return new ArrayList(this.listaDespesas);
    }
}
    