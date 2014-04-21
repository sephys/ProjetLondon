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
import javax.swing.JButton;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import vue.London;

/**
 *
 * @author Anh-Djuy
 */
public class Debug implements ActionListener{
        String[] nomJoueurs;
        
    public void actionPerformed(ActionEvent e) {
        Joueur.setNbJoueur(4);
        nomJoueurs = new String[4];
        nomJoueurs[0] = "Joueur 1";
        nomJoueurs[1] = "Joueur 2";
        nomJoueurs[2] = "Joueur 3";
        nomJoueurs[3] = "Joueur 4";
        London.setListeJoueur(initialisationJoueur(London.getDeck()));
        London.setEtalage(new Etalage(London.getListeJoueur().getNbJoueur() + 1));
        London.start();
    }    
    
    private TourJoueur initialisationJoueur(ArrayDeque<Carte> arrayDeque) {
        int nb = Joueur.getNbJoueur();
        London.setTabJoueur(new Joueur[nb]);
        int fin = nb * 6;
        for (int i = 0; i < fin; i++) {
            switch (i % nb) {
                case 0:
                    if (London.getTabJoueur()[0] == null) {
                        London.getTabJoueur()[0] = new Joueur(nomJoueurs[0],Color.red);

                    }
                    London.getTabJoueur()[0].piocheCarte(arrayDeque.poll());
                    break;
                case 1:
                    if (London.getTabJoueur()[1] == null) {
                        London.getTabJoueur()[1] = new Joueur(nomJoueurs[1],Color.blue);

                    }
                    London.getTabJoueur()[1].piocheCarte(arrayDeque.poll());
                    break;

                case 2:
                    if (London.getTabJoueur()[2] == null) {
                        London.getTabJoueur()[2] = new Joueur(nomJoueurs[2],Color.GREEN);

                    }
                    London.getTabJoueur()[2].piocheCarte(arrayDeque.poll());
                    break;
                case 3:
                    if (London.getTabJoueur()[3] == null) {
                        London.getTabJoueur()[3] = new Joueur(nomJoueurs[3],Color.yellow);

                    }
                    London.getTabJoueur()[3].piocheCarte(arrayDeque.poll());
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
                    current = new TourJoueur(London.getTabJoueur()[0]);
                    London.getTabJoueur()[0].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;
                case 1:
                    current = new TourJoueur(London.getTabJoueur()[1]);
                    London.getTabJoueur()[1].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;

                case 2:
                    current = new TourJoueur(London.getTabJoueur()[2]);
                    London.getTabJoueur()[2].setPlaceJoueur(i);
                    if (tmp != null) {
                        tmp.setSuivant(current);
                    } else {
                        first = current;
                    }
                    tmp = current;
                    break;
                case 3:
                    current = new TourJoueur(London.getTabJoueur()[3]);
                    London.getTabJoueur()[3].setPlaceJoueur(i);
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
