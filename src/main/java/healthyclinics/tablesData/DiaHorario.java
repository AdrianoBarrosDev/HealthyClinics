
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="dia_horario")
public class DiaHorario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn
    private Dia dia;
    
    @ManyToOne
    @JoinColumn
    private Horario horario;
    
    
    public DiaHorario() {
        
    }
    
    
    public DiaHorario(Dia dia, Horario horario) {
        this.dia = dia;
        this.horario = horario;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    
}
