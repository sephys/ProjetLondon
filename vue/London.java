/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Joke
 */
public class London {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        start();
            
    }
    
    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public static void start()
    {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
            JTabbedPane panel = new JTabbedPane();
            
            Plateau plateau=null;
            try
            {
               URL uri = Menu.class.getResource("../img/plateau.png");
               Image image = ImageIO.read(uri);
               plateau = new Plateau(image);

            }
            catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            }
            // ajout d'un onglet :
            
            
            panel.addTab("plateau",new JScrollPane(plateau));
            
            // ajout bouton :
            plateau.setLayout(null);
            JButton jb=new JButton("plop");
            jb.setBounds(300, 150, 50, 50);
            plateau.add(jb);
 
            frame.setContentPane(panel);
 
 
            frame.setSize(820,820);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
    
}
