
package healthyclinics.layouts;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoundedButtonRadius extends JButton implements Border{
    
    private final int radius;
    private RoundedPanel pnlBackground;
    private Color colorBorder;
    
    
    public RoundedButtonRadius(String nome, int radius, Color colorBorder) {
        super(nome);
        this.radius = radius;
        this.colorBorder = colorBorder;
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setFocusable(false);
        setContentAreaFilled(false);
        
    }
    
    
    public RoundedPanel getPnlBackground() {
        return pnlBackground;
    }
    
    
    // Atualiza o UI
    @Override 
    public void updateUI() {
      super.updateUI();
      setOpaque(false); // Define o botão como não "Opaque" para não aparecer o fundo do botão original
      setBorder(this); // Define a borda arredondada do botão
    }
    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(colorBorder);
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
    
    
    public final void configBackgroundBtn() {
        
        // Configurações do painel utilizado como background do botão
        pnlBackground = new RoundedPanel(radius, radius);
        pnlBackground.setBackground(this.getBackground());
        pnlBackground.setBounds(this.getBounds());
        pnlBackground.setLayout(null);
        this.setBounds(0, 0, pnlBackground.getWidth(), pnlBackground.getHeight());
        pnlBackground.add(this);
        
    }
    
    
    public void changeColors(Color colorDefault, Color colorPressed) {
        
        pnlBackground.setBackground(colorDefault);
        
        addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent evt) {
                if(getModel().isPressed()) { // Muda a cor do botão quando ele é pressionado
                    pnlBackground.setBackground(colorPressed);

                } else if(getModel().isRollover()) { // Muda a cor do botão quando o mouse passa por ele
                    pnlBackground.setBackground(colorPressed);

                } else { // Muda a cor default do botão
                    pnlBackground.setBackground(colorDefault);

                }
                
            }
            
        });
        
    }
    
    
    public void transparentButton() {
        
        setOpaque(false);
        setBorderPainted(false);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorder(null);
        
    }
    
    
}
