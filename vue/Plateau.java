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
        imageWidth=1200;
        imageHeight=1073;
    }
    
    private void updateImageSizeDezoom() {   
        imageWidth=675;
        imageHeight=588;
    }
 
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }
 
    @Override
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, imageWidth, imageHeight, this);
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
