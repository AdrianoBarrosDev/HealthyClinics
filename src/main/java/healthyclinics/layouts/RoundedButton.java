
package healthyclinics.layouts;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalButtonUI;

public class RoundedButton extends JButton {
    
    private RoundedPanel pnlBackground;
    
    // Define o texto do botão
    public RoundedButton(String nome) {
        super(nome);
        
        // Configurações padrão dos botões do sistema
        setForeground(new Color(0xF5EDED));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusable(false);
        setOpaque(false);
        setContentAreaFilled(false);
    }

    
    public RoundedPanel getPnlBackground() {
        return pnlBackground;
    }
    
    
    public final void configBackgroundBtn(int radius) {
        
        setBorder(new RoundedCornerBorder(getBackground()));
        pnlBackground = new RoundedPanel(radius, radius);
        pnlBackground.setBackground(this.getBackground());
        pnlBackground.setBounds(this.getBounds());
        pnlBackground.setLayout(null);
        this.setBounds(0, 0, pnlBackground.getWidth(), pnlBackground.getHeight());
        pnlBackground.add(this);
        
    }
    
    
    // Arredonda o botão
    @Override 
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.dispose();
        }
        super.paintComponent(g);
    }
    
    
    @Override 
    public void updateUI() {
      super.updateUI();
      setOpaque(false); // Define o botão como não opaco para não aparecer o fundo
      setBorder(new RoundedCornerBorder(getBackground())); // Define a borda arredondada do botão e a cor
    } 
    
    
    // Método para mudar a cor do background do botão dependendo do estado (COM BORDA, cor no parâmetro)
    public void changeColorsBorderVisible(Color colorDefault, Color colorBorder, Color colorPressed) {
        
        setBorder(new RoundedCornerBorder(colorDefault));
        
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if(getModel().isPressed()) { // Muda a cor do botão quando ele é pressionado
                    pnlBackground.setBackground(colorPressed);
                    setBorder(new RoundedCornerBorder(colorBorder));

                } else if(getModel().isRollover()) { // Muda a cor do botão quando o mouse passa por ele
                    pnlBackground.setBackground(colorPressed);
                    setBorder(new RoundedCornerBorder(colorPressed));

                } else { // Muda a cor default do botão
                    pnlBackground.setBackground(colorDefault);
                    setBorder(new RoundedCornerBorder(colorDefault));

                }
            }
        });
        
    }
    
    
    // Método para configurar o botão como transparente
    public void transparentButton() {
        
        setBorder(null);
        setOpaque(false);
        setBorderPainted(false);
        setFocusable(false);
        setContentAreaFilled(false);
        
        // Modifica a cor do botão quando ele está desativado
        setUI(new MetalButtonUI(){
                
                @Override
                protected Color getDisabledTextColor() {
                    return getForeground();
            
                }
            
        });
        
    }
    
    
}
