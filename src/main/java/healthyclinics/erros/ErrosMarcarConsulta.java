
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ErrosMarcarConsulta {

    private JPanel mainPanel;
    private ImageIcon imgError;
    private JLabel errorData;
    private JLabel errorHorario;
    private JLabel errorMedico;
    private JLabel errorOrientacao;
    
    
    public ErrosMarcarConsulta(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    public void mostrarErroData() {
        
        // Erro no PasswordField Data
        removerErro(errorData);
        errorData = new JLabel();
        errorData.setBounds(862, 79, imgError.getIconWidth(), imgError.getIconHeight());
        errorData.setIcon(imgError);
        mainPanel.add(errorData, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroHorario() {
        
        // Erro no TextField Horário
        removerErro(errorHorario);
        errorHorario = new JLabel();
        errorHorario.setBounds(862, 177, imgError.getIconWidth(), imgError.getIconHeight());
        errorHorario.setIcon(imgError);
        mainPanel.add(errorHorario, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroMedico() {
        
        // Erro no TextField Médico
        removerErro(errorMedico);
        errorMedico = new JLabel();
        errorMedico.setBounds(862, 272, imgError.getIconWidth(), imgError.getIconHeight());
        errorMedico.setIcon(imgError);
        mainPanel.add(errorMedico, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacao() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacao);
        errorOrientacao = new JLabel("Dados inválidos! Verifique as informações.");
        errorOrientacao.setBounds(544, 364, 342, 20);
        errorOrientacao.setForeground(new Color(0x395886));
        errorOrientacao.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacao.setIcon(imgError);
        errorOrientacao.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(errorOrientacao, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void removerErro(JLabel error) {
        // Remove o erro do parâmetro da tela
        try {
            mainPanel.remove(error);
        } catch (Exception e) {
            
        }
    }
    
    public void removerErros() {
        // Remove todos os erros da tela
        removerErro(errorHorario);
        removerErro(errorMedico);
        removerErro(errorData);
        removerErro(errorOrientacao);
    }
    
    
}
