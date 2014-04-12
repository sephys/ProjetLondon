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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Carte;
import model.Constructible;
import model.Etalage;

/**
 *
 * @author Anh-Djuy Bouton représentant une carte
 */
public class JBCarte extends JButton implements  MouseListener {

    private Image image;
    private Carte carte;
    private static boolean doubleClick; // permet de savoir si on autorise le double click pour la défausse -> étalage

    public JBCarte(Carte carte) {
        this.carte=carte;
        URL uri = JBCarte.class.getResource(carte.getPath()); 
        try {
        	System.out.println(carte.getPath());
            image = ImageIO.read(uri);
        } catch (IOException ex) {
        	System.out.println(carte.getPath());
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIcon(new ImageIcon(JBCarte.scaleImage(image, 67, 103)));
        this.setPreferredSize(new Dimension(79, 121));
        // D&D
        DragGestureRecognizer dragRecognizer1 = London.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, London.dndListener);
        
        this.addMouseListener(this);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JBCarte other = (JBCarte) obj;
        if (!Objects.equals(this.carte, other.carte)) {
            return false;
        }
        return true;
    }
    
    

   // pour poser une carte sur l'etalage
    @Override
    public void mouseClicked(MouseEvent e) {
        JBCarte jb=(JBCarte) e.getComponent(); // jb =bout
        if(e.getClickCount()==2)
        {
           if(doubleClick)
           {
               // ajout de la carte dans l'etalage
               London.getEtalage().addCarte(jb.getCarte());
               // on rafrachit l'etalage
               London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
               // on enleve la carte de la main du joueur ( graphiquement )
               London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(jb.carte);
               //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
               // j'aimerai supprmer la carte de la main du joeuur , mais ca ne fonctionne pas, il me supprime rien.
               London.getListeJoueur().getJoueur().getMain().remove(((JBCarte) e.getComponent()).carte);
               //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
               London.getListeJoueur().getJoueur().defausseMoins();
               
               
           }
           else
           {
               JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");

           }
           
        }
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
        JPZoom.setImg(carte.getPath());

    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public static boolean isDoubleClick() {
        return doubleClick;
    }

    public static void setDoubleClick(boolean doubleClick) {
        JBCarte.doubleClick = doubleClick;
    }
    
    
    
    
    
}
