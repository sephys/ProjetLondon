/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vue.JBCarte;
import vue.London;
import vue.Main;

/**
 *  Ce Listener permet de gérer l'appui sur le bouton Jouer des cartes
 *  du menu à droite. 
 * @author Joke
 */
public class JouerControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                "Êtes-vous sûr de vouloir jouer des cartes ?",
                "Jouer des cartes",
                JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            Main.getJeu().getListeJoueur().getJoueur().setDerniereAction("Jouer des cartes");
            // Activaction du Drag & Drop
            London.dndListener.setDragEnable(true);
            // Activation du double clic du bouton droit pour les cartes non-constructibles mais activables.
            JBCarte.setClicDroitJouer(true);
            Main.getJeu().getMenudroite().getLabelInfo().setText("Vous pouvez jouer des cartes");

            m.disableAll();
            Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
            // Change d'onglet pour l'onglet d'affichage des Chantiers du Joueur.
            Main.getJeu().getPanelOnglet().setSelectedIndex(2);
        }
    }

}
