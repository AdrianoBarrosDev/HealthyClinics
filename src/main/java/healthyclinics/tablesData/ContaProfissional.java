
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conta_profissional")
public class ContaProfissional {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private String crm;
    
    @ManyToOne
    @JoinColumn(name="clinica_id")
    private Clinica clinica;
    
    @ManyToOne
    @JoinColumn(name="conta_id")
    private Conta conta;

    
    public ContaProfissional() {
        
    }
    
    
    public ContaProfissional(String crm, Conta conta) {
        this.crm = crm;
        this.conta = conta;
    }
    
    
    public ContaProfissional(String crm, Clinica clinica, Conta conta) {
        this.crm = crm;
        this.clinica = clinica;
        this.conta = conta;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    
    @Override
    public String toString() {
        return conta.getNome();
    }
    
    
}
