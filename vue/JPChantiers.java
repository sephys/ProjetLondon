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
    GridBagConstraints gc = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 2;
    gc.gridy = 1;
    JPPileChantier chantier = new JPPileChantier();
    this.add(chantier,gc);
 
    
    GridBagConstraints gc2 = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc2.gridx = 4;
    gc2.gridy = 1;
    JPPileChantier chantier2 = new JPPileChantier();
    this.add(chantier2,gc2);
        
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.setSize(700, 500);
        f.add(new JPChantiers());

    f.setVisible(true);
    }
}
