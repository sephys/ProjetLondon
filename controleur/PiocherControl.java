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

/**
 *
 * @author Joke
 */
public class PiocherControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (London.getListeJoueur().getJoueur().getPioche() != 0) {
                    London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                    London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(London.getDeck().poll());
                    London.getListeJoueur().getJoueur().piocheMoins();
                    London.getMenudroite().repaint();
                    London.getMenudroite().revalidate();

                } else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de piocher une carte");
                }
    }
    
}
