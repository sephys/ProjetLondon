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
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Anh-Djuy Bouton représentant une carte
 */
public class JBCarte extends JButton implements ActionListener, MouseListener {

    private Image image;
    private String imagePath;

    public JBCarte(String imagePath) {
        this.imagePath=imagePath;
        URL uri = JBCarte.class.getResource(imagePath); 
        try {
            image = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIcon(new ImageIcon(JBCarte.scaleImage(image, 77, 118)));
        this.setPreferredSize(new Dimension(77, 118));
        // D&D
        DragGestureRecognizer dragRecognizer1 = London.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, London.dndListener);
        
        this.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Code repris de http://www.developpez.net permettant de redimensionner une
     * image.
     *
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPZoom.setImg("../img/cartes/Background.png");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPZoom.setImg(this.imagePath);

    }
}
