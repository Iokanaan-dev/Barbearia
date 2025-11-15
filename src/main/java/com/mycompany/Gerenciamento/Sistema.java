/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.StatusAgendamento;
import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.Utilidades.TipoUsuario;
import com.mycompany.barbearia.modelos.*;
import com.mycompany.compara.ComparatorTelefoneIndividuo;
import com.mycompany.compara.ComparatorDataNascimentoIndividuo;
import com.mycompany.compara.ComparatorTamanhoNomeIndividuo;
import com.mycompany.compara.ComparatorTempoServicosEmAgendamento;
import com.mycompany.compara.Find;
import com.mycompany.compara.Teste2;
import com.mycompany.date_Barbearia.Barbearia_date;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author intalo
 */
public class Sistema {
    
    GestaoClientes gestaoC = GestaoClientes.getInstancia();
    GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
    GestaoServicos gestaoS = GestaoServicos.getInstancia();
    GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
    GestaoAgendamentos gestaoA = GestaoAgendamentos.getInstancia();
    GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
    GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
    GestaoFinanceira gestaoF = GestaoFinanceira.getInstancia();
    GestaoEstoque gestaoES = GestaoEstoque.getInstancia();
    
    public void questao03(){
        gestaoC.limparLista();
        gestaoU.limparLista();
        gestaoS.limparLista();
        gestaoP.limparLista();
        
        Cliente clienteToString  = new Cliente("Felipe", "14141414141", "38997001313", LocalDate.of(1990, 8, 12), "felipe@email.com");
        Barbeiro barbeiroToString  = new Barbeiro("barbeiro_toni", "tonin1234", "Toninho", "14141414141", "38997001111", LocalDate.of(1981, 12, 2));
        Servico servicoToString = new Servico("Corte", 35.00, "Um corte social", 2, TipoEstacao.CORRIQUEIRA);
        Produto produtoToString = new Produto("Shampoo",15.00 ,25.00, "Shampoo cheiroso");
        
        System.out.println("===== Alguns Exemplos toString=====");
        System.out.println(clienteToString);
        System.out.println(barbeiroToString);
        System.out.println(servicoToString);
        System.out.println(produtoToString);  
    }
    
    public void questao06(){ 
        
        gestaoU.limparLista();
        
        Barbeiro barbeiroNovo  = new Barbeiro("barbeiro_toni", "tonin1234", "Toninho", "14141414141", "38997001111", LocalDate.of(1981, 12, 2));
        Atendente atendenteNovo  = new Atendente("atendente_thiago", "thiago1234", "Thiago", "12323232323", "38997001111", LocalDate.of(1999, 11, 4));
        Gerente gerenteNovo  = new Gerente("gerente_sebastiao", "sebinho1234", "Sebastiao", "64646464675", "38997001111", LocalDate.of(1994, 5, 5), "12345");
        
        // cadastro de colaboradores e adm (gerente)
        gestaoU.cadastrar(barbeiroNovo);
        gestaoU.cadastrar(atendenteNovo);
        gestaoU.cadastrar(gerenteNovo);
        
        //edicao de colaborador (funcao ADM)
        gestaoU.editar("gerente_sebastiao", "sebinho1234", atendenteNovo.getId(), "Thiago", "12323232323", "559292019", LocalDate.of(1999, 9, 3));
        gestaoU.editarUsuarioLogin(atendenteNovo.getId(), "atendente_thiago", "thiago1234", "thiagin_do_mel", "thiagin123");
        
        // remocao de colaborador (funcao ADM)
        Barbeiro barbeiroApagavel = new Barbeiro("barneiro_apagavel", "apagavel123", "Apagavel", "00000000000", "000000000", LocalDate.of(1900, 1, 1));
        gestaoU.cadastrar(barbeiroApagavel);
        gestaoU.remover("gerente_sebastiao", "sebinho1234", barbeiroApagavel);
        
        // print colaboradores
        System.out.println("===== Novos Usuarios Cadastrados=====");
        gestaoU.printLista();    
    }
    
    public void questao07() throws Exception{   
        
        gestaoC.limparLista();
        
        Cliente clienteNovo  = new Cliente("Isaias", "12312312312", "38997001111", LocalDate.of(2011, 11, 11), "isaias@gmail.com");
 
        // cadastro de cliente
        gestaoC.cadastrar(clienteNovo);
        
        //edicao de cliente
        gestaoC.editar(clienteNovo.getId(), "Isaquinho", "55555555555", "99749529", LocalDate.of(2012, 12, 12), "frescoball@gmail.com");
        
        // remocao de cliente
        Cliente clienteApagavel  = new Cliente("Apagavel", "00000000000", "00000000", LocalDate.of(1900, 1, 1), "apagavel@delete.com");
        gestaoC.cadastrar(clienteApagavel);     
        
        gestaoC.remover(clienteApagavel.getId());        
        
        // print clientes
        System.out.println("===== Novos Clientes Cadastrados=====");
        gestaoC.printLista();    
    }

    public void questao08() throws Exception{
        
        /*
              configura todas as informacoes necessarias para se cadastrar 2 novas ordens de servico
           */        
        gestaoC.limparLista();
        gestaoU.limparLista();
        gestaoA.limparLista();
        gestaoS.limparLista();
        gestaoOS.limparLista();
        
        Cliente cliente1  = new Cliente("Felipe", "14141414141", "38997001313", LocalDate.of(1990, 8, 12), "felipe@email.com");
        Cliente cliente2  = new Cliente("Roberta", "15151515151", "38997001414", LocalDate.of(1978, 11, 9), "roberta@email.com"); 
        
        Barbeiro usuario1  = new Barbeiro("barbeiro_thiago", "thiago1234", "Thiago", "14141414141", "38997001111", LocalDate.of(1990, 8, 12));
        Barbeiro usuario2  = new Barbeiro("barbeiro_marco", "marcos5678", "Marcos", "15151515151", "38997002222", LocalDate.of(1990, 8, 12));        
        Atendente usuario3 = new Atendente("atendente_fabio", "fabio8080", "Fabio", "23232323232", "38997001010", LocalDate.of(1990, 8, 12));

        Servico servico1  = new Servico("Corte Degradê", 55.0, "Corte moderno com transição suave de volumes", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico2  = new Servico("Barba Italiana", 40.0, "Barba feita com toalha quente e navalha", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico3  = new Servico("Corte Infantil", 45.0, "Corte especial para crianças, com acabamento suave", 4, TipoEstacao.CORRIQUEIRA);
        Servico servico4  = new Servico("Hidratação Capilar", 65.0, "Tratamento para revitalizar e hidratar os fios", 4, TipoEstacao.LAVAGEM);
        Servico servico5  = new Servico("Relaxamento Capilar", 90.0, "Reduz o volume dos fios sem alisar completamente", 5, TipoEstacao.LAVAGEM);  
        
        gestaoC.cadastrar(cliente1);
        gestaoC.cadastrar(cliente2);

        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);

        gestaoS.cadastrar(servico1);
        gestaoS.cadastrar(servico2);
        gestaoS.cadastrar(servico3);
        gestaoS.cadastrar(servico4);
        gestaoS.cadastrar(servico5);
        
        ArrayList<Servico> servicos1 = new ArrayList<>(List.of(servico1, servico2)); // CORRIQUEIRA
        ArrayList<Servico> servicos2 = new ArrayList<>(List.of(servico3)); // CORRIQUEIRA
        ArrayList<Servico> servicos3 = new ArrayList<>(List.of(servico4)); // LAVAGEM
        ArrayList<Servico> servicos4 = new ArrayList<>(List.of(servico5)); // LAVAGEM

        Agendamento agendamento1 = new Agendamento(cliente1, usuario1, usuario3, gestaoE.getEstacao(1), servicos1,
                LocalDateTime.of(2026, 11, 19, 10, 0), StatusAgendamento.CONFIRMADO, false, null);

        Agendamento agendamento2 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(0), servicos3,
                LocalDateTime.of(2026, 11, 13, 9, 0), StatusAgendamento.CONFIRMADO, false, null);

        Agendamento agendamento3 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(2), servicos2,
                LocalDateTime.of(2026, 11, 14, 14, 0), StatusAgendamento.AGUARDANDO_PAGAMENTO, false, null);

        Agendamento agendamento4 = new Agendamento(cliente2, usuario1, usuario3, gestaoE.getEstacao(0), servicos4,
                LocalDateTime.of(2026, 11, 15, 16, 0), StatusAgendamento.CONFIRMADO, false, null);
        Agendamento agendamento5 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(0), servicos4, 
                LocalDateTime.of(2027, 11, 15, 16, 0), StatusAgendamento.PRE_AGENDADO, false, null);

        gestaoA.cadastrar(agendamento1);
        gestaoA.cadastrar(agendamento2);
        gestaoA.cadastrar(agendamento3);
        gestaoA.cadastrar(agendamento4);
                
        OrdemServico os1 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19), "Cliente gostou dos atendimentos");
        OrdemServico os2 = new OrdemServico(cliente2, usuario1, LocalDate.of(2026, 11, 19));
        OrdemServico os3 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19));
        
        
        gestaoOS.cadastrar(os1, agendamento1);
        gestaoOS.adicionarAgendamento(os1, agendamento2);
        gestaoOS.adicionarAgendamento(os1, agendamento3);
        gestaoOS.cadastrar(os2, agendamento4);
        gestaoOS.cadastrar(os3, agendamento5);
        
        /*
            fim das configuracoes
           */        
        
        System.out.println("===== OSs Cliente 01=====");
        gestaoOS.printListaOSCliente(cliente1);
        
        System.out.println("===== OSs Cliente 02=====");        
        gestaoOS.printListaOSCliente(cliente2);
        
    }
    
    public void questao10() throws Exception{
        
        /*
              configura todas as informacoes necessarias para se cadastrar 2 novas ordens de servico
           */        
        gestaoC.limparLista();
        gestaoU.limparLista();
        gestaoA.limparLista();
        gestaoS.limparLista();        
        gestaoOS.limparLista();
        
        Cliente cliente1  = new Cliente("Felipe", "14141414141", "38997001313", LocalDate.of(1990, 8, 12), "felipe@email.com");
        Cliente cliente2  = new Cliente("Roberta", "15151515151", "38997001414", LocalDate.of(1978, 11, 9), "roberta@email.com"); 
        
        Barbeiro usuario1  = new Barbeiro("barbeiro_thiago", "thiago1234", "Thiago", "14141414141", "38997001111", LocalDate.of(1990, 8, 12));
        Barbeiro usuario2  = new Barbeiro("barbeiro_marco", "marcos5678", "Marcos", "15151515151", "38997002222", LocalDate.of(1990, 8, 12));        
        Atendente usuario3 = new Atendente("atendente_fabio", "fabio8080", "Fabio", "23232323232", "38997001010", LocalDate.of(1990, 8, 12));

        Servico servico1  = new Servico("Corte Degradê", 55.0, "Corte moderno com transição suave de volumes", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico2  = new Servico("Barba Italiana", 40.0, "Barba feita com toalha quente e navalha", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico3  = new Servico("Corte Infantil", 45.0, "Corte especial para crianças, com acabamento suave", 4, TipoEstacao.CORRIQUEIRA);
        Servico servico4  = new Servico("Hidratação Capilar", 65.0, "Tratamento para revitalizar e hidratar os fios", 4, TipoEstacao.LAVAGEM);
        Servico servico5  = new Servico("Relaxamento Capilar", 90.0, "Reduz o volume dos fios sem alisar completamente", 5, TipoEstacao.LAVAGEM);  
        
        gestaoC.cadastrar(cliente1);
        gestaoC.cadastrar(cliente2);

        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);

        gestaoS.cadastrar(servico1);
        gestaoS.cadastrar(servico2);
        gestaoS.cadastrar(servico3);
        gestaoS.cadastrar(servico4);
        gestaoS.cadastrar(servico5);
        
        ArrayList<Servico> servicos1 = new ArrayList<>(List.of(servico1, servico2)); // CORRIQUEIRA
        ArrayList<Servico> servicos2 = new ArrayList<>(List.of(servico3)); // CORRIQUEIRA
        ArrayList<Servico> servicos3 = new ArrayList<>(List.of(servico4)); // LAVAGEM
        ArrayList<Servico> servicos4 = new ArrayList<>(List.of(servico5)); // LAVAGEM

        Agendamento agendamento1 = new Agendamento(cliente1, usuario1, usuario3, gestaoE.getEstacao(1), servicos1,
                LocalDateTime.of(2026, 11, 19, 10, 0), StatusAgendamento.CONFIRMADO, false, null);

        Agendamento agendamento2 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(0), servicos3,
                LocalDateTime.of(2026, 11, 13, 9, 0), StatusAgendamento.CONFIRMADO, false, null);

        Agendamento agendamento3 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(2), servicos2,
                LocalDateTime.of(2026, 11, 14, 14, 0), StatusAgendamento.AGUARDANDO_PAGAMENTO, false, null);

        Agendamento agendamento4 = new Agendamento(cliente2, usuario1, usuario3, gestaoE.getEstacao(0), servicos4,
                LocalDateTime.of(2026, 11, 15, 16, 0), StatusAgendamento.CONFIRMADO, false, null);
        Agendamento agendamento5 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(0), servicos4, 
                LocalDateTime.of(2027, 11, 15, 16, 0), StatusAgendamento.PRE_AGENDADO, false, null);

        gestaoA.cadastrar(agendamento1);
        gestaoA.cadastrar(agendamento2);
        gestaoA.cadastrar(agendamento3);
        gestaoA.cadastrar(agendamento4);
                
        OrdemServico os1 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19), "Cliente gostou dos atendimentos");
        OrdemServico os2 = new OrdemServico(cliente2, usuario1, LocalDate.of(2026, 11, 19));
        OrdemServico os3 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19));
        
        
        gestaoOS.cadastrar(os1, agendamento1);
        gestaoOS.adicionarAgendamento(os1, agendamento2);
        gestaoOS.adicionarAgendamento(os1, agendamento3);
        gestaoOS.cadastrar(os2, agendamento4);
        gestaoOS.cadastrar(os3, agendamento5);   
        
        /*
            fim das configuracoes
           */ 
                
        System.out.println("===== Extrato Cliente 01=====");
        gestaoOS.processarPagamentoFinal(os1);
        System.out.println(gestaoF.gerarNotaCliente("14141414141"));
           
    }
    
   public void questao11(){
        System.out.println("===== Print Contador Private =====");
        System.out.printf("Numero de intancias de serviço: %d%n", GestaoServicos.getContadorPrivate());  
        
        System.out.println("\n===== Print Contador Protected =====");
        System.out.printf("Numero de intancias de serviço: %d%n", GestaoServicos.contadorProtected);  
        
      /*
            A grande vantagem do uso do modificador de acesso protected eh que
            ele aumenta o desempenho, pois nao eh necessario um metodo intermediario
            para o acesso a variavel de instancia. Entretanto, ele reduz a seguranca,
            ja que qualquer classe do pacote ou que seja subclasse pode acessar, como
            foi feito acima. O uso do modificador private tem como principal vantagem
            a seguranca proporcionada, pois existe um maior controle sobre como acessar.
            Para uma variavel que nao deva ser modificada, existe a possibilidade de
            nao deixar um metodo setter disponivel, o que impede acesso indevido,
            o que protected nao proporciona. Alem disso, caso seja possivel acessar e
            modifica-la via metodo setter, eh possivel validar a modificacao, impedindo
            dados inconsistentes. Notemos que, com o carater static da variavel, eh
            possivel acessa-la via qualquer classe desse pacote diretamente ou por
            seus objetos (tambem por subclasse e seus objetos, mas nao eh o caso
            para Servicos), o que pode ser perigoso.        
        */
        
   }
   
    public void questao12(){
        System.out.println("\n===== Numero OS Instanciadas =====");        
        System.out.println(gestaoOS.getNumeroOS());
    }   
       
    public void questao13() throws Exception{
        
        gestaoC.limparLista();
        gestaoU.limparLista();
        gestaoS.limparLista();
        gestaoA.limparLista();
        
        // cria 2 clientes
        Cliente cliente1  = new Cliente("Pitolomeu", "14141414141", "38997001313", LocalDate.of(1990, 10, 12), "pitin@email.com");
        Cliente cliente2  = new Cliente("Robertin", "15151515151", "38997001414", LocalDate.of(1990, 10, 12), "robertao@email.com"); 
        
        // cria o objeto que ira comparar as datas 
        ComparatorDataNascimentoIndividuo comparaDataNascimento = new ComparatorDataNascimentoIndividuo();
        
        // printa quem nasceu primeiro usando o compare personalizado
        System.out.println("\n===== Comparando data nascimento clientes =====");        
        comparaDataNascimento.quemNasceuPrimeiro(cliente1, cliente2);
        
        /*
              configura todas as informacoes necessarias para se cadastrar 2 novos novo agendamentos
           */
        Barbeiro usuario1  = new Barbeiro("barbeiro_thiago", "thiago1234", "Thiago", "14141414141", "38997001111", LocalDate.of(1990, 8, 12));
        Barbeiro usuario2  = new Barbeiro("barbeiro_marco", "marcos5678", "Marcos", "15151515151", "38997002222", LocalDate.of(1990, 8, 12));        
        Atendente usuario3 = new Atendente("atendente_fabio", "fabio8080", "Fabio", "23232323232", "38997001010", LocalDate.of(1990, 8, 12));
        Servico servico1  = new Servico("Corte Degradê", 55.0, "Corte moderno com transição suave de volumes", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico2  = new Servico("Barba Italiana", 40.0, "Barba feita com toalha quente e navalha", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico3  = new Servico("Hidratação Capilar", 65.0, "Tratamento para revitalizar e hidratar os fios", 4, TipoEstacao.LAVAGEM);
        
        gestaoC.cadastrar(cliente1);
        gestaoC.cadastrar(cliente2);
        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);
        gestaoS.cadastrar(servico1);
        gestaoS.cadastrar(servico2);
        gestaoS.cadastrar(servico3);
   
        ArrayList<Servico> servicos1 = new ArrayList<>(List.of(servico1, servico2)); // CORRIQUEIRA
        ArrayList<Servico> servicos2 = new ArrayList<>(List.of(servico3)); // LAVAGEM

        Agendamento agendamento1 = new Agendamento(cliente1, usuario1, usuario3, gestaoE.getEstacao(1), servicos1, LocalDateTime.of(2026, 11, 19, 10, 0), StatusAgendamento.CONFIRMADO, false, null);
        Agendamento agendamento2 = new Agendamento(cliente1, usuario2, usuario3, gestaoE.getEstacao(0), servicos2, LocalDateTime.of(2026, 11, 13, 9, 0), StatusAgendamento.CONFIRMADO, false, null);

        gestaoA.cadastrar(agendamento1);
        gestaoA.cadastrar(agendamento2);
        /*
            fim das configuracoes
           */
        
        // cria o objeto que ira comparar os tempos 
        ComparatorTempoServicosEmAgendamento comparaTempoAgendamento = new ComparatorTempoServicosEmAgendamento();
        
        // printa qual dura menos usando o compare personalizado
        System.out.println("\n===== Comparando tempo de atendimento =====");        
        comparaTempoAgendamento.qualDuraMenos(agendamento1, agendamento2);
    }
    
    public void questao14() throws Exception{
                                                

        //instancia Serviços
        Servico servico1  = new Servico("Corte Degradê", 55.0, "Corte moderno com transição suave de volumes", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico2  = new Servico("Barba Italiana", 40.0, "Barba feita com toalha quente e navalha", 3, TipoEstacao.CORRIQUEIRA);
        Servico servico3  = new Servico("Corte Infantil", 45.0, "Corte especial para crianças, com acabamento suave", 4, TipoEstacao.CORRIQUEIRA);
        Servico servico4  = new Servico("Hidratação Capilar", 65.0, "Tratamento para revitalizar e hidratar os fios", 4, TipoEstacao.LAVAGEM);
        Servico servico5  = new Servico("Relaxamento Capilar", 90.0, "Reduz o volume dos fios sem alisar completamente", 5, TipoEstacao.LAVAGEM);
        
        //cadastrando serviços
        gestaoS.cadastrar(servico1);
        gestaoS.cadastrar(servico2);
        gestaoS.cadastrar(servico3);
        gestaoS.cadastrar(servico4);
        gestaoS.cadastrar(servico5);
        
        ArrayList<Servico> servicos1 = new ArrayList<>(List.of(servico1, servico2)); // CORRIQUEIRA
        ArrayList<Servico> servicos2 = new ArrayList<>(List.of(servico3)); // CORRIQUEIRA
        ArrayList<Servico> servicos3 = new ArrayList<>(List.of(servico4)); // LAVAGEM
        ArrayList<Servico> servicos4 = new ArrayList<>(List.of(servico5)); // LAVAGEM 
        
        //cadastrando clientes
        gestaoC.cadastrar("Felipe", "14141414141", "38997001313", LocalDate.of(1990, 8, 12), "felipe@email.com");
        gestaoC.cadastrar("Roberta", "15151515151", "38997001414", LocalDate.of(1978, 11, 9), "roberta@email.com");
        
        //cadastrando usuarios
        gestaoU.cadastrar("barbeiro_thiago", "thiago1234", "Thiago", "14141414141", "38997001111", LocalDate.of(1990, 8, 12), TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_marco", "marcos5678", "Marcos", "15151515151", "38997002222", LocalDate.of(1990, 8, 12), TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("atendente_fabio", "fabio8080", "Fabio", "23232323232", "38997001010", LocalDate.of(1990, 8, 12), TipoUsuario.ATENDENTE);
        gestaoU.cadastrar("gerente_fernanda", "fernanda3210", "Fernanda", "19191919191", "38997006666", LocalDate.of(1995, 12, 2),TipoUsuario.GERENTE ,"1234");

        //cadastrar os produtos
        gestaoP.cadastrar("Shampoo", 15.00, 25.00, "Shampoo cheiroso");
        gestaoP.cadastrar("Condicionador",15.00 ,25.00, "Shampoo cheiroso");
        gestaoP.cadastrar("Mascara Hidratante",20.00 ,30.00, "Hidrada qualquer tipo de cabelo");
        
        Produto shampoo = gestaoP.buscarPorId(gestaoP.buscarPorNome("Shampoo").get(0).getId());
        Produto condicionador = gestaoP.buscarPorId(gestaoP.buscarPorNome("Condicionador").get(0).getId());
        Produto mascara = gestaoP.buscarPorId(gestaoP.buscarPorNome("Mascara Hidratante").get(0).getId());
        
        
        //cadastrando no Estoque
        gestaoES.cadastrarProdutoNoEstoque(shampoo.getId(), 10);
        gestaoES.cadastrarProdutoNoEstoque(condicionador.getId(), 10);
        gestaoES.cadastrarProdutoNoEstoque(mascara.getId(), 10);
        
        // adicionar agendamentos; ordem de serviço; lista de despesas; lista de relatorios; fila de espera;
        
        //salvando
        Barbearia_date dados = Barbearia_date.getInstancia();
        dados.salvar();
        
        
        
    }
    
    public void questao15P2() throws Exception{
        gestaoC.limparLista();
    
        // cria os clientes que vao ser iterados
        Cliente cliente1 = new Cliente("Lorenzo", "11111111111", "38997000001", LocalDate.of(1990, 2, 15), "lorenzo@email.com");
        Cliente cliente2 = new Cliente("Mirela", "22222222222", "38997000002", LocalDate.of(1998, 6, 3), "mirela@email.com");
        Cliente cliente3 = new Cliente("Caio", "33333333333", "38997000003", LocalDate.of(1985, 11, 20), "caio@email.com");
        Cliente cliente4 = new Cliente("Helena", "44444444444", "38997000004", LocalDate.of(2000, 1, 8), "helena@email.com");
        Cliente cliente5 = new Cliente("Benjamin", "55555555555", "38997000005", LocalDate.of(1993, 9, 27), "benjamin@email.com");
        Cliente cliente6 = new Cliente("Aurora", "66666666666", "38997000006", LocalDate.of(1987, 5, 13), "aurora@email.com");
        Cliente cliente7 = new Cliente("Otavio", "77777777777", "38997000007", LocalDate.of(2002, 4, 9), "otavio@email.com");
        Cliente cliente8 = new Cliente("Estela", "88888888888", "38997000008", LocalDate.of(1996, 10, 22), "estela@email.com");
        Cliente cliente9 = new Cliente("Noah", "99999999999", "38997000009", LocalDate.of(1991, 3, 5), "noah@email.com");
        Cliente cliente10 = new Cliente("Valentina", "10101010101", "38997000010", LocalDate.of(1999, 12, 30), "valentina@email.com");

        // Cadastro dos clientes para serem iterados
        gestaoC.cadastrar(cliente1);
        gestaoC.cadastrar(cliente2);
        gestaoC.cadastrar(cliente3);
        gestaoC.cadastrar(cliente4);
        gestaoC.cadastrar(cliente5);
        gestaoC.cadastrar(cliente6);
        gestaoC.cadastrar(cliente7);
        gestaoC.cadastrar(cliente8);
        gestaoC.cadastrar(cliente9);
        gestaoC.cadastrar(cliente10);

        // instancia do objeto iterator
        Iterator<Cliente> iterator = gestaoC.getListaCopia().iterator(); // linha 1

        System.out.println("\n===== Iterator sobre 10 clientes ====="); // linha 2        
        while(iterator.hasNext()) // linha 3
            System.out.println(iterator.next()); // linha 4


       /*
            A linha 1 chama o metodo iterator de Iterable, herdado por Collection, 
            para obter um Iterator para esse ArrayList que esta sendo retornado por 
            pelo metodo getListaCopia de gestaoC. A condicao de continuao de loop na
            linha 3 chama o metodo hasNext para determinar se existem mais 
            elementos para iterar. O metodo hasNext retorna true se outro elemento 
            existe e false caso contrario. 

            O foreach em Java eh um bloco de instrucoes que usa implicitamente o
            iterator, deixando o codigo mais legivel. Quando se usa foreach o que
            se esta fazendo na verdade eh utilizar o iterator "escondido". Esse
            codigo poderia ser escrito com foreach para obter o mesmo resultado.
            Eh interessante ressaltar, contudo, que como o foreach "esconde" 
            o iterator nao eh possivel usar metodos como remove por exemplo.
          */

        System.out.println("\n===== foreach sobre 10 clientes =====");     
        for(Cliente cliente :gestaoC.getListaCopia())
            System.out.println(cliente); 

    }
    
    public void questao16P2(){
        
        gestaoU.limparLista();
    
       /*
            OBS: O teste de comparacao sobre o tempo dos servicos em um 
            agendamento se encontra na questao 13
         */
       
        // configura todas as informacoes necessarias para se cadastrar novos usuarios e clientes
        Barbeiro usuario1  = new Barbeiro("barbeiro_ricardo", "ricardo12345", "Ricardo", "16161616161", "38997003333", LocalDate.of(1992, 3, 15));
        Barbeiro usuario2  = new Barbeiro("barbeiro_paulo", "paulo987654", "Paulo", "17171717171", "38997004444", LocalDate.of(1992, 3, 15));
        Atendente usuario3 = new Atendente("atendente_julia", "julia999999", "Julia", "18181818181", "38997005555", LocalDate.of(1995, 9, 8));
        Gerente usuario4   = new Gerente("gerente_fernanda", "fernanda3210", "Fernanda", "19191919191", "38997006666", LocalDate.of(1995, 12, 2), "1234");
        Barbeiro usuario5  = new Barbeiro("barbeiro_vini", "vini11223", "Vini", "20202020202", "38997007777", LocalDate.of(1994, 7, 11));
        Barbeiro usuario6  = new Barbeiro("barbeiro_leandro", "leandro55667", "Leandro", "21212121212", "38997008888", LocalDate.of(1989, 10, 4));       
        
        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);
        gestaoU.cadastrar(usuario4);
        gestaoU.cadastrar(usuario5);
        gestaoU.cadastrar(usuario6);
        
        ComparatorTamanhoNomeIndividuo comparaTamanhoNome = new ComparatorTamanhoNomeIndividuo();
        ComparatorDataNascimentoIndividuo comparaDataNascimento = new ComparatorDataNascimentoIndividuo();
        
        // comparacoes de nome
        System.out.println("\n===== Teste: Tamanho de Nome ====="); 
        System.out.println("Ricardo x Paulo"); 
        comparaTamanhoNome.quemTemMenorNome(usuario1, usuario2);
        
        System.out.println("\nPaulo x Ricardo"); 
        comparaTamanhoNome.quemTemMenorNome(usuario2, usuario1);   
        
        System.out.println("\nRicardo x Fernanda"); 
        comparaTamanhoNome.quemTemMenorNome(usuario1, usuario4);  
        
        System.out.println("\nJulia x Paulo"); 
        comparaTamanhoNome.quemTemMenorNome(usuario3, usuario2); 
        
        // comparacoes de data
        System.out.println("\n===== Teste: Dia de Nascimento ====="); 
        System.out.println("Ricardo x Paulo"); 
        comparaDataNascimento.quemNasceuPrimeiro(usuario1, usuario2);

        System.out.println("\nPaulo x Ricardo"); 
        comparaDataNascimento.quemNasceuPrimeiro(usuario2, usuario1);
        
        System.out.println("\nRicardo x Fernanda"); 
        comparaDataNascimento.quemNasceuPrimeiro(usuario1, usuario4);  
        
        System.out.println("\nJulia x Paulo"); 
        comparaDataNascimento.quemNasceuPrimeiro(usuario3, usuario2); 
       
        // impressao da lista desorganizada
        System.out.println("\nLista desorganizada:");
        gestaoU.printLista();
       
        // organiza por tamanho do nome
        Collections.sort(gestaoU.getListaReal(), comparaTamanhoNome);
       
        // imprime a lista organizada por tamanho do nome
        System.out.println("Lista organizada por tamanho do nome:");
        gestaoU.printLista();
        
        // organiza a lista por data de nascimento
        Collections.sort(gestaoU.getListaReal(), comparaDataNascimento);

        // imprime a lista organizada por data de nascimento
        System.out.println("Lista organizada por data de nascimento:");
        gestaoU.printLista();                 
    }
    
    public void questao17P2() throws Exception{
        
        /*
              configura todas as informacoes necessarias para se cadastrar 10 clientes
           */
        gestaoC.limparLista();

        Cliente cliente1  = new Cliente("Bruno Almeida",     "10110110110", "83451127",   LocalDate.of(1990, 3, 15),  "bruno.almeida@gmail.com");
        Cliente cliente2  = new Cliente("Mariana Souza",     "20220220220", "72984253",   LocalDate.of(1995, 7, 22),  "mariana.souza@gmail.com");
        Cliente cliente3  = new Cliente("Diego Martins",     "30330330330", "56490382",   LocalDate.of(1988, 11, 5),  "diego.martins@gmail.com");
        Cliente cliente4  = new Cliente("Juliana Pereira",   "40440440440", "91524336",   LocalDate.of(1993, 1, 27),  "juliana.pereira@gmail.com");
        Cliente cliente5  = new Cliente("Rafael Costa",      "50550550550", "48712590",   LocalDate.of(2000, 9, 10),  "rafael.costa@gmail.com");
        Cliente cliente6  = new Cliente("Carolina Dias",     "60660660660", "30197254",   LocalDate.of(1992, 4, 30),  "carolina.dias@gmail.com");
        Cliente cliente7  = new Cliente("Thiago Ramos",      "70770770770", "65842217",   LocalDate.of(1986, 6, 18),  "thiago.ramos@gmail.com");
        Cliente cliente8  = new Cliente("Larissa Prado",     "80880880880", "742551963",  LocalDate.of(1998, 12, 9),  "larissa.prado@gmail.com");
        Cliente cliente9  = new Cliente("Eduardo Silva",     "90990990990", "95168432",   LocalDate.of(1991, 2, 2),   "eduardo.silva@gmail.com");
        Cliente cliente10 = new Cliente("Gabriela Rocha",    "11211211211", "38426701",   LocalDate.of(1994, 8, 14),  "gabriela.rocha@gmail.com");
        Cliente cliente11 = new Cliente("Fernando Azevedo",  "11311311311", "95314750",   LocalDate.of(1987, 5, 19),  "fernando.azevedo@gmail.com");
        Cliente cliente12 = new Cliente("Isabela Moura",     "11411411411", "62975438",   LocalDate.of(1999, 3, 3),   "isabela.moura@gmail.com");
        Cliente cliente13 = new Cliente("Lucas Ferreira",    "11511511511", "74563091",   LocalDate.of(1996, 10, 7),  "lucas.ferreira@gmail.com");
        Cliente cliente14 = new Cliente("Patrícia Duarte",   "11611611611", "59036142",   LocalDate.of(1993, 9, 29),  "patricia.duarte@gmail.com");
        Cliente cliente15 = new Cliente("Hugo Santana",      "11711711711", "84276913",   LocalDate.of(1985, 11, 21), "hugo.santana@gmail.com");
        Cliente cliente16 = new Cliente("Bianca Vasconcelos","11811811811", "70392486",   LocalDate.of(2001, 4, 17),  "bianca.vasconcelos@gmail.com");
        Cliente cliente17 = new Cliente("Marcelo Tavares",   "11911911911", "97682043",   LocalDate.of(1990, 8, 8),   "marcelo.tavares@gmail.com");
        Cliente cliente18 = new Cliente("Renata Cardoso",    "12012012012", "43679815",   LocalDate.of(1997, 12, 25), "renata.cardoso@gmail.com");
        Cliente cliente19 = new Cliente("Igor Nascimento",   "12112112112", "51447962",   LocalDate.of(1989, 1, 4),   "igor.nascimento@gmail.com");
        Cliente cliente20 = new Cliente("Paula Menezes",     "12212212212", "89662054",   LocalDate.of(1994, 6, 12),  "paula.menezes@gmail.com");
        Cliente cliente21 = new Cliente("André Vinicius",    "12312312312", "24037871",   LocalDate.of(1998, 7, 16),  "andre.vinicius@gmail.com");
        Cliente cliente22 = new Cliente("Tatiane Pires",     "12412412412", "57384419",   LocalDate.of(1992, 5, 30),  "tatiane.pires@gmail.com");
        Cliente cliente23 = new Cliente("Rodrigo Lima",      "12512512512", "91846230",   LocalDate.of(1984, 9, 1),   "rodrigo.lima@gmail.com");
        Cliente cliente24 = new Cliente("Aline Castro",      "12612612612", "35672509",   LocalDate.of(1996, 2, 14),  "aline.castro@gmail.com");
        Cliente cliente25 = new Cliente("Vinícius Duarte",   "12712712712", "82931746",   LocalDate.of(1991, 3, 19),  "vinicius.duarte@gmail.com");
        Cliente cliente26 = new Cliente("João Pedro",        "12812812812", "45509127",   LocalDate.of(2002, 10, 22), "joaopedro@gmail.com");
        Cliente cliente27 = new Cliente("Luana Ribeiro",     "12912912912", "78453390",   LocalDate.of(1995, 4, 5),   "luana.ribeiro@gmail.com");
        Cliente cliente28 = new Cliente("Cláudio Torres",    "13013013013", "91247585",   LocalDate.of(1983, 8, 13),  "claudio.torres@gmail.com");
        Cliente cliente29 = new Cliente("Michele Andrade",   "13113113113", "65478331",   LocalDate.of(1997, 3, 11),  "michele.andrade@gmail.com");
        Cliente cliente30 = new Cliente("Gustavo Correia",   "13213213213", "98791624",   LocalDate.of(1990, 12, 6),  "gustavo.correia@gmail.com");

        gestaoC.cadastrar(cliente1);
        gestaoC.cadastrar(cliente2);
        gestaoC.cadastrar(cliente3);
        gestaoC.cadastrar(cliente4);
        gestaoC.cadastrar(cliente5);
        gestaoC.cadastrar(cliente6);
        gestaoC.cadastrar(cliente7);
        gestaoC.cadastrar(cliente8);
        gestaoC.cadastrar(cliente9);
        gestaoC.cadastrar(cliente10);
        gestaoC.cadastrar(cliente11);
        gestaoC.cadastrar(cliente12);
        gestaoC.cadastrar(cliente13);
        gestaoC.cadastrar(cliente14);
        gestaoC.cadastrar(cliente15);
        gestaoC.cadastrar(cliente16);
        gestaoC.cadastrar(cliente17);
        gestaoC.cadastrar(cliente18);
        gestaoC.cadastrar(cliente19);
        gestaoC.cadastrar(cliente20);
        gestaoC.cadastrar(cliente21);
        gestaoC.cadastrar(cliente22);
        gestaoC.cadastrar(cliente23);
        gestaoC.cadastrar(cliente24);
        gestaoC.cadastrar(cliente25);
        gestaoC.cadastrar(cliente26);
        gestaoC.cadastrar(cliente27);
        gestaoC.cadastrar(cliente28);
        gestaoC.cadastrar(cliente29);
        gestaoC.cadastrar(cliente30);

        /*
            fim das configuracoes
           */    
        
        // cria um objeto Find         
        Find find = new Find(); // MUDE O QUE FOR PRECISO PARA QUE SE BUSQUE UM CLIENTE NUMA LISTA E NAO POR UM TELEFONE
        
        System.out.println("\n===== Testes Find Telefone =====");         
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente1);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente2);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente5);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente6);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente27);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente28);
        find.printClienteEncontrado(gestaoC.getListaCopia(), cliente30); 
        
        // cria um objeto que ordena a lista pelo numero de telefone
        ComparatorTelefoneIndividuo comparaTelefone = new ComparatorTelefoneIndividuo();
        
        // ordena a lista
        Collections.sort(gestaoC.getListaReal(), comparaTelefone);
        
        gestaoC.printLista();
        
        System.out.println("\n===== Testes Find Telefone x Binary Search=====");         
                
        System.out.printf("Busca cliente 5  find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente5));
        System.out.printf("Busca cliente 5  binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente5, comparaTelefone));
        System.out.printf("Busca cliente 7  find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente7));
        System.out.printf("Busca cliente 7  binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente7, comparaTelefone));
        System.out.printf("Busca cliente 15 find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente15));
        System.out.printf("Busca cliente 15 binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente15, comparaTelefone));
        System.out.printf("Busca cliente 25 find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente25));
        System.out.printf("Busca cliente 25 binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente25, comparaTelefone));
        System.out.printf("Busca cliente 30 find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente30));
        System.out.printf("Busca cliente 30 binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente30, comparaTelefone));
        System.out.printf("Busca cliente 1  find:         %d%n", find.findViaTelefone(gestaoC.getListaReal(), cliente1));
        System.out.printf("Busca cliente 1  binarySearch: %d%n", Collections.binarySearch(gestaoC.getListaReal(), cliente1, comparaTelefone));             
    
    
        /*
            O metodo find eh util quando a lista de clientes nao esta ordenada,
            e quando as buscas nao sao frequentes. Funciona independentemente da
            ordem dos elementos e nao exige preparacao na lista. Apenas percorre 
            todos os itens ate achar o cliente desejado. Ja o binarySearch do 
            Java so pode ser usado quando a lista esta ordenada pois depende
            dessa ordenacao para funcionar. Quando a condicao de ordenar a lista
            eh atendida o binarySearch eh muito mais rapido, ideal para listas
            muito grandes.
            Nessa implementaçao o find funciona apenas para o ArrayList<Clientes>
            devido a como o sistema foi planejado com uso de ArrayLists para todas 
            as listas, o que eh uma desvantagem dessa implementaçao atual 
            mas conceitualmente o find poderia funcionar para mais tipos de 
            coleçoes que o binarySearch pois ele funciona em qualquer coleçao 
            que possa ser percorrida. Ja o bynarySearch eh utilizavel em Array ou
            List, desde que ordenadas.
            
           */
    }   
}