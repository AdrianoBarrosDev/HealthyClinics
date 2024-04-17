
package healthyclinics.layouts;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel{
    
    private int width;
    private int height;
    
    public RoundedPanel(int width, int height){
        this.width = width;
        this.height = height;
        
        setOpaque(false);
        setLayout(null);
    }
    
    
    // Deixa o painél arredondado
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       
       // Dimensões do arredondamento
       Dimension arcs = new Dimension(width, height); 
       int width = getWidth();
       int height = getHeight();
       Graphics2D graphics = (Graphics2D) g;

       // Desenha o painel arredondado
       graphics.setColor(getBackground());
       graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
       
    };
    
}
