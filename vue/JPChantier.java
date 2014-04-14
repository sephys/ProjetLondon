/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.Joueur;

/**
 *
 * @author FT
 */
public class JPChantier extends JPanel{
    
    public JPChantier(){
        this.setLayout(new GridLayout(4,1,0,20));
        
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.add(new JPChantier());
        f.pack();
        f.setVisible(true);
    }
}
