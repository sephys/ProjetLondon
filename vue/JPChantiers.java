/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import javax.swing.*;
import model.Joueur;

/**
 *
 * @author FT
 */
public class JPChantiers extends JPanel{
        private JPPileChantier[] chantiers;
        
    public JPChantiers(){
           
    //On ajoute le bouton au content pane de la JFrame
    chantiers = new JPPileChantier[48];
    for(int i=0; i<32;i++){
         JPPileChantier chantier = new JPPileChantier();
         chantiers[i]=chantier;
         this.add(chantier);
    }

            
        
        
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.setSize(700, 500);
        f.add(new JPChantiers());

    f.setVisible(true);
    }
}
