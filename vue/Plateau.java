/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Joke
 */
public class Plateau extends JPanel implements MouseListener {
 
    private Image image;
    private int imageWidth;
    private int imageHeight;
    private boolean estZoome;
 
    public Plateau(Image image) {
        this.image=image;
        estZoome=false;
        updateImageSizeDezoom();
        this.addMouseListener(this);
    }
 
    public void setZoom() {
        if(!estZoome)
        {
          updateImageSizeZoom();  
          estZoome=true;
        }
        else{
          updateImageSizeDezoom();  
          estZoome=false;
        }
        
        revalidate();
    }
 
    private void updateImageSizeZoom() {
        /*
        imageWidth=(int) (image.getWidth()*zoom);
        imageHeight=(int) (image.getHeight()*zoom);
        */
        imageWidth=1200;
        imageHeight=1200;
    }
    
    private void updateImageSizeDezoom() {
        
        imageWidth=800;
        imageHeight=800;
    }
 
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }
 
        public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, imageWidth, imageHeight, this);
    }
 
    public static void main(String[] args) {
 
 
        
            /*JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
 
            Plateau imagePanel = new Plateau(ImageIO.read(new File("/Users/Joke/NetBeansProjects/London/src/london/img/plateau.png")));
            panel.add(new JScrollPane(imagePanel), BorderLayout.CENTER);
 
         
 
            frame.setContentPane(panel);
 
 
            frame.setSize(820,820);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            */
            JFrame f=new JFrame();
            JTabbedPane pt=new JTabbedPane();
            pt.addTab("plp", new JPanel());
            pt.addTab("plop",new JPanel());
            f.add(pt);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
 
 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {
            this.setZoom();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
 
}
