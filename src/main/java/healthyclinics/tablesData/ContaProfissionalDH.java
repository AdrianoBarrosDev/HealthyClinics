
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conta_profissional_dia_horario")
public class ContaProfissionalDH {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="conta_profissional_id")
    private ContaProfissional contaProfissional;
    
    @ManyToOne
    @JoinColumn(name="dia_horario_id")
    private DiaHorario diaHorario;
    
    
    public ContaProfissionalDH() {
        
    }
    
    
    public ContaProfissionalDH(ContaProfissional contaProfissional, DiaHorario diaHorario) {
        this.contaProfissional = contaProfissional;
        this.diaHorario = diaHorario;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContaProfissional getContaProfissional() {
        return contaProfissional;
    }

    public void setContaProfissional(ContaProfissional contaProfissional) {
        this.contaProfissional = contaProfissional;
    }

    public DiaHorario getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(DiaHorario diaHorario) {
        this.diaHorario = diaHorario;
    }
    
}
