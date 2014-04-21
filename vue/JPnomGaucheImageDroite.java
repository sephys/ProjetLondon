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
 *
 * @author Anh-Djuy
 */
class JPnomGaucheImageDroite extends JPanel{
    
    JLabel droite;
    JLabel gauche;
    
    public JPnomGaucheImageDroite(){
        Joueur j = London.getListeJoueur().getJoueur();
        this.setLayout(new FlowLayout());
        this.setOpaque(false); // transparance
        gauche = new JLabel();
        gauche.setText(j.getNom());
        gauche.setFont(gauche.getFont ().deriveFont (14.0f));
        droite = new JLabel();
        changeImage(j);
        droite.setHorizontalAlignment(JLabel.CENTER);
        gauche.setHorizontalAlignment(JLabel.CENTER);
        //  this.centrerTexte();
        this.add(gauche, BorderLayout.CENTER);
        this.add(droite, BorderLayout.NORTH);
        droite.setPreferredSize(new Dimension(60,50));
        this.setBackground(Color.LIGHT_GRAY);
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
        gauche.setText(j.getNom());
    }
    
    private void changeImage(Joueur j) {
        try {
            this.remove(droite);
            URL uri = JBCarte.class.getResource(image(j));
            Image image = ImageIO.read(uri);
            droite = new JLabel(new ImageIcon(image));
            this.add(droite);
        } catch (IOException ex) {
            Logger.getLogger(JPnomGaucheImageDroite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
