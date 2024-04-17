
package healthyclinics.erros;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ErrosGerenciarClinica {

    private JLayeredPane layeredPane;
    private ImageIcon imgError;
    private JLabel errorNome;
    private JLabel errorCnpj;
    private JLabel errorEndereco;
    private JLabel errorDescricao;
    private JLabel errorValor;
    private JLabel errorOrientacao;
    private JLabel errorOrientacaoCnpj;
    
    
    public ErrosGerenciarClinica(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.imgError = new ImageIcon(getClass().getResource("/imagens/Error.png"));
    }
    
    
    
    public void mostrarErroNome() {
        
        // Erro no TextField Nome
        removerErro(errorNome);
        errorNome = new JLabel();
        errorNome.setBounds(1162, 323, imgError.getIconWidth(), imgError.getIconHeight());
        errorNome.setIcon(imgError);
        layeredPane.add(errorNome, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroCnpj() {
        
        // Erro no TextField Cnpj
        removerErro(errorCnpj);
        errorCnpj = new JLabel();
        errorCnpj.setBounds(1162, 396, imgError.getIconWidth(), imgError.getIconHeight());
        errorCnpj.setIcon(imgError);
        layeredPane.add(errorCnpj, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroEndereco() {
        
        // Erro no TextField Endereço
        removerErro(errorEndereco);
        errorEndereco = new JLabel();
        errorEndereco.setBounds(1162, 468, imgError.getIconWidth(), imgError.getIconHeight());
        errorEndereco.setIcon(imgError);
        layeredPane.add(errorEndereco, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroDescricao() {
        
        // Erro no TextField Descrição
        removerErro(errorDescricao);
        errorDescricao = new JLabel();
        errorDescricao.setBounds(1584, 648, imgError.getIconWidth(), imgError.getIconHeight());
        errorDescricao.setIcon(imgError);
        layeredPane.add(errorDescricao, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarErroValor() {
        
        // Erro no TextField Valor
        removerErro(errorValor);
        errorValor = new JLabel();
        errorValor.setBounds(1584, 731, imgError.getIconWidth(), imgError.getIconHeight());
        errorValor.setIcon(imgError);
        layeredPane.add(errorValor, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacao() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacao);
        errorOrientacao = new JLabel("Dados inválidos! Verifique as informações.");
        errorOrientacao.setBounds(792, 806, 345, 20);
        errorOrientacao.setForeground(new Color(0x395886));
        errorOrientacao.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacao.setIcon(imgError);
        layeredPane.add(errorOrientacao, JLayeredPane.POPUP_LAYER);
        
    }
    
    
    public void mostrarOrientacaoCnpj() {
        
        // Mostra informações sobre os erros
        removerErro(errorOrientacaoCnpj);
        errorOrientacaoCnpj = new JLabel("Este CNPJ já foi cadastrado! Verifique as informações.");
        errorOrientacaoCnpj.setBounds(746, 806, 435, 20);
        errorOrientacaoCnpj.setForeground(new Color(0x395886));
        errorOrientacaoCnpj.setFont(new Font("Volkhov", Font.PLAIN, 15));
        errorOrientacaoCnpj.setIcon(imgError);
        layeredPane.add(errorOrientacaoCnpj, JLayeredPane.POPUP_LAYER);
        
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
        removerErro(errorCnpj);
        removerErro(errorEndereco);
        removerErro(errorDescricao);
        removerErro(errorValor);
        removerErro(errorOrientacaoCnpj);
        
    }
    
    
}
