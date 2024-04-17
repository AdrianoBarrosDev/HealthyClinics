
package healthyclinics.ferramentas;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SalvarImagem {

    private JFileChooser fileChooser;
    private ImageIcon imgSelecionada;
    private File imageFile;
    
    public SalvarImagem() {
        
    }
    
    
    public ImageIcon getImgSelecionada() {
        return imgSelecionada;
    }

    public void setImgSelecionada(ImageIcon imgSelecionada) {
        this.imgSelecionada = imgSelecionada;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    
    public boolean mostrarTela() {
        
        fileChooser = new JFileChooser();
        
        // Filtro para o filechooser aceitar apenas imagens
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg"));
        
        int response = fileChooser.showOpenDialog(null);
        
        if(response == JFileChooser.APPROVE_OPTION) {
            
            imageFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                imgSelecionada = new ImageIcon(imageFile.toString());
                return true;
                
            } catch(Exception e) {
                System.out.println("Erro ao adicionar o arquivo!");
            }
            
        }
        return false;
        
    }
    
}
