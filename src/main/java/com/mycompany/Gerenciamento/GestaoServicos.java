/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.date_Barbearia.Barbearia_date;

        
/**
 * Gerencia os servicos
 * @author italo
 */
public class GestaoServicos extends Gestao <Servico>{
        
    private static GestaoServicos instancia;
    private Barbearia_date dados;
    
    /**
     * Contador private para a questao
     */
    private static int contadorPrivate = 0;

    /**
     * Contador protected para questao
     */
    protected static int contadorProtected = 0;
    
    /**
     * Inicializa os dados
     * @param dados 
     */
    GestaoServicos(Barbearia_date dados){
        this.dados = dados;
        listaModelo = dados.getListaServicos();
    }
    
    /**
     * Inicaliza com os dados salvos
     * @param dados a serem carregados
     */
    public static void inicializa(Barbearia_date dados) {
        if (instancia == null) {
        instancia = new GestaoServicos(dados);
        }
    }
    

    /**
     * Obtem o valor do contador private
     * @return contador private no momento atual
     */
    public static int getContadorPrivate() {
        return contadorPrivate;
    }
    
    /**
     * Permite o uso do padrao singleton para permitir o acesso da lista dessa classe em outras classes
     * @return GestaoClientes
     */
    public static GestaoServicos getInstancia(){
        return instancia;
    }
    
    /**
     * Cadastra um novo servico na lista de servicos pelo seus dados e incrementa
     * os contadores de instancia
     * @param nome do servico
     * @param preco do servico
     * @param descricao do servico
     * @param temp tempo do servico em slots
     * @param tipoRequerido tipo da estacao necessaria para o servico
     * @throws java.lang.Exception
     */
    public void cadastrar(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido) throws Exception{
        verificarServicoExiste(nome);
        verificarServicoDuracao(temp);
       
        Servico servico = construirServico(nome, preco, descricao, temp, tipoRequerido);
        super.adicionar(servico);
        ++contadorPrivate;
        ++contadorProtected;
    }
    
    /**
     * Cadastra um novo servico diretamente e incrementa o contador de instancia
     * @param servico a ser cadastrado
     * @throws Exception
     */
    public void cadastrar(Servico servico) throws Exception{
        verificarServicoExiste(servico.getNome());
        verificarServicoDuracao(servico.getTempoEmMinutos());
        
        super.adicionar(servico);
        ++contadorPrivate;
        ++contadorProtected;
    }
    
    /**
     * Chama o construtor de um Servico
     * @param nome do servico
     * @param preco do servico
     * @param descricao do servico
     * @param temp tempo em slots do servico
     * @param tipoRequerido tipo de estacao necessaria para o servico
     * @return 
     */
    private Servico construirServico(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido){
        return new Servico(nome, preco, descricao, temp, tipoRequerido);
    }
    
    /**
     * Verifica se o servico existe pelo nome. Usado em cadastrar
     * @param nome o nome do servico
     * @throws Exception 
     */
    private void verificarServicoExiste(String nome) throws Exception{
        if (buscarPorNomeExato(nome) != null)
            throw new Exception("Já existe um serviço cadastrado com o nome: " + nome);       
    }
    
    /**
     * Verifica a duracao de um servico eh valida. Usado em cadastrar
     * @param temp tempo de um servico
     * @throws Exception 
     */
    private void verificarServicoDuracao(int temp) throws Exception{
        if (temp <= 0)
            throw new IllegalArgumentException("Duração deve ser positiva.");    
    }    

    /**
     * Permite a edicao de informacoes de um servico
     * @param servico a ser editado
     * @param nome novo do servico
     * @param preco novo do servico
     * @param descricao nova para o servico
     * @param temp tempo novo para o servico
     */
    public void editar(Servico servico, String nome, double preco, String descricao, int temp ){
                
        super.verificarModeloNulo(servico);
        
        if(nome != null)
            servico.setNome(nome);
        
        if(preco > 0)
            servico.setPreco(preco);
        
        if(descricao != null)
            servico.setDescricao(descricao);
        
        if(temp > 0)
            servico.setTempoEmMinutos(temp);
        
    }       
}