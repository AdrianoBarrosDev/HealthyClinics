
package healthyclinics.layouts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class LayoutTableHeader extends DefaultTableCellRenderer{
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Configurações da tabela
        setForeground(new Color(0x395886));
        setFont(new Font("Volkhov", Font.PLAIN, 14));
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        
        if(column != 5) {
            if(column % 2 == 0) {
                setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(0x395886)));
            } else {
                setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(0x395886)));
            }
        } else {
            setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x395886)));
        }
            
        
        return this;
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.height = 30;
        return d;
    }
    
    
}
