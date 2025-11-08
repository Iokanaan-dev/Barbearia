<<<<<<< HEAD
=======
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//
>>>>>>> origin/feature/PontoUsuario
package com.mycompany.barbearia;

import Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import Utilidades.TipoDespesa;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
<<<<<<< HEAD
    
    public static int totalOrdensServico() {
        return OrdemServico.getContador();
    }
    
=======
//
    /**
     *
     * @param args
     */
>>>>>>> origin/feature/PontoUsuario
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
<<<<<<< HEAD
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
            Cliente clienteItalo = gestaoC.buscarPorId("CL1");
            
            // B. Cadastrar Usuários
            gestaoU.cadastrar("atendente_pedro", "pedro123", "pedro", "33333333333", "38998090957", data1, "Atendente");
            gestaoU.cadastrar("barbeiro_marcos", "marcos123", "marcos", "44444444444", "38998090957", data1, "Barbeiro");
            gestaoU.cadastrar("barbeiro_antonio", "antonio123", "antonio", "55555555555", "38998090957", data1, "Barbeiro");
            gestaoU.cadastrar("gerente_joao", "joao1239", "joão", "00000000000", "38998090957", data1, "Gerente", "8181");
            
            Barbeiro barbeiroMarcos = (Barbeiro) gestaoU.buscarPorId("BA1");
            Atendente atendentePedro = (Atendente) gestaoU.buscarPorId("AT1");
            Usuario gerenteJoao = gestaoU.buscarPorId("GE1");
            
            // C. Cadastrar Serviços (em SLOTS de 10 min)
            gestaoS.cadastrar("Corte", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA); // 30 min
            gestaoS.cadastrar("Lavar", 20.0, "Lavagem simples", 1, TipoEstacao.LAVAGEM); // 10 min
            gestaoS.cadastrar("Barba", 15.00, "Corte de barba", 3, TipoEstacao.CORRIQUEIRA); // 30 min
            
            Servico servicoLavar = gestaoS.buscarPorId("SE2");
            Servico servicoCorte = gestaoS.buscarPorId("SE1");
            
            // D. Cadastrar Produtos e Estoque (Para Venda na Loja)
            gestaoP.cadastrar("Pomada Modeladora", 25.00, "Pomada de alta fixação");
            Produto pomada = gestaoP.buscarPorId("PO1");
            gestaoEstoque.cadastrar(pomada.getId(), 0); // Registra no inventário
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
            OrdemServico os1 = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, hoje, ag1);
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
            OrdemServico osTaxa = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, hoje, agTaxa);
            
            // 1. Cancelar (Logística)
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
            OrdemServico osLongo = gestaoOS.criarOrdemDeServico(clienteItalo, barbeiroMarcos, hoje, agLongo);
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
                Cliente clienteZeca = gestaoC.buscarPorId("CL2");
                
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
                e.printStackTrace();
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
            


            /* --- [RENUMERADO] CENÁRIO 8: TESTE DE BUSCA DE VAGAS ---
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
            */
        } catch (Exception m) {
            System.err.println("!!! OCORREU UM ERRO INESPERADO NO TESTE: " + m.getMessage());
            m.printStackTrace();
        }
    }  
}
=======
        GestaoPonto gestaoPo = GestaoPonto.getInstancia();
//        
//        // talvez valha a pena criar uma classe com nome de Sistema (acho que vale muito kkkkkkkkkkkk)
//        //para ser instanciada ao inves de instanciar item a item do pacote gestao (tudo é instanciado por meio do metodo de cadastro agora)
//        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
//
//
        try {
            
            
            

////        
////         TESTES CLIENTES---------------------------------------------------
////        
////        // instancia 4 clientes para testes
////        gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
////        gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
////        gestaoC.cadastrar("Joao", "33333333333", "387382389", data1, "receba@melhordomundo.com");
////        gestaoC.cadastrar("Italo", "44444444444", "000820483", data1, "borabill@gmail.com");
////        gestaoC.cadastrar("Tobias", "55555555555", "000642483", data1, "nobruapelao@frifai.com");
////       
////        //Exibindo todos os clientes
////        System.out.println("Lista de clientes inicial");
////        gestaoC.printLista();
////
////  
////      // GestaoClientes: teste para metodo de busca via nome ====================
////        System.out.println("Cliente com ID 'CL3'");
////        gestaoC.printPorId("CL3");
////        
////        System.out.println("Buscando clientes chamados 'Italo'");
////       
////        //instancia um ArrayList para ser usado nos testes de buscas
////        ArrayList<Cliente> clientesSelecionados = gestaoC.buscarPorNome("Italo");
////        printArrayList(clientesSelecionados);
////        
////        // busca com elemento inexistente para testar a exeçao NoSuchElementException
////        System.out.println("Buscando clientes chamados 'XXX'");
////        clientesSelecionados = gestaoC.buscarPorNome("XXX");
////        printArrayList(clientesSelecionados);     
////     
////        
////      // GestaoClientes: teste para metodo de busca via id ====================
////       
////        System.out.println("Buscando clientes com ID 'CL3'");
////       
////        // instancia um cliente para ser usado no teste de busca por ID
////        Cliente clienteSelecionado = gestaoC.buscarPorId("CL3");
////        System.out.println(clienteSelecionado);
////        
////        // busca com elemento inexistente para testar a exeçao NoSuchElementException
////        System.out.println("Buscando clientes com ID 'XX'");
////        clienteSelecionado = gestaoC.buscarPorId("XX");
////        System.out.println(clienteSelecionado);
////        
////     
////      
////        System.out.println("Removendo cliente 'CL5'");
////        gestaoC.remover("CL5"); 
////        gestaoC.remover("XX"); //tentativa de remocao de cliente que nao existe
////        
////        System.out.println("Lista de clientes apos remocao do cliente com ID CL1");
////        todosClientes = gestaoC.getLista();
////        printArrayList(todosClientes);                 
////
//// 
////        
////        System.out.println("Tentando editar cliente CL2");
////        gestaoC.editar("CL2", "Cabuloso", "33333333333","38998090957", data1, "perebinha@gmail");
////        printArrayList(todosClientes);
//     
//        // TESTES USUARIOS---------------------------------------------------
        
        gestaoU.cadastrar("italof1ad", "123456789", "marcos", "33333333333", "38998090957", data1, "Barbeiro");
        gestaoPo.baterPonto("BA1", "Entrada");        
        gestaoU.cadastrar("italod1ad", "123456789", "antonio", "33333333333", "38998090957", data1, "Barbeiro");        
        gestaoU.cadastrar("italod3cd", "123456789", "pedro", "33333333333", "38998090957", data1, "Atendente");
        gestaoU.cadastrar("adm123123", "000000001", "joão", "33333333333", "38998090957", data1, "Gerente", "8181");

          
        
        System.out.println("\nLista de usuarios inicial");
        gestaoU.printLista();
        
       // AS BUSCAS ESTAO FUNCIONANDO 
       // Gestao de Usuarios: teste para busca por ID=============================
       //System.out.println("\nUsuario com ID AT1");
       //gestaoU.printPorId("AT1"); 
       // AS EDIÇOES ESTAO FUNCIONANDO
       // Gestao de Usuarios: teste para ediçao=============================
       // gestaoU.editar("adm123123", "000000001", "AT1", "gabriel vieira", "11111111111", "38998090957", data1);
       //gestaoU.editarUsuarioLogin("BA1", "italof1ad", "123456789", "marquin123", "123456789");

        //ESTAS BUSCAS POR CARGO ESTAO FUNCIONANDO        
        //System.out.print("Lista de gerentes inicial");
        //gestaoU.printListaFuncao("Gerente");
         
        //System.out.print("Lista de barbeiros inicial");
        //gestaoU.printListaFuncao("Barbeiro");

        //System.out.print("Lista de atendentes inicial");
        //gestaoU.printListaFuncao("Atendente"); 
                
        
        
//        // TESTES PONTOS USUARIOS---------------------------------------------
       

       for(int i=0; i<100000000; i++)
           System.out.print("");
       
       System.out.println("");
       
       gestaoPo.baterPonto("BA1", "Saida");
       gestaoPo.baterPonto("BA1", "Entrada");
       gestaoPo.baterPonto("BA1", "Saida");
       gestaoPo.baterPonto("BA1", "Entrada");
       gestaoPo.baterPonto("BA1", "Saida");       
       
       
       gestaoPo.printPontosUsuario("BA1");
       
       gestaoPo.printHorasUsuario("BA1");
        
        } 
        catch (Exception m)
        {
                m.printStackTrace();
        } 
        
    }
}    
////      // TESTES SERVICOS------------------------------------------------------
////        
////        gestaoS.cadastrar("Pintar", 99, "Uma bela de uma pintada", 7, TipoEstacao.LAVAGEM);
////        gestaoS.cadastrar("Lavar", 99, "Uma bela de uma pintada", 1, TipoEstacao.LAVAGEM);
////        gestaoS.cadastrar("Corte de cabelo", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA);
////        gestaoS.cadastrar("Corte de barba", 15.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA);        
////        gestaoS.cadastrar("Escova", 45.00, "Escova feminina", 1, TipoEstacao.CORRIQUEIRA);
////        gestaoS.cadastrar("Descoloracao", 80.00, "Tira a cor do cabelo", 9, TipoEstacao.CORRIQUEIRA);        
//////
////        System.out.println("\nLista de servicos inicial");
////        gestaoS.printLista();
////        
////        gestaoS.remover("SE1");
////        
////        System.out.println("\nLista de servicos apos remocao de 'SE1'");
////        gestaoS.printLista();
//        
////        
////      // TESTES PRODUTOS------------------------------------------------------
////        
////        gestaoP.cadastrar("Balm Madeira", 25.55, "Um balm com aroma amadeirado e refrescante");
////        gestaoP.cadastrar("Pomada Cobre", 15.55, "Pomada de fixação média com brilho suave");
////        gestaoP.cadastrar("Pó Descolorante", 10.99, "Pó que remove pigmentação dos fios");
////        gestaoP.cadastrar("Água Oxigenada", 5.50, "Solução oxidante para descoloração");
////        gestaoP.cadastrar("Pomada Prata", 18.75, "Pomada de alta fixação e brilho intenso");
////        gestaoP.cadastrar("Balm Mentolado", 22.00, "Balm refrescante com mentol e aloe vera");
////        gestaoP.cadastrar("Shampoo Anticaspa", 19.90, "Limpeza profunda e controle da oleosidade");
////        gestaoP.cadastrar("Condicionador Hidratante", 21.30, "Hidrata e facilita o desembaraço dos fios");
////        gestaoP.cadastrar("Pomada Negra", 17.60, "Pomada com efeito matte e aroma suave");
////        gestaoP.cadastrar("Balm Citrus", 23.40, "Balm com fragrância cítrica revitalizante");
////        gestaoP.cadastrar("Óleo de Barba", 29.99, "Nutre e amacia os fios da barba");
////
////        System.out.println("Lista de produtos inicial");
////        gestaoP.printLista();
//        
//        // testa a remoçao de produtos   
//        //System.out.println("Lista de produtos apos remocao de 'PO2'");
//        //gestaoP.remover("PO2");
//        //gestaoP.printLista();
//        
//        //testa a busca por nomes
////        gestaoP.printPorNome("Pomada");
//        
////        
////        
////      //TESTES ORDENS DE SERVICO
//        //gestaoOS.cadastrar("CL1", "BA1", "Corte de Cabelo e Barba", data1);
//       // gestaoOS.cadastrar("CL1", "BA1", "Sombrancelha e Barba", data1);
//        //gestaoOS.cadastrar("CL3", "BA2", data1);
//        //gestaoOS.cadastrar("CL1", "BA1", data1);
//       // gestaoOS.cadastrar("CL2", "BA2", data1);
//       // gestaoOS.cadastrar("CL2", "BA1", data1);
//       // gestaoOS.cadastrar("CL1", "BA2", data1);        
//        
//        //System.out.println("Lista de ordens de servico inicial");
//        //gestaoOS.printLista();
//
////        // Teste adicao de servicos a OS OS3
//       //gestaoOS.adcionarServico("OS1", "SE2");
//      // gestaoOS.adcionarServico("OS1", "SE3");
//      // System.out.println("Lista de ordens de servico apos adiçao de servicos em 'OS1'");       
//       //gestaoOS.printLista();
//       
////        // Teste adicao de produtos a OS OS3       
//       //gestaoOS.adcionarProduto("OS3", "PO3");
//       //gestaoOS.adcionarProduto("OS3", "PO4");
//       //gestaoOS.editar("OS3", "Descoloraçao de cabelo, sombrancelha e barba");       
//       //System.out.println("Lista de ordens de servico apos adiçao de produtos e observacoes em 'OS3'");       
//       //gestaoOS.printLista();         
////        
////        // Testa a associacao de OSs ao cliente CL1 e CL2        
//       //System.out.println("Lista de ordens de servico associadas a CL1");
//       //gestaoOS.printListaOSCliente("CL1");
////        printArrayList(osSelecionadas);
////        
////        System.out.print("Lista de ordens de servico associadas a CL3");
////        osSelecionadas = gestaoOS.buscarOS("CL3");
////        printArrayList(osSelecionadas);    
//
//// GESTAO DE ESTOQUE AINDA NAO ESTA TESTADA!!!
//        
////TESTE DE AGENDAMENTO
//
////        //Usuario atendeteana = gestaoU.buscarPorNome("marcos").get(0); // é possível fazer assim tbm
////        Atendente atendente = (Atendente) gestaoU.buscarPorId("AT1");
////        Barbeiro barbeiro = (Barbeiro) gestaoU.buscarPorId("BA1");
////        Cliente cliente = gestaoC.buscarPorNome("Italo").get(0);
////        Estacao estacaoCorte = gestaoE.getEstacao(1); // "Corriqueira 1"
////        Servico servicoCorte = gestaoS.buscarPorNome("Corte").get(0);
////        ArrayList<Servico> servicosParaCorte = new ArrayList<>();
////        servicosParaCorte.add(servicoCorte);
////        boolean taxaDeEncaixe = true;
//        
//                
//// --- CENÁRIO 1: AGENDAMENTO BEM-SUCEDIDO ("Happy Path") ---
//        System.out.println("\n--- Teste 1: Agendamento 'Feliz' ---");
//        try {
//            // Marcar para amanhã às 10:00
//            LocalDateTime horario1 = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
//            
//            Agendamento ag1 = gestaoAGE.criarAgendamento(
//                cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horario1, taxaDeEncaixe
//            );
//            
//            System.out.println("SUCESSO: Agendamento criado!");
//            System.out.println(ag1);
//            
//        } catch (Exception e) {
//            System.err.println("TESTE 1 FALHOU: " + e.getMessage());
//        }
//
//        // --- CENÁRIO 2: AGENDAMENTO CONFLITANTE (Barbeiro Ocupado) ---
//        System.out.println("\n--- Teste 2: Conflito de Agendamento (Barbeiro) ---");
//        try {
//            // Tentar marcar no MESMO horário, MESMO barbeiro
//            LocalDateTime horario2 = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
//            
//            gestaoAGE.criarAgendamento(
//                cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horario2, false
//            );
//            System.err.println("TESTE 2 FALHOU: O sistema permitiu um agendamento conflitante.");
//            
//        } catch (Exception e) {
//            System.out.println("SUCESSO: Sistema bloqueou agendamento conflitante.");
//            System.out.println("   -> Mensagem: " + e.getMessage());
//        }
//        
//        // --- CENÁRIO 3: BUSCA DE HORÁRIOS (Verificando o "Motor da Agenda") ---
//        System.out.println("\n--- Teste 3: Buscar Vagas (Deve excluir 10:00) ---");
//        try {
//            LocalDate amanha = LocalDate.now().plusDays(1);
//            // Busca vagas para o serviço "Corte" (30 min)
//            ArrayList<Agenda> vagas = gestaoAGE.buscarHorarioVagoAgendamento(servicosParaCorte, amanha);
//            
//            System.out.println("Verificando vagas para " + amanha + "...");
//            boolean horarioOcupadoFoiListado = false;
//            for (Agenda vaga : vagas) {
//                // O horário de conflito é 10:00. 
//                // Um corte de 30 min (3 slots) não pode começar 9:40 ou 9:50.
//                if (vaga.horario().getHour() == 10 && vaga.horario().getMinute() == 0) {
//                    horarioOcupadoFoiListado = true;
//                }
//                if (vaga.horario().getHour() == 9 && vaga.horario().getMinute() >= 40) {
//                    horarioOcupadoFoiListado = true;
//                }
//            }
//            
//            if (horarioOcupadoFoiListado) {
//                System.err.println("TESTE 3 FALHOU: O horário ocupado (10:00) foi listado como vago."); 
//            } else {
//                System.out.println("SUCESSO: Motor da agenda filtrou o horário ocupado.");
//                System.out.println("   -> Total de vagas encontradas: " + vagas.size());
//            }
//            
//        } catch (Exception e) {
//            System.err.println("TESTE 3 FALHOU: " + e.getMessage());
//        }
//
//        // --- CENÁRIO 4: STATUS ---
//        System.out.println("\n--- Teste 4: Status e Cancelamento ---");
//        try {
//            // A. Agendamento Longo PRE_AGENDADO
//            LocalDateTime horarioLongo = LocalDateTime.now().plusDays(20).withHour(14).withMinute(0);
//            Agendamento agLongo = gestaoAGE.criarAgendamento(cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horarioLongo, false);
//            System.out.println("Status Agend. Longo (20 dias): " + agLongo.getStatus()); // Deve ser PRE_AGENDADO
//
//            // B. Agendamento Curto 
//            LocalDateTime horarioCurto = LocalDateTime.now().plusDays(5).withHour(15).withMinute(0);
//            Agendamento agCurto = gestaoAGE.criarAgendamento(cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horarioCurto, false);
//            System.out.println("Status Agend. Curto (5 dias): " + agCurto.getStatus()); // Deve ser CONFIRMADO
//            
//            // C. Cancelar agendamento longo 
//            gestaoAGE.cancelarAgendamento(agLongo.getId());
//            Agendamento agLongoCancelado = gestaoAGE.buscarAgendamentoID(agLongo.getId());
//            System.out.println("Cancelamento Longo (Status): " + agLongoCancelado.getStatus()); // CANCELADO
//
//
//            // D. Cancelar agendamento curto 
//            gestaoAGE.cancelarAgendamento(agCurto.getId());
//            Agendamento agCurtoCancelado = gestaoAGE.buscarAgendamentoID(agCurto.getId());
//            System.out.println("Cancelamento Curto (Status): " + agCurtoCancelado.getStatus()); // CANCELADO
//   
//
//        } catch (Exception e) {
//            System.err.println("TESTE 4 FALHOU: " + e.getMessage());
//        }
//        
//        System.out.println("\n--- FIM DOS TESTES ---");
// 
//        System.out.println("\n--- Teste 3b: Imprimindo Vagas Disponíveis ---");
//    try {
//    // 1. DEFINIR A BUSCA
//    // Para qual dia? Amanhã.
//    LocalDate dataDaBusca = LocalDate.now().plusDays(1);
//    
//    // Para quais serviços? Apenas "Corte" (30 min)
//    ArrayList<Servico> servicosBusca = new ArrayList<>();
//    servicosBusca.add(servicoCorte);
//
//    // 2. CHAMAR O "MOTOR DA AGENDA"
//    // Pedimos à GestaoAgendamento todas as opções válidas
//    ArrayList<Agenda> vagasEncontradas = gestaoAGE.buscarHorarioVagoAgendamento(
//        servicosBusca, 
//        dataDaBusca
//    );
//
//    // 3. IMPRIMIR O RESULTADO 
//    System.out.println("--- Vagas Encontradas para 'Corte' em " + dataDaBusca + " ---");
//    
//    if (vagasEncontradas.isEmpty()) {
//        System.out.println("Nenhuma vaga (combinação de barbeiro/estação) foi encontrada.");
//    } else {
//        // Criamos um formatador para mostrar a hora de forma amigável
//        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
//        
//        int i = 1;
//        for (Agenda vaga : vagasEncontradas) {
//            System.out.printf(
//                "%d. Horário: %s | Barbeiro: %s | Estação: %s%n",
//                i++,
//                vaga.horario().format(formatadorHora), // Pega o LocalDateTime e formata
//                vaga.barbeiro().getNome(),            // Pega o nome do Barbeiro
//                vaga.estacao().getNome()              // Pega o nome da Estação
//            );
//        }
//    }
//         System.out.println("-------------------------------------------------");
//
//        } catch (Exception e) {
//            System.err.println("TESTE 3b FALHOU: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        }
//        catch (Exception m)
//        {
//                m.printStackTrace();
//        }
//        
//    }  
//    
//
//}
>>>>>>> origin/feature/PontoUsuario
