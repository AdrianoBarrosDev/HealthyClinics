
package healthyclinics.validacoes;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.db.TipoContaDAO;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.erros.ErrosPerfilCliente;
import healthyclinics.tablesData.TipoConta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLayeredPane;

public class ValidacaoUsuarioCliente {
    
    private final ConfigFrame configFrame;
    private final JLayeredPane layeredPane;
    private final ErrosPerfilCliente erros;
    private String nome;
    private String cpf;
    private String data;
    private String telefone;
    private String endereco;
    private String genero;
    private String senha;
    private String convenio;
    private Conta conta;
    private TipoConta tipoConta;
    
    public ValidacaoUsuarioCliente(ConfigFrame configFrame, JLayeredPane layeredPane) {
        this.configFrame = configFrame;
        this.layeredPane = layeredPane;
        this.erros = new ErrosPerfilCliente(layeredPane);
    }

    public ErrosPerfilCliente getErros() {
        return erros;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
    public boolean validarUser(String nome, String cpf, String data, String telefone, String endereco, String genero, String senha, String convenio) {
        
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.telefone = telefone;
        this.endereco = endereco;
        this.genero = genero;
        this.senha = senha;
        this.convenio = convenio;
        
        erros.removerErros(); // Remove os erros da tela
        
        // Busca a conta logada no banco
        ContaDAO dao = new ContaDAO();
        conta = dao.get(configFrame.getManager().getTelas().getTelaLogin().getContaLogada().getId());
        
        TipoContaDAO daoTipo = new TipoContaDAO();
        tipoConta = daoTipo.consultar(1);
        
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
        
        if(usuarioValido == true) {
            return buscarCpf() == true;
        }
        
        layeredPane.repaint();
        return false;
        
    }
    
    
    public ContaCliente retornarNovosDados(ContaCliente contaCliente) {
        
        try {
            
            // Crie e retorna uma conta do tipo "Profissional"
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataDate = df.parse(data);
                        
            df = new SimpleDateFormat("yyyy-MM-dd");
            conta = contaCliente.getConta();
            conta.setNome(nome);
            conta.setCpf(cpf);
            conta.setDataNascimento(java.sql.Date.valueOf(df.format(dataDate)));
            conta.setTelefone(telefone);
            conta.setEndereco(endereco);
            conta.setGenero(genero);
            conta.setSenha(senha);
            conta.setTipoConta(tipoConta);
            
            contaCliente.setConvenio(convenio);
            return contaCliente;
            
        } catch(Exception ex) {

        }
        return null;
        
    }
    
    
    public boolean buscarCpf() {
        
        ContaDAO dao = new ContaDAO();
        List<Conta> listaContas = dao.getListaContas();
        if(listaContas != null) {
            for(Conta conta : listaContas) {
                if(conta.getTipoConta().getDescricao().equals("Cliente")) {
                    if(!conta.getCpf().equals(this.conta.getCpf())) {
                        if(conta.getCpf().equals(cpf)) {
                            erros.mostrarErroCpf();
                            erros.mostrarOrientacaoCpf();
                            return false;
                        }
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
    
    
    
}
