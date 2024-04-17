
package healthyclinics.tablesData;

import healthyclinics.ferramentas.ConvertBlob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.File;
import java.sql.Blob;
import java.sql.Date;


@Entity
@Table(name="conta")
public class Conta {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    private String nome;
    
    private String cpf;
    
    @Column(name="data_nascimento")
    private Date dataNascimento;
    
    private String telefone;
    
    private String genero;
    
    private String endereco;
    
    private String senha;
    
    private Blob img;
    
    @ManyToOne
    @JoinColumn(name="tipo_conta_id")
    private TipoConta tipoConta;
    
    public Conta() {
        
    }
    
    
    public Conta(String nome, String cpf, Date dataNascimento, String telefone, String endereco, String senha, TipoConta tipoConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
        this.tipoConta = tipoConta;
        
        ConvertBlob convertBlob = new ConvertBlob();
        this.img = convertBlob.converterImagem(new File(getClass().getResource("/imagens/ImgContaDefault.png").getPath()));
        
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
    
    
}
