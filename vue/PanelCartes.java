/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Carte;

/**
 *
 * @author Anh-Djuy
 */
class PanelCartes extends JPanel {
    
    int leftPadding;
    Image image;

    PanelCartes(int leftPadding, Image image) {
        this.leftPadding = leftPadding;
        this.image = image;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
