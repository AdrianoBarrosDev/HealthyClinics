
package healthyclinics.ferramentas;

import healthyclinics.shadows.CircleButtonShadow;
import healthyclinics.shadows.CirclePanelShadow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CircleImageSlider extends JPanel implements ActionListener {

    private JLayeredPane layers;
    private ImageIcon firstImage;
    private ImageIcon secondImage;
    private ImageIcon thirdImage;
    private CirclePanelShadow mainPanel;
    private AvatarImage centerPanel;
    private AvatarImage leftPanel;
    private AvatarImage rightPanel;
    private AvatarImage newPanel;
    private CircleButtonShadow btnLeft;
    private CircleButtonShadow btnRight;
    private Timer timer;
    private JButton btnSelecionado;
    private int times = 0;
    
    
    public CircleImageSlider() {
        
        this.setBounds(0, 0, 1341, 362);
        this.setOpaque(false);
        this.setLayout(null);
        
        configLayers();
        configImages();
        configButtons();
        
        timer = new Timer(1, this);
        
    }
    
    
    public final void configLayers() {
        layers = new JLayeredPane();
        layers.setBounds(this.getBounds());
        layers.setLayout(null);
        this.add(layers);
    }
    
    
    public final void configImages() {
        
        mainPanel = new CirclePanelShadow();
        mainPanel.setBounds(415, 14, 350, 351);
        mainPanel.setBackground(new Color(0x152E52));
        layers.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        
        firstImage = new ImageIcon(getClass().getResource("/imagens/CircleSliderMayo.png"));
        centerPanel = new AvatarImage(firstImage);
        centerPanel.setBounds(11, 18, 328, 315);
        mainPanel.add(centerPanel);
        
        secondImage = new ImageIcon(getClass().getResource("/imagens/CircleSliderMayo.png"));
        leftPanel = new AvatarImage(secondImage);
        leftPanel.setBounds(-370, 18, 328, 315);
        leftPanel.setIcon(secondImage);
        mainPanel.add(leftPanel);
        
        thirdImage = new ImageIcon(getClass().getResource("/imagens/CircleSliderMayo.png"));
        rightPanel = new AvatarImage(thirdImage);
        rightPanel.setBounds(375, 18, 328, 315);
        rightPanel.setIcon(thirdImage);
        mainPanel.add(rightPanel);
        
    }
    
    
    public final void configButtons() {
        
        ImageIcon imgArrow = new ImageIcon(getClass().getResource("/imagens/Arrow.png"));
        btnLeft = new CircleButtonShadow("");
        btnLeft.addActionListener(this);
        btnLeft.setBounds(179, 225, 116, 116);
        btnLeft.setBackground(new Color(0x638ECB));
        btnLeft.setIcon(imgArrow);
        btnLeft.changeColors(new Color(0x638ECB), new Color(0x8AAEE0));
        btnLeft.setIconTextGap(0);
        layers.add(btnLeft, JLayeredPane.PALETTE_LAYER);
        
        
        BufferedImage bi = new BufferedImage(imgArrow.getIconWidth(), imgArrow.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        imgArrow.paintIcon(null, g, 0, 0);
        g.dispose();
        
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bi.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bi = op.filter(bi, null);
        
        ImageIcon imgArrowFliped = new ImageIcon(bi);
        
        btnRight = new CircleButtonShadow("");
        btnRight.setBounds(1018, 181, 116, 116);
        btnRight.addActionListener(this);
        btnRight.setBackground(new Color(0x638ECB));
        btnRight.setIcon(imgArrowFliped);
        btnRight.changeColors(new Color(0x638ECB), new Color(0x8AAEE0));
        btnRight.setIconTextGap(0);
        layers.add(btnRight, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void createShadow(AvatarImage label) {
        
        newPanel = new AvatarImage(label.getIcon());
        newPanel.setBounds(label.getBounds());
        newPanel.setIcon(label.getIcon());
        mainPanel.add(newPanel);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnRight) {
            
            btnSelecionado = btnRight;
            timer.start();
            
        } else if(e.getSource() == btnLeft) {
            
            btnSelecionado = btnLeft;
            timer.start();
            
        } else if(e.getSource() == timer) {
            
            if(btnSelecionado == btnRight) {
                
                if(times == 0) {
                    createShadow(leftPanel);
                    leftPanel.setBounds(739, leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
                    
                    mainPanel.remove(centerPanel);
                    centerPanel.setBounds(this.getX() + 426, this.getY() + 2472, this.getWidth(), this.getHeight());
                    layers.add(centerPanel, JLayeredPane.MODAL_LAYER);
                    
                    
                    times += 1;
                }
                
                if(centerPanel.getX() > -328) {
                    centerPanel.setBounds(centerPanel.getX() - 10, centerPanel.getY(), centerPanel.getWidth(), centerPanel.getHeight());
                    leftPanel.setBounds(leftPanel.getX() - 10, leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
                    rightPanel.setBounds(rightPanel.getX() - 10, rightPanel.getY(), rightPanel.getWidth(), rightPanel.getHeight());
                    newPanel.setBounds(newPanel.getX() - 10, newPanel.getY(), newPanel.getWidth(), newPanel.getHeight());
                    this.repaint();
                    
                } else {
                    centerPanel.setBounds(-370, centerPanel.getY(), centerPanel.getWidth(), centerPanel.getHeight());
                    leftPanel.setBounds(375, leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
                    rightPanel.setBounds(11, rightPanel.getY(), rightPanel.getWidth(), rightPanel.getHeight());
                    
                    AvatarImage aux = centerPanel;
                    centerPanel = rightPanel;
                    rightPanel = leftPanel;
                    leftPanel = aux;
                    
                    mainPanel.remove(newPanel);
                    times = 0;
                    
                    timer.stop();
                    this.repaint();
                }
                
            } else if(btnSelecionado == btnLeft) {
                
                if(times == 0) {
                    createShadow(rightPanel);
                    rightPanel.setBounds(-2376, rightPanel.getY(), rightPanel.getWidth(), rightPanel.getHeight());
                    times += 1;
                }
                
                if(centerPanel.getX() < 1662) {
                    centerPanel.setBounds(centerPanel.getX() + 20, centerPanel.getY(), centerPanel.getWidth(), centerPanel.getHeight());
                    leftPanel.setBounds(leftPanel.getX() + 20, leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
                    rightPanel.setBounds(rightPanel.getX() + 20, rightPanel.getY(), rightPanel.getWidth(), rightPanel.getHeight());
                    newPanel.setBounds(newPanel.getX() + 20, newPanel.getY(), newPanel.getWidth(), newPanel.getHeight());
                } else {
                    
                    centerPanel.setBounds(1662, centerPanel.getY(), centerPanel.getWidth(), centerPanel.getHeight());
                    leftPanel.setBounds(316, leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
                    rightPanel.setBounds(-1030, rightPanel.getY(), rightPanel.getWidth(), rightPanel.getHeight());
                    
                    AvatarImage aux = centerPanel;
                    centerPanel = leftPanel;
                    leftPanel = rightPanel;
                    rightPanel = aux;
                    
                    layers.remove(newPanel);
                    times = 0;
                    
                    timer.stop();
                    this.repaint();
                    
                }
                
                this.repaint();
                
            }
            
        }
        
    }

    
    
}
