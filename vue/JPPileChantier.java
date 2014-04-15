/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author FT
 */
public class JPPileChantier extends JPanel{
    
    public JPPileChantier(){
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        this.setPreferredSize(new Dimension(80,122));
       
    }
}
