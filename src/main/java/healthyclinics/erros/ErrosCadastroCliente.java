
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosCadastroCliente {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorNome;
    private JLabel errorCpf;
    private JLabel errorData;
    private JLabel errorTelefone;
    private JLabel errorEndereco;
    private JLabel errorConvenio;
    private JLabel errorSenha;
    private JLabel errorOrientacao;
    private JLabel errorOrientacaoCpf;
    
    
    public ErrosCadastroCliente(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    
    public void mostrarErroNome() {
        
        // Erro no TextField Nome
        removerErro(errorNome);
        errorNome = new JLabel();
        errorNome.setBounds(1665, 382, imgError.getIconWidth(), imgError.getIconHeight());
        errorNome.setIcon(imgError);
        layeredPane.add(errorNome, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroCpf() {
        
        // Erro no TextField Cpf
        removerErro(errorCpf);
        errorCpf = new JLabel();
        errorCpf.setBounds(1665, 461, imgError.getIconWidth(), imgError.getIconHeight());
        errorCpf.setIcon(imgError);
        layeredPane.add(errorCpf, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroData() {
        
        // Erro no TextField Data
        removerErro(errorData);
        errorData = new JLabel();
        errorData.setBounds(1665, 540, imgError.getIconWidth(), imgError.getIconHeight());
        errorData.setIcon(imgError);
        layeredPane.add(errorData, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroTelefone() {
        
        // Erro no TextField Telefone
        removerErro(errorTelefone);
        errorTelefone = new JLabel();
        errorTelefone.setBounds(1665, 619, imgError.getIconWidth(), imgError.getIconHeight());
        errorTelefone.setIcon(imgError);
        layeredPane.add(errorTelefone, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroEndereco() {
        
        // Erro no TextField Endereço
        removerErro(errorEndereco);
        errorEndereco = new JLabel();
        errorEndereco.setBounds(1665, 698, imgError.getIconWidth(), imgError.getIconHeight());
        errorEndereco.setIcon(imgError);
        layeredPane.add(errorEndereco, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroConvenio() {
        
        // Erro no TextField Convênio
        removerErro(errorConvenio);
        errorConvenio = new JLabel();
        errorConvenio.setBounds(1665, 777, imgError.getIconWidth(), imgError.getIconHeight());
        errorConvenio.setIcon(imgError);
        layeredPane.add(errorConvenio, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    
    public void mostrarErroSenha() {
        
        // Erro no TextField Senha
        removerErro(errorSenha);
        errorSenha = new JLabel();
        errorSenha.setBounds(1665, 853, imgError.getIconWidth(), imgError.getIconHeight());
        errorSenha.setIcon(imgError);
        layeredPane.add(errorSenha, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    
    public void mostrarOrientacao() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacao);
        errorOrientacao = new JLabel("Dados Inválidos! Verifique as informações.");
        errorOrientacao.setBounds(1278, 911, 345, 20);
        errorOrientacao.setForeground(new Color(0x395886));
        errorOrientacao.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacao.setIcon(imgError);
        layeredPane.add(errorOrientacao, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacaoCpf() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacaoCpf);
        errorOrientacaoCpf = new JLabel("Este CPF já foi cadastrado! Verifique as informações.");
        errorOrientacaoCpf.setBounds(1243, 911, 408, 20);
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
        removerErro(errorConvenio);
        removerErro(errorSenha);
        removerErro(errorOrientacao);
        removerErro(errorOrientacaoCpf);
        
    }
    
    
}
