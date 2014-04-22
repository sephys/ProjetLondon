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
import static vue.MenuDroite.invest;

/**
 *
 * @author Joke
 */
public class FinTourControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        
        if (London.getListeJoueur().getJoueur().isFinitTour()) {
                    // check trop grand nombre de cartes dans la main
                    if (London.getListeJoueur().getJoueur().getMain().size() > 9) {
                        //System.out.println(London.getListeJoueur().getJoueur().getMain().size());
                        //JBCarte.setDoubleClick(true);
                        //London.getListeJoueur().getJoueur().setPiocheDefausse("defausse");
                        //setDefausseCarte(true); // peut se defausser de n'importe quelle carte

                        JOptionPane.showMessageDialog(null, "Vous avez trop de cartes en main. Vous devez vous en défausser avant de finir votre tour");
                        // if(London.getListeJoueur().getJoueur().getDefausse()==0)
                        //{
                        London.getListeJoueur().getJoueur().setDefausse(London.getListeJoueur().getJoueur().getMain().size() - 9);
                        //}

                        m.disableAll();
                        London.getMenudroite().getLabelInfo().setText("Vous avez trop de cartes en main");
                       // finTour.setEnabled(true);

                        // change onglet
                        London.getPanelOnglet().setSelectedIndex(1);

                    } else { // ici le joueur finit son tour
                        m.actualiserMain();

                        invest = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas finit votre tour");
                }
    }
    
}
