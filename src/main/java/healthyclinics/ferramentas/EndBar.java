
package healthyclinics.ferramentas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.ShadowType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class EndBar extends JPanel implements ActionListener {
    
    private final ConfigFrame configFrame;
    private final JScrollPane scrollPane;
    private ButtonShadow btnSobre;
    
    
    public EndBar(ConfigFrame configFrame, JScrollPane scrollPane) {
        this.configFrame = configFrame;
        this.scrollPane = scrollPane;
        configPanel();
        configTexts();
        configButtons();
    }
    
    
    public final void configPanel() {
        
        // Configura o painel
        setPreferredSize(new Dimension(1920, 210));
        setBackground(new Color(0x395886));
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,new Color(0x638ECB)));
        setOpaque(true);
        setLayout(null);
        
    }
    
    
    public final void configTexts() {
        
        // Imagem logo
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCWhite.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(36, 35, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        this.add(lblLogo);
        
        // Texto "Propósitos do Sistema"
        JLabel lblPropositos = new JLabel("Propósitos do Sistema:");
        lblPropositos.setBounds(341, 52, 233, 25);
        lblPropositos.setForeground(new Color(0xF0F3FA));
        lblPropositos.setFont(new Font("Volkhov", Font.PLAIN, 20));
        this.add(lblPropositos);
        
        // Propósitos do sistema
        JTextPane txaPropositos = new JTextPane();
        txaPropositos.setText("• Ajudar a melhorar a qualidade de vida da sociedade, atráves da área da saúde.\n" 
                + "• Sistema desenvolvido como um Projeto Integrador do Senac. \n" 
                + "• Melhorar minhas capacidades técnicas e visuais, planejando e desenvolvendo sistemas.");
        txaPropositos.setBounds(341, 94, 667, 63);
        txaPropositos.setForeground(new Color(0xF0F3FA));
        txaPropositos.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txaPropositos.setEditable(false);
        txaPropositos.setOpaque(false);
        txaPropositos.setCaret(null);
        this.add(txaPropositos);
        
        // Texto "Propósitos do Sistema"
        JLabel lblCreditos = new JLabel("Créditos:");
        lblCreditos.setBounds(1050, 52, 100, 25);
        lblCreditos.setForeground(new Color(0xF0F3FA));
        lblCreditos.setFont(new Font("Volkhov", Font.PLAIN, 20));
        this.add(lblCreditos);
        
        // Propósitos do sistema
        JTextPane txaCreditos = new JTextPane();
        txaCreditos.setText("• Desenvolvido e criado por Adriano Barros.\n" 
                + "• Algumas imagens presentes no sistema, foram retiradas da internet, apenas para fins educacionais.");
        txaCreditos.setBounds(1050, 94, 567, 63);
        txaCreditos.setForeground(new Color(0xF0F3FA));
        txaCreditos.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txaCreditos.setEditable(false);
        txaCreditos.setOpaque(false);
        txaCreditos.setCaret(null);
        this.add(txaCreditos);
        
        // Texto "Sobre Nós"
        JLabel lblSobre = new JLabel("Sobre Nós:");
        lblSobre.setBounds(1682, 52, 120, 25);
        lblSobre.setForeground(new Color(0xF0F3FA));
        lblSobre.setFont(new Font("Volkhov", Font.PLAIN, 20));
        this.add(lblSobre);
        
    }
    
    
    public final void configButtons() {
        
        btnSobre = new ButtonShadow("Saber Mais", 30);
        btnSobre.addActionListener(this);
        btnSobre.setBounds(1682, 91, 178, 45);
        btnSobre.setBackground(new Color(0xD5DEEF));
        btnSobre.setForeground(new Color(0x395886));
        btnSobre.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnSobre.setShadowSize(2);
        btnSobre.setShadowType(ShadowType.BOT);
        btnSobre.changeColors(new Color(0xD5DEEF), new Color(0x8AAEE0));
        this.add(btnSobre);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnSobre) {
            
            // Abre a tela Sobre Nós
            configFrame.getMainFrame().remove(scrollPane);
            configFrame.getManager().getTelas().getTelaSobre().configTelaSobre();
            
        }
        
    }

    
    
}
