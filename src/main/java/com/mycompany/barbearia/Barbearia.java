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
        
        
        
        System.out.println("--- INICIANDO SISTEMA DE BARBEARIA (MODO DE TESTE) ---");
        
        // --- 1. INICIALIZAÇÃO DOS SINGLETONS ---
     
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
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        LocalDate hoje = LocalDate.now();
        

        
        try {
            System.out.println("\n--- Cadastrando dados de setup ---");
            
            // A. Cadastrar Clientes
            gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
            gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
            Cliente clienteItalo = gestaoC.buscarCPF("11111111111");
            
            // B. Cadastrar Usuários
            gestaoU.cadastrar("atendente_pedro", "pedro123", "pedro", "33333333333", "38998090957", data1, TipoUsuario.ATENDENTE);
            gestaoU.cadastrar("barbeiro_marcos", "marcos123", "marcos", "44444444444", "38998090957", data1, TipoUsuario.BARBEIRO);
            gestaoU.cadastrar("barbeiro_antonio", "antonio123", "antonio", "55555555555", "38998090957", data1, TipoUsuario.BARBEIRO);
            gestaoU.cadastrar("gerente_joao", "joao1239", "joão", "00000000000", "38998090957", data1, TipoUsuario.GERENTE, "8181");
            
            Barbeiro barbeiroMarcos = (Barbeiro) gestaoU.buscarUsername("barbeiro_marcos");
            Atendente atendentePedro = (Atendente) gestaoU.buscarUsername("pedro123");
            Usuario gerenteJoao = gestaoU.buscarUsername("gerente_joao");
            
            // C. Cadastrar Serviços (em SLOTS de 10 min)
            gestaoS.cadastrar("Corte", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA); // 30 min
            gestaoS.cadastrar("Lavar", 20.0, "Lavagem simples", 1, TipoEstacao.LAVAGEM); // 10 min
            gestaoS.cadastrar("Barba", 15.00, "Corte de barba", 3, TipoEstacao.CORRIQUEIRA); // 30 min
            
            Servico servicoLavar = gestaoS.buscarPorId(gestaoS.buscarPorNome("Lavar").get(0).getId());
            Servico servicoCorte = gestaoS.buscarPorId(gestaoS.buscarPorNome("Corte").get(0).getId());
            
            // D. Cadastrar Produtos e Estoque (Para Venda na Loja)
            gestaoP.cadastrar("Pomada Modeladora", 25.00, "Pomada de alta fixação");
            Produto pomada = gestaoP.buscarPorId(gestaoP.buscarPorNome("Pomada modeladora").get(0).getId());
            gestaoEstoque.adicionarAoEstoque(pomada.getId(), 0); // Registra no inventário
            gestaoEstoque.aumentarQuantidade(pomada.getId(), 10); // Adiciona 10 ao estoque
            
            // E. Cadastrar Despesas 
            gestaoDES.lancarDespesas("Café do Mês", 150.00, hoje, TipoDespesa.CONSUMIVEIS, "Compra de café e açúcar", gerenteJoao);
            
            System.out.println("Setup concluído.");

            // --- 3. TESTES DE FLUXO FINANCEIRO (Onde tudo se conecta) ---
            System.out.println("\n--- INICIANDO TESTES DE FLUXO FINANCEIRO ---");

            // --- CENÁRIO 1: Fluxo de Pagamento 50% ---
            System.out.println("\n[Cenário 1]: Agendamento (< 14 dias) e Pagamento 50%");
            LocalDateTime horario1 = LocalDateTime.now().plusDays(5).withHour(10).withMinute(0).withSecond(0);
            ArrayList<Servico> servicosLavar = new ArrayList<>() {{ add(servicoLavar); }};

            // 1. Logística: Cria agendamento (Status: AGUARDANDO_PAGAMENTO)
            Agendamento ag1 = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoE.getEstacao(0), atendentePedro, servicosLavar, horario1, false);
            
            // 2. Finanças: Cria a "Conta" (OS)
            OrdemServico os1 = gestaoOS.cadastrar(clienteItalo, barbeiroMarcos, hoje, ag1);
            System.out.println("OS Criada: " + os1.getId() + " | Status Logístico: " + ag1.getStatus());
            System.out.printf("Valor Total da OS: R$ %.2f%n", os1.getValorTotalAPagar()); // Deve ser 20.00
            
            
            
            // 3. Finanças: Processa o Adiantamento 50%
            gestaoOS.processarPagamentoAdiantado(os1.getId());
            
            // 4. Verificação
           
            System.out.println("Status Logístico (Pós-Pagto): " + ag1.getStatus()); // Deve ser CONFIRMADO
            System.out.printf("Valor Adiantado: R$ %.2f%n", os1.getValorAdiantado_50pct()); // Deve ser 10.00
            System.out.printf("Valor Pendente: R$ %.2f%n", os1.getValorPendente()); // Deve ser 10.00
            
            // --- CENÁRIO 2: Add-on (Encaixe 10%) e Venda de Loja ---
            System.out.println("\n[Cenário 2]: Adicionar 'Corte' (Encaixe 10%) e 'Pomada' (Loja) à OS");
            LocalDateTime horario2 = horario1.plusMinutes(10); // 10:10
            ArrayList<Servico> servicosCorte = new ArrayList<>() {{ add(servicoCorte); }};

            // 1. Logística: Cria agendamento "add-on" (isEncaixe = true)
            Agendamento ag2_encaixe = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoE.getEstacao(1), atendentePedro, servicosCorte, horario2, true);
            
            // 2. Finanças: Adiciona o add-on à "Conta" (OS)
            gestaoOS.adicionarAgendamentoEmOS(os1.getId(), ag2_encaixe);
            System.out.println("Agendamento 'Encaixe' (add-on) adicionado à OS.");

            // 3. Finanças: Adiciona Venda da Loja
            gestaoOS.adicionarProdutoVendido(os1.getId(), pomada.getId(), 2); // 2 Pomadas (R$ 50.00)
            
            // 4. Verificação
            os1 = gestaoOS.buscarPorId(os1.getId());
            // Valor = 20 (Lavar) + (35 * 1.10) (Corte Encaixe) + (25 * 2) (Pomadas) = R$ 108.50
            System.out.printf("Valor Total FINAL da OS: R$ %.2f%n", os1.getValorTotalAPagar()); // Deve ser 108.50
            // Pendente = 108.50 - 10.00 (Adiantamento) = R$ 98.50
            System.out.printf("Valor Pendente FINAL da OS: R$ %.2f%n", os1.getValorPendente()); // Deve ser 98.50
            System.out.println("Estoque restante de Pomada: " + gestaoEstoque.getQuantidade(pomada.getId())); // Deve ser 8

            // --- CENÁRIO 3: Cancelamento (Com Taxa 35%) ---
            System.out.println("\n[Cenário 3]: Teste de Cancelamento (Com Taxa 35%)");
            LocalDateTime horarioTaxa = LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);
            Agendamento agTaxa = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoE.getEstacao(1), atendentePedro, servicosCorte, horarioTaxa, false);
            System.out.println(agTaxa.getStatus());
            OrdemServico osTaxa = gestaoOS.cadastrar(clienteItalo, barbeiroMarcos, hoje, agTaxa);
            
            // 1. Cancelar (Logística)
            System.out.println(agTaxa);
            Agendamento agCancelado = gestaoAGE.cancelarAgendamento(agTaxa.getId());
            
            // 2. Processar (Finanças)
            gestaoOS.processarCancelamentoFinanceiro(osTaxa.getId(), agCancelado);
            
            // 3. Verificar
            osTaxa = gestaoOS.buscarPorId(osTaxa.getId());
            System.out.println("Status Financeiro da OS: " + osTaxa.getStatus()); // Deve ser CANCELADO
            // Taxa = 35.00 * 0.35 = 12.25
            System.out.printf("Valor Taxa Cancelamento (35%%): R$ %.2f%n", osTaxa.getValorTaxaCancelamento_35pct()); // Deve ser 12.25
            System.out.printf("Valor Pendente (Multa): R$ %.2f%n", osTaxa.getValorPendente()); // Deve ser 12.25

            // --- CENÁRIO 4: Cancelamento (Sem Taxa - PRE_AGENDADO) ---
            System.out.println("\n[Cenário 4]: Teste de Cancelamento (Sem Taxa)");
            LocalDateTime horarioLongo = LocalDateTime.now().plusDays(20).withHour(11).withMinute(0);
            Agendamento agLongo = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoE.getEstacao(1), atendentePedro, servicosCorte, horarioLongo, false);
            OrdemServico osLongo = gestaoOS.cadastrar(clienteItalo, barbeiroMarcos, hoje, agLongo);
            System.out.println("Status Logístico (Longo): " + agLongo.getStatus()); // Deve ser PRE_AGENDADO

            agCancelado = gestaoAGE.cancelarAgendamento(agLongo.getId());
            gestaoOS.processarCancelamentoFinanceiro(osLongo.getId(), agCancelado);
            osLongo = gestaoOS.buscarPorId(osLongo.getId());
            System.out.printf("Valor Taxa Cancelamento (PRE_AGENDADO): R$ %.2f%n", osLongo.getValorTaxaCancelamento_35pct()); // Deve ser 0.0
            
            
            
            //CENÁRIO 5: FECHAMENTO DE CAIXA (Pagamento Final) ---
            
            System.out.println("\n[Cenário 5]: Fechamento de Caixa (Pagamento Final da OS1)");
            gestaoOS.processarPagamentoFinal(os1.getId());
            //os1 = gestaoOS.buscarPorId(os1.getId());
            System.out.println("Status Financeiro da OS1: " + os1.getStatus()); // Deve ser PAGO
            System.out.printf("Valor Pendente da OS1: R$ %.2f%n", os1.getValorPendente()); // Deve ser 0.00
            
            //CENÁRIO 6: TESTE DA LISTA DE ESPERA (PILHA LIFO) ---
            System.out.println("\n--- Teste 6: Lista de Espera (Pilha LIFO) ---");
            try {
                // 1. OBTER DADOS PARA O TESTE
                LocalDate dataOcupada = horario1.toLocalDate(); 
                Cliente clienteZeca = gestaoC.buscarCPF("22222222222"); 
                
                // 2. SIMULAR CLIENTES ENTRANDO NA LISTA DE ESPERA (LIFO)
                System.out.println("Adicionando 'Zeca' (Primeiro a entrar) na espera para " + dataOcupada);
                gestaoLE.adicionarClienteEspera(clienteZeca, servicosCorte, dataOcupada, null); // null = sem preferência
                
                System.out.println("Adicionando 'Italo' (Segundo a entrar) na espera para " + dataOcupada);
                gestaoLE.adicionarClienteEspera(clienteItalo, servicosCorte, dataOcupada, null);

                // 3. O ATENDENTE CONSULTA A PILHA (LIFO)
                System.out.println("... Vaga cancelada (simulação) ...");
                ListaEspera proximoDaPilha = gestaoLE.consultaProximoPilha(); // .peek()
                
                System.out.println("Atendente consultou a pilha. Próximo a ser chamado (LIFO): " + proximoDaPilha.getCliente().getNome());
                
                if (!proximoDaPilha.getCliente().getId().equals(clienteItalo.getId())) { // Comparando IDs
                    throw new Exception("Falha na lógica LIFO: O último (Italo) não foi o primeiro a sair.");
                }
                System.out.println("SUCESSO: A lógica LIFO (Pilha) está correta.");

                // 4. ATENDENTE REMOVE DA PILHA (simulando a confirmação)
                ListaEspera solicitacaoRemovida = gestaoLE.removerProximoPilha(); // .pop()
                System.out.println("Removido da pilha: " + solicitacaoRemovida.getCliente().getNome());
                
                // 5. Verificando o próximo (agora o Zeca)
                proximoDaPilha = gestaoLE.consultaProximoPilha();
                System.out.println("Próximo da pilha (agora): " + proximoDaPilha.getCliente().getNome()); // Deve ser Zeca

            } catch (Exception e) {
                System.err.println("TESTE 6 (LISTA DE ESPERA) FALHOU: " + e.getMessage());
            }

            // --- [RENUMERADO] CENÁRIO 7: TESTE DOS RELATÓRIOS FINANCEIROS ---
            System.out.println("\n--- Teste 7: GestaoFinanceira (Relatórios) ---");
            
            // 1. Relatório de Vendas do Dia (Req 35)
            // Deve somar a OS1 (R$ 108.50) e a OS_Taxa (R$ 12.25)
            String relatorioDiario = gestaoFIN.gerarRelatorioVendasDiario(hoje);
            System.out.println(relatorioDiario); 
            // (Receita Total = 108.50 + 12.25 = 120.75)

            // 2. Balanço Mensal (Req 36) - (Restrito ao Gerente)
            System.out.println("\n--- Teste 7b: Balanço Mensal (Restrito ao Gerente) ---");
            int mesAtual = hoje.getMonthValue();
            int anoAtual = hoje.getYear();
            String balanco = gestaoFIN.gerarBalancoMensal(mesAtual, anoAtual, gerenteJoao);
            System.out.println(balanco);
            // (Balanço = Receita[120.75] - Despesa[150.00] = -29.25)
            
           String nota = gestaoFIN.gerarNotaCliente(clienteItalo);
           System.out.println(nota);
            


            // --- [RENUMERADO] CENÁRIO 8: TESTE DE BUSCA DE VAGAS ---
            System.out.println("\n--- Teste 8: Imprimindo Vagas Disponíveis (Amanhã) ---");
            LocalDate dataDaBusca = LocalDate.now().plusDays(1);
            ArrayList<Agenda> vagasEncontradas = gestaoAGE.buscarHorarioVagoAgendamento(servicosCorte, dataDaBusca);
            System.out.println("--- Vagas Encontradas para 'Corte' em " + dataDaBusca + " ---");
            
            if (vagasEncontradas.isEmpty()) {
                System.out.println("Nenhuma vaga (combinação de barbeiro/estação) foi encontrada.");
            } else {
                DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
                int i = 1;
                for (Agenda vaga : vagasEncontradas) {
                    System.out.printf(
                        "%d. Horário: %s | Barbeiro: %s | Estação: %s%n",
                        i++,
                        vaga.horario().format(formatadorHora),
                        vaga.barbeiro().getNome(),
                        vaga.estacao().getNome()
                    );
                }
            }
            System.out.println("-------------------------------------------------");
            
        } catch (Exception m) {
            System.err.println("!!! OCORREU UM ERRO INESPERADO NO TESTE: " + m.getMessage());
        }
    }  
}