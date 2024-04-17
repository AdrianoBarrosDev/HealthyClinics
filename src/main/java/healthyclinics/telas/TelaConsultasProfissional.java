
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ConsultaDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.erros.ErrosConsultaProfissional;
import healthyclinics.ferramentas.EndBar;
import healthyclinics.ferramentas.ButtonBar;
import healthyclinics.ferramentas.LinesComponent;
import healthyclinics.layouts.RoundedButton;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.shadows.ButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.shadows.TextFieldShadow;
import healthyclinics.tablesData.Consulta;
import healthyclinics.tablesData.ContaProfissional;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TelaConsultasProfissional implements ActionListener {

    private final ConfigFrame configFrame;
    private final Consulta consulta;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JLayeredPane layeredPane;
    private JPanel pnlConsulta;
    private PanelShadow pnlDadosConsulta;
    private PanelShadow pnlRelatorio;
    private ButtonShadow btnFinalizar;
    private ButtonShadow btnCancelar;
    private RoundedButton btnVoltar;
    private JTextArea txaRelatorio;
    private ErrosConsultaProfissional erros;
    
    public TelaConsultasProfissional(ConfigFrame configFrame, Consulta consulta) {
        this.configFrame = configFrame;
        this.consulta = consulta;
    }
    
    
    public void configDadosConsulta() {
        
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
        ButtonBar buttonBar = new ButtonBar(configFrame, scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 0;
        gbc.weighty = 0;
        mainPanel.add(buttonBar.getButtonBar());
        
        // Painel de imagens
        pnlConsulta = new JPanel();
        pnlConsulta.setPreferredSize(new Dimension(1920, 928));
        pnlConsulta.setBackground(new Color(0xF0F3FA));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        pnlConsulta.setLayout(null);
        pnlConsulta.setBorder(null);
        mainPanel.add(pnlConsulta, gbc);
        
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
        pnlConsulta.add(layeredPane);
        
        // Painel com as informações da consulta
        pnlDadosConsulta = new PanelShadow(50);
        pnlDadosConsulta.setBounds(555, 199, 810, 619);
        pnlDadosConsulta.setBackground(new Color(0xD5DEEF));
        pnlDadosConsulta.setShadowSize(3);
        pnlDadosConsulta.setShadowType(ShadowType.BOT);
        layeredPane.add(pnlDadosConsulta, JLayeredPane.MODAL_LAYER);
        
        // Painel do relatório
        pnlRelatorio = new PanelShadow(35);
        pnlRelatorio.setBounds(96, 384, 619, 110);
        pnlRelatorio.setBackground(new Color(0xF0F3FA));
        pnlRelatorio.setShadowSize(3);
        pnlRelatorio.setShadowType(ShadowType.BOT);
        pnlDadosConsulta.add(pnlRelatorio);
        
        // Erros
        erros = new ErrosConsultaProfissional(layeredPane);
        
    }
    
    
    public void configImages() {
        
        // Imagem flor azul
        ImageIcon imgBlueFlower = new ImageIcon(getClass().getResource("/imagens/ImgBlueFlower.png"));
        
        // Imagem flor azul esquerda
        JLabel lblFlowerLeft = new JLabel();
        lblFlowerLeft.setBounds(-144, 390, imgBlueFlower.getIconWidth(), imgBlueFlower.getIconHeight());
        lblFlowerLeft.setIcon(imgBlueFlower);
        layeredPane.add(lblFlowerLeft, JLayeredPane.DEFAULT_LAYER);
        
        // Imagem flor azul direita
        JLabel lblFlowerRight = new JLabel();
        lblFlowerRight.setBounds(1523, -72, imgBlueFlower.getIconWidth(), imgBlueFlower.getIconHeight());
        lblFlowerRight.setIcon(imgBlueFlower);
        layeredPane.add(lblFlowerRight, JLayeredPane.DEFAULT_LAYER);
        
        // Ellipse da esquerda
        ImageIcon imgEllipseLeft = new ImageIcon(getClass().getResource("/imagens/EllipseDadosConsultaLeft.png"));
        JLabel lblEllipseLeft = new JLabel();
        lblEllipseLeft.setBounds(0, 0, imgEllipseLeft.getIconWidth(), imgEllipseLeft.getIconHeight());
        lblEllipseLeft.setIcon(imgEllipseLeft);
        layeredPane.add(lblEllipseLeft, JLayeredPane.PALETTE_LAYER);
        
        // Ellipse da direita
        ImageIcon imgEllipseRight = new ImageIcon(getClass().getResource("/imagens/EllipseDadosConsultaRight.png"));
        JLabel lblEllipseRight = new JLabel();
        lblEllipseRight.setBounds(1123, -152, imgEllipseRight.getIconWidth(), imgEllipseRight.getIconHeight());
        lblEllipseRight.setIcon(imgEllipseRight);
        layeredPane.add(lblEllipseRight, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void configLines() {
        
        // Linhas do painel inicial
        LinesComponent linesPnlInicial = new LinesComponent();
        linesPnlInicial.setBounds(0, 0, 810, 360);
        
        linesPnlInicial.addLine(46, 28, 146, 28, 2, new Color(0x395886)); // Linha horizontal, canto superior esquerdo
        linesPnlInicial.addLine(46, 28, 46, 128, 2, new Color(0x395886)); // Linha vertical, canto superior esquerdo

        linesPnlInicial.addLine(664, 28, 764, 28, 2, new Color(0x395886)); // Linha horizontal, canto superior direito
        linesPnlInicial.addLine(764, 28, 764, 128, 2, new Color(0x395886)); // Linha vertical, canto superior direito
        
        linesPnlInicial.addLine(55, 340, 360, 340, 2, new Color(0x395886)); // Linha horizontal, canto inferior esquerdo
        linesPnlInicial.addLine(55, 290, 55, 340, 2, new Color(0x395886)); // Linha vertical, canto inferior esquerdo

        linesPnlInicial.addLine(450, 340, 755, 340, 2, new Color(0x395886)); // Linha horizontal, canto inferior direito
        linesPnlInicial.addLine(755, 290, 755, 340, 2, new Color(0x395886)); // Linha vertical, canto inferior direito

        pnlDadosConsulta.add(linesPnlInicial);
        
    }
    
    
    public void configText() {
        
        // Texto "Dados Consulta"
        JLabel lblDados = new JLabel("Dados Consulta");
        lblDados.setBounds(557, 94, 795, 105);
        lblDados.setForeground(new Color(0x152E52));
        lblDados.setFont(new Font("Rufina", Font.BOLD, 100));
        lblDados.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(lblDados, JLayeredPane.MODAL_LAYER);
        
        // Texto "Paciente"
        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setBounds(110, 47, 190, 20);
        lblPaciente.setForeground(new Color(0x395886));
        lblPaciente.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblPaciente);
        
        // Texto "Clínica"
        JLabel lblClinica = new JLabel("Clínica");
        lblClinica.setBounds(110, 117, 190, 20);
        lblClinica.setForeground(new Color(0x395886));
        lblClinica.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblClinica);
        
        // Texto "Convênio"
        JLabel lblConvenio = new JLabel("Convênio");
        lblConvenio.setBounds(110, 187, 190, 20);
        lblConvenio.setForeground(new Color(0x395886));
        lblConvenio.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblConvenio);
        
        // Texto "Data da Consulta"
        JLabel lblData = new JLabel("Data da Consulta");
        lblData.setBounds(454, 47, 190, 20);
        lblData.setForeground(new Color(0x395886));
        lblData.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblData);
        
        // Texto "Horário"
        JLabel lblHorario = new JLabel("Horário");
        lblHorario.setBounds(454, 117, 190, 20);
        lblHorario.setForeground(new Color(0x395886));
        lblHorario.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblHorario);
        
        // Texto "Médico"
        JLabel lblMedico = new JLabel("Médico");
        lblMedico.setBounds(454, 187, 190, 20);
        lblMedico.setForeground(new Color(0x395886));
        lblMedico.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblMedico);
        
        // Texto "Valor"
        JLabel lblValor = new JLabel("Valor");
        lblValor.setBounds(359, 257, 90, 20);
        lblValor.setForeground(new Color(0x395886));
        lblValor.setFont(new Font("Rufina", Font.PLAIN, 20));
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosConsulta.add(lblValor);
        
        // Texto "Relatório"
        JLabel lblRelatorio = new JLabel("Relatório");
        lblRelatorio.setBounds(110, 356, 190, 20);
        lblRelatorio.setForeground(new Color(0x395886));
        lblRelatorio.setFont(new Font("Rufina", Font.PLAIN, 20));
        pnlDadosConsulta.add(lblRelatorio);
        
        // Relatório
        txaRelatorio = new JTextArea();
        txaRelatorio.setText(consulta.getRelatorio());
        txaRelatorio.setForeground(new Color(0x395886));
        txaRelatorio.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txaRelatorio.setOpaque(false);
        txaRelatorio.setLineWrap(true);
        txaRelatorio.setWrapStyleWord(true);
        
        // Scrollpane para o relatório
        JScrollPane scrollPane = new JScrollPane(txaRelatorio);
        scrollPane.setBounds(11, 8, 598, 91);
        scrollPane.getViewport().setBackground(new Color(0xF0F3FA));
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        pnlRelatorio.add(scrollPane);
        
        // Se o médico que está visualizando a consulta, é o mesmo médico responsável por ela
        if(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getCpf().equals(consulta.getMedico().getConta().getCpf())) {
            
            // Se o relatório for nulo
            if(consulta.getRelatorio() == null) {
                
                // Texto "Relatório não enviado pelo médico."
                erros.erroRelatorioNaoEnviado();
                
                // Permite o médico finalizar ou cancelar a consulta
                configBtnsEdicao();

            } else { // Se houver um relatório
                // Mostra na tela o status da consulta
                mostrarStatusConsulta();

            }
            
        } else { // Se o médico que está visualizando os dados, não for o mesmo médico da consulta
            // Mostra na tela o status da consulta
            mostrarStatusConsulta();
            
        }
        
        
    }
    
    
    public void configTxt() {
        
        // TextField Paciente
        TextFieldShadow txtPaciente = new TextFieldShadow(40);
        txtPaciente.setText(consulta.getPaciente().getConta().getNome());
        txtPaciente.setBounds(96, 68, 275, 45);
        txtPaciente.setBackground(new Color(0xF0F3FA));
        txtPaciente.setDisabledTextColor(new Color(0x395886));
        txtPaciente.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtPaciente.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtPaciente.setEnabled(false);
        pnlDadosConsulta.add(txtPaciente);
        
        // TextField Clínica
        TextFieldShadow txtClinica = new TextFieldShadow(40);
        txtClinica.setText(consulta.getClinica().getNome());
        txtClinica.setBounds(96, 138, 275, 45);
        txtClinica.setBackground(new Color(0xF0F3FA));
        txtClinica.setDisabledTextColor(new Color(0x395886));
        txtClinica.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtClinica.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtClinica.setEnabled(false);
        pnlDadosConsulta.add(txtClinica);
        
        // TextField Convênio
        TextFieldShadow txtConvenio = new TextFieldShadow(40);
        txtConvenio.setText(consulta.getPaciente().getConvenio());
        txtConvenio.setBounds(96, 208, 275, 45);
        txtConvenio.setBackground(new Color(0xF0F3FA));
        txtConvenio.setDisabledTextColor(new Color(0x395886));
        txtConvenio.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtConvenio.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtConvenio.setEnabled(false);
        pnlDadosConsulta.add(txtConvenio);
        
        // TextField Data da Consulta
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        TextFieldShadow txtData = new TextFieldShadow(40);
        txtData.setText(df.format(consulta.getDiaHorario().getDia().getDia()));
        txtData.setBounds(440, 68, 275, 45);
        txtData.setBackground(new Color(0xF0F3FA));
        txtData.setDisabledTextColor(new Color(0x395886));
        txtData.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtData.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtData.setEnabled(false);
        pnlDadosConsulta.add(txtData);
        
        // TextField Horário da Consulta
        TextFieldShadow txtHorario = new TextFieldShadow(40);
        txtHorario.setText(consulta.getDiaHorario().getHorario().getHorario());
        txtHorario.setBounds(440, 138, 275, 45);
        txtHorario.setBackground(new Color(0xF0F3FA));
        txtHorario.setDisabledTextColor(new Color(0x395886));
        txtHorario.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtHorario.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtHorario.setEnabled(false);
        pnlDadosConsulta.add(txtHorario);
        
        // TextField Nome Médico
        TextFieldShadow txtMedico = new TextFieldShadow(40);
        txtMedico.setText(consulta.getMedico().getConta().getNome());
        txtMedico.setBounds(440, 208, 275, 45);
        txtMedico.setBackground(new Color(0xF0F3FA));
        txtMedico.setDisabledTextColor(new Color(0x395886));
        txtMedico.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtMedico.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtMedico.setEnabled(false);
        pnlDadosConsulta.add(txtMedico);
        
        // TextField Valor
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        TextFieldShadow txtValor = new TextFieldShadow(40);
        txtValor.setText("R$" + decimalFormatter.format(consulta.getClinica().getValor_minimo_consulta()));
        txtValor.setBounds(306, 278, 198, 45);
        txtValor.setBackground(new Color(0xF0F3FA));
        txtValor.setDisabledTextColor(new Color(0x395886));
        txtValor.setFont(new Font("Volkhov", Font.PLAIN, 15));
        txtValor.setBorder(BorderFactory.createEmptyBorder(0, 20, 3, 20));
        txtValor.setEnabled(false);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosConsulta.add(txtValor);
        
    }
    
    
    public void configBtn() {
        
        // Botão "Voltar"
        btnVoltar = new RoundedButton("Voltar");
        btnVoltar.addActionListener(this);
        btnVoltar.setBounds(871, 849, 195, 30);
        btnVoltar.setForeground(new Color(0x152E52));
        btnVoltar.setFont(new Font("Volkhov", Font.BOLD, 30));
        btnVoltar.transparentButton();
        layeredPane.add(btnVoltar, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void mostrarStatusConsulta() {
        
        txaRelatorio.setEditable(false);
        txaRelatorio.setFocusable(false);
            
        JLabel lblStatus = new JLabel("CONSULTA " + consulta.getStatus().toUpperCase());
        lblStatus.setBounds(110, 530, 615, 40);
        lblStatus.setForeground(new Color(0x395886));
        lblStatus.setFont(new Font("Volkhov", Font.BOLD, 30));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        pnlDadosConsulta.add(lblStatus);
        
    }
    
    
    public void configBtnsEdicao() {
        
        // Botão "Finalizar"
        btnFinalizar = new ButtonShadow("Finalizar", 50);
        btnFinalizar.addActionListener(this);
        btnFinalizar.setBounds(195, 547, 190, 52);
        btnFinalizar.setBackground(new Color(0x395886));
        btnFinalizar.setForeground(new Color(0xF0F3FA));
        btnFinalizar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnFinalizar.setShadowSize(2);
        btnFinalizar.setShadowType(ShadowType.BOT);
        btnFinalizar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlDadosConsulta.add(btnFinalizar);
        
        // Botão "Cancelar"
        btnCancelar = new ButtonShadow("Cancelar", 50);
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(425, 547, 190, 52);
        btnCancelar.setBackground(new Color(0x395886));
        btnCancelar.setForeground(new Color(0xF0F3FA));
        btnCancelar.setFont(new Font("Volkhov", Font.BOLD, 20));
        btnCancelar.setShadowSize(2);
        btnCancelar.setShadowType(ShadowType.BOT);
        btnCancelar.changeColors(new Color(0x395886), new Color(0x638ECB));
        pnlDadosConsulta.add(btnCancelar);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnVoltar) {
            // Abre a tela anterior
            configFrame.getMainFrame().remove(scrollPane);
            
            ContaProfissionalDAO dao = new ContaProfissionalDAO();
            ContaProfissional contaLogada = dao.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
            
            PerfilClinica perfilClinica = new PerfilClinica(configFrame, contaLogada.getClinica());
            perfilClinica.configPerfilClinica();
            
        } else if(e.getSource() == btnFinalizar) {
            
            if(txaRelatorio.getText().isEmpty() || txaRelatorio.getText().isBlank()) {
                erros.erroRelatorioInvalido();
                
            } else {
                
                erros.removerErros();
                
                consulta.setRelatorio(txaRelatorio.getText());
                consulta.setStatus("Encerrada");
                
                // Atualiza a consulta no banco
                ConsultaDAO consultaDao = new ConsultaDAO();
                consultaDao.atualizar(consulta);
                
                pnlDadosConsulta.remove(btnFinalizar);
                pnlDadosConsulta.remove(btnCancelar);
                
                mostrarStatusConsulta();
                
                configFrame.getMainFrame().repaint();
                
            }
            
        } else if(e.getSource() == btnCancelar) {
            
            erros.removerErros();
            
            consulta.setStatus("Cancelada");

            pnlDadosConsulta.remove(btnFinalizar);
            pnlDadosConsulta.remove(btnCancelar);

            mostrarStatusConsulta();
            
            configFrame.getMainFrame().repaint();
            
        }
        
    }
    
}
