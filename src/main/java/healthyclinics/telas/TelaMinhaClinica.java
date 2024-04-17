
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.Recomendacoes;
import healthyclinics.ferramentas.TableConsultasProfissional;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.tablesData.ContaProfissional;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaMinhaClinica {

    private PerfilClinica perfilClinica;
    private ConfigFrame configFrame;
    private ContaProfissional conta;
    private JPanel mainPanel;
    private JPanel pnlMedicos;
    private JPanel thirdPanel;
    private JPanel fourthPanel;
    private JPanel pnlFinal;
    private JLayeredPane layeredPane;
    private PanelShadow pnlConsultasClinica;
    private Recomendacoes recomendacoes;
    private GridBagConstraints gbc;
    private TableConsultasProfissional tblConsultas;
    
    
    public TelaMinhaClinica(PerfilClinica perfilClinica, ConfigFrame configFrame, ContaProfissional conta) {
        
        this.conta = conta;
        this.perfilClinica = perfilClinica;
        this.configFrame = configFrame;
        this.mainPanel = perfilClinica.getMainPanel();
        this.pnlMedicos = perfilClinica.getPnlMedicos();
        this.thirdPanel = perfilClinica.getThirdPanel();
        this.fourthPanel = perfilClinica.getFourthPanel();
        this.pnlFinal = perfilClinica.getPnlFinal();
        this.recomendacoes = perfilClinica.getRecomendacoes();
        
        // Redimensiona o painel principal
        mainPanel.setPreferredSize(new Dimension(1920, 3645));
        mainPanel.remove(pnlFinal); // Remove o último painel
        
        // Remove as recomendações do terceiro painel
        thirdPanel.remove(recomendacoes);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Adiciona o quarto painel na tela
        fourthPanel = new JPanel();
        fourthPanel.setPreferredSize(new Dimension(1920, 930));
        fourthPanel.setBackground(new Color(0xF0F3FA));
        fourthPanel.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        mainPanel.add(fourthPanel, gbc);
        fourthPanel.add(recomendacoes);
        
        // Adiciona o painel final novamente
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        // Painel das consultas da clínica
        pnlConsultasClinica = new PanelShadow(40);
        pnlConsultasClinica.setBounds(-10, 35, 1940, 740);
        pnlConsultasClinica.setBackground(new Color(0x152E52));
        pnlConsultasClinica.setShadowType(ShadowType.BOT);
        thirdPanel.add(pnlConsultasClinica);
        
        // Configurações das layers
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(10, 0, 1920, 740);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        pnlConsultasClinica.add(layeredPane);
        
    }
    
    
    public void configResponsavel() {
        
        // Configura os elementos padrões
        configMinhaClinicaDefault();
        
        RoundedButton btnMais = new RoundedButton("+");
        btnMais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configFrame.getMainFrame().remove(perfilClinica.getScrollPane());
                AdicionarMedicos telaAdicionarMedicos = new AdicionarMedicos(configFrame);
                telaAdicionarMedicos.configTelaAdicionar();
            }
        });
        btnMais.setBounds(1807, 73, 80, 80);
        btnMais.setForeground(new Color(0xD5DEEF));
        btnMais.setFont(new Font("Volkhov", Font.BOLD, 80));
        btnMais.setHorizontalAlignment(SwingConstants.CENTER);
        btnMais.transparentButton();
        pnlMedicos.add(btnMais);
        
    }
    
    
    public void configMedico() {
        
        // Configura os elementos padrões
        configMinhaClinicaDefault();
        
    }
    
    
    public void configMinhaClinicaDefault() {
        
        // Configura as imagens
        configImages();
        
        // Configura a tabela de consultas
        configTblConsultas();
        
    }
    
    
    public void configImages() {
        
        // Imagem retângulo esquerdo no painel das consultas
        ImageIcon rectangleLeft = new ImageIcon(getClass().getResource("/imagens/RectangleConsultasLeft.png"));
        JLabel lblRectangleLeft = new JLabel();
        lblRectangleLeft.setBounds(0, 66, rectangleLeft.getIconWidth(), rectangleLeft.getIconHeight());
        lblRectangleLeft.setIcon(rectangleLeft);
        pnlConsultasClinica.add(lblRectangleLeft, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem retângulo direito no painel das consultas
        ImageIcon rectangleRight = new ImageIcon(getClass().getResource("/imagens/RectangleConsultasRight.png"));
        JLabel lblRectangleRight = new JLabel();
        lblRectangleRight.setBounds(1634, 0, rectangleRight.getIconWidth(), rectangleRight.getIconHeight());
        lblRectangleRight.setIcon(rectangleRight);
        pnlConsultasClinica.add(lblRectangleRight, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configTblConsultas() {
        
        // Painel de fundo da tabela de consultas
        PanelShadow backgroundTblConsultas = new PanelShadow(50);
        backgroundTblConsultas.setBounds(194, 153, 1531, 465);
        backgroundTblConsultas.setBackground(new Color(0xB1C9EF));
        backgroundTblConsultas.setShadowType(ShadowType.BOT);
        backgroundTblConsultas.setBorder(null);
        layeredPane.add(backgroundTblConsultas, JLayeredPane.PALETTE_LAYER);
        
        // Tabela com todas as consultas da clínica
        tblConsultas = new TableConsultasProfissional(configFrame, perfilClinica.getScrollPane(), conta);
        tblConsultas.setBounds(6, 28, 1519, 405);
        tblConsultas.gerarTabela();
        backgroundTblConsultas.add(tblConsultas);
        
        // Texto "Consultas" + o nome da clínica
        JLabel lblConsultas = new JLabel("Consultas " + conta.getClinica().getNome());
        lblConsultas.setBounds(212, 27, 1000, 126);
        lblConsultas.setForeground(new Color(0xD5DEEF));
        lblConsultas.setFont(new Font("Rufina", Font.BOLD, 60));
        pnlConsultasClinica.add(lblConsultas, JLayeredPane.PALETTE_LAYER);
        
        // Texto informando uma funcionalidade
        JLabel lblInformacoes = new JLabel("• Clique duas vezes na consulta, para vê-la individualmente.");
        lblInformacoes.setBounds(212, 635, 600, 30);
        lblInformacoes.setForeground(new Color(0xD5DEEF));
        lblInformacoes.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlConsultasClinica.add(lblInformacoes, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
}
