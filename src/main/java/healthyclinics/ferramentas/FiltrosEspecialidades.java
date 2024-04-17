
package healthyclinics.ferramentas;

import healthyclinics.db.ClinicaEspecialidadeDAO;
import healthyclinics.shadows.CircleButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import healthyclinics.tablesData.Clinica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FiltrosEspecialidades extends PanelShadow implements ActionListener {
    
    private String [] filtrosEspecialidades;
    private TableClinicas tabelaClinicas;
    private ImageIcon imgCheck;
    private CircleButtonShadow btnGeral;
    private CircleButtonShadow btnPediatria;
    private CircleButtonShadow btnCardiologia;
    private CircleButtonShadow btnOftalmologia;
    private CircleButtonShadow btnOdontologia;
    private CircleButtonShadow btnOrtopedia;
    private CircleButtonShadow btnRadiologia;
    private CircleButtonShadow btnNeurologia;
    
    
    public FiltrosEspecialidades(int radius, Clinica clinica) {
        super(radius);
        
        this.setPreferredSize(new Dimension(359, 225));
        this.setBackground(new Color(0xF0F3FA));
        this.setShadowSize(3);
        this.setShadowType(ShadowType.BOT);
        this.setLayout(null);
        
        ClinicaEspecialidadeDAO dao = new ClinicaEspecialidadeDAO();
        filtrosEspecialidades = dao.getListaEspecialidades(clinica).getListaEspecialidades();
        
        imgCheck = new ImageIcon(getClass().getResource("/imagens/Check.png"));
        
        configLabels();
        configBtns();
        configEspecialidades();
        
    }
    
    
    public FiltrosEspecialidades(int radius, TableClinicas tabelaClinicas) {
        super(radius);
        
        this.tabelaClinicas = tabelaClinicas;
        
        this.setPreferredSize(new Dimension(359, 225));
        this.setBackground(new Color(0xF0F3FA));
        this.setShadowSize(3);
        this.setShadowType(ShadowType.BOT);
        this.setLayout(null);
        
        filtrosEspecialidades = new String[8];
        imgCheck = new ImageIcon(getClass().getResource("/imagens/Check.png"));
        
        configLabels();
        configBtns();
        
    }
    
    
    public FiltrosEspecialidades(int radius) {
        super(radius);
        
        this.setPreferredSize(new Dimension(359, 225));
        this.setBackground(new Color(0xF0F3FA));
        this.setShadowSize(3);
        this.setShadowType(ShadowType.BOT);
        this.setLayout(null);
        
        filtrosEspecialidades = new String[8];
        imgCheck = new ImageIcon(getClass().getResource("/imagens/Check.png"));
        
        configLabels();
        configBtns();
        
    }

    
    public String[] getFiltrosEspecialidades() {
        return filtrosEspecialidades;
    }
    
    
    public final void configLabels() {
        
        // Texto "Clínica Geral"
        JLabel lblGeral = new JLabel("Clínica Geral");
        lblGeral.setBounds(62, 19, 109, 26);
        lblGeral.setForeground(new Color(0x395886));
        lblGeral.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblGeral);
        
        // Texto "Pediatria"
        JLabel lblPediatria = new JLabel("Pediatria");
        lblPediatria.setBounds(62, 69, 109, 26);
        lblPediatria.setForeground(new Color(0x395886));
        lblPediatria.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblPediatria);
        
        // Texto "Cardiologia"
        JLabel lblCardiologia = new JLabel("Cardiologia");
        lblCardiologia.setBounds(62, 119, 109, 26);
        lblCardiologia.setForeground(new Color(0x395886));
        lblCardiologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblCardiologia);
        
        // Texto "Oftalmologia"
        JLabel lblOftalmologia = new JLabel("Oftalmologia");
        lblOftalmologia.setBounds(62, 169, 109, 26);
        lblOftalmologia.setForeground(new Color(0x395886));
        lblOftalmologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblOftalmologia);
        
        // Texto "Odontologia"
        JLabel lblOdontologia = new JLabel("Odontologia");
        lblOdontologia.setBounds(236, 19, 109, 26);
        lblOdontologia.setForeground(new Color(0x395886));
        lblOdontologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblOdontologia);
        
        // Texto "Ortopedia"
        JLabel lblOrtopedia = new JLabel("Ortopedia");
        lblOrtopedia.setBounds(236, 69, 109, 26);
        lblOrtopedia.setForeground(new Color(0x395886));
        lblOrtopedia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblOrtopedia);
        
        // Texto "Radiologia"
        JLabel lblRadiologia = new JLabel("Radiologia");
        lblRadiologia.setBounds(236, 119, 109, 26);
        lblRadiologia.setForeground(new Color(0x395886));
        lblRadiologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblRadiologia);
        
        // Texto "Neurologia"
        JLabel lblNeurologia = new JLabel("Neurologia");
        lblNeurologia.setBounds(236, 169, 109, 26);
        lblNeurologia.setForeground(new Color(0x395886));
        lblNeurologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(lblNeurologia);
        
        // Desenha a Linha
        LinesComponent lines = new LinesComponent();
        lines.setBounds(179, 18, 5, 201);
        lines.addLine(0, 0, 0, 183, 2, new Color(0x395886));
        this.add(lines);
        
    }
    
    
    public final void configBtns() {
        
        // Botão "Clínica Geral"
        btnGeral = new CircleButtonShadow("");
        btnGeral.addActionListener(this);
        btnGeral.setBounds(21, 18, 30, 30);
        btnGeral.setBackground(new Color(0xD5DEEF));
        btnGeral.setForeground(new Color(0x395886));
        btnGeral.setShadowSize(2);
        btnGeral.setShadowType(ShadowType.BOT);
        btnGeral.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnGeral);
        
        // Botão "Pediatria"
        btnPediatria = new CircleButtonShadow("");
        btnPediatria.addActionListener(this);
        btnPediatria.setBounds(21, 69, 30, 30);
        btnPediatria.setBackground(new Color(0xD5DEEF));
        btnPediatria.setForeground(new Color(0x395886));
        btnPediatria.setShadowSize(2);
        btnPediatria.setShadowType(ShadowType.BOT);
        btnPediatria.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnPediatria);
        
        // Botão "Cardiologia"
        btnCardiologia = new CircleButtonShadow("");
        btnCardiologia.addActionListener(this);
        btnCardiologia.setBounds(21, 120, 30, 30);
        btnCardiologia.setBackground(new Color(0xD5DEEF));
        btnCardiologia.setForeground(new Color(0x395886));
        btnCardiologia.setShadowSize(2);
        btnCardiologia.setShadowType(ShadowType.BOT);
        btnCardiologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnCardiologia);
        
        // Botão "Oftalmologia"
        btnOftalmologia = new CircleButtonShadow("");
        btnOftalmologia.addActionListener(this);
        btnOftalmologia.setBounds(21, 171, 30, 30);
        btnOftalmologia.setBackground(new Color(0xD5DEEF));
        btnOftalmologia.setForeground(new Color(0x395886));
        btnOftalmologia.setShadowSize(2);
        btnOftalmologia.setShadowType(ShadowType.BOT);
        btnOftalmologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnOftalmologia);
        
        // Botão "Odontologia"
        btnOdontologia = new CircleButtonShadow("");
        btnOdontologia.addActionListener(this);
        btnOdontologia.setBounds(195, 18, 30, 30);
        btnOdontologia.setBackground(new Color(0xD5DEEF));
        btnOdontologia.setForeground(new Color(0x395886));
        btnOdontologia.setShadowSize(2);
        btnOdontologia.setShadowType(ShadowType.BOT);
        btnOdontologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnOdontologia);
        
        // Botão "Ortopedia"
        btnOrtopedia = new CircleButtonShadow("");
        btnOrtopedia.addActionListener(this);
        btnOrtopedia.setBounds(195, 69, 30, 30);
        btnOrtopedia.setBackground(new Color(0xD5DEEF));
        btnOrtopedia.setForeground(new Color(0x395886));
        btnOrtopedia.setShadowSize(2);
        btnOrtopedia.setShadowType(ShadowType.BOT);
        btnOrtopedia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnOrtopedia);
        
        // Botão "Radiologia"
        btnRadiologia = new CircleButtonShadow("");
        btnRadiologia.addActionListener(this);
        btnRadiologia.setBounds(195, 120, 30, 30);
        btnRadiologia.setBackground(new Color(0xD5DEEF));
        btnRadiologia.setForeground(new Color(0x395886));
        btnRadiologia.setShadowSize(2);
        btnRadiologia.setShadowType(ShadowType.BOT);
        btnRadiologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnRadiologia);
        
        // Botão "Neurologia"
        btnNeurologia = new CircleButtonShadow("");
        btnNeurologia.addActionListener(this);
        btnNeurologia.setBounds(195, 171, 30, 30);
        btnNeurologia.setBackground(new Color(0xD5DEEF));
        btnNeurologia.setForeground(new Color(0x395886));
        btnNeurologia.setShadowSize(2);
        btnNeurologia.setShadowType(ShadowType.BOT);
        btnNeurologia.setFont(new Font("Volkhov", Font.PLAIN, 15));
        this.add(btnNeurologia);
        
    }
    
    
    public final void configEspecialidades() {
        
        // Desativa os botões
        btnGeral.setEnabled(false);
        btnPediatria.setEnabled(false);
        btnCardiologia.setEnabled(false);
        btnOftalmologia.setEnabled(false);
        btnOdontologia.setEnabled(false);
        btnOrtopedia.setEnabled(false);
        btnRadiologia.setEnabled(false);
        btnNeurologia.setEnabled(false);
        
        // Adiciona o ícone no botão que está presente na lista de filtros
        configIconBtn(btnGeral, 0);
        configIconBtn(btnPediatria, 1);
        configIconBtn(btnCardiologia, 2);
        configIconBtn(btnOftalmologia, 3);
        configIconBtn(btnOdontologia, 4);
        configIconBtn(btnOrtopedia, 5);
        configIconBtn(btnRadiologia, 6);
        configIconBtn(btnNeurologia, 7);
        
    }
    
    
    public final void configEditable() {
        
        // Ativa os botões
        btnGeral.setEnabled(true);
        btnPediatria.setEnabled(true);
        btnCardiologia.setEnabled(true);
        btnOftalmologia.setEnabled(true);
        btnOdontologia.setEnabled(true);
        btnOrtopedia.setEnabled(true);
        btnRadiologia.setEnabled(true);
        btnNeurologia.setEnabled(true);
        
    }
    
    
    public void configIconBtn(JButton btn, int posEspecialidade) {
        
        String [] ordemBtns = {"Clínica Geral", "Pediatria", "Cardiologia", "Oftalmologia", "Odontologia", "Ortopedia", "Radiologia", "Neurologia"};
        
        for(String text : filtrosEspecialidades) {
            if(text.equals(ordemBtns[posEspecialidade])) {
                if(btn.getIcon() == null) {
                    btn.setIcon(imgCheck);
                    btn.setDisabledIcon(imgCheck);
                }
            }
        }
        
    }
    
    
    public void filtrarPesquisa(JButton btn, String [] filtrosEspecialidades, int posEspecialidade) {
        
        if(tabelaClinicas != null) {
            if(btn.getIcon() == null) {
                btn.setIcon(imgCheck);
                tabelaClinicas.atualizarTabela(filtrosEspecialidades);
            } else {
                btn.setIcon(null);

                filtrosEspecialidades[posEspecialidade] = null;

                boolean filtroVazio = true;
                for(String especialidade : filtrosEspecialidades) {
                    if(especialidade != null) {
                        filtroVazio = false;
                    }
                }

                if(filtroVazio == true) {
                    tabelaClinicas.atualizarTabela(null);
                } else {
                    tabelaClinicas.atualizarTabela(filtrosEspecialidades);
                }

            }
        } else {
            
            if(btn.getIcon() == null) {
                btn.setIcon(imgCheck);
            } else {
                btn.setIcon(null);
                filtrosEspecialidades[posEspecialidade] = null;
            }
            
        }
            
        repaint();
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnGeral) {
            filtrosEspecialidades[0] = "Clínica Geral";
            filtrarPesquisa(btnGeral, filtrosEspecialidades, 0);
            
        } else if(e.getSource() == btnPediatria) {
            filtrosEspecialidades[1] = "Pediatria";
            filtrarPesquisa(btnPediatria, filtrosEspecialidades, 1);
            
        } else if(e.getSource() == btnCardiologia) {
            filtrosEspecialidades[2] = "Cardiologia";
            filtrarPesquisa(btnCardiologia, filtrosEspecialidades, 2);
            
        } else if(e.getSource() == btnOftalmologia) {
            filtrosEspecialidades[3] = "Oftalmologia";
            filtrarPesquisa(btnOftalmologia, filtrosEspecialidades, 3);
            
        } else if(e.getSource() == btnOdontologia) {
            filtrosEspecialidades[4] = "Odontologia";
            filtrarPesquisa(btnOdontologia, filtrosEspecialidades, 4);
            
        } else if(e.getSource() == btnOrtopedia) {
            filtrosEspecialidades[5] = "Ortopedia";
            filtrarPesquisa(btnOrtopedia, filtrosEspecialidades, 5);
            
        } else if(e.getSource() == btnRadiologia) {
            filtrosEspecialidades[6] = "Radiologia";
            filtrarPesquisa(btnRadiologia, filtrosEspecialidades, 6);
            
        } else if(e.getSource() == btnNeurologia) {
            filtrosEspecialidades[7] = "Neurologia";
            filtrarPesquisa(btnNeurologia, filtrosEspecialidades, 7);
        }
        
    }
    
    
}
