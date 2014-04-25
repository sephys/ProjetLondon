/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.Main;

/**
 *
 * @author Joke
 */
public class TestControl implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        PouvoirBeta.pouvoirFleetStreet();
     */
        for(int i=0; i<80; i++){
            Main.getJeu().getDeck().poll();
        }
    }
    
}
