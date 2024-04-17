
package healthyclinics.ferramentas;

import healthyclinics.db.Conexao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ConvertBlob {

    private ImageIcon imgConvertida;
    
    public ConvertBlob() {
        
    }
    
    public ImageIcon imgConvertida(Blob img) {
        
        if(img != null) {
            InputStream in;
            try {
                in = img.getBinaryStream();
                BufferedImage image = ImageIO.read(in);
                return new ImageIcon(image);
            } catch (SQLException ex) {
                Logger.getLogger(ConvertBlob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConvertBlob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }
    
    
    public Blob converterImagem(File imgFile) {
        
        try {
            byte[] fileContent = Files.readAllBytes(imgFile.toPath());
            
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            Blob blob = conn.createBlob();
            blob.setBytes(1, fileContent);
            return blob;
            
        } catch (IOException ex) {
            
        } catch (SQLException ex) {
            
        }
        return null;
        
    }
    
    
}
