/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.Joueur;

/**
 *  Ce conteneur contient les éléments graphiques permettant d'accéder 
 *  aux informations relatives à un joueur.
 * @author Anh-Djuy
 */
public class JPSousInfos extends JPanel{
    
    private Image img;
    GridLayout gl;
    JPSousSousInfos piece, pauvrete, emprunt, victoire, metro;
    JPSousInfosJoueur joueur;
    
    public JPSousInfos(Joueur joueur){
        this.setLayout(new GridLayout(2, 3));
        this.setOpaque(false); // transparance
        this.joueur = new JPSousInfosJoueur(joueur);
        this.add(this.joueur);
        this.piece = new JPSousSousInfos("../img/jetons/Piece1.png", 5);
        this.pauvrete = new JPSousSousInfos("../img/jetons/PointPauvrete.png", 5);
        this.emprunt = new JPSousSousInfos("../img/jetons/10LivreEmprunt.png", 0);
        this.victoire = new JPSousSousInfos("../img/jetons/PointVictoire.png", 0);
        this.metro = new JPSousSousInfos("../img/jetons/JetonMetro.png", 0);
        this.add(this.piece);
        this.add(this.pauvrete);
        this.add(this.emprunt);
        this.add(this.victoire);
        this.add(this.metro);
        this.setBackground(joueur.getColor());        
    }    
    
    public void addPieces(int pieces){
        this.piece.ajoutNombre(pieces);
    }
    
    public void setPieces(int pieces){
        this.piece.setNombre(pieces);
    }
    
    public void addPauvrete(int pauvrete){
        this.pauvrete.ajoutNombre(pauvrete);
    }
    
    public void setPauvrete(int pauvrete){
        this.pauvrete.setNombre(pauvrete);
    }
    
    public void addEmprunt(int emprunt){
        this.emprunt.ajoutNombre(emprunt);
    }
    
    public void setEmprunt(int emprunt){
        this.emprunt.setNombre(emprunt);
    }
    
    public void addVictoire(int victoire){
        this.victoire.ajoutNombre(victoire);
    }
    
    public void setVictoire(int victoire){
        this.victoire.setNombre(victoire);
    }
    
    public void addMetro(int metro){
        this.metro.ajoutNombre(metro);
    }
    
    public void setMetro(int metro){
        this.metro.setNombre(metro);
    }
}
