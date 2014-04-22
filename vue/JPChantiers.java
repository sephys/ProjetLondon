/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Joueur;

/**
 *
 * @author FT
 */
public class JPChantiers extends JPanel{
        private JPPileChantier[] chantiers;
        private Image img; // image du chantier
        
    public JPChantiers(){
        
        try {
            URL uri = JPEtalage.class.getResource("../img/chantier.jpg");            
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setLayout(null);
           
       chantiers = new JPPileChantier[15];
        
        /* On ajoute un gridbagLauout au panel */
       this.setLayout(new GridBagLayout());

       /* Le gridBagConstraints va définir la position et la taille des éléments */
       GridBagConstraints gc = new GridBagConstraints();
       gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 0;
       for(int i=0; i<15; i++){
           if(i%5==0){
                gc.gridy++;
           }
            gc.insets = new Insets(0,0,5,5);
            JPPileChantier chantier = new JPPileChantier(i);
             
            chantiers[i] = chantier;
            this.add(chantier,gc);
       }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 847, 663, this);
    }

    public JPPileChantier[] getChantiers() {
        return chantiers;
    }


    public void setChantiers(JPPileChantier[] chantiers) {
        this.chantiers = chantiers;
    }
  
    
}
