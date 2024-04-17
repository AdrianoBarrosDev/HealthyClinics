
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TelaEscolha implements ActionListener {

    private final ConfigFrame configFrame;
    private JLayeredPane layeredPane;
    private PanelShadow pnlBackgroundCliente;
    private PanelShadow pnlCliente;
    private PanelShadow pnlInfoCliente;
    private PanelShadow pnlBackgroundPro;
    private PanelShadow pnlPro;
    private PanelShadow pnlInfoPro;
    private RoundedButton btnLogo;
    private RoundedButton btnEntrar;
    private RoundedButton btnCadastrar;
    private ButtonShadow btnCliente;
    private ButtonShadow btnProfissional;
    
    
    public TelaEscolha(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaEscolha() {
        
        // Configura os elementos da tela
        configLayers();
        configPnls();
        configLabels();
        configImages();
        configLines();
        configBtns();
        
        configFrame.getMainFrame().repaint(); // Atualiza o frame
        
    }
    
    
    public void configLayers() {
        
        // Configuração das layers
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1920, 1080);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        
        // Adiciona as layers no frame
        configFrame.getMainFrame().add(layeredPane);
        
    }
    
    
    public void configPnls() {
        
        // Painéis da conta "Cliente"
        pnlBackgroundCliente = new PanelShadow(50);
        pnlBackgroundCliente.setBounds(259, 347, 589, 590);
        pnlBackgroundCliente.setBackground(new Color(0x152E52));
        pnlBackgroundCliente.setShadowSize(3);
        pnlBackgroundCliente.setShadowType(ShadowType.BOT_RIGHT);
        layeredPane.add(pnlBackgroundCliente, JLayeredPane.PALETTE_LAYER);
        
        pnlCliente = new PanelShadow(60);
        pnlCliente.setBounds(375, 385, 356, 68);
        pnlCliente.setBackground(new Color(0x395886));
        pnlCliente.setShadowSize(3);
        pnlCliente.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlCliente, JLayeredPane.MODAL_LAYER);
        
        pnlInfoCliente = new PanelShadow(60);
        pnlInfoCliente.setBounds(78, 138, 433, 402);
        pnlInfoCliente.setBackground(new Color(0xD5DEEF));
        pnlInfoCliente.setShadowSize(3);
        pnlInfoCliente.setShadowType(ShadowType.BOT);
        pnlBackgroundCliente.add(pnlInfoCliente);
        
        
        // Painéis da conta "Profissional"
        pnlBackgroundPro = new PanelShadow(50);
        pnlBackgroundPro.setBounds(1072, 347, 589, 590);
        pnlBackgroundPro.setBackground(new Color(0x152E52));
        pnlBackgroundPro.setShadowSize(3);
        pnlBackgroundPro.setShadowType(ShadowType.BOT_RIGHT);
        layeredPane.add(pnlBackgroundPro, JLayeredPane.PALETTE_LAYER);
        
        pnlPro = new PanelShadow(60);
        pnlPro.setBounds(1188, 385, 356, 68);
        pnlPro.setBackground(new Color(0x395886));
        pnlPro.setShadowSize(3);
        pnlPro.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlPro, JLayeredPane.MODAL_LAYER);
        
        pnlInfoPro = new PanelShadow(60);
        pnlInfoPro.setBounds(78, 138, 433, 402);
        pnlInfoPro.setBackground(new Color(0xD5DEEF));
        pnlInfoPro.setShadowSize(3);
        pnlInfoPro.setShadowType(ShadowType.BOT);
        pnlBackgroundPro.add(pnlInfoPro);
        
    }
    
    
    public void configLabels() {
        
        // Texto "Escolha um Tipo de Conta"
        JLabel lblEscolha = new JLabel("Selecione uma Conta");
        lblEscolha.setBounds(456, 189, 1015, 113);
        lblEscolha.setForeground(new Color(0x152E52));
        lblEscolha.setFont(new Font("Rufina", Font.BOLD, 100));
        layeredPane.add(lblEscolha, JLayeredPane.DEFAULT_LAYER);
        
        // Texto "Cliente"
        JLabel lblCliente = new JLabel("CLIENTE");
        lblCliente.setBounds(105, 15, 150, 31);
        lblCliente.setForeground(new Color(0xF0F3FA));
        lblCliente.setFont(new Font("Volkhov", Font.BOLD, 30));
        pnlCliente.add(lblCliente);
        
        // Texto "Paciente"
        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setBounds(45, 22, 115, 25);
        lblPaciente.setForeground(new Color(0x395886));
        lblPaciente.setFont(new Font("Volkhov", Font.BOLD, 25));
        pnlInfoCliente.add(lblPaciente);
        
        // Texto "Profissional"
        JLabel lblProfissional = new JLabel("PROFISSIONAL");
        lblProfissional.setBounds(60, 15, 240, 31);
        lblProfissional.setForeground(new Color(0xF0F3FA));
        lblProfissional.setFont(new Font("Volkhov", Font.BOLD, 30));
        pnlPro.add(lblProfissional);
        
        // Texto "Médico"
        JLabel lblMédico = new JLabel("Médico");
        lblMédico.setBounds(45, 22, 115, 25);
        lblMédico.setForeground(new Color(0x395886));
        lblMédico.setFont(new Font("Volkhov", Font.BOLD, 25));
        pnlInfoPro.add(lblMédico);
        
        // Texto "Médico Responsável Clínica"
        JLabel lblResponsavel = new JLabel("Médico Responsável Clínica");
        lblResponsavel.setBounds(45, 194, 360, 28);
        lblResponsavel.setForeground(new Color(0x395886));
        lblResponsavel.setFont(new Font("Volkhov", Font.BOLD, 25));
        pnlInfoPro.add(lblResponsavel);
        
        SimpleAttributeSet atributo = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributo, StyleConstants.ALIGN_LEFT);
        
        // Informações das funcionalidades da conta "Cliente"
        JTextArea txaInfoCliente = new JTextArea();
        txaInfoCliente.setText(
                "· Cadastre suas informações para agendar consultas facilmente.\n" +
                "\n" +
                "· Cadastre seu convênio, se houver.\n" +
                "\n" +
                "· Pesquise clínicas de diferentes áreas.\n" +
                "\n" +
                "· Agende consultas na clínica de sua preferência.\n" +
                "\n" +
                "· Veja suas consultas agendadas.\n" +
                "\n" +
                "· Edite informações no seu perfil de paciente.\n"
        );
        txaInfoCliente.setBounds(30, 67, 393, 312);
        txaInfoCliente.setBackground(null);
        txaInfoCliente.setForeground(new Color(0x152E52));
        txaInfoCliente.setFont(new Font("Volkhov", Font.PLAIN, 17));
        txaInfoCliente.setEditable(false);
        txaInfoCliente.setLineWrap(true);
        txaInfoCliente.setWrapStyleWord(true);
        pnlInfoCliente.add(txaInfoCliente);
        
        // Informações das funcionalidades da conta "Profissional" para médicos
        JTextArea txaInfoMedicos = new JTextArea();
        txaInfoMedicos.setText(
                "· Cadastre seu CRM.\n" +
                "· Trabalhe pela plataforma.\n" +
                "· Veja as suas consultas agendadas.\n" +
                "· Edite informações do seu perfil profissional."
        );
        txaInfoMedicos.setBounds(30, 67, 393, 115);
        txaInfoMedicos.setBackground(null);
        txaInfoMedicos.setForeground(new Color(0x152E52));
        txaInfoMedicos.setFont(new Font("Volkhov", Font.PLAIN, 17));
        txaInfoMedicos.setEditable(false);
        txaInfoMedicos.setLineWrap(true);
        txaInfoMedicos.setWrapStyleWord(true);
        pnlInfoPro.add(txaInfoMedicos);
        
        // Informações das funcionalidades da conta "Profissional" para responsáveis por clínicas
        JTextArea txaInfoResponsavel = new JTextArea();
        txaInfoResponsavel.setText(
                "· Cadastre seu CRM.\n" +
                "· Crie o perfil da sua clínica.\n" +
                "· Veja as consultas agendadas da sua clínica.\n" +
                "· Edite informações do seu perfil e da sua clínica.\n" +
                "· Cadastre os médicos da sua clínica, para terem acesso às consultas agendadas."
        );
        txaInfoResponsavel.setBounds(30, 232, 393, 150);
        txaInfoResponsavel.setBackground(null);
        txaInfoResponsavel.setForeground(new Color(0x152E52));
        txaInfoResponsavel.setFont(new Font("Volkhov", Font.PLAIN, 17));
        txaInfoResponsavel.setEditable(false);
        txaInfoResponsavel.setLineWrap(true);
        txaInfoResponsavel.setWrapStyleWord(true);
        pnlInfoPro.add(txaInfoResponsavel);
        
    }
    
    
    public void configImages() {
        
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCSmall.png"));
        
        JLabel lblLogoCliente = new JLabel();
        lblLogoCliente.setBounds(190, -4, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogoCliente.setIcon(imgLogo);
        pnlBackgroundCliente.add(lblLogoCliente);
        
        JLabel lblLogoPro = new JLabel();
        lblLogoPro.setBounds(190, -4, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogoPro.setIcon(imgLogo);
        pnlBackgroundPro.add(lblLogoPro);
        
        
        ImageIcon imgCircleWater = new ImageIcon(getClass().getResource("/imagens/WaterCircleEscolha.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(0, 372, imgCircleWater.getIconWidth(), imgCircleWater.getIconHeight());
        lblWater.setIcon(imgCircleWater);
        layeredPane.add(lblWater, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Configura as linhas desenhadas na tela
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 300, 300);
        lines.addLine(67, 50, 267, 50, 3, new Color(0x395886));
        lines.addLine(66, 50, 66, 250, 3, new Color(0x395886));
        layeredPane.add(lines, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configBtns() {
        
        // Botão logo para voltar para a tela inicial
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCSmall.png"));
        btnLogo = new RoundedButton("");
        btnLogo.addActionListener(this);
        btnLogo.setBounds(870, 50, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        btnLogo.setIcon(imgLogo);
        btnLogo.setBorder(null);
        layeredPane.add(btnLogo, JLayeredPane.PALETTE_LAYER);
        
        // Botão "Entrar"
        btnEntrar = new RoundedButton("Entrar");
        btnEntrar.addActionListener(this);
        btnEntrar.setBounds(1512, 50, 137, 39);
        btnEntrar.setForeground(new Color(0x395886));
        btnEntrar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnEntrar.transparentButton();
        layeredPane.add(btnEntrar, JLayeredPane.MODAL_LAYER);
        
        // Botão "Cadastrar"
        btnCadastrar = new RoundedButton("Cadastrar-se");
        btnCadastrar.setBounds(1684, 50, 190, 39);
        btnCadastrar.setForeground(new Color(0x395886));
        btnCadastrar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCadastrar.transparentButton();
        layeredPane.add(btnCadastrar, JLayeredPane.MODAL_LAYER);
        
        // Botão para escolher a conta "Cliente"
        btnCliente = new ButtonShadow("Selecionar", 50);
        btnCliente.addActionListener(this);
        btnCliente.setBounds(434, 973, 210, 60);
        btnCliente.setBackground(new Color(0x152E52));
        btnCliente.setForeground(new Color(0xF0F3FA));
        btnCliente.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCliente.changeColors(new Color(0x152E52), new Color(0x395886));
        layeredPane.add(btnCliente, JLayeredPane.PALETTE_LAYER);
        
        // Botão para escolher a conta "Profissional"
        btnProfissional = new ButtonShadow("Selecionar", 50);
        btnProfissional.addActionListener(this);
        btnProfissional.setBounds(1259, 973, 210, 60);
        btnProfissional.setBackground(new Color(0x152E52));
        btnProfissional.setForeground(new Color(0xF0F3FA));
        btnProfissional.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnProfissional.changeColors(new Color(0x152E52), new Color(0x395886));
        layeredPane.add(btnProfissional, JLayeredPane.PALETTE_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnCliente) { // Se o botão "Cliente" for pressionado
            
            // Abre a tela de cadastro para contas clientes
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getCadastroCliente().configCadastroCliente();
            
        } else if(e.getSource() == btnProfissional) { // Se o botão "Profissional" for pressionado
            
            // Abre a tela de cadastro para contas profissionais
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getCadastroProfissional().configCadastroProfissional();
            
        } else if(e.getSource() == btnLogo) { // Se o botão "Logo" for pressionado
            
            // Abre a tela inicial
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaInicial().configTelaInicial();
            
        } else if(e.getSource() == btnEntrar) { // Se o botão "Entrar" for pressionado
            
            // Abre a tela de Login
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaLogin().configTelaLogin();
            
        }
        
    }
    
    
    
}
