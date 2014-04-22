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
        Joueur j = London.getListeJoueur().getJoueur();
        changeImage(j);
        London.getMenudroite().getJpsij().getGauche().setText(j.getNom());
    }
    
    public void changeImage(Joueur j) {
        try {
            London.getMenudroite().getJpsij().remove(London.getMenudroite().getJpsij().getDroite());
            URL uri = JBCarte.class.getResource(image(j));
            Image image = ImageIO.read(uri);
            London.getMenudroite().getJpsij().setDroite(new JLabel(new ImageIcon(image)));
            London.getMenudroite().getJpsij().add(London.getMenudroite().getJpsij().getDroite());
        } catch (IOException ex) {
            Logger.getLogger(JPnomGaucheImageDroite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
