/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.JBCarteControl;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;

/**
 *
 * @author Anh-Djuy Bouton représentant une carte
 */
public class JBCarte extends JButton{

    private Image image;
    private Carte carte;
    private static boolean doubleClick = false; // permet de savoir si on autorise le double click pour la défausse -> étalage
    private static boolean clicDroitJouer = false; /* Autorise le clic droit pour jouer la carte depuis la main */

    private static boolean activerCarte = false; // savoir si le joueur peut activer des cartes

    private String position; // permet de savoir ou est la carte : main - etalage - construction
    private boolean defausse; // est-ce que la carte pour etre defauser
    // private boolean retournee;

    public JBCarte(Carte carte) {
        this.carte = carte;
        URL uri = JBCarte.class.getResource(carte.getPath());
        try {
            System.out.println(carte.getPath());
            image = ImageIO.read(uri);
        } catch (IOException ex) {
            System.out.println(carte.getPath());
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.position = "main";

        // this.retournee = false;
        this.setIcon(new ImageIcon(scaleImage(image, 79, 121)));
        this.setPreferredSize(new Dimension(79, 121));
        // D&D
        DragGestureRecognizer dragRecognizer1 = London.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, London.dndListener);

        this.addMouseListener(new JBCarteControl());
    }

    public void changeTailleBoutonImage(Dimension d) {
        this.setPreferredSize(d);

        this.setIcon(new ImageIcon(scaleImage(image, (int) d.getWidth(), (int) d.getHeight())));

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
   

    /**
     * Code repris de http://www.developpez.net permettant de redimensionner une
     * image.
     *
     * @param source
     * @param width
     * @param height
     * @return
     */
    public Image scaleImage(Image source, int width, int height) {

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }

    public static boolean isClicDroitJouer() {
        return clicDroitJouer;
    }

    public static void setClicDroitJouer(boolean clicDroitJouer) {
        JBCarte.clicDroitJouer = clicDroitJouer;
    }

    public static boolean isActiverCarte() {
        return activerCarte;
    }

    public static void setActiverCarte(boolean activerCarte) {
        JBCarte.activerCarte = activerCarte;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static boolean isDoubleClick() {
        return doubleClick;
    }

    public static void setDoubleClick(boolean doubleClick) {
        JBCarte.doubleClick = doubleClick;
    }

    public void setDefausse(boolean defausse) {
        this.defausse = defausse;
    }

    public boolean isDefausse() {
        return defausse;
    }

    public void pouvoirBridge() {
        int argent = 0;
        if (this.carte.getNom().equals("Bridge")) {
            ArrayList al = new ArrayList(Main.getJeu().getListeJoueur().getJoueur().getListeChantier());
            for (Object o : al) {
                ArrayDeque<Constructible> a = (ArrayDeque<Constructible>) o;
                if (a.poll().getCouleur().equals("marron")) {
                    argent++;
                }
            }
        }
        Main.getJeu().getListeJoueur().getJoueur().addArgent(argent);
    }

    public void pouvoirBrixtonPrison(int nombreCartes) {
        Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-nombreCartes);
    }

    public void changerImage(String path) {
        URL uri = JBCarte.class.getResource(path);
        try {
            image = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIcon(new ImageIcon(scaleImage(image, 122, 168)));
    }
    
    public static boolean getDoubleClick(){
        return JBCarte.doubleClick;
    }
}
