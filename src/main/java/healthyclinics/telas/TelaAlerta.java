
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.db.ClinicaEspecialidadeDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.layouts.DefaultTextPane;
import healthyclinics.layouts.RoundedButtonRadius;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.ContaProfissional;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class TelaAlerta implements ActionListener {
    
    private final ConfigFrame configFrame;
    private final JComponent tela;
    private JLayeredPane layers;
    private JLabel lblTitle;
    private DefaultTextPane txaAviso;
    private RoundedButtonRadius btnSair;
    private RoundedButtonRadius btnCancelar;
    private ContaProfissional contaLogada;

    
    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public DefaultTextPane getTxaAviso() {
        return txaAviso;
    }

    public void setTxaAviso(DefaultTextPane txaAviso) {
        this.txaAviso = txaAviso;
    }
    
    
    public TelaAlerta(ConfigFrame configFrame, JComponent tela) {
        this.configFrame = configFrame;
        this.tela = tela;
    }
    
    
    public void configTelaAlerta() {
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        contaLogada = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
        
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
        layers = new JLayeredPane();
        layers.setBounds(0, 0, 1920, 1080);
        layers.setLayout(null);
        
        configFrame.getMainFrame().remove(tela);
        layers.add(tela, JLayeredPane.DEFAULT_LAYER);
        configFrame.getMainFrame().add(layers);
        
    }
    
    
    public void configImgs() {
        
        // Imagem transparente que deixa a tela mais escura
        ImageIcon imgMask = new ImageIcon(getClass().getResource("/imagens/Mask.png"));
        JLabel lblMask = new JLabel();
        lblMask.setBounds(0, 0, imgMask.getIconWidth(), imgMask.getIconHeight());
        lblMask.setIcon(imgMask);
        layers.add(lblMask, JLayeredPane.PALETTE_LAYER);
        
        // Painel de informações
        ImageIcon imgPanel = new ImageIcon(getClass().getResource("/imagens/PanelConfirmar.png"));
        JLabel lblPanel = new JLabel();
        lblPanel.setBounds(739, 426, imgPanel.getIconWidth(), imgPanel.getIconHeight()); // 426
        lblPanel.setIcon(imgPanel);
        layers.add(lblPanel, JLayeredPane.MODAL_LAYER);
        
        // GIF
        ImageIcon gifPanel = new ImageIcon(getClass().getResource("/imagens/Alert.gif"));
        gifPanel.setImage(gifPanel.getImage().getScaledInstance(84, 84, Image.SCALE_DEFAULT));
        JLabel lblDelete = new JLabel();
        lblDelete.setBounds(918, 369, gifPanel.getIconWidth(), gifPanel.getIconHeight()); // 426
        lblDelete.setIcon(gifPanel);
        layers.add(lblDelete, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void configLabels() {
        
        // Texto "Permissão Inválida"
        lblTitle = new JLabel("Sair da Clínica?");
        lblTitle.setBounds(879, 473, 163, 27);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Volkhov", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        layers.add(lblTitle, JLayeredPane.POPUP_LAYER);
        
        // Configurações da TextPane
        txaAviso = new DefaultTextPane();
        txaAviso.setBounds(763, 504, 394, 80);
        txaAviso.setBackground(new Color(0xF5EDED));
        txaAviso.setForeground(new Color(0x777777));
        layers.add(txaAviso, JLayeredPane.POPUP_LAYER);
        if(contaLogada.getCrm().equals(contaLogada.getClinica().getResponsavel().getCrm())) {
            txaAviso.setText("Você é o atual responsável pela clínica. Caso você saia, outro médico se tornará o responsável. E caso não exista nenhum médico cadastrado, a clínica será excluída. Você deseja continuar?");
            txaAviso.setFont(new Font("Rufina", Font.PLAIN, 14));
        } else {
            txaAviso.setText("Ao sair da clínica, você só poderá voltar se o médico responsável cadastrar você novamente na clínica. Você deseja continuar?");
            txaAviso.setFont(new Font("Rufina", Font.PLAIN, 15));
        }
        
    }
    
    
    public void configBtn() {
        
        // Configurações do botão "Cancelar"
        btnCancelar = new RoundedButtonRadius("Cancelar", 10, new Color(0xD0342C));
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(763, 590, 182, 39);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setForeground(new Color(0xD0342C));
        btnCancelar.setFont(new Font("Volkhov", Font.BOLD, 18));
        layers.add(btnCancelar, JLayeredPane.POPUP_LAYER);
        
        // Configurações do botão "Sair"
        btnSair = new RoundedButtonRadius("Sair", 10, new Color(0xD0342C));
        btnSair.addActionListener(this);
        btnSair.setBounds(975, 590, 182, 39);
        btnSair.setBackground(new Color(0xD0342C));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Volkhov", Font.BOLD, 18));
        btnSair.configBackgroundBtn();
        btnSair.getPnlBackground().setBackground(new Color(0xD0342C));
        layers.add(btnSair.getPnlBackground(), JLayeredPane.POPUP_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnCancelar) { // Se o botão "Cancelar" for pressionado
            
            layers.remove(tela); // Remove as layers da tela Aviso
            
            // Volta para a tela de perfil
            configFrame.getMainFrame().remove(layers);
            configFrame.getMainFrame().add(tela);
            configFrame.getMainFrame().repaint();
            
        } else if(e.getSource() == btnSair) {
            
            Clinica clinicaModificada = contaLogada.getClinica();
            
            ClinicaDAO clinicaDao = new ClinicaDAO();
            
            // Remove a clínica da conta do usuário
            contaLogada.setClinica(null);
            ContaProfissionalDAO contaPDao = new ContaProfissionalDAO();
            contaPDao.atualizar(contaLogada);
            
            // Se o crm do usuário for igual ao crm do responsável pela clínica
            if(contaLogada.getCrm().equals(clinicaModificada.getResponsavel().getCrm())) {
                
                // Se a clínica possui outros médicos
                List<ContaProfissional> listaMedicos = contaPDao.getListaMedicos(clinicaModificada);
                if(!listaMedicos.isEmpty()) {
                    clinicaModificada.setResponsavel(listaMedicos.get(0)); // O primeiro médico adicionado se torna o responsável
                    clinicaDao.atualizar(clinicaModificada);
                    
                } else { // Se a clínica não pussui outros médicos
                    ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
                    daoCE.limpar(clinicaModificada);
                    clinicaDao.remover(clinicaModificada.getId()); // Deleta a clínica
                }
                
            }
            
            layers.remove(tela); // Remove as layers da tela Aviso
            
            // Volta para a tela de perfil
            configFrame.getMainFrame().remove(layers);
            configFrame.getManager().getTelas().getTelaPerfil().getPerfilProfissional().configTelaPerfil();
            
        }
        
    }
    
    
    
}
