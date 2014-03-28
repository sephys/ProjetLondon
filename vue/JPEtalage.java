/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Joke
 * @Vue
 * Représentage l'étalage
 */
public class JPEtalage extends JPanel{
    
    private Image img; // image de l'étalage
    
    public JPEtalage()
    {   
        
        try {
            URL uri = JPZoom.class.getResource("../img/etalage.png"); 
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setLayout(null);
        initEtalage(5);
        
           
    }
    
    
    @Override
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);
    }
        
    
    public void initEtalage(int nbJoueur)
    {
        
        for(int i=0;i<5;i++)
        {
            JPanel eta1=new JPanel(); // panel haut
            eta1.setBounds(26+134*i, 13, 90, 138);
            
            this.add(eta1);
            // D&D
            DropTarget dropTarget1 = new DropTarget(eta1, DnDConstants.ACTION_MOVE, 
                London.dndListener);
            
            JPanel eta2=new JPanel(); // panel bas
            eta2.setBounds(26+134*i, 181, 90, 138);
            
            this.add(eta2);
            // D&D
            DropTarget dropTarget2 = new DropTarget(eta2, DnDConstants.ACTION_MOVE, 
                London.dndListener);
        }
        
    }
}
