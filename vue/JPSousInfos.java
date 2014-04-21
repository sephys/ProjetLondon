/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.Joueur;

/**
 *  Penser à ajouter un gridlayout
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
        changeImageJoueur(joueur);
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

    private void changeImageJoueur(Joueur j) {
        StringBuilder sb = new StringBuilder("../img/");
        Color c = j.getColor();
        if(c == Color.yellow){
            sb.append("jaune.png");
        }else{
            if(c == Color.blue){
                sb.append("bleu.png");
            }else{
                if(c == Color.red){
                    sb.append("rouge.png");
                }else{
                    sb.append("vert.png");
                }
            }
        }
        this.joueur = new JPSousInfosJoueur(new String(sb), j.getNom());
        this.add(this.joueur);
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
    
    
    /**
     * Fonction pour insérer une image dans un JTextPane avec du texte
     * reprise de developpez.net
     * @param textpane
     */    
    private static void initialiseLigneTexte(JTextPane textpane, String filePath, String valeur) {
        try {
            StyledDocument document = (StyledDocument)textpane.getDocument();
            Style imageStyle = document.addStyle("ImageStyle", null);
            URL uri = JPMain.class.getResource(filePath);
            Image image = ImageIO.read(uri);
            StyleConstants.setIcon(imageStyle, new ImageIcon(image)); // exemple de path d'image
            document.insertString(document.getLength(), "\uFFFC", imageStyle);  // insertion de l'image dans le texte, ici j'utilise un caractère de remplacement qui est \uFFFC, mais tu peux utiliser n'importe quelle chaîne, si tu as besoin de reconnaître l'image dans le texte (par exemple [IMG=note.png]) )
            document.insertString(document.getLength(), " : " + valeur, null); // texte normal dans le style de base
        } catch (BadLocationException e){
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(JPInfos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
