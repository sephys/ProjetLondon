/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Joueur;
import model.TourJoueur;

/**
 *  Listener qui permet d'initialiser le nombre de joueurs à deux
 * @author Anh-Djuy
 */
public class DeuxJoueurs implements ActionListener{
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	TourJoueur.setNbJoueur(2);
    }
    
}
