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
import com.mycompany.barbearia.modelos.OrdemServico;
import com.mycompany.barbearia.modelos.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    
    public void Questao06(){        
        Barbeiro barbeiroNovo  = new Barbeiro("barbeiro_toni", "tonin1234", "Toninho", "14141414141", "38997001111", LocalDate.of(1981, 12, 2));
        Atendente atendenteNovo  = new Atendente("atendente_thiago", "thiago1234", "Thiago", "12323232323", "38997001111", LocalDate.of(1999, 11, 4));
        Gerente gerenteNovo  = new Gerente("gerente_sebastiao", "sebinho1234", "Sebastiao", "64646464675", "38997001111", LocalDate.of(1994, 5, 5), "12345");
        
        
        // cadastro de colaboradores e adm (gerente)
        gestaoU.cadastrar(barbeiroNovo);
        gestaoU.cadastrar(atendenteNovo);
        gestaoU.cadastrar(gerenteNovo);
        
        //edicao de colaborador
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
    
    public void Questao07() throws Exception{        
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

    public void Questao08() throws Exception{
    
        gestaoC.limparLista();
        gestaoU.limparLista();
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
        
        gestaoA.printLista();
        
        OrdemServico os1 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19), "Cliente gostou dos atendimentos");
        OrdemServico os2 = new OrdemServico(cliente2, usuario1, LocalDate.of(2026, 11, 19));
        OrdemServico os3 = new OrdemServico(cliente1, usuario1, LocalDate.of(2026, 11, 19));      
        
        gestaoOS.adicionarAgendamento(os1, agendamento1);
        gestaoOS.adicionarAgendamento(os1, agendamento2);
        gestaoOS.adicionarAgendamento(os1, agendamento3);
        gestaoOS.adicionarAgendamento(os2, agendamento4);
        gestaoOS.adicionarAgendamento(os3, agendamento5);
        
        System.out.println("===== OSs Cliente 01=====");
        gestaoOS.printListaOSCliente(cliente1);
        
        System.out.println("===== OSs Cliente 02=====");        
        gestaoOS.printListaOSCliente(cliente2);
        
    }
    
   public void Questao11(){
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
   
    public void Questao12(){
        System.out.println("\n===== Numero OS Instanciadas =====");        
        System.out.println(gestaoOS.getNumeroOS());
    }   
   
   

    
    public void Questao13(){
        
    }
    
    
}
