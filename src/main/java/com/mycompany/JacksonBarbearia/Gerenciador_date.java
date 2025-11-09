/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.JacksonBarbearia;
import com.mycompany.barbearia.modelos.*;
import com.mycompany.Gerenciamento.*;

/**
 *
 * @author italo
 */
public class Gerenciador_date {
    private static Gerenciador_date instancia;
    
    // O nome do arquivo único
    private static final String ARQUIVO_BD = "dados/barbearia_completa.json";

    private Gerenciador_date() {}
    
    public static Gerenciador_date getInstancia() {
        if (instancia == null) {
            instancia = new Gerenciador_date();
        }
        return instancia;
    }
    
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        GestaoAgendamento gestaoA = GestaoAgendamento.getInstancia();
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoServico gestaoS = GestaoServico.getInstancia();
        GestaoEstoque gestaoE = GestaoEstoque.getInstancia();
        GestaoDespesas gestaoD = GestaoDespesas.getInstancia();
        GestaoListaEspera gestaoLE = GestaoListaEspera.getInstancia();
        Usuario gerente = gestaoU.buscarUsername("gerente_joao"); 
        GestaoPonto gestaoPonto = GestaoPonto.getInstancia();
    
    // --- O MÉTODO DE SALVAR ---
    public void salvarTudo() throws Exception {
        System.out.println("[Persistencia]: Salvando dados (Método @JsonIdentityInfo)...");
       
        Barbearia_date dados = new Barbearia_date();
     
        dados.listaClientes = gestaoC.getLista();
        dados.listaUsuarios = gestaoU.getLista();
        dados.listaProdutos = gestaoP.getLista();
        dados.listaServicos = gestaoS.getLista();
        dados.listaDespesas = gestaoD.getList(gerente); 
        
        dados.listaAgendamentos = gestaoA.getLista();
        dados.listaOrdensServico = gestaoOS.getLista();
        
        dados.listaDeEspera = gestaoLE.getPilhaEspera(); 
        dados.estoque = gestaoE.getEstoque();
        dados.tabelaPonto = gestaoPonto.getTabela();

        Jackson.salvarObjeto(dados, ARQUIVO_BD);
        
        System.out.println("[Persistencia]: Dados salvos com sucesso!");
    }
    
    // --- O MÉTODO DE CARREGAR ---
    public void carregarTudo() throws Exception {
        System.out.println("[Persistencia]: Carregando dados (Método @JsonIdentityInfo)...");
        Barbearia_date dados = Jackson.carregarObjeto(ARQUIVO_BD, Barbearia_date.class);
        
        if (dados == null) {
             System.out.println("[Persistencia]: Nenhum arquivo de dados encontrado. Iniciando sistema vazio.");
                 return;
        }
        
        gestaoC.carregarLista(dados.listaClientes);
        gestaoU.carregarLista(dados.listaUsuarios);
        gestaoP.carregarLista(dados.listaProdutos);
        gestaoS.carregarLista(dados.listaServicos);
        gestaoD.carregarLista(dados.listaDespesas);
        
        gestaoA.carregarLista(dados.listaAgendamentos);
        gestaoOS.carregarLista(dados.listaOrdensServico);
        
        gestaoE.carregarEstoque(dados.estoque); 
        gestaoLE.carregarPilha(dados.listaDeEspera); 
        gestaoPonto.carregarTabela(dados.tabelaPonto);

        System.out.println("[Persistencia]: Dados carregados com sucesso!");
    }
}
