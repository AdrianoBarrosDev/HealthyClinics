
package healthyclinics.validacoes;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.db.TipoContaDAO;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.erros.ErrosCadastroCliente;
import healthyclinics.tablesData.TipoConta;
import healthyclinics.telas.TelaCadastrarCliente;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLayeredPane;

public class ValidacaoCadastroCliente {
    
    private final ConfigFrame configFrame;
    private final TelaCadastrarCliente cadastroCliente;
    private final JLayeredPane layeredPane;
    private final ErrosCadastroCliente erros;
    private Conta conta;
    private TipoConta tipoConta;
    
    public ValidacaoCadastroCliente(ConfigFrame configFrame, TelaCadastrarCliente cadastroCliente, JLayeredPane layeredPane) {
        this.configFrame = configFrame;
        this.cadastroCliente = cadastroCliente;
        this.layeredPane = layeredPane;
        this.erros = new ErrosCadastroCliente(layeredPane);
    }

    public ErrosCadastroCliente getErros() {
        return erros;
    }

    public Conta getConta() {
        return conta;
    }
    
    
    public ContaCliente validarUser(String nome, String cpf, String data, String telefone, String endereco, String senha, String convenio) {
        
        erros.removerErros(); // Remove os erros da tela
        
        TipoContaDAO daoTipo = new TipoContaDAO();
        tipoConta = daoTipo.consultar(1);
        
        boolean usuarioValido = true;
        if(validarNome(nome) == false) { // Se o nome for inválido
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
        if(validarEndereco(endereco) == false) { // Se o endereço for inválido
            usuarioValido = false;
            erros.mostrarErroEndereco();
        }
        if(validarSenha(senha) == false) { // Se a senha for inválida
            usuarioValido = false;
            erros.mostrarErroSenha();
        }
        
        
        if(usuarioValido == true) { // Se as informações digitadas forem todas válidas
            
            if(cadastroCliente != null) {
                try {

                    if(buscarCpf()) {
                        // Crie e retorna uma conta do tipo "Cliente"
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataDate = df.parse(cadastroCliente.getTxtData().getText());
                        
                        df = new SimpleDateFormat("yyyy-MM-dd");
                        conta = new Conta(nome, 
                                cadastroCliente.getTxtCpf().getText(), 
                                java.sql.Date.valueOf(df.format(dataDate)), 
                                cadastroCliente.getTxtTelefone().getText(), 
                                endereco,
                                senha,
                                tipoConta);
                        
                        return new ContaCliente(
                                convenio,
                                conta
                        );
                        
                    }

                } catch(Exception ex) {

                }
                
            }
            
        } else {
            erros.mostrarOrientacao(); // Mostra a orientação dos erros
        }
        
        
        layeredPane.repaint();
        return null;
        
    }
    
    
    public boolean buscarCpf() {
        
        ContaDAO dao = new ContaDAO();
        List<Conta> listaContas = dao.getListaContas();
        if(listaContas != null) {
            for(Conta conta : listaContas) {
                if(conta.getTipoConta().getDescricao().equals(tipoConta.getDescricao())) {
                    if(conta.getCpf().equals(cadastroCliente.getTxtCpf().getText())) {
                        erros.mostrarOrientacaoCpf();
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
    
    
    public boolean validarNome(String texto) {
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
            cadastroCliente.getTxtCpf().setText(cpf);
            return true;
        }
        cadastroCliente.getTxtCpf().setText(cpf);
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
            cadastroCliente.getTxtData().setText(data);
            return true;
        }
        cadastroCliente.getTxtData().setText(data);
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
                cadastroCliente.getTxtTelefone().setText(telefone);
                return true;
            }
            return false;
            
        }
        cadastroCliente.getTxtTelefone().setText(telefone);
        return true;
    }
    
    
    public boolean validarEndereco(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    public boolean validarSenha(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    
}
