/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author FT
 */
public class JPInfo extends JPanel{
    
    public JPInfo(){
        this.setPreferredSize(new Dimension(300,810));
        this.setLayout(new GridLayout(4, 1));
        JPInfosJoueur j1 = new JPInfosJoueur("jean");
        this.add(j1);
    }
}
