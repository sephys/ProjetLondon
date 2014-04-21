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
import javax.swing.border.Border;

/**
 *
 * @author FT
 */
public class JPPileChantier extends JPanel{
    private int index;
    private boolean posable;
    private boolean carte2;
    
    public JPPileChantier(int index){
        if(index==0){
            /*Ajout Drag n Drop*/
            DropTarget dropChantier = new DropTarget(this, DnDConstants.ACTION_MOVE, 
            London.dndListener);
            this.index = index;
            this.posable=true;
            Border greenline = BorderFactory.createLineBorder(Color.green);
            this.setBorder(greenline);
        }
        else{
            this.index = index;
            this.posable=false;
             Border blackline = BorderFactory.createLineBorder(Color.GRAY);
             this.setBorder(blackline);
        }
        this.carte2=false;
        
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(122,168));
        this.setOpaque(false);
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isPosable() {
        return posable;
    }

    public void setPosable(boolean posable) {
        this.posable = posable;
        if(posable==true){
            /*Ajout Drag n Drop*/
            DropTarget dropChantier = new DropTarget(this, DnDConstants.ACTION_MOVE, 
            London.dndListener);
            Border greenline = BorderFactory.createLineBorder(Color.green);
            this.setBorder(greenline);
            this.repaint();
        }
    }

    public boolean isCarte2() {
        return carte2;
    }

    public void setCarte2(boolean carte2) {
        this.carte2 = carte2;
    }
    
    
}
