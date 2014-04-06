/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import model.Plateau;

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
        initBouton();
    }
    
    public void initBouton(){
        // ajout bouton :   
        
        JButton jb_berthnal=new JButton("investir");
        jb_berthnal.setBounds(875, 405, 80, 30);
        this.add(jb_berthnal);
        
                JButton jb_city=new JButton("investir");
        jb_city.setBounds(625, 420, 80, 30);
        this.add(jb_city);
        
                JButton jb_hackney=new JButton("investir");
        jb_hackney.setBounds(800, 200, 80, 30);
        this.add(jb_hackney);
        
                JButton jb_islington=new JButton("investir");
        jb_islington.setBounds(590, 200, 80, 30);
        this.add(jb_islington);
        
                JButton jb_pancras=new JButton("investir");
        jb_pancras.setBounds(450, 305, 80, 30);
        this.add(jb_pancras);
        
                JButton jb_hampstead=new JButton("investir");
        jb_hampstead.setBounds(275, 215, 80, 30);
        this.add(jb_hampstead);
        
                JButton jb_marylebone=new JButton("investir");
        jb_marylebone.setBounds(355, 390, 80, 30);
        this.add(jb_marylebone);
        
                JButton jb_paddington=new JButton("investir");
        jb_paddington.setBounds(220, 355, 80, 30);
        this.add(jb_paddington);
        
                JButton jb_kensington=new JButton("investir");
        jb_kensington.setBounds(230, 565, 80, 30);
        this.add(jb_kensington);
        
                JButton jb_hammersmith=new JButton("investir");
        jb_hammersmith.setBounds(65, 545, 80, 30);
        this.add(jb_hammersmith);
        
                JButton jb_fulham=new JButton("investir");
        jb_fulham.setBounds(200, 745, 80, 30);
        this.add(jb_fulham);
        
                JButton jb_wandsworth=new JButton("investir");
        jb_wandsworth.setBounds(100, 850, 80, 30);
        this.add(jb_wandsworth);
        
                JButton jb_buttersea=new JButton("investir");
        jb_buttersea.setBounds(370, 790, 80, 30);
        this.add(jb_buttersea);
        
                JButton jb_lambeth=new JButton("investir");
        jb_lambeth.setBounds(570, 830, 80, 30);
        this.add(jb_lambeth);
        
                        JButton jb_camberwell=new JButton("investir");
        jb_camberwell.setBounds(740, 800, 80, 30);
        this.add(jb_camberwell);
        
                        JButton jb_depford=new JButton("investir");
        jb_depford.setBounds(865, 630, 80, 30);
        this.add(jb_depford);
        
                        JButton jb_lewisham=new JButton("investir");
        jb_lewisham.setBounds(975, 930, 80, 30);
        this.add(jb_lewisham);
        
                        JButton jb_greenwich=new JButton("investir");
        jb_greenwich.setBounds(1095, 685, 80, 30);
        this.add(jb_greenwich);
        
                        JButton jb_southwark=new JButton("investir");
        jb_southwark.setBounds(660, 600, 80, 30);
        this.add(jb_southwark);
        
                        JButton jb_westminster=new JButton("investir");
        jb_westminster.setBounds(435, 550, 80, 30);
        this.add(jb_westminster);
    }
    
public void hide_buttons(){
}

public void show_buttons(){
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
