/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vue.Main;

/**
 *  Ce Listener permet de gérer l'appui sur le bouton Piocher du menu de droite.
 * @author Joke
 */
public class PiocherControl implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.getJeu().getListeJoueur().getJoueur().getPioche() != 0) {
            // Le joueur pioche la première carte du Deck
            Main.getJeu().getListeJoueur().getJoueur().piocheCarte(Main.getJeu().getDeck().peekFirst());
            // On ajoute la carte à sa main
            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(Main.getJeu().getDeck().poll());
            
            // Si le Deque et vide
            if(Main.getJeu().getDeck().isEmpty()){
                // On lance la fin de tour
                Main.getJeu().getListeJoueur().setFinTour(Main.getJeu().getListeJoueur().getNbJoueur());
            }
            Main.getJeu().getListeJoueur().getJoueur().piocheMoins();
            // On actualise le nombre de cartes restantes dans le bouton
            int nbCarteRestantes = Main.getJeu().getDeck().size();
            Main.getJeu().getMenudroite().getPiocher().setText("          Piocher ("+nbCarteRestantes+")      ");
            Main.getJeu().getMenudroite().repaint();
            Main.getJeu().getMenudroite().revalidate();
            
        } else {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de piocher une carte");
        }
    }
    
}
