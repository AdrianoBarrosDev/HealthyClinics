
package healthyclinics.ferramentas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ConsultaDAO;
import healthyclinics.db.ContaClienteDAO;
import healthyclinics.layouts.CustomTableCellRenderer;
import healthyclinics.layouts.LayoutTableHeader;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.tablesData.Consulta;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.telas.TelaDadosConsulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableConsultasCliente extends JPanel {

    private final ConfigFrame configFrame;
    private final JScrollPane tela;
    private JTable tabelaConsultas;
    private DefaultTableModel model;
    private ContaCliente conta;
    private TelaDadosConsulta telaConsulta;
    
    
    public TableConsultasCliente(ConfigFrame configFrame, JScrollPane tela, ContaCliente conta) {
        this.configFrame = configFrame;
        this.tela = tela;
        this.conta = conta;
        setLayout(null);
    }

    
    public JTable getTabelaConsultas() {
        return tabelaConsultas;
    }
    
    
    public void gerarTabela() {
        
        // Cria o modelo da tabela
        model = new DefaultTableModel() {
           
            // Deixa a tabela não editável
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        
        // Cria a tabela
        tabelaConsultas = new JTable(model);
        tabelaConsultas.setBackground(new Color(0xF0F3FA));
        tabelaConsultas.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabelaConsultas.setRowHeight(23);
        tabelaConsultas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        tabelaConsultas.setShowGrid(false);
        tabelaConsultas.setIntercellSpacing(new Dimension(-1, -1));
        tabelaConsultas.setBorder(BorderFactory.createLineBorder(new Color(0x395886)));
        tabelaConsultas.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1) { // Se houver um double click e for em alguma linha da tabela
                    
                    int idxClinicaSelecionada = table.getSelectedRow();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    DateTimeFormatter dfHora = DateTimeFormatter.ofPattern("HH:mm");
                    
                    // Busca a conta cliente no banco
                    ContaClienteDAO daoContaCliente = new ContaClienteDAO();
                    ContaCliente contaCliente = daoContaCliente.getConta(configFrame.getManager().getTelas().getTelaLogin().getContaLogada());
                    
                    // Retorna a lista de consultas da conta do cliente no banco
                    ConsultaDAO dao = new ConsultaDAO();
                    List<Consulta> listaConsultas = dao.getListaConsultas(contaCliente);
                    
                    for(Consulta c : listaConsultas) {
                        
                        if(c.getClinica().getNome().equals(table.getValueAt(idxClinicaSelecionada, 0))) {
                            if(c.getMedico().getConta().getNome().equals(table.getValueAt(idxClinicaSelecionada, 1))) {
                                if(df.format(c.getDiaHorario().getDia().getDia()).equals(table.getValueAt(idxClinicaSelecionada, 2))) {
                                    if(c.getDiaHorario().getHorario().getHorario().equals((String) table.getValueAt(idxClinicaSelecionada, 3))) {
                                        if(c.getStatus().equals(table.getValueAt(idxClinicaSelecionada, 6))) {
                                            
                                            // Abre os detalhes da consulta
                                            configFrame.getMainFrame().remove(tela);
                                            telaConsulta = new TelaDadosConsulta(configFrame, c);
                                            telaConsulta.configDadosConsulta();
                                            
                                        }
                                    }
                                }
                            }
                        }
                    }   
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
        // Adiciona as colunas
        model.addColumn("Nome da Clínica");
        model.addColumn("Nome do Médico");
        model.addColumn("Data da Consulta");
        model.addColumn("Horário");
        model.addColumn("Convênio");
        model.addColumn("Valor");
        model.addColumn("Status");
        
        // Define o tamanho de cada coluna
        tabelaConsultas.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth() / 5);
        tabelaConsultas.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth() / 5);
        tabelaConsultas.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth() / 7);
        tabelaConsultas.getColumnModel().getColumn(3).setPreferredWidth(this.getWidth() / 7);
        tabelaConsultas.getColumnModel().getColumn(4).setPreferredWidth(this.getWidth() / 6);
        tabelaConsultas.getColumnModel().getColumn(5).setPreferredWidth(this.getWidth() / 7);
        tabelaConsultas.getColumnModel().getColumn(6).setPreferredWidth(this.getWidth() / 7);
        
        // Modifica o cabeçalho da tabela
        changeColorsHeader(new Color(0xB1C9EF));
        
        // Atualiza as informações da tabela
        atualizarTabela();
        
        JScrollPane scrollPane = new JScrollPane(tabelaConsultas);
        scrollPane.setBounds(0, 0, this.getWidth(), this.getHeight());
        scrollPane.getViewport().setBackground(new Color(0xF0F3FA));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setOpaque(true);
        this.add(scrollPane);
        
        
    }
    
    
    public void atualizarTabela() {
        
        limparTabela();

        ConsultaDAO dao = new ConsultaDAO();
        List<Consulta> listaConsultas = dao.getListaConsultas(conta);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dfHora = DateTimeFormatter.ofPattern("HH:mm");
        DecimalFormat formatter = new DecimalFormat("0.00");
        for(Consulta c : listaConsultas) {

            Object[] obj = new Object[] {
                c.getClinica().getNome(),
                c.getMedico().getConta().getNome(),
                df.format(c.getDiaHorario().getDia().getDia()),
                c.getDiaHorario().getHorario().getHorario(),
                c.getPaciente().getConvenio(),
                "R$" + formatter.format(c.getClinica().getValor_minimo_consulta()),
                c.getStatus()
            };

            model.addRow(obj);

        }
        
    }
    
    
    public void atualizarTabelaData() {
        
        limparTabela();
        
        ConsultaDAO dao = new ConsultaDAO();
        List<Consulta> listaConsultas = dao.getListaConsultas(conta);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dfHora = DateTimeFormatter.ofPattern("HH:mm");
        DecimalFormat formatter = new DecimalFormat("0.00");
        Date atualDate = Calendar.getInstance().getTime(); // Data do sistema
        LocalTime atualHora = LocalTime.now(); // Data do sistema
        for(Consulta c : listaConsultas) {

            // Se o dia da consulta é igual ao dia atual no sistema da máquina
            if(df.format(atualDate).compareTo(df.format(c.getDiaHorario().getDia().getDia())) == 0) {
                
                // Se o horário atual comparado com o horário da consulta for menor ou igual
                if(atualHora.compareTo(LocalTime.parse(c.getDiaHorario().getHorario().getHorario())) <= 0) {
                    Object[] obj = new Object[] {
                        c.getClinica().getNome(),
                        c.getMedico().getConta().getNome(),
                        df.format(c.getDiaHorario().getDia().getDia()),
                        c.getDiaHorario().getHorario().getHorario(),
                        c.getPaciente().getConvenio(),
                        "R$" + formatter.format(c.getClinica().getValor_minimo_consulta()),
                        c.getStatus()
                    };
                    model.addRow(obj); 
                }
                
            } else if(df.format(atualDate).compareTo(df.format(c.getDiaHorario().getDia().getDia())) < 0) { // Se o dia ainda não chegou
                Object[] obj = new Object[] {
                    c.getClinica().getNome(),
                    c.getMedico().getConta().getNome(),
                    df.format(c.getDiaHorario().getDia().getDia()),
                    c.getDiaHorario().getHorario().getHorario(),
                    c.getPaciente().getConvenio(),
                    "R$" + formatter.format(c.getClinica().getValor_minimo_consulta()),
                    c.getStatus()
                };
                model.addRow(obj);
            }

        }
        
    }
    
    
    public void cancelarConsulta(int row) {
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dfHora = DateTimeFormatter.ofPattern("HH:mm");
        
        ConsultaDAO dao = new ConsultaDAO();
        List<Consulta> listaConsultas = dao.getListaConsultas(conta);
        
        for(Consulta c : listaConsultas) {
                        
            if(c.getClinica().getNome().equals(tabelaConsultas.getValueAt(row, 0))) {
                if(c.getMedico().getConta().getNome().equals(tabelaConsultas.getValueAt(row, 1))) {
                    if(df.format(c.getDiaHorario().getDia().getDia()).equals(tabelaConsultas.getValueAt(row, 2))) {
                        if(c.getDiaHorario().getHorario().getHorario().equals(tabelaConsultas.getValueAt(row, 3))) {
                            if(c.getStatus().equals(tabelaConsultas.getValueAt(row, 6))) {

                                // Se a consulta for a mesma da lista, marca a consulta como cancelada
                                c.setStatus("Cancelada");

                            }
                        }
                    }
                }
            }
        }
        
        atualizarTabelaData();
        
    }
    
    
    public void limparTabela() {
        model.setNumRows(0);
    }
    
    
    private void changeColorsHeader(Color colorDefault) {
        
        for(int i = 0; i < tabelaConsultas.getModel().getColumnCount(); i++) {
            
            // Customização do cabeçalho das colunas
            if(i < tabelaConsultas.getModel().getColumnCount()) {
                DefaultTableCellRenderer headerRendererDefault = new LayoutTableHeader();
                headerRendererDefault.setBackground(colorDefault);
                tabelaConsultas.getColumnModel().getColumn(i).setHeaderRenderer(headerRendererDefault);
            }
        }
        
    }
    
    
}
