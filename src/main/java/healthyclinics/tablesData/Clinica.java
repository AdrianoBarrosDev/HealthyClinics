
package healthyclinics.tablesData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name="clinica")
public class Clinica {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String descricao;
    private float valor_minimo_consulta;
    private Blob img;
    
    @ManyToOne
    @JoinColumn(name="responsavel_id")
    private ContaProfissional responsavel;

    
    public Clinica() {
        
    }
    
    
    public Clinica(String nome, String cnpj, String endereco, String descricao, float valor_minimo_consulta, ContaProfissional responsavel) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.descricao = descricao;
        this.valor_minimo_consulta = valor_minimo_consulta;
        this.responsavel = responsavel;
    }
    
    public Clinica(String nome, String cnpj, String endereco, String descricao, float valor_minimo_consulta, Blob img, ContaProfissional responsavel) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.descricao = descricao;
        this.valor_minimo_consulta = valor_minimo_consulta;
        this.img = img;
        this.responsavel = responsavel;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor_minimo_consulta() {
        return valor_minimo_consulta;
    }

    public void setValor_minimo_consulta(float valor_minimo_consulta) {
        this.valor_minimo_consulta = valor_minimo_consulta;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public ContaProfissional getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ContaProfissional responsavel) {
        this.responsavel = responsavel;
    }
    
    
}
