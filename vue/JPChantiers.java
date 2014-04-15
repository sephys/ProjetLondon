/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.*;
import model.Joueur;

/**
 *
 * @author FT
 */
public class JPChantiers extends JPanel{
        private JPPileChantier[] chantiers;
        
    public JPChantiers(){
           
       chantiers = new JPPileChantier[16];
        
        /* On ajoute un gridbagLauout au panel */
       this.setLayout(new GridBagLayout());

       /* Le gridBagConstraints va définir la position et la taille des éléments */
       GridBagConstraints gc = new GridBagConstraints();
       gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 0;
       for(int i=0; i<16; i++){
           if(i==8){
                gc.gridy = 1;
           }
            gc.insets = new Insets(0,0,5,5);
            JPPileChantier chantier = new JPPileChantier(i);
             
            chantiers[i] = chantier;
            this.add(chantier,gc);
       }
    }

    public JPPileChantier[] getChantiers() {
        return chantiers;
    }


    public void setChantiers(JPPileChantier[] chantiers) {
        this.chantiers = chantiers;
    }
  
    public static void main(String[] args){
        Frame f = new Frame();
        f.setSize(700, 500);
        f.add(new JPChantiers());

    f.setVisible(true);
    }
}
