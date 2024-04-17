
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosAdicionarMedico {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorCpfInvalido;
    private JLabel errorMedicoInvalido;
    private JLabel errorNaoEncontrado;
    
    
    public ErrosAdicionarMedico(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    public void erroCpfInvalido() {
        
        // Erro no TextField Cpf
        removerErro(errorCpfInvalido);
        errorCpfInvalido = new JLabel("CPF inválido!");
        errorCpfInvalido.setBounds(1024, 377, 132, 20);
        errorCpfInvalido.setForeground(new Color(0x395886));
        errorCpfInvalido.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorCpfInvalido.setIcon(imgError);
        layeredPane.add(errorCpfInvalido, JLayeredPane.POPUP_LAYER);
    }
    
    
    public void erroMedicoInvalido() {
        
        // Erro no TextField Cpf
        removerErro(errorMedicoInvalido);
        errorMedicoInvalido = new JLabel("Não é possível adicionar esse médico! Ele já está registrado em uma clínica.");
        errorMedicoInvalido.setBounds(833, 707, 575, 20);
        errorMedicoInvalido.setForeground(new Color(0xD5DEEF));
        errorMedicoInvalido.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorMedicoInvalido.setIcon(imgError);
        layeredPane.add(errorMedicoInvalido, JLayeredPane.POPUP_LAYER);
    }
    
    
    public void erroNaoEncontrado() {
        
        // Erro no TextField Cpf
        removerErro(errorNaoEncontrado);
        errorNaoEncontrado = new JLabel("Médico não encontrado! Verifique o CPF.");
        errorNaoEncontrado.setBounds(932, 377, 335, 20);
        errorNaoEncontrado.setForeground(new Color(0x395886));
        errorNaoEncontrado.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorNaoEncontrado.setIcon(imgError);
        layeredPane.add(errorNaoEncontrado, JLayeredPane.POPUP_LAYER);
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
        removerErro(errorCpfInvalido);
        removerErro(errorMedicoInvalido);
        removerErro(errorNaoEncontrado);
        
    }
    
    
}
