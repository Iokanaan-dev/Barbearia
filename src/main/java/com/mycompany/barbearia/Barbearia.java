package com.mycompany.barbearia;

import com.mycompany.date_Barbearia.GerenciadorDeArquivos;
import com.mycompany.date_Barbearia.Barbearia_date;
import com.mycompany.Gerenciamento.*;
import com.mycompany.Utilidades.*;
import com.mycompany.barbearia.modelos.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
    
    // (Seu método totalOrdensServico() está ótimo)
    
    public static void main(String[] args) throws Exception {
                
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        
        // 🔹 Carrega os dados existentes ou cria novos
        Barbearia_date dados = GerenciadorDeArquivos.carregar();
        GestaoClientes.inicializar(dados);
        GestaoUsuarios.inicializar(dados);
        GestaoServicos.inicializa(dados);
        GestaoDespesas.inicializa(dados);
        GestaoProdutos.inicializa(dados);
        GestaoEstoque.inicializa(dados);
        GestaoAgendamento.inicializa(dados);
        GestaoOrdemServico.inicializar(dados);
        GestaoListaEspera.inicializar(dados);
        GestaoPonto.inicializar(dados);

        // 🔹 Manipula normalmente
        
        GestaoClientes gestaoC = GestaoClientes.getInstancia();

        gestaoC.cadastrar("Italo", "11111111111", "99999999", data1, "italo@picles.com");
        gestaoC.cadastrar("Zeca", "22222222222", "728294729", data1, "borabill@oi.com");
        gestaoC.cadastrar("Maria", "33333333333", "988877766", data1, "maria@email.com");
        gestaoC.cadastrar("João", "44444444444", "977766655", data1, "joao@email.com");
        gestaoC.cadastrar("Ana", "55555555555", "966655544", data1, "ana@email.com");
        gestaoC.cadastrar("Carlos", "66666666666", "955544433", data1, "carlos@email.com");
        gestaoC.cadastrar("Fernanda", "77777777777", "944433322", data1, "fernanda@email.com");
        gestaoC.cadastrar("Bruno", "88888888888", "933322211", data1, "bruno@email.com");
        gestaoC.cadastrar("Juliana", "99999999999", "922211100", data1, "juliana@email.com");
        gestaoC.cadastrar("Ricardo", "10101010101", "911100099", data1, "ricardo@email.com");
        
        Cliente cliente1 = new Cliente("Felipe", "14141414141", "38997001313", data1, "felipe@email.com");
        Cliente cliente2 = new Cliente("Roberta", "15151515151", "38997001414", data1, "roberta@email.com");
        Cliente cliente3 = new Cliente("Gustavo", "16161616161", "38997001515", data1, "gustavo@email.com");
        Cliente cliente4 = new Cliente("Camila", "17171717171", "38997001616", data1, "camila@email.com");
        Cliente cliente5 = new Cliente("Leonardo", "18181818181", "38997001717", data1, "leonardo@email.com");
        Cliente cliente6 = new Cliente("Patrícia", "19191919191", "38997001818", data1, "patricia@email.com");
        Cliente cliente7 = new Cliente("Eduardo", "20202020202", "38997001919", data1, "eduardo@email.com");
        Cliente cliente8 = new Cliente("Beatriz", "21212121212", "38997002020", data1, "beatriz@email.com");
        Cliente cliente9 = new Cliente("Rafael", "22222222223", "38997002121", data1, "rafael@email.com");
        Cliente cliente10 = new Cliente("Larissa", "23232323232", "38997002222", data1, "larissa@email.com");
        
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

        gestaoC.printLista();

        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();

        gestaoU.cadastrar("barbeiro_andre", "andre123", "Andre", "11111111111", "38997000111", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_caio", "caio1234", "Caio", "22222222222", "38997000222", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_lucas", "lucas123", "Lucas", "33333333333", "38997000333", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_rafa", "rafa1234", "Rafa", "44444444444", "38997000444", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_diego", "diego123", "Diego", "55555555555", "38997000555", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_vini", "vini1234", "Vini", "66666666666", "38997000666", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_enzo", "enzo1234", "Enzo", "77777777777", "38997000777", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("barbeiro_otavio", "otavio123", "Otavio", "88888888888", "38997000888", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("gerente_mario", "mario123", "Mario", "99999999999", "38997000999", data1, TipoUsuario.GERENTE, "4321");
        gestaoU.cadastrar("gerente_clara", "clara123", "Clara", "10101010101", "38997001010", data1, TipoUsuario.GERENTE, "8765");
        gestaoU.cadastrar("atendente_lara", "lara1234", "Lara", "12121212121", "38997001111", data1, TipoUsuario.ATENDENTE);
        gestaoU.cadastrar("atendente_paulo", "paulo123", "Paulo", "13131313131", "38997001212", data1, TipoUsuario.ATENDENTE);

        Barbeiro usuario1 = new Barbeiro("barbeiro_thiago", "thiago1234", "Thiago", "14141414141", "38997001111", data1);
        Barbeiro usuario2 = new Barbeiro("barbeiro_marcos", "marcos5678", "Marcos", "15151515151", "38997002222", data1);
        Barbeiro usuario3 = new Barbeiro("barbeiro_daniel", "daniel9101", "Daniel", "16161616161", "38997003333", data1);
        Barbeiro usuario4 = new Barbeiro("barbeiro_rodrigo", "rodrigo2020", "Rodrigo", "17171717171", "38997004444", data1);
        Barbeiro usuario5 = new Barbeiro("barbeiro_matheus", "matheus3030", "Matheus", "18181818181", "38997005555", data1);
        Barbeiro usuario6 = new Barbeiro("barbeiro_victor", "victor4040", "Victor", "19191919191", "38997006666", data1);
        Barbeiro usuario7 = new Barbeiro("barbeiro_gabriel", "gabriel5050", "Gabriel", "20202020202", "38997007777", data1);
        Barbeiro usuario8 = new Barbeiro("barbeiro_renan", "renan6060", "Renan", "21212121212", "38997008888", data1);
        Barbeiro usuario9 = new Barbeiro("barbeiro_jorge", "jorge7070", "Jorge", "22222222222", "38997009999", data1);
        Barbeiro usuario10 = new Barbeiro("barbeiro_fabio", "fabio8080", "Fabio", "23232323232", "38997001010", data1);

        gestaoU.cadastrar(usuario1);
        gestaoU.cadastrar(usuario2);
        gestaoU.cadastrar(usuario3);
        gestaoU.cadastrar(usuario4);
        gestaoU.cadastrar(usuario5);
        gestaoU.cadastrar(usuario6);
        gestaoU.cadastrar(usuario7);
        gestaoU.cadastrar(usuario8);
        gestaoU.cadastrar(usuario9);
        gestaoU.cadastrar(usuario10);

        gestaoU.printLista();
        
GestaoServicos gestaoS = GestaoServicos.getInstancia();

        gestaoS.cadastrar("Luzes Masculinas", 120.0, "Técnica de clareamento parcial dos fios", 75, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Selagem Capilar", 100.0, "Reduz o frizz e dá brilho intenso", 60, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Escova Modeladora", 50.0, "Modelagem com secador e escova", 40, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Massagem Facial", 35.0, "Relaxamento e ativação da circulação facial", 20, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Limpeza de Pele", 70.0, "Limpeza profunda com extração de impurezas", 50, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Hidratação de Barba", 40.0, "Tratamento hidratante e revitalizante para barba", 25, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Corte Navalhado", 45.0, "Corte rente com navalha e acabamento preciso", 35, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Pigmentação Capilar", 90.0, "Restaura o tom natural do cabelo", 55, TipoEstacao.LAVAGEM);
        gestaoS.cadastrar("Corte com Tesoura", 50.0, "Corte artesanal feito totalmente com tesoura", 30, TipoEstacao.CORRIQUEIRA);
        gestaoS.cadastrar("Design de Cavanhaque", 25.0, "Modelagem e alinhamento do cavanhaque", 15, TipoEstacao.CORRIQUEIRA);
        
        Servico servico1 = new Servico("Corte Degradê", 55.0, "Corte moderno com transição suave de volumes", 35, TipoEstacao.CORRIQUEIRA);
        Servico servico2 = new Servico("Barba Italiana", 40.0, "Barba feita com toalha quente e navalha", 25, TipoEstacao.CORRIQUEIRA);
        Servico servico3 = new Servico("Corte Infantil", 45.0, "Corte especial para crianças, com acabamento suave", 30, TipoEstacao.CORRIQUEIRA);
        Servico servico4 = new Servico("Hidratação Capilar", 65.0, "Tratamento para revitalizar e hidratar os fios", 40, TipoEstacao.LAVAGEM);
        Servico servico5 = new Servico("Relaxamento Capilar", 90.0, "Reduz o volume dos fios sem alisar completamente", 60, TipoEstacao.LAVAGEM);
        Servico servico6 = new Servico("Cauterização", 110.0, "Reconstrução profunda dos fios danificados", 70, TipoEstacao.LAVAGEM);
        Servico servico7 = new Servico("Corte Social", 50.0, "Corte clássico e elegante para todas as idades", 25, TipoEstacao.CORRIQUEIRA);
        Servico servico8 = new Servico("Sobrancelha Masculina", 25.0, "Limpeza e definição das sobrancelhas", 15, TipoEstacao.CORRIQUEIRA);
        Servico servico9 = new Servico("Escova Progressiva", 130.0, "Alisamento capilar com efeito natural e duradouro", 90, TipoEstacao.LAVAGEM);
        Servico servico10 = new Servico("Tratamento Antiqueda", 85.0, "Fortalecimento do couro cabeludo e prevenção de queda", 45, TipoEstacao.LAVAGEM);

        gestaoS.cadastrar(servico1);
        gestaoS.cadastrar(servico2);
        gestaoS.cadastrar(servico3);
        gestaoS.cadastrar(servico4);
        gestaoS.cadastrar(servico5);
        gestaoS.cadastrar(servico6);
        gestaoS.cadastrar(servico7);
        gestaoS.cadastrar(servico8);
        gestaoS.cadastrar(servico9);
        gestaoS.cadastrar(servico10);

        gestaoS.printLista();
        
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();

        gestaoP.cadastrar("Pomada Modeladora Strong", 45.0, "Pomada de fixação forte e efeito fosco para penteados duradouros");
        gestaoP.cadastrar("Shampoo Antiqueda", 60.0, "Fortalece a raiz e reduz a queda de cabelo");
        gestaoP.cadastrar("Condicionador Hidratante", 55.0, "Hidrata e desembaraça os fios sem pesar");
        gestaoP.cadastrar("Óleo Capilar de Argan", 70.0, "Repara pontas duplas e dá brilho intenso aos cabelos");
        gestaoP.cadastrar("Gel Fixador Ultra", 40.0, "Gel com fixação máxima e brilho molhado");
        gestaoP.cadastrar("Espuma de Barbear Premium", 35.0, "Proporciona um barbear suave e sem irritações");
        gestaoP.cadastrar("Balm Pós-Barba Refrescante", 50.0, "Acalma a pele após o barbear e reduz vermelhidão");
        gestaoP.cadastrar("Shampoo para Barba", 45.0, "Limpa e hidrata a barba, mantendo os fios macios");
        gestaoP.cadastrar("Pente de Madeira Antiestático", 25.0, "Evita o frizz e ajuda a modelar barba e cabelo");
        gestaoP.cadastrar("Toalha de Rosto Premium", 30.0, "Toalha macia e de alta absorção para uso profissional");

        Produto produto1 = new Produto("Cera Capilar Matte", 48.0, "Cera de efeito seco e fixação média, ideal para penteados naturais");
        Produto produto2 = new Produto("Creme para Barbear Mentolado", 38.0, "Creme refrescante que amacia os fios e facilita o barbear");
        Produto produto3 = new Produto("Shampoo Anticaspa Fresh", 52.0, "Remove impurezas e combate a caspa mantendo o couro cabeludo saudável");
        Produto produto4 = new Produto("Loção Pós-Barba Classic", 44.0, "Hidrata e suaviza a pele após o barbear, com fragrância amadeirada");
        Produto produto5 = new Produto("Escova Modeladora Profissional", 65.0, "Escova térmica com cerdas firmes para finalização precisa");
        Produto produto6 = new Produto("Pó Modelador Capilar", 42.0, "Dá volume e textura aos fios com acabamento fosco e leve");
        Produto produto7 = new Produto("Tesoura Profissional de Corte", 95.0, "Tesoura de aço inox para cortes precisos e confortáveis");
        Produto produto8 = new Produto("Creme Hidratante Facial", 58.0, "Hidrata profundamente e reduz a oleosidade da pele");
        Produto produto9 = new Produto("Balm para Barba Citrus", 47.0, "Nutre e perfuma a barba com toque refrescante e leve");
        Produto produto10 = new Produto("Toalha de Algodão Premium", 32.0, "Toalha macia e altamente absorvente, ideal para uso profissional");


        gestaoP.cadastrar(produto1);
        gestaoP.cadastrar(produto2);
        gestaoP.cadastrar(produto3);
        gestaoP.cadastrar(produto4);
        gestaoP.cadastrar(produto5);
        gestaoP.cadastrar(produto6);
        gestaoP.cadastrar(produto7);
        gestaoP.cadastrar(produto8);
        gestaoP.cadastrar(produto9);
        gestaoP.cadastrar(produto10);

    

    gestaoP.printLista();
        
        
        
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();

//        gestaoOS.cadastrar(new OrdemServico(cliente1, barbeiro_andre, data1, "Corte degradê e barba completa"));
//        gestaoOS.cadastrar(new OrdemServico(cliente2, barbeiro_caio, data1, "Corte social simples"));
//        gestaoOS.cadastrar(new OrdemServico(cliente3, barbeiro_lucas, data1)); // usa o construtor sem observações
//        gestaoOS.cadastrar(new OrdemServico(cliente4, barbeiro_rafa, data1, "Corte + sobrancelha"));
//        gestaoOS.cadastrar(new OrdemServico(cliente5, barbeiro_diego, data1)); // sem observações
//        gestaoOS.cadastrar(new OrdemServico(cliente6, barbeiro_vini, data1, "Apenas barba com navalha"));
//        gestaoOS.cadastrar(new OrdemServico(cliente7, barbeiro_enzo, data1, "Corte americano e barba"));
//        gestaoOS.cadastrar(new OrdemServico(cliente8, barbeiro_otavio, data1)); // sem observações
//        gestaoOS.cadastrar(new OrdemServico(cliente9, barbeiro_andre, data1, "Corte infantil degradê"));
//        gestaoOS.cadastrar(new OrdemServico(cliente10, barbeiro_caio, data1, "Corte moderno com risco"));

        gestaoOS.printLista();
        



    
        /*
        // C. Cadastrar Serviços (em SLOTS de 10 min)
        GestaoServicos gestaoS = GestaoServicos.getInstancia();
        gestaoS.cadastrar("Corte", 35.00, "Corte geral", 3, TipoEstacao.CORRIQUEIRA);
                                                                            
        gestaoS.cadastrar("Lavar", 20.0, "Lavagem simples", 1, TipoEstacao.LAVAGEM); 
        gestaoS.cadastrar("Barba", 15.00, "Corte de barba", 3, TipoEstacao.CORRIQUEIRA); 
            
        Servico servicoLavar = gestaoS.buscarPorId(gestaoS.buscarPorNome("Lavar").get(0).getId());
        Servico servicoCorte = gestaoS.buscarPorId(gestaoS.buscarPorNome("Corte").get(0).getId());
        Servico servicoBarba = gestaoS.buscarPorId(gestaoS.buscarPorNome("Barba").get(0).getId());
            
        // D. Cadastrar Produtos e Estoque (Para Venda na Loja)
        GestaoProdutos gestaoP = GestaoProdutos.getInstancia();
        GestaoEstoque gestaoE = GestaoEstoque.getInstancia();
        gestaoP.cadastrar("Pomada Modeladora", 25.00, "Pomada de alta fixação");
        Produto pomada = gestaoP.buscarPorId(gestaoP.buscarPorNome("Pomada modeladora").get(0).getId());
        gestaoE.adicionarAoEstoque(pomada.getId(), 0); // Registra no inventário
        gestaoE.aumentarQuantidade(pomada.getId(), 10); // Adiciona 10 ao estoque
        
        LocalDate hoje = LocalDate.now();
        
        // E. Cadastrar Despesas
        GestaoDespesas gestaoDES = GestaoDespesas.getInstancia();
        gestaoDES.lancarDespesas("Café do Mês", 150.00, hoje, TipoDespesa.CONSUMIVEIS, "Compra de café e açúcar", gerenteJoao);

	// Declarando lista servicos
	LocalDateTime horario1 = LocalDateTime.now().plusDays(5).withHour(10).withMinute(0).withSecond(0);
        ArrayList<Servico> servicosLavar = new ArrayList<>() {{ add(servicoLavar); }};
        
        GestaoAgendamento gestaoAGE = GestaoAgendamento.getInstancia();
        GestaoOrdemServico gestaoOS = GestaoOrdemServico.getInstancia();
        GestaoEstacao gestaoES = GestaoEstacao.getInstancia();
	Agendamento ag1 = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoES.getEstacao(1), atendentePedro, servicosLavar, horario1, false, null);
        OrdemServico os1 = gestaoOS.cadastrar(clienteItalo, barbeiroMarcos, hoje, ag1);

        Cliente clienteZeca = gestaoC.buscarCPF("22222222222");
        GestaoListaEspera gestaoLE = GestaoListaEspera.getInstancia();
                
        // 2. SIMULAR CLIENTES ENTRANDO NA LISTA DE ESPERA (LIFO)

        gestaoLE.adicionarClienteEspera(clienteZeca, servicosLavar, null);
        
        gestaoLE.adicionarClienteEspera(clienteZeca, servicosLavar, null); // é possível cadastrar um mesmo cliente na lista de espera

        gestaoLE.adicionarClienteEspera(clienteItalo, servicosLavar, null);

        // 🔹 Salva as mudanças
        //dados.listaClientes.add(clienteItalo);
       // dados.listaClientes.add(clienteZaca);
        
        //dados.listaBarbeiros.add(barbeiroMarcos);
        //dados.listaAtendentes.add(atendentePedro);
        //dados.listaGerentes.add(gerenteJoao);
        
        //dados.listaServicos.add(servicoCorte);
        //dados.listaServicos.add(servicoBarba);
        //dados.listaServicos.add(servicoLavar);
        
        //dados.listaProdutos.add(pomada);
        
        //dados.listaAgendamentos.add(ag1);
        //dados.listaOrdensServico.add(os1);
        
        //dados.listaDeEspera = GestaoListaEspera.getInstancia().getPilhaEspera();
        //dados.estoque = GestaoEstoque.getInstancia().getEstoque();
        //dados.tabelaPonto = GestaoPonto.getInstancia().getTabela();
                
                
        //GerenciadorDeArquivos.salvar(dados); todos esses dados devem ser carregados dentro das gestões em seus metodos de cadastro 
    */
        }

    
}