
package healthyclinics.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import healthyclinics.classes.ConfigFrame;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.layouts.RoundedButtonRadius;
import healthyclinics.tablesData.Clinica;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class TelaConfirmar implements ActionListener {
    
    private final ConfigFrame configFrame;
    private final JComponent tela;
    private final Clinica clinica;
    private JLayeredPane layersConfirmar;
    private RoundedButtonRadius btnContinuar;
    
    public TelaConfirmar(ConfigFrame configFrame, JComponent tela, Clinica clinica) {
        this.configFrame = configFrame;
        this.tela = tela;
        this.clinica = clinica;
    }
    
    
    public void configTelaConfirmar() {
        
        // Configura as novas layers
        configLayers();
        
        // Configura os elementos da tela
        configImgs();
        configLabels();
        configBtn();
        
        
        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void configLayers() {
        // Configurações da layeredPane
        layersConfirmar = new JLayeredPane();
        layersConfirmar.setBounds(0, 0, 1920, 1080);
        layersConfirmar.setLayout(null);
        
        configFrame.getMainFrame().remove(tela);
        layersConfirmar.add(tela, JLayeredPane.DEFAULT_LAYER);
        configFrame.getMainFrame().add(layersConfirmar);
        
    }
    
    
    public void configImgs() {
        
        // Imagem transparente que deixa a tela mais escura
        ImageIcon imgMask = new ImageIcon(getClass().getResource("/imagens/Mask.png"));
        JLabel lblMask = new JLabel();
        lblMask.setBounds(0, 0, imgMask.getIconWidth(), imgMask.getIconHeight());
        lblMask.setIcon(imgMask);
        layersConfirmar.add(lblMask, JLayeredPane.PALETTE_LAYER);
        
        // Painel de informações
        ImageIcon imgPanel = new ImageIcon(getClass().getResource("/imagens/PanelConfirmar.png"));
        JLabel lblPanel = new JLabel();
        lblPanel.setBounds(739, 433, imgPanel.getIconWidth(), imgPanel.getIconHeight()); // 426
        lblPanel.setIcon(imgPanel);
        layersConfirmar.add(lblPanel, JLayeredPane.MODAL_LAYER);
        
        // GIF
        ImageIcon gifPanel = new ImageIcon(getClass().getResource("/imagens/Check.gif"));
        gifPanel.setImage(gifPanel.getImage().getScaledInstance(143, 143, Image.SCALE_DEFAULT));
        JLabel lblCheck = new JLabel();
        lblCheck.setBounds(888, 355, gifPanel.getIconWidth(), gifPanel.getIconHeight()); // 426
        lblCheck.setIcon(gifPanel);
        layersConfirmar.add(lblCheck, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void configLabels() {
        
        // Inicializando o atributo
        SimpleAttributeSet atributo = new SimpleAttributeSet();
        // Alinhando o texto no centro
        StyleConstants.setAlignment(atributo, StyleConstants.ALIGN_CENTER);
        
        // Texto "Consulta Marcada"
        JLabel lblTitle = new JLabel("Consulta Marcada");
        lblTitle.setBounds(867, 485, 185, 27);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Volkhov", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        layersConfirmar.add(lblTitle, JLayeredPane.POPUP_LAYER);

        // Configurações da TextPane
        DefaultTextPane txa = new DefaultTextPane();
        txa.setText("A consulta foi cadastrada com sucesso! Para mais informações acesse a tela de consultas.");
        txa.setBounds(784, 521, 352, 51);
        txa.setForeground(new Color(0x777777));
        txa.setFont(new Font("Rufina", Font.PLAIN, 15));
        layersConfirmar.add(txa, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void configBtn() {
        
        // Configurações do botão "Continuar"
        btnContinuar = new RoundedButtonRadius("Continuar", 10, new Color(0x289B2D));
        btnContinuar.addActionListener(this);
        btnContinuar.setBounds(868, 587, 182, 39);
        btnContinuar.setBackground(new Color(0x289B2D));
        btnContinuar.setForeground(new Color(0x289B2D));
        btnContinuar.setFont(new Font("Volkhov", Font.BOLD, 18));
        btnContinuar.setFocusable(false);
        layersConfirmar.add(btnContinuar, JLayeredPane.POPUP_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Se o botão continuar for pressionado
        if(e.getSource() == btnContinuar) {
            
            // Abre a tela do perfil da clínica
            configFrame.getMainFrame().remove(layersConfirmar);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, clinica);
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
    
    
}
