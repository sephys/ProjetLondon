/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package london;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Joke
 * @Vue
 * Représente le panel contenant le plateau
 */
public class Plateau extends JPanel {
    Image image;

    public Plateau(){
        this.setLayout(new BorderLayout());
        image = getToolkit().getImage("/Users/Joke/NetBeansProjects/London/src/london/img/plateau.png");
    }
    
    @Override
    public void paintComponent(Graphics g){
     super.paintComponent(g);
     g.drawImage (image, 0, 0, getWidth(),getHeight(),this);
     repaint();
   }
    
    
}
