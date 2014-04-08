/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Joke
 */
public class MenuDroite extends JPanel{
    
    private JLabel labelJoueur; // indique quel joueur joue
    private JPanel main;
    
    
    public MenuDroite()
    {
        super(new BorderLayout());
        main=new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
        
        // 1er attribut : largeur
        Dimension d=new Dimension(310,260);
        
        main.setPreferredSize(d);
        main.setBackground(Color.yellow);
        
        // a changer : dynamique
        labelJoueur=new JLabel(London.getListeJoueur().getJoueur().getNom());
        
        main.add(labelJoueur);
        main.add(new JButton("Jouer des cartes"));
        main.add(new JButton("Restaurer la ville"));
        main.add(new JButton("Investir"));
        main.add(new JButton("Prendre trois cartes"));
        JButton jbPiocher = new JButton("Piocher");
        jbPiocher.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {  
            	London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                JPMain.ajoutCarte(London.getDeck().poll());
            }
            
        });
        
        JButton jbFinTour=new JButton("Fin du Tour");
        jbFinTour.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) 
            {  
                
                actualiserMain();
               
                
            }
            
        });
        
        main.add(jbPiocher);
        main.add(jbFinTour);
        this.add(main,BorderLayout.NORTH);
        
        JPZoom zoom=new JPZoom();
        zoom.setPreferredSize(new Dimension(300,400));
        // zoom.setBackground(Color.red);
        this.add(zoom,BorderLayout.SOUTH);
        
        
    }
    
    public void actualiserMain()
    {
        
                
                
                // sauvegarde de la main dans le tableau
                London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()]=(JPMain) London.south;
                // on enleve la main
                London.central.remove(London.south);
                // on passe au joueur suivant
                London.setListeJoueur(London.getListeJoueur().getSuivant());
                // change le label nomJoeuur
                labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
                // on remplace le panel par celui du nouveau joueur
                London.south=London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()];
                // on ajoute le panel
                London.central.add(London.south,BorderLayout.SOUTH);
                
                // actualiser la fenêtre
                London.frame.repaint();
                London.central.revalidate();
    }

   
   
    
    
    
}
