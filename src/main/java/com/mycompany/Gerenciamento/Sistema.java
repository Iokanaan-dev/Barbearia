/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.StatusAgendamento;
import com.mycompany.Utilidades.TipoEstacao;
import com.mycompany.barbearia.modelos.Agendamento;
import com.mycompany.barbearia.modelos.Atendente;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Cliente;
import com.mycompany.barbearia.modelos.Individuo;
import com.mycompany.barbearia.modelos.OrdemServico;
import com.mycompany.barbearia.modelos.Servico;
import com.mycompany.compara.DataNascimentoIndividuoComparator;
import com.mycompany.compara.TamanhoNomeIndividuoComparator;
import com.mycompany.compara.TempoServicosEmAgendamentoComparator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    GestaoAgendamentos gestaoA = GestaoAgendamentos.getInstancia();
    GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
    GestaoEstacao gestaoE = GestaoEstacao.getInstancia();
    
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
    
        gestaoC.limparLista();
        gestaoU.limparLista();
        gestaoA.limparLista();
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
        
        System.out.println("===== OSs Cliente 01=====");
        gestaoOS.printListaOSCliente(cliente1);
        
        System.out.println("===== OSs Cliente 02=====");        
        gestaoOS.printListaOSCliente(cliente2);
        
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
        DataNascimentoIndividuoComparator comparaDataNascimento = new DataNascimentoIndividuoComparator();
        
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
        TempoServicosEmAgendamentoComparator comparaTempoAgendamento = new TempoServicosEmAgendamentoComparator();
        
        // printa qual dura menos usando o compare personalizado
        System.out.println("\n===== Comparando tempo de atendimento =====");        
        comparaTempoAgendamento.qualDuraMenos(agendamento1, agendamento2);
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
        Gerente usuario4   = new Gerente("gerente_fernanda", "fernanda3210", "Fernanda", "19191919191", "38997006666", LocalDate.of(1995, 12, 2), "12345");
        Barbeiro usuario5  = new Barbeiro("barbeiro_vini", "vini11223", "Vini", "20202020202", "38997007777", LocalDate.of(1994, 7, 11));
        Barbeiro usuario6  = new Barbeiro("barbeiro_leandro", "leandro55667", "Leandro", "21212121212", "38997008888", LocalDate.of(1989, 10, 4));       
        
        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);
        gestaoU.cadastrar(usuario4);
        gestaoU.cadastrar(usuario5);
        gestaoU.cadastrar(usuario6);
        
        TamanhoNomeIndividuoComparator comparaTamanhoNome = new TamanhoNomeIndividuoComparator();
        DataNascimentoIndividuoComparator comparaDataNascimento = new DataNascimentoIndividuoComparator();
        
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
        
//        ArrayList<Individuo> lista = new ArrayList<>();
//        
//        lista.add(usuario1);
//        lista.add(usuario2);
//        lista.add(usuario3);
//        lista.add(usuario4);
//        lista.add(usuario5);
//        lista.add(usuario6);
//                
//        System.out.printf("Lista desorganizada: %n%s", lista);
//        
//        Collections.sort(lista, new TamanhoNomeIndividuoComparator());
//        System.out.printf("Lista organizada: %n%s", lista);

       System.out.println("\nLista desorganizada:");
       gestaoU.printLista();
       
       Collections.sort(gestaoU.getListaReal(), comparaTamanhoNome);
       
       System.out.println("Lista organizada por tamanho do nome:");
       gestaoU.printLista();
       
       Collections.sort(gestaoU.getListaReal(), comparaDataNascimento);
       
       System.out.println("Lista organizada por data de nascimento:");
       gestaoU.printLista();       
       
       
       
       
        
               
    }
    
}
