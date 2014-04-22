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
public class EmprunterControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        int rep = JOptionPane.showConfirmDialog(London.getFrame(),
                        "Êtes-vous sûr de vouloir emprunter 10£ à la banque ?",
                        "Emprunter",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    London.getListeJoueur().getJoueur().setNbPret(London.getListeJoueur().getJoueur().getNbPret() + 1);
                    London.getListeJoueur().getJoueur().setArgent(London.getListeJoueur().getJoueur().getArgent() + 10);
                    London.getInfos().maj_infos();
                }
    }
    
}
