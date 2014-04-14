/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import model.Joueur;

/**
 *
 * @author FT
 */
public class JPChantiers extends JPanel{
    
    public JPChantiers(){

    this.setLayout(new GridLayout(4,3, 20, 20));
    //On ajoute le bouton au content pane de la JFrame
this.add(new JButton("1"));
this.add(new JButton("2"));
this.add(new JButton("3"));
this.add(new JButton("4"));
this.add(new JButton("5"));
this.add(new JButton("4"));
this.add(new JButton("5"));
this.add(new JButton("4"));
this.add(new JButton("5"));

            
        
        
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.add(new JPChantiers());
        f.pack();
        f.setVisible(true);
    }
}
