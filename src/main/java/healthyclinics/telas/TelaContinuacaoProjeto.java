
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.shadows.ButtonShadow;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TelaContinuacaoProjeto implements ActionListener {

    public final ConfigFrame configFrame;
    public JLayeredPane layeredPane;
    private ButtonShadow btnVoltar;
    
    
    public TelaContinuacaoProjeto(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaContinuacao() {
        
        // Configura os elementos da tela
        configLayers();
        configImages();
        configLabels();
        configLines();
        configBtn();
        
        // Atualiza o frame
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
        
        // Logo HealthyClinics Médio
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(646, 150, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        layeredPane.add(lblLogo, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configLabels() {
        
        // Texto "Créditos"
        JLabel lblNome = new JLabel("Desenvolvido por Adriano Barros");
        lblNome.setBounds(727, 755, 480, 36);
        lblNome.setForeground(new Color(0x395886));
        lblNome.setFont(new Font("Rufina", Font.PLAIN, 30));
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(lblNome, JLayeredPane.DEFAULT_LAYER);
        
        SimpleAttributeSet atributo = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributo, StyleConstants.ALIGN_CENTER);
        
        JTextPane txaContinuacao = new JTextPane();
        txaContinuacao.setText("Continuação na Etapa seguinte do Projeto Integrador (PI). Utilize a conta “Cliente” no momento.");
        txaContinuacao.setBounds(276, 526, 1385, 185);
        txaContinuacao.setForeground(new Color(0x152E52));
        txaContinuacao.setBackground(null);
        txaContinuacao.setFont(new Font("Rufina", Font.BOLD, 50));
        txaContinuacao.setParagraphAttributes(atributo, false);
        txaContinuacao.setEditable(false);
        layeredPane.add(txaContinuacao, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Configura as linhas desenhadas na tela
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1920, 1080);
        lines.addLine(66, 50, 66, 250, 3, new Color(0x395886)); // Vertical Line (canto superior esquerdo)
        lines.addLine(67, 50, 267, 50, 3, new Color(0x395886)); // Horizontal Line (canto superior esquerdo)
        lines.addLine(1654, 1030, 1854, 1030, 3, new Color(0x395886)); // Horizontal Line (canto inferior esquerdo)
        lines.addLine(1854, 830, 1854, 1030, 3, new Color(0x395886)); // Vertical Line (canto inferior direito)
        layeredPane.add(lines, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configBtn() {
        
        // Botão "Voltar"
        btnVoltar = new ButtonShadow("Voltar", 50);
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(851, 903, 206, 55);
        btnVoltar.setBackground(new Color(0x395886));
        btnVoltar.setForeground(new Color(0xF0F3FA));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnVoltar.setShadowOpacity((float) 0.3);
        btnVoltar.setShadowSize(3);
        btnVoltar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnVoltar, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Se o botão "Voltar" for pressionado
        if(e.getSource() == btnVoltar) {
            
            // Abre a tela inicial
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaInicial().configTelaInicial();
            
        }
        
    }
    
    
    
    
}
