
package healthyclinics.ferramentas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JComponent;

public class LinesComponent extends JComponent {

    private final LinkedList<Line> lines = new LinkedList<>();
    
    
    public LinesComponent() {
        super();
    }

    
    public LinkedList<Line> getLines() {
        return lines;
    }

    
    public void addLine(int x1, int x2, int x3, int x4, int stroke, Color color) {
        lines.add(new Line(x1, x2, x3, x4, stroke, color));        
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        // Desenha as linhas do vetor
        for (Line line : lines) {
            g2.setStroke(new BasicStroke(line.getStroke()));
            g2.setColor(line.getColor());
            g2.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }
    
    
}
