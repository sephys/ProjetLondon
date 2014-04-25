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
import model.Carte;

/**
 *  Ce conteneur contient un chantier
 * @author FT
 */
public class JPPileChantier extends JPanel{
    private int index;
    private boolean posable;
    private boolean carte2;
    private Image img; // image du chantier
    private JBCarte carte;
    
    public JPPileChantier(int index){
         try {
            URL uri = JPEtalage.class.getResource("../img/carteChantier.png");            
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
     @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 122, 168, this);
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

    /**
     * Quand on met que le posable à vrai, on active en même temps le Drag & Drop
     * @param posable   La nouvelle valeur de posable
     */
    public void setPosable(boolean posable) {
        this.posable = posable;
        if(posable==true){
            // Ajout Drag & Drop
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
    
    /**
     * Cette méthode permet d'ajouter une carte sur le chantier.
     * On la retient pour gérer si on peut Restaurer la ville ou pas.
     * @param e 
     */
    public  void ajoutCarte(Carte e){
        JBCarte carte = new JBCarte(e);
        carte.changeTailleBoutonImage(new Dimension(122, 168));
        this.add(carte);
        this.revalidate();
    }
    
    public JBCarte getJBCarte(){
        return this.carte;
    }
}
