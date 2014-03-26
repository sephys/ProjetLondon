/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import sun.awt.image.ImageAccessException;

/**
 *
 * @author Anh-Djuy
 */
public class JPMain extends JPanel {
    
    private JScrollPane JSPMain;
    private static JPanel main;
    
    public JPMain(){
        super(new BorderLayout());
        main = new JPanel(new FlowLayout());
        main.setBackground(Color.red);
        main.setPreferredSize(new Dimension(820,290));
        JSPMain = new JScrollPane(main);
        JSPMain.setPreferredSize(new Dimension(820,308));
        this.add(JSPMain, BorderLayout.NORTH);
        main.add(new JButton("ponyta"));
        main.add(new JButton("ponyta"));
        main.add(new JButton("ponyta"));
        main.add(new JButton("ponyta"));
        try {
            URL uri = JPMain.class.getResource("../img/cartes/LePauvre.png");
            Image image = ImageIO.read(uri);
            main.add(new JBCarte(image));
        } catch (IOException ex) {
            Logger.getLogger(JPMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ajoutCarte(){
        try {
            URL uri = JPMain.class.getResource("../img/cartes/LePauvre.png");
            Image image = ImageIO.read(uri);
            main.add(new JBCarte(image));
        } catch (IOException ex) {
            Logger.getLogger(JPMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
