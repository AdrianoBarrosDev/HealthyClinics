
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="clinica_especialidade")
public class ClinicaEspecialidade {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn
    private Clinica clinica;
    
    @ManyToOne
    @JoinColumn
    private Especialidade especialidade;

    
    public ClinicaEspecialidade() {
        
    }
    
    
    public ClinicaEspecialidade(Clinica clinica, Especialidade especialidade) {
        this.clinica = clinica;
        this.especialidade = especialidade;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
    
}
