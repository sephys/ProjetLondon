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
    JPanel t1=new JPanel();
    JPanel t2=new JPanel();
    JPanel t3=new JPanel();
    JPanel t4=new JPanel();
    JPanel t5=new JPanel();
    t5.setPreferredSize(new Dimension(30,30));
    t5.setBackground(Color.red);
    t1.add(new JButton("1"));
    t2.add(new JButton("2"));
    t3.add(new JButton("3"));
    t4.add(new JButton("4"));
    t5.add(new JButton("5"));
    this.add(t1);
    this.add(t2);
    this.add(t3);
    this.add(t4);
    this.add(t5);
    this.revalidate();
    
            
        
        
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.setPreferredSize(new Dimension(700,600));
        f.add(new JPChantiers());
        f.pack();
        f.setVisible(true);
    }
}
