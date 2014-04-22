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
public class JPnomGaucheImageDroite extends JPanel{
    
    JLabel droite;
    JLabel gauche;
    
    public JPnomGaucheImageDroite(){
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        this.setLayout(new FlowLayout());
        this.setOpaque(false); // transparance
        gauche = new JLabel();
        gauche.setText(j.getNom());
        gauche.setFont(gauche.getFont ().deriveFont (14.0f));
        droite = new JLabel();
        
        droite.setHorizontalAlignment(JLabel.CENTER);
        gauche.setHorizontalAlignment(JLabel.CENTER);
        //  this.centrerTexte();
        this.add(gauche, BorderLayout.CENTER);
        this.add(droite, BorderLayout.NORTH);
        droite.setPreferredSize(new Dimension(60,50));
        this.setBackground(Color.LIGHT_GRAY);
    }

    public JLabel getDroite() {
        return droite;
    }

    public JLabel getGauche() {
        return gauche;
    }

    public void setDroite(JLabel droite) {
        this.droite = droite;
    }

    public void setGauche(JLabel gauche) {
        this.gauche = gauche;
    }
    
    
    

}
