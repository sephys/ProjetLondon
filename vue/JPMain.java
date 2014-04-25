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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Carte;
import model.Joueur;

/**
 *
 * @author Anh-Djuy
 *
 * Classe JPMain
 *
 * Cette classe permet d'afficher la main d'un joueur.
 *
 */
public class JPMain extends JPanel {
    
    private JScrollPane JSPMain;
    private JPanel main;
    private boolean goEtalage; // est-ce que le joueur peut poser une carte sur l'etalage
    private Image img;
    
    public JPMain(){
        super(new BorderLayout());
        main = new JPanel(new FlowLayout());
        main.setBackground(Color.red);
        JSPMain = new JScrollPane(main);
        JSPMain.setPreferredSize(new Dimension(675,131));
        this.add(JSPMain, BorderLayout.NORTH);
        
        
        
        //// D&D
        DropTarget dropTarget1 = new DropTarget(main, DnDConstants.ACTION_MOVE,
                London.dndListener);
    }
    
    public JPMain(Joueur j)
    {
        super(new BorderLayout());
        
        try {
            URL uri = JPEtalage.class.getResource("../img/mainBG1.png");
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        main = new JPanel(new FlowLayout()){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, 855, 147, this);
            }
        };
        // main.setBackground(Color.red);
        JSPMain = new JScrollPane(main);
        JSPMain.setPreferredSize(new Dimension(675,150));
        JSPMain.setWheelScrollingEnabled(true);
        
        
        this.add(JSPMain, BorderLayout.NORTH);
        //// D&D
        DropTarget dropTarget1 = new DropTarget(main, DnDConstants.ACTION_MOVE,
                London.dndListener);
        
        for(int i=0;i<j.getMain().size();i++)
        {
            main.add(new JBCarte(j.getCarteMain(i)));
        }
        
    }
    
    
    
    // ajout d'une carte dans le panel main du joueur
    public  void ajoutCarte(Carte e){
        JBCarte carte = new JBCarte(e);
        carte.changeTailleBoutonImage(new Dimension(79, 121));
        main.add(carte);
        main.revalidate();
    }
    
    public JPanel getMain()
    {
        return this.main;
    }
    
    public void removeCarte(Carte e)
    {
        JBCarte c=new JBCarte(e);
        boolean b = true;   // Pour qu'une seule carte soit supprimée et pas toutes la cartes similaires à la Carte e.
        for(int i=0;i<main.getComponentCount()&&b == true;i++)
        {
            if(((JBCarte) main.getComponent(i)).equals(c))
            {
                main.remove(main.getComponent(i));
                b = false;
            }
        }
        
        main.revalidate();
        
    }
    
    public void removeCarteNom(String s){
        boolean b = true;   // Pour qu'une seule carte soit supprimée et pas toutes la cartes similaires à la Carte e.
        for(int i=0;i<main.getComponentCount()&&b == true;i++)
        {
            if(((JBCarte) main.getComponent(i)).getCarte().getNom().equals(s))
            {
                main.remove(main.getComponent(i));
                b = false;
            }
        }        
        main.revalidate();
        
    }
}
