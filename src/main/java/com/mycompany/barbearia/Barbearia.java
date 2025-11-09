package com.mycompany.barbearia;

import com.mycompany.Gerenciamento.GestaoAgendamento;
import com.mycompany.Gerenciamento.GestaoClientes;
import com.mycompany.Gerenciamento.GestaoDespesas;
import com.mycompany.Gerenciamento.GestaoProdutos;
import com.mycompany.Gerenciamento.GestaoEstoque;
import com.mycompany.Gerenciamento.GestaoServico;
import com.mycompany.Gerenciamento.GestaoEstacao;
import com.mycompany.Gerenciamento.GestaoUsuarios;
import com.mycompany.Gerenciamento.GestaoFinanceira;
import com.mycompany.Gerenciamento.GestaoListaEspera;
import com.mycompany.Gerenciamento.GestaoOrdemServico;
import com.mycompany.JacksonBarbearia.Gerenciador_date;
import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import com.mycompany.Utilidades.TipoDespesa;
import com.mycompany.Utilidades.TipoUsuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
    
    public static int totalOrdensServico() {
        return OrdemServico.getContador();
    }
    
    public static void main(String[] args) {
     
     System.out.println("--- INICIANDO SISTEMA DE BARBEARIA (TESTE JSON) ---");
        
        // --- 1. INICIALIZAÇÃO DOS SINGLETONS ---
        // (Isso é rápido, eles são criados vazios)
        System.out.println("Inicializando serviços de gestão...");
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        GestaoServico gestaoS = GestaoServico.getInstancia();
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoEstoque gestaoEstoque = GestaoEstoque.getInstancia();
        GestaoAgendamento gestaoAGE = GestaoAgendamento.getInstancia();
        GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        GestaoListaEspera gestaoLE = GestaoListaEspera.getInstancia(); 
        GestaoDespesas gestaoDES = GestaoDespesas.getInstancia();
        GestaoFinanceira gestaoFIN = GestaoFinanceira.getInstancia();
        
        // Pega o Orquestrador de Persistência
        Gerenciador_date persistencia = Gerenciador_date.getInstancia();
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        LocalDate hoje = LocalDate.now();
        
        // --- 2. CARREGAR OS DADOS DO DISCO ---
        System.out.println("\n--- [CARREGANDO DADOS] ---");
        try {
            // O Orquestrador chama o JsonManager para ler o arquivo
            // e "hidratar" todos os Singletons.
            persistencia.carregarTudo(); 
        } catch (Exception e) {
            System.err.println("AVISO: Nenhum arquivo de dados (barbearia_completa.json) encontrado.");
            System.out.println("Sistema iniciando vazio. (Executando Setup pela primeira vez...)");
            
            // ***************************************************************
            // *** EXECUÇÃO 1 (SETUP): SE O JSON NÃO EXISTE, CADASTRE TUDO ***
            // (Se você rodar pela primeira vez, o código abaixo será executado)
            // ***************************************************************
            try {
                System.out.println("\n--- Cadastrando dados de setup (Primeira Execução) ---");
                
                // A. Cadastrar Clientes
                gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
                gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
                
                // B. Cadastrar Usuários
                gestaoU.cadastrar("atendente_pedro", "pedro123", "pedro", "333", "38998090957", data1, TipoUsuario.ATENDENTE);
                gestaoU.cadastrar("barbeiro_marcos", "marcos123", "marcos", "444", "38998090957", data1, TipoUsuario.BARBEIRO);
                Usuario gerenteJoao = gestaoU.buscarUsername("gerente_joao"); // (Erro, ele não existe ainda)
                gestaoU.cadastrar("gerente_joao", "joao1239", "joão", "000", "38998090957", data1, TipoUsuario.GERENTE, "8181");
                gerenteJoao = gestaoU.buscarPorId("GE1"); // (Busca correta)
                
                // C. Cadastrar Serviços (em SLOTS de 10 min)
                gestaoS.cadastrar("Corte", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA); // 30 min
                gestaoS.cadastrar("Lavar", 20.0, "Lavagem simples", 1, TipoEstacao.LAVAGEM); // 10 min
                
                // D. Cadastrar Produtos e Estoque
                gestaoP.cadastrar("Pomada Modeladora", 25.00, "Pomada de alta fixação");
                Produto pomada = gestaoP.buscarPorId("PO1");
                gestaoEstoque.adicionarAoEstoque(pomada.getId(), 0); 
                gestaoEstoque.aumentarQuantidade(pomada.getId(), 10);
                
                // E. Cadastrar Despesas
                gestaoDES.lancarDespesas("Café do Mês", 150.00, hoje, TipoDespesa.CONSUMIVEIS, "Compra", gerenteJoao);
                
                System.out.println("Setup concluído.");

            } catch (Exception setupError) {
                System.err.println("!!! ERRO CRÍTICO NO SETUP DE DADOS: " + setupError.getMessage());
                setupError.printStackTrace();
            }
        }

        // --- 3. TESTE DE VERIFICAÇÃO (O PROGRAMA PRINCIPAL) ---
        // (Depois da Execução 2, as listas abaixo devem estar CHEIAS)
        
        System.out.println("\n--- [TESTE PÓS-CARREGAMENTO] ---");
        System.out.println("Verificando clientes carregados em memória:");
        gestaoC.printLista(); // (Este é o seu método da Gestao<M>)
        
        System.out.println("Verificando usuários carregados em memória:");
        gestaoU.printLista();
        
        System.out.println("Verificando serviços carregados em memória:");
        gestaoS.printLista();
        
        // (Aqui você pode rodar os seus Cenários de Teste 1, 2, 3...
        //  Eles agora vão operar sobre os dados carregados do JSON)
        
        // ... (Seus testes de Agendamento, OS, etc. viriam aqui) ...


        // --- 4. SALVAR AO SAIR ---
        System.out.println("\n--- [SALVANDO DADOS] ---");
        try {
            // O Orquestrador chama o JsonManager para salvar
            // o estado ATUAL da memória (incluindo novos agendamentos)
            // de volta para o 'barbearia_completa.json'
            persistencia.salvarTudo();
        } catch (Exception e) {
            System.err.println("ERRO CRÍTICO AO SALVAR DADOS: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("--- SISTEMA ENCERRADO ---");    
    }  
}