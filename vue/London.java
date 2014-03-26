/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 *
 * @author Joke
 */
public class London {

    /**
     * @param args the command line arguments
     */
    
    static JFrame frame;
    public static void main(String[] args)  {
      
        start();
            
    }
    
    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public static void start()
    {
             frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
            JTabbedPane panel = new JTabbedPane();
            
            Plateau plateau=null;
            try
            {
               URL uri = London.class.getResource("../img/plateau.png");
               Image image = ImageIO.read(uri);
               plateau = new Plateau(image);

            }
            catch (IOException e1) {
            
            }
            
            
            // ajout d'un onglet :
            
            panel.addTab("plateau",new JScrollPane(plateau));
            panel.addTab("plop",new JPanel());
            
            // ajout bouton :
            plateau.setLayout(null);
            JButton jb=new JButton("plop");
            
            //listener test enter
            jb.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseExited(MouseEvent e) {
                    JPZoom.setImg("../img/plateau.png");
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    JPZoom.setImg("../img/cartes/LePauvre.png");
                    
                }
                
            }
            );
        
    
            
            
            jb.setBounds(900, 400, 50, 50);
            plateau.add(jb);
 
            frame.add(panel);
            
            MenuDroite menudroite=new MenuDroite();
            
            frame.add(menudroite,BorderLayout.EAST);
 
            frame.setSize(1000,820);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        
    }
    
}

