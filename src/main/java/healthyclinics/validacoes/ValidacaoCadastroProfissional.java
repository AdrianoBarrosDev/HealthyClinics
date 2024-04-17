
package healthyclinics.validacoes;

import healthyclinics.classes.ConfigFrame;
import healthyclinics.db.ContaDAO;
import healthyclinics.db.ContaProfissionalDAO;
import healthyclinics.db.TipoContaDAO;
import healthyclinics.erros.ErrosCadastroProfissional;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.tablesData.TipoConta;
import healthyclinics.telas.TelaCadastrarProfissional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLayeredPane;

public class ValidacaoCadastroProfissional {
    
    private final ConfigFrame configFrame;
    private final TelaCadastrarProfissional cadastroProfissional;
    private final JLayeredPane layeredPane;
    private final ErrosCadastroProfissional erros;
    private Conta conta;
    private TipoConta tipoConta;
    
    public ValidacaoCadastroProfissional(ConfigFrame configFrame, TelaCadastrarProfissional cadastroProfissional, JLayeredPane layeredPane) {
        this.configFrame = configFrame;
        this.cadastroProfissional = cadastroProfissional;
        this.layeredPane = layeredPane;
        this.erros = new ErrosCadastroProfissional(layeredPane);
    }

    public ErrosCadastroProfissional getErros() {
        return erros;
    }

    public Conta getConta() {
        return conta;
    }
    
    
    public ContaProfissional validarUser(String nome, String cpf, String data, String telefone, String endereco, String senha, String crm) {
        
        erros.removerErros(); // Remove os erros da tela
        
        TipoContaDAO daoTipo = new TipoContaDAO();
        tipoConta = daoTipo.consultar(2);
        
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
        if(validarCrm(crm) == false) { // Se o crm for inválido
            usuarioValido = false;
            erros.mostrarErroCrm();
        }
        if(validarSenha(senha) == false) { // Se a senha for inválida
            usuarioValido = false;
            erros.mostrarErroSenha();
        }
        
        if(usuarioValido == true) { // Se as informações digitadas forem todas válidas
            
            try {
                
                if(buscarCpf() == true) {
                    if(buscarCrm() == true) {
                        // Crie e retorna uma conta do tipo "Profissional"
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataDate = df.parse(cadastroProfissional.getTxtData().getText());
                        
                        df = new SimpleDateFormat("yyyy-MM-dd");
                        conta = new Conta(nome, 
                                cadastroProfissional.getTxtCpf().getText(), 
                                java.sql.Date.valueOf(df.format(dataDate)), 
                                cadastroProfissional.getTxtTelefone().getText(), 
                                cadastroProfissional.getTxtCrm().getText(), 
                                senha,
                                tipoConta);
                        
                        return new ContaProfissional(
                                cadastroProfissional.getTxtCrm().getText(),
                                conta
                        );
                    }
                }
                
            } catch(Exception ex) {
                
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
                    if(conta.getCpf().equals(cadastroProfissional.getTxtCpf().getText())) {
                        erros.mostrarOrientacaoCpf();
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
    
    
    public boolean buscarCrm() {
        
        ContaProfissionalDAO dao = new ContaProfissionalDAO();
        List<ContaProfissional> listaContas = dao.getContasProfissionais();
        if(listaContas != null) {
            for(ContaProfissional conta : listaContas) {
                if(conta.getCrm().equals(cadastroProfissional.getTxtCrm().getText())) {
                    erros.mostrarOrientacaoCrm();
                    return false;
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
            cadastroProfissional.getTxtCpf().setText(cpf);
            return true;
        }
        cadastroProfissional.getTxtCpf().setText(cpf);
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
            cadastroProfissional.getTxtData().setText(data);
            return true;
        }
        cadastroProfissional.getTxtData().setText(data);
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
                cadastroProfissional.getTxtTelefone().setText(telefone);
                return true;
            }
            return false;
            
        }
        cadastroProfissional.getTxtTelefone().setText(telefone);
        return true;
    }
    
    
    public boolean validarEndereco(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    public boolean validarCrm(String texto) {
        boolean expCrm = texto.matches("[C][R][M][/][A-Z]{2}[ ][0-9]{6}");
        if(expCrm == false) {
            expCrm = texto.matches("[C][R][M][/][A-Z]{2}[0-9]{6}");
            if(expCrm == true) {
                texto = texto.substring(0, 6) + " " + texto.substring(6);
                cadastroProfissional.getTxtCrm().setText(texto);
                return true;
            }
            return false;
        }
        return true;
    }
    
    
    public boolean validarSenha(String texto) {
        return !(texto.isBlank() || texto.isEmpty());
    }
    
    
    
}