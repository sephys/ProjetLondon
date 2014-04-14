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
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Zone;

/**
 *
 * @author Joke
 */
public class JPPlateau extends JPanel implements MouseListener {
    
    private Image image;
    private int imageWidth;
    private int imageHeight;
    private boolean estZoome;
    private JBZone[] tableauZone;

 
    public JPPlateau() {
        this.tableauZone = new JBZone[20];
        this.setLayout(null);
        try{
           URL uri = London.class.getResource("../img/plateau.png");
           image = ImageIO.read(uri); 
        }
        catch (IOException e1) {
            System.out.println("image du plateau introuvable");
            }
        
        estZoome=false;
        updateImageSizeDezoom();
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(5,568));
        this.setBackground(Color.blue);
        initBouton();
    }
    
    /**
     * Cette fonction crée puis place tous les boutons associés aux zones.
     */
    public void initBouton(){
        // ajout bouton :
        
        JBZone jb1=new JBZone(London.zones.get("Bethnal Green, Stepney & Poplar"));
        jb1.setBounds(870, 328, 86, 38);
        jb1.setVisible(false);
        jb1.setEnabled(false);
        this.add(jb1);
        
        JBZone jb2=new JBZone(London.zones.get("City"));
        jb2.setBounds(620, 342, 88, 38);
        jb2.setVisible(false);
        jb2.setEnabled(false);
        this.add(jb2);
        
        JBZone jb3=new JBZone(London.zones.get("Hackney"));
        jb3.setBounds(793, 122, 88, 38);
        jb3.setVisible(false);
        jb3.setEnabled(false);
        this.add(jb3);
        
        JBZone jb4=new JBZone(London.zones.get("Islington & Stoke Newington"));
        jb4.setBounds(582, 122, 88, 38);
        jb4.setVisible(false);
        jb4.setEnabled(false);
        this.add(jb4);
        
        JBZone jb5=new JBZone(London.zones.get("St. Pancras"));
        jb5.setBounds(447, 225, 88, 38);
        jb5.setVisible(false);
        jb5.setEnabled(false);
        this.add(jb5);
        
        JBZone jb6=new JBZone(London.zones.get("Hampstead"));
        jb6.setBounds(271, 139, 88, 38);
        jb6.setVisible(false);
        jb6.setEnabled(false);        
        this.add(jb6);
        
        JBZone jb7=new JBZone(London.zones.get("St. Marylebone"));
        jb7.setBounds(333, 313, 88, 38);
        jb7.setVisible(false);
        jb7.setEnabled(false);
        this.add(jb7);
        
        JBZone jb8=new JBZone(London.zones.get("Paddington"));
        jb8.setBounds(242, 389, 88, 38);
        jb8.setVisible(false);
        jb8.setEnabled(false);
        this.add(jb8);
        
        JBZone jb9=new JBZone(London.zones.get("Kensington"));
        jb9.setBounds(186, 486, 88, 38);
        jb9.setVisible(false);
        jb9.setEnabled(false);
        this.add(jb9);
        
        JBZone jb10=new JBZone(London.zones.get("Hammersmith"));
        jb10.setBounds(60, 467, 88, 38);
        jb10.setVisible(false);
        jb10.setEnabled(false);
        this.add(jb10);
        
        JBZone jb11=new JBZone(London.zones.get("Westminster"));
        jb11.setBounds(432, 462, 88, 38);
        jb11.setVisible(false);
        jb11.setEnabled(false);
        this.add(jb11);
        
        JBZone jb12=new JBZone(London.zones.get("Southwark & Bermondsey"));
        jb12.setBounds(657, 511, 88, 38);
        jb12.setVisible(false);
        jb12.setEnabled(false);
        this.add(jb12);
        
        JBZone jb13=new JBZone(London.zones.get("Lambeth"));
        jb13.setBounds(579, 740, 88, 38);
        jb13.setVisible(false);
        jb13.setEnabled(false);
        this.add(jb13);
        
        JBZone jb14=new JBZone(London.zones.get("Camberwell"));
        jb14.setBounds(736, 712, 88, 38);
        jb14.setVisible(false);
        jb14.setEnabled(false);
        this.add(jb14);
        
        JBZone jb15=new JBZone(London.zones.get("Deptford"));
        jb15.setBounds(885, 665, 88, 38);
        jb15.setVisible(false);
        jb15.setEnabled(false);
        this.add(jb15);
        
        JBZone jb16=new JBZone(London.zones.get("Lewisham"));
        jb16.setBounds(972, 842, 88, 38);
        jb16.setVisible(false);
        jb16.setEnabled(false);
        this.add(jb16);
        
        JBZone jb17=new JBZone(London.zones.get("Greenwich"));
        jb17.setBounds(1091, 611, 88, 38);
        jb17.setVisible(false);
        jb17.setEnabled(false);
        this.add(jb17);
        
        JBZone jb18=new JBZone(London.zones.get("Fulham & Chelsea"));
        jb18.setBounds(181, 659, 88, 38);
        jb18.setVisible(false);
        jb18.setEnabled(false);
        this.add(jb18);
        
        JBZone jb19=new JBZone(London.zones.get("Wandsworth"));
        jb19.setBounds(207, 843, 88, 38);
        jb19.setVisible(false);
        jb19.setEnabled(false);
        this.add(jb19);
        
        JBZone jb20=new JBZone(London.zones.get("Battersea"));
        jb20.setBounds(366, 711, 88, 38);
        jb20.setVisible(false);
        jb20.setEnabled(false);
        this.add(jb20);
        
        tableauZone[0] = jb1;
        tableauZone[1] = jb2;
        tableauZone[2] = jb3;
        tableauZone[3] = jb4;
        tableauZone[4] = jb5;
        tableauZone[5] = jb6;
        tableauZone[6] = jb7;
        tableauZone[7] = jb8;
        tableauZone[8] = jb9;
        tableauZone[9] = jb10;
        tableauZone[10] = jb11;
        tableauZone[11] = jb12;
        tableauZone[12] = jb13;
        tableauZone[13] = jb14;
        tableauZone[14] = jb15;
        tableauZone[15] = jb16;
        tableauZone[16] = jb17;
        tableauZone[17] = jb18;
        tableauZone[18] = jb19;
        tableauZone[19] = jb20;
    }

    public JBZone[] getTableauZone() {
        return tableauZone;
    }
    
    /**
     * La méthode activerZonesInvestissables active toutes les zones investissables
     * (les zones adjacentes aux zones qui ont déjà été investies)
     */
    public void activerZonesInvestissables() {
        for(int i = 0; i < tableauZone.length; i++){
            JBZone courrant = tableauZone[i];
            if(courrant.getZone().isActivable()){
                courrant.setEnabled(true);
            }
        }
    }
    
    /**
     * Cette méthode permet de récupérer l'indice d'un bouton associé à une zone
     * grâce au nom de celle-ci.
     * @param nom   Le nom de la zone recherchée
     * @return      L'indice de la zone recherchée
     */
    public int indiceZone(String nom){
        int z = -1;
        for(int i = 0; i < tableauZone.length; i++){
            JBZone zone = tableauZone[i];
            if(zone.getZone().getNom().equals(nom)){
                z = i;
            }
        }
        return z;
    }
    
    public void setZoom() {
        if(!estZoome)
        {
            updateImageSizeZoom();
            estZoome=true;
            for(int i = 0; i < this.tableauZone.length; i++){
                tableauZone[i].setVisible(true);
            }
        }
        else{
            updateImageSizeDezoom();
            estZoome=false;
            //enlever boutons
            for(int i = 0; i < this.tableauZone.length; i++){
                tableauZone[i].setVisible(false);
            }
        }
        
        revalidate();
    }
    
    private void updateImageSizeZoom() {
        imageWidth=1200;
        imageHeight=1073;
    }
    
    private void updateImageSizeDezoom() {
        imageWidth=842;
        imageHeight=608;
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
    
    /**
     * Cette méthode désactive toutes les boutons associés aux zones
     */
    public  void desactiveZones(){
        for(int i = 0; i < tableauZone.length; i++){
            tableauZone[i].setEnabled(false);
        }
    }
}
