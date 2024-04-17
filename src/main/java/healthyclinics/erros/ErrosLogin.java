
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosLogin {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorSenha;
    private JLabel errorCpf;
    private JLabel errorOrientacao;
    
    
    public ErrosLogin(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    public void mostrarErroCpf() {
        
        // Erro no TextField Cpf
        removerErro(errorCpf);
        errorCpf = new JLabel();
        errorCpf.setBounds(1167, 483, imgError.getIconWidth(), imgError.getIconHeight());
        errorCpf.setIcon(imgError);
        layeredPane.add(errorCpf, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroSenha() {
        
        // Erro no PasswordField senha
        removerErro(errorSenha);
        errorSenha = new JLabel();
        errorSenha.setBounds(1167, 604, imgError.getIconWidth(), imgError.getIconHeight());
        errorSenha.setIcon(imgError);
        layeredPane.add(errorSenha, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacao() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacao);
        errorOrientacao = new JLabel("Usuário ou senha incorretos.");
        errorOrientacao.setBounds(836, 680, 245, 20);
        errorOrientacao.setForeground(new Color(0x395886));
        errorOrientacao.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacao.setIcon(imgError);
        layeredPane.add(errorOrientacao, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void removerErro(JLabel error) {
        // Remove o erro do parâmetro da tela
        try {
            layeredPane.remove(error);
        } catch (Exception e) {
            
        }
    }
    
    public void removerErros() {
        // Remove todos os erros da tela
        removerErro(errorCpf);
        removerErro(errorSenha);
    }
    
    
}
