/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Joueur;

/**
 *
 * @author Anh-Djuy
 */
public class TroisJoueurs implements ActionListener {
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur.setNbJoueur(3);
    }
}
