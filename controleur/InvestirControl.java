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
public class InvestirControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                        "Êtes-vous sûr de vouloir investir ?",
                        "Investir",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    Main.getJeu().getListeJoueur().getJoueur().setDerniereAction("Investir");
                    m.disableAll();
                    Main.getJeu().getMenudroite().getLabelInfo().setText("Vous devez choisir une zone à investir");
                    Main.getJeu().getPlateau().activerZonesInvestissables();

                    Main.getJeu().getPlateau().desactiveZonesInvesties();
                  
                    Main.getJeu().getMenudroite().getEmprunter().setEnabled(true);
                    // change onglet
                    Main.getJeu().getPanelOnglet().setSelectedIndex(0);
                }
    }
    
}
