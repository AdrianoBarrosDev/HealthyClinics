
package healthyclinics.classes;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ConfigFrame {

    private final Manager manager;
    private final JFrame mainFrame;
    
    public ConfigFrame(Manager manager) {
        
        // Configurações do frame
        ImageIcon iconHealthyClinics = new ImageIcon(getClass().getResource("/imagens/IconHC.png"));
        this.manager = manager;
        mainFrame = new JFrame("HelthyClinics");
        mainFrame.setIconImage(iconHealthyClinics.getImage());
        mainFrame.getContentPane().setBackground(new Color(0xF0F3FA));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);
        mainFrame.setLayout(null);
        
    }

    
    public JFrame getMainFrame() {
        return mainFrame;
    }
    
    
    public Manager getManager() {
        return manager;
    }
    
    
}
