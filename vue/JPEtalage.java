/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import model.TourJoueur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Carte;

/**
 *  Ce conteneur contient les éléments de l'étalage
 * @author Joke
 * @Vue Représentage l'étalage
 */
public class JPEtalage extends JPanel {
    
    private Image img; // image de l'étalage
    private JPanel[] tab1;
    private JPanel[] tab2;
    
    public JPEtalage() {        
        // Image de l'étalage
        try {
            URL uri = JPEtalage.class.getResource("../img/etalage.png");            
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
        g.drawImage(img, 0, 0, 855, 663, this);
    }
    
    /** 
     * Initialisation de l'étalage en créant les JPanels qui accueilleront les
     * JBCarte. 3 colonnes sont utilisées pour 2 jouers, 4 pour 3 joueurs, 
     * 5 pour 4 joueurs.
     * @param nbJoueur Le nombre de joueurs.
     */
    public void initEtalage(int nbJoueur) {
        
        int nbEtalage = TourJoueur.getNbJoueur() + 1;
        tab1 = new JPanel[nbEtalage];
        tab2 = new JPanel[nbEtalage];
        
        JPanel[] tab1transi=new JPanel[5];
        JPanel[] tab2transi=new JPanel[5];
        
        // tab1
        JPanel p1=new JPanel(new BorderLayout());
        p1.setBounds(28, 18, 122, 168);
        p1.setOpaque(false);
        tab1transi[0]=p1;
        this.add(p1);
        
        JPanel p2=new JPanel(new BorderLayout());
        p2.setBounds(198, 18, 122, 168);
        p2.setOpaque(false);
        tab1transi[1]=p2;
        this.add(p2);
        
        JPanel p3=new JPanel(new BorderLayout());
        p3.setBounds(368, 18, 122, 168);
        p3.setOpaque(false);
        tab1transi[2]=p3;
        this.add(p3);
        
        JPanel p4=new JPanel(new BorderLayout());
        p4.setBounds(535, 18, 122, 168);
        p4.setOpaque(false);
        tab1transi[3]=p4;
        this.add(p4);
        
        JPanel p5=new JPanel(new BorderLayout());
        p5.setBounds(705, 18, 122, 168);
        p5.setOpaque(false);
        tab1transi[4]=p5;
        this.add(p5);
        
        // tab2
        JPanel p6=new JPanel(new BorderLayout());
        p6.setBounds(28, 231, 122, 168);
        p6.setOpaque(false);
        tab2transi[0]=p6;
        this.add(p6);
        
        JPanel p7=new JPanel(new BorderLayout());
        p7.setBounds(198, 231, 122, 168);
        p7.setOpaque(false);
        tab2transi[1]=p7;
        this.add(p7);
        
        JPanel p8=new JPanel(new BorderLayout());
        p8.setBounds(368, 231, 122, 168);
        p8.setOpaque(false);
        tab2transi[2]=p8;
        this.add(p8);
        
        JPanel p9=new JPanel(new BorderLayout());
        p9.setBounds(535, 231, 122, 168);
        p9.setOpaque(false);
        tab2transi[3]=p9;
        this.add(p9);
        
        JPanel p10=new JPanel(new BorderLayout());
        p10.setBounds(705, 231, 122, 168);
        p10.setOpaque(false);
        tab2transi[4]=p10;
        this.add(p10);       
        
        for (int i = 0; i < nbEtalage; i++) {         
            tab1[i] = tab1transi[i];            
            tab2[i] = tab2transi[i];
        }
        
    }
    
    /**
     * Cette méthode permet de reconstruire l'étalage. S'il est plein, les cartes
     * de la ligne du haut sont défaussées. La ligne du bas passe en haut.
     * @param tab1
     * @param tab2 
     */
    public void actualiser(Carte[] tab1, Carte[] tab2) {

        // on vide l'etalage
        for (int i = 0; i < tab1.length; i++) {
            if (this.tab1[i].getComponentCount() != 0) {
                this.tab1[i].remove(0);
            }
            
            if (this.tab2[i].getComponentCount() != 0) {
                this.tab2[i].remove(0);
            }
        }

        // on remplit l'etalage
        for (int i = 0; i < tab1.length; i++) {
            if (tab1[i] != null) {
                JBCarte jb = new JBCarte(tab1[i]);
                jb.setPosition("etalage");
                jb.changeTailleBoutonImage(new Dimension(122, 168));                
                this.tab1[i].add(jb);
            }            
            if (tab2[i] != null) {                
                JBCarte jb = new JBCarte(tab2[i]);
                jb.setPosition("etalage");
                jb.changeTailleBoutonImage(new Dimension(122, 168));      
                this.tab2[i].add(jb);                
            }
            
        }
        
        this.repaint();
        this.revalidate();
    }
}
