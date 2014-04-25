/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Joueur;

/**
 *  Ce conteneur contient les éléments graphiques permettant d'afficher l'image du joueur
 * ainsi que le pseudo du joueur.
 * @author Anh-Djuy
 */
public class JPSousInfosJoueur extends JPanel{
    
    JLabel haut;
    JLabel bas;
    String nomJoueur;
    
    public JPSousInfosJoueur(Joueur j){
       // this.setPreferredSize(new Dimension(50,50));
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // transparance
        URL uri = JBCarte.class.getResource(image(j));
        bas = new JLabel();
        bas.setText(j.getNom());
        bas.setFont(bas.getFont ().deriveFont (14.0f));
        try {
            Image image = ImageIO.read(uri);
            haut = new JLabel(new ImageIcon(image));
            haut.setHorizontalAlignment(JLabel.CENTER);
            bas.setHorizontalAlignment(JLabel.CENTER);
            //  this.centrerTexte();
            this.add(haut, BorderLayout.NORTH);
            this.add(bas, BorderLayout.CENTER);            
        } catch (IOException ex) {
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        haut.setPreferredSize(new Dimension(60,50));
        this.setBackground(Color.LIGHT_GRAY);
        this.nomJoueur = j.getNom();
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }   
    
    /**
     * Cette méthode permet de compléter le chemin vers l'image
     * du joueur, dépendant de sa couleur.
     * @param j Le joueur 
     * @return  L'image du joueur suivant sa couleur
     */
    public String image(Joueur j){        
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
        return new String(sb);
    }
}
