/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import com.mycompany.barbearia.modelos.*;
import Gerenciamento.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * @author italo
 */
public class Barbearia {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        // Instanciando gestoes
        GestaoClientes gestaoC = GestaoClientes.getInstancia(); // usa singleton para obter instancia
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia(); // usa singleton para obter instancia 
        GestaoServico gestaoS = GestaoServico.getInstancia(); // usa singleton para obter instancia 
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        //GestaoAtendimento gestaoATE = GestaoAtendimento.getInstancia();
        GestaoAgendamento gestaoAGE = GestaoAgendamento.getInstancia();
        GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
        
        // talvez valha a pena criar uma classe com nome de Sistema (acho que vale muito kkkkkkkkkkkk)
        //para ser instanciada ao inves de instanciar item a item do pacote gestao (tudo é instanciado por meio do metodo de cadastro agora)
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);


        try {
        
        // TESTES CLIENTES---------------------------------------------------
        
//        // instancia 4 clientes para testes
        gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
        gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
        gestaoC.cadastrar("Joao", "33333333333", "387382389", data1, "receba@melhordomundo.com");
        gestaoC.cadastrar("Italo", "44444444444", "000820483", data1, "borabill@gmail.com");
        gestaoC.cadastrar("Tobias", "55555555555", "000642483", data1, "nobruapelao@frifai.com");
//       
//        //Exibindo todos os clientes
        System.out.println("Lista de clientes inicial");
        gestaoC.printLista();
//
//  
//      // GestaoClientes: teste para metodo de busca via nome ====================
        System.out.println("Cliente com ID 'CL3'");
        gestaoC.printPorId("CL3");
        
//        System.out.println("Buscando clientes chamados 'Italo'");
//       
//        //instancia um ArrayList para ser usado nos testes de buscas
//        ArrayList<Cliente> clientesSelecionados = gestaoC.buscarPorNome("Italo");
//        printArrayList(clientesSelecionados);
//        
//        // busca com elemento inexistente para testar a exeçao NoSuchElementException
//        System.out.println("Buscando clientes chamados 'XXX'");
//        clientesSelecionados = gestaoC.buscarPorNome("XXX");
//        printArrayList(clientesSelecionados);     
//     
//        
//      // GestaoClientes: teste para metodo de busca via id ====================
//       
//        System.out.println("Buscando clientes com ID 'CL3'");
//       
//        // instancia um cliente para ser usado no teste de busca por ID
//        Cliente clienteSelecionado = gestaoC.buscarPorId("CL3");
//        System.out.println(clienteSelecionado);
//        
//        // busca com elemento inexistente para testar a exeçao NoSuchElementException
//        System.out.println("Buscando clientes com ID 'XX'");
//        clienteSelecionado = gestaoC.buscarPorId("XX");
//        System.out.println(clienteSelecionado);
//        
//     
//      
//        System.out.println("Removendo cliente 'CL5'");
//        gestaoC.remover("CL5"); 
//        gestaoC.remover("XX"); //tentativa de remocao de cliente que nao existe
//        
//        System.out.println("Lista de clientes apos remocao do cliente com ID CL1");
//        todosClientes = gestaoC.getLista();
//        printArrayList(todosClientes);                 
//
// 
//        
//        System.out.println("Tentando editar cliente CL2");
//        gestaoC.editar("CL2", "Cabuloso", "33333333333","38998090957", data1, "perebinha@gmail");
//        printArrayList(todosClientes);
     
        // TESTES USUARIOS---------------------------------------------------
        
        gestaoU.cadastrar("italof1ad", "123456789", "marcos", "33333333333", "38998090957", data1, "Barbeiro");
        gestaoU.cadastrar("italod1ad", "123456789", "antonio", "33333333333", "38998090957", data1, "Barbeiro");        
        gestaoU.cadastrar("italod3cd", "123456789", "pedro", "33333333333", "38998090957", data1, "Atendente");
        gestaoU.cadastrar("adm123123", "000000001", "joão", "33333333333", "38998090957", data1, "Gerente", "8181");
          
        
        System.out.println("\nLista de usuarios inicial");
        gestaoU.printLista();
        
//       // Gestao de Usuarios: teste para busca por ID=============================
        
        System.out.println("\nUsuario com ID AT1");
        //gestaoU.printPorId("AT1"); 

        
        System.out.println("\nLista de Atendentes");
        gestaoU.printListaFuncao("Atendente");
        
        
//       // Gestao de Usuarios: teste para ediçao=============================

//        gestaoU.editar("adm123123", "000000001", "AT1", "gabriel vieira", "11111111111", "38998090957", data1);

//        ESTAS BUSCAS POR CARGO ESTAO FUNCIONANDO        
//        System.out.print("Lista de gerentes inicial");
//        todosUsuarios = gestaoU.getListaGerentes();
//        printArrayList(todosUsuarios); 
//        
//        System.out.print("Lista de barbeiros inicial");
//        todosUsuarios = gestaoU.getListaBarbeiros();
//        printArrayList(todosUsuarios);
//        
//        System.out.print("Lista de atendentes inicial");
//        todosUsuarios = gestaoU.getListaAtendentes();
//        printArrayList(todosUsuarios); 

//       // Gestao de Usuarios: teste para remoçao=============================
//        gestaoU.remover("BA1");
//        gestaoU.remover("BA2");
//        System.out.print("Lista de usuarios apos remoçao de 'BA1' e 'BA2'");
//        todosUsuarios = gestaoU.getLista();
//        printArrayList(todosUsuarios);        
        
        //System.out.println("\nDigite o ID do usuario\n");
        
        //gestaoU.editarUsuarioAtributos(novoUsuario0, "calabreso", "12755050667", "38998909068", data1);
        //gestaoU.editarUsuarioLogin(novoUsuario2, "italod3ab", "123456789", "carinhafeliz", "00000000");
        
//        
//        
//      // TESTES SERVICOS------------------------------------------------------
//        
        gestaoS.cadastrar("Pintar", 99, "Uma bela de uma pintada", 7, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Lavar", 99, "Uma bela de uma pintada", 1, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Corte de cabelo", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Corte de barba", 15.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA);        
        gestaoS.cadastrar("Escova", 45.00, "Escova feminina", 1, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Descoloracao", 80.00, "Tira a cor do cabelo", 9, TipoEstacao.CORRIQUEIRA);        
//
        System.out.println("\nLista de servicos inicial");
        gestaoS.printLista();
        
        gestaoS.remover("SE1");
        
        System.out.println("\nLista de servicos apos remocao de 'SE1'");
        gestaoS.printLista();
        
//        
//      // TESTES PRODUTOS------------------------------------------------------
//        
        gestaoP.cadastrar("Balm Madeira", 25.55, "Um balm com aroma amadeirado e refrescante");
        gestaoP.cadastrar("Pomada Cobre", 15.55, "Pomada de fixação média com brilho suave");
        gestaoP.cadastrar("Pó Descolorante", 10.99, "Pó que remove pigmentação dos fios");
        gestaoP.cadastrar("Água Oxigenada", 5.50, "Solução oxidante para descoloração");
        gestaoP.cadastrar("Pomada Prata", 18.75, "Pomada de alta fixação e brilho intenso");
        gestaoP.cadastrar("Balm Mentolado", 22.00, "Balm refrescante com mentol e aloe vera");
        gestaoP.cadastrar("Shampoo Anticaspa", 19.90, "Limpeza profunda e controle da oleosidade");
        gestaoP.cadastrar("Condicionador Hidratante", 21.30, "Hidrata e facilita o desembaraço dos fios");
        gestaoP.cadastrar("Pomada Negra", 17.60, "Pomada com efeito matte e aroma suave");
        gestaoP.cadastrar("Balm Citrus", 23.40, "Balm com fragrância cítrica revitalizante");
        gestaoP.cadastrar("Óleo de Barba", 29.99, "Nutre e amacia os fios da barba");

        System.out.println("Lista de produtos inicial");
        gestaoP.printLista();
        
        // testa a remoçao de produtos   
        //System.out.println("Lista de produtos apos remocao de 'PO2'");
        //gestaoP.remover("PO2");
        //gestaoP.printLista();
        
        //testa a busca por nomes
        gestaoP.printPorNome("Pomada");
        
//        
//        
//      //TESTES ORDENS DE SERVICO
        //gestaoOS.cadastrar("CL1", "BA1", "Corte de Cabelo e Barba", data1);
       // gestaoOS.cadastrar("CL1", "BA1", "Sombrancelha e Barba", data1);
        //gestaoOS.cadastrar("CL3", "BA2", data1);
        //gestaoOS.cadastrar("CL1", "BA1", data1);
       // gestaoOS.cadastrar("CL2", "BA2", data1);
       // gestaoOS.cadastrar("CL2", "BA1", data1);
       // gestaoOS.cadastrar("CL1", "BA2", data1);        
        
        //System.out.println("Lista de ordens de servico inicial");
        //gestaoOS.printLista();

//        // Teste adicao de servicos a OS OS3
       //gestaoOS.adcionarServico("OS1", "SE2");
      // gestaoOS.adcionarServico("OS1", "SE3");
      // System.out.println("Lista de ordens de servico apos adiçao de servicos em 'OS1'");       
       //gestaoOS.printLista();
       
//        // Teste adicao de produtos a OS OS3       
       //gestaoOS.adcionarProduto("OS3", "PO3");
       //gestaoOS.adcionarProduto("OS3", "PO4");
       //gestaoOS.editar("OS3", "Descoloraçao de cabelo, sombrancelha e barba");       
       //System.out.println("Lista de ordens de servico apos adiçao de produtos e observacoes em 'OS3'");       
       //gestaoOS.printLista();         
//        
//        // Testa a associacao de OSs ao cliente CL1 e CL2        
       //System.out.println("Lista de ordens de servico associadas a CL1");
       //gestaoOS.printListaOSCliente("CL1");
//        printArrayList(osSelecionadas);
//        
//        System.out.print("Lista de ordens de servico associadas a CL3");
//        osSelecionadas = gestaoOS.buscarOS("CL3");
//        printArrayList(osSelecionadas);    

// GESTAO DE ESTOQUE AINDA NAO ESTA TESTADA!!!
        
//TESTE DE AGENDAMENTO

        //Usuario atendeteana = gestaoU.buscarPorNome("marcos").get(0); // é possível fazer assim tbm
        Atendente atendente = (Atendente) gestaoU.buscarPorId("AT1");
        Barbeiro barbeiro = (Barbeiro) gestaoU.buscarPorId("BA1");
        Cliente cliente = gestaoC.buscarPorNome("Italo").get(0);
        Estacao estacaoCorte = gestaoE.getEstacao(1); // "Corriqueira 1"
        Servico servicoCorte = gestaoS.buscarPorNome("Corte").get(0);
        ArrayList<Servico> servicosParaCorte = new ArrayList<>();
        servicosParaCorte.add(servicoCorte);
        boolean taxaDeEncaixe = true;
        
                
// --- CENÁRIO 1: AGENDAMENTO BEM-SUCEDIDO ("Happy Path") ---
        System.out.println("\n--- Teste 1: Agendamento 'Feliz' ---");
        try {
            // Marcar para amanhã às 10:00
            LocalDateTime horario1 = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
            
            Agendamento ag1 = gestaoAGE.criarAgendamento(
                cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horario1, taxaDeEncaixe
            );
            
            System.out.println("SUCESSO: Agendamento criado!");
            System.out.println(ag1);
            
        } catch (Exception e) {
            System.err.println("TESTE 1 FALHOU: " + e.getMessage());
        }

        // --- CENÁRIO 2: AGENDAMENTO CONFLITANTE (Barbeiro Ocupado) ---
        System.out.println("\n--- Teste 2: Conflito de Agendamento (Barbeiro) ---");
        try {
            // Tentar marcar no MESMO horário, MESMO barbeiro
            LocalDateTime horario2 = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
            
            gestaoAGE.criarAgendamento(
                cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horario2, false
            );
            System.err.println("TESTE 2 FALHOU: O sistema permitiu um agendamento conflitante.");
            
        } catch (Exception e) {
            System.out.println("SUCESSO: Sistema bloqueou agendamento conflitante.");
            System.out.println("   -> Mensagem: " + e.getMessage());
        }
        
        // --- CENÁRIO 3: BUSCA DE HORÁRIOS (Verificando o "Motor da Agenda") ---
        System.out.println("\n--- Teste 3: Buscar Vagas (Deve excluir 10:00) ---");
        try {
            LocalDate amanha = LocalDate.now().plusDays(1);
            // Busca vagas para o serviço "Corte" (30 min)
            ArrayList<Agenda> vagas = gestaoAGE.buscarHorarioVagoAgendamento(servicosParaCorte, amanha);
            
            System.out.println("Verificando vagas para " + amanha + "...");
            boolean horarioOcupadoFoiListado = false;
            for (Agenda vaga : vagas) {
                // O horário de conflito é 10:00. 
                // Um corte de 30 min (3 slots) não pode começar 9:40 ou 9:50.
                if (vaga.horario().getHour() == 10 && vaga.horario().getMinute() == 0) {
                    horarioOcupadoFoiListado = true;
                }
                if (vaga.horario().getHour() == 9 && vaga.horario().getMinute() >= 40) {
                    horarioOcupadoFoiListado = true;
                }
            }
            
            if (horarioOcupadoFoiListado) {
                System.err.println("TESTE 3 FALHOU: O horário ocupado (10:00) foi listado como vago."); 
            } else {
                System.out.println("SUCESSO: Motor da agenda filtrou o horário ocupado.");
                System.out.println("   -> Total de vagas encontradas: " + vagas.size());
            }
            
        } catch (Exception e) {
            System.err.println("TESTE 3 FALHOU: " + e.getMessage());
        }

        // --- CENÁRIO 4: STATUS ---
        System.out.println("\n--- Teste 4: Status e Cancelamento ---");
        try {
            // A. Agendamento Longo PRE_AGENDADO
            LocalDateTime horarioLongo = LocalDateTime.now().plusDays(20).withHour(14).withMinute(0);
            Agendamento agLongo = gestaoAGE.criarAgendamento(cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horarioLongo, false);
            System.out.println("Status Agend. Longo (20 dias): " + agLongo.getStatus()); // Deve ser PRE_AGENDADO

            // B. Agendamento Curto 
            LocalDateTime horarioCurto = LocalDateTime.now().plusDays(5).withHour(15).withMinute(0);
            Agendamento agCurto = gestaoAGE.criarAgendamento(cliente, barbeiro, estacaoCorte, atendente, servicosParaCorte, horarioCurto, false);
            System.out.println("Status Agend. Curto (5 dias): " + agCurto.getStatus()); // Deve ser CONFIRMADO
            
            // C. Cancelar agendamento longo 
            gestaoAGE.cancelarAgendamento(agLongo.getId());
            Agendamento agLongoCancelado = gestaoAGE.buscarAgendamentoID(agLongo.getId());
            System.out.println("Cancelamento Longo (Status): " + agLongoCancelado.getStatus()); // CANCELADO


            // D. Cancelar agendamento curto 
            gestaoAGE.cancelarAgendamento(agCurto.getId());
            Agendamento agCurtoCancelado = gestaoAGE.buscarAgendamentoID(agCurto.getId());
            System.out.println("Cancelamento Curto (Status): " + agCurtoCancelado.getStatus()); // CANCELADO
   

        } catch (Exception e) {
            System.err.println("TESTE 4 FALHOU: " + e.getMessage());
        }
        
        System.out.println("\n--- FIM DOS TESTES ---");
 
        System.out.println("\n--- Teste 3b: Imprimindo Vagas Disponíveis ---");
    try {
    // 1. DEFINIR A BUSCA
    // Para qual dia? Amanhã.
    LocalDate dataDaBusca = LocalDate.now().plusDays(1);
    
    // Para quais serviços? Apenas "Corte" (30 min)
    ArrayList<Servico> servicosBusca = new ArrayList<>();
    servicosBusca.add(servicoCorte);

    // 2. CHAMAR O "MOTOR DA AGENDA"
    // Pedimos à GestaoAgendamento todas as opções válidas
    ArrayList<Agenda> vagasEncontradas = gestaoAGE.buscarHorarioVagoAgendamento(
        servicosBusca, 
        dataDaBusca
    );

    // 3. IMPRIMIR O RESULTADO 
    System.out.println("--- Vagas Encontradas para 'Corte' em " + dataDaBusca + " ---");
    
    if (vagasEncontradas.isEmpty()) {
        System.out.println("Nenhuma vaga (combinação de barbeiro/estação) foi encontrada.");
    } else {
        // Criamos um formatador para mostrar a hora de forma amigável
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
        
        int i = 1;
        for (Agenda vaga : vagasEncontradas) {
            System.out.printf(
                "%d. Horário: %s | Barbeiro: %s | Estação: %s%n",
                i++,
                vaga.horario().format(formatadorHora), // Pega o LocalDateTime e formata
                vaga.barbeiro().getNome(),            // Pega o nome do Barbeiro
                vaga.estacao().getNome()              // Pega o nome da Estação
            );
        }
    }
         System.out.println("-------------------------------------------------");

        } catch (Exception e) {
            System.err.println("TESTE 3b FALHOU: " + e.getMessage());
            e.printStackTrace();
        }

        }
        catch (Exception m)
        {
                m.printStackTrace();
        }
        
    }  
    

}
