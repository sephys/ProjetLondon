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
public class InvestirControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuDroiteControl m=new MenuDroiteControl();
        int rep = JOptionPane.showConfirmDialog(London.getFrame(),
                        "Êtes-vous sûr de vouloir investir ?",
                        "Investir",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    m.disableAll();
                    London.getMenudroite().getLabelInfo().setText("Vous devez choisir une zone à investir");
                    London.getPlateau().activerZonesInvestissables();

                    London.getPlateau().desactiveZonesInvesties();
                    invest = true;
                    London.getMenudroite().getEmprunter().setEnabled(true);
                    // change onglet
                    London.getPanelOnglet().setSelectedIndex(0);
                }
    }
    
}
