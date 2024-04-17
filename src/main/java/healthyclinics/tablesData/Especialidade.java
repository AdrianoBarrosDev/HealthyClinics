
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private String descricao;
    
    
    public Especialidade() {
        
    }
    
    
    public Especialidade(String descricao) {
        this.descricao = descricao;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
