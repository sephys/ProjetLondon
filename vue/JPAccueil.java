/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package vue;

import controleur.AfficheRegles;
import controleur.DebugControl;
import controleur.DeuxJoueurs;
import controleur.JPAccueilControl;
import controleur.QuatreJoueurs;

import controleur.TroisJoueurs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import model.Zone;

/**
 *
 * @author Joke
 */
public class JPAccueil extends JPanel {
    
    private Image img; // image de fond
    private boolean lancer = false; // savoir si on peut démarrer le jeu
    
    
    public JPAccueil() {
        super();
        this.setLayout(null);
        
        
        
        // image de fond
        try {
            URL uri = JPZoom.class.getResource("../img/accueil.png");
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // --- BOUTON DEBUG A SUPPRIMER A LA FIN
        JButton jbDebug = new JButton("Debug");
        jbDebug.addActionListener(new DebugControl());
        
        // --- FIN BOUTON DEBUG A SUPPRIMER A LA FIN
        JButton regle = new JButton("Règles");
        regle.addActionListener(new AfficheRegles());
        
        JButton play = new JButton("Jouer");
        play.addActionListener(new JPAccueilControl());
        
        play.setBounds(243, 365, 115, 20);
        jbDebug.setBounds(243, 405, 115, 20);
        regle.setBounds(243,445,115,20);
        this.add(play);
        this.add(jbDebug);
        this.add(regle);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 589, 810, this);
    }
    
 
}
