
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.tablesData.Conta;
import healthyclinics.db.ContaDAO;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PasswordFieldShadow;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.validacoes.ValidacaoLogin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TelaLogin implements ActionListener {
    
    private final ConfigFrame configFrame;
    private JLayeredPane layeredPane;
    private TextFieldShadow txtCpf;
    private PasswordFieldShadow txtSenha;
    private ButtonShadow btnEntrar;
    private RoundedButton btnVoltar;
    private Conta contaLogada;
    private ValidacaoLogin validacaoLogin;
    
    
    public TelaLogin(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }

    
    public TextFieldShadow getTxtCpf() {
        return txtCpf;
    }

    public PasswordFieldShadow getTxtSenha() {
        return txtSenha;
    }

    public Conta getContaLogada() {
        return contaLogada;
    }

    public void setContaLogada(Conta contaLogada) {
        this.contaLogada = contaLogada;
    }
    
    
    public void configTelaLogin() {
        
        // Configura os elementos da tela
        configLayers();
        configImages();
        configLabels();
        configLines();
        configTxt();
        configBtns();
        
        // Validação das informações inseridas
        validacaoLogin = new ValidacaoLogin(this, layeredPane);
        
        configFrame.getMainFrame().repaint();
        
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
    
    
    public void configImages() {
        
        // Imagem da água
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/WaterCircleLogin.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(0, 0, imgWater.getIconWidth(), imgWater.getIconHeight());
        lblWater.setIcon(imgWater);
        layeredPane.add(lblWater, JLayeredPane.DEFAULT_LAYER);
        
        // Logo HealthyClinics Médio
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(639, 44, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        layeredPane.add(lblLogo, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configLabels() {
        
        // Texto "Digite o CPF:"
        JLabel lblCpf = new JLabel("Digite o CPF:");
        lblCpf.setBounds(726, 440, 245, 27);
        lblCpf.setForeground(new Color(0x395886));
        lblCpf.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblCpf, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Digite a Senha:"
        JLabel lblNome = new JLabel("Digite a Senha:");
        lblNome.setBounds(726, 561, 245, 27);
        lblNome.setForeground(new Color(0x395886));
        lblNome.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblNome, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Configurações das linhas desenhadas na tela
        
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1920, 1080);
        
        // Canto superior esquerdo
        lines.addLine(604, 44, 804, 44, 3, new Color(0x395886)); // Linha horizontal
        lines.addLine(604, 44, 604, 244, 3, new Color(0x395886)); // Linha vertical
        
        // Canto superior direito
        lines.addLine(1104, 44, 1304, 44, 3, new Color(0x395886)); // Linha horizontal
        lines.addLine(1304, 44, 1304, 244, 3, new Color(0x395886)); // Linha vertical
        
        // Canto inferior esquerdo
        lines.addLine(604, 1036, 804, 1036, 3, new Color(0x395886)); // Linha horizontal
        lines.addLine(604, 963, 604, 1036, 3, new Color(0x395886)); // Linha vertical
        
        // Canto inferior direito
        lines.addLine(1073, 1036, 1304, 1036, 3, new Color(0x395886)); // Linha horizontal
        lines.addLine(1304, 963, 1304, 1036, 3, new Color(0x395886)); // Linha vertical
        
        
        layeredPane.add(lines, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configTxt() {
        
        // TextField para inserir o CPF
        txtCpf = new TextFieldShadow(45);
        txtCpf.setBounds(702, 467, 502, 55);
        txtCpf.setBackground(new Color(0xD5DEEF));
        txtCpf.setForeground(new Color(0x395886));
        txtCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtCpf, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir a senha
        txtSenha = new PasswordFieldShadow(45);
        txtSenha.setBounds(702, 588, 502, 55);
        txtSenha.setBackground(new Color(0xD5DEEF));
        txtSenha.setForeground(new Color(0x395886));
        txtSenha.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtSenha, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void configBtns() {
        
        // Botão "Entrar"
        btnEntrar = new ButtonShadow("Entrar", 50);
        btnEntrar.addActionListener(this);
        btnEntrar.setBounds(851, 791, 206, 55);
        btnEntrar.setBackground(new Color(0x395886));
        btnEntrar.setForeground(new Color(0xF0F3FA));
        btnEntrar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnEntrar.setShadowOpacity((float) 0.3);
        btnEntrar.setShadowSize(3);
        btnEntrar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnEntrar, JLayeredPane.MODAL_LAYER);
        
        // Botão "Voltar"
        btnVoltar = new RoundedButton("Voltar");
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(882, 865, 144, 28);
        btnVoltar.setForeground(new Color(0x395886));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 22));
        btnVoltar.transparentButton();
        layeredPane.add(btnVoltar, JLayeredPane.MODAL_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnEntrar) { // Se o botão entrar for pressionado
            
            // Se os dados inseridos são válidos
            if(validacaoLogin.validarLogin(txtCpf.getText(), String.valueOf(txtSenha.getPassword()))) {
                
                try {   
                    // Buscando a conta no banco
                    ContaDAO dao = new ContaDAO();
                    contaLogada = dao.get(txtCpf.getText());
                    
                } catch(Exception ex) {
                    validacaoLogin.getErros().mostrarOrientacao();
                }
                
                // Se a conta foi encontrada
                if(contaLogada != null) {
                    // Abre a tela home
                    configFrame.getMainFrame().remove(layeredPane);
                    configFrame.getManager().getTelas().getTelaHome().configHome();   
                }   
            }
            
        } else if(e.getSource() == btnVoltar) { // Se o botão voltar for pressionado
            
            // Abre a tela de escolhas
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaInicial().configTelaInicial();
            
        }
        
    }
    
}
