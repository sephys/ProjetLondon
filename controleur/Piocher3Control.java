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
public class Piocher3Control implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                        "Êtes-vous sûr de vouloir choisir l'action 'Piocher 3 cartes' ?",
                        "Emprunter",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {

                    Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true); // le joueur a finit son tour apres avoir piocher 3 cartes
                    Main.getJeu().getListeJoueur().getJoueur().setPioche(3);
                    //London.getListeJoueur().getJoueur().setFinTourPiocheCarte(true);

                }
    }
    
}
