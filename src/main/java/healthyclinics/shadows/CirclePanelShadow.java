
package healthyclinics.shadows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CirclePanelShadow extends JPanel {

    private ShadowType shadowType = ShadowType.CENTER;
    private int shadowSize = 6;
    private float shadowOpacity = 0.3f;
    private Color shadowColor = Color.BLACK;
    
    
    public CirclePanelShadow() {
        setOpaque(false);
        setLayout(null);
    }

    
    public ShadowType getShadowType() {
        return shadowType;
    }

    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
    
    
    @Override
    protected void paintComponent(Graphics graphics) {
        createShadow(graphics);
        super.paintComponent(graphics);
    }
    
    
    private void createShadow(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        int width = getWidth() - size;
        int height = getHeight() - size;
        
        if(shadowType == ShadowType.TOP) {
            x = shadowSize;
            y = size;
        } else if(shadowType == ShadowType.BOT) {
            x = shadowSize;
            y = 0;
        } else if(shadowType == ShadowType.TOP_LEFT) {
            x = size;
            y = size;
        } else if(shadowType == ShadowType.TOP_RIGHT) {
            x = 0;
            x = size;
        } else if(shadowType == ShadowType.BOT_LEFT) {
            x = size;
            y = 0;
        } else if(shadowType == ShadowType.BOT_RIGHT) {
            x = 0;
            y = 0;
        } else {
            // Center
            x = shadowSize;
            y = shadowSize;
        }
        
        BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bf.createGraphics();
        g.setBackground(getBackground());
        g.setColor(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval(0, 0, width, height);
        
        // Create shadow
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(bf), 0, 0, null);
        g2.drawImage(bf, x, y, null);
        
    }
    
}
