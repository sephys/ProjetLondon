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
 *  Ce Listener permet de gérer l'appui sur le bouton Piocher 3 cartes du menu de droite.
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
            Main.getJeu().getListeJoueur().getJoueur().setDerniereAction("Piocher 3 cartes");
            // La fin de tour est mis à vraie lorsqu'il finit de piocher
            Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true); 
            // Il doit piocher 3 cartes
            Main.getJeu().getListeJoueur().getJoueur().setPioche(3);
            
        }
    }
    
}
