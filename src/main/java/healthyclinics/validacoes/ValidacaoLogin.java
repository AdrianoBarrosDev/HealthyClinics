
package healthyclinics.validacoes;

import healthyclinics.erros.ErrosLogin;
import healthyclinics.telas.TelaLogin;
import javax.swing.JLayeredPane;

public class ValidacaoLogin {
    
    private final TelaLogin telaLogin;
    private final JLayeredPane layeredPane;
    private final ErrosLogin erros;
    
    public ValidacaoLogin(TelaLogin telaLogin, JLayeredPane layeredPane) {
        this.telaLogin = telaLogin;
        this.layeredPane = layeredPane;
        this.erros = new ErrosLogin(layeredPane);
    }

    public ErrosLogin getErros() {
        return erros;
    }
    
    
    public boolean validarLogin(String cpf, String senha) {
        
        erros.removerErros(); // Remove os erros da tela
        
        boolean loginValido = true;
        if(validarCpf(cpf) == false) { // Se o cpf for inválido
            loginValido = false;
            erros.mostrarErroCpf();
        }
        if(validarSenha(senha) == false) { // Se a senha for inválida
            loginValido = false;
            erros.mostrarErroSenha();
        }
        if(loginValido == false) { // Se o login for inválido
            erros.mostrarOrientacao();
        }
        
        layeredPane.repaint();
        return loginValido;
        
    }
    
    
    public boolean validarSenha(String texto) {
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
            telaLogin.getTxtCpf().setText(cpf);
            return true;
        }
        telaLogin.getTxtCpf().setText(cpf);
        return true;
    }
    
    
}
