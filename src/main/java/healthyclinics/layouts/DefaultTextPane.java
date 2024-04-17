
package healthyclinics.layouts;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class DefaultTextPane extends JTextPane {
    
    private SimpleAttributeSet atributo;
    
    
    public DefaultTextPane() {
        
        atributo = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributo, StyleConstants.ALIGN_CENTER);
        
        setEditable(false);
        setOpaque(false);
        setParagraphAttributes(atributo, false);
        
    }
    
    
}
