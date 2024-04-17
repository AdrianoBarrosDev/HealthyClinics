
package healthyclinics.ferramentas;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.dados.ListaEspecialidade;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.db.ClinicaEspecialidadeDAO;
import healthyclinics.tablesData.Clinica;
import healthyclinics.layouts.CustomTableCellRenderer;
import healthyclinics.layouts.LayoutTableHeader;
import healthyclinics.scrollbar.ScrollBarCustom;
import healthyclinics.telas.PerfilClinica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableClinicas extends JPanel {

    private final JComponent tela;
    private final ConfigFrame configFrame;
    private JTable tabelaClinicas;
    private DefaultTableModel model;
    
    
    public TableClinicas(ConfigFrame configFrame, JComponent tela) {
        this.configFrame = configFrame;
        this.tela = tela;
        setLayout(null);
    }

    
    public JTable getTabelaClinicas() {
        return tabelaClinicas;
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
        tabelaClinicas = new JTable(model);
        tabelaClinicas.setBackground(new Color(0xF0F3FA));
        tabelaClinicas.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabelaClinicas.setRowHeight(23);
        tabelaClinicas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        tabelaClinicas.setShowGrid(false);
        tabelaClinicas.setIntercellSpacing(new Dimension(-1, -1));
        tabelaClinicas.setBorder(BorderFactory.createLineBorder(new Color(0x395886)));
        tabelaClinicas.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1) { // Se houver um double click e for em alguma linha da tabela
                    
                    String cnpjClinica = table.getValueAt(table.getSelectedRow(), 1).toString();
                    
                    ClinicaDAO dao = new ClinicaDAO();
                    List<Clinica> listaClinicas = dao.getListaClinicas();
                    for(Clinica c : listaClinicas) {
                        if(c.getCnpj().equals(cnpjClinica)) { // Se o CNPJ da clínica da lista for igual ao CNPJ da tabela
                            
                            // Abre o perfil da clínica
                            configFrame.getMainFrame().remove(tela);
                            PerfilClinica perfilClinica = new PerfilClinica(configFrame, c);
                            perfilClinica.configPerfilClinica();
                            
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
        model.addColumn("CNPJ");
        model.addColumn("Especialidade(s)");
        model.addColumn("Endereço");
        model.addColumn("CRM Responsável");
        
        // Define o tamanho de cada coluna
        tabelaClinicas.getColumnModel().getColumn(0).setPreferredWidth(212);
        tabelaClinicas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaClinicas.getColumnModel().getColumn(2).setPreferredWidth(295);
        tabelaClinicas.getColumnModel().getColumn(3).setPreferredWidth(247);
        tabelaClinicas.getColumnModel().getColumn(4).setPreferredWidth(160);
        
        // Modifica o cabeçalho da tabela
        changeColorsHeader(new Color(0xB1C9EF));
        
        // Atualiza as informações da tabela
        atualizarTabela();
        
        JScrollPane scrollPane = new JScrollPane(tabelaClinicas);
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

        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> listaClinicas = dao.getListaClinicas();

        ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
        for(Clinica c : listaClinicas) {

            ListaEspecialidade listaEspecialidade = daoCE.getListaEspecialidades(c);
            Object[] obj = new Object[] {
                c.getNome(),
                c.getCnpj(),
                listaEspecialidade.toString(),
                c.getEndereco(),
                c.getResponsavel().getCrm()
            };

            model.addRow(obj);

        }
        
    }
    
    
    public void atualizarTabela(String [] filtroEspecialidade) {
        
        limparTabela();

        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> listaClinicas = dao.getListaClinicas();
        
        ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
        if(filtroEspecialidade != null) {
            for(Clinica c : listaClinicas) {
                
                ListaEspecialidade listaEspecialidade = daoCE.getListaEspecialidades(c);

                boolean clinicaFiltrada = true;
                for(String especialidade : filtroEspecialidade) {
                    if (especialidade != null) {
                        if (!listaEspecialidade.toString().toLowerCase().contains(especialidade.toLowerCase())) {
                            clinicaFiltrada = false;
                        }
                        
                    }
                    
                }
                
                if(clinicaFiltrada == true) {
                        Object[] obj = new Object[] {
                        c.getNome(),
                        c.getCnpj(),
                        listaEspecialidade.toString(),
                        c.getEndereco(),
                        c.getResponsavel().getCrm()
                    };

                    model.addRow(obj);
                }

            }
            
        } else {
            atualizarTabela();
        }
        
        
    }
    
    
    
    public void pesquisarClinica(String nome, String [] filtroEspecialidade) {
        
        limparTabela();

        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> listaClinicas = dao.getListaClinicas();
        
        ClinicaEspecialidadeDAO daoCE = new ClinicaEspecialidadeDAO();
        // Se o nome não for nulo ou não estiver vazio
        if(!(nome.isBlank() || nome.isEmpty())) {
            
            // Se o filtro de especialidades não for nulo
            if(filtroEspecialidade != null) {
                for(Clinica c : listaClinicas) {

                    boolean clinicaFiltrada = false;
                    boolean filtroVazio = true;
                    
                    ListaEspecialidade listaEspecialidade = daoCE.getListaEspecialidades(c);

                    // Se a clínica da lista contém o nome digitado pelo usuário
                    if(c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                        for(String especialidade : filtroEspecialidade) {
                            
                            // Se a especialidade do vetor não for nula ou nenhuma
                            if (especialidade != null) {
                                filtroVazio = false;
                                
                                // Se as especialidades da clínica da lista contém as especialidades desejadas pelo usuário
                                if (listaEspecialidade.toString().toLowerCase().contains(especialidade.toLowerCase())) {
                                    clinicaFiltrada = true;
                                }

                            }
                            
                        }
                    }

                    // Se o filtro não estiver vazio
                    if(filtroVazio == false) {
                        if(clinicaFiltrada == true) { // Se a clínica contém a especialidade
                                Object[] obj = new Object[] {
                                c.getNome(),
                                c.getCnpj(),
                                listaEspecialidade.toString(),
                                c.getEndereco(),
                                c.getResponsavel().getCrm()
                            };

                            model.addRow(obj);
                        }
                    } else { // Se o filtro estiver vazio
                            if(c.getNome().toLowerCase().contains(nome.toLowerCase())) {

                            Object[] obj = new Object[] {
                                c.getNome(),
                                c.getCnpj(),
                                listaEspecialidade.toString(),
                                c.getEndereco(),
                                c.getResponsavel().getCrm()
                            };

                            model.addRow(obj);

                        }
                    }
                        

                }

            } else { // Se o filtro de especialidades for nulo

                for(Clinica c : listaClinicas) { 
                    
                    ListaEspecialidade listaEspecialidade = daoCE.getListaEspecialidades(c);
                    if(c.getNome().toLowerCase().contains(nome.toLowerCase())) {

                        Object[] obj = new Object[] {
                            c.getNome(),
                            c.getCnpj(),
                            listaEspecialidade.toString(),
                            c.getEndereco(),
                            c.getResponsavel().getCrm()
                        };

                        model.addRow(obj);

                    }
                }

            }
            
        } else { // Se o nome for nulo ou estiver vazio
            
            boolean filtroVazio = true;
            for(String especialidade : filtroEspecialidade) {
                if(especialidade != null) {
                    filtroVazio = false;
                }
            }
            
            // Se o filtro estiver vazio
            if(filtroVazio == true) {
                atualizarTabela(); // Atualiza a tabela inteira
                
            } else { // Se o filtro não estiver vazio
                
                // Atualiza a tabela com todas as clínicas que contém as especialidades do filtro
                atualizarTabela(filtroEspecialidade); 
            
            }
                
        }
        
    }
    
    
    
    public void limparTabela() {
        model.setNumRows(0);
    }
    
    
    private void changeColorsHeader(Color colorDefault) {
        
        for(int i = 0; i < tabelaClinicas.getModel().getColumnCount(); i++) {
            
            // Customização do cabeçalho das colunas
            if(i < tabelaClinicas.getModel().getColumnCount()) {
                DefaultTableCellRenderer headerRendererDefault = new LayoutTableHeader();
                headerRendererDefault.setBackground(colorDefault);
                tabelaClinicas.getColumnModel().getColumn(i).setHeaderRenderer(headerRendererDefault);
            }
        }
        
    }
    
    
}
