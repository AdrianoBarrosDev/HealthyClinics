
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.FiltrosEspecialidades;
import healthyclinics.ferramentas.ImageSlider;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.ferramentas.Recomendacoes;
import healthyclinics.ferramentas.TableClinicas;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.StyleConstants;

public class TelaClinicas implements ActionListener {

    private ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JPanel pnlClinicas;
    private PanelShadow pnlPesquisaClinicas;
    private JPanel pnlClinicasRecomendadas;
    private JPanel pnlOutrasClinicas;
    private PanelShadow pnlTabelaClinicas;
    private PanelShadow pnlFiltros;
    private FiltrosEspecialidades pnlEspecialidades;
    private JButton btnPesquisar;
    private ButtonBar buttonBar;
    private TextFieldShadow txtPesquisa;
    private TableClinicas tblClinicas;
    private ImageSlider slider;
    private Recomendacoes recomendacoes;
    private JLayeredPane layeredPane;
    
    public TelaClinicas(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaClinicas() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configTexts();
        configTxt();
        configLines();
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
        mainPanel.setPreferredSize(new Dimension(1920, 3000));
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
        pnlClinicas = new JPanel();
        pnlClinicas.setPreferredSize(new Dimension(1920, 2638));
        pnlClinicas.setBackground(new Color(0xF0F3FA));
        pnlClinicas.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        mainPanel.add(pnlClinicas, gbc);
        
        // Painel Pesquisa de Clínicas
        pnlPesquisaClinicas = new PanelShadow(50);
        pnlPesquisaClinicas.setBounds(-10, -20, 1940, 690);
        pnlPesquisaClinicas.setBackground(new Color(0x152E52));
        pnlPesquisaClinicas.setLayout(null);
        pnlPesquisaClinicas.setShadowSize(3);
        pnlPesquisaClinicas.setShadowType(ShadowType.BOT);
        pnlClinicas.add(pnlPesquisaClinicas);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(10, 20, 1920, 693);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        pnlPesquisaClinicas.add(layeredPane);
        
        // Painel Clínicas Recomendadas
        pnlClinicasRecomendadas = new JPanel();
        pnlClinicasRecomendadas.setBounds(0, 663, 1920, 1155);
        pnlClinicasRecomendadas.setBackground(new Color(0xF0F3FA));
        pnlClinicasRecomendadas.setLayout(null);
        pnlClinicas.add(pnlClinicasRecomendadas);
        
        // Painel Outras Clínicas Recomendadas
        pnlOutrasClinicas = new JPanel();
        pnlOutrasClinicas.setBounds(0, 1818, 1920, 820);
        pnlOutrasClinicas.setBackground(new Color(0xF0F3FA));
        pnlOutrasClinicas.setLayout(null);
        pnlClinicas.add(pnlOutrasClinicas);
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        // Painel de filtros sobre clínicas
        pnlFiltros = new PanelShadow(50);
        pnlFiltros.setBounds(112, 209, 455, 395);
        pnlFiltros.setBackground(new Color(0xB1C9EF));
        pnlFiltros.setShadowSize(3);
        pnlFiltros.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlFiltros, JLayeredPane.PALETTE_LAYER);
        
        // Painel da tabela de pesquisa
        pnlTabelaClinicas = new PanelShadow(40);
        pnlTabelaClinicas.setBounds(608, 209, 1111, 395);
        pnlTabelaClinicas.setBackground(new Color(0xB1C9EF));
        pnlTabelaClinicas.setLayout(null);
        pnlTabelaClinicas.setShadowSize(3);
        pnlTabelaClinicas.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlTabelaClinicas, JLayeredPane.PALETTE_LAYER);
        
        // Configura a tabela
        configTables();
        
        // Painel de especialidades
        pnlEspecialidades = new FiltrosEspecialidades(40, tblClinicas);
        pnlEspecialidades.setBounds(48, 134, pnlEspecialidades.getPreferredSize().width, pnlEspecialidades.getPreferredSize().height);
        pnlFiltros.add(pnlEspecialidades);
        
        // Recomendações
        if(recomendacoes == null) {
            recomendacoes = new Recomendacoes(configFrame, scrollPane);
            recomendacoes.setBounds(159, 0, recomendacoes.getWidth(), recomendacoes.getHeight());
            recomendacoes.changeTextLabel("Outras Clínicas");
            pnlOutrasClinicas.add(recomendacoes);
        } else {
            recomendacoes.setTela(scrollPane);
            pnlOutrasClinicas.add(recomendacoes);
        }
        
    }
    
    
    public void configImages() {
        
        // Imagem Retângulo no painel clínicas
        ImageIcon imgRectangle = new ImageIcon(getClass().getResource("/imagens/RectangleClinica.png"));
        JLabel lblRectangle = new JLabel();
        lblRectangle.setBounds(884, -176, imgRectangle.getIconWidth(), imgRectangle.getIconHeight());
        lblRectangle.setIcon(imgRectangle);
        layeredPane.add(lblRectangle, JLayeredPane.DEFAULT_LAYER);
        
        // Imagens das principais clínicas
        if(slider == null) {
            slider = new ImageSlider();
            slider.setBounds(slider.getX(), 211, slider.getWidth(), slider.getHeight());
            slider.changeSize(1289, 730);
            slider.adicionarNomeClinica();
        }
        pnlClinicasRecomendadas.add(slider);
        
    }
    
    
    public void configTables() {
        
        // Tabela clínicas
        tblClinicas = new TableClinicas(configFrame, scrollPane);
        tblClinicas.setBounds(2, 23, 1106, 344);
        tblClinicas.gerarTabela();
        pnlTabelaClinicas.add(tblClinicas);
        
    }
    
    
    public void configTexts() {
        
        // Texto "Clínica"
        JLabel lblClinica = new JLabel("Clínicas");
        lblClinica.setBounds(115, 58, 351, 90);
        lblClinica.setForeground(new Color(0xD5DEEF));
        lblClinica.setFont(new Font("Rufina", Font.BOLD, 90));
        lblClinica.setVerticalAlignment(StyleConstants.ALIGN_CENTER);
        layeredPane.add(lblClinica, JLayeredPane.DEFAULT_LAYER);
        
        // Texto "Pesquisa"
        JLabel lblPesquisa = new JLabel("Pesquisa");
        lblPesquisa.setBounds(58, 27, 126, 30);
        lblPesquisa.setForeground(new Color(0x395886));
        lblPesquisa.setFont(new Font("Volkhov", Font.PLAIN, 25));
        lblPesquisa.setVerticalAlignment(StyleConstants.ALIGN_CENTER);
        pnlFiltros.add(lblPesquisa);
        
        // Texto "Clínicas Recomendadas"
        JLabel lblRecomendacoes = new JLabel("Clínicas Recomendadas");
        lblRecomendacoes.setBounds(445, 51, 1031, 111);
        lblRecomendacoes.setForeground(new Color(0x395886));
        lblRecomendacoes.setFont(new Font("Rufina", Font.BOLD, 90));
        lblRecomendacoes.setVerticalAlignment(StyleConstants.ALIGN_CENTER);
        pnlClinicasRecomendadas.add(lblRecomendacoes);
        
    }
    
    
    public void configTxt() {
     
        // TextField para a pesquisa de nomes de clínica
        txtPesquisa = new TextFieldShadow(45);
        txtPesquisa.setBounds(48, 70, 359, 53);
        txtPesquisa.setBackground(new Color(0xF0F3FA));
        txtPesquisa.setForeground(new Color(0x395886));
        txtPesquisa.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtPesquisa.setBorder(BorderFactory.createEmptyBorder(-3, 60, 0, 5));
        pnlFiltros.add(txtPesquisa);
        
    }
    
    
    public void configLines() {
        
        // Linhas da tela
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1920, 120);
        lines.addLine(49, 107, 417, 107, 2, new Color(0x395886));
        lines.addLine(1497, 107, 1874, 107, 2, new Color(0x395886));
        pnlClinicasRecomendadas.add(lines);
        
    }
    
    
    public void configBtns() {
        
        // Botão lupa
        ImageIcon imgLupa = new ImageIcon(getClass().getResource("/imagens/Lupa.png"));
        btnPesquisar = new JButton();
        btnPesquisar.addActionListener(this);
        btnPesquisar.setBounds(180, 290, imgLupa.getIconWidth(), imgLupa.getIconHeight());
        btnPesquisar.setIcon(imgLupa);
        btnPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPesquisar.setOpaque(false);
        btnPesquisar.setBorder(null);
        btnPesquisar.setContentAreaFilled(false);
        btnPesquisar.setFocusable(false);
        layeredPane.add(btnPesquisar, JLayeredPane.MODAL_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnPesquisar) {
            tblClinicas.pesquisarClinica(txtPesquisa.getText(), pnlEspecialidades.getFiltrosEspecialidades());
        }
        
    }
    
    
    
}
