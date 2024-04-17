
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.erros.ErrosAdicionarMedico;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.CardImage;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.validacoes.ValidacaoUsuarioProfissional;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AdicionarMedicos implements ActionListener {

    private final ConfigFrame configFrame;
    private ContaProfissional conta;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JLayeredPane layeredPane;
    private JPanel pnlAdicionar;
    private PanelShadow pnlDadosMedico;
    private PanelShadow pnlBuscar;
    private TextFieldShadow txtBuscarCpf;
    private TextFieldShadow txtNome;
    private TextFieldShadow txtCpf;
    private TextFieldShadow txtDataNascimento;
    private TextFieldShadow txtTelefone;
    private TextFieldShadow txtEndereco;
    private TextFieldShadow txtGenero;
    private TextFieldShadow txtCrm;
    private ButtonShadow btnBuscar;
    private ButtonShadow btnAdicionar;
    private RoundedButton btnVoltar;
    private CardImage fotoMedico;
    private ButtonBar buttonBar;
    private ContaProfissional medicoEncontrado;
    private ValidacaoUsuarioProfissional validacao;
    private ErrosAdicionarMedico erros;
    
    public AdicionarMedicos (ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaAdicionar() {
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        conta = dao.get(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getId());
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configLines();
        configText();
        configTxt();
        configBtn();
        
        // Define o scroll no início da tela
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                }
            }
        );
        
        validacao = new ValidacaoUsuarioProfissional(configFrame, null);
        erros = new ErrosAdicionarMedico(layeredPane);
        
        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void configGrid() {
        
        // Configura as layers
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1920, 1290));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(null);
        
        // Configuração da scroll pane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 1920, 1080);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom()); // Scroll customizado
        scrollPane.setBorder(null);
        
        // Configura os painéis da tela
        configPnls();
        
        // Adiciona as layers no frame
        configFrame.getMainFrame().add(scrollPane);
        
    }
    
    
    public void configPnls() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Barra de botões
        buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 0;
        gbc.weighty = 0;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel de imagens
        pnlAdicionar = new JPanel();
        pnlAdicionar.setPreferredSize(new Dimension(1920, 928));
        pnlAdicionar.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlAdicionar.setLayout(null);
        pnlAdicionar.setBorder(null);
        mainPanel.add(pnlAdicionar, gbc);
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1920, 928);
        layeredPane.setOpaque(false);
        layeredPane.setLayout(null);
        pnlAdicionar.add(layeredPane);
        
        // Painel com as informações do paciente
        pnlDadosMedico = new PanelShadow(50);
        pnlDadosMedico.setBounds(225, 170, 1471, 582);
        pnlDadosMedico.setBackground(new Color(0x152E52));
        pnlDadosMedico.setShadowSize(3);
        pnlDadosMedico.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlDadosMedico, JLayeredPane.PALETTE_LAYER);
        
        // Painel com as informações do paciente
        pnlBuscar = new PanelShadow(0);
        pnlBuscar.setBounds(0, 42, 1471, 200);
        pnlBuscar.setBackground(new Color(0xD5DEEF));
        pnlBuscar.setShadowSize(3);
        pnlBuscar.setShadowType(ShadowType.BOT);
        pnlDadosMedico.add(pnlBuscar);
        
    }
    
    
    public void configImages() {
        
        // Retângulo esquerdo
        ImageIcon rectangle = new ImageIcon(getClass().getResource("/imagens/RectangleMarcarConsulta.png"));
        JLabel lblLeftRectangle = new JLabel();
        lblLeftRectangle.setBounds(0, -380, rectangle.getIconWidth(), rectangle.getIconHeight());
        lblLeftRectangle.setIcon(rectangle);
        layeredPane.add(lblLeftRectangle, JLayeredPane.PALETTE_LAYER);
        
        // Retângulo direito
        BufferedImage bfRectangle = new BufferedImage(rectangle.getIconWidth(), rectangle.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bfRectangle.createGraphics();
        rectangle.paintIcon(null, g, 0,0);
        g.dispose();
        
        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bfRectangle.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bfRectangle = op.filter(bfRectangle, null);
        
        rectangle = new ImageIcon(bfRectangle);
        JLabel lblRightRectangle = new JLabel();
        lblRightRectangle.setBounds(1215, -380, rectangle.getIconWidth(), rectangle.getIconHeight());
        lblRightRectangle.setIcon(rectangle);
        layeredPane.add(lblRightRectangle, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem remédios caindo
        ImageIcon medicineDropping = new ImageIcon(getClass().getResource("/imagens/MedicineDropping.png"));
        JLabel lblMedicine = new JLabel();
        lblMedicine.setBounds(-324, 3, medicineDropping.getIconWidth(), medicineDropping.getIconHeight());
        lblMedicine.setIcon(medicineDropping);
        layeredPane.add(lblMedicine, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem estetoscópio
        ImageIcon imgEstetoscopio = new ImageIcon(getClass().getResource("/imagens/ImgEstetoscopio.png"));
        JLabel lblEstetoscopio = new JLabel();
        lblEstetoscopio.setBounds(1233, -114, imgEstetoscopio.getIconWidth(), imgEstetoscopio.getIconHeight());
        lblEstetoscopio.setIcon(imgEstetoscopio);
        layeredPane.add(lblEstetoscopio, JLayeredPane.MODAL_LAYER);
        
        // Imagem de perfil do médico
        if(medicoEncontrado == null) {
            fotoMedico = new CardImage(new ImageIcon(getClass().getResource("/imagens/ImgContaDefault.png")), 200);
            fotoMedico.setBounds(266, 259, 418, 409);
            layeredPane.add(fotoMedico, JLayeredPane.MODAL_LAYER);
        }
        
    }
    
    
    public void configLines() {
        
        // Linhas do painel inicial
        LinesComponent linesPerfil = new LinesComponent();
        linesPerfil.setBounds(0, 0, 1920, 928);
        
        linesPerfil.addLine(249, 130, 374, 130, 2, new Color(0x395886)); // Linha horizontal, canto superior esquerdo
        linesPerfil.addLine(1096, 130, 1646, 130, 2, new Color(0x395886)); // Linha vertical, canto superior esquerdo

        layeredPane.add(linesPerfil, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configText() {
        
        // Texto "Adicionar Médicos"
        JLabel lblAdicionar = new JLabel("Adicionar Médicos");
        lblAdicionar.setBounds(374, 65, 722, 105);
        lblAdicionar.setForeground(new Color(0x152E52));
        lblAdicionar.setFont(new Font("Rufina", Font.BOLD, 75));
        lblAdicionar.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(lblAdicionar, JLayeredPane.DEFAULT_LAYER);
        
        // Texto "Buscar Médico"
        JLabel lblBuscar = new JLabel("Buscar Médico");
        lblBuscar.setBounds(712, 14, 362, 57);
        lblBuscar.setForeground(new Color(0x395886));
        lblBuscar.setFont(new Font("Volkhov", Font.BOLD, 35));
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBuscar.add(lblBuscar);
        
        // Texto "Digite o CPF do médico:"
        JLabel lblDigiteCpf = new JLabel("Digite o CPF do médico:");
        lblDigiteCpf.setBounds(690, 89, 417, 28);
        lblDigiteCpf.setForeground(new Color(0x395886));
        lblDigiteCpf.setFont(new Font("Volkhov", Font.BOLD, 20));
        pnlBuscar.add(lblDigiteCpf);
        
        // Texto "Nome Completo"
        JLabel lblDados = new JLabel("Nome Completo");
        lblDados.setBounds(507, 294, 210, 21);
        lblDados.setForeground(new Color(0xD5DEEF));
        lblDados.setFont(new Font("Rufina", Font.BOLD, 20));
        pnlDadosMedico.add(lblDados);
        
        // Texto "CPF"
        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(507, 374, 210, 21);
        lblCpf.setForeground(new Color(0xD5DEEF));
        lblCpf.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosMedico.add(lblCpf);
        
        // Texto "Data de Nascimento"
        JLabel lblData = new JLabel("Data de Nascimento");
        lblData.setBounds(787, 294, 210, 21);
        lblData.setForeground(new Color(0xD5DEEF));
        lblData.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosMedico.add(lblData);
        
        // Texto "Telefone"
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(787, 374, 210, 21);
        lblTelefone.setForeground(new Color(0xD5DEEF));
        lblTelefone.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosMedico.add(lblTelefone);
        
        // Texto "Endereço"
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(1066, 294, 210, 21);
        lblEndereco.setForeground(new Color(0xD5DEEF));
        lblEndereco.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosMedico.add(lblEndereco);
        
        // Texto "Gênero"
        JLabel lblGenero = new JLabel("Gênero");
        lblGenero.setBounds(1066, 374, 210, 21);
        lblGenero.setForeground(new Color(0xD5DEEF));
        lblGenero.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosMedico.add(lblGenero);
        
        // Texto "CRM"
        JLabel lblCrm = new JLabel("CRM");
        lblCrm.setBounds(848, 454, 90, 21);
        lblCrm.setForeground(new Color(0xD5DEEF));
        lblCrm.setFont(new Font("Rufina", Font.PLAIN, 20));
        lblCrm.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosMedico.add(lblCrm);
        
    }
    
    
    public void configTxt() {
        
        // TextField para inserir o CPF do médico
        txtBuscarCpf = new TextFieldShadow(45);
        txtBuscarCpf.setBounds(676, 116, 379, 52);
        txtBuscarCpf.setBackground(new Color(0xF0F3FA));
        txtBuscarCpf.setDisabledTextColor(new Color(0x395886));
        txtBuscarCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtBuscarCpf.setShadowSize(3);
        txtBuscarCpf.setShadowType(ShadowType.BOT);
        txtBuscarCpf.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 50));
        pnlBuscar.add(txtBuscarCpf);
        
        // TextField Nome Completo
        txtNome = new TextFieldShadow(45);
        txtNome.setBounds(494, 321, 239, 48);
        txtNome.setBackground(new Color(0x395886));
        txtNome.setDisabledTextColor(new Color(0xD5DEEF));
        txtNome.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtNome.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtNome.setEnabled(false);
        pnlDadosMedico.add(txtNome);
        
        // TextField CPF
        txtCpf = new TextFieldShadow(45);
        txtCpf.setBounds(494, 401, 239, 48);
        txtCpf.setBackground(new Color(0x395886));
        txtCpf.setDisabledTextColor(new Color(0xD5DEEF));
        txtCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCpf.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtCpf.setEnabled(false);
        pnlDadosMedico.add(txtCpf);
        
        // TextField Data de Nascimento
        txtDataNascimento = new TextFieldShadow(45);
        txtDataNascimento.setBounds(774, 321, 239, 48);
        txtDataNascimento.setBackground(new Color(0x395886));
        txtDataNascimento.setDisabledTextColor(new Color(0xD5DEEF));
        txtDataNascimento.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtDataNascimento.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtDataNascimento.setEnabled(false);
        pnlDadosMedico.add(txtDataNascimento);
        
        // TextField Telefone
        txtTelefone = new TextFieldShadow(45);
        txtTelefone.setBounds(774, 401, 239, 48);
        txtTelefone.setBackground(new Color(0x395886));
        txtTelefone.setDisabledTextColor(new Color(0xD5DEEF));
        txtTelefone.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtTelefone.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtTelefone.setEnabled(false);
        pnlDadosMedico.add(txtTelefone);
        
        // TextField Paciente
        txtEndereco = new TextFieldShadow(45);
        txtEndereco.setBounds(1053, 321, 239, 48);
        txtEndereco.setBackground(new Color(0x395886));
        txtEndereco.setDisabledTextColor(new Color(0xD5DEEF));
        txtEndereco.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtEndereco.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtEndereco.setEnabled(false);
        pnlDadosMedico.add(txtEndereco);
        
        // TextField Gênero
        txtGenero = new TextFieldShadow(45);
        txtGenero.setBounds(1053, 401, 239, 48);
        txtGenero.setBackground(new Color(0x395886));
        txtGenero.setDisabledTextColor(new Color(0xD5DEEF));
        txtGenero.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtGenero.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtGenero.setEnabled(false);
        pnlDadosMedico.add(txtGenero);
        
        // TextField CRM
        txtCrm = new TextFieldShadow(45);
        txtCrm.setBounds(803, 475, 183, 48);
        txtCrm.setBackground(new Color(0x395886));
        txtCrm.setDisabledTextColor(new Color(0xD5DEEF));
        txtCrm.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCrm.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtCrm.setEnabled(false);
        pnlDadosMedico.add(txtCrm);
        
    }
    
    
    public void configBtn() {
        
        // Botão "Buscar"
        ImageIcon imgLupa = new ImageIcon(getClass().getResource("/imagens/Lupa.png"));
        btnBuscar = new ButtonShadow("", 100);
        btnBuscar.addActionListener(this);
        btnBuscar.setBounds(1283, 328, 52, 50);
        btnBuscar.setBackground(new Color(0x395886));
        btnBuscar.setIcon(imgLupa);
        btnBuscar.setShadowSize(1);
        btnBuscar.setShadowType(ShadowType.BOT);
        layeredPane.add(btnBuscar, JLayeredPane.POPUP_LAYER);
        
        // Botão "Adicionar"
        btnAdicionar = new ButtonShadow("Adicionar", 48);
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(865, 785, 190, 52);
        btnAdicionar.setBackground(new Color(0x395886));
        btnAdicionar.setForeground(new Color(0xF0F3FA));
        btnAdicionar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnAdicionar.setShadowSize(2);
        btnAdicionar.setShadowType(ShadowType.BOT);
        btnAdicionar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnAdicionar, JLayeredPane.POPUP_LAYER);
        
        // Botão "Voltar"
        btnVoltar = new RoundedButton("Voltar");
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(890, 857, 140, 35);
        btnVoltar.setForeground(new Color(0x395886));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnVoltar.transparentButton();
        layeredPane.add(btnVoltar, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void atualizarMedico() {
        
        if(medicoEncontrado != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            txtNome.setText(medicoEncontrado.getConta().getNome());
            txtCpf.setText(medicoEncontrado.getConta().getCpf());
            txtDataNascimento.setText(df.format(medicoEncontrado.getConta().getDataNascimento()));
            txtTelefone.setText(medicoEncontrado.getConta().getTelefone());
            txtEndereco.setText(medicoEncontrado.getConta().getEndereco());
            txtGenero.setText(medicoEncontrado.getConta().getGenero());
            txtCrm.setText(medicoEncontrado.getCrm());

            ConvertBlob convertBlob = new ConvertBlob();
            fotoMedico.setImage(convertBlob.imgConvertida(medicoEncontrado.getConta().getImg()));

            configFrame.getMainFrame().repaint();
        } else {
            txtNome.setText("");
            txtCpf.setText("");
            txtDataNascimento.setText("");
            txtTelefone.setText("");
            txtEndereco.setText("");
            txtGenero.setText("");
            txtCrm.setText("");

            fotoMedico.setImage(new ImageIcon(getClass().getResource("/imagens/ImgContaDefault.png")));

            configFrame.getMainFrame().repaint();
        }
            
        
    }
    
    
    public void pesquisarMedico() {
        
        erros.removerErros();
        
        medicoEncontrado = null;
        if(validacao.validarCpf(txtBuscarCpf.getText())) {

            // Pesquisa do médico
            ContaDAO daoConta = new ContaDAO();
            Conta conta = daoConta.get(validacao.getCpf());
            
            ContaProfissionalDAO daoProfissional = new ContaProfissionalDAO();
            medicoEncontrado = daoProfissional.getConta(conta);
            
            if(medicoEncontrado == null) {
                erros.erroNaoEncontrado();
            }

        } else {
            erros.erroCpfInvalido();
            medicoEncontrado = null;
        }
        atualizarMedico();
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnBuscar) {
            if(validacao.validarCpf(txtBuscarCpf.getText())) {
                txtBuscarCpf.setText(validacao.getCpf());
                // Pesquisa o médico de acordo com o cpf
                pesquisarMedico();
            }
            
        } else if(e.getSource() == btnAdicionar) {
            if(medicoEncontrado != null) {
                // Se o médico não estiver cadastrado em uma clínica
                if(medicoEncontrado.getClinica() == null) {
                    
                    // Adiciona a clínica na conta do médico
                    medicoEncontrado.setClinica(conta.getClinica());
                    
                    ContaProfissionalDAO dao = new ContaProfissionalDAO();
                    dao.atualizar(medicoEncontrado);
                    
                    // Mensagem de confirmação
                    TelaContinuar telaContinuar = new TelaContinuar(configFrame, scrollPane, txtBuscarCpf);
                    telaContinuar.configTelaContinuar();
                    
                } else {
                    erros.erroMedicoInvalido();
                }
                    
            } else {
                erros.erroCpfInvalido();
            }
            
        } else if(e.getSource() == btnVoltar) {
            // Abre o perfil da clínica do usuário
            configFrame.getMainFrame().remove(scrollPane);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, conta.getClinica());
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
}
