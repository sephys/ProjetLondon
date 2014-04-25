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
 *
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
            London.dndListener.setDragEnable(true);
            JBCarte.setClicDroitJouer(true);
            Main.getJeu().getMenudroite().getLabelInfo().setText("Vous pouvez jouer des cartes");

            m.disableAll();
            Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
            // change onglet
            Main.getJeu().getPanelOnglet().setSelectedIndex(2);
        }
    }

}
