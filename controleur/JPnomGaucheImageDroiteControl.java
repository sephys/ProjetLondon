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
import vue.London;
import vue.Main;

/**
 *
 * @author Joke
 */
public class JPnomGaucheImageDroiteControl {
    
    public JPnomGaucheImageDroiteControl()
    {
        super();
    }
    
    
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
    
    public void actualiseJoueur(){
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        changeImage(j);
        Main.getJeu().getMenudroite().getJpsij().getGauche().setText(j.getNom());
    }
    
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
}
