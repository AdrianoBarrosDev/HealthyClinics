
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.dados.ListaEspecialidade;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.db.ClinicaEspecialidadeDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.CardImage;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.FiltrosEspecialidades;
import healthyclinics.ferramentas.SalvarImagem;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.ClinicaEspecialidade;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.tablesData.Especialidade;
import healthyclinics.validacoes.ValidacaoClinica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TelaGerenciarClinica implements ActionListener {

    private final ConfigFrame configFrame;
    private ContaProfissional conta;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JLayeredPane layeredPane;
    private JPanel pnlClinica;
    private PanelShadow pnlDadosClinica;
    private PanelShadow pnlImagemClinica;
    private TextFieldShadow txtNome;
    private TextFieldShadow txtCnpj;
    private TextFieldShadow txtEndereco;
    private TextFieldShadow txtCrm;
    private TextFieldShadow txtDescricao;
    private TextFieldShadow txtValor;
    private FiltrosEspecialidades pnlEspecialidades;
    private ButtonShadow btnEditar;
    private ButtonShadow btnSalvar;
    private RoundedButton btnVoltar;
    private CardImage fotoClinica;
    private ButtonBar buttonBar;
    private ValidacaoClinica validacaoClinica;
    private SalvarImagem salvarImagem;
    private ConvertBlob convertBlob;
    
    public TelaGerenciarClinica(ConfigFrame configFrame) {
        this.configFrame = configFrame;
    }
    
    
    public void configGerenciarClinica() {
        
        // Busca a conta profissional no banco de dados
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        conta = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
        
        salvarImagem = new SalvarImagem();
        
        // Configura os elementos da tela
        configGrid();
        configImages();
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
        
        validacaoClinica = new ValidacaoClinica(configFrame, layeredPane);
        
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
        pnlClinica = new JPanel();
        pnlClinica.setPreferredSize(new Dimension(1920, 928));
        pnlClinica.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlClinica.setLayout(null);
        pnlClinica.setBorder(null);
        mainPanel.add(pnlClinica, gbc);
        
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
        pnlClinica.add(layeredPane);
        
        // Painel com as informações da clínica
        pnlDadosClinica = new PanelShadow(50);
        pnlDadosClinica.setBounds(159, 214, 1531, 581);
        pnlDadosClinica.setBackground(new Color(0xD5DEEF));
        pnlDadosClinica.setShadowSize(3);
        pnlDadosClinica.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlDadosClinica, JLayeredPane.PALETTE_LAYER);
        
        // Painel com a imagem da clínica
        pnlImagemClinica = new PanelShadow(50);
        pnlImagemClinica.setBounds(34, 35, 542, 318);
        pnlImagemClinica.setBackground(new Color(0x152E52));
        pnlImagemClinica.setShadowSize(3);
        pnlImagemClinica.setShadowType(ShadowType.BOT);
        pnlDadosClinica.add(pnlImagemClinica);
        
        // Painel com as especialidades
        if(conta.getClinica() != null) {
            pnlEspecialidades = new FiltrosEspecialidades(40, conta.getClinica());
            pnlEspecialidades.configEditable();
        } else {
            pnlEspecialidades = new FiltrosEspecialidades(40);
        }
        pnlEspecialidades.setBounds(1099, 138, pnlEspecialidades.getPreferredSize().width, pnlEspecialidades.getPreferredSize().height);
        pnlDadosClinica.add(pnlEspecialidades);
        
    }
    
    
    public void configImages() {
        
        convertBlob = new ConvertBlob();
        // Imagem de perfil da clínica
        if(conta.getClinica() == null) {
            fotoClinica = new CardImage(new ImageIcon(getClass().getResource("/imagens/ImgDefaultClinica.png")), 10);
        } else {
            if(conta.getClinica().getImg() != null) {
                fotoClinica = new CardImage(convertBlob.imgConvertida(conta.getClinica().getImg()), 10);
            } else {
                fotoClinica = new CardImage(new ImageIcon(getClass().getResource("/imagens/ImgDefaultClinica.png")), 10);
            }
        }
        fotoClinica.setBounds(19, 17, 504, 280);
        pnlImagemClinica.add(fotoClinica);
        
        // Imagem Ellipse
        ImageIcon imgEllipse = new ImageIcon(getClass().getResource("/imagens/EllipseGerenciarClinica.png"));
        JLabel lblEllipse = new JLabel();
        lblEllipse.setBounds(0, -152, imgEllipse.getIconWidth(), imgEllipse.getIconHeight());
        lblEllipse.setIcon(imgEllipse);
        layeredPane.add(lblEllipse, JLayeredPane.DEFAULT_LAYER);
        
    }
    
    
    public void configText() {
        
        JLabel lblTitulo = new JLabel();
        lblTitulo.setBounds(159, 75, 760, 111);
        lblTitulo.setForeground(new Color(0xD5DEEF));
        lblTitulo.setFont(new Font("Rufina", Font.BOLD, 90));
        layeredPane.add(lblTitulo, JLayeredPane.PALETTE_LAYER);
        
        if(conta.getClinica() == null) {
            lblTitulo.setText("Cadastrar Clínica");
        } else {
            lblTitulo.setText("Editar Clínica");
        }
        
        // Texto "Informações da Clínica"
        JLabel lblInformacoesClinica = new JLabel("Informações da Clínica");
        lblInformacoesClinica.setBounds(872, 20, 395, 39);
        lblInformacoesClinica.setForeground(new Color(0x152E52));
        lblInformacoesClinica.setFont(new Font("Volkhov", Font.BOLD, 30));
        lblInformacoesClinica.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosClinica.add(lblInformacoesClinica);
        
        // Texto "Nome"
        JLabel lblDados = new JLabel("Nome");
        lblDados.setBounds(696, 75, 269, 21);
        lblDados.setForeground(new Color(0x395886));
        lblDados.setFont(new Font("Rufina", Font.BOLD, 20));
        pnlDadosClinica.add(lblDados);
        
        // Texto "CNPJ"
        JLabel lblCnpj = new JLabel("CNPJ");
        lblCnpj.setBounds(696, 147, 269, 21);
        lblCnpj.setForeground(new Color(0x395886));
        lblCnpj.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosClinica.add(lblCnpj);
        
        // Texto "Endereço"
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(696, 220, 269, 21);
        lblEndereco.setForeground(new Color(0x395886));
        lblEndereco.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosClinica.add(lblEndereco);
        
        // Texto "CRM do Responsável"
        JLabel lblCrm = new JLabel("CRM do Responsável");
        lblCrm.setBounds(696, 293, 269, 21);
        lblCrm.setForeground(new Color(0x395886));
        lblCrm.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosClinica.add(lblCrm);
        
        // Texto "Descrição"
        JLabel lblDescricao = new JLabel("Descrição");
        lblDescricao.setBounds(696, 376, 269, 21);
        lblDescricao.setForeground(new Color(0x395886));
        lblDescricao.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosClinica.add(lblDescricao);
        
        // Texto "Especialidades"
        JLabel lblEspecialidades = new JLabel("Especialidades");
        lblEspecialidades.setBounds(1110, 102, 269, 21);
        lblEspecialidades.setForeground(new Color(0x395886));
        lblEspecialidades.setFont(new Font("Rufina", Font.PLAIN, 25));
        pnlDadosClinica.add(lblEspecialidades);
        
        // Texto "Valor Mínimo das Consultas"
        JLabel lblValor = new JLabel("Valor Mínimo das Consultas");
        lblValor.setBounds(985, 515, 269, 21);
        lblValor.setForeground(new Color(0x395886));
        lblValor.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosClinica.add(lblValor);
        
    }
    
    
    public void configTxt() {
        
        // TextField Nome
        txtNome = new TextFieldShadow(40);
        txtNome.setBounds(681, 96, 353, 47);
        txtNome.setBackground(new Color(0xF0F3FA));
        txtNome.setForeground(new Color(0x395886));
        txtNome.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtNome.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlDadosClinica.add(txtNome);
        
        // TextField CNPJ
        txtCnpj = new TextFieldShadow(40);
        txtCnpj.setBounds(681, 169, 353, 47);
        txtCnpj.setBackground(new Color(0xF0F3FA));
        txtCnpj.setForeground(new Color(0x395886));
        txtCnpj.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCnpj.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlDadosClinica.add(txtCnpj);
        
        // TextField Endereço
        txtEndereco = new TextFieldShadow(40);
        txtEndereco.setBounds(681, 241, 353, 47);
        txtEndereco.setBackground(new Color(0xF0F3FA));
        txtEndereco.setForeground(new Color(0x395886));
        txtEndereco.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtEndereco.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlDadosClinica.add(txtEndereco);
        
        // TextField CRM
        txtCrm = new TextFieldShadow(40);
        txtCrm.setText(conta.getCrm());
        txtCrm.setBounds(681, 314, 353, 47);
        txtCrm.setBackground(new Color(0x395886));
        txtCrm.setDisabledTextColor(new Color(0xF0F3FA));
        txtCrm.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtCrm.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtCrm.setEnabled(false);
        pnlDadosClinica.add(txtCrm);
        
        // TextField Descrição
        txtDescricao = new TextFieldShadow(30);
        txtDescricao.setBounds(681, 399, 777, 88);
        txtDescricao.setBackground(new Color(0xF0F3FA));
        txtDescricao.setForeground(new Color(0x395886));
        txtDescricao.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtDescricao.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlDadosClinica.add(txtDescricao);
        
        // TextField Valor
        txtValor = new TextFieldShadow(40);
        txtValor.setBounds(1263, 504, 195, 47);
        txtValor.setBackground(new Color(0xF0F3FA));
        txtValor.setForeground(new Color(0x395886));
        txtValor.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtValor.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlDadosClinica.add(txtValor);
        
        // Atualiza os textfields caso a tela esteja no modo edição
        atualizarTxt();
        
    }
    
    
    public void configBtn() {
        
        // Botão "Editar"
        btnEditar = new ButtonShadow("Editar", 32);
        btnEditar.addActionListener(this);
        btnEditar.setBounds(250, 366, 110, 33);
        btnEditar.setBackground(new Color(0x395886));
        btnEditar.setForeground(new Color(0xF0F3FA));
        btnEditar.setFont(new Font("Volkhov", Font.BOLD, 15));
        btnEditar.setShadowSize(2);
        btnEditar.setShadowType(ShadowType.BOT);
        btnEditar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlDadosClinica.add(btnEditar);
        
        // Botão "Salvar"
        btnSalvar = new ButtonShadow("", 45);
        btnSalvar.addActionListener(this);
        btnSalvar.setBackground(new Color(0x395886));
        btnSalvar.setForeground(new Color(0xF0F3FA));
        btnSalvar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnSalvar.setShadowSize(2);
        btnSalvar.setShadowType(ShadowType.BOT);
        btnSalvar.changeColors(new Color(0x395886), new Color(0x638ECB));
        layeredPane.add(btnSalvar, JLayeredPane.MODAL_LAYER);
        
        if(conta.getClinica() == null) {
            btnSalvar.setText("Cadastrar");
            btnSalvar.setBounds(865, 837, 190, 52);
            
        } else {
            btnSalvar.setText("Salvar");
            btnSalvar.setBounds(1500, 812, 190, 52);
            
            // Botão "Voltar"
            btnVoltar = new RoundedButton("Voltar");
            btnVoltar.addActionListener(this);
            btnVoltar.setBounds(1529, 881, 132, 30);
            btnVoltar.setForeground(new Color(0x395886));
            btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 25));
            btnVoltar.transparentButton();
            layeredPane.add(btnVoltar, JLayeredPane.MODAL_LAYER);
            
        }
        
    }
    
    
    public void atualizarTxt() {
        
        if(conta.getClinica() != null) {
            txtNome.setText(conta.getClinica().getNome());
            txtCnpj.setText(conta.getClinica().getCnpj());
            txtEndereco.setText(conta.getClinica().getEndereco());
            txtDescricao.setText(conta.getClinica().getDescricao());

            DecimalFormat df = new DecimalFormat("0.00");
            txtValor.setText("R$" + df.format(conta.getClinica().getValor_minimo_consulta()));
        }
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnSalvar) {
            
            if(conta.getClinica() != null) {
                validacaoClinica.setClinica(conta.getClinica());
            }
            
            // Se os dados da clínica forem válidos
            if(validacaoClinica.validarClinica(txtNome.getText(), txtCnpj.getText(), txtEndereco.getText(), conta, txtDescricao.getText(), pnlEspecialidades.getFiltrosEspecialidades(), txtValor.getText()) == true) {
                
                ClinicaDAO dao = new ClinicaDAO();
                // Se o usuário ainda não possuí uma clínica
                if(conta.getClinica() == null) {
                    
                    // Retorna a clínica com os dados formatados
                    Clinica clinica = validacaoClinica.retornarNovosDados();
                    
                    // Se foi inserida uma imagem no cadastro
                    if(salvarImagem.getImageFile() != null) {
                        clinica.setImg(convertBlob.converterImagem(salvarImagem.getImageFile()));
                    }
                    
                    // Adiciona a clínica no banco de dados
                    dao.salvar(clinica);
                    
                    // Obtém as especialidades do painel
                    ListaEspecialidade listaEspecialidades = new ListaEspecialidade();
                    listaEspecialidades.setListaEspecialidades(pnlEspecialidades.getFiltrosEspecialidades());
                    List<Especialidade> listEspecialidades = listaEspecialidades.getEspecialidades();
                    
                    // Salva cada especialidade da clínica no banco de dados
                    ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
                    for(Especialidade esp : listEspecialidades) {
                        daoCE.salvar(new ClinicaEspecialidade(clinica, esp));
                    }
                    
                    // Adiciona a clínica na conta do profissional
                    conta.setClinica(clinica);
                    
                    // Adiciona a clínica na conta do profissional no banco de dados
                    ContaProfissionalDAO daoProfissional = new ContaProfissionalDAO();
                    daoProfissional.atualizar(conta);
                    
                } else { // Se o usuário já possuí uma clínica
                    
                    // Validação das informações da clínica
                    Clinica novosDados = validacaoClinica.retornarNovosDados(conta.getClinica());
                    
                    // Adiciona a clínica no banco de dados
                    dao = new ClinicaDAO();
                    dao.atualizar(novosDados);
                    
                    // Obtém as especialidades do painel
                    ListaEspecialidade listaEspecialidades = new ListaEspecialidade();
                    listaEspecialidades.setListaEspecialidades(pnlEspecialidades.getFiltrosEspecialidades());
                    List<Especialidade> listEspecialidades = listaEspecialidades.getEspecialidades();
                    
                    
                    ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
                    
                    // Limpa as especialidades antigas no banco
                    daoCE.limpar(novosDados);
                    
                    // Salva cada especialidade da clínica no banco de dados
                    for(Especialidade esp : listEspecialidades) {    
                        daoCE.salvar(new ClinicaEspecialidade(novosDados, esp));
                    }
                    
                }
                
                // Abre o perfil da clínica
                configFrame.getMainFrame().remove(scrollPane);
                PerfilClinica perfilClinica = new PerfilClinica(configFrame, conta.getClinica());
                perfilClinica.configPerfilClinica();
                
                
            } else { // Se os dados da clínica não forem válidos
                
                // Atualiza os campos formatados
                txtCnpj.setText(validacaoClinica.getCnpj());
                txtValor.setText(validacaoClinica.getValorConsultas());
                configFrame.getMainFrame().repaint();
                
            }
            
        } else if(e.getSource() == btnEditar) {
            
            // Abre a escolha de imagem
            if(salvarImagem.mostrarTela()) {
                
                // Muda a imagem da foto da clínica
                fotoClinica.setImage(salvarImagem.getImgSelecionada());
                
                // Muda a foto da clínica no banco se o usuário possuir uma clínica
                if(conta.getClinica() != null) {
                    conta.getClinica().setImg(convertBlob.converterImagem(salvarImagem.getImageFile()));
                    
                    // Atualiza a clínica no banco
                    ClinicaDAO dao = new ClinicaDAO();
                    dao.atualizar(conta.getClinica());
                    
                }
                
                configFrame.getMainFrame().repaint(); // Atualiza o frame
                
            }
            
        } else if(e.getSource() == btnVoltar) {
            
            // Abre o perfil da clínica
            configFrame.getMainFrame().remove(scrollPane);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, conta.getClinica());
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
    
}
