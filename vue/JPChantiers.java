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
           
        /* On ajoute un gridbagLauout au panel */
       this.setLayout(new GridBagLayout());

       /* Le gridBagConstraints va définir la position et la taille des éléments */
       for(int i=0; i<16; i++){
           for(int j=0; j<16;j++){
            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.gridx = 2;
            gc.gridy = 1;
            gc.insets = new Insets(10,10,10,10);
            JPPileChantier chantier = new JPPileChantier();
            this.add(chantier,gc);
           }
       }
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.setSize(700, 500);
        f.add(new JPChantiers());

    f.setVisible(true);
    }
}
