package InterfaceG;

import com.mycompany.barbearia.modelos.Atendente;
import com.mycompany.barbearia.modelos.Barbeiro;
import com.mycompany.barbearia.modelos.Gerente;
import com.mycompany.barbearia.modelos.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipalTela extends JFrame {

    // Sidebar
    private JPanel sidebar;

    // Main panel
    private JPanel mainPainel;

    // Botões do menu
    private JToggleButton btnClientes   , btnAgenda, btnAgendamentos,
            btnOrdemServico, btnBaterPonto, btnFinanceiro, btnGestaoUsuarios,
            btnGestaoEstoque, btnGestaoDespesas, btnEncerrarSessao;

    // Grupo de botões para manter apenas um selecionado
    private ButtonGroup grupoMenu;

    private Usuario usuarioLogado;
    
    public MenuPrincipalTela(Usuario user) {
        this.usuarioLogado = user;
        initComponents();
        configurarMenuPorPerfil();
    }

    private void initComponents() {
      // Configurações da janela
        setTitle("Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(new Color(7, 7, 7));
        sidebar.setLayout(new GridLayout(0, 1, 0, 4));

        // Inicializa botões
        btnClientes = criarBotao("Clientes");
        btnAgenda = criarBotao("Agenda");
        btnAgendamentos = criarBotao("Agendamentos");
        btnOrdemServico = criarBotao("Ordem de Serviço");
        btnBaterPonto = criarBotao("Bater Ponto");
        btnFinanceiro = criarBotao("Financeiro");
        btnGestaoUsuarios = criarBotao("Gestão Usuários");
        btnGestaoEstoque = criarBotao("Gestão Estoque");
        btnGestaoDespesas = criarBotao("Gestão Despesas");
        btnEncerrarSessao = criarBotao("Encerrar Sessão");

        // Adiciona botões ao sidebar
        sidebar.add(btnClientes);
        sidebar.add(btnAgenda);
        sidebar.add(btnAgendamentos);
        sidebar.add(btnOrdemServico);
        sidebar.add(btnBaterPonto);
        sidebar.add(btnFinanceiro);
        sidebar.add(btnGestaoUsuarios);
        sidebar.add(btnGestaoEstoque);
        sidebar.add(btnGestaoDespesas);
        sidebar.add(btnEncerrarSessao);

        // ButtonGroup para JToggleButtons
        grupoMenu = new ButtonGroup();
        grupoMenu.add(btnClientes);
        grupoMenu.add(btnAgenda);
        grupoMenu.add(btnAgendamentos);
        grupoMenu.add(btnOrdemServico);
        grupoMenu.add(btnBaterPonto);
        grupoMenu.add(btnFinanceiro);
        grupoMenu.add(btnGestaoUsuarios);
        grupoMenu.add(btnGestaoEstoque);
        grupoMenu.add(btnGestaoDespesas);
        grupoMenu.add(btnEncerrarSessao);

        add(sidebar, BorderLayout.WEST);

        // Top panel com título e botão de perfil
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(24, 7, 7));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding

        // Título à esquerda
        JLabel titulo = new JLabel("Italo's Barbearia");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.ITALIC, 18));
        topPanel.add(titulo, BorderLayout.WEST);

        // Botão de perfil à direita
        JButton btnPerfil = new JButton("Perfil");
        btnPerfil.setBackground(new Color(102, 0, 0));
        btnPerfil.setForeground(Color.WHITE);
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
        btnPerfil.setOpaque(true);
        topPanel.add(btnPerfil, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        
        btnPerfil.addActionListener((java.awt.event.ActionEvent evt) -> {
                        dispose(); // Fecha a janela
            new TelaDeLogin().setVisible(true);
        });
        
        btnClientes.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new ClientesPanel());
        });

        btnAgenda.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new AgendaPanel());
        });

        btnAgendamentos.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new AgendamentosPanel());
        });

        btnOrdemServico.addActionListener((java.awt.event.ActionEvent evt) -> {
           // setView(new OrdemServicoPanel());
        });

        btnBaterPonto.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new BaterPontoPanel());
        });

        btnFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new FinanceiroPanel());
        });

        btnGestaoUsuarios.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new GestaoUsuariosPanel());
        });

        btnGestaoEstoque.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new GestaoEstoquePanel());
        });

        btnGestaoDespesas.addActionListener((java.awt.event.ActionEvent evt) -> {
            //setView(new GestaoDespesasPanel());
        });

        btnEncerrarSessao.addActionListener((java.awt.event.ActionEvent evt) -> {
            dispose(); // Fecha a janela
            new TelaDeLogin().setVisible(true); // Abre a tela de login
        });

        
    }

    // Método para criar botões com estilo e hover
    private JToggleButton criarBotao(String texto) {
        JToggleButton btn = new JToggleButton(texto);
        btn.setBackground(new Color(102, 0, 0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(150, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btn.isSelected()) {
                    btn.setBackground(new Color(102, 0, 0));
                }
            }
        });

        return btn;
    }

    // Método para trocar telas
    public void setView(JPanel tela) {
        mainPainel.removeAll();
        mainPainel.add(tela, BorderLayout.CENTER);
        mainPainel.revalidate();
        mainPainel.repaint();
    }

    // Método para configurar menu de acordo com o perfil do usuário
    public void configurarMenuPorPerfil() {
        btnClientes.setVisible(true);
        btnEncerrarSessao.setVisible(true);

        if (usuarioLogado instanceof Gerente) {
            btnAgenda.setVisible(true);
            btnAgendamentos.setVisible(true);
            btnOrdemServico.setVisible(true);
            btnBaterPonto.setVisible(true);
            btnFinanceiro.setVisible(true);
            btnGestaoUsuarios.setVisible(true);
            btnGestaoEstoque.setVisible(true);
            btnGestaoDespesas.setVisible(true);
        } else if (usuarioLogado instanceof Atendente) {
            btnGestaoUsuarios.setVisible(false);
            btnGestaoEstoque.setVisible(false);
            btnGestaoDespesas.setVisible(false);
            btnAgenda.setVisible(true);
            btnAgendamentos.setVisible(true);
            btnOrdemServico.setVisible(true);
            btnBaterPonto.setVisible(true);
            btnFinanceiro.setVisible(true);
        } else if (usuarioLogado instanceof Barbeiro) {
            btnGestaoUsuarios.setVisible(false);
            btnGestaoEstoque.setVisible(false);
            btnGestaoDespesas.setVisible(false);
            btnAgenda.setVisible(true);
            btnAgendamentos.setVisible(true);
            btnOrdemServico.setVisible(true);
            btnBaterPonto.setVisible(true);
            btnFinanceiro.setVisible(true);
        }  
    }
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  
        Usuario user = new Gerente(); // TESTE: Gerente, Atendente ou Barbeiro
        new MenuPrincipalTela(user);
    });
    }
}
