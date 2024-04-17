
package healthyclinics.layouts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
        
        setForeground(new Color(0x395886));
        setFont(new Font("Volkhov", Font.PLAIN, 12));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        
        if(!isSelected) {
            
            setForeground(new Color(0x395886));
            
            // Se a linha for par
            if(row % 2 == 0){
                setBackground(new Color(0xF0F3FA));
                setBorder(BorderFactory.createLineBorder(new Color(0x395886)));
                
            }else{ // Se a linha for ímpar
                setBackground(new Color(0xF0F3FA));
                setBorder(BorderFactory.createLineBorder(new Color(0x395886)));
                
            }
           
        } else {
            setForeground(new Color(0x152E52));
            setBackground(new Color(0x638ECB));
            setBorder(BorderFactory.createLineBorder(new Color(0x395886)));
        }
        
        return this;
        
    }
    
}
