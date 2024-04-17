
package healthyclinics.tablesData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="consulta")
public class Consulta {
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="paciente_id")
    private ContaCliente paciente;
    
    @ManyToOne
    @JoinColumn(name="clinica_id")
    private Clinica clinica;
    
    @ManyToOne
    @JoinColumn(name="medico_id")
    private ContaProfissional medico;
    
    @ManyToOne
    @JoinColumn(name="dia_horario_id")
    private DiaHorario diaHorario;
    
    @Column(name="relatorio")
    private String relatorio;
    
    @Column(name="status")
    private String status;
    
    
    public Consulta() {
        
    }

    
    public Consulta(ContaCliente paciente, Clinica clinica, ContaProfissional medico, DiaHorario diaHorario) {
        this.paciente = paciente;
        this.clinica = clinica;
        this.medico = medico;
        this.diaHorario = diaHorario;
        this.status = "Em andamento";
    }
    
    
    public Consulta(ContaCliente paciente, Clinica clinica, ContaProfissional medico, DiaHorario diaHorario, String relatorio, String status) {
        this.paciente = paciente;
        this.clinica = clinica;
        this.medico = medico;
        this.diaHorario = diaHorario;
        this.relatorio = relatorio;
        this.status = status;
    }
    
    
    public Consulta(ContaCliente paciente, Clinica clinica, ContaProfissional medico, DiaHorario diaHorario, String status) {
        this.paciente = paciente;
        this.clinica = clinica;
        this.medico = medico;
        this.diaHorario = diaHorario;
        this.status = status;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContaCliente getPaciente() {
        return paciente;
    }

    public void setPaciente(ContaCliente paciente) {
        this.paciente = paciente;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public ContaProfissional getMedico() {
        return medico;
    }

    public void setMedico(ContaProfissional medico) {
        this.medico = medico;
    }

    public DiaHorario getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(DiaHorario diaHorario) {
        this.diaHorario = diaHorario;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
