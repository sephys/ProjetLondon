/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Carte;
import model.Constructible;
import model.Joueur;
import model.PouvoirBeta;
import vue.Main;

/**
 *
 * @author Joke
 */
public class TestControl implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        PouvoirBeta.pouvoirFleetStreet();
               
                
    }
    
}
