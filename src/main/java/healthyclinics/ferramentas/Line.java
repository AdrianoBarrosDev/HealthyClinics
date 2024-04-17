
package healthyclinics.ferramentas;

import java.awt.Color;
import javax.swing.JComponent;

public class Line extends JComponent {
    
    private final int x1; 
    private final int y1;
    private final int x2;
    private final int y2;   
    private final int stroke;   
    private final Color color;

    public Line(int x1, int y1, int x2, int y2, int stroke, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke;
        this.color = color;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getStroke() {
        return stroke;
    }

    public Color getColor() {
        return color;
    }
    
    
}