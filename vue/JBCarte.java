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
import java.util.ArrayDeque;
import java.util.ArrayList;
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
import model.Joueur;

/**
 *
 * @author Anh-Djuy Bouton représentant une carte
 */
public class JBCarte extends JButton implements  MouseListener {
    
    private Image image;
    private Carte carte;
    private static boolean doubleClick = false; // permet de savoir si on autorise le double click pour la défausse -> étalage

    private String position; // permet de savoir ou est la carte : main - etalage - construction
    private boolean defausse; // est-ce que la carte pour etre defauser


    private boolean retournee;
    

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
        this.position="main";
        this.retournee = false;
        this.setIcon(new ImageIcon(JBCarte.scaleImage(image, 79, 121)));
        this.setPreferredSize(new Dimension(79, 121));
        // D&D
        DragGestureRecognizer dragRecognizer1 = London.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, London.dndListener);
        
        this.addMouseListener(this);
    }
    
    public void changeTailleBoutonImage(Dimension d){
        this.setPreferredSize(d);
        this.setIcon(new ImageIcon(JBCarte.scaleImage(image, (int)d.getWidth(), (int)d.getHeight())));
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
        
        if(e.getClickCount()==2)
        {

           Joueur courrant = London.getListeJoueur().getJoueur();
               switch (((JBCarte) e.getComponent()).getPosition()) {
                   case "main": // on met la carte de la main sur le l'étalage
                       //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                      if(London.getListeJoueur().getJoueur().getDefausse()!=0 && ((JBCarte) e.getComponent()).isDefausse())
                      {

                        /**/
                        London.getListeJoueur().getJoueur().setPayeConstruction(false);
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
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        London.getListeJoueur().getJoueur().defausseMoins();
                        
                        
                        
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
                    
                    
                case "etalage": // on met la carte de l'etalage dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if(London.getListeJoueur().getJoueur().getPioche()!=0)
                    {
                        // ajout de la carte dans la main du joueur graphiquement
                        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).carte);
                        // ajout de la carte das la main du joueur
                        London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).carte);
                        // suppresssion de la carte dans l'etalage
                        London.getEtalage().piocherCarte(((JBCarte) e.getComponent()).carte);
                        // on rafraichit l'étalage
                        London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                        London.getListeJoueur().getJoueur().piocheMoins();
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas prendre de cette carte");
                    }
                    break;
                    
                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if(doubleClick)
                    {
                        
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
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
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
    
    
    
    

    
    public void pouvoirBridge(){
        int argent = 0;
        if(this.carte.getNom().equals("Bridge")){
            ArrayList al = new ArrayList(London.getListeJoueur().getJoueur().getListeChantier());
            for(Object o : al){
                ArrayDeque<Constructible> a = (ArrayDeque<Constructible>) o;
                if(a.poll().getCouleur().equals("marron")){
                    argent++;
                }
            }
        }
        London.getListeJoueur().getJoueur().addArgent(argent);
    }
    
    public void pouvoirBrixtonPrison(int nombreCartes){
        London.getListeJoueur().getJoueur().addPointPauvrete(-nombreCartes);
    }
}
