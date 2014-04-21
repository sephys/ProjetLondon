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

/**
 *
 * @author Anh-Djuy
 */
public class JPSousInfosJoueur extends JPanel{
    
    JLabel haut;
    JLabel bas;
    String nomJoueur;
    
    public JPSousInfosJoueur(String imagePath, String nomJoueur){
       // this.setPreferredSize(new Dimension(50,50));
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // transparance
        URL uri = JBCarte.class.getResource(imagePath);
        bas = new JLabel();
        bas.setText(nomJoueur);
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
        this.nomJoueur = nomJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }   
}
