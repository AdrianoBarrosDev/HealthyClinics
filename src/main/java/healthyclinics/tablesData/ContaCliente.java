
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conta_cliente")
public class ContaCliente {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private String convenio;
    
    @ManyToOne
    @JoinColumn(name="conta_id")
    private Conta conta;

    
    public ContaCliente() {
        
    }
    
    
    public ContaCliente(String convenio, Conta conta) {
        this.convenio = convenio;
        this.conta = conta;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
}
