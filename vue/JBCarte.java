/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Anh-Djuy
 * Bouton représentant une carte
 */
public class JBCarte extends JButton implements ActionListener{
    
    
    private Image image;
    
    public JBCarte(Image image) {
        this.image = image;
        this.setIcon(new ImageIcon(JBCarte.scaleImage(image, 77, 118)));
        this.setPreferredSize(new Dimension(77, 118));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Code repris de http://www.developpez.net permettant de redimensionner une image.
     * @param source
     * @param width
     * @param height
     * @return 
     */
    public static Image scaleImage(Image source, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }
}
