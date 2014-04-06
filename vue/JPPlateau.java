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
public class JPPlateau extends JPanel implements MouseListener {
 
    private Image image;
    private int imageWidth;
    private int imageHeight;
    private boolean estZoome;
 
    public JPPlateau(Image image) {
        this.setLayout(null);
        this.image=image;
        estZoome=false;
        updateImageSizeDezoom();
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(5,588));
        this.setBackground(Color.blue);
       
    }
    
    public void initBouton(){
        // ajout bouton :   
        
        JButton jb1=new JButton("investir");
        jb1.setBounds(875, 405, 80, 30);
        this.add(jb1);
        
                JButton jb2=new JButton("investir");
        jb2.setBounds(625, 420, 80, 30);
        this.add(jb2);
        
                JButton jb3=new JButton("investir");
        jb3.setBounds(800, 200, 80, 30);
        this.add(jb3);
        
                JButton jb4=new JButton("investir");
        jb4.setBounds(590, 200, 80, 30);
        this.add(jb4);
        
                JButton jb5=new JButton("investir");
        jb5.setBounds(450, 305, 80, 30);
        this.add(jb5);
        
                JButton jb6=new JButton("investir");
        jb6.setBounds(275, 215, 80, 30);
        this.add(jb6);
        
                JButton jb7=new JButton("investir");
        jb7.setBounds(355, 390, 80, 30);
        this.add(jb7);
        
                JButton jb8=new JButton("investir");
        jb8.setBounds(625, 420, 80, 30);
        this.add(jb8);
        
                JButton jb9=new JButton("investir");
        jb9.setBounds(625, 420, 80, 30);
        this.add(jb9);
        
                JButton jb10=new JButton("investir");
        jb10.setBounds(625, 420, 80, 30);
        this.add(jb10);
    }
 
    public void setZoom() {
        if(!estZoome)
        {
          updateImageSizeZoom();  
          estZoome=true;
          initBouton();
        }
        else{
          updateImageSizeDezoom();  
          estZoome=false;
          //enlever boutons
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
