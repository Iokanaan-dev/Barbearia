/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gerenciamento;

import Listas.ListaGenerica;
import com.mycompany.barbearia.modelos.OrdemServico;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.barbearia.modelos.Produto;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author intalo
 */
public class GestaoOrdemServico {
    
    // cria uma lista de OSs que sera gerenciada
    private final ListaGenerica<OrdemServico> listaOS = new ListaGenerica();
    
    // metodo que coleta os dados do cliente para o construtor e passa para adicionar cliente
    public void cadastrar(String idCliente, String idBarbeiro, String observacoes, LocalDate dataExecucao){
        OrdemServico novaOrdemServico = new OrdemServico(idCliente, idBarbeiro, observacoes, dataExecucao);
        this.listaOS.adicionar(novaOrdemServico);
    }
    
    // essa sobrecarga em cadastrar torna o campo de observacoes opcional
    public void cadastrar(String idCliente, String idBarbeiro, LocalDate dataExecucao){
        OrdemServico novaOrdemServico = new OrdemServico(idCliente, idBarbeiro, dataExecucao);
        this.listaOS.adicionar(novaOrdemServico);
    }    
    
    /**
     *
     * @param ID
     * @return
     */
    public OrdemServico buscarId(String ID){
       OrdemServico ordensSelecionadas;
       ordensSelecionadas = this.listaOS.buscaPorId(ID);
       
       if(ordensSelecionadas == null)
              System.out.println("Nenhuma ordem encontrada.");
       return ordensSelecionadas;
    }
    
    /**
     *
     * @param cliente
     * @param nome
     * @param cpf
     * @param telefone
     * @param dataNascimento
     * @param email
     */
    public void editarDescricao(String idOrdemServico, String observacao){
      // armazena a ordem de servico que sera editada
      OrdemServico ordemSelecionada = buscarId(idOrdemServico);
      
      // se a ordem retornada pelo buscarId nao for nula, edite a observacao
      if(ordemSelecionada != null)
          ordemSelecionada.setObservacoes(observacao);
    }
    
    public void adcionarServico(String idOrdemServico, Servico servicoAdicionado){
      // armazena a ordem de servico que sera editada
      OrdemServico ordemSelecionada = buscarId(idOrdemServico);
      
      // se a ordem retornada pelo buscarId nao for nula, aidcione o servico
      if(ordemSelecionada != null)
          ordemSelecionada.adicionarServico(servicoAdicionado);
    }
    
    public void adcionarProduto(String idOrdemServico, Produto produtoAdicionado){
      // armazena a ordem de servico que sera editada
      OrdemServico ordemSelecionada = buscarId(idOrdemServico);
      
      // se a ordem retornada pelo buscarId nao for nula, aidcione o servico
      if(ordemSelecionada != null)
          ordemSelecionada.adicionarProduto(produtoAdicionado);
    }    
    
    /**
     *
     * @return
     */
    public ArrayList<OrdemServico> getListaOS(){
        return this.listaOS.getLista();
    }

    /**
     *
     * @param idCliente
     */
    public void removerOS(String idCliente){
        // armazena o retorno do metodo remover, remover() retorna true apenas se um objeto foi encontrado e removido
        boolean removeu = this.listaOS.remover(idCliente);
  
        if(removeu)
            System.out.println("Remoção bem-sucedida.");
        else
            System.out.println("Não foi possivel remover");
    }

}
