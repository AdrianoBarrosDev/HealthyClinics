
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="dia")
public class Dia {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private Date dia;

    
    public Dia() {
        
    }
    
    
    public Dia(Date dia) {
        this.dia = dia;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    
    
    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dia);
    }
    
    
}
