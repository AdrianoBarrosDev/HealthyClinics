
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
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.layouts.RoundedButtonRadius;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaContinuar implements ActionListener {
    
    private final ConfigFrame configFrame;
    private final JComponent tela;
    private JLayeredPane layersConfirmar;
    private RoundedButtonRadius btnContinuar;
    private RoundedButtonRadius btnVoltar;
    private JTextField txtBuscarCpf;
    
    public TelaContinuar(ConfigFrame configFrame, JComponent tela, JTextField txtBuscarCpf) {
        this.configFrame = configFrame;
        this.tela = tela;
        this.txtBuscarCpf = txtBuscarCpf;
    }
    
    
    public void configTelaContinuar() {
        
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
        JLabel lblTitle = new JLabel("Médico Adicionado");
        lblTitle.setBounds(759, 484, 400, 27);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Volkhov", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        layersConfirmar.add(lblTitle, JLayeredPane.POPUP_LAYER);

        // Configurações da TextPane
        DefaultTextPane txa = new DefaultTextPane();
        txa.setText("O médico foi adicionado com sucesso em sua clínica! Você deseja adicionar outro médico?");
        txa.setBounds(759, 521, 400, 51);
        txa.setForeground(new Color(0x777777));
        txa.setFont(new Font("Rufina", Font.PLAIN, 15));
        layersConfirmar.add(txa, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void configBtn() {
        
        // Configurações do botão "Adicionar"
        btnContinuar = new RoundedButtonRadius("Adicionar", 10, new Color(0x289B2D));
        btnContinuar.addActionListener(this);
        btnContinuar.setBounds(977, 592, 182, 39);
        btnContinuar.setBackground(new Color(0x289B2D));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Volkhov", Font.BOLD, 18));
        btnContinuar.setFocusable(false);
        btnContinuar.configBackgroundBtn();
        btnContinuar.getPnlBackground().setBackground(new Color(0x289B2D));
        layersConfirmar.add(btnContinuar.getPnlBackground(), JLayeredPane.POPUP_LAYER);
        
        // Configurações do botão "Voltar"
        btnVoltar = new RoundedButtonRadius("Voltar", 10, new Color(0x289B2D));
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(759, 592, 182, 39);
        btnVoltar.setBackground(new Color(0x289B2D));
        btnVoltar.setForeground(new Color(0x289B2D));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 18));
        btnVoltar.setFocusable(false);
        layersConfirmar.add(btnVoltar, JLayeredPane.POPUP_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Se o botão continuar for pressionado
        if(e.getSource() == btnContinuar) {
            
            txtBuscarCpf.setText("");
            
            // Remove a tela de confirmação
            configFrame.getMainFrame().remove(layersConfirmar);
            configFrame.getMainFrame().add(tela);
            configFrame.getMainFrame().repaint();
            
        } else if(e.getSource() == btnVoltar) {
            
            // Abre o perfil da clínica
            configFrame.getMainFrame().remove(layersConfirmar);
            
            ContaProfissionalDAO dao = new ContaProfissionalDAO();
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada()).getClinica());
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
    
    
}
