
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.*;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.shadows.TextPanel;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Home {

    private ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JPanel pnlImages;
    private JPanel pnlInicial;
    private PanelShadow pnlInicialMain;
    private PanelShadow pnlInicialBusque;
    private PanelShadow pnlInicialMarque;
    private JPanel pnlComplementar;
    private TextPanel pnlComplementarResponsavel;
    private TextPanel pnlComplementarPaciente;
    private TextPanel pnlComplementarMedico;
    
    public Home(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configHome() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configLines();
        configLabels();
        
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
        mainPanel.setPreferredSize(new Dimension(1920, 2370));
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
        pnlImages = new JPanel();
        pnlImages.setPreferredSize(new Dimension(1920, 506));
        pnlImages.setBackground(new Color(0xD5DEEF));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlImages.setLayout(null);
        pnlImages.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPanel.add(pnlImages, gbc);
        
        // Painel inicial da tela home
        pnlInicial = new JPanel();
        pnlInicial.setPreferredSize(new Dimension(1920, 676));
        pnlInicial.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0;
        pnlInicial.setLayout(null);
        mainPanel.add(pnlInicial, gbc);
        
        // Painel complementar da tela home
        pnlComplementar = new JPanel();
        pnlComplementar.setPreferredSize(new Dimension(1920, 826));
        pnlComplementar.setBackground(new Color(0xD5DEEF));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 3;
        gbc.weighty = 0;
        pnlComplementar.setLayout(null);
        mainPanel.add(pnlComplementar, gbc);
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 4;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        
        // Painéis presentes no painel inicial
        pnlInicialMain = new PanelShadow(60);
        pnlInicialMain.setBounds(112, 226, 1086, 310);
        pnlInicialMain.setBackground(new Color(0xB1C9EF));
        pnlInicialMain.setShadowSize(3);
        pnlInicialMain.setShadowType(ShadowType.BOT);
        pnlInicial.add(pnlInicialMain);
        
        pnlInicialBusque = new PanelShadow(60);
        pnlInicialBusque.setBounds(682, 36, 356, 90);
        pnlInicialBusque.setBackground(new Color(0xF0F3FA));
        pnlInicialBusque.setShadowSize(3);
        pnlInicialBusque.setShadowType(ShadowType.BOT);
        pnlInicialMain.add(pnlInicialBusque);
        
        pnlInicialMarque = new PanelShadow(60);
        pnlInicialMarque.setBounds(682, 150, 356, 125);
        pnlInicialMarque.setBackground(new Color(0xF0F3FA));
        pnlInicialMarque.setShadowSize(3);
        pnlInicialMarque.setShadowType(ShadowType.BOT);
        pnlInicialMain.add(pnlInicialMarque);
        
        
        // Painéis presentes no painel complementar
        pnlComplementarPaciente = new TextPanel(50, 20);
        pnlComplementarPaciente.setBounds(759, 186, 439, 181);
        pnlComplementarPaciente.setBackground(new Color(0xB1C9EF));
        pnlComplementarPaciente.setShadowSize(2);
        pnlComplementarPaciente.setShadowType(ShadowType.BOT);
        pnlComplementarPaciente.configPnlCenter();
        pnlComplementar.add(pnlComplementarPaciente);
        
        pnlComplementarMedico = new TextPanel(50, 20);
        pnlComplementarMedico.setBounds(1271, 186, 579, 181);
        pnlComplementarMedico.setBackground(new Color(0xB1C9EF));
        pnlComplementarMedico.setShadowSize(2);
        pnlComplementarMedico.setShadowType(ShadowType.BOT);
        pnlComplementarMedico.configPnlCenter();
        pnlComplementar.add(pnlComplementarMedico);
        
        pnlComplementarResponsavel = new TextPanel(50, 20);
        pnlComplementarResponsavel.setBounds(759, 452, 1086, 181);
        pnlComplementarResponsavel.setBackground(new Color(0xB1C9EF));
        pnlComplementarResponsavel.setShadowSize(2);
        pnlComplementarResponsavel.setShadowType(ShadowType.BOT);
        pnlComplementarResponsavel.configPnlCenter();
        pnlComplementar.add(pnlComplementarResponsavel);
        
    }
    
    
    public void configImages() {
        
        // Image slider
        ImageSlider slider = new ImageSlider();
        pnlImages.add(slider);
        
    }
    
    
    public void configLines() {
        
        // Linhas do painel inicial
        LinesComponent linesPnlInicial = new LinesComponent();
        linesPnlInicial.setBounds(0, 0, 1920, 676);
        
        linesPnlInicial.addLine(62, 45, 262, 45, 3, new Color(0x395886)); // Linha horizontal no painel inicial
        linesPnlInicial.addLine(62, 45, 62, 245, 3, new Color(0x395886)); // Linha vertical no painel inicial

        linesPnlInicial.addLine(1733, 622, 1858, 622, 2, new Color(0x395886)); // Linha horizontal no painel inicial
        linesPnlInicial.addLine(1858, 582, 1858, 622, 2, new Color(0x395886)); // Linha vertical no painel inicial

        pnlInicial.add(linesPnlInicial);
        
        // Linhas do painel inicial
        LinesComponent linesPnlComplementar = new LinesComponent();
        linesPnlComplementar.setBounds(0, 0, 1920, 826);
        
        linesPnlComplementar.addLine(794, 173, 844, 173, 4, new Color(0x395886)); // Linha "Paciente" esquerda
        linesPnlComplementar.addLine(990, 173, 1165, 173, 4, new Color(0x395886)); // Linha "Paciente" direita
        
        linesPnlComplementar.addLine(1307, 173, 1357, 173, 4, new Color(0x395886)); // Linha "Médico" esquerda
        linesPnlComplementar.addLine(1503, 173, 1806, 173, 4, new Color(0x395886)); // Linha "Médico" direita
        
        linesPnlComplementar.addLine(794, 437, 844, 437, 4, new Color(0x395886)); // Linha "Responsável por ..." esquerda
        linesPnlComplementar.addLine(1285, 437, 1811, 437, 4, new Color(0x395886)); // Linha "Responsável por ..." direita

        pnlComplementar.add(linesPnlComplementar);
        
    }
    
    
    public void configLabels() {
        
        // Painel Inicial
        
        // Texto "Seja Bem-Vindo" + nome do usuário
        String nomeUsuario = configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getNome();
        if(nomeUsuario.contains(" ")) {
            nomeUsuario = nomeUsuario.substring(0, nomeUsuario.indexOf(" "));
        }
        JLabel lblBoasVindas = new JLabel("Seja Bem-Vindo " + nomeUsuario);
        lblBoasVindas.setBounds(151, 77, 1021, 119);
        lblBoasVindas.setForeground(new Color(0x395886));
        lblBoasVindas.setFont(new Font("Rufina", Font.BOLD, 75));
        pnlInicial.add(lblBoasVindas);
        
        DefaultTextPane txaInfoHC = new DefaultTextPane();
        txaInfoHC.setBounds(49, 45, 586, 236);
        txaInfoHC.setText("A Healthy Clinic é uma plataforma desenvolvida para melhorar a sua e a qualidade de vida de muitas pessoas. "
                + "Nosso grande objetivo é facilitar a comunicação e o procedimento de agendamento de consultas em diferentes tipos de clínicas.");
        txaInfoHC.setForeground(new Color(0x152E52));
        txaInfoHC.setFont(new Font("Volkhov", Font.PLAIN, 25));
        pnlInicialMain.add(txaInfoHC);
        
        DefaultTextPane txaInfoBusque = new DefaultTextPane();
        txaInfoBusque.setBounds(18, 12, 320, 56);
        txaInfoBusque.setText("Busque clínicas de diferentes áreas na plataforma.");
        txaInfoBusque.setForeground(new Color(0x152E52));
        txaInfoBusque.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInicialBusque.add(txaInfoBusque);
        
        DefaultTextPane txaInfoMarque = new DefaultTextPane();
        txaInfoMarque.setBounds(18, 15, 320, 95);
        txaInfoMarque.setText("Marque consultas nas clínicas desejadas facilmente, não deixe sua saúde esperando.");
        txaInfoMarque.setForeground(new Color(0x152E52));
        txaInfoMarque.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInicialMarque.add(txaInfoMarque);
        
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/ImgWaterDropping.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(1206, 46, 714, 576);
        lblWater.setIcon(imgWater);
        pnlInicial.add(lblWater);
        
        
        // Painel Complementar
        
        // Imagem logo HealthyClinics
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCLarge.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(2, 140, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        pnlComplementar.add(lblLogo);
        
        // Textos Painel Responsável
        JLabel lblPaciente = new JLabel("Paciente", SwingConstants.CENTER);
        lblPaciente.setBounds(844, 156, 146, 30);
        lblPaciente.setForeground(new Color(0x395886));
        lblPaciente.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlComplementar.add(lblPaciente);
        
        DefaultTextPane txaPaciente = new DefaultTextPane();
        txaPaciente.setBounds(8, 40, 380, 60);
        txaPaciente.setText("Veja clínicas e cadastre consultas quando desejar.");
        txaPaciente.setForeground(new Color(0x395886));
        txaPaciente.setFont(new Font("Volkhov", Font.PLAIN, 23));
        pnlComplementarPaciente.getPnlCenter().add(txaPaciente);
        
        // Textos Painel Médico
        JLabel lblMedico = new JLabel("Médico", SwingConstants.CENTER);
        lblMedico.setBounds(1357, 156, 146, 30);
        lblMedico.setForeground(new Color(0x395886));
        lblMedico.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlComplementar.add(lblMedico);
        
        DefaultTextPane txaMedico = new DefaultTextPane();
        txaMedico.setBounds(40, 40, 451, 60);
        txaMedico.setText("Acesse informações da sua clínica e veja as consultas marcadas.");
        txaMedico.setForeground(new Color(0x395886));
        txaMedico.setFont(new Font("Volkhov", Font.PLAIN, 23));
        pnlComplementarMedico.getPnlCenter().add(txaMedico);
        
        // Textos Painel Responsável
        JLabel lblResponsavel = new JLabel("Responsável por uma Clínica?", SwingConstants.CENTER);
        lblResponsavel.setBounds(844, 420, 441, 30);
        lblResponsavel.setForeground(new Color(0x395886));
        lblResponsavel.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlComplementar.add(lblResponsavel);
        
        JLabel lblCadastre = new JLabel("Cadastre sua clínica na Healthy Clinic! Conheça algumas vantagens:");
        lblCadastre.setBounds(28, 10, 957, 39);
        lblCadastre.setForeground(new Color(0x395886));
        lblCadastre.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlComplementarResponsavel.getPnlCenter().add(lblCadastre);
        
        JTextArea txaResponsavel = new JTextArea();
        txaResponsavel.setBounds(28, 30, 957, 80);
        txaResponsavel.setText("\n• Através de nossa plataforma você consegue administrar sua clínica e ver as consultas marcadas."
                + "\n• Sua clínica poderá ser mais reconhecida e buscada atráves da pesquisa de clínicas.");
        txaResponsavel.setForeground(new Color(0x395886));
        txaResponsavel.setFont(new Font("Volkhov", Font.PLAIN, 20));
        txaResponsavel.setEditable(false);
        txaResponsavel.setOpaque(false);
        txaResponsavel.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        pnlComplementarResponsavel.getPnlCenter().add(txaResponsavel);
        
        
    }
    
    
}
