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

/**
 *
 * @author Joke
 */
public class JouerControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        int rep = JOptionPane.showConfirmDialog(London.getFrame(),
                "Êtes-vous sûr de vouloir jouer des cartes ?",
                "Jouer des cartes",
                JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            London.dndListener.setDragEnable(true);
            JBCarte.setClicDroitJouer(true);
            London.getMenudroite().getLabelInfo().setText("Vous pouvez jouer des cartes");

            m.disableAll();
            London.getMenudroite().getFinTour().setEnabled(true);
            // change onglet
            London.getPanelOnglet().setSelectedIndex(2);
        }
    }

}
