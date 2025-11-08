/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;
import com.mycompany.barbearia.modelos.Servico;
import Utilidades.TipoEstacao;

        
/**
 *
 * @author italo
 */
public class GestaoServico extends Gestao <Servico>{
        
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
        super.adicionar(servico);
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
        
        if(servico == null){
            throw new IllegalArgumentException("Servico nao pode ser nulo.");
        }
        
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDescricao(descricao);
        servico.setTempoEmMinutos(temp);
    }       
}
