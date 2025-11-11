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
        GestaoServico.inicializa(dados);
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
        Cliente clienteZaca = gestaoC.buscarCPF("22222222222");
        Cliente clienteItalo = gestaoC.buscarCPF("11111111111");
       
        
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        gestaoU.cadastrar("atendente_pedro", "pedro123", "pedro", "33333333333", "38998090957", data1, TipoUsuario.ATENDENTE);
        gestaoU.cadastrar("barbeiro_marcos", "marcos123", "marcos", "44444444444", "38998090957", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("gerente_joao", "joao1239", "joão", "00000000000", "38998090957", data1, TipoUsuario.GERENTE, "8181");

        Barbeiro barbeiroMarcos = (Barbeiro) gestaoU.buscarUsername("barbeiro_marcos");
        Atendente atendentePedro = (Atendente) gestaoU.buscarUsername("pedro123");
        Gerente gerenteJoao = (Gerente) gestaoU.buscarUsername("gerente_joao");        
        
        // C. Cadastrar Serviços (em SLOTS de 10 min)
        GestaoServico gestaoS = GestaoServico.getInstancia();
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
	Agendamento ag1 = gestaoAGE.criarAgendamento(clienteItalo, barbeiroMarcos, gestaoES.getEstacao(1), atendentePedro, servicosLavar, horario1, false);
	OrdemServico os1 = gestaoOS.cadastrar(clienteItalo, barbeiroMarcos, hoje, ag1);

        //CENÁRIO 6: TESTE DA LISTA DE ESPERA (PILHA LIFO) ---
        System.out.println("\n--- Teste 6: Lista de Espera (Pilha LIFO) ---");

        LocalDate dataOcupada = horario1.toLocalDate(); 
        Cliente clienteZeca = gestaoC.buscarCPF("22222222222");
        GestaoListaEspera gestaoLE = GestaoListaEspera.getInstancia();
                
        // 2. SIMULAR CLIENTES ENTRANDO NA LISTA DE ESPERA (LIFO)
        System.out.println("Adicionando 'Zeca' (Primeiro a entrar) na espera para " + dataOcupada);
        gestaoLE.adicionarClienteEspera(clienteZeca, servicosLavar, dataOcupada, null); // null = sem preferência
                
        System.out.println("Adicionando 'Italo' (Segundo a entrar) na espera para " + dataOcupada);
        gestaoLE.adicionarClienteEspera(clienteItalo, servicosLavar, dataOcupada, null);

        // 🔹 Salva as mudanças
        dados.listaClientes.add(clienteItalo);
        dados.listaClientes.add(clienteZaca);
        
        dados.listaBarbeiros.add(barbeiroMarcos);
        dados.listaAtendentes.add(atendentePedro);
        dados.listaGerentes.add(gerenteJoao);
        
        dados.listaServicos.add(servicoCorte);
        dados.listaServicos.add(servicoBarba);
        dados.listaServicos.add(servicoLavar);
        
        dados.listaProdutos.add(pomada);
        
        dados.listaAgendamentos.add(ag1);
        dados.listaOrdensServico.add(os1);
        
        dados.listaDeEspera = GestaoListaEspera.getInstancia().getPilhaEspera();
        dados.estoque = GestaoEstoque.getInstancia().getEstoque();
        dados.tabelaPonto = GestaoPonto.getInstancia().getTabela();
                
                
        GerenciadorDeArquivos.salvar(dados);
    }
}