package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Barbearia {

    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO SISTEMA DE BARBEARIA (MODO DE TESTE) ---");
        
        // --- 1. INICIALIZAÇÃO DOS SINGLETONS ---
        System.out.println("Inicializando serviços de gestão...");
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        GestaoServico gestaoS = GestaoServico.getInstancia();
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoEstoque gestaoEstoque = GestaoEstoque.getInstancia(); // (Presumi que você criou este)
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        GestaoAgendamento gestaoAGE = GestaoAgendamento.getInstancia();
        GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        LocalDate hoje = LocalDate.now();
        
        // --- 2. SETUP DE DADOS (Seu código, 100% mantido) ---
        try {
            System.out.println("\n--- Cadastrando dados de setup ---");
            // A. Clientes
            gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
            gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
            
            // B. Usuários
            gestaoU.cadastrar("atendente_pedro", "pedro123", "pedro", "33333333333", "38998090957", data1, "Atendente");
            gestaoU.cadastrar("barbeiro_marcos", "marcos123", "marcos", "44444444444", "38998090957", data1, "Barbeiro");
            gestaoU.cadastrar("barbeiro_antonio", "antonio123", "antonio", "55555555555", "38998090957", data1, "Barbeiro");

            // C. Serviços (Usando SLOTS de 10 min)
            gestaoS.cadastrar("Corte", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA); // 30 min
            gestaoS.cadastrar("Lavar", 20.0, "Lavagem simples", 1, TipoEstacao.LAVAGEM); // 10 min
            gestaoS.cadastrar("Barba", 15.00, "Corte de barba", 3, TipoEstacao.CORRIQUEIRA); // 30 min

            // D. Produtos e Estoque (Para Venda na Loja)
            gestaoP.cadastrar("Pomada Modeladora", 25.00, "Pomada de alta fixação");
            Produto pomada = gestaoP.buscarPorId("PO1"); // (Assumindo que o ID é PO1)
            gestaoEstoque.cadastrar(pomada.getId(), 0);
            gestaoEstoque.aumentarQuantidade(pomada.getId(), 10);// Adiciona 10 ao estoque
            
            System.out.println("Setup concluído.");

        // --- FIM DO SETUP ---
            
            System.out.println("\n--- INICIANDO TESTES DE FLUXO FINANCEIRO ---");

            // --- Objetos de Teste (Buscando os dados que cadastramos) ---
            Cliente clienteItalo = gestaoC.buscarPorId("CL1");
            Barbeiro barbeiroMarcos = (Barbeiro) gestaoU.buscarPorId("BA1");
            Atendente atendentePedro = (Atendente) gestaoU.buscarPorId("AT1");
            Estacao estacaoLavagem = gestaoE.getEstacao(0);
            Estacao estacaoCorte = gestaoE.getEstacao(1);
            Servico servicoLavar = gestaoS.buscarPorId("SE2");
            Servico servicoCorte = gestaoS.buscarPorId("SE1");
            
            ArrayList<Servico> servicosLavar = new ArrayList<>() {{ add(servicoLavar); }};
            ArrayList<Servico> servicosCorte = new ArrayList<>() {{ add(servicoCorte); }};

            // --- CENÁRIO 1: FLUXO DE PAGAMENTO (REGRA 50%) ---
            System.out.println("\n[Cenário 1]: Agendamento (< 14 dias) e Pagamento 50%");
            
            LocalDateTime horarioAgendado = LocalDateTime.now().plusDays(5).withHour(10).withMinute(0).withSecond(0);
            
            // 1. Criar Agendamento Logístico (deve virar AGUARDANDO_PAGAMENTO)
            Agendamento ag1 = gestaoAGE.criarAgendamento(
                clienteItalo, barbeiroMarcos, estacaoLavagem, atendentePedro, servicosLavar, horarioAgendado, false
            );
            
            // 2. Criar a "Conta" (OrdemServico)
            OrdemServico os1 = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, LocalDate.now(), ag1);
            System.out.println("OS Criada: " + os1.getId() + " | Status Logístico: " + ag1.getStatus()); // Deve ser AGUARDANDO_PAGAMENTO
            System.out.printf("Valor Total da OS: R$ %.2f%n", os1.getValorTotalAPagar()); // Deve ser 20.00
            
            // 3. Processar o Adiantamento de 50%
            gestaoOS.processarPagamentoAdiantado(os1.getId());
            
            // 4. VERIFICAR OS STATUS
            os1 = gestaoOS.buscarPorId(os1.getId());
            System.out.println("Status Logístico (Pós-Pagto): " + ag1.getStatus()); // Deve ser CONFIRMADO
            System.out.printf("Valor Adiantado na OS: R$ %.2f%n", os1.getValorAdiantado_50pct()); // Deve ser 10.00
            System.out.printf("Valor Pendente na OS: R$ %.2f%n", os1.getValorPendente()); // Deve ser 10.00
            
            // --- CENÁRIO 2: ADD-ON (ENCAIXE 10%) E VENDA DE LOJA ---
            System.out.println("\n[Cenário 2]: Adicionar 'Corte' (Encaixe) e 'Pomada' (Loja) à OS");
            
            // 1. Criar Agendamento "Encaixe" (add-on)
            LocalDateTime horarioEncaixe = horarioAgendado.plusMinutes(10); // 10:10
            Agendamento ag2_encaixe = gestaoAGE.criarAgendamento(
                clienteItalo, barbeiroMarcos, estacaoCorte, atendentePedro, servicosCorte, horarioEncaixe, true // <-- true = ENCAIXE
            );
            
            // 2. Adicionar o "Encaixe" à OS existente
            gestaoOS.adicionarAgendamentoEmOS(os1.getId(), ag2_encaixe);
            System.out.println("Agendamento 'Encaixe' adicionado à OS.");

            // 3. Adicionar Venda da Loja
            gestaoOS.adicionarProdutoVendido(os1.getId(), pomada.getId(), 2); // 2 Pomadas
            System.out.println("Venda de 2x " + pomada.getNome() + " registrada na OS.");

            // 4. VERIFICAR OS VALORES FINAIS
            os1 = gestaoOS.buscarPorId(os1.getId());
            // Valor = 20 (Lavar) + (35 * 1.10) (Corte Encaixe) + (25 * 2) (Pomadas)
            // Valor = 20 + 38.5 + 50 = R$ 108.50
            System.out.printf("Valor Total FINAL da OS: R$ %.2f%n", os1.getValorTotalAPagar()); // Deve ser 108.50
            // Pendente = 108.50 - 10.00 (Adiantamento) = R$ 98.50
            System.out.printf("Valor Pendente FINAL da OS: R$ %.2f%n", os1.getValorPendente()); // Deve ser 98.50
            System.out.println("Estoque restante de Pomada: " + gestaoEstoque.getQuantidade(pomada.getId())); // Deve ser 8

            // --- CENÁRIO 3: CANCELAMENTO (COM TAXA 35%) ---
            System.out.println("\n[Cenário 3]: Teste de Cancelamento (Com Taxa)");
            
            // 1. Criar um novo agendamento e OS (agendado para < 7 dias)
            LocalDateTime horarioTaxa = LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);
            Agendamento agTaxa = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, estacaoCorte, atendentePedro, servicosCorte, horarioTaxa, false);
            System.out.println(agTaxa.getStatus());
            OrdemServico osTaxa = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, hoje, agTaxa);
            
            // 2. Cancelar (Logística)
            Agendamento agCancelado = gestaoAGE.cancelarAgendamento(agTaxa.getId());
            
            // 3. Processar (Finanças)
            gestaoOS.processarCancelamentoFinanceiro(osTaxa.getId(), agCancelado);
            
            // 4. Verificar
            osTaxa = gestaoOS.buscarPorId(osTaxa.getId());
            System.out.println("Status Financeiro da OS: " + osTaxa.getStatus()); // Deve ser CANCELADO
            // Taxa = 35.00 * 0.35 = 12.25
            System.out.printf("Valor Taxa Cancelamento (35%%): R$ %.2f%n", osTaxa.getValorTaxaCancelamento_35pct()); // Deve ser 12.25
            System.out.printf("Valor Pendente (Multa): R$ %.2f%n", osTaxa.getValorPendente()); // Deve ser 12.25

            // --- CENÁRIO 4: CANCELAMENTO (SEM TAXA - PRE_AGENDADO) ---
            System.out.println("\n[Cenário 4]: Teste de Cancelamento (Sem Taxa)");
            
            // 1. Criar (agendado para > 14 dias)
            LocalDateTime horarioLongo = LocalDateTime.now().plusDays(20).withHour(11).withMinute(0);
            Agendamento agLongo = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, estacaoCorte, atendentePedro, servicosCorte, horarioLongo, false);
            OrdemServico osLongo = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, hoje, agLongo);
            System.out.println("Status Logístico (Longo): " + agLongo.getStatus()); // Deve ser PRE_AGENDADO

            // 2. Cancelar (Logística)
            agCancelado = gestaoAGE.cancelarAgendamento(agLongo.getId());
            
            // 3. Processar (Finanças)
            gestaoOS.processarCancelamentoFinanceiro(osLongo.getId(), agCancelado);
            
            // 4. Verificar
            osLongo = gestaoOS.buscarPorId(osLongo.getId());
            System.out.printf("Valor Taxa Cancelamento (PRE_AGENDADO): R$ %.2f%n", osLongo.getValorTaxaCancelamento_35pct()); // Deve ser 0.0
            
            // --- MANTER O SEU TESTE DE BUSCA DE VAGAS ---
            System.out.println("\n--- Teste 3b: Imprimindo Vagas Disponíveis (Amanhã) ---");
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
            m.printStackTrace();
        }
    }  
}