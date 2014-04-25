
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Joueur;

/**
 *
 * @author Anh-Djuy
 * Panel en haut du menu de droite, contenant le label du joueur qui joue et son image
 */
public class JPnomGaucheImageDroite extends JPanel{
    
    JLabel droite;
    JLabel gauche;
    
    public JPnomGaucheImageDroite(){
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        this.setLayout(new FlowLayout());
        this.setOpaque(false); // transparence
        gauche = new JLabel();
        gauche.setText(j.getNom());
        gauche.setFont(gauche.getFont ().deriveFont (14.0f));
        droite = new JLabel();
        
        droite.setHorizontalAlignment(JLabel.CENTER);
        gauche.setHorizontalAlignment(JLabel.CENTER);
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
