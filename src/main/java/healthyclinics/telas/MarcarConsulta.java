
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ConsultaDAO;
import healthyclinics.db.ContaClienteDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.db.ContaProfissionalDHDAO;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.CardImage;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.layouts.CustomComboBox;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.Consulta;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.tablesData.ContaProfissionalDH;
import healthyclinics.tablesData.DiaHorario;
import healthyclinics.validacoes.ValidacaoConsulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MarcarConsulta implements ActionListener {

    private ConfigFrame configFrame;
    private JScrollPane scrollPane;
    private Clinica clinica;
    private JPanel mainPanel;
    private JPanel pnlImage;
    private JPanel pnlMarcarConsulta;
    private PanelShadow pnlNome;
    private PanelShadow pnlInformacoes;
    private ButtonShadow btnConfirmar;
    private RoundedButton btnVoltar;
    private ContaCliente contaUsuario;
    private TextFieldShadow txtData;
    private CustomComboBox cboMedicos;
    private CustomComboBox cboHorarios;
    private int posHorario;
    private ValidacaoConsulta validacaoConsulta;
    private ContaProfissional medico;
    private DiaHorario dh;
    
    public MarcarConsulta(ConfigFrame configFrame, Clinica clinica) {
        this.configFrame = configFrame;
        this.clinica = clinica;
        
        ContaClienteDAO dao = new ContaClienteDAO();
        this.contaUsuario = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
    }
    
    
    public void configMarcarConsulta() {
        
        // Configura os elementos da tela
        configGrid();
        configImages();
        configText();
        configTxt();
        configComboBox();
        configBtn();
        
        // Define o scroll no início da tela
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
                }
            }
        );
        
        // Validação da consulta
        validacaoConsulta = new ValidacaoConsulta(pnlInformacoes);
        
        configFrame.getMainFrame().repaint();
        
    }
    
    
    public void configGrid() {
        
        // Configura as layers
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1920, 2012));
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
        ButtonBar buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 0;
        gbc.weighty = 0;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel de imagens
        pnlImage = new JPanel();
        pnlImage.setPreferredSize(new Dimension(1920, 414));
        pnlImage.setBackground(new Color(0x152E52));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlImage.setLayout(null);
        pnlImage.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x395886)));
        mainPanel.add(pnlImage, gbc);
        
        // Painel principal da tela
        pnlMarcarConsulta = new JPanel();
        pnlMarcarConsulta.setPreferredSize(new Dimension(1920, 1236));
        pnlMarcarConsulta.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.weighty = 0;
        pnlMarcarConsulta.setLayout(null);
        mainPanel.add(pnlMarcarConsulta, gbc);
        
        // Painel final (créditos)
        EndBar pnlFinal = new EndBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 3;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(pnlFinal, gbc);
        
        // Painel com o nome da clínica
        pnlNome = new PanelShadow(45);
        pnlNome.setBounds(684, 27, 552, 60);
        pnlNome.setBackground(new Color(0x152E52));
        pnlNome.setShadowSize(3);
        pnlNome.setShadowType(ShadowType.BOT);
        pnlMarcarConsulta.add(pnlNome);
        
        // Painel para inserir as informações
        pnlInformacoes = new PanelShadow(45);
        pnlInformacoes.setBounds(112, 378, 914, 464);
        pnlInformacoes.setBackground(new Color(0xD5DEEF));
        pnlInformacoes.setShadowSize(3);
        pnlInformacoes.setShadowType(ShadowType.BOT);
        pnlMarcarConsulta.add(pnlInformacoes);
        
    }
    
    
    public void configImages() {
        
        // Imagem da clínica
        ConvertBlob convertBlob = new ConvertBlob();
        CardImage imgClinica = new CardImage(convertBlob.imgConvertida(clinica.getImg()), 0);
        imgClinica.setBounds(0, 0, 1920, 413);
        pnlImage.add(imgClinica);
        
        ImageIcon imgRectangle = new ImageIcon(getClass().getResource("/imagens/RectangleMarcarConsulta.png"));
        
        // Imagem retângulo superior
        JLabel lblRetanguloSuperior = new JLabel();
        lblRetanguloSuperior.setBounds(-167, -192, imgRectangle.getIconWidth(), imgRectangle.getIconHeight());
        lblRetanguloSuperior.setIcon(imgRectangle);
        pnlMarcarConsulta.add(lblRetanguloSuperior);
        
        // Imagem retângulo inferior
        JLabel lblRetanguloInferior = new JLabel();
        lblRetanguloInferior.setBounds(1418, 885, imgRectangle.getIconWidth(), imgRectangle.getIconHeight());
        lblRetanguloInferior.setIcon(imgRectangle);
        pnlMarcarConsulta.add(lblRetanguloInferior);
        
        // Imagem médica virtual
        ImageIcon imgMedica = new ImageIcon(getClass().getResource("/imagens/ImgMedicaVirtual.png"));
        JLabel lblMedica = new JLabel();
        lblMedica.setBounds(1136, 175, imgMedica.getIconWidth(), imgMedica.getIconHeight());
        lblMedica.setIcon(imgMedica);
        pnlMarcarConsulta.add(lblMedica);
        
        
    }
    
    
    public void configText() {
        
        // Nome da clínica
        JLabel lblNome = new JLabel(clinica.getNome().toUpperCase());
        lblNome.setBounds(33, 9, 488, 39);
        lblNome.setForeground(new Color(0xD5DEEF));
        lblNome.setFont(new Font("Rufina", Font.BOLD, 40));
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        pnlNome.add(lblNome);
        
        // Texto "Marcar Consulta"
        JLabel lblMarcar = new JLabel("Marcar Consulta");
        lblMarcar.setBounds(124, 258, 713, 89);
        lblMarcar.setForeground(new Color(0x152E52));
        lblMarcar.setFont(new Font("Rufina", Font.BOLD, 90));
        pnlMarcarConsulta.add(lblMarcar);
        
        // Texto "Paciente"
        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setBounds(80, 44, 250, 22);
        lblPaciente.setForeground(new Color(0x395886));
        lblPaciente.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblPaciente);
        
        // Texto "Clínica"
        JLabel lblClinica = new JLabel("Clínica");
        lblClinica.setBounds(80, 142, 250, 22);
        lblClinica.setForeground(new Color(0x395886));
        lblClinica.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblClinica);
        
        // Texto "Convênio"
        JLabel lblConvenio = new JLabel("Convênio");
        lblConvenio.setBounds(80, 239, 250, 22);
        lblConvenio.setForeground(new Color(0x395886));
        lblConvenio.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblConvenio);
        
        // Texto "Valor"
        JLabel lblValor = new JLabel("Valor");
        lblValor.setBounds(239, 336, 83, 21);
        lblValor.setForeground(new Color(0x395886));
        lblValor.setFont(new Font("Volkhov", Font.PLAIN, 20));
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInformacoes.add(lblValor);
        
        // Texto "Data da Consulta"
        JLabel lblData = new JLabel("Data da Consulta");
        lblData.setBounds(590, 45, 219, 21);
        lblData.setForeground(new Color(0x395886));
        lblData.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblData);
        
        // Texto "Horário"
        JLabel lblHorario = new JLabel("Horário");
        lblHorario.setBounds(590, 137, 219, 21);
        lblHorario.setForeground(new Color(0x395886));
        lblHorario.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblHorario);
        
        // Texto "Médico"
        JLabel lblMedico = new JLabel("Médico");
        lblMedico.setBounds(590, 233, 219, 21);
        lblMedico.setForeground(new Color(0x395886));
        lblMedico.setFont(new Font("Volkhov", Font.PLAIN, 20));
        pnlInformacoes.add(lblMedico);
        
        
    }
    
    
    public void configTxt() {
        
        // TextField para mostrar o nome do paciente
        TextFieldShadow txtPaciente = new TextFieldShadow(45);
        txtPaciente.setText(contaUsuario.getConta().getNome());
        txtPaciente.setBounds(65, 66, 432, 50);
        txtPaciente.setBackground(new Color(0x395886));
        txtPaciente.setDisabledTextColor(new Color(0xF0F3FA));
        txtPaciente.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtPaciente.setEnabled(false);
        txtPaciente.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlInformacoes.add(txtPaciente);
        
        // TextField para mostrar o nome da clínica
        TextFieldShadow txtClinica = new TextFieldShadow(45);
        txtClinica.setText(clinica.getNome());
        txtClinica.setBounds(65, 163, 432, 50);
        txtClinica.setBackground(new Color(0x395886));
        txtClinica.setDisabledTextColor(new Color(0xF0F3FA));
        txtClinica.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtClinica.setEnabled(false);
        txtClinica.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlInformacoes.add(txtClinica);
        
        // TextField para mostrar o convênio do usuário
        TextFieldShadow txtConvenio = new TextFieldShadow(45);
        txtConvenio.setText(contaUsuario.getConvenio());
        txtConvenio.setBounds(65, 260, 432, 50);
        txtConvenio.setBackground(new Color(0x395886));
        txtConvenio.setDisabledTextColor(new Color(0xF0F3FA));
        txtConvenio.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtConvenio.setEnabled(false);
        txtConvenio.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        pnlInformacoes.add(txtConvenio);
        
        // TextField para mostrar o valor da consulta
        DecimalFormat formatter = new DecimalFormat("0.00");
        TextFieldShadow txtValor = new TextFieldShadow(45);
        txtValor.setText("R$" + formatter.format(clinica.getValor_minimo_consulta()));
        txtValor.setBounds(186, 357, 190, 50);
        txtValor.setBackground(new Color(0x395886));
        txtValor.setDisabledTextColor(new Color(0xF0F3FA));
        txtValor.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtValor.setEnabled(false);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
        pnlInformacoes.add(txtValor);
        
        // TextField para inserir a data da consulta
        txtData = new TextFieldShadow(45);
        txtData.setBounds(575, 66, 275, 50);
        txtData.setBackground(new Color(0xF0F3FA));
        txtData.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtData.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtData.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                limparComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                limparComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                limparComboBox();
            }
            
            private void limparComboBox() {
                cboMedicos.removeAllItems();
                cboHorarios.removeAllItems();
                cboHorarios.addItem("");
                // Adiciona os horários na combobox se ela estiver vazia
                for(int i = 6; i <= 22; i++) {
                    cboHorarios.addItem(String.valueOf(i) + ":" + "00");
                    if(i < 22) {
                        cboHorarios.addItem(String.valueOf(i) + ":" + "30");
                    }
                }
            }
            
        });
        pnlInformacoes.add(txtData);
        
    }
    
    
    public void configComboBox() {
        
        // ComboBox para escolher o médico
        cboHorarios = new CustomComboBox();
        cboHorarios.setBounds(575, 163, 275, 43);
        cboHorarios.setFont(new Font("Volkhov", Font.PLAIN, 15));
        cboHorarios.addItem("");
        pnlInformacoes.add(cboHorarios);
        
        // Adiciona os horários na combobox
        for(int i = 6; i <= 22; i++) {
            cboHorarios.addItem(String.valueOf(i) + ":" + "00");
            if(i < 22) {
                cboHorarios.addItem(String.valueOf(i) + ":" + "30");
            }
        }
        
        // ComboBox para escolher o médico
        cboMedicos = new CustomComboBox();
        cboMedicos.setBounds(575, 254, 275, 43);
        cboMedicos.setFont(new Font("Volkhov", Font.PLAIN, 15));
        pnlInformacoes.add(cboMedicos);
        
        
        ActionListener cboActionListener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Se a data digitada na textfield for válida
                if(validacaoConsulta.validarData(txtData.getText())) {
                    
                    if(e.getSource() == cboHorarios) { // Se um item da combobox dos horários for selecionada
                        
                        // Remove os médicos da combobox
                        cboMedicos.removeAllItems();
                        
                        // Se o item selecionado não é nulo
                        if(cboHorarios.getSelectedItem() != null) {
                            String horarioSelecionado = String.valueOf(cboHorarios.getSelectedItem()); // Horário selecionado na combobox

                            // Se o horário selecionado for diferente de ""
                            if(!horarioSelecionado.equals("")) {
                                
                                ContaProfissionalDAO dao = new ContaProfissionalDAO();
                                List<ContaProfissional> listaMedicos = dao.getListaMedicos(clinica);
                                
                                for(ContaProfissional medico : listaMedicos) {

                                    // Váriavel que define se a data digitada já existe na agenda do médico
                                    boolean dataCadastrada = false;
                                    
                                    // Busca as datas que o médico possui consultando no banco de dados
                                    ContaProfissionalDHDAO daoCH = new ContaProfissionalDHDAO();
                                    List<ContaProfissionalDH> agenda = daoCH.getAgenda(medico);
                                    
                                    for(ContaProfissionalDH contaDh : agenda) {

                                        // Se na agenda do médico, a data selecionada for igual a data digitada na textfield
                                        if(contaDh.getDiaHorario().getDia().toString().equals(txtData.getText())) {
                                            dataCadastrada = true;
                                            
                                            // Se o horário selecionado não estiver sendo utilizado pelo médico
                                            if(!contaDh.getDiaHorario().getHorario().getHorario().equals(horarioSelecionado)) {
                                                // Adiciona o médico na combobox para ser selecionado
                                                cboMedicos.addItem(medico.getConta().getNome());   
                                            } 
                                        }
                                    }

                                    // Se a data digitada não existir ainda na agenda do médico
                                    if(dataCadastrada == false) {

                                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                        try {

                                            Date dataDigitada = df.parse(txtData.getText());
                                            LocalDate localDate = LocalDate.now();
                                            Date dataAtual = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                                            // Se a data digitada for maior ou igual a data atual
                                            if(dataDigitada.compareTo(df.parse(df.format(dataAtual))) >= 0) {
                                                
                                                // Adiciona o médico na combobox para ser selecionado
                                                cboMedicos.addItem(medico.getConta().getNome());                                                

                                            }

                                        } catch (ParseException ex) {
                                            
                                        }

                                    }
                                }
                            }
                        }
                    } 
                }
            }
        };
        
        cboHorarios.addActionListener(cboActionListener);
        cboHorarios.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(validacaoConsulta.validarData(txtData.getText())) {
                    
                    validacaoConsulta.getErros().removerErros(); // Remove os erros da tela
                    if(cboHorarios.getSelectedItem() != null) {
                        definirHorarios();
                        
                        if(cboHorarios.getSelectedItem().toString().isEmpty()) {
                            cboHorarios.setSelectedItem(1);
                            cboHorarios.removeItemAt(0);
                        }
                        
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                validacaoConsulta.getErros().removerErros();
                if(!validacaoConsulta.validarData(txtData.getText())) {
                    cboHorarios.removeAllItems();
                    validacaoConsulta.getErros().mostrarErroData();
                }
                pnlInformacoes.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
        cboMedicos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(cboHorarios.getItemAt(0) != null) {
                    if(cboHorarios.getItemAt(0).equals("")) {
                        validacaoConsulta.getErros().removerErros();
                        if(!validacaoConsulta.validarData(txtData.getText())) {
                            validacaoConsulta.getErros().mostrarErroData();
                        }
                        validacaoConsulta.getErros().mostrarErroHorario();
                        pnlInformacoes.repaint();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        
    }
    
    
    public void configBtn() {
        
        // Botão "Confirmar"
        btnConfirmar = new ButtonShadow("Confirmar", 45);
        btnConfirmar.addActionListener(this);
        btnConfirmar.setBounds(451, 895, 190, 50);
        btnConfirmar.setBackground(new Color(0x395886));
        btnConfirmar.setForeground(new Color(0xF0F3FA));
        btnConfirmar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnConfirmar.setShadowSize(2);
        btnConfirmar.setShadowType(ShadowType.CENTER);
        btnConfirmar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlMarcarConsulta.add(btnConfirmar);
        
        // Botão "Voltar"
        btnVoltar = new RoundedButton("Voltar");
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(480, 959, 132, 36);
        btnVoltar.setForeground(new Color(0x395886));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnVoltar.transparentButton();
        pnlMarcarConsulta.add(btnVoltar);
        
    }
    
    
    private void definirHorarios() {
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dfHora = new SimpleDateFormat("HH:mm:ss");
        if(validacaoConsulta.validarData(txtData.getText())) {
            try {
                cboHorarios.removeAllItems();
                if(validacaoConsulta.getDataConsulta().toString().equals(df.format(new Date()))) {
                    
                    cboHorarios.addItem("");
                    Date horaAtual = new Date();
                    Date horarioEscolhido;
                    
                    // Adiciona os horários na combobox
                    for(int i = 6; i <= 22; i++) {
                        if(i < 10) {
                            horarioEscolhido = dfHora.parse("0" + i + ":" + "00" + ":" + "00");
                        } else {
                            horarioEscolhido = dfHora.parse(i + ":" + "00" + ":" + "00");
                        }
                        if(horarioEscolhido.compareTo(dfHora.parse(dfHora.format(horaAtual))) > 0) {
                            cboHorarios.addItem(String.valueOf(i) + ":" + "00");
                            if(i < 22) {
                                cboHorarios.addItem(String.valueOf(i) + ":" + "30");
                            }
                        } else {
                            
                        }
                            
                    }
                    
                } else {
                    
                    cboHorarios.addItem("");
                    // Adiciona os horários na combobox
                    for(int i = 6; i <= 22; i++) {
                        cboHorarios.addItem(String.valueOf(i) + ":" + "00");
                        if(i < 22) {
                            cboHorarios.addItem(String.valueOf(i) + ":" + "30");
                        }
                    }
                    
                }
            } catch (ParseException ex) {
                
            }
            
        }
        
    }
    
    
    public void limparTela() {
        
        // Remove as alterações do usuário nos elementos da tela
        txtData.setText("");
        cboMedicos.removeAllItems();
        cboHorarios.removeAllItems();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnConfirmar) { // Se o botão "Confirmar" for pressionado
            
            String horario;
            if(cboHorarios.getSelectedItem() == null) {
                horario = "";
            } else {
                horario = cboHorarios.getSelectedItem().toString();
            }
            
            String medico;
            if(cboMedicos.getSelectedItem() == null) {
                medico = "";
            } else {
                medico = cboMedicos.getSelectedItem().toString();
            }
            
            // Se a consulta for válida
            if(validacaoConsulta.validarConsulta(contaUsuario, clinica, txtData.getText(), horario, medico)) {
                
                // Salva a consulta no banco de dados
                ConsultaDAO daoConsulta = new ConsultaDAO();
                daoConsulta.salvar(new Consulta(contaUsuario, clinica, validacaoConsulta.getMedico(), validacaoConsulta.getDiaHorario(), "Em andamento"));
                
                // Salva o médico como ocupado neste horário no banco
                ContaProfissionalDHDAO dhDao = new ContaProfissionalDHDAO();
                dhDao.salvar(new ContaProfissionalDH(validacaoConsulta.getMedico(), validacaoConsulta.getDiaHorario()));
                
                // Abre a tela de confirmação
                TelaConfirmar telaConfirmacao = new TelaConfirmar(configFrame, scrollPane, clinica);
                telaConfirmacao.configTelaConfirmar();
                
            }
            
        } else if(e.getSource() == btnVoltar) { // Se o botão "Voltar" for pressionado
            
            // Abre a tela de perfil da clínica
            configFrame.getMainFrame().remove(scrollPane);
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, clinica);
            perfilClinica.configPerfilClinica();
            
        }
        
    }
    
    
}
