
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaClienteDAO;
import healthyclinics.db.ContaDAO;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.CardImage;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.ferramentas.SalvarImagem;
import healthyclinics.layouts.CustomComboBox;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.PasswordFieldShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.validacoes.ValidacaoUsuarioCliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PerfilCliente implements ActionListener {

    private final ConfigFrame configFrame;
    private ContaCliente conta;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JLayeredPane layeredPane;
    private JPanel pnlPerfil;
    private PanelShadow pnlDadosPaciente;
    private TextFieldShadow txtNome;
    private TextFieldShadow txtCpf;
    private TextFieldShadow txtDataNascimento;
    private TextFieldShadow txtTelefone;
    private TextFieldShadow txtSenha;
    private PasswordFieldShadow pwfSenha;
    private TextFieldShadow txtGenero;
    private TextFieldShadow txtConvenio;
    private TextFieldShadow txtEndereco;
    private ButtonShadow btnEditar;
    private ButtonShadow btnAtualizar;
    private ButtonShadow btnSair;
    private RoundedButton btnCancelar;
    private CardImage fotoPaciente;
    private CustomComboBox cboGenero;
    private ButtonBar buttonBar;
    private ValidacaoUsuarioCliente validacaoUsuario;
    
    public PerfilCliente (ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configTelaPerfil() {
        
        ContaClienteDAO dao = new ContaClienteDAO();
        conta = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configLines();
        configText();
        configTxt();
        configCbo();
        configBtn();
        
        // Define o scroll no início da tela
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                }
            }
        );
        
        validacaoUsuario = new ValidacaoUsuarioCliente(configFrame, layeredPane);
        
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
        pnlPerfil = new JPanel();
        pnlPerfil.setPreferredSize(new Dimension(1920, 928));
        pnlPerfil.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlPerfil.setLayout(null);
        pnlPerfil.setBorder(null);
        mainPanel.add(pnlPerfil, gbc);
        
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
        pnlPerfil.add(layeredPane);
        
        // Painéis com as informações do paciente
        pnlDadosPaciente = new PanelShadow(50);
        pnlDadosPaciente.setBounds(225, 161, 1471, 663);
        pnlDadosPaciente.setBackground(new Color(0xD5DEEF));
        pnlDadosPaciente.setShadowSize(3);
        pnlDadosPaciente.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlDadosPaciente, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configImages() {
        
        // Imagem de perfil do usuário
        ConvertBlob convertBlob = new ConvertBlob();
        fotoPaciente = new CardImage(convertBlob.imgConvertida(conta.getConta().getImg()), 200);
        fotoPaciente.setBounds(162, 73, 418, 409);
        layeredPane.add(fotoPaciente, JLayeredPane.PALETTE_LAYER);
        
        ImageIcon imgPencil = new ImageIcon(getClass().getResource("/imagens/ImgPencil.png"));
        JLabel lblPencil = new JLabel();
        lblPencil.setBounds(1374, 36, imgPencil.getIconWidth(), imgPencil.getIconHeight());
        lblPencil.setIcon(imgPencil);
        layeredPane.add(lblPencil, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Linhas do painel inicial
        LinesComponent linesPerfil = new LinesComponent();
        linesPerfil.setBounds(0, 0, 1920, 928);
        
        linesPerfil.addLine(534, 122, 580, 122, 2, new Color(0x395886)); // Linha horizontal, canto superior esquerdo
        linesPerfil.addLine(1050, 122, 1655, 122, 2, new Color(0x395886)); // Linha vertical, canto superior esquerdo
        
        linesPerfil.addLine(170, 880, 280, 880, 2, new Color(0x395886)); // Linha horizontal, canto inferior esquerdo
        linesPerfil.addLine(170, 767, 170, 880, 2, new Color(0x395886)); // Linha vertical, canto inferior esquerdo

        pnlPerfil.add(linesPerfil);
        
    }
    
    
    public void configText() {
        
        // Texto "Perfil Paciente"
        JLabel lblPerfil = new JLabel("Perfil de Paciente");
        lblPerfil.setBounds(580, 83, 470, 78);
        lblPerfil.setForeground(new Color(0x395886));
        lblPerfil.setFont(new Font("Rufina", Font.BOLD, 50));
        lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(lblPerfil, JLayeredPane.DEFAULT_LAYER);
        
        // Texto "Informações Pessoais"
        JLabel lblInformacoes = new JLabel("Informações Pessoais");
        lblInformacoes.setBounds(527, 30, 417, 78);
        lblInformacoes.setForeground(new Color(0x395886));
        lblInformacoes.setFont(new Font("Rufina", Font.BOLD, 35));
        lblInformacoes.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosPaciente.add(lblInformacoes);
        
        // Texto "Nome Completo"
        JLabel lblDados = new JLabel("Nome Completo");
        lblDados.setBounds(475, 121, 197, 25);
        lblDados.setForeground(new Color(0x395886));
        lblDados.setFont(new Font("Rufina", Font.BOLD, 20));
        pnlDadosPaciente.add(lblDados);
        
        // Texto "CPF"
        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(475, 208, 197, 25);
        lblCpf.setForeground(new Color(0x395886));
        lblCpf.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblCpf);
        
        // Texto "Data de Nascimento"
        JLabel lblData = new JLabel("Data de Nascimento");
        lblData.setBounds(475, 295, 197, 25);
        lblData.setForeground(new Color(0x395886));
        lblData.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblData);
        
        // Texto "Telefone"
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(475, 382, 197, 25);
        lblTelefone.setForeground(new Color(0x395886));
        lblTelefone.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblTelefone);
        
        // Texto "Senha"
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(783, 208, 197, 25);
        lblSenha.setForeground(new Color(0x395886));
        lblSenha.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblSenha);
        
        // Texto "Gênero"
        JLabel lblGenero = new JLabel("Gênero");
        lblGenero.setBounds(783, 295, 197, 25);
        lblGenero.setForeground(new Color(0x395886));
        lblGenero.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblGenero);
        
        // Texto "Convênio"
        JLabel lblConvenio = new JLabel("Convênio");
        lblConvenio.setBounds(783, 382, 197, 25);
        lblConvenio.setForeground(new Color(0x395886));
        lblConvenio.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblConvenio);
        
        // Texto "Endereço"
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(475, 469, 197, 25);
        lblEndereco.setForeground(new Color(0x395886));
        lblEndereco.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosPaciente.add(lblEndereco);
        
    }
    
    
    public void configTxt() {
        
        // TextField Nome Completo
        txtNome = new TextFieldShadow(45);
        txtNome.setText(conta.getConta().getNome());
        txtNome.setBounds(455, 146, 561, 52);
        txtNome.setBackground(new Color(0x395886));
        txtNome.setDisabledTextColor(new Color(0xF0F3FA));
        txtNome.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtNome.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtNome.setEnabled(false);
        pnlDadosPaciente.add(txtNome);
        
        // TextField CPF
        txtCpf = new TextFieldShadow(45);
        txtCpf.setText(conta.getConta().getCpf());
        txtCpf.setBounds(455, 233, 254, 52);
        txtCpf.setBackground(new Color(0x395886));
        txtCpf.setDisabledTextColor(new Color(0xF0F3FA));
        txtCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCpf.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtCpf.setEnabled(false);
        pnlDadosPaciente.add(txtCpf);
        
        // TextField Data de Nascimento
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        txtDataNascimento = new TextFieldShadow(45);
        txtDataNascimento.setText(df.format(conta.getConta().getDataNascimento()));
        txtDataNascimento.setBounds(455, 320, 254, 52);
        txtDataNascimento.setBackground(new Color(0x395886));
        txtDataNascimento.setDisabledTextColor(new Color(0xF0F3FA));
        txtDataNascimento.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtDataNascimento.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtDataNascimento.setEnabled(false);
        pnlDadosPaciente.add(txtDataNascimento);
        
        // TextField Telefone
        txtTelefone = new TextFieldShadow(45);
        txtTelefone.setText(conta.getConta().getTelefone());
        txtTelefone.setBounds(455, 407, 254, 52);
        txtTelefone.setBackground(new Color(0x395886));
        txtTelefone.setDisabledTextColor(new Color(0xF0F3FA));
        txtTelefone.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtTelefone.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtTelefone.setEnabled(false);
        pnlDadosPaciente.add(txtTelefone);
        
        // TextField Senha
        txtSenha = new TextFieldShadow(45);
        txtSenha.setBounds(760, 233, 254, 52);
        txtSenha.setBackground(new Color(0xF0F3FA));
        txtSenha.setDisabledTextColor(new Color(0xF0F3FA));
        txtSenha.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtSenha.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        
        // PasswordField Senha
        pwfSenha = new PasswordFieldShadow(45);
        pwfSenha.setText(conta.getConta().getSenha());
        pwfSenha.setBounds(760, 233, 254, 52);
        pwfSenha.setBackground(new Color(0x395886));
        pwfSenha.setDisabledTextColor(new Color(0xF0F3FA));
        pwfSenha.setFont(new Font("Volkhov", Font.PLAIN, 15));
        pwfSenha.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pwfSenha.setEnabled(false);
        pnlDadosPaciente.add(pwfSenha);
        
        // TextField Gênero
        txtGenero = new TextFieldShadow(45);
        txtGenero.setBounds(760, 320, 254, 52);
        txtGenero.setBackground(new Color(0x395886));
        txtGenero.setDisabledTextColor(new Color(0xF0F3FA));
        txtGenero.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtGenero.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtGenero.setEnabled(false);
        if(conta.getConta().getGenero() != null) {
            txtGenero.setText(conta.getConta().getGenero());
        }
        pnlDadosPaciente.add(txtGenero);
        
        // TextField Convênio
        txtConvenio = new TextFieldShadow(45);
        txtConvenio.setText(conta.getConvenio());
        txtConvenio.setBounds(760, 407, 254, 52);
        txtConvenio.setBackground(new Color(0x395886));
        txtConvenio.setDisabledTextColor(new Color(0xF0F3FA));
        txtConvenio.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtConvenio.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtConvenio.setEnabled(false);
        pnlDadosPaciente.add(txtConvenio);
        
        // TextField Paciente
        txtEndereco = new TextFieldShadow(45);
        txtEndereco.setText(conta.getConta().getEndereco());
        txtEndereco.setBounds(455, 494, 561, 52);
        txtEndereco.setBackground(new Color(0x395886));
        txtEndereco.setDisabledTextColor(new Color(0xF0F3FA));
        txtEndereco.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtEndereco.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtEndereco.setEnabled(false);
        pnlDadosPaciente.add(txtEndereco);
        
    }
    
    
    public void configBtn() {
        
        // Botão "Editar"
        btnEditar = new ButtonShadow("Editar Foto", 35);
        btnEditar.addActionListener(this);
        btnEditar.setBounds(78, 359, 137, 38);
        btnEditar.setBackground(new Color(0x395886));
        btnEditar.setForeground(new Color(0xF0F3FA));
        btnEditar.setFont(new Font("Volkhov", Font.BOLD, 15));
        btnEditar.setShadowSize(2);
        btnEditar.setShadowType(ShadowType.BOT);
        btnEditar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlDadosPaciente.add(btnEditar);
        
        // Botão "Atualizar"
        btnAtualizar = new ButtonShadow("Atualizar", 48);
        btnAtualizar.addActionListener(this);
        btnAtualizar.setBounds(640, 575, 190, 52);
        btnAtualizar.setBackground(new Color(0x395886));
        btnAtualizar.setForeground(new Color(0xF0F3FA));
        btnAtualizar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnAtualizar.setShadowSize(2);
        btnAtualizar.setShadowType(ShadowType.BOT);
        btnAtualizar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlDadosPaciente.add(btnAtualizar);
        
        // Botão "Sair"
        btnSair = new ButtonShadow("SAIR", 35);
        btnSair.addActionListener(this);
        btnSair.setBounds(1559, 855, 137, 38);
        btnSair.setBackground(new Color(0x152E52));
        btnSair.setForeground(new Color(0xF0F3FA));
        btnSair.setFont(new Font("Volkhov", Font.BOLD, 15));
        btnSair.setShadowSize(2);
        btnSair.setShadowType(ShadowType.BOT);
        layeredPane.add(btnSair, JLayeredPane.MODAL_LAYER);
        
        // Botão "Cancelar"
        btnCancelar = new RoundedButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(876, 853, 169, 37);
        btnCancelar.setForeground(new Color(0x395886));
        btnCancelar.setFont(new Font("Volkhov", Font.BOLD, 25));
        btnCancelar.transparentButton();
        
    }
    
    
    public void configCbo() {
        
        // Gêneros utilizados tendo em mente as análises biológicas que podem ser necessárias para o médico
        Object[] generos = {"Masculino", "Feminino"};
        
        // Configurações da combobox "Gênero"
        cboGenero = new CustomComboBox(generos);
        cboGenero.setBounds(774, 320, 230, 52);
        cboGenero.setBackground(new Color(0xD5DEEF));
        cboGenero.setForeground(new Color(0x395886));
        cboGenero.setFont(new Font("Volkhov", Font.PLAIN, 15));
        
    }
    
    
    public void atualizarPressed() {
        
        if(!txtNome.isEnabled()) {
            
            // Configura os elementos da tela
            configElementos();
            
            layeredPane.add(btnCancelar, JLayeredPane.PALETTE_LAYER);
            
        } else {
            
            if(validacaoUsuario.validarUser(txtNome.getText(), txtCpf.getText(), txtDataNascimento.getText(), txtTelefone.getText(), txtEndereco.getText(), cboGenero.getSelectedItem().toString(),txtSenha.getText(), txtConvenio.getText())) {
                
                // Atualização de dados
                ContaCliente novosDados = validacaoUsuario.retornarNovosDados(conta);
                
                ContaDAO contaDao = new ContaDAO();
                contaDao.atualizar(validacaoUsuario.getConta()); // Atualiza as informações na tabela conta
                
                ContaClienteDAO dao = new ContaClienteDAO();
                dao.atualizar(novosDados); // Atualiza as informações na tabela conta_profissional
                conta = dao.get(novosDados.getId()); // Busca as novas informações no banco novamente
                
                // Configura os elementos da tela
                configElementos();
                
            }
            
            
        }
        
    }
    
    
    private void configElementos() {
        
        if(!txtNome.isEnabled()) { // Configura os elementos necessários da tela para o usuário poder atualizar as informações
            
            btnAtualizar.setText("Confirmar");
            layeredPane.add(btnCancelar, JLayeredPane.PALETTE_LAYER);
            
            // Atualiza as textfiels
            atualizarTxt(txtNome);
            atualizarTxt(txtCpf);
            atualizarTxt(txtDataNascimento);
            atualizarTxt(txtTelefone);
            pnlDadosPaciente.remove(pwfSenha);
            pnlDadosPaciente.add(txtSenha);
            pnlDadosPaciente.remove(txtGenero);
            pnlDadosPaciente.add(cboGenero);
            atualizarTxt(txtConvenio);
            atualizarTxt(txtEndereco);
            
            txtSenha.setText(conta.getConta().getSenha());
            
        } else { // Configura os elementos necessários da tela para o usuário poder salvar as informações
            
            btnAtualizar.setText("Atualizar");
            
            // Atualiza as textfiels
            atualizarTxt(txtNome);
            atualizarTxt(txtCpf);
            atualizarTxt(txtDataNascimento);
            atualizarTxt(txtTelefone);
            pnlDadosPaciente.remove(txtSenha);
            pnlDadosPaciente.add(pwfSenha);
            pnlDadosPaciente.remove(cboGenero);
            pnlDadosPaciente.add(txtGenero);
            atualizarTxt(txtConvenio);
            atualizarTxt(txtEndereco);
            
            // Configura novamente as textfields com as informações atualizadas
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            txtNome.setText(conta.getConta().getNome());
            txtCpf.setText(conta.getConta().getCpf());
            txtDataNascimento.setText(df.format(conta.getConta().getDataNascimento()));
            txtTelefone.setText(conta.getConta().getTelefone());
            pwfSenha.setText(conta.getConta().getSenha());
            txtGenero.setText(conta.getConta().getGenero());
            txtConvenio.setText(conta.getConvenio());
            txtEndereco.setText(conta.getConta().getEndereco());
            
            layeredPane.remove(btnCancelar);
            
        }
        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void atualizarTxt(JTextField txt) {
        
        if(txt.isEnabled()) {
            txt.setEnabled(false);
            txt.setBackground(new Color(0x395886));
        } else {
            txt.setEnabled(true);
            txt.setBackground(new Color(0xF0F3FA));
        }
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnCancelar) {
            
            // Remove os erros da tela
            validacaoUsuario.getErros().removerErros();
            
            // Configura os elementos da tela novamente para o padrão
            configElementos();
            
        } else if(e.getSource() == btnEditar) {
            
            // Abre a escolha de imagem
            SalvarImagem salvarImagem = new SalvarImagem();
            if(salvarImagem.mostrarTela()) {
                
                // Muda a imagem da foto da conta
                ConvertBlob convertBlob = new ConvertBlob();
                conta.getConta().setImg(convertBlob.converterImagem(salvarImagem.getImageFile()));
                
                // Muda a imagem da foto na conta do banco
                ContaDAO dao = new ContaDAO();
                dao.atualizar(conta.getConta());
                
                fotoPaciente.setImage(convertBlob.imgConvertida(conta.getConta().getImg()));
                // Muda a imagem da foto no botão perfil
                buttonBar.getFotoPerfil().setImage(convertBlob.imgConvertida(conta.getConta().getImg()));
                
                configFrame.getMainFrame().repaint(); // Atualiza o frame
                
            }
            
        } else if(e.getSource() == btnAtualizar) {
            
            atualizarPressed();
            
        } else if(e.getSource() == btnSair) {
            
            // Abre a tela inicial
            configFrame.getMainFrame().remove(scrollPane);
            configFrame.getManager().getTelas().getTelaLogin().setContaLogada(null);
            configFrame.getManager().getTelas().getTelaInicial().configTelaInicial();
            
        }
        
    }
    
}
