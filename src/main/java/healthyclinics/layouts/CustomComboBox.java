
package healthyclinics.layouts;

import healthyclinics.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBox extends JComboBox{

    private Color lineColor = new Color(0x395886);
    private boolean mouseOver;

    
    public CustomComboBox() {
        
        setBackground(new Color(0xD5DEEF));
        setForeground(new Color(0x395886));
        setBorder(new EmptyBorder(5, 3, 5, 3));
        setUI(new ComboUI(this));
        
    }
    
    
    public CustomComboBox(Object[] items) {
        super(items);
        
        setBackground(new Color(0xD5DEEF));
        setForeground(new Color(0x395886));
        setBorder(new EmptyBorder(5, 3, 5, 3));
        setUI(new ComboUI(this));
        
    }
    
    
    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    
    
    private class ComboUI extends BasicComboBoxUI {
        
        private CustomComboBox comboBox;
        
        public ComboUI(CustomComboBox comboBox) {
            this.comboBox = comboBox;
            
            addMouseListener(new MouseAdapter() {
               
                @Override
                public void mouseEntered(MouseEvent me) {
                    mouseOver = true;
                    repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent me) {
                    mouseOver = false;
                    repaint();
                }
                
            });
            
        }
        
        
        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            
        }

        
        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        
        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup pop = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    JScrollPane scroll = new JScrollPane(list);
                    scroll.setBackground(comboBox.getBackground());
                    ScrollBarCustom sb = new ScrollBarCustom(50);
                    sb.setUnitIncrement(30);
                    sb.setForeground(new Color(0x395886));
                    scroll.setVerticalScrollBar(sb);
                    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    return scroll; 
                }
            };
            return pop;
        }
        
        
        @Override
        public void paint(Graphics grphcs, JComponent jc) {

            super.paint(grphcs, jc);

            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            int width = getWidth();
            int height = getHeight();

            if (mouseOver) {
                g2.setColor(lineColor);
            } else {
                g2.setColor(new Color(150, 150, 150));
            }

            g2.fillRect(2, height - 1, width - 4, 1);
            createLineStyle(g2);
            g2.dispose();

        }
        
        
    }
    
    
    private class ArrowButton extends JButton {

        public ArrowButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(5, 0, 5, 0));
            setBackground(new Color(150, 150, 150));
        }

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int size = 10;
            int x = (width - size) / 2;
            int y = (height - size) / 2;
            int px[] = {x, x + size, x + size / 2};
            int py[] = {y, y, y + size};
            
            if (mouseOver) {
                g2.setColor(lineColor);
            } else {
                g2.setColor(new Color(150, 150, 150));
            }
            
            g2.fillPolygon(px, py, px.length);
            g2.dispose();
        }
        
    }
    
    
    private void createLineStyle(Graphics2D g2) {
        
        if(isFocusOwner()) {
            
            double width = getWidth() - 4;
            int height = getHeight();
            double size;
            
            g2.setColor(lineColor);
            if(mouseOver) {
                size = width * 1f;
            } else {
                size = width * 1f;
            }
            
            double x = (width - size) / 2;
            g2.fillRect(2, height - 1, (int) width, 1);
            
        }
        
    }
    
    
}
