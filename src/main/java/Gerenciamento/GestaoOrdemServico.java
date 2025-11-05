package Gerenciamento;

import com.mycompany.barbearia.modelos.*; // Importa todos os modelos
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
    
    private final ArrayList<OrdemServico> listaOS = new ArrayList<>();
    private static GestaoOrdemServico instancia;
    
    private final GestaoEstoque gestaoEstoque;
    private final GestaoProdutos gestaoProdutos;

    
    private static final int CANCELAMENTO_SEM_TAXA = 7;
    private static final double TAXA_CANCELAMENTO = 0.35;
    private static final double ADIANTAMENTO_PERCENTUAL = 0.50; 
    private static final double TAXA_ENCAIXE_PERCENTUAL = 0.10;

    private GestaoOrdemServico() {
        this.gestaoEstoque = GestaoEstoque.getInstancia();
        this.gestaoProdutos = GestaoProdutos.getInstancia();
    
    } 

    public static GestaoOrdemServico getInstancia() {
        if (instancia == null) {
            instancia = new GestaoOrdemServico();
        }
        return instancia;
    }

    /**
     * Retorna uma CÓPIA segura
     */
    public ArrayList<OrdemServico> getLista() {
        return new ArrayList<>(this.listaOS);
    }

    /**
     * Cria uma nova Ordem de Serviço (conta) para um cliente e
     * anexa o primeiro agendamento a ela.
     */
    public OrdemServico criarOrdemDeServico(Cliente cliente, Barbeiro barbeiro, LocalDate data, Agendamento agendamentoInicial) {
        OrdemServico novaOS = new OrdemServico(cliente, barbeiro, data);
        novaOS.adicionarAgendamento(agendamentoInicial); 
        super.adicionar(this.listaOS, novaOS);
        return novaOS;
    }
    
    private void recalcularValoresTotais(OrdemServico os) {
        if (os == null) return;
        
        // --- 1. Calcular Serviços ---
        double totalBaseServicos = 0.0;
        double totalTaxaEncaixe = 0.0;
        
        for (Agendamento ag : os.getAgendamentos()) {
            double valorBaseAg = ag.getValorDosServicos();
            totalBaseServicos += valorBaseAg;
            
            if (ag.isEncaixe()) {
                totalTaxaEncaixe += (valorBaseAg * 0.10); 
            }
        }
        
        // Calcular Produtos ---
        double totalProdutos = 0.0;
        
        // Itera pelo Map
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
     * Anexa um agendamento a uma OS já existente.
     */
    public void adicionarAgendamentoEmOS(String osID, Agendamento novoAgendamento) throws Exception {
        OrdemServico os = this.buscarPorId(osID); 
        if (os == null) {
            throw new Exception("Ordem de Serviço não encontrada.");
        }
        os.adicionarAgendamento(novoAgendamento);
        this.recalcularValoresTotais(os);
    }
    
    /**
     * Processa o pagamento do adiantamento de 50%.
     * Esta é a "cola" que conecta Finanças e Logística.
     */
    public void processarPagamentoAdiantado(String osID) throws Exception {
        OrdemServico os = this.buscarPorId(osID);
        if (os == null) throw new Exception("OS não encontrada.");
        
 
        double valorAdiantamento = os.getValorTotalAPagar() * ADIANTAMENTO_PERCENTUAL;
        os.setValorAdiantado_50pct(valorAdiantamento);
        
 
        // Libera os agendamentos logisticamente.
        for (Agendamento ag : os.getAgendamentos()) {
            if (ag.getStatus() == StatusAgendamento.AGUARDANDO_PAGAMENTO) {
                ag.setStatus(StatusAgendamento.CONFIRMADO);
            }
        }
        
        System.out.println("Pagamento de 50% registrado para a " + os.getId());
    }
    
    /**
     * Este método é chamado DEPOIS que GestaoAgendamento.cancelarAgendamento() é chamado.
     */
    public void processarCancelamentoFinanceiro(String osID, Agendamento agCancelado) throws Exception {
        OrdemServico os = this.buscarPorId(osID);
        if (os == null) throw new Exception("OS não encontrada.");
        
        // LÓGICA FINANCEIRA (35%):
        long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), agCancelado.getDataHoraInicioAgendamento().toLocalDate());
        
    
        // E SE o cancelamento foi em cima da hora (menos de 7 dias).
        if (agCancelado.getStatus() != StatusAgendamento.PRE_AGENDADO && diasRestantes < CANCELAMENTO_SEM_TAXA) {
            
            double valorBase = agCancelado.getValorDosServicos();
            if (agCancelado.isEncaixe()) {
                valorBase += (valorBase * TAXA_ENCAIXE_PERCENTUAL); // A taxa de 35% é sobre o valor final 
            }
            
            double taxa = valorBase * TAXA_CANCELAMENTO;
            os.setValorTaxaCancelamento_35pct(taxa);
        }
        
        os.setStatus(StatusAtendimento.CANCELADO);
    }

    /**
     * Busca uma ordem de serviço na lista usando o ID.
     */
    public OrdemServico buscarPorId(String id) {
        return super.procurandoID(this.listaOS, id);
    }

    /**
     * Edita as observacoes (de forma segura).
     */
    public void editar(String id, String observacoes) throws Exception {
        OrdemServico ordemServico = this.buscarPorId(id);
        
        if (ordemServico == null) {
            throw new Exception("OS com ID " + id + " não encontrada.");
        }
        
        ordemServico.setObservacoes(observacoes);
    }
    
    /**
     * Remove uma ordem de serviço com base no ID informado.
     */
    public void remover(String id) {
        super.remover(listaOS, id);
    }

    /**
     * Imprime a lista de OSs atual.
     */
    public void printLista() {
        super.printLista(listaOS);
    }
    
    /**
     * Adiciona um produto usado ao histórico da OS.
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
     */
    private ArrayList<OrdemServico> buscarOSCliente(String idCliente) {
        ArrayList<OrdemServico> osSelecionadas = new ArrayList<>();
        for (OrdemServico os : this.listaOS) {
            if (os.getIdCliente().equals(idCliente)) {
                
                osSelecionadas.add(os);
            }
        }
        return osSelecionadas;
    }
    
    /**
     * Imprime todas as ordens de serviço associadas a um dado cliente.
     */
    public void printListaOSCliente(String id) {
        ArrayList<OrdemServico> lista = buscarOSCliente(id);
        super.printLista(lista);
    }
}