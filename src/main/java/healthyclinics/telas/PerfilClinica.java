
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.CardImage;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.ferramentas.FiltrosEspecialidades;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.ferramentas.Recomendacoes;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.shadows.TextPanel;
import healthyclinics.tablesData.Conta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class PerfilClinica implements ActionListener {

    private ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private Clinica clinica;
    private JPanel mainPanel;
    private JPanel pnlMedicos;
    private JPanel pnlPerfil;
    private JPanel thirdPanel;
    private JPanel fourthPanel;
    private PanelShadow pnlFoto;
    private TextPanel pnlDescricao;
    private TextPanel pnlInformacoes;
    private FiltrosEspecialidades pnlEspecialidades;
    private Recomendacoes recomendacoes;
    private EndBar pnlFinal;
    private ButtonShadow btnAcoesClinica;
    private GridBagConstraints gbc;
    private TelaMinhaClinica telaMinhaClinica;
    private ConvertBlob convertBlob;
    
    public PerfilClinica(ConfigFrame configFrame, Clinica clinica) {
        this.configFrame = configFrame;
        this.clinica = clinica;
    }

    
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
    
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getPnlMedicos() {
        return pnlMedicos;
    }

    public void setPnlMedicos(JPanel pnlMedicos) {
        this.pnlMedicos = pnlMedicos;
    }

    public JPanel getThirdPanel() {
        return thirdPanel;
    }

    public void setThirdPanel(JPanel thirdPanel) {
        this.thirdPanel = thirdPanel;
    }

    public JPanel getFourthPanel() {
        return fourthPanel;
    }

    public void setFourthPanel(JPanel fourthPanel) {
        this.fourthPanel = fourthPanel;
    }

    public Recomendacoes getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(Recomendacoes recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public EndBar getPnlFinal() {
        return pnlFinal;
    }

    public void setPnlFinal(EndBar pnlFinal) {
        this.pnlFinal = pnlFinal;
    }
    
    
    public void configPerfilClinica() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configLines();
        configText();
        configTxt();
        configBtn();
        
        // Define o scroll no início da tela
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                }
            }
        );
        
        
        // Conta do usuário
        Conta conta = configFrame.getManager().getTelas().getTelaLogin().getContaLogada();
        
        // Se o usuário estiver utilizando uma conta profissional
        if(conta.getTipoConta().getDescricao().equals("Profissional")) {
            
            ContaProfissionalDAO dao = new ContaProfissionalDAO();
            ContaProfissional contaProfissional = dao.getConta(conta);
            
            // Se o usuário possuí uma clínica
            if(contaProfissional.getClinica() != null) {
                
                // Se a clínica do usuário for igual a clínica que está sendo mostrada
                if(contaProfissional.getClinica().getCnpj().equals(clinica.getCnpj())) {
                    configMinhaClinica(); // Configura o perfil da clínica, com diversos elementos permitidos apenas para o responsável da clínica
                }
                
            }
            
        }
        
        
        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void configGrid() {
        
        // Configura as layers
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1920, 2715));
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

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Barra de botões
        ButtonBar buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel de imagens
        pnlMedicos = new JPanel();
        pnlMedicos.setPreferredSize(new Dimension(1920, 225));
        pnlMedicos.setBackground(new Color(0x152E52));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlMedicos.setLayout(null);
        pnlMedicos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPanel.add(pnlMedicos, gbc);
        
        // Painel inicial da tela home
        pnlPerfil = new JPanel();
        pnlPerfil.setPreferredSize(new Dimension(1920, 1236));
        pnlPerfil.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlPerfil.setLayout(null);
        mainPanel.add(pnlPerfil, gbc);
        
        // Painel com outras clínicas recomendadas
        thirdPanel = new JPanel();
        thirdPanel.setPreferredSize(new Dimension(1920, 892));
        thirdPanel.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        thirdPanel.setLayout(null);
        mainPanel.add(thirdPanel, gbc);
        
        // Recomendações
        if(recomendacoes == null) {
            recomendacoes = new Recomendacoes(configFrame, scrollPane);
            recomendacoes.setBounds(159, 0, recomendacoes.getWidth(), recomendacoes.getHeight());
            recomendacoes.changeTextLabel("Outras Clínicas");
            thirdPanel.add(recomendacoes);
        } else {
            thirdPanel.add(recomendacoes);
        }
        
        // Painel final (créditos)
        pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        // Background foto da clínica
        pnlFoto = new TextPanel(45, 15);
        pnlFoto.setBounds(163, 117, 797, 463);
        pnlFoto.setBackground(new Color(0x152E52));
        pnlFoto.setShadowSize(3);
        pnlFoto.setShadowType(ShadowType.BOT);
        pnlPerfil.add(pnlFoto);
        
        // Painel com a descrição
        pnlDescricao = new TextPanel(45, 15);
        pnlDescricao.setBounds(1053, 117, 705, 113);
        pnlDescricao.setBackground(new Color(0xB1C9EF));
        pnlDescricao.setShadowSize(3);
        pnlDescricao.setShadowType(ShadowType.BOT);
        pnlDescricao.configPnlCenter();
        pnlPerfil.add(pnlDescricao);
        
        // Background do painel especialidades
        PanelShadow backgroundEspecialidades = new PanelShadow(45);
        backgroundEspecialidades.setBounds(1053, 308, 413, 272);
        backgroundEspecialidades.setShadowSize(3);
        backgroundEspecialidades.setShadowType(ShadowType.BOT);
        backgroundEspecialidades.setBackground(new Color(0xB1C9EF));
        backgroundEspecialidades.setLayout(null);
        pnlPerfil.add(backgroundEspecialidades);
        
        // Painel de especialidades
        pnlEspecialidades = new FiltrosEspecialidades(40, clinica);
        pnlEspecialidades.setBounds(27, 26, pnlEspecialidades.getPreferredSize().width, pnlEspecialidades.getPreferredSize().height);
        backgroundEspecialidades.add(pnlEspecialidades);
        
        // Painel com informações sobre a clínica
        pnlInformacoes = new TextPanel(45, 20);
        pnlInformacoes.setBounds(163, 698, 1595, 412);
        pnlInformacoes.setBackground(new Color(0xB1C9EF));
        pnlInformacoes.setShadowSize(3);
        pnlInformacoes.setShadowType(ShadowType.BOT);
        pnlInformacoes.configPnlCenter();
        pnlInformacoes.getPnlCenter().setBackground(new Color(0xD5DEEF));
        pnlPerfil.add(pnlInformacoes);
        
        
        
    }
    
    
    public void configImages() {
        
        // Foto da clínica
        convertBlob = new ConvertBlob();
        ImageIcon imgClinica = convertBlob.imgConvertida(clinica.getImg());
        if(imgClinica == null) {
            imgClinica = new ImageIcon(getClass().getResource("/imagens/ImgDefaultClinica.png"));
        }
        CardImage cardClinic = new CardImage(imgClinica);
        cardClinic.setBounds(32, 25, 733, 409);
        pnlFoto.add(cardClinic);
        
        // Imagem água
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/ImgWaterClinic.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(1471, 91, imgWater.getIconWidth(), imgWater.getIconHeight());
        lblWater.setIcon(imgWater);
        pnlPerfil.add(lblWater);
        
        
        // Adiciona as imagens dos médicos da clínica
        Rectangle firstPosition = new Rectangle(145, 33, 170, 160);
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        List<ContaProfissional> listaMedicos = dao.getListaMedicos(clinica);
        
        for(int i = 0; i < 7; i++) {
            
            ImageIcon imgPerfil;
            ImageIcon imgDefaultConta = new ImageIcon(getClass().getResource("/imagens/ImgContaDefault.png"));
            ImageIcon imgDefault = new ImageIcon(getClass().getResource("/imagens/ImgMedicosDefault.png"));
            
            if(i == 0) {
                if(listaMedicos.size() > i) {
                    
                    imgPerfil = convertBlob.imgConvertida(listaMedicos.get(i).getConta().getImg());
                    
                    CardImage imgMedico;
                    if(imgPerfil == null) {
                        imgMedico = new CardImage(imgDefaultConta, 200);
                    } else {
                        imgMedico = new CardImage(imgPerfil, 200);
                    }
                    
                    imgMedico.setBounds(firstPosition.x, 33, 172, 160);
                    pnlMedicos.add(imgMedico);

                } else {
                    
                    CardImage imgMedico = new CardImage(imgDefault);
                    imgMedico.setBounds(firstPosition.x, 33, 172, 160);
                    imgMedico.setBackground(Color.green);
                    pnlMedicos.add(imgMedico);
                    
                }
                
            } else {
                
                if(listaMedicos.size() > i) {

                    imgPerfil = convertBlob.imgConvertida(listaMedicos.get(i).getConta().getImg());
                    
                    CardImage imgMedico;
                    if(imgPerfil == null) {
                        imgMedico = new CardImage(imgDefaultConta, 200);
                    } else {
                        imgMedico = new CardImage(imgPerfil, 200);
                    }
                    
                    imgMedico.setBounds(firstPosition.x + (i * 71) + (i * 172), 33, 172, 160);
                    pnlMedicos.add(imgMedico);

                } else {
                    
                    CardImage imgMedico = new CardImage(imgDefault);
                    imgMedico.setBounds(firstPosition.x + (i * 71) + (i * 172), 33, 172, 160);
                    imgMedico.setBackground(Color.green);
                    pnlMedicos.add(imgMedico);
                }
                
            }

        }
            
        
        
    }
    
    
    public void configLines() {
        
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1532, 353);
        
        lines.addLine(50, 41, 250, 41, 2, new Color(0x395886)); // Linha horizontal
        lines.addLine(50, 41, 50, 155, 2, new Color(0x395886)); // Linha vertical
        
        lines.addLine(1304, 41, 1504, 41, 2, new Color(0x395886)); // Linha horizontal
        lines.addLine(1504, 41, 1504, 155, 2, new Color(0x395886)); // Linha vertical
        
        lines.addLine(745, 52, 745, 302, 2, new Color(0x395886)); // Linha vertical central
       
        pnlInformacoes.getPnlCenter().add(lines);
        
    }
    
    
    public void configText() {
        
        // Nome da clínica
        JLabel lblNomeClinica = new JLabel(clinica.getNome());
        lblNomeClinica.setBounds(170, 23, 584, 86);
        lblNomeClinica.setForeground(new Color(0x395886));
        lblNomeClinica.setFont(new Font("Rufina", Font.BOLD, 50));
        pnlPerfil.add(lblNomeClinica);
        
        // Texto "Descrição"
        JLabel lblDescricao = new JLabel("Descrição");
        lblDescricao.setBounds(1092, 74, 189, 26);
        lblDescricao.setForeground(new Color(0x395886));
        lblDescricao.setFont(new Font("Rufina", Font.BOLD, 25));
        pnlPerfil.add(lblDescricao);
        
        // Descrição da clínica
        DefaultTextPane txaDescricao = new DefaultTextPane();
        txaDescricao.setBounds(5, 10, 660, 70);
        txaDescricao.setText(clinica.getDescricao());
        txaDescricao.setForeground(new Color(0x395886));
        txaDescricao.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDescricao.getPnlCenter().add(txaDescricao);
        
        // Texto "Especialidades"
        JLabel lblEspecialidades = new JLabel("Especialidades");
        lblEspecialidades.setBounds(1092, 265, 189, 26);
        lblEspecialidades.setForeground(new Color(0x395886));
        lblEspecialidades.setFont(new Font("Rufina", Font.BOLD, 25));
        pnlPerfil.add(lblEspecialidades);
        
        // Texto "Mais Informações"
        JLabel lblInformacoes = new JLabel("Mais Informações");
        lblInformacoes.setBounds(212, 660, 350, 26);
        lblInformacoes.setForeground(new Color(0x395886));
        lblInformacoes.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlPerfil.add(lblInformacoes);
        
        // Texto "Nome da Clínica"
        JLabel lblNome = new JLabel("Nome da Clínica");
        lblNome.setBounds(151, 70, 300, 26);
        lblNome.setForeground(new Color(0x395886));
        lblNome.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlInformacoes.getPnlCenter().add(lblNome);
        
        // Texto "CNPJ"
        JLabel lblCnpj = new JLabel("CNPJ");
        lblCnpj.setBounds(874, 70, 300, 26);
        lblCnpj.setForeground(new Color(0x395886));
        lblCnpj.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlInformacoes.getPnlCenter().add(lblCnpj);
        
        // Texto "Endereço"
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(151, 198, 300, 26);
        lblEndereco.setForeground(new Color(0x395886));
        lblEndereco.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlInformacoes.getPnlCenter().add(lblEndereco);
        
        // Texto "CRM do Responsável"
        JLabel lblCrm = new JLabel("CRM do Responsável");
        lblCrm.setBounds(874, 198, 300, 26);
        lblCrm.setForeground(new Color(0x395886));
        lblCrm.setFont(new Font("Rufina", Font.BOLD, 30));
        pnlInformacoes.getPnlCenter().add(lblCrm);
        
    }
    
    
    public void configTxt() {
        
        // TextField para mostrar o nome da clínica
        TextFieldShadow txtNome = new TextFieldShadow(45);
        txtNome.setText(clinica.getNome());
        txtNome.setBounds(130, 104, 507, 51);
        txtNome.setBackground(new Color(0xF0F3FA));
        txtNome.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtNome.setEnabled(false);
        txtNome.setBorder(BorderFactory.createEmptyBorder(0, 30, 3, 5));
        pnlInformacoes.getPnlCenter().add(txtNome);
        
        // TextField para mostrar o cnpj da clínica
        TextFieldShadow txtCnpj = new TextFieldShadow(45);
        txtCnpj.setText(clinica.getCnpj());
        txtCnpj.setBounds(853, 104, 507, 51);
        txtCnpj.setBackground(new Color(0xF0F3FA));
        txtCnpj.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCnpj.setEnabled(false);
        txtCnpj.setBorder(BorderFactory.createEmptyBorder(0, 30, 3, 5));
        pnlInformacoes.getPnlCenter().add(txtCnpj);
        
        // TextField para mostrar o endereço da clínica
        TextFieldShadow txtEndereco = new TextFieldShadow(45);
        txtEndereco.setText(clinica.getEndereco());
        txtEndereco.setBounds(130, 232, 507, 51);
        txtEndereco.setBackground(new Color(0xF0F3FA));
        txtEndereco.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtEndereco.setEnabled(false);
        txtEndereco.setBorder(BorderFactory.createEmptyBorder(0, 30, 3, 5));
        pnlInformacoes.getPnlCenter().add(txtEndereco);
        
        // TextField para mostrar o crm da clínica
        TextFieldShadow txtCrm = new TextFieldShadow(45);
        txtCrm.setText(clinica.getResponsavel().getCrm());
        txtCrm.setBounds(853, 232, 507, 51);
        txtCrm.setBackground(new Color(0xF0F3FA));
        txtCrm.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCrm.setEnabled(false);
        txtCrm.setBorder(BorderFactory.createEmptyBorder(0, 30, 3, 5));
        pnlInformacoes.getPnlCenter().add(txtCrm);
        
    }
    
    
    public void configBtn() {
        
        // Se a conta for do tipo cliente, é possível marcar a consulta 
        if(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getTipoConta().getDescricao().equals("Cliente")) {
            
            // Botão para marcar a consulta na clínica
            btnAcoesClinica = new ButtonShadow("MARCAR CONSULTA", 30);
            
            btnAcoesClinica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // Abre a tela para marcar a consulta
                    configFrame.getMainFrame().remove(scrollPane);
                    MarcarConsulta telaMarcarConsulta = new MarcarConsulta(configFrame, clinica);
                    telaMarcarConsulta.configMarcarConsulta();
                    
                }
            });
            
            btnAcoesClinica.setBounds(760, 39, 200, 50);
            btnAcoesClinica.setBackground(new Color(0x395886));
            btnAcoesClinica.setForeground(new Color(0xF0F3FA));
            btnAcoesClinica.setFont(new Font("Volkhov", Font.BOLD, 16));
            btnAcoesClinica.setShadowSize(2);
            btnAcoesClinica.setShadowType(ShadowType.CENTER);
            btnAcoesClinica.changeColors(new Color(0x395886), new Color(0x638ECB));
            pnlPerfil.add(btnAcoesClinica);
            
        } else { // Se a conta for do tipo profissional
            
            // Se o usuário for o responsável pela clínica
            ContaProfissionalDAO dao = new ContaProfissionalDAO();
            ContaProfissional contaProfissional = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
            
            if(contaProfissional.getClinica() != null) {
                if(contaProfissional.getCrm().equals(clinica.getResponsavel().getCrm())) {
                    
                    // Botão para editar as informações da clínica
                    btnAcoesClinica = new ButtonShadow("EDITAR INFORMAÇÕES", 30);
                    btnAcoesClinica.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            // Abre a tela de gerenciamento de clínica
                            configFrame.getMainFrame().remove(scrollPane);
                            configFrame.getManager().getTelas().getTelaGerenciarClinica().configGerenciarClinica();

                        }
                    });

                    btnAcoesClinica.setBounds(760, 39, 200, 50);
                    btnAcoesClinica.setBackground(new Color(0x395886));
                    btnAcoesClinica.setForeground(new Color(0xF0F3FA));
                    btnAcoesClinica.setFont(new Font("Volkhov", Font.BOLD, 14));
                    btnAcoesClinica.setShadowSize(2);
                    btnAcoesClinica.setShadowType(ShadowType.CENTER);
                    btnAcoesClinica.changeColors(new Color(0x395886), new Color(0x638ECB));
                    pnlPerfil.add(btnAcoesClinica);
                
                }
            }
        }
        
    }
    
    
    
    public void configMinhaClinica() {
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        ContaProfissional contaProfissional = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
        
        telaMinhaClinica = new TelaMinhaClinica(this, configFrame, contaProfissional);
        if(clinica.getResponsavel().getCrm().equals(contaProfissional.getCrm())) {
            telaMinhaClinica.configResponsavel();
        } else {
            telaMinhaClinica.configMedico();
        }
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
    
}
