/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.TourJoueur;



/**
 *
 * @author Joke
 */
public class JPAccueil extends JPanel {
    
    private Image img; // image de fond
    
    public JPAccueil()
    {
        super();
        
        // image de fond
        try {
           URL uri = JPZoom.class.getResource("../img/accueil.png"); 
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // premier bouton play
        JButton play =new JButton("play");
        play.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                // frame choix du joueur
                final JFrame nbJoueur=new JFrame();
                nbJoueur.setLayout(new GridLayout(4,1));
                nbJoueur.setSize(300,200);
                nbJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                // premet de lier les radio boutons
                ButtonGroup bg=new ButtonGroup();
                
                JRadioButton j1=new JRadioButton("2 joueurs");
                JRadioButton j2=new JRadioButton("3 joueurs");
                JRadioButton j3=new JRadioButton("4 joueurs");
                
                // Ajout des listener sur les radio boutons
                j1.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TourJoueur.setNbJoueur(2);
                    }
                });
                
                j2.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TourJoueur.setNbJoueur(3);
                    }
                });
                
                j3.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TourJoueur.setNbJoueur(4);
                    }
                });
                
                
                bg.add(j1);
                bg.add(j2);
                bg.add(j3);
                
                nbJoueur.add(j1);
                nbJoueur.add(j2);
                nbJoueur.add(j3);
                
                // deuxième bouton jouer
                JButton p=new JButton("Jouer");
                p.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nbJoueur.dispose();
                        London.start();
                    }
                });
                nbJoueur.add(p);
                nbJoueur.setLocationRelativeTo(null);
                nbJoueur.setVisible(true);
                
                
                
                
            }
            
        });
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(play);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 589, 810, this);
    }
    
    
}
