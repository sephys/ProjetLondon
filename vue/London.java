/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    
    public static void start()
    {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            Plateau imagePanel=null;
            try
            {
               imagePanel = new Plateau(ImageIO.read(new File("./../img/plateau.png")));

            }
            catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            }
            panel.add(new JScrollPane(imagePanel), BorderLayout.CENTER);
 
         
 
            frame.setContentPane(panel);
 
 
            frame.setSize(820,820);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
    
}
