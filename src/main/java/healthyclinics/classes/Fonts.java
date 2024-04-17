
package healthyclinics.classes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class Fonts {
    
    public Fonts() {
        
        // Adiciona as fontes no sistema
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/Rufina-Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/Rufina-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/Volkhov-Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/Volkhov-Regular.ttf")));
        } catch(FontFormatException | IOException e) {
            System.out.println("Erro ao adicionar a fonte.");
        }
        
    }
    
}
