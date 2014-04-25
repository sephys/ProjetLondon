/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Joueur;
import model.TourJoueur;

/**
 *
 * @author Anh-Djuy
 */
public class JPInfos extends JPanel {

    JPSousInfos[] aljpsi; // faudra mettre les getter et setter
    private Image img;

    public JPInfos(Joueur listeJoueurs[]) {

        aljpsi = new JPSousInfos[TourJoueur.getNbJoueur()];


        this.setPreferredSize(new Dimension(280, 810));
        this.setLayout(new GridLayout(4, 1, 0, 20));
        for (int i = 0; i < listeJoueurs.length; i++) {
            JPSousInfos jps = new JPSousInfos(listeJoueurs[i]);
            aljpsi[i] = jps;
            this.add(jps);
        }

        try {
            URL uri = JPEtalage.class.getResource("../img/lfbord.png");
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode de mise a jour du panel d'infos pour tous les joueurs
     */
    public void maj_infos() {
        for (int j = 0; j<Main.getJeu().getTabJoueur().length; j++) {
            Joueur joueur = Main.getJeu().getTabJoueur()[j];
            for (int i = 0; i < aljpsi.length; i++) {
                if (aljpsi[i].joueur.getNomJoueur().equals(joueur.getNom())) {
                    aljpsi[i].setEmprunt(joueur.getNbPret());
                    aljpsi[i].setPieces(joueur.getArgent());
                    aljpsi[i].setPauvrete(joueur.getPointPauvrete());
                    aljpsi[i].setVictoire(joueur.getPointVictoire());
                    //LE NOMBRE DE METRO NE SE MET PAS A JOUR DANS CETTE FONCTION CAR INEXISTANT DANS LA CLASSE JOUEUR
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 280, 810, this);
    }

}
