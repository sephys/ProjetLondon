/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;
import model.TourJoueur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Carte;
import model.Joueur;


/**
 *
 * @author Joke
 * @Vue
 * Représentage l'étalage
 */
public class JPEtalage extends JPanel{
    
    private Image img; // image de l'étalage
    private JPanel[] tab1;
    private JPanel[] tab2;
    
    
 
    
    public JPEtalage()
    {   
        
        try {
            URL uri = JPZoom.class.getResource("../img/etalage.png"); 
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setLayout(null);
        
        initEtalage(TourJoueur.getNbJoueur());
        
           
    }
    
    
    @Override
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);
    }
        
    
    public void initEtalage(int nbJoueur)
    {
       
        
        int nbEtalage=Joueur.getNbJoueur()+1;
        tab1=new JPanel[nbEtalage];
        tab2=new JPanel[nbEtalage];
        for(int i=0;i<nbEtalage;i++)
        {
            JPTest eta1=new JPTest();
          
             // panel haut
        
            eta1.setBounds(23+161*i, 13, 122, 168);
            eta1.setOpaque(false); // transparance
            
            this.add(eta1);
            tab1[i]=eta1;
            // D&D
            DropTarget dropTarget1 = new DropTarget(eta1, DnDConstants.ACTION_MOVE, 
                London.dndListener);
            
            JPTest eta2=new JPTest(); // panel bas
            eta2.setBounds(23+161*i, 216, 122, 168);
            eta2.setOpaque(false); // transparance
            
            this.add(eta2);
            tab2[i]=eta2;
            // D&D
            DropTarget dropTarget2 = new DropTarget(eta2, DnDConstants.ACTION_MOVE, 
                London.dndListener);
        }
        
    }
    
    public void actualiser(Carte[] tab1,Carte[] tab2)
    {
        for(int i=0;i<tab1.length;i++)
        {
            this.tab1[i].add(new JBCarte(tab1[i]));
            this.tab2[i].add(new JBCarte(tab2[i]));
        }
    }
}
