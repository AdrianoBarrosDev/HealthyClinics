
package healthyclinics.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {
    
    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(2, 2));
        setForeground(new Color(0x395886));
        setBackground(new Color(0xF0F3FA));
        setUnitIncrement(50);
        
    }
    
    
    public ScrollBarCustom(int THUMB_SIZE) {
        setUI(new ModernScrollBarUI(THUMB_SIZE));
        setPreferredSize(new Dimension(2, 2));
        setForeground(new Color(0x395886));
        setBackground(new Color(0xF0F3FA));
        setUnitIncrement(50);
        
    }
    
    
}
