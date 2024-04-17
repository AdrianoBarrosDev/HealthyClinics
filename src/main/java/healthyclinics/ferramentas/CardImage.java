
package healthyclinics.ferramentas;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CardImage extends JComponent {
    
    private Icon image;
    private int radius;
    
    
    public CardImage(Icon image) {
        this.image = image;
        this.radius = 25;
    }
    
    
    public CardImage(Icon image, int radius) {
        this.image = image;
        this.radius = radius;
    }

    
    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }
    
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        
        if (image != null) {
            
            Rectangle size = getAutoSize(image);
            int width = getWidth();
            int height = getHeight();
            
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g = img.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setColor(getBackground());
            
            if(radius < 50) {
                g.fillRoundRect(0, 0, width, height, radius, radius);
            } else {
                g.fillOval(0, 0, width, height);
            }
            g.setComposite(AlphaComposite.SrcIn);
            g.drawImage(toImage(image), size.x, size.y, size.width, size.height, null);
            g.dispose();
            
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.drawImage(img, 0, 0, null);
            g2.dispose();
            
        }
        
        super.paintComponent(grphcs);
        
    }
    
    
    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }
    

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
    
}
