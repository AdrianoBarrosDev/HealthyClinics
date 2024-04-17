
package healthyclinics.shadows;

import java.awt.Color;

public class TextPanel extends PanelShadow {
    
    private PanelShadow pnlCenter;
    private int padding;
    
    public TextPanel(int radius, int padding) {
        super(radius);
        this.padding = padding;
        setLayout(null);
    }

    
    public PanelShadow getPnlCenter() {
        return pnlCenter;
    }
    
    
    public void configPnlCenter() {
        
        pnlCenter = new PanelShadow(this.getRadius() - padding);
        pnlCenter.setBounds(padding, padding, this.getWidth() - (padding * 2), this.getHeight() - (padding * 2));
        pnlCenter.setBackground(new Color(0xF0F3FA));
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(ShadowType.BOT);
        pnlCenter.setLayout(null);
        this.add(pnlCenter);
        this.repaint();
        
    }
    
    
    public void configPnlBorder(int x, int y, int width, int height) {
        
        pnlCenter = new PanelShadow(this.getRadius());
        pnlCenter.setBounds(x, y, width, height);
        pnlCenter.setBackground(new Color(0xF0F3FA));
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(ShadowType.BOT);
        pnlCenter.setLayout(null);
        this.add(pnlCenter);
        this.repaint();
        
    }
    
    
}
