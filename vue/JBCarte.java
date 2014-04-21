/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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
public class JBCarte extends JButton implements MouseListener {

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

        this.addMouseListener(this);
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
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            Joueur courrant = London.getListeJoueur().getJoueur();
            switch (((JBCarte) e.getComponent()).getPosition()) {
                case "main": // on met la carte de la main sur le l'étalage
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                    // cas ou defausse car trop de cartes dans la main
                    if (London.getListeJoueur().getJoueur().getDefausse() != 0 && London.getListeJoueur().getJoueur().getLastCarte() == null) {

                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
                        // ajout de la carte dans l'etalage
                        London.getEtalage().addCarte(carte.carte);
                        // on rafrachit l'etalage
                        London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                        // on enleve la carte de la main du joueur ( graphiquement )
                        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).carte);
                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());

                        // suppression de la carte de la main du joueur
                        London.getListeJoueur().getJoueur().getMain().remove(carte.carte);
                        
                        //on enlève la défausse
                        London.getListeJoueur().getJoueur().defausseMoins();

                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        //London.getListeJoueur().getJoueur().payeConstruction(carte.carte);
                    } // cas defausse à cause d'une construction
                    else if (London.getListeJoueur().getJoueur().getDefausse() != 0) {
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");

                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        // si carte ne respecte pas les contraintes de defausse
                        if (London.getListeJoueur().getJoueur().payeConstruction(carte.carte) == false) {
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                        } else {
                            // ajout de la carte dans l'etalage
                            London.getEtalage().addCarte(carte.carte);
                            // on rafrachit l'etalage
                            London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                            // on enleve la carte de la main du joueur ( graphiquement )
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).carte);
                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());

                            // suppression de la carte de la main du joueur
                            London.getListeJoueur().getJoueur().getMain().remove(carte.carte);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;

                case "etalage": // on met la carte de l'etalage dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (London.getListeJoueur().getJoueur().getPioche() != 0) {
                        // ajout de la carte dans la main du joueur graphiquement
                        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).carte);
                        // ajout de la carte das la main du joueur
                        London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).carte);
                        // suppresssion de la carte dans l'etalage
                        London.getEtalage().piocherCarte(((JBCarte) e.getComponent()).carte);
                        // on rafraichit l'étalage
                        London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                        London.getListeJoueur().getJoueur().piocheMoins();

                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas prendre de cette carte");
                    }
                    break;

                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (doubleClick) {

                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir choisir \"" + this.getCarte().getNom() + "\" ?",
                                "Piocher",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            London.getMenudroite().getF().dispose();
                            London.getMenudroite().getF().remetCartes(this);
                            // ajout de la carte dans la main du joueur graphiquement
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).carte);
                            // ajout de la carte das la main du joueur
                            London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).carte);
                            London.getListeJoueur().getJoueur().piocheMoins();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;

                case "construction": // on active une carte qui est dans la zone de construction
                    if (JBCarte.activerCarte) {
                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir activer cette carte ?",
                                "Activer la carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            London.getListeJoueur().getJoueur().activerCarte(((Constructible) ((JBCarte) e.getComponent()).carte));

                            // on check si la carte doit être retourné
                            if (((Constructible) ((JBCarte) e.getComponent()).carte).isARetourne()) {
                                this.changerImage("../img/cartes/Background.png");
                            }
                            London.getInfos().maj_infos();
                            London.getListeJoueur().getJoueur().setFinitTour(true);

                        }

                    }
                    break;

            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (JBCarte.clicDroitJouer) {
                if (carte.getClass() == NonConstructible.class) {
                    if (!"Paupers".equals(carte.getNom())) {
                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir jouer cette carte ?",
                                "Jouer carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            System.out.println(carte.getNom());
                            London.getListeJoueur().getJoueur().jouerCarte(null, carte, 0);
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).carte);
                            if (London.getListeJoueur().getJoueur().getPouvoir().get("Wren") == 1) {
                                London.getMenudroite().getLabelInfo().setText("<html>[Pouvoir Wren] Posez deux cartes sur votre<br/>chantier sans devoir défausser des cartes</html>");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Paupers ne peux pas être jouée");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cette carte est constructible'");
                }
            } else {

                JOptionPane.showMessageDialog(null, "Vous devez choisir l'action 'Jouer des cartes'");

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

    public Image scaleImage(Image source, int width, int height) {

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
            ArrayList al = new ArrayList(London.getListeJoueur().getJoueur().getListeChantier());
            for (Object o : al) {
                ArrayDeque<Constructible> a = (ArrayDeque<Constructible>) o;
                if (a.poll().getCouleur().equals("marron")) {
                    argent++;
                }
            }
        }
        London.getListeJoueur().getJoueur().addArgent(argent);
    }

    public void pouvoirBrixtonPrison(int nombreCartes) {
        London.getListeJoueur().getJoueur().addPointPauvrete(-nombreCartes);
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
}
