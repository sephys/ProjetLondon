/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Joke
 * @Vue
 * Représente la fenêtre où l'on peut choisir le nombre de joueur
 */
public class Menu extends JPanel{
        Image image;

    public Menu(){
        this.setLayout(new BorderLayout());
        image = getToolkit().getImage("/Users/Joke/NetBeansProjects/London/src/london/img/menu.png");
    }
    
    @Override
    public void paintComponent(Graphics g){
     super.paintComponent(g);
     g.drawImage (image, 0, 0, getWidth(),getHeight(),this);
     repaint();
   }
}
