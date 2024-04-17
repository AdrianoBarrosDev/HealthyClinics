
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.shadows.ButtonShadow;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TelaInicial implements ActionListener {

    private final ConfigFrame configFrame;
    private JLayeredPane layeredPane;
    private ButtonShadow btnComecar;
    private RoundedButton btnEntrar;
    private RoundedButton btnCadastrar;
    
    
    public TelaInicial(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaInicial() {
        
        // Configuração dos elementos da tela
        configLayers();
        configImages();
        configTextPane();
        configLines();
        configBtn();
        
        configFrame.getMainFrame().setVisible(true);
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
        
        // Imagem círculo de água
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/WaterCircleInicial.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(887, 0, 1033, 990);
        lblWater.setIcon(imgWater);
        layeredPane.add(lblWater, JLayeredPane.PALETTE_LAYER);
        
        // Logo Healthy Clinics Médio
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(240, 50, 630, 346);
        lblLogo.setIcon(imgLogo);
        layeredPane.add(lblLogo, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configTextPane() {
        
        SimpleAttributeSet atributo = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributo, StyleConstants.ALIGN_CENTER);
        
        JTextPane txaMain = new JTextPane();
        txaMain.setText("VENHA CUIDAR DA SUA SAÚDE");
        txaMain.setBounds(138, 398, 835, 265);
        txaMain.setForeground(new Color(0x152E52));
        txaMain.setBackground(null);
        txaMain.setFont(new Font("Rufina", Font.BOLD, 100));
        txaMain.setParagraphAttributes(atributo, false);
        txaMain.setEditable(false);
        layeredPane.add(txaMain, JLayeredPane.PALETTE_LAYER);
        
        JTextPane txaSecondary = new JTextPane();
        txaSecondary.setText("Encontre o atendimento clínico que você precisa aqui, de uma maneira fácil e rápida.");
        txaSecondary.setBounds(296, 696, 518, 100);
        txaSecondary.setForeground(new Color(0x395886));
        txaSecondary.setBackground(null);
        txaSecondary.setFont(new Font("Volkhov", Font.PLAIN, 25));
        txaSecondary.setParagraphAttributes(atributo, false);
        txaSecondary.setEditable(false);
        layeredPane.add(txaSecondary, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Configurações das linhas desenhadas na tela
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1920, 1080);
        lines.addLine(66, 50, 66, 250, 3, new Color(0x395886)); // Vertical Line (canto superior esquerdo)
        lines.addLine(67, 50, 267, 50, 3, new Color(0x395886)); // Horizontal Line (canto superior esquerdo)
        lines.addLine(66, 1042, 766, 1042, 3, new Color(0x395886)); // Horizontal Line (canto inferior esquerdo)
        lines.addLine(1862, 842, 1862, 1042, 3, new Color(0x395886)); // Vertical Line (canto inferior direito)
        layeredPane.add(lines, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configBtn() {
        
        // Botão "Começar"
        btnComecar = new ButtonShadow("Começar Agora", 55);
        btnComecar.addActionListener(this);
        btnComecar.setBounds(450, 840, 210, 59);
        btnComecar.setBackground(new Color(0x395886));
        btnComecar.setForeground(new Color(0xF0F3FA));
        btnComecar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnComecar.setShadowSize(3);
        btnComecar.setShadowOpacity((float) 0.3);
        btnComecar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnComecar, JLayeredPane.MODAL_LAYER);
        
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
        btnCadastrar.addActionListener(this);
        btnCadastrar.setBounds(1684, 50, 190, 39);
        btnCadastrar.setForeground(new Color(0x395886));
        btnCadastrar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCadastrar.transparentButton();
        layeredPane.add(btnCadastrar, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnComecar || e.getSource() == btnEntrar) { // Se o botão "Começar" for pressionado"
            
            // Abre a tela de Login
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaLogin().configTelaLogin();
            
        } else if(e.getSource() == btnCadastrar) { // Se o botão "Cadastrar" for pressionado"
            
            // Abre a tela de escolha de conta
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaEscolha().configTelaEscolha();
            
        }
        
    }
    
    
}
