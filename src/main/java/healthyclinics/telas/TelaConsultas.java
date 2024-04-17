
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaClienteDAO;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.Recomendacoes;
import healthyclinics.ferramentas.TableConsultasCliente;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.shadows.TextPanel;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.CirclePanelShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.tablesData.ContaCliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class TelaConsultas implements ActionListener {

    private ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JPanel pnlConsultas;
    private PanelShadow pnlConsultasProximas;
    private PanelShadow pnlTableConsultas;
    private PanelShadow pnlMinhasConsultas;
    private TextPanel pnlNovaConsulta;
    private TextPanel pnlRedirecionar;
    private ButtonShadow btnNovaConsulta;
    private ButtonShadow btnCancelar;
    private ButtonShadow btnNovaConsultaMid;
    private ButtonShadow btnNovaConsultaBot;
    private ButtonBar buttonBar;
    private Recomendacoes recomendacoes;
    private JLayeredPane layeredPane;
    private TableConsultasCliente tblConsultasProximas;
    private TableConsultasCliente tblMinhasConsultas;
    
    public TelaConsultas(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaConsultas() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configTexts();
        configTables();
        configBtns();

        // Define o scroll no início da tela
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                }
            }
        );

        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void configGrid() {
        
        // Configura as layers
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1920, 3973));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(null);
        
        // Configuração da scroll pane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 1920, 1080);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom()); // Scroll customizado
        scrollPane.setBorder(null);
        
        // Configura os painéis
        configPnls();
        
        // Adiciona as layers no frame
        configFrame.getMainFrame().add(scrollPane);
        
    }
    
    
    public void configPnls() {
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Barra de botões
        buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel da tela
        pnlConsultas = new JPanel();
        pnlConsultas.setPreferredSize(new Dimension(1920, 3610));
        pnlConsultas.setBackground(new Color(0xF0F3FA));
        pnlConsultas.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        mainPanel.add(pnlConsultas, gbc);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1920, 3610);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        pnlConsultas.add(layeredPane);
        
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        
        // Painel Consultas Próximas
        pnlConsultasProximas = new PanelShadow(50);
        pnlConsultasProximas.setBounds(-10, 53, 1930, 529);
        pnlConsultasProximas.setBackground(new Color(0xD5DEEF));
        pnlConsultasProximas.setLayout(null);
        pnlConsultasProximas.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlConsultasProximas, JLayeredPane.MODAL_LAYER);
        
        // Painel da tabela de consultas próximas
        pnlTableConsultas = new PanelShadow(50);
        pnlTableConsultas.setBounds(180, 165, 1272, 293);
        pnlTableConsultas.setBackground(new Color(0xB1C9EF));
        pnlTableConsultas.setLayout(null);
        pnlTableConsultas.setShadowType(ShadowType.BOT);
        pnlConsultasProximas.add(pnlTableConsultas);
        
        // Painel da tabela "Minhas Consultas"
        pnlMinhasConsultas = new PanelShadow(40);
        pnlMinhasConsultas.setBounds(82, 1723, 1135, 480);
        pnlMinhasConsultas.setBackground(new Color(0x395886));
        pnlMinhasConsultas.setLayout(null);
        pnlMinhasConsultas.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlMinhasConsultas, JLayeredPane.PALETTE_LAYER);
        
        // Painel de informações sobre consultas
        pnlNovaConsulta = new TextPanel(50, 15);
        pnlNovaConsulta.setBounds(561, 932, 798, 285);
        pnlNovaConsulta.setBackground(new Color(0xB1C9EF));
        pnlNovaConsulta.setShadowSize(2);
        pnlNovaConsulta.setShadowType(ShadowType.BOT);
        pnlNovaConsulta.configPnlCenter();
        layeredPane.add(pnlNovaConsulta, JLayeredPane.MODAL_LAYER);
        
        // Painel Texto para agendamento de consultas
        pnlRedirecionar = new TextPanel(50, 20);
        pnlRedirecionar.setBounds(625, 2378, 584, 181);
        pnlRedirecionar.setBackground(new Color(0xB1C9EF));
        pnlRedirecionar.setShadowSize(3);
        pnlRedirecionar.setShadowType(ShadowType.BOT);
        pnlRedirecionar.configPnlCenter();
        layeredPane.add(pnlRedirecionar, JLayeredPane.PALETTE_LAYER);
        
        // Recomendações
        if(recomendacoes == null) {
            recomendacoes = new Recomendacoes(configFrame, scrollPane);
            recomendacoes.setBounds(159, 2820, recomendacoes.getWidth(), recomendacoes.getHeight());
            layeredPane.add(recomendacoes, JLayeredPane.PALETTE_LAYER);
        } else {
            recomendacoes.setTela(scrollPane);
            layeredPane.add(recomendacoes, JLayeredPane.PALETTE_LAYER);
        }
        
        
    }
    
    
    public void configImages() {
        
        // Imagem do relógio no painel de consultas próximas
        ImageIcon imgClock = new ImageIcon(getClass().getResource("/imagens/ImgClock.png"));
        JLabel lblClock = new JLabel();
        lblClock.setBounds(1727, 26, imgClock.getIconWidth(), imgClock.getIconHeight());
        lblClock.setIcon(imgClock);
        layeredPane.add(lblClock, JLayeredPane.POPUP_LAYER);
        
        
        // Imagem flor azul
        ImageIcon imgBlueFlower = new ImageIcon(getClass().getResource("/imagens/ImgBlueFlower.png"));
        
        // Imagem flor azul esquerda
        JLabel lblFlowerLeft = new JLabel();
        lblFlowerLeft.setBounds(-110, 674, imgBlueFlower.getIconWidth(), imgBlueFlower.getIconHeight());
        lblFlowerLeft.setIcon(imgBlueFlower);
        layeredPane.add(lblFlowerLeft, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem flor azul direita
        JLabel lblFlowerRight = new JLabel();
        lblFlowerRight.setBounds(1449, 543, imgBlueFlower.getIconWidth(), imgBlueFlower.getIconHeight());
        lblFlowerRight.setIcon(imgBlueFlower);
        layeredPane.add(lblFlowerRight, JLayeredPane.DEFAULT_LAYER);
        
        
        // Ellipse da esquerda
        ImageIcon imgEllipseLeft = new ImageIcon(getClass().getResource("/imagens/EllipseConsultasLeft.png"));
        JLabel lblEllipseLeft = new JLabel();
        lblEllipseLeft.setBounds(-196, 283, imgEllipseLeft.getIconWidth(), imgEllipseLeft.getIconHeight());
        lblEllipseLeft.setIcon(imgEllipseLeft);
        layeredPane.add(lblEllipseLeft, JLayeredPane.PALETTE_LAYER);
        
        // Ellipse da direita
        ImageIcon imgEllipseRight = new ImageIcon(getClass().getResource("/imagens/EllipseConsultasRight.png"));
        JLabel lblEllipseRight = new JLabel();
        lblEllipseRight.setBounds(1026, 420, imgEllipseRight.getIconWidth(), imgEllipseRight.getIconHeight());
        lblEllipseRight.setIcon(imgEllipseRight);
        layeredPane.add(lblEllipseRight, JLayeredPane.PALETTE_LAYER);
        
        // Logo Healthy Clinics
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogoConsultas = new JLabel();
        lblLogoConsultas.setBounds(645, 592, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogoConsultas.setIcon(imgLogo);
        layeredPane.add(lblLogoConsultas, JLayeredPane.MODAL_LAYER);
        
        // Painel da imagem da água
        CirclePanelShadow pnlWater = new CirclePanelShadow();
        pnlWater.setBounds(1333, 1540, 1224, 1181);
        pnlWater.setBackground(new Color(0xD5DEEF));
        layeredPane.add(pnlWater, JLayeredPane.DEFAULT_LAYER);
        
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/ImgWaterConsultas.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(24, 24, imgWater.getIconWidth(), imgWater.getIconHeight());
        lblWater.setIcon(imgWater);
        pnlWater.add(lblWater);
        
        // Logo Healthy Clinics
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(17, 2303, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        layeredPane.add(lblLogo, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void configTexts() {
        
        // Texto "Próximas Consultas"
        JLabel lblProximasConsultas = new JLabel("Próximas Consultas");
        lblProximasConsultas.setBounds(187, 39, 590, 126);
        lblProximasConsultas.setForeground(new Color(0x395886));
        lblProximasConsultas.setFont(new Font("Rufina", Font.BOLD, 60));
        pnlConsultasProximas.add(lblProximasConsultas);
        
        // Texto "Clique duas vezes na consulta..."
        JLabel lblDetalhes = new JLabel("• Clique duas vezes na consulta, para vê-la individualmente.");
        lblDetalhes.setBounds(195, 468, 600, 44);
        lblDetalhes.setForeground(new Color(0x395886));
        lblDetalhes.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlConsultasProximas.add(lblDetalhes);
        
        // Texto "NOVA CONSULTA"
        JLabel lblNovaConsulta = new JLabel("NOVA CONSULTA");
        lblNovaConsulta.setBounds(591, 888, 230, 32);
        lblNovaConsulta.setForeground(new Color(0x395886));
        lblNovaConsulta.setFont(new Font("Volkhov", Font.BOLD, 25));
        layeredPane.add(lblNovaConsulta, JLayeredPane.MODAL_LAYER);
        
        // Informações sobre agendamento de consultas
        DefaultTextPane txaConsultas = new DefaultTextPane();
        txaConsultas.setText("Agende uma consulta em clínicas especializadas e de confiança na Helthy Clinics."
                + "\nNa plataforma temos uma grande variedade de áreas, clínicas e médicos. Escolha a área e a clínica que você deseja através da pesquisa de clínicas."
                + "\nAo escolher a clínica, agende uma consulta, de acordo com as datas disponíveis da clínica.");
        txaConsultas.setBounds(35, 22, 702, 221);
        txaConsultas.setForeground(new Color(0x395886));
        txaConsultas.setFont(new Font("Volkhov", Font.PLAIN, 23));
        pnlNovaConsulta.getPnlCenter().add(txaConsultas);
        
        // Texto "Minhas Consultas"
        JLabel lblMinhasConsultas = new JLabel("Minhas Consultas");
        lblMinhasConsultas.setBounds(122, 1559, 870, 126);
        lblMinhasConsultas.setForeground(new Color(0x395886));
        lblMinhasConsultas.setFont(new Font("Rufina", Font.BOLD, 90));
        layeredPane.add(lblMinhasConsultas, JLayeredPane.MODAL_LAYER);
        
        // Informações para o agendamento de consultas
        DefaultTextPane txaRedirecionar = new DefaultTextPane();
        txaRedirecionar.setText("Pensando em agendar uma nova consulta?" 
                + "\nClique no botão abaixo para ser redirecionado para a pesquisa de clínicas.");
        txaRedirecionar.setBounds(15, 20, 512, 110);
        txaRedirecionar.setForeground(new Color(0x395886));
        txaRedirecionar.setFont(new Font("Volkhov", Font.PLAIN, 21));
        pnlRedirecionar.getPnlCenter().add(txaRedirecionar);
        
    }
    
    
    public void configTables() {
        
        // Tabela "Consultas Próximas"
        ContaClienteDAO dao = new ContaClienteDAO();
        ContaCliente contaLogada = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
        
        tblConsultasProximas = new TableConsultasCliente(configFrame, scrollPane, contaLogada);
        tblConsultasProximas.setBounds(7, 20, 1258, 240);
        tblConsultasProximas.gerarTabela();
        tblConsultasProximas.limparTabela();
        tblConsultasProximas.atualizarTabelaData();
        pnlTableConsultas.add(tblConsultasProximas);
        
        // Design da tabela "Minhas Consultas"
        CirclePanelShadow leftCircle = new CirclePanelShadow();
        leftCircle.setBounds(-110, 1677, 370, 357);
        leftCircle.setBackground(new Color(0xB1C9EF));
        layeredPane.add(leftCircle, JLayeredPane.DEFAULT_LAYER);
        
        CirclePanelShadow rightCircle = new CirclePanelShadow();
        rightCircle.setBounds(1029, 1642, 266, 256);
        rightCircle.setBackground(new Color(0x638ECB));
        layeredPane.add(rightCircle, JLayeredPane.DEFAULT_LAYER);
        
        CirclePanelShadow midCircle = new CirclePanelShadow();
        midCircle.setBounds(495, 1877, 473, 455);
        midCircle.setBackground(new Color(0x152E52));
        layeredPane.add(midCircle, JLayeredPane.DEFAULT_LAYER);
        
        // Tabela "Minhas Consultas"
        tblMinhasConsultas = new TableConsultasCliente(configFrame, scrollPane, contaLogada);
        tblMinhasConsultas.setBounds(6, 30, 1123, 410);
        tblMinhasConsultas.gerarTabela();
        pnlMinhasConsultas.add(tblMinhasConsultas);
        
        
    }
    
    
    public void configBtns() {
        
        // Botão "NOVA CONSULTA"
        btnNovaConsulta = new ButtonShadow("NOVA CONSULTA", 70);
        btnNovaConsulta.addActionListener(this);
        btnNovaConsulta.setBounds(1508, 191, 242, 72);
        btnNovaConsulta.setBackground(new Color(0xB1C9EF));
        btnNovaConsulta.setForeground(new Color(0x395886));
        btnNovaConsulta.setFont(new Font("Volkhov", Font.BOLD, 24));
        btnNovaConsulta.setShadowSize(2);
        btnNovaConsulta.setShadowType(ShadowType.BOT);
        btnNovaConsulta.setShadowOpacity(0.3f);
        pnlConsultasProximas.add(btnNovaConsulta);
        
        // Botão "CANCELAR"
        btnCancelar = new ButtonShadow("CANCELAR", 70);
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(1508, 290, 242, 72);
        btnCancelar.setBackground(new Color(0xB1C9EF));
        btnCancelar.setForeground(new Color(0x395886));
        btnCancelar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCancelar.setShadowSize(2);
        btnCancelar.setShadowOpacity(0.3f);
        btnCancelar.setShadowType(ShadowType.BOT);
        pnlConsultasProximas.add(btnCancelar);
        
        // Botão "NOVA CONSULTA" na região central da tela
        btnNovaConsultaMid = new ButtonShadow("NOVA CONSULTA", 60);
        btnNovaConsultaMid.addActionListener(this);
        btnNovaConsultaMid.setBounds(759, 1272, 402, 107);
        btnNovaConsultaMid.setBackground(new Color(0x395886));
        btnNovaConsultaMid.setForeground(new Color(0xF0F3FA));
        btnNovaConsultaMid.setFont(new Font("Volkhov", Font.BOLD, 30));
        btnNovaConsultaMid.setShadowSize(3);
        btnNovaConsultaMid.setShadowType(ShadowType.BOT);
        btnNovaConsultaMid.setShadowOpacity(0.3f);
        layeredPane.add(btnNovaConsultaMid, JLayeredPane.MODAL_LAYER);
        
        // Botão "Nova Consulta"
        btnNovaConsultaBot = new ButtonShadow("Nova Consulta", 65);
        btnNovaConsultaBot.addActionListener(this);
        btnNovaConsultaBot.setBounds(800, 2600, 237, 68);
        btnNovaConsultaBot.setBackground(new Color(0xB1C9EF));
        btnNovaConsultaBot.setForeground(new Color(0x395886));
        btnNovaConsultaBot.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnNovaConsultaBot.setShadowSize(2);
        btnNovaConsultaBot.setShadowType(ShadowType.BOT);
        btnNovaConsultaBot.setShadowOpacity(0.3f);
        layeredPane.add(btnNovaConsultaBot, JLayeredPane.MODAL_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnNovaConsulta || e.getSource() == btnNovaConsultaMid || e.getSource() == btnNovaConsultaBot) {
            
            // Abre a tela de clínicas
            configFrame.getMainFrame().remove(scrollPane);
            configFrame.getManager().getTelas().getTelaClinicas().configTelaClinicas();
            
        } else if(e.getSource() == btnCancelar) {
            
            if(tblConsultasProximas.getTabelaConsultas().getSelectedRow() != -1) {
                // Marca a consulta como cancelada
                tblConsultasProximas.cancelarConsulta(tblConsultasProximas.getTabelaConsultas().getSelectedRow());
            }
            
        }
        
    }
    
    
}
