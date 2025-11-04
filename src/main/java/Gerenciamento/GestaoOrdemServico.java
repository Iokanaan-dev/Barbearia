/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import com.mycompany.barbearia.modelos.OrdemServico;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.barbearia.modelos.Produto;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author intalo
 */
public class GestaoOrdemServico extends Gestao<OrdemServico> {
    
    // cria uma lista de OSs que sera gerenciada
    private final ArrayList<OrdemServico> listaOS = new ArrayList<>();
    
    private static GestaoOrdemServico instancia;
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoOrdemServico
     */
    public static GestaoOrdemServico getInstancia()
    {
        if(instancia == null)
            instancia = new GestaoOrdemServico();
        
        return instancia;
    }
    

    /**
     * Cadastra uma nova ordem de serviço na lista de ordens de Serviça
     * @param idCliente
     * @param idBarbeiro
     * @param observacoes
     * @param dataExecucao
     */
    public void cadastrar(String idCliente, String idBarbeiro, String observacoes, LocalDate dataExecucao){
        OrdemServico novaOrdemServico = new OrdemServico(idCliente, idBarbeiro, observacoes, dataExecucao);
        super.adicionar(listaOS, novaOrdemServico);
    }
    
    /**
     *  Permite cadastrar uma ordem de serviço sem adicionar as observaçoes, tornando esse campo opcional
     * @param idCliente
     * @param idBarbeiro
     * @param dataExecucao
     */
    public void cadastrar(String idCliente, String idBarbeiro, LocalDate dataExecucao){
        OrdemServico novaOrdemServico = new OrdemServico(idCliente, idBarbeiro, dataExecucao);
        super.adicionar(listaOS, novaOrdemServico);
    }
    
    /**
     * Torna possivel a busca por id em outras classes, como as de gestao
     * @return ArrayList<>
     */
    public ArrayList<OrdemServico> getLista() {
        return listaOS;
    }       
    
    /**
     * Busca uma ordem de serviço na lista usando o ID
     * @param id
     * 
     * @return Cliente
     */
    public OrdemServico buscarPorId(String id){
        return super.buscarPorId(listaOS, id);
    }  
    
    /**
     * Edita as observacoes e a data de execucao
     * @param id
     * @param observacoes
     * @param dataExecucao
     */
    public void editar(String id, String observacoes, LocalDate dataExecucao){
      
      OrdemServico ordemServico = this.buscarPorId(id);
        
      ordemServico.setObservacoes(observacoes);
      ordemServico.setDataExecucao(dataExecucao);
    } 
    
    /**
     * Edita as observacoes
     * @param id
     * @param observacoes
     */
    public void editar(String id, String observacoes){
      
      OrdemServico ordemServico = this.buscarPorId(id);
        
      ordemServico.setObservacoes(observacoes);
    }
    
    /**
     * Remove uma ordem de serviço com base no ID informado
     * @param id
     */
    public void remover(String id){
        super.remover(listaOS, id);
    }
    
    /**
     * Imprime a lista de OSs atual
     */
    public void printLista(){
        super.printLista(listaOS);
    }        
   
    /**
     * Adiciona a uma ordem de serviço um serviço especificando ambos (ordem de serviço e serviço) pelos seus respectivos IDs
     * @param idOrdem
     * @param idServico
     */
    public void adcionarServico(String idOrdem, String idServico){
      // armazena a ordem de servico que sera editada
      OrdemServico ordemSelecionada = buscarPorId(idOrdem);
      
      // armazena o servico que sera adicionado
      Servico servicoAdicionado = GestaoServico.getInstancia().buscarPorId(idServico);
      
      ordemSelecionada.adicionarServico(servicoAdicionado);
    }
    
    /**
     * Adiciona a uma ordem de serviço um produto especificando ambos (ordem de serviço e produto) pelos seus respectivos IDs
     * @param idOrdem
     * @param idProduto
     */    
    public void adcionarProduto(String idOrdem, String idProduto){
      // armazena a ordem de servico que sera editada
      OrdemServico ordemSelecionada = buscarPorId(idOrdem);
      
      // armazena o produto que sera adicionado
      Produto produtoAdicionado = GestaoProdutos.getInstancia().buscarPorId(idProduto);
      
      ordemSelecionada.adicionarProduto(produtoAdicionado);
    }    
    
    /**
     * Busca todas as ordens de serviços associadas a um dado cliente e retorna em um ArrayList
     * @param id
     * @return ArrayList
     */
    private ArrayList<OrdemServico> buscarOSCliente(String id){ // private pois so sera usado aqui na classe
 ArrayList<OrdemServico> osSelecionadas = new ArrayList<>();

    for (OrdemServico os : listaOS) {
        if (os.getIdCliente().equals(id)) 
            super.adicionar(osSelecionadas, os);  
    }

    return osSelecionadas;
    }
    
    /**
     * Imprime todas as ordens de serviço associadas a um dado cliente
     * @param id
     */
    public void printListaOSCliente(String id){
        super.printLista(buscarOSCliente(id));
    }
}
