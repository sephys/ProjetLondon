/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vue.London;
import vue.Main;

/**
 *
 * @author Joke
 */
public class PiocherControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.getJeu().getListeJoueur().getJoueur().getPioche() != 0) {
                    Main.getJeu().getListeJoueur().getJoueur().piocheCarte(Main.getJeu().getDeck().peekFirst());
                    Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(Main.getJeu().getDeck().poll());
                    
                    // si le deque et vide
                    if(Main.getJeu().getDeck().isEmpty()){
                    	Main.getJeu().getListeJoueur().setFinTour(Main.getJeu().getListeJoueur().getNbJoueur());
                    }
                    Main.getJeu().getListeJoueur().getJoueur().piocheMoins();
                    Main.getJeu().getMenudroite().repaint();
                    Main.getJeu().getMenudroite().revalidate();

                } else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de piocher une carte");
                }
    }
    
}
