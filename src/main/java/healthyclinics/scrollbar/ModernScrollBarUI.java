
package healthyclinics.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {

    private int THUMB_SIZE;
    
    
    public ModernScrollBarUI() {
        this.THUMB_SIZE = 200;
    }
    
    public ModernScrollBarUI(int THUMB_SIZE) {
        this.THUMB_SIZE = THUMB_SIZE;
    }
    
    
    @Override
    protected Dimension getMaximumThumbSize() {
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }
    
    @Override
    protected Dimension getMinimumThumbSize() {
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ScrollBarButton();
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ScrollBarButton();
    }
    
    
    @Override
    protected void paintTrack(Graphics g, JComponent jc, Rectangle r) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int orientation = scrollbar.getOrientation();
        int size;
        int x;
        int y;
        int width;
        int height;
        if(orientation == JScrollBar.VERTICAL) {
            size = r.width / 2;
            x = r.x + ((r.width - size) / 2);
            y = r.y;
            width = size;
            height = r.height;
        } else {
            size = r.height / 2;
            y = r.y + ((r.height - size) / 2);
            x = 0;
            width = r.width;
            height = size;
        }
        g2.setColor(new Color(0xF0F3FA));
        g2.fillRect(x, y, width, height);
        
        
    }
    
    
    @Override
    protected void paintThumb(Graphics g, JComponent jc, Rectangle r) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            
        } else {
            
        }
        g2.setColor(scrollbar.getForeground());
        g2.fillRoundRect(x, y, width, height, 10, 10);
        
    }
    
    
    private class ScrollBarButton extends JButton {
        
        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }
        
        @Override
        public void paint(Graphics g) {
            
        }
        
    }
    
    
}
