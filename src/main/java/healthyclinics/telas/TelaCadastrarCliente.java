
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaClienteDAO;
import healthyclinics.db.ContaDAO;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.validacoes.ValidacaoCadastroCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TelaCadastrarCliente implements ActionListener {

    private final ConfigFrame configFrame;
    private JLayeredPane layeredPane;
    private TextFieldShadow txtNome;
    private TextFieldShadow txtCpf;
    private TextFieldShadow txtData;
    private TextFieldShadow txtTelefone;
    private TextFieldShadow txtEndereco;
    private TextFieldShadow txtConvenio;
    private TextFieldShadow txtSenha;
    private ButtonShadow btnCadastrar;
    private RoundedButton btnVoltar;
    private ValidacaoCadastroCliente validacaoUsuario;
    
    
    public TelaCadastrarCliente(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }

    
    public TextFieldShadow getTxtNome() {
        return txtNome;
    }

    public TextFieldShadow getTxtCpf() {
        return txtCpf;
    }

    public TextFieldShadow getTxtData() {
        return txtData;
    }

    public TextFieldShadow getTxtTelefone() {
        return txtTelefone;
    }

    public TextFieldShadow getTxtEndereco() {
        return txtEndereco;
    }

    public TextFieldShadow getTxtConvenio() {
        return txtConvenio;
    }

    public TextFieldShadow getTxtSenha() {
        return txtSenha;
    }
    
    
    public void configCadastroCliente() {
        
        // Configura os elementos da tela
        configLayers();
        configImages();
        configLabels();
        configLines();
        configTxt();
        configBtns();
        
        // Validação das informações digitadas
        validacaoUsuario = new ValidacaoCadastroCliente(configFrame, this, layeredPane);
        
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
        ImageIcon imgWater = new ImageIcon(getClass().getResource("/imagens/WaterCircleCliente.png"));
        JLabel lblWater = new JLabel();
        lblWater.setBounds(0, 0, imgWater.getIconWidth(), imgWater.getIconHeight());
        lblWater.setIcon(imgWater);
        layeredPane.add(lblWater, JLayeredPane.DEFAULT_LAYER);
        
        // Logo HealthyClinics médio
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/imagens/ImgHCMedium.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(1132, -1, imgLogo.getIconWidth(), imgLogo.getIconHeight());
        lblLogo.setIcon(imgLogo);
        layeredPane.add(lblLogo, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem Retângulo
        ImageIcon imgRetangulo = new ImageIcon(getClass().getResource("/imagens/RectangleCliente.png"));
        JLabel lblRetangulo = new JLabel();
        lblRetangulo.setBounds(1623, 813, imgRetangulo.getIconWidth(), imgRetangulo.getIconHeight());
        lblRetangulo.setIcon(imgRetangulo);
        layeredPane.add(lblRetangulo, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configLabels() {
        
        // Texto "Nome Completo"
        JLabel lblNome = new JLabel("Nome Completo");
        lblNome.setBounds(1220, 339, 245, 27);
        lblNome.setForeground(new Color(0x395886));
        lblNome.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblNome, JLayeredPane.PALETTE_LAYER);
        
        // Texto "CPF"
        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(1220, 418, 245, 27);
        lblCpf.setForeground(new Color(0x395886));
        lblCpf.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblCpf, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Data de Nascimento"
        JLabel lblData = new JLabel("Data de Nascimento");
        lblData.setBounds(1220, 497, 245, 27);
        lblData.setForeground(new Color(0x395886));
        lblData.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblData, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Telefone"
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(1220, 576, 245, 27);
        lblTelefone.setForeground(new Color(0x395886));
        lblTelefone.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblTelefone, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Endereço"
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(1220, 655, 245, 27);
        lblEndereco.setForeground(new Color(0x395886));
        lblEndereco.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblEndereco, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Convênio (Opcional)"
        JLabel lblConvenio = new JLabel("Convênio (Opcional)");
        lblConvenio.setBounds(1220, 734, 245, 27);
        lblConvenio.setForeground(new Color(0x395886));
        lblConvenio.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblConvenio, JLayeredPane.PALETTE_LAYER);
        
        // Texto "Senha"
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(1220, 810, 245, 27);
        lblSenha.setForeground(new Color(0x395886));
        lblSenha.setFont(new Font("Volkhov", Font.PLAIN, 20));
        layeredPane.add(lblSenha, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Configurações das linhas da tela
        LinesComponent lines = new LinesComponent();
        lines.setBounds(0, 0, 1920, 1080);
        lines.addLine(1692, 21, 1892, 21, 3, new Color(0x395886)); // Linha horizontal (canto superior direito)
        lines.addLine(1892, 21, 1892, 221, 3, new Color(0x395886)); // Linha vertical (canto superior direito)
        lines.addLine(996, 1057, 1196, 1057, 3, new Color(0x395886)); // Linha horizontal (canto inferior esquerdo)
        lines.addLine(996, 857, 996, 1057, 3, new Color(0x395886)); // Linha vertical (canto inferior esquerdo)
        layeredPane.add(lines, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configTxt() {
        
        // TextField para inserir o nome
        txtNome = new TextFieldShadow(45);
        txtNome.setBounds(1196, 366, 502, 55);
        txtNome.setBackground(new Color(0xD5DEEF));
        txtNome.setForeground(new Color(0x395886));
        txtNome.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtNome, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir o CPF
        txtCpf = new TextFieldShadow(45);
        txtCpf.setBounds(1196, 445, 502, 55);
        txtCpf.setBackground(new Color(0xD5DEEF));
        txtCpf.setForeground(new Color(0x395886));
        txtCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtCpf, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir a data de nascimento
        txtData = new TextFieldShadow(45);
        txtData.setBounds(1196, 524, 502, 55);
        txtData.setBackground(new Color(0xD5DEEF));
        txtData.setForeground(new Color(0x395886));
        txtData.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtData, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir o telefone
        txtTelefone = new TextFieldShadow(45);
        txtTelefone.setBounds(1196, 603, 502, 55);
        txtTelefone.setBackground(new Color(0xD5DEEF));
        txtTelefone.setForeground(new Color(0x395886));
        txtTelefone.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtTelefone, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir o endereço
        txtEndereco = new TextFieldShadow(45);
        txtEndereco.setBounds(1196, 682, 502, 55);
        txtEndereco.setBackground(new Color(0xD5DEEF));
        txtEndereco.setForeground(new Color(0x395886));
        txtEndereco.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtEndereco, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir o convênio
        txtConvenio = new TextFieldShadow(45);
        txtConvenio.setBounds(1196, 761, 502, 55);
        txtConvenio.setBackground(new Color(0xD5DEEF));
        txtConvenio.setForeground(new Color(0x395886));
        txtConvenio.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtConvenio, JLayeredPane.MODAL_LAYER);
        
        // TextField para inserir a senha
        txtSenha = new TextFieldShadow(45);
        txtSenha.setBounds(1196, 837, 502, 55);
        txtSenha.setBackground(new Color(0xD5DEEF));
        txtSenha.setForeground(new Color(0x395886));
        txtSenha.setFont(new Font("Volkhov", Font.PLAIN, 15));
        layeredPane.add(txtSenha, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void configBtns() {
        
        // Botão "Cadastrar"
        btnCadastrar = new ButtonShadow("Cadastrar", 50);
        btnCadastrar.addActionListener(this);
        btnCadastrar.setBounds(1344, 950, 206, 55);
        btnCadastrar.setBackground(new Color(0x395886));
        btnCadastrar.setForeground(new Color(0xF0F3FA));
        btnCadastrar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCadastrar.setShadowOpacity((float) 0.3);
        btnCadastrar.setShadowSize(3);
        btnCadastrar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnCadastrar, JLayeredPane.MODAL_LAYER);
        
        // Botão "Voltar"
        btnVoltar = new RoundedButton("Voltar");
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(1375, 1024, 144, 28);
        btnVoltar.setForeground(new Color(0x395886));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnVoltar.transparentButton();
        layeredPane.add(btnVoltar, JLayeredPane.MODAL_LAYER);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Se o botão "Cadastrar" for pressionado
        if(e.getSource() == btnCadastrar) {
            
            ContaCliente contaCliente = validacaoUsuario.validarUser(txtNome.getText(), txtCpf.getText(), txtData.getText(), txtTelefone.getText(), txtEndereco.getText(), txtSenha.getText(), txtConvenio.getText());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            // Se a conta cliente foi validada
            if(contaCliente != null) {
                
                // Adiciona a conta no banco de dados
                ContaDAO dao = new ContaDAO();
                dao.salvar(validacaoUsuario.getConta());
                
                // Adiciona a contaCliente cliente no banco de dados
                ContaClienteDAO daoCliente = new ContaClienteDAO();
                daoCliente.salvar(contaCliente);
                
                // Abre a tela de login
                configFrame.getMainFrame().remove(layeredPane);
                configFrame.getManager().getTelas().getTelaLogin().configTelaLogin();
                
            }
            
            
        } else if(e.getSource() == btnVoltar) { // Se o botão "Voltar" for pressionado
            
            // Abre a tela de escolhas
            configFrame.getMainFrame().remove(layeredPane);
            configFrame.getManager().getTelas().getTelaEscolha().configTelaEscolha();
            
        }
        
    }
    
    
}
