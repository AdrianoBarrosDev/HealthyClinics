
package healthyclinics.ferramentas;

import healthyclinics.shadows.CircleButtonShadow;
import healthyclinics.shadows.PanelShadow;
import healthyclinics.shadows.ShadowType;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ImageSlider extends JPanel implements ActionListener {

    private JLayeredPane layers;
    private JPanel pnlBackground;
    private PanelShadow pnlNomeClinica;
    private JLabel lblNomeClinica;
    private ImageIcon firstImage;
    private ImageIcon secondImage;
    private ImageIcon thirdImage;
    private JLabel imgCenter;
    private JLabel imgLeft;
    private JLabel imgRight;
    private JLabel newImage;
    private CircleButtonShadow btnLeft;
    private CircleButtonShadow btnRight;
    private Timer timer;
    private JButton btnSelecionado;
    private String [] listaNomes = {"HIGH CLINIC", "MAYO CLINIC", "BIG CLINIC"};
    private int times = 0;
    private boolean btnPressionado;
    private int idxListaNomes;
    
    
    public ImageSlider() { 
        
        this.setBounds(0, 0, 1920, 506);
        this.setBackground(new Color(0xF0F3FA));
        this.setLayout(null);
        
        configLayers();
        configImages();
        configButtons();
        
        timer = new Timer(1, this);
        
    }
    
    
    public final void configLayers() {
        
        // Configuração das layers
        layers = new JLayeredPane();
        layers.setBounds(this.getBounds());
        layers.setLayout(null);
        this.add(layers);
        
    }
    
    
    public final void configImages() {
        
        pnlBackground = new JPanel();
        pnlBackground.setBounds(this.getBounds());
        pnlBackground.setBackground(new Color(0xD5DEEF));
        pnlBackground.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0x395886)));
        pnlBackground.setLayout(null);
        layers.add(pnlBackground, JLayeredPane.DEFAULT_LAYER);
        
        firstImage = new ImageIcon(getClass().getResource("/imagens/MayoClinic.png"));
        imgCenter = new JLabel();
        imgCenter.setBounds(316, 0, 1289, 505);
        imgCenter.setIcon(firstImage);
        
        secondImage = new ImageIcon(getClass().getResource("/imagens/ImgClinic.png"));
        imgLeft = new JLabel();
        imgLeft.setBounds(-1030, 0, 1289, 505);
        imgLeft.setIcon(secondImage);
        
        thirdImage = new ImageIcon(getClass().getResource("/imagens/ImgClinicRoom.png"));
        imgRight = new JLabel();
        imgRight.setBounds(1662, 0, 1289, 505);
        imgRight.setIcon(thirdImage);
        
        layers.add(imgCenter, JLayeredPane.PALETTE_LAYER);
        layers.add(imgLeft, JLayeredPane.PALETTE_LAYER);
        layers.add(imgRight, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public final void configButtons() {
        
        ImageIcon imgArrow = new ImageIcon(getClass().getResource("/imagens/Arrow.png"));
        btnLeft = new CircleButtonShadow("");
        btnLeft.addActionListener(this);
        btnLeft.setBounds(229, 195, 116, 116);
        btnLeft.setBackground(new Color(0x638ECB));
        btnLeft.setIcon(imgArrow);
        btnLeft.changeColors(new Color(0x638ECB), new Color(0x8AAEE0));
        btnLeft.setIconTextGap(0);
        layers.add(btnLeft, JLayeredPane.MODAL_LAYER);
        
        
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
        btnRight.setBounds(1574, 195, 116, 116);
        btnRight.addActionListener(this);
        btnRight.setBackground(new Color(0x638ECB));
        btnRight.setIcon(imgArrowFliped);
        btnRight.changeColors(new Color(0x638ECB), new Color(0x8AAEE0));
        btnRight.setIconTextGap(0);
        layers.add(btnRight, JLayeredPane.MODAL_LAYER);
        
    }
    
    
    public void createShadow(JLabel label) {
        
        newImage = new JLabel();
        newImage.setBounds(label.getBounds());
        newImage.setIcon(label.getIcon());
        layers.add(newImage, JLayeredPane.PALETTE_LAYER);
        
    }
    
    
    public void changeSize(int width, int height) {
        
        this.setBounds(this.getX(), this.getY(), this.getWidth(), height);
        layers.setBounds(0, 0, this.getWidth(), height);
        thirdImage = new ImageIcon(getClass().getResource("/imagens/BigClinicOriginal.png"));
        
        imgCenter.setBounds(imgCenter.getX(), 0, width, height);
        imgLeft.setBounds(imgLeft.getX(), 0, width, height);
        imgRight.setBounds(imgRight.getX(), 0, width, height);
        
        imgCenter.setIcon(resizeImage(firstImage.getImage()));
        imgLeft.setIcon(resizeImage(secondImage.getImage()));
        imgRight.setIcon(resizeImage(thirdImage.getImage()));
        
        btnLeft.setBounds(btnLeft.getX(), (height / 2) - btnLeft.getHeight(), btnLeft.getWidth(), btnLeft.getHeight());
        btnRight.setBounds(btnRight.getX(), (height / 2) - btnRight.getHeight(), btnRight.getWidth(), btnRight.getHeight());
        
    }
    
    
    public ImageIcon resizeImage(Image img) {
        
        Image newImage = img.getScaledInstance(1289, 730, 100);
        return new ImageIcon(newImage);
        
    }
    
    
    public void adicionarNomeClinica() {
        
        this.setBounds(this.getX(), this.getY(), this.getWidth(), 1050);
        pnlBackground.setBounds(0, 0, this.getWidth(), 730);
        idxListaNomes = 1;
        
        // Painel que contém o nome da clínica recomendada
        pnlNomeClinica = new PanelShadow(45);
        pnlNomeClinica.setBounds(616, 757, 689, 72);
        pnlNomeClinica.setBackground(new Color(0x152E52));
        pnlNomeClinica.setShadowSize(3);
        pnlNomeClinica.setShadowType(ShadowType.BOT);
        this.add(pnlNomeClinica);
        
        lblNomeClinica = new JLabel(listaNomes[idxListaNomes]);
        lblNomeClinica.setBounds(208, 11, 275, 49);
        lblNomeClinica.setForeground(new Color(0xD5DEEF));
        lblNomeClinica.setFont(new Font("Rufina", Font.BOLD, 40));
        lblNomeClinica.setHorizontalAlignment(SwingConstants.CENTER);
        pnlNomeClinica.add(lblNomeClinica);
        
    }
    
    
    public void atualizarNome(String sentido) {
        
        if(pnlNomeClinica != null) {
            
            if(sentido.equals("right")) {

                if(lblNomeClinica.getX() > -268) {
                    lblNomeClinica.setBounds(lblNomeClinica.getX() - 7, lblNomeClinica.getY(), lblNomeClinica.getWidth(), lblNomeClinica.getHeight());
                }

                if(lblNomeClinica.getX() <= -268 && btnPressionado == true) {

                    idxListaNomes += 1;
                    if(idxListaNomes > 2) {
                        idxListaNomes = 0;
                    }
                    lblNomeClinica.setText(listaNomes[idxListaNomes]);

                    lblNomeClinica.setBounds(208, lblNomeClinica.getY(), lblNomeClinica.getWidth(), lblNomeClinica.getHeight());
                    btnPressionado = false;

                }

            } else if(sentido.equals("left")) {

                if(lblNomeClinica.getX() < 684) {
                    lblNomeClinica.setBounds(lblNomeClinica.getX() + 7, lblNomeClinica.getY(), lblNomeClinica.getWidth(), lblNomeClinica.getHeight());
                }

                if(lblNomeClinica.getX() >= 684 && btnPressionado == true) {

                    idxListaNomes -= 1;
                    if(idxListaNomes < 0) {
                        idxListaNomes = 2;
                    }
                    lblNomeClinica.setText(listaNomes[idxListaNomes]);

                    lblNomeClinica.setBounds(208, lblNomeClinica.getY(), lblNomeClinica.getWidth(), lblNomeClinica.getHeight());
                    btnPressionado = false;

                }

            }
            
        }
            
        
    } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnRight) {
            
            btnPressionado = true;
            btnSelecionado = btnRight;
            timer.start();
            
        } else if(e.getSource() == btnLeft) {
            
            btnPressionado = true;
            btnSelecionado = btnLeft;
            timer.start();
            
        } else if(e.getSource() == timer) {
            
            if(btnSelecionado == btnRight) {
                
                if(times == 0) {
                    createShadow(imgLeft);
                    imgLeft.setBounds(3008, imgLeft.getY(), imgLeft.getWidth(), imgLeft.getHeight());
                    times += 1;
                }
                
                if(imgCenter.getX() > -1030) {
                    imgCenter.setBounds(imgCenter.getX() - 20, imgCenter.getY(), imgCenter.getWidth(), imgCenter.getHeight());
                    imgLeft.setBounds(imgLeft.getX() - 20, imgLeft.getY(), imgLeft.getWidth(), imgLeft.getHeight());
                    imgRight.setBounds(imgRight.getX() - 20, imgRight.getY(), imgRight.getWidth(), imgRight.getHeight());
                    newImage.setBounds(newImage.getX() - 20, newImage.getY(), newImage.getWidth(), newImage.getHeight());
                    atualizarNome("right"); // Atualiza a posição da label e o nome da clínica
                    this.repaint();
                    
                } else {
                    imgCenter.setBounds(-1030, imgCenter.getY(), imgCenter.getWidth(), imgCenter.getHeight());
                    imgLeft.setBounds(1662, imgLeft.getY(), imgLeft.getWidth(), imgLeft.getHeight());
                    imgRight.setBounds(316, imgRight.getY(), imgRight.getWidth(), imgRight.getHeight());
                    
                    JLabel aux = imgCenter;
                    imgCenter = imgRight;
                    imgRight = imgLeft;
                    imgLeft = aux;
                    
                    layers.remove(newImage);
                    times = 0;
                    
                    timer.stop();
                    this.repaint();
                }
                
            } else if(btnSelecionado == btnLeft) {
                
                if(times == 0) {
                    createShadow(imgRight);
                    imgRight.setBounds(-2376, imgRight.getY(), imgRight.getWidth(), imgRight.getHeight());
                    times += 1;
                }
                
                if(imgCenter.getX() < 1662) {
                    imgCenter.setBounds(imgCenter.getX() + 20, imgCenter.getY(), imgCenter.getWidth(), imgCenter.getHeight());
                    imgLeft.setBounds(imgLeft.getX() + 20, imgLeft.getY(), imgLeft.getWidth(), imgLeft.getHeight());
                    imgRight.setBounds(imgRight.getX() + 20, imgRight.getY(), imgRight.getWidth(), imgRight.getHeight());
                    newImage.setBounds(newImage.getX() + 20, newImage.getY(), newImage.getWidth(), newImage.getHeight());
                    atualizarNome("left"); // Atualiza a posição da label e o nome da clínica

                } else {
                    
                    imgCenter.setBounds(1662, imgCenter.getY(), imgCenter.getWidth(), imgCenter.getHeight());
                    imgLeft.setBounds(316, imgLeft.getY(), imgLeft.getWidth(), imgLeft.getHeight());
                    imgRight.setBounds(-1030, imgRight.getY(), imgRight.getWidth(), imgRight.getHeight());
                    
                    JLabel aux = imgCenter;
                    imgCenter = imgLeft;
                    imgLeft = imgRight;
                    imgRight = aux;
                    
                    layers.remove(newImage);
                    times = 0;
                    
                    timer.stop();
                    this.repaint();
                    
                }
                
                this.repaint();
                
            }
            
        }
        
    }

    
    
}
