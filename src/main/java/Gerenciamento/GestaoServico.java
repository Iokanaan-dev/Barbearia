/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.barbearia.modelos.TipoEstacao;
import java.util.ArrayList;

        
/**
 *
 * @author italo
 */
public class GestaoServico extends Gestao <Servico>{
    
    private final ArrayList<Servico> listaServicos = new ArrayList();
    
    private static GestaoServico instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoServico getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoServico();
        
        return instancia;
    }
    
    /**
     * Cadastra um novo servico na lista de servicos
     * @param nome
     * @param preco
     * @param descricao
     * @param temp
     */
    public void cadastrar(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido){
        Servico servico = new Servico(nome, preco, descricao, temp, tipoRequerido);
        super.adicionar(listaServicos, servico);
    }
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return
     */
    public ArrayList<Servico> getLista(){
        return listaServicos;
    }
    
    /**
     * Busca servicos na lista de clientes usando o nome
     * @param nome
     * @return
     */
    public ArrayList<Servico> buscarPorNome(String nome){
        return super.procurandoNome(listaServicos, nome);
    }
    
    /**
     * Imprime todos os servicos com um certo nome
     * @param nome
     */
    public void printPorNome(String nome){
        super.printLista(buscarPorNome(nome));
    }     

    /**
     * Busca um cliente na lista de clientes usando o id
     * @param id
     * @return
     */
    public Servico buscarPorId(String id){
        return super.procurandoID(listaServicos, id);
    }
    
    /**
     * Imprime o servico com um certo ID
     * @param id
     */
    public void printPorId(String id){
        super.printItem(buscarPorId(id));
    }     

    /**
     * Permite a edicao de informacoes de um servico
     * @param id
     * @param nome
     * @param preco
     * @param descricao
     * @param temp
     */
    public void editar(String id, String nome, double preco, String descricao, int temp ){
        
        Servico servico = this.buscarPorId(id);
        
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDescricao(descricao);
        servico.setTempoEmMinutos(temp);
    }    
    
    /**
     * Remove um servico com base no Id informado
     * @param Id
     */
    public void remover(String Id){
        super.remover(listaServicos, Id);
    }
    
    /**
     * Imprime a lista de servicos atual
     */
    public void printLista(){
        super.printLista(listaServicos);
    }    
    
}
