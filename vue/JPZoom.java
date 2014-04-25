/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Joke panel contenant le zoom de la carte : affiche l'image sur
 * laquelle on pointe
 */
public class JPZoom extends JPanel {

    private static Image img; // image de fond = une carte

    public JPZoom() {
        super();
        setImg("../img/cartes/Background.png");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 300, 400, this);
    }

    public Image getImg() {
        return img;

    }

    public static void setImg(String url) {
        URL uri = JPZoom.class.getResource(url);

        try {
            img = ImageIO.read(uri);
            Main.getJeu().getFrame().repaint();
        } catch (IOException e1) {

        }
    }

}
