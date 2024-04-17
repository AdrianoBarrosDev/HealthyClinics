
package healthyclinics.ferramentas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.layouts.RoundedButtonRadius;
import healthyclinics.shadows.TextPanel;
import healthyclinics.shadows.ShadowType;
import healthyclinics.tablesData.Clinica;
import healthyclinics.telas.PerfilClinica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Recomendacoes extends JPanel implements ActionListener {

    private ConfigFrame configFrame;
    private JComponent tela;
    private JLayeredPane layeredPane;
    private Clinica firstClinic;
    private Clinica secondClinic;
    private Clinica thirdClinic;
    private Clinica fourthClinic;
    private TextPanel firstPanel;
    private TextPanel secondPanel;
    private TextPanel thirdPanel;
    private TextPanel fourthPanel;
    private RoundedButtonRadius btnFirstClinic;
    private RoundedButtonRadius btnSecondClinic;
    private RoundedButtonRadius btnThirdClinic;
    private RoundedButtonRadius btnFourthClinic;
    private JLabel lblRecomendacoes;
    
    
    public Recomendacoes(ConfigFrame configFrame, JComponent tela) {
        
        this.configFrame = configFrame;
        this.tela = tela;
        
        this.setSize(1602, 684);
        this.setOpaque(false);
        this.setLayout(null);
        
        // Configura os elementos da tela
        configLayers();
        configPnls();
        configImages();
        configLine();
        configLabels();
        configTxa();
        configBtns();
        
    }

    
    public JComponent getTela() {
        return tela;
    }

    public void setTela(JComponent tela) {
        this.tela = tela;
    }
    
    
    public final void configLayers() {
        
        // Configuração das layers
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(this.getBounds());
        layeredPane.setLayout(null);
        this.add(layeredPane);
        
    }
    
    
    public final void configPnls() {
        
        // Painel da primeira clínica
        firstPanel = new TextPanel(35, 3);
        firstPanel.setBounds(0, 144, 345, 540);
        firstPanel.setBackground(new Color(0x152E52));
        firstPanel.setShadowSize(3);
        firstPanel.setShadowType(ShadowType.BOT);
        firstPanel.configPnlBorder(1, 28, 343, 470);
        layeredPane.add(firstPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Painel da segunda clínica
        secondPanel = new TextPanel(35, 3);
        secondPanel.setBounds(419, 144, 345, 540);
        secondPanel.setBackground(new Color(0x152E52));
        secondPanel.setShadowSize(3);
        secondPanel.setShadowType(ShadowType.BOT);
        secondPanel.configPnlBorder(1, 28, 343, 470);
        layeredPane.add(secondPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Painel da terceira clínica
        thirdPanel = new TextPanel(35, 3);
        thirdPanel.setBounds(838, 144, 345, 540);
        thirdPanel.setBackground(new Color(0x152E52));
        thirdPanel.setShadowSize(3);
        thirdPanel.setShadowType(ShadowType.BOT);
        thirdPanel.configPnlBorder(1, 28, 343, 470);
        layeredPane.add(thirdPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Painel da quarta clínica
        fourthPanel = new TextPanel(35, 3);
        fourthPanel.setBounds(1257, 144, 345, 540);
        fourthPanel.setBackground(new Color(0x152E52));
        fourthPanel.setShadowSize(3);
        fourthPanel.setShadowType(ShadowType.BOT);
        fourthPanel.configPnlBorder(1, 28, 343, 470);
        layeredPane.add(fourthPanel, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public final void configImages() {
        
        // Pesquisa de clínicas
        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> listaClinicas = dao.getListaClinicas();
        for(Clinica c : listaClinicas) {
            
            // Mayo Clinic
            if(c.getCnpj().equals("79.712.712/0001-93")) {
                firstClinic = c;
            }
            // Dentristy Clinic
            if(c.getCnpj().equals("23.694.827/0001-48")) {
                secondClinic = c;
            }
            // OfClinic
            if(c.getCnpj().equals("00.384.972/0001-27")) {
                thirdClinic = c;
            }
            // Pediatrics Clinic
            if(c.getCnpj().equals("29.734.876/0001-43")) {
                fourthClinic = c;
            }
            
        }
        
        // Imagem do perfil
        ImageIcon imgPerfil;
        
        // Objeto para converter o formato das imagens
        ConvertBlob convertBlob = new ConvertBlob();
        
        // Imagem da primeira clínica
        imgPerfil = convertBlob.imgConvertida(firstClinic.getImg());
        if(imgPerfil == null) {
            imgPerfil = new ImageIcon(getClass().getResource("/imagens/MayoClinicOriginal.png"));
        }
        CardImage firstImage = new CardImage(imgPerfil);
        firstImage.setBounds(2, 0, 339, 229);
        firstPanel.getPnlCenter().add(firstImage);

        // Imagem da segunda clínica
        imgPerfil = convertBlob.imgConvertida(firstClinic.getImg());
        if(imgPerfil == null) {
            imgPerfil = new ImageIcon(getClass().getResource("/imagens/DentristyClinicOriginal.png"));
        }
        CardImage secondImage = new CardImage(imgPerfil);
        secondImage.setBounds(2, 0, 339, 229);
        secondPanel.getPnlCenter().add(secondImage);

        // Imagem da terceira clínica
        imgPerfil = convertBlob.imgConvertida(firstClinic.getImg());
        if(imgPerfil == null) {
            imgPerfil = new ImageIcon(getClass().getResource("/imagens/OfClinicOriginal.png"));
        }
        CardImage thirdImage = new CardImage(imgPerfil);
        thirdImage.setBounds(2, 0, 339, 229);
        thirdPanel.getPnlCenter().add(thirdImage);
        
        // Imagem da quarta clínica
        imgPerfil = convertBlob.imgConvertida(firstClinic.getImg());
        if(imgPerfil == null) {
            imgPerfil = new ImageIcon(getClass().getResource("/imagens/PediatricsClinicOriginal.png"));
        }
        CardImage fourthImage = new CardImage(imgPerfil);   
        fourthImage.setBounds(2, 0, 339, 229);
        fourthPanel.getPnlCenter().add(fourthImage);
        
        
    }
    
    
    public final void configLabels() {
        
        // Texto "Recomendações"
        lblRecomendacoes = new JLabel("Recomendações");
        lblRecomendacoes.setBounds(0, 0, 600, 111);
        lblRecomendacoes.setForeground(new Color(0x395886));
        lblRecomendacoes.setFont(new Font("Rufina", Font.BOLD, 75));
        lblRecomendacoes.setAlignmentX(SwingConstants.LEFT);
        lblRecomendacoes.setAlignmentY(SwingConstants.CENTER);
        layeredPane.add(lblRecomendacoes, JLayeredPane.DEFAULT_LAYER);
        
        // Nome da primeira clínica
        JLabel lblFirstClinic = new JLabel(firstClinic.getNome());
        lblFirstClinic.setBounds(11, 240, 322, 49);
        lblFirstClinic.setForeground(new Color(0x395886));
        lblFirstClinic.setFont(new Font("Rufina", Font.BOLD, 30));
        centerAlignment(lblFirstClinic);
        firstPanel.getPnlCenter().add(lblFirstClinic);
        
        // Nome da segunda clínica
        JLabel lblSecondClinic = new JLabel(secondClinic.getNome());
        lblSecondClinic.setBounds(11, 240, 322, 49);
        lblSecondClinic.setForeground(new Color(0x395886));
        lblSecondClinic.setFont(new Font("Rufina", Font.BOLD, 30));
        centerAlignment(lblSecondClinic);
        secondPanel.getPnlCenter().add(lblSecondClinic);
        
        // Nome da terceira clínica
        JLabel lblThirdClinic = new JLabel(thirdClinic.getNome());
        lblThirdClinic.setBounds(11, 240, 322, 49);
        lblThirdClinic.setForeground(new Color(0x395886));
        lblThirdClinic.setFont(new Font("Rufina", Font.BOLD, 30));
        centerAlignment(lblThirdClinic);
        thirdPanel.getPnlCenter().add(lblThirdClinic);
        
        // Nome da quarta clínica
        JLabel lblFourthClinic = new JLabel(fourthClinic.getNome());
        lblFourthClinic.setBounds(11, 240, 322, 49);
        lblFourthClinic.setForeground(new Color(0x395886));
        lblFourthClinic.setFont(new Font("Rufina", Font.BOLD, 30));
        centerAlignment(lblFourthClinic);
        fourthPanel.getPnlCenter().add(lblFourthClinic);
        
    }
    
    
    public void centerAlignment(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    
    public void changeTextLabel(String text) {
        lblRecomendacoes.setText(text);
    }
    
    
    public final void configTxa() {
        
        // Descrição da primeira clínica
        DefaultTextPane txaFirst = new DefaultTextPane();
        txaFirst.setText(firstClinic.getDescricao());
        txaFirst.setBounds(4, 298, 337, 148);
        txaFirst.setForeground(new Color(0x395886));
        txaFirst.setFont(new Font("Rufina", Font.PLAIN, 23));
        firstPanel.getPnlCenter().add(txaFirst);
        
        // Descrição da Segunda clínica
        DefaultTextPane txaSecond = new DefaultTextPane();
        txaSecond.setText(secondClinic.getDescricao());
        txaSecond.setBounds(4, 298, 337, 148);
        txaSecond.setForeground(new Color(0x395886));
        txaSecond.setFont(new Font("Rufina", Font.PLAIN, 23));
        secondPanel.getPnlCenter().add(txaSecond);
        
        // Descrição da terceira clínica
        DefaultTextPane txaThird = new DefaultTextPane();
        txaThird.setText(thirdClinic.getDescricao());
        txaThird.setBounds(4, 298, 337, 148);
        txaThird.setForeground(new Color(0x395886));
        txaThird.setFont(new Font("Rufina", Font.PLAIN, 23));
        thirdPanel.getPnlCenter().add(txaThird);
        
        // Descrição da quarta clínica
        DefaultTextPane txaFourth = new DefaultTextPane();
        txaFourth.setText(fourthClinic.getDescricao());
        txaFourth.setBounds(4, 298, 337, 148);
        txaFourth.setForeground(new Color(0x395886));
        txaFourth.setFont(new Font("Rufina", Font.PLAIN, 23));
        fourthPanel.getPnlCenter().add(txaFourth);
        
    }
    
    
    public final void configLine() {
        
        LinesComponent lines = new LinesComponent();
        lines.setBounds(this.getBounds());
        lines.addLine(611, 56, 1519, 56, 2, new Color(0x395886));
        layeredPane.add(lines, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public final void configBtns() {
        
        btnFirstClinic = new RoundedButtonRadius("", 35, null);
        btnFirstClinic.addActionListener(this);
        btnFirstClinic.setBounds(firstPanel.getBounds());
        btnFirstClinic.transparentButton();
        layeredPane.add(btnFirstClinic, JLayeredPane.PALETTE_LAYER);
        
        btnSecondClinic = new RoundedButtonRadius("", 35, null);
        btnSecondClinic.addActionListener(this);
        btnSecondClinic.setBounds(secondPanel.getBounds());
        btnSecondClinic.transparentButton();
        layeredPane.add(btnSecondClinic, JLayeredPane.PALETTE_LAYER);
        
        btnThirdClinic = new RoundedButtonRadius("", 35, null);
        btnThirdClinic.addActionListener(this);
        btnThirdClinic.setBounds(thirdPanel.getBounds());
        btnThirdClinic.transparentButton();
        layeredPane.add(btnThirdClinic, JLayeredPane.PALETTE_LAYER);
        
        btnFourthClinic = new RoundedButtonRadius("", 35, null);
        btnFourthClinic.addActionListener(this);
        btnFourthClinic.setBounds(fourthPanel.getBounds());
        btnFourthClinic.transparentButton();
        layeredPane.add(btnFourthClinic, JLayeredPane.PALETTE_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnFirstClinic) {
            
            // Abre a primeira clínica
            configFrame.getMainFrame().remove(tela);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, firstClinic);
            perfilClinica.configPerfilClinica();
            
        } else if(e.getSource() == btnSecondClinic) {
            
            // Abre a segunda clínica
            configFrame.getMainFrame().remove(tela);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, secondClinic);
            perfilClinica.configPerfilClinica();
            
        } else if(e.getSource() == btnThirdClinic) {
            
            // Abre a terceira clínica
            configFrame.getMainFrame().remove(tela);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, thirdClinic);
            perfilClinica.configPerfilClinica();
            
        } else if(e.getSource() == btnFourthClinic) {
            
            // Abre a quarta clínica
            configFrame.getMainFrame().remove(tela);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, fourthClinic);
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
    
    
}
