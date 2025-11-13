package com.mycompany.Gerenciamento;

import com.mycompany.Utilidades.StatusAtendimento;
import com.mycompany.Utilidades.StatusAgendamento;
import com.mycompany.barbearia.modelos.*; 
import com.mycompany.date_Barbearia.Barbearia_date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

/**
 * SERVIÇO FINANCEIRO. Gerencia as "Contas" (Ordens de Serviço).
 * Calcula taxas, processa pagamentos e cancelamentos.
 * @author italo
 */
public class GestaoOrdemServico extends Gestao<OrdemServico> {
    
    private static GestaoOrdemServico instancia;
    
    private final GestaoEstoque gestaoEstoque;
    private final GestaoProdutos gestaoProdutos;
    private Barbearia_date dados;

    
    private static final int CANCELAMENTO_SEM_TAXA = 7;
    private static final double TAXA_CANCELAMENTO = 0.35;
    private static final double TAXA_ENCAIXE_PERCENTUAL = 0.10;
   
    private GestaoOrdemServico(Barbearia_date dados) {
        this.dados = dados;
        this.gestaoEstoque = GestaoEstoque.getInstancia();
        this.gestaoProdutos = GestaoProdutos.getInstancia();
        
        
        this.listaModelo = new ArrayList<>();
        if (dados.getListaOrdensServico() != null) {
            this.listaModelo.addAll(dados.getListaOrdensServico().stream().filter(os -> os != null).toList());
        }
    }

    
    public static void inicializar(Barbearia_date dados) {
        if (instancia == null) {
            instancia = new GestaoOrdemServico(dados);
        }
    }

    
    public static GestaoOrdemServico getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestaoOrdemServico ainda não foi inicializada");
        }
        return instancia;
    }

    /**
     * Retorna uma CÓPIA segura
     * @return 
     */
    public ArrayList<OrdemServico> getListaCopia() {
        return new ArrayList<>(listaModelo);
    }

    /**
     * Cria uma nova Ordem de Serviço (conta) para um cliente e
     * anexa o primeiro agendamento a ela.
     * @param cliente
     * @param barbeiro
     * @param data
     * @param agendamentoInicial
     * @return 
     */
        public OrdemServico cadastrar(Cliente cliente, Barbeiro barbeiro, LocalDate data, Agendamento agendamentoInicial) throws Exception {

            validarAgendamentoParaCadastro(agendamentoInicial);

            OrdemServico novaOS = construirOrdemServicoBasica(cliente, barbeiro, data, agendamentoInicial);

            vincularAgendamentoAOrdem(novaOS, agendamentoInicial);
            recalcularValoresTotais(novaOS);

            super.adicionar(novaOS);
            return novaOS;
        }
        
        /** 
           * Verifica se o agendamento já está associado a uma OS
           */
        private void validarAgendamentoParaCadastro(Agendamento agendamento) throws Exception {
            if (agendamento.getAssociadoOrdemServico() != null) {
                throw new Exception(
                    "O Agendamento " + agendamento.getId() + 
                    " já pertence à OS " + agendamento.getAssociadoOrdemServico() + 
                    ". Não é possível criar uma nova OS para ele."
                );
            }
        }
        
        public void cadastrar(OrdemServico ordemServico, Agendamento agendamento) throws Exception{
            validarAgendamentoParaCadastro(agendamento);
            validarCliente(ordemServico.getIdCliente(), agendamento.getCliente());
            vincularAgendamentoAOrdem(ordemServico, agendamento);
            recalcularValoresTotais(ordemServico);
            super.adicionar(ordemServico);
        }
        
        private void validarCliente(String idClienteOS, Cliente clienteAgendamento) throws Exception{
            Cliente clienteOS = GestaoClientes.getInstancia().buscarPorId(idClienteOS);
            if(!clienteOS.getId().equals(clienteAgendamento.getId()))
                  throw new Exception("Cliente da OS difere do cliente do Agendamento;");  
        }

        /** 
           * Cria uma nova ordem de serviço com os dados básicos 
           */
        private OrdemServico construirOrdemServicoBasica(Cliente cliente, Barbeiro barbeiro, LocalDate data, Agendamento agendamento) {
            return new OrdemServico(cliente, barbeiro, data);
        }

        /**
           * Vincula o agendamento à ordem e define o relacionamento entre ambos 
           * 
           */
        private void vincularAgendamentoAOrdem(OrdemServico os, Agendamento agendamento) {
            os.adicionarAgendamento(agendamento);
            agendamento.setAssociado_Ordem_Servico(os.getId());
        }        
    
        private void recalcularValoresTotais(OrdemServico os) {
            if (os == null) return;
        
       
            double totalBaseServicos = 0.0;
            double totalTaxaEncaixe = 0.0;
        
            for (Agendamento ag : os.getAgendamentos()) {
                if(ag.getStatus() != StatusAgendamento.CANCELADO) {

                    double valorBaseAg = ag.getValorDosServicos();
                    totalBaseServicos += valorBaseAg;

                    if (ag.isEncaixe()) {
                        totalTaxaEncaixe += (valorBaseAg * 0.10); 
                    }
                }
            }

        
        // Calcular Produtos 
        double totalProdutos = 0.0;
        
        
        for (Map.Entry<String, Integer> itemVendido : os.getProdutosVendidos().entrySet()) {
            String produtoId = itemVendido.getKey();
            int quantidade = itemVendido.getValue();
            
            Produto produto = this.gestaoProdutos.buscarPorId(produtoId); 
            
            if (produto != null) {
                totalProdutos += (produto.getPreco() * quantidade);
            }
        }
        
        os.setValorTotalServicos(totalBaseServicos);
        os.setValorTaxaEncaixe(totalTaxaEncaixe);
        os.setValorTotalProdutos(totalProdutos);
    }

    /**
     *
     * @param osID
     * @param produtoID
     * @param quantidade
     * @throws Exception
     */
    public void adicionarProdutoVendido(String osID, String produtoID, int quantidade) throws Exception {
        

        OrdemServico os = this.buscarPorId(osID);
        if (os == null) throw new Exception("OS não encontrada.");
        
        Produto produto = this.gestaoProdutos.buscarPorId(produtoID);
        if (produto == null) throw new Exception("Produto do catálogo não encontrado.");
        
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");



        try {

            this.gestaoEstoque.reduzirQuantidade(produtoID, quantidade); 
        } catch (Exception e) {
            throw new Exception("Falha ao adicionar produto à conta: " + e.getMessage());
        }
        os.adicionarProdutoVendido(produto, quantidade);
        this.recalcularValoresTotais(os);
        
        System.out.println("Produto " + produto.getNome() + " (x" + quantidade + ") adicionado à OS " + os.getId());
    }

    
    /**
     * Processa o pagamento do adiantamento de 50%.
     * @param osID
     * @throws java.lang.Exception
     */
    public void processarPagamentoAdiantado(String osID) throws Exception {
        OrdemServico os = this.buscarPorId(osID);
        if (os == null) throw new Exception("OS não encontrada.");
        
 
        double valorAdiantamento = os.getValorTotalAPagar() * 0.50;
        os.setValorAdiantado_50pct(valorAdiantamento);
        
 
        // Libera os agendamentos
        for (Agendamento ag : os.getAgendamentos()) {
            if (ag.getStatus() == StatusAgendamento.AGUARDANDO_PAGAMENTO) {
                ag.setStatus(StatusAgendamento.CONFIRMADO);
            }
        }
        
        System.out.println("Pagamento de 50% registrado para a " + os.getId());
    }
    
    /**
     *
     * @param osID
     * @throws Exception
     */
    public void processarPagamentoFinal(String osID) throws Exception {
        OrdemServico os = this.buscarPorId(osID);
        if (os == null) throw new Exception("OS não encontrada.");    
        
        double valorPendente = os.getValorPendente();
        System.out.println("Recebendo pagamento final de: R$" + valorPendente);
        
        // Zera o adiantamento 
        os.setValorAdiantado_50pct(os.getValorTotalAPagar()); 
        os.setStatus(StatusAtendimento.PAGO);
        
        System.out.println("Ordem de Serviço " + os.getId() + " finalizada e PAGA.");
    }    
    
    /**
     * Este método é chamado DEPOIS que GestaoAgendamento.cancelarAgendamento() é chamado.
     * @param ordemServicoID
     * @param agCancelado
     * @throws java.lang.Exception
     */
    public void processarCancelamentoFinanceiro(String ordemServicoID, Agendamento agCancelado) throws Exception {
        OrdemServico os = this.buscarPorId(ordemServicoID);
        if (os == null) throw new Exception("OS não encontrada.");
        
        // LÓGICA FINANCEIRA (35%):
        long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), agCancelado.getDataHoraInicioAgendamento().toLocalDate());
        
    
        // E SE o cancelamento foi em cima da hora (menos de 7 dias).
        if (agCancelado.getStatus() != StatusAgendamento.PRE_AGENDADO && diasRestantes < CANCELAMENTO_SEM_TAXA) {
            
            double valorBase = agCancelado.getValorDosServicos();
            if (agCancelado.isEncaixe()) {
                valorBase += (valorBase * TAXA_ENCAIXE_PERCENTUAL); 
            }
            
            double taxa = valorBase * TAXA_CANCELAMENTO;
            os.setValorTaxaCancelamento_35pct(taxa);
            
        }
        this.recalcularValoresTotais(os);
        os.setStatus(StatusAtendimento.CANCELADO);
    }

    /**
     * Busca uma ordem de serviço na lista usando o ID.
     * @param id
     * @return 
     */
    public OrdemServico buscarPorId(String id) {
        return super.buscarPorId(id);
    }

    /**
     * Edita as observacoes (de forma segura).
     * @param id
     * @param observacoes
     * @throws java.lang.Exception
     */
    public void editar(String id, String observacoes) throws Exception {
        OrdemServico ordemServico = this.buscarPorId(id);
        
        if (ordemServico == null) {
            throw new Exception("OS com ID " + id + " não encontrada.");
        }
        
        ordemServico.setObservacoes(observacoes);
    }
    
    /**
     * Adiciona um produto usado ao histórico da OS.
     * @param idOrdem
     * @param idProduto
     * @throws java.lang.Exception
     */
    public void adicionarProdutoUtilizado(String idOrdem, String idProduto) throws Exception {
        OrdemServico ordemSelecionada = this.buscarPorId(idOrdem);
        if (ordemSelecionada == null) {
            throw new Exception("OS " + idOrdem + " não encontrada.");
        }

        Produto produtoAdicionado = GestaoProdutos.getInstancia().buscarPorId(idProduto);
        if (produtoAdicionado == null) {
            throw new Exception("Produto " + idProduto + " não encontrado.");
        }
        
        ordemSelecionada.adicionarProdutoUtilizado(produtoAdicionado);
    }
    
    /**
     * Busca todas as ordens de serviços associadas a um dado cliente.
     * @param idCliente
     * @return 
     */
    public ArrayList<OrdemServico> buscarOSCliente(String idCliente) {
        ArrayList<OrdemServico> osSelecionadas = new ArrayList<>();
        for (OrdemServico os : listaModelo) {
            if (os.getIdCliente().equals(idCliente)) {
                
                osSelecionadas.add(os);
            }
        }
        return osSelecionadas;
    }
    
    /**
     * Busca todas as ordens de serviços associadas a um dado cliente.
     * @param cliente
     * @return 
     */
    public ArrayList<OrdemServico> buscarOSCliente(Cliente cliente) {
        ArrayList<OrdemServico> osSelecionadas = new ArrayList<>();
        for (OrdemServico os : listaModelo) {
            if (os.getCliente() == cliente && !osSelecionadas.contains(os))
                osSelecionadas.add(os);   
        }
        return osSelecionadas;
    }    
    
    /**
     * Imprime todas as ordens de serviço associadas a um dado cliente.
     * @param id
     */
    public void printListaOSCliente(String id) {
        ArrayList<OrdemServico> lista = buscarOSCliente(id);
        super.printLista(lista);
    }
    
    /**
     * Imprime todas as ordens de serviço associadas a um dado cliente.
     * @param cliente
     */
    public void printListaOSCliente(Cliente cliente) {
        ArrayList<OrdemServico> lista = buscarOSCliente(cliente);
        super.printLista(lista);
    } 
    
    public void adicionarAgendamento(OrdemServico ordemServico, Agendamento agendamento){
        ordemServico.adicionarAgendamento(agendamento);
    }
    
    public int getNumeroOS(){
        return OrdemServico.getContador();
    }
}