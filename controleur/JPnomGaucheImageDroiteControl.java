/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Joueur;
import vue.JBCarte;
import vue.JPnomGaucheImageDroite;
import vue.Main;

/**
 *  Cette classe permet de gérer le JPnomGaucheImageDroite 
 *  (Panel avec le nom du joueur et son image en haut à droite ?)
 * @author Joke
 */
public class JPnomGaucheImageDroiteControl {
    
    public JPnomGaucheImageDroiteControl(){
        super();
    }
    
    /**
     *  Cette méthode retourne la suite du chemin de l'image suivant le joueur
     * @param j Joueur concerné 
     * @return  Chemin vers l'image
     */
    public String image(Joueur j){
        StringBuilder sb = new StringBuilder("../img/");
        Color c = j.getColor();
        /*  Suivant la couleur associée au joueur, le chemin associé à l'image
            est ajouté.
        */
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
    
    /**
     * Cette méthode change l'image du joueur entré en paramètre.
     * @param j Joueur concerné par le changement d'image
     */
    public void changeImage(Joueur j) {
        try {
            Main.getJeu().getMenudroite().getJpsij().remove(Main.getJeu().getMenudroite().getJpsij().getDroite());
            URL uri = JBCarte.class.getResource(image(j));
            Image image = ImageIO.read(uri);
            Main.getJeu().getMenudroite().getJpsij().setDroite(new JLabel(new ImageIcon(image)));
            Main.getJeu().getMenudroite().getJpsij().add(Main.getJeu().getMenudroite().getJpsij().getDroite());
        } catch (IOException ex) {
            Logger.getLogger(JPnomGaucheImageDroite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  Cette méthode change le nom et l'image du JPnomGaucheImageDroite pour le joueur suivant.
     */
    public void actualiseJoueur(){
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        changeImage(j);
        Main.getJeu().getMenudroite().getJpsij().getGauche().setText(j.getNom());
    }
}
