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
public class RestaurerControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        // check si pas de chantier
                if (Main.getJeu().getListeJoueur().getJoueur().getListeChantier().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas restaurer la ville");
                } else {
                    int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                            "Êtes-vous sûr de vouloir restaurer la ville ?",
                            "Restaurer la ville",
                            JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.YES_OPTION) {
                        m.disableAll();
                        Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
                        Main.getJeu().getMenudroite().getLabelInfo().setText("Vous pouvez activer des cartes");
                        JBCarte.setActiverCarte(true);
                        // change onglet
                        Main.getJeu().getPanelOnglet().setSelectedIndex(2);

                    }
                }
    }
    
}
