/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.Frame3Cartes;
import vue.JBCarte;
import vue.London;

/**
 *
 * @author Joke
 */
public class Regarder3CartesControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JBCarte c1 = new JBCarte(London.getDeck().poll());
                JBCarte c2 = new JBCarte(London.getDeck().poll());
                JBCarte c3 = new JBCarte(London.getDeck().poll());
                //London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
                Frame3Cartes f = new Frame3Cartes(c1, c2, c3);
    }
    
}
