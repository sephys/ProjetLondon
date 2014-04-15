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
import javax.swing.border.Border;

/**
 *
 * @author FT
 */
public class JPPileChantier extends JPanel{
    private int index;
    
    public JPPileChantier(int index){
        this.index = index;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        this.setPreferredSize(new Dimension(85,120));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
}
