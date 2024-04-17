
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.CirclePanelShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TelaSobre {

    private final ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private JLayeredPane layeredPane;
    private JPanel mainPanel;
    private JPanel pnlSobre;
    private CirclePanelShadow pnlLogo;
    private PanelShadow pnlTextSobre;
    private PanelShadow pnlTextObjetivo;
    
    public TelaSobre(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaSobre() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configLines();
        configText();
        
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
        mainPanel.setPreferredSize(new Dimension(1920, 1290));
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
        
        // Configura os painéis da tela
        configPnls();
        
        // Adiciona as layers no frame
        configFrame.getMainFrame().add(scrollPane);
        
    }
    
    
    public void configPnls() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Barra de botões
        ButtonBar buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 0;
        gbc.weighty = 0;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel de imagens
        pnlSobre = new JPanel();
        pnlSobre.setPreferredSize(new Dimension(1920, 928));
        pnlSobre.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlSobre.setLayout(null);
        pnlSobre.setBorder(null);
        mainPanel.add(pnlSobre, gbc);
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1920, 928);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        pnlSobre.add(layeredPane);
        
        // Painel redondo
        pnlLogo = new CirclePanelShadow();
        pnlLogo.setBounds(731, 41, 458, 441);
        pnlLogo.setBackground(new Color(0xF0F3FA));
        pnlLogo.setShadowSize(3);
        pnlLogo.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlLogo, JLayeredPane.DEFAULT_LAYER);
        
        // Painel com textos sobre a Healthy Clinics
        pnlTextSobre = new PanelShadow(30);
        pnlTextSobre.setBounds(126, 544, 670, 306);
        pnlTextSobre.setBackground(new Color(0xD5DEEF));
        pnlTextSobre.setShadowSize(3);
        pnlTextSobre.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlTextSobre, JLayeredPane.PALETTE_LAYER);
        
        // Painel com textos sobre o objetivo da Healthy Clinics
        pnlTextObjetivo = new PanelShadow(30);
        pnlTextObjetivo.setBounds(1134, 544, 670, 306);
        pnlTextObjetivo.setBackground(new Color(0xD5DEEF));
        pnlTextObjetivo.setShadowSize(3);
        pnlTextObjetivo.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlTextObjetivo, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configImages() {
        
        // Imagem de fundo
        ImageIcon imgBackground = new ImageIcon(getClass().getResource("/imagens/ImgBackgroundSobre.png"));
        JLabel lblBackground = new JLabel();
        lblBackground.setBounds(-75, 0, imgBackground.getIconWidth(), imgBackground.getIconHeight());
        lblBackground.setIcon(imgBackground);
        layeredPane.add(lblBackground, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem logo
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(-86, 55, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        pnlLogo.add(lblLogo);
        
    }
    
    
    public void configLines() {
        
        // Linhas do painel "Sobre Nós"
        LinesComponent linesSobre = new LinesComponent();
        linesSobre.setBounds(0, 0, pnlTextSobre.getWidth(), pnlTextSobre.getHeight());
        
        linesSobre.addLine(42, 45, 217, 45, 2, new Color(0x395886)); // Linha horizontal, canto superior esquerdo
        linesSobre.addLine(454, 45, 629, 45, 2, new Color(0x395886)); // Linha vertical, canto superior esquerdo

        pnlTextSobre.add(linesSobre);
        
        // Linhas do painel "Objetivo"
        LinesComponent linesObjetivo = new LinesComponent();
        linesObjetivo.setBounds(0, 0, pnlTextObjetivo.getWidth(), pnlTextObjetivo.getHeight());
        
        linesObjetivo.addLine(42, 45, 217, 45, 2, new Color(0x395886)); // Linha horizontal, canto superior esquerdo
        linesObjetivo.addLine(454, 45, 629, 45, 2, new Color(0x395886)); // Linha vertical, canto superior esquerdo

        pnlTextObjetivo.add(linesObjetivo);
        
    }
    
    
    public void configText() {
        
        // Texto "Sobre Nós"
        JLabel lblSobre = new JLabel("Sobre Nós");
        lblSobre.setBounds(217, 16, 237, 58);
        lblSobre.setForeground(new Color(0x152E52));
        lblSobre.setFont(new Font("Rufina", Font.BOLD, 35));
        lblSobre.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTextSobre.add(lblSobre);
        
        DefaultTextPane txaEmpresa = new DefaultTextPane();
        txaEmpresa.setText("• Empresa desenvolvida e elaborada por Adriano Barros, 16 anos e estudante do curso: Técnico em Desenvolvimento de Sistemas - Senac.");
        txaEmpresa.setBounds(63, 86, 545, 91);
        txaEmpresa.setForeground(new Color(0x152E52));
        txaEmpresa.setFont(new Font("Rufina", Font.PLAIN, 18));
        pnlTextSobre.add(txaEmpresa);
        
        DefaultTextPane txaCriada = new DefaultTextPane();
        txaCriada.setText("• Criada para a utilização no Projeto Integrador, projeto desenvolvido ao decorrer do curso, com fins educacionais.");
        txaCriada.setBounds(63, 174, 545, 91);
        txaCriada.setForeground(new Color(0x152E52));
        txaCriada.setFont(new Font("Rufina", Font.PLAIN, 18));
        pnlTextSobre.add(txaCriada);
        
        // Texto "Objetivo"
        JLabel lblObjetivo = new JLabel("Objetivo");
        lblObjetivo.setBounds(217, 16, 237, 58);
        lblObjetivo.setForeground(new Color(0x152E52));
        lblObjetivo.setFont(new Font("Rufina", Font.BOLD, 35));
        lblObjetivo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTextObjetivo.add(lblObjetivo);
        
        DefaultTextPane txaIndice = new DefaultTextPane();
        txaIndice.setText("• Criar uma plataforma que facilite o agendamento e a busca de clínicas que atendam a todos os tipos de necessidades na área da saúde. Buscando aumentar o índice de acesso à saúde para toda a população.");
        txaIndice.setBounds(49, 86, 572, 94);
        txaIndice.setForeground(new Color(0x152E52));
        txaIndice.setFont(new Font("Rufina", Font.PLAIN, 18));
        pnlTextObjetivo.add(txaIndice);
        
        DefaultTextPane txaAjudar = new DefaultTextPane();
        txaAjudar.setText("• Ajudar e incentivar as pessoas a cuidarem de sua saúde, com mais atenção e frequência. E mostrar a importância de uma rotina de exames periódicos na prevenção e no tratamento de doenças.");
        txaAjudar.setBounds(53, 177, 565, 94);
        txaAjudar.setForeground(new Color(0x152E52));
        txaAjudar.setFont(new Font("Rufina", Font.PLAIN, 18));
        pnlTextObjetivo.add(txaAjudar);
        
    }
    
    
    public void atualizarTxt(JTextField txt) {
        
        if(txt.isEnabled()) {
            txt.setEnabled(false);
            txt.setBackground(new Color(0x395886));
        } else {
            txt.setEnabled(true);
            txt.setBackground(new Color(0xF0F3FA));
        }
        
    }
    
    
}
