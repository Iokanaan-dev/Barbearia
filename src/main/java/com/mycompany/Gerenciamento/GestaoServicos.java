/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.date_Barbearia.Barbearia_date;

        
/**
 *
 * @author italo
 */
public class GestaoServicos extends Gestao <Servico>{
        
    private static GestaoServicos instancia;
    private Barbearia_date dados;
    
    GestaoServicos(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.listaServicos;
    }
    
    public static void inicializa(Barbearia_date dados) {
        if (instancia == null) {
        instancia = new GestaoServicos(dados);
        }
    }
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoServicos getInstancia(){
        return instancia;
    }
    
    /**
     * Cadastra um novo servico na lista de servicos
     * @param nome
     * @param preco
     * @param descricao
     * @param temp
     */
    public void cadastrar(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido) throws Exception{
        verificarServicoExiste(nome);
        verificarServicoDuracao(temp);
       
        Servico servico = construirServico(nome, preco, descricao, temp, tipoRequerido);
        super.adicionar(servico);
    }
    
    public void cadastrar(Servico servico) throws Exception{
        verificarServicoExiste(servico.getNome());
        verificarServicoDuracao(servico.getTempoEmMinutos10());
        
        super.adicionar(servico);    
    }
    
    private Servico construirServico(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido){
        return new Servico(nome, preco, descricao, temp, tipoRequerido);
    }
    
    private void verificarServicoExiste(String nome) throws Exception{
        if (buscarPorNomeExato(nome) != null)
            throw new Exception("Já existe um serviço cadastrado com o nome: " + nome);       
    }
    
    private void verificarServicoDuracao(int temp) throws Exception{
        if (temp <= 0)
            throw new IllegalArgumentException("Duração deve ser positiva.");    
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
        
        super.verificarModeloNulo(servico);
        
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDescricao(descricao);
        servico.setTempoEmMinutos(temp);
    }       
}
