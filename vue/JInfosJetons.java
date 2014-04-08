/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author FT
 */
public class JInfosJetons extends JPanel{
    JLabel haut;
    JLabel bas;
    
    public JInfosJetons() throws IOException{
        bas = new JLabel();
        URL uri = JBCarte.class.getResource("../img/jetons/Piece1.png");
        Image image = ImageIO.read(uri);
        haut = new JLabel(new ImageIcon(image));
        haut.setHorizontalAlignment(JLabel.CENTER);
        bas.setHorizontalAlignment(JLabel.CENTER);
        this.add(haut, BorderLayout.NORTH);
        this.add(bas, BorderLayout.CENTER);
    }
}
