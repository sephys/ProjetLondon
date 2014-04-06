/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Joke
 */
public class MenuDroite extends JPanel{
    
    private JLabel joueur; // indique quel joueur joue
    private JPanel main;
    
    
    public MenuDroite()
    {
        super(new BorderLayout());
        main=new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
        
        // 1er attribut : largeur
        Dimension d=new Dimension(420,260);
        
        main.setPreferredSize(d);
        main.setBackground(Color.yellow);
        
        // a changer : dynamique
        joueur=new JLabel("Joueur 1");
        
        main.add(joueur);
        main.add(new JButton("Jouer des cartes"));
        main.add(new JButton("Restaurer la ville"));
        main.add(new JButton("Investir"));
        main.add(new JButton("Prendre trois cartes"));
        JButton jb = new JButton("Piocher");
        jb.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {  
            	London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                JPMain.ajoutCarte(London.getDeck().poll());
            }
            
        });
        main.add(jb);
        this.add(main,BorderLayout.NORTH);
        
        JPZoom zoom=new JPZoom();
        zoom.setPreferredSize(new Dimension(300,400));
        // zoom.setBackground(Color.red);
        this.add(zoom,BorderLayout.SOUTH);
        
        
    }
    
}
