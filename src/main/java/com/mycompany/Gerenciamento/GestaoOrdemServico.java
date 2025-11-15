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
            this.listaModelo = dados.getListaOrdensServico(); 
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
        public ArrayList<OrdemServico> getLista() {
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
        public void cadastrar(OrdemServico ordemServico, Agendamento agendamento) throws Exception{
            validarAgendamentoParaCadastro(agendamento);
            validarCliente(ordemServico.getIdCliente(), agendamento.getCliente());
            vincularAgendamentoAOrdem(ordemServico, agendamento);
            recalcularValoresTotais(ordemServico);
            super.adicionar(ordemServico);
        }

        public OrdemServico cadastrar(Cliente cliente, Barbeiro barbeiro, LocalDate data, Agendamento agendamentoInicial) throws Exception {

            validarAgendamentoParaCadastro(agendamentoInicial);

            OrdemServico novaOS = construirOrdemServicoBasica(cliente, barbeiro, data);

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
                    ". Não é possível criar uma nova OS para ele.");
            }
        }


        private void validarCliente(String idClienteOS, Cliente clienteAgendamento) throws Exception{
            Cliente clienteOS = GestaoClientes.getInstancia().buscarPorId(idClienteOS);
            if(!clienteOS.getId().equals(clienteAgendamento.getId()))
                  throw new Exception("Cliente da OS difere do cliente do Agendamento;");  
        }

        /** 
        * Cria uma nova ordem de serviço com os dados básicos 
        */
        private OrdemServico construirOrdemServicoBasica(Cliente cliente, Barbeiro barbeiro, LocalDate data) {
            return new OrdemServico(cliente, barbeiro, data);
        }

        /**
        * Vincula o agendamento à ordem e define o relacionamento entre ambos 
        */
        private void vincularAgendamentoAOrdem(OrdemServico os, Agendamento agendamento) {
            os.adicionarAgendamento(agendamento);
            agendamento.setAssociado_Ordem_Servico(os.getId());
        }        

        private void recalcularValoresTotais(OrdemServico os) {
            if (os == null) return;

            double totalServicos = calcularTotalServicos(os);
            double totalProdutos = calcularTotalProdutos(os);
            double taxaEncaixe = calcularTaxaEncaixe(os);

            os.setValorTotalServicos(totalServicos);
            os.setValorTotalProdutos(totalProdutos);
            os.setValorTaxaEncaixe(taxaEncaixe);
        }

        private double calcularTotalServicos(OrdemServico os) {
        return os.getAgendamentos().stream()
            .filter(ag -> ag.getStatus() != StatusAgendamento.CANCELADO)
            .mapToDouble(Agendamento::getValorDosServicos)
            .sum();
        }

        private double calcularTaxaEncaixe(OrdemServico os) {
            return os.getAgendamentos().stream()
                .filter(Agendamento::isEncaixe)
                .mapToDouble(ag -> ag.getValorDosServicos() * TAXA_ENCAIXE_PERCENTUAL)
                .sum();
        }

        private double calcularTotalProdutos(OrdemServico os) {
            return os.getProdutosVendidos().entrySet().stream()
                .mapToDouble(item -> {
                    Produto p = gestaoProdutos.buscarPorId(item.getKey());
                    return (p != null) ? p.getPreco() * item.getValue() : 0.0;
                })
                .sum();
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
            OrdemServico os = buscarOSValida(osID);
            os.setValorAdiantado_50pct(os.getValorTotalAPagar() * 0.5);
            os.getAgendamentos().forEach(ag -> { if (ag.getStatus() == StatusAgendamento.AGUARDANDO_PAGAMENTO)ag.setStatus(StatusAgendamento.CONFIRMADO);
            });
        }


        public void processarPagamentoFinal(OrdemServico os) throws Exception {
            os.setValorAdiantado_50pct(os.getValorTotalAPagar());
            os.setStatus(StatusAtendimento.PAGO);
            os.getAgendamentos().forEach(ag -> ag.setStatus(StatusAgendamento.FINALIZADO));
        }

        private OrdemServico buscarOSValida(String id) throws Exception {
            OrdemServico os = buscarPorId(id);
                if (os == null) throw new Exception("Ordem de serviço não encontrada.");
            return os;
        }



        /**
         * Este método é chamado DEPOIS que GestaoAgendamento.cancelarAgendamento() é chamado.
         * @param ordemServicoID
         * @param agCancelado
         * @throws java.lang.Exception
         */
        public void processarCancelamentoFinanceiro(String ordemServicoID, Agendamento agCancelado) throws Exception {
            OrdemServico os = buscarOSValida(ordemServicoID);

            if (deveCobrarTaxa(agCancelado)) {
                double taxa = calcularTaxaCancelamento(agCancelado);
                os.setValorTaxaCancelamento_35pct(taxa);
            }

            os.setStatus(StatusAtendimento.CANCELADO);
            recalcularValoresTotais(os);
        }

        private boolean deveCobrarTaxa(Agendamento ag) {
            long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), ag.getDataHoraInicioAgendamento().toLocalDate());
            return ag.getStatus() != StatusAgendamento.PRE_AGENDADO && diasRestantes < CANCELAMENTO_SEM_TAXA;
        }

        private double calcularTaxaCancelamento(Agendamento ag) {
            double valorBase = ag.getValorDosServicos();
            if (ag.isEncaixe()) valorBase += valorBase * TAXA_ENCAIXE_PERCENTUAL;
            return valorBase * TAXA_CANCELAMENTO;
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
        public void adicionarProdutoUtilizado(OrdemServico ordemSelecionada, Produto produtoAdicionado) throws Exception {
            if (ordemSelecionada == null) {
                throw new Exception("OS invalidada!.");
            }

            if (produtoAdicionado == null) {
                throw new Exception("Produto invalido!");
            }

            ordemSelecionada.adicionarProdutoUtilizado(produtoAdicionado);
        }

        /**
         * Busca todas as ordens de serviços associadas a um dado cliente.
         * @param idCliente
         * @return 
         */
        public ArrayList<OrdemServico> buscarOSCliente(String idCliente) {
            return new ArrayList<>(listaModelo.stream().filter(os -> os.getIdCliente().equals(idCliente)).toList());
        }

        public ArrayList<OrdemServico> buscarOSCliente(Cliente cliente) {
            return buscarOSCliente(cliente.getId());
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

        public void adicionarAgendamento(OrdemServico ordemServico, Agendamento agendamento) throws Exception{
            validarCliente(ordemServico.getIdCliente(), agendamento.getCliente());
            ordemServico.adicionarAgendamento(agendamento);
        }

        public int getNumeroOS(){
            return OrdemServico.getContador();
        }
}
    