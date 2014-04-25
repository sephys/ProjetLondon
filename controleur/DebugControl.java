/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import vue.Main;

/**
 *
 * @author Anh-Djuy
 */
public class DebugControl implements ActionListener{
        String[] nomJoueurs;
        
    public void actionPerformed(ActionEvent e) {
        TourJoueur.setNbJoueur(4);
        nomJoueurs = new String[4];
        nomJoueurs[0] = "Joueur 1";
        nomJoueurs[1] = "Joueur 2";
        nomJoueurs[2] = "Joueur 3";
        nomJoueurs[3] = "Joueur 4";
        Main.getJeu().setListeJoueur(initialisationJoueur(Main.getJeu().getDeck()));
        Main.getJeu().setEtalage(new Etalage(Main.getJeu().getListeJoueur().getNbJoueur() + 1));
        Main.getJeu().start();
    }    
    
    private TourJoueur initialisationJoueur(ArrayDeque<Carte> arrayDeque) {
        int nb = TourJoueur.getNbJoueur();
        Main.getJeu().setTabJoueur(new Joueur[nb]);
        int fin = nb * 6;
        for (int i = 0; i < fin; i++) {
            switch (i % nb) {
                case 0:
                    if (Main.getJeu().getTabJoueur()[0] == null) {
                        Main.getJeu().getTabJoueur()[0] = new Joueur(nomJoueurs[0],Color.red);

                    }
                    Main.getJeu().getTabJoueur()[0].piocheCarte(arrayDeque.poll());
                    break;
                case 1:
                    if (Main.getJeu().getTabJoueur()[1] == null) {
                        Main.getJeu().getTabJoueur()[1] = new Joueur(nomJoueurs[1],Color.blue);

                    }
                    Main.getJeu().getTabJoueur()[1].piocheCarte(arrayDeque.poll());
                    break;

                case 2:
                    if (Main.getJeu().getTabJoueur()[2] == null) {
                        Main.getJeu().getTabJoueur()[2] = new Joueur(nomJoueurs[2],Color.GREEN);

                    }
                    Main.getJeu().getTabJoueur()[2].piocheCarte(arrayDeque.poll());
                    break;
                case 3:
                    if (Main.getJeu().getTabJoueur()[3] == null) {
                        Main.getJeu().getTabJoueur()[3] = new Joueur(nomJoueurs[3],Color.yellow);

                    }
                    Main.getJeu().getTabJoueur()[3].piocheCarte(arrayDeque.poll());
                    break;
            }
        }
        //choix hasard premier joueur
        int indice = (int) (Math.random() * (nb - 1)); //borne [0.. nbjoueur-1]
        TourJoueur first = null;
        TourJoueur tmp = null;
        for (int i = 0; i < nb; i++) {
            //implementer structure cyclique
            TourJoueur current;
            switch ((indice + i) % nb) {
                case 0:
                    current = new TourJoueur(Main.getJeu().getTabJoueur()[0]);
                    Main.getJeu().getTabJoueur()[0].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;
                case 1:
                    current = new TourJoueur(Main.getJeu().getTabJoueur()[1]);
                    Main.getJeu().getTabJoueur()[1].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;

                case 2:
                    current = new TourJoueur(Main.getJeu().getTabJoueur()[2]);
                    Main.getJeu().getTabJoueur()[2].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;
                case 3:
                    current = new TourJoueur(Main.getJeu().getTabJoueur()[3]);
                    Main.getJeu().getTabJoueur()[3].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;
            }

        }
        tmp.setSuivant(first);
        return first;
        // TODO Auto-generated method stub

    }
}
