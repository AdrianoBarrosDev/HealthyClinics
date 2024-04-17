
package healthyclinics.ferramentas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.tablesData.Conta;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.telas.PerfilClinica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ButtonBar extends JPanel implements ActionListener {

    private ConfigFrame configFrame;
    private JScrollPane tela;
    private Conta conta;
    private RoundedButton btnLogo;
    private RoundedButton btnHome;
    private RoundedButton btnConsultas;
    private RoundedButton btnClinicas;
    private RoundedButton btnMinhaClinica;
    private RoundedButton btnSobre;
    private RoundedButton btnPerfil;
    private CardImage fotoPerfil;
    private JLayeredPane layeredPane;
    private String tipoConta;
    
    
    public ButtonBar(ConfigFrame configFrame, JScrollPane tela) {
        this.configFrame = configFrame;
        this.tela = tela;
        
        // Busca a conta logada no banco de dados
        ContaDAO contaDao = new ContaDAO();
        this.conta = contaDao.get(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getId());
        
        this.tipoConta = conta.getTipoConta().getDescricao();
    }

    
    public ConfigFrame getConfigFrame() {
        return configFrame;
    }

    public void setConfigFrame(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }

    public JScrollPane getTela() {
        return tela;
    }

    public void setTela(JScrollPane tela) {
        this.tela = tela;
    }

    public RoundedButton getBtnLogo() {
        return btnLogo;
    }

    public void setBtnLogo(RoundedButton btnLogo) {
        this.btnLogo = btnLogo;
    }

    public RoundedButton getBtnHome() {
        return btnHome;
    }

    public void setBtnHome(RoundedButton btnHome) {
        this.btnHome = btnHome;
    }

    public RoundedButton getBtnConsultas() {
        return btnConsultas;
    }

    public void setBtnConsultas(RoundedButton btnConsultas) {
        this.btnConsultas = btnConsultas;
    }

    public RoundedButton getBtnClinicas() {
        return btnClinicas;
    }

    public void setBtnClinicas(RoundedButton btnClinicas) {
        this.btnClinicas = btnClinicas;
    }

    public RoundedButton getBtnSobre() {
        return btnSobre;
    }

    public void setBtnSobre(RoundedButton btnSobre) {
        this.btnSobre = btnSobre;
    }

    public RoundedButton getBtnPerfil() {
        return btnPerfil;
    }

    public void setBtnPerfil(RoundedButton btnPerfil) {
        this.btnPerfil = btnPerfil;
    }

    public CardImage getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(CardImage fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
    
    public JPanel getButtonBar() {
        
        
        
        configPanel();
        configButtons();
        return this;
        
    }
    
    
    public void configPanel() {
        
        setPreferredSize(new Dimension(1920, 152));
        setBackground(new Color(0xF0F3FA));
        setOpaque(true);
        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x395886)));
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(1735, 0 , 185, 152);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        this.add(layeredPane);
        
    }
    
    
    public void configButtons() {
        
        // Botão logo
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCSmall.png"));
        btnLogo = new RoundedButton("");
        btnLogo.addActionListener(this);
        btnLogo.setBounds(22, 4, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        btnLogo.setIcon(imgLogo);
        btnLogo.setBorder(null);
        this.add(btnLogo);
        
        // Botão "Home"
        btnHome = new RoundedButton("Home");
        btnHome.addActionListener(this);
        btnHome.setBounds(379, 60, 137, 39);
        btnHome.setForeground(new Color(0x395886));
        btnHome.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnHome.transparentButton();
        this.add(btnHome);
        
        // Botão "Clínicas"
        btnClinicas = new RoundedButton("Clínicas");
        btnClinicas.addActionListener(this);
        btnClinicas.setForeground(new Color(0x395886));
        btnClinicas.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnClinicas.transparentButton();
        this.add(btnClinicas);
        
        if(tipoConta.equals("Cliente")) {
            
            // Botão "Consultas"
            btnConsultas = new RoundedButton("Consultas");
            btnConsultas.addActionListener(this);
            btnConsultas.setBounds(551, 60, 166, 39);
            btnConsultas.setForeground(new Color(0x395886));
            btnConsultas.setFont(new Font("Volkhov", Font.BOLD, 25));
            btnConsultas.transparentButton();
            this.add(btnConsultas);
            
            // Posição do botão "Clínicas"
            btnClinicas.setBounds(752, 60, 137, 39);
            
        } else if(tipoConta.equals("Profissional")) {
            
            // Botão "Minha Clínica"
            btnMinhaClinica = new RoundedButton("Minha Clínica");
            btnMinhaClinica.addActionListener(this);
            btnMinhaClinica.setBounds(685, 60, 220, 39);
            btnMinhaClinica.setForeground(new Color(0x395886));
            btnMinhaClinica.setFont(new Font("Volkhov", Font.BOLD, 25));
            btnMinhaClinica.setHorizontalAlignment(SwingConstants.CENTER);
            btnMinhaClinica.transparentButton();
            this.add(btnMinhaClinica);
            
            // Posição do botão "Clínicas"
            btnClinicas.setBounds(528, 60, 137, 39);
            
        }
        
        
        // Botão "Sobre Nós"
        btnSobre = new RoundedButton("Sobre Nós");
        btnSobre.addActionListener(this);
        btnSobre.setBounds(924, 60, 160, 39);
        btnSobre.setForeground(new Color(0x395886));
        btnSobre.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnSobre.transparentButton();
        this.add(btnSobre);
        
        // Imagem de perfil
        ConvertBlob convertBlob = new ConvertBlob();
        ImageIcon imgPerfil = convertBlob.imgConvertida(conta.getImg());
        if(imgPerfil == null) {
            imgPerfil = new ImageIcon(getClass().getResource("/imagens/ImgContaDefault.png"));
        }
        fotoPerfil = new CardImage(imgPerfil, 60);
        fotoPerfil.setBounds(69, 60, 46, 45);
        layeredPane.add(fotoPerfil, JLayeredPane.DEFAULT_LAYER);
        
        // Botão perfil
        btnPerfil = new RoundedButton("");
        btnPerfil.addActionListener(this);
        btnPerfil.setBounds(69, 60, 46, 45);
        btnPerfil.setBorder(null);
        btnPerfil.transparentButton();
        layeredPane.add(btnPerfil, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnHome) {
            // Abre a tela home
            configFrame.getMainFrame().remove(tela);
            configFrame.getManager().getTelas().getTelaHome().configHome();
            
        } else if(e.getSource() == btnLogo) {
            // Abre a tela home
            configFrame.getMainFrame().remove(tela);
            configFrame.getManager().getTelas().getTelaHome().configHome();
            
        } else if(e.getSource() == btnConsultas) {
            // Abre a tela de consultas
            configFrame.getMainFrame().remove(tela);
            configFrame.getManager().getTelas().getTelaConsultas().configTelaConsultas();
            
        } else if(e.getSource() == btnClinicas) {
            // Abre a tela de clínicas
            configFrame.getMainFrame().remove(tela);
            configFrame.getManager().getTelas().getTelaClinicas().configTelaClinicas();
            
        } else if(e.getSource() == btnMinhaClinica) {
            
            // Busca a conta profissional no banco de dados
            ContaProfissionalDAO dao = new ContaProfissionalDAO();
            ContaProfissional contaProfissional = dao.get(conta.getId());
            
            // Se a conta não possui uma clínica
            if(contaProfissional.getClinica() == null) {
                // Abre a tela para cadastrar uma clínica
                configFrame.getMainFrame().remove(tela);
                configFrame.getManager().getTelas().getTelaGerenciarClinica().configGerenciarClinica();
                
            } else { // Se a conta possui uma clínica
                // Abre o perfil da clínica
                configFrame.getMainFrame().remove(tela);
                PerfilClinica perfilClinica = new PerfilClinica(configFrame, contaProfissional.getClinica());
                perfilClinica.configPerfilClinica();
                
            }
            
        } else if(e.getSource() == btnSobre) {
            // Abre a tela sobre nós
            configFrame.getMainFrame().remove(tela);
            configFrame.getManager().getTelas().getTelaSobre().configTelaSobre();
            
        } else if(e.getSource() == btnPerfil) {
            
            // Abre a tela de perfil
            configFrame.getMainFrame().remove(tela);
            
            // Se o tipo da conta for "Cliente" abre o perfil cliente
            if(tipoConta.equals("Cliente")) {
                configFrame.getManager().getTelas().getTelaPerfil().getPerfilCliente().configTelaPerfil();
                
            } else if(tipoConta.equals("Profissional")) { // Se o tipo da conta for "Profissional" abre o perfil profissional
                configFrame.getManager().getTelas().getTelaPerfil().getPerfilProfissional().configTelaPerfil();
            }
            
        }
        
    }

    
    
}
