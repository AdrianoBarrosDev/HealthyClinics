
package healthyclinics.validacoes;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.dados.ListaEspecialidade;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.erros.ErrosGerenciarClinica;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.ContaProfissional;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLayeredPane;

public class ValidacaoClinica {
    
    private final ConfigFrame configFrame;
    private final JLayeredPane layeredPane;
    private final ErrosGerenciarClinica erros;
    private Clinica clinica;
    private String nome;
    private String cnpj;
    private String endereco;
    private String descricao;
    private String[] listaEspecialidades;
    private String valorConsultas;
    private ContaProfissional responsavel;
    
    public ValidacaoClinica(ConfigFrame configFrame, JLayeredPane layeredPane) {
        this.configFrame = configFrame;
        this.layeredPane = layeredPane;
        this.erros = new ErrosGerenciarClinica(layeredPane);
    }

    public ErrosGerenciarClinica getErros() {
        return erros;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getValorConsultas() {
        return valorConsultas;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
    
    
    public boolean validarClinica(String nome, String cnpj, String endereco, ContaProfissional responsavel, String descricao, String[] listaEspecialidades, String valorConsultas) {
        
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.descricao = descricao;
        this.listaEspecialidades = listaEspecialidades;
        this.valorConsultas = valorConsultas;
        
        erros.removerErros(); // Remove os erros da tela

        
        boolean usuarioValido = true;
        if(validarTexto(nome) == false) { // Se o nome for inválido
            usuarioValido = false;
            erros.mostrarErroNome();
        }
        if(validarCnpj(cnpj) == false) { // Se o cnpj for inválido
            usuarioValido = false;
            erros.mostrarErroCnpj();
        }
        if(validarTexto(endereco) == false) { // Se o endereço for inválido
            usuarioValido = false;
            erros.mostrarErroEndereco();
        }
        if(validarTexto(descricao) == false) { // Se o valorConsultas for inválido
            usuarioValido = false;
            erros.mostrarErroDescricao();
        }
        if(validarValor(valorConsultas) == false) {
            this.valorConsultas = "";
            usuarioValido = false;
            erros.mostrarErroValor();
        }
        if(usuarioValido == true) {
            return buscarCnpj() == true;
        }
        
        layeredPane.repaint();
        return false;
        
    }
    
    
    public Clinica retornarNovosDados() {
        
        // Validação das especialidades
        ListaEspecialidade especialidades = new ListaEspecialidade();
        for(int i = 0; i < listaEspecialidades.length; i++) {
            if(listaEspecialidades[i] == null) {
                listaEspecialidades[i] = "";
            }
        }
        especialidades.setListaEspecialidades(listaEspecialidades);
        
        // Crie e retorna uma clinica do tipo "Cliente"
        try {
            
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            clinica = new Clinica(
                    nome,
                    cnpj,
                    endereco,
                    descricao,
                    retornarValor(),
                    responsavel
            );
            return clinica;
            
        } catch(Exception ex) {

        }
        return null;
        
    }
    
    
    public Clinica retornarNovosDados(Clinica clinica) {
        
        // Validação das especialidades
        ListaEspecialidade especialidades = new ListaEspecialidade();
        for(int i = 0; i < listaEspecialidades.length; i++) {
            if(listaEspecialidades[i] == null) {
                listaEspecialidades[i] = "";
            }
        }
        especialidades.setListaEspecialidades(listaEspecialidades);
        
        // Crie e retorna uma clinica do tipo "Cliente"
        try {
            
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            this.clinica = clinica;
            this.clinica.setNome(nome);
            this.clinica.setCnpj(cnpj);
            this.clinica.setEndereco(endereco);
            this.clinica.setDescricao(descricao);
            this.clinica.setValor_minimo_consulta(retornarValor());
            this.clinica.setResponsavel(responsavel);
            return this.clinica;
            
        } catch(Exception ex) {

        }
        return null;
        
    }
    
    
    public float retornarValor() {
        
        if(valorConsultas.contains("R$")) {
            valorConsultas = valorConsultas.substring(valorConsultas.indexOf("R$") + 2);
        }
        if(valorConsultas.contains(",")) {
            valorConsultas = valorConsultas.replace(",", ".");
        }
        return Float.parseFloat(valorConsultas);
        
    }
    
    
    public boolean buscarCnpj() {
        
        ClinicaDAO dao = new ClinicaDAO();
        List<Clinica> listaClinicas = dao.getListaClinicas();
        
        for(Clinica clinica : listaClinicas) {
            if(this.clinica != null) {
                if(!this.clinica.getCnpj().equals(clinica.getCnpj())) {
                    if(clinica.getCnpj().equals(cnpj)) {
                        erros.mostrarErroCnpj();
                        erros.mostrarOrientacaoCnpj();
                        return false;
                    }
                }
            } else {
                if(clinica.getCnpj().equals(cnpj)) {
                    erros.mostrarErroCnpj();
                    erros.mostrarOrientacaoCnpj();
                    return false;
                }
            }
        }
        return true;
        
    }
    
    
    public boolean validarTexto(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    public boolean validarCnpj(String cnpj) {
        boolean expCpf = cnpj.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[-][0-9]{2}");
        if(expCpf == false) {
            expCpf = cnpj.matches("[0-9]{14}");
            if(expCpf == false) {
                return false;
            }
            cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
            this.cnpj = cnpj;
            return true;
        }
        this.cnpj = cnpj;
        return true;
    }
    
    
    public boolean validarValor(String valorConsultas) {
        
        boolean expValor = valorConsultas.matches("[0-9]+");
        if(expValor == false) {
            
            expValor = valorConsultas.contains("R$");
            if(expValor == true) {
                expValor = valorConsultas.matches("[R][$][0-9]+");
                if(expValor == false) {
                    expValor = valorConsultas.matches("[R][$][0-9]+[,][0-9]+");
                    return expValor;
                }
                return true;
            }
            
            expValor = valorConsultas.contains(",");
            if(expValor == true) {
                expValor = valorConsultas.matches("[0-9]+[,][0-9]+");
                if(expValor == true) {
                    this.valorConsultas = "R$" + valorConsultas;
                }
                return expValor;
            }
            
            return false;
            
        }
        this.valorConsultas = "R$" + valorConsultas;
        return true;
        
    }
    
    
}
