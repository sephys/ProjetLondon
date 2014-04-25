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
public class EmprunterControl implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                "Êtes-vous sûr de vouloir emprunter 10£ à la banque ?",
                "Emprunter",
                JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            Main.getJeu().getListeJoueur().getJoueur().addPret(1);
            Main.getJeu().getInfos().maj_infos();
        }
    }
    
}
