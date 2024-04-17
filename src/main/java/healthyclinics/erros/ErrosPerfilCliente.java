
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosPerfilCliente {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorNome;
    private JLabel errorCpf;
    private JLabel errorData;
    private JLabel errorTelefone;
    private JLabel errorSenha;
    private JLabel errorEndereco;
    private JLabel errorOrientacaoCpf;
    
    
    public ErrosPerfilCliente(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    
    public void mostrarErroNome() {
        
        // Erro no TextField Nome
        removerErro(errorNome);
        errorNome = new JLabel();
        errorNome.setBounds(1207, 322, imgError.getIconWidth(), imgError.getIconHeight());
        errorNome.setIcon(imgError);
        layeredPane.add(errorNome, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroCpf() {
        
        // Erro no TextField Cpf
        removerErro(errorCpf);
        errorCpf = new JLabel();
        errorCpf.setBounds(899, 409, imgError.getIconWidth(), imgError.getIconHeight());
        errorCpf.setIcon(imgError);
        layeredPane.add(errorCpf, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroData() {
        
        // Erro no TextField Data
        removerErro(errorData);
        errorData = new JLabel();
        errorData.setBounds(899, 496, imgError.getIconWidth(), imgError.getIconHeight());
        errorData.setIcon(imgError);
        layeredPane.add(errorData, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroTelefone() {
        
        // Erro no TextField Telefone
        removerErro(errorTelefone);
        errorTelefone = new JLabel();
        errorTelefone.setBounds(899, 583, imgError.getIconWidth(), imgError.getIconHeight());
        errorTelefone.setIcon(imgError);
        layeredPane.add(errorTelefone, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroSenha() {
        
        // Erro no TextField Senha
        removerErro(errorSenha);
        errorSenha = new JLabel();
        errorSenha.setBounds(1207, 409, imgError.getIconWidth(), imgError.getIconHeight());
        errorSenha.setIcon(imgError);
        layeredPane.add(errorSenha, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroEndereco() {
        
        // Erro no TextField Endereço
        removerErro(errorEndereco);
        errorEndereco = new JLabel();
        errorEndereco.setBounds(1207, 670, imgError.getIconWidth(), imgError.getIconHeight());
        errorEndereco.setIcon(imgError);
        layeredPane.add(errorEndereco, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacaoCpf() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacaoCpf);
        errorOrientacaoCpf = new JLabel("Este CPF já foi cadastrado! Verifique as informações.");
        errorOrientacaoCpf.setBounds(253, 844, 408, 20);
        errorOrientacaoCpf.setForeground(new Color(0x395886));
        errorOrientacaoCpf.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacaoCpf.setIcon(imgError);
        layeredPane.add(errorOrientacaoCpf, JLayeredPane.POPUP_LAYER);
        
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
        removerErro(errorNome);
        removerErro(errorCpf);
        removerErro(errorData);
        removerErro(errorTelefone);
        removerErro(errorEndereco);
        removerErro(errorSenha);
        removerErro(errorOrientacaoCpf);
        
    }
    
    
}
