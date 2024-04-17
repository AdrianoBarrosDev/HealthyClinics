
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="horario")
public class Horario {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private String horario;

    
    public Horario() {
        
    }
    
    
    public Horario(String horario) {
        this.horario = horario;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
}
