
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosConsultaProfissional {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorRelatorioNaoEnviado;
    private JLabel errorRelatorioInvalido;
    
    public ErrosConsultaProfissional(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    public void erroRelatorioNaoEnviado() {
        
        removerErro(errorRelatorioNaoEnviado);
        errorRelatorioNaoEnviado = new JLabel("Envie o relatório para encerrar esta consulta.");
        errorRelatorioNaoEnviado.setBounds(785, 709, 355, 20);
        errorRelatorioNaoEnviado.setForeground(new Color(0x395886));
        errorRelatorioNaoEnviado.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorRelatorioNaoEnviado.setIcon(imgError);
        layeredPane.add(errorRelatorioNaoEnviado, JLayeredPane.POPUP_LAYER);
        layeredPane.repaint();
        
    }
    
    
    public void erroRelatorioInvalido() {
        
        removerErro(errorRelatorioInvalido);
        errorRelatorioInvalido = new JLabel("Relatório inválido!");
        errorRelatorioInvalido.setBounds(1091, 562, 175, 20);
        errorRelatorioInvalido.setForeground(new Color(0x395886));
        errorRelatorioInvalido.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorRelatorioInvalido.setIcon(imgError);
        layeredPane.add(errorRelatorioInvalido, JLayeredPane.POPUP_LAYER);
        layeredPane.repaint();
        
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
        removerErro(errorRelatorioNaoEnviado);
        removerErro(errorRelatorioInvalido);
        
    }
    
    
}
