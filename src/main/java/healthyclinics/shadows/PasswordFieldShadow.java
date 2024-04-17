
package healthyclinics.shadows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public final class PasswordFieldShadow extends JPasswordField {

    private ShadowType shadowType = ShadowType.CENTER;
    private int shadowSize = 6;
    private float shadowOpacity = 0.5f;
    private Color shadowColor = Color.BLACK;
    private int radius;
    private Graphics2D g;
    
    
    public PasswordFieldShadow() {
        this.radius = 0;
        
        // Configurações padrões do PasswordField
        setOpaque(false);
        setBorder(null);
    }
    
    public PasswordFieldShadow(int radius) {
        this.radius = radius;
        
        // Configurações padrões do PasswordField
        setShadowType(ShadowType.BOT);
        setShadowSize(3);
        setShadowOpacity((float) 0.3);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 3, 35));
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
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        g.setBackground(getBackground());
        g.setColor(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width, height, radius, radius);
        
        // Create shadow
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
        
    }
    
    
}
