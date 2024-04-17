
package healthyclinics.validacoes;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.db.TipoContaDAO;
import healthyclinics.erros.ErrosPerfilProfissional;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.tablesData.TipoConta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLayeredPane;

public class ValidacaoUsuarioProfissional {
    
    private final ConfigFrame configFrame;
    private final JLayeredPane layeredPane;
    private final ErrosPerfilProfissional erros;
    private String nome;
    private String cpf;
    private String data;
    private String telefone;
    private String endereco;
    private String genero;
    private String senha;
    private String crm;
    private Conta conta;
    private TipoConta tipoConta;
    
    public ValidacaoUsuarioProfissional(ConfigFrame configFrame, JLayeredPane layeredPane) {
        this.configFrame = configFrame;
        this.layeredPane = layeredPane;
        this.erros = new ErrosPerfilProfissional(layeredPane);
    }

    
    public ErrosPerfilProfissional getErros() {
        return erros;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
    public boolean validarUser(String nome, String cpf, String data, String telefone, String endereco, String genero, String senha, String crm) {
        
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.telefone = telefone;
        this.endereco = endereco;
        this.genero = genero;
        this.senha = senha;
        this.crm = crm;
        
        erros.removerErros(); // Remove os erros da tela

        // Busca a conta logada no banco
        ContaDAO dao = new ContaDAO();
        conta = dao.get(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getId());
        
        TipoContaDAO daoTipo = new TipoContaDAO();
        tipoConta = daoTipo.consultar(2);
        
        boolean usuarioValido = true;
        if(validarTexto(nome) == false) { // Se o nome for inválido
            usuarioValido = false;
            erros.mostrarErroNome();
        }
        if(validarCpf(cpf) == false) { // Se o cpf for inválido
            usuarioValido = false;
            erros.mostrarErroCpf();
        }
        if(validarData(data) == false) { // Se a data for inválida
            usuarioValido = false;
            erros.mostrarErroData();
        }
        if(validarTelefone(telefone) == false) { // Se o telefone for inválido
            usuarioValido = false;
            erros.mostrarErroTelefone();
        }
        if(validarTexto(endereco) == false) { // Se o endereço for inválido
            usuarioValido = false;
            erros.mostrarErroEndereco();
        }
        if(validarTexto(senha) == false) { // Se a senha for inválida
            usuarioValido = false;
            erros.mostrarErroSenha();
        }
        if(validarCrm(crm) == false) { // Se a senha for inválida
            usuarioValido = false;
            erros.mostrarErroCrm();
        }
        if(usuarioValido == true) {
            return buscarCpf() == true;
        }
        
        layeredPane.repaint();
        return false;
        
    }
    
    
    public ContaProfissional retornarNovosDados(ContaProfissional contaProfissional) {
        
        try {
            
            // Crie e retorna uma conta do tipo "Profissional"
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataDate = df.parse(data);
                        
            df = new SimpleDateFormat("yyyy-MM-dd");
            conta = contaProfissional.getConta();
            conta.setNome(nome);
            conta.setCpf(cpf);
            conta.setDataNascimento(java.sql.Date.valueOf(df.format(dataDate)));
            conta.setTelefone(telefone);
            conta.setEndereco(endereco);
            conta.setGenero(genero);
            conta.setSenha(senha);
            conta.setTipoConta(tipoConta);
            
            contaProfissional.setCrm(crm);
            return contaProfissional;
            //contaProfissional.setConta(conta);

        } catch(Exception ex) {

        }
        return null;
        
    }
    
    
    public boolean buscarCpf() {
        
        ContaDAO dao = new ContaDAO();
        List<Conta> listaContas = dao.getListaContas();
        
        for(Conta conta : listaContas) {
            if(conta.getTipoConta().getDescricao().equals("Profissional")) {
                if(!conta.getCpf().equals(this.conta.getCpf())) {
                    if(conta.getCpf().equals(cpf)) {
                        erros.mostrarErroCpf();
                        erros.mostrarOrientacaoCpf();
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
    
    
    public boolean validarTexto(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    public boolean validarCpf(String cpf) {
        boolean expCpf = cpf.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}");
        if(expCpf == false) {
            expCpf = cpf.matches("[0-9]{11}");
            if(expCpf == false) {
                return false;
            }
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            this.cpf = cpf;
            return true;
        }
        this.cpf = cpf;
        return true;
    }
    
    
    public boolean validarData(String data) {
        boolean expData = data.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}");
        if(expData == false) {
            expData = data.matches("[0-9]{8}");
            if(expData == false) {
                return false;
            }
            data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 8);
            this.data = data;
            return true;
        }
        this.data = data;
        return true;
    }
    
    
    public boolean validarTelefone(String telefone) {
        boolean expTelefone = telefone.matches("[(][0-9]{2,3}[)][ ][0-9]{4,5}[-][0-9]{4}");
        if(expTelefone == false) {
            
            expTelefone = telefone.matches("[0-9]{10,11}");
            if(expTelefone == true) {
                if(telefone.length() == 10) {
                    telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
                } else if(telefone.length() == 11) {
                    telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
                }
                this.telefone = telefone;
                return true;
            }
            return false;
            
        }
        this.telefone = telefone;
        return true;
    }
    
    
    public boolean validarCrm(String crm) {
        boolean expCrm = crm.matches("[C][R][M][/][A-Z]{2}[ ][0-9]{6}");
        if(expCrm == false) {
            
            expCrm = crm.matches("[C][R][M][/][A-Z]{2}[0-9]{6}");
            if(expCrm == true) {
                crm = crm.substring(0, 6) + " " + crm.substring(6);
                this.crm = crm;
                return true;
            }
            return false;
            
        }
        this.crm = crm;
        return true;
    }
    
    
    
}
