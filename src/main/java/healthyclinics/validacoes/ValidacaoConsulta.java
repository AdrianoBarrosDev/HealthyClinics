
package healthyclinics.validacoes;

import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.db.DiaDAO;
import healthyclinics.db.DiaHorarioDAO;
import healthyclinics.db.HorarioDAO;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.erros.ErrosMarcarConsulta;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.Consulta;
import healthyclinics.tablesData.Dia;
import healthyclinics.tablesData.DiaHorario;
import healthyclinics.tablesData.Horario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

public class ValidacaoConsulta {
    
    private final JPanel pnlInformacoes;
    private ContaCliente contaUsuario;
    private Clinica clinica;
    private Dia dataConsulta;
    private Horario horario;
    private ContaProfissional medico;
    private ErrosMarcarConsulta erros;
    private Consulta consulta;
    private DiaHorario diaHorario;
    
    public ValidacaoConsulta(JPanel pnlInformacoes) {
        this.pnlInformacoes = pnlInformacoes;
        this.erros = new ErrosMarcarConsulta(pnlInformacoes);
    }

    
    public ContaCliente getContaUsuario() {
        return contaUsuario;
    }

    public void setContaUsuario(ContaCliente contaUsuario) {
        this.contaUsuario = contaUsuario;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Dia getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Dia dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public ContaProfissional getMedico() {
        return medico;
    }

    public void setMedico(ContaProfissional medico) {
        this.medico = medico;
    }

    public ErrosMarcarConsulta getErros() {
        return erros;
    }

    public void setErros(ErrosMarcarConsulta erros) {
        this.erros = erros;
    }

    public DiaHorario getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(DiaHorario diaHorario) {
        this.diaHorario = diaHorario;
    }
    
    
    public boolean validarConsulta(ContaCliente contaUsuario, Clinica clinica, String dataConsulta, String horario, String nomeMedico) {
        
        erros.removerErros();
        
        boolean consultaValida = true;
        
        // Valida a consulta do usuário
        if(!validarUsuario(contaUsuario)) {
            consultaValida = false;
        }
        // Valida a clínica
        if(!validarClinica(clinica)) {
            consultaValida = false;
        }
        // Valida a data digitada
        if(!validarData(dataConsulta)) {
            consultaValida = false;
            erros.mostrarErroData();
        }
        // Valida o horário
        if(!validarHorario(horario)) {
            consultaValida = false;
            erros.mostrarErroHorario();
        }
        // Valida o médico
        if(!validarMedico(nomeMedico)) {
            consultaValida = false;
            erros.mostrarErroMedico();
        }
        
        if(consultaValida == false) {
            erros.mostrarOrientacao();
        } else {
            DiaHorarioDAO diaHorarioDAO = new DiaHorarioDAO();
            diaHorarioDAO.salvar(new DiaHorario(this.dataConsulta, this.horario));
            diaHorario = diaHorarioDAO.get(this.dataConsulta, this.horario);
        }
        
        pnlInformacoes.repaint();
        return consultaValida;
    }
    
    
    public boolean validarUsuario(Object objeto) {
        contaUsuario = (ContaCliente) objeto;
        return objeto != null;
    }
    
    public boolean validarClinica(Object objeto) {
        clinica = (Clinica) objeto;
        return objeto != null;
    }
    
    
    public boolean validarData(String data) {
        
        DiaDAO diaDAO = new DiaDAO();
        boolean dataValida = false;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConsulta = null;
        try {
            dataConsulta = df.parse(data);
            Date dataAtual = new Date();
            if(dataConsulta.compareTo(dataAtual) > 0) {
                dataValida = true;
            } else if(dataConsulta.toString().equals(df.parse(df.format(dataAtual)).toString())) {
                dataValida = true;
            } else {
                dataValida = false;
            }
            
            if(dataValida == true) {
                df = new SimpleDateFormat("yyyy-MM-dd");
                this.dataConsulta = diaDAO.get(java.sql.Date.valueOf(df.format(dataConsulta)));
                return true;
            }
            return false;
            
        } catch(Exception ex) {
            if(this.dataConsulta == null) {
                if(dataConsulta != null) {
                    diaDAO.salvar(new Dia(java.sql.Date.valueOf(df.format(dataConsulta))));
                    return true;
                }
            }
            return false;
        }
        
    }
    
    
    public boolean validarHorario(String strHorario) {
        
        try {
            HorarioDAO dao = new HorarioDAO();
            horario = dao.get(strHorario);
            return true;
        } catch(Exception ex) {
            return false;
        }
        
    }
    
    
    public boolean validarMedico(String nomeMedico) {
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        List<ContaProfissional> listaMedicos = dao.getListaMedicos(clinica);
        
        for(ContaProfissional medico : listaMedicos) {
            if(medico.getConta().getNome().equals(nomeMedico)) {
                this.medico = medico;
                return true;
            }
        }
        return false;
        
    }
    
    
    
    public Consulta retornarConsulta(Consulta c) {
        
        try {
            
            // Crie e retorna uma consulta
            DiaHorario diaHorario = new DiaHorario(dataConsulta, horario);
            
            consulta = c;
            consulta.setPaciente(contaUsuario);
            consulta.setClinica(clinica);
            consulta.setMedico(medico);
            consulta.setDiaHorario(diaHorario);
            consulta.setStatus("Em andamento");
            
            return consulta;
            
        } catch(Exception ex) {

        }
        return null;
        
    }
    
    
}
