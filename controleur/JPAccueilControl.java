/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import model.Zone;
import vue.Main;

/**
 * Ce Listener permet de gérer le bouton Jouer de la fenêtre d'accueil.
 *
 * @author Joke
 */
public class JPAccueilControl implements ActionListener {

    String[] nomJoueurs; // tableau de noms permettant l'initialisation

    public JPAccueilControl() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Frame choix du joueur
        final JFrame nbJoueur = new JFrame();
        nbJoueur.setLayout(new GridLayout(4, 1));
        nbJoueur.setSize(300, 200);
        nbJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Permet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();

        JRadioButton j1 = new JRadioButton("2 joueurs");
        JRadioButton j2 = new JRadioButton("3 joueurs");
        JRadioButton j3 = new JRadioButton("4 joueurs");

        // Ajout des listeners sur les radio boutonsS
        j1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TourJoueur.setNbJoueur(2);
            }

        });
        j2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TourJoueur.setNbJoueur(3);
            }

        });
        j3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TourJoueur.setNbJoueur(4);
            }

        });

        bg.add(j1);
        bg.add(j2);
        bg.add(j3);

        nbJoueur.add(j1);
        nbJoueur.add(j2);
        nbJoueur.add(j3);

        // Deuxième bouton jouer pour lancer la partie
        JButton p = new JButton("Jouer");

        p.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nbJoueur.dispose();

                // Affichage des noms des joueurs
                nomJoueurs = new String[TourJoueur.getNbJoueur()];
                int i;
                for (i = 0; i < TourJoueur.getNbJoueur(); i++) {
                    String nom = JOptionPane.showInputDialog("Nom du joueur " + (i + 1));
                    // L'utilisateur a appuyé sur la croix ou bien sur cancel
                    if (nom == null) {
                        break;
                    }
                    nomJoueurs[i] = nom;
                }
                if (i == TourJoueur.getNbJoueur()) // L'utilisateur a rentré tous les nom
                {
                    Main.getJeu().setListeJoueur(initialisationJoueur(Main.getJeu().getDeck()));
                    Main.getJeu().setEtalage(new Etalage(TourJoueur.getNbJoueur() + 1));

                    Main.getJeu().start();
                }
            }
        });
        nbJoueur.add(p);
        nbJoueur.setLocationRelativeTo(null);
        nbJoueur.setVisible(true);

    }

    /**
     * Darin, tu me commentes ça ?
     *
     * @param arrayDeque
     * @return
     */
    public TourJoueur initialisationJoueur(ArrayDeque<Carte> arrayDeque) {
        int nb = TourJoueur.getNbJoueur();
        Main.getJeu().setTabJoueur(new Joueur[nb]);
        int fin = nb * 6;
        for (int i = 0; i < fin; i++) {
            switch (i % nb) {
                case 0:
                    if (Main.getJeu().getTabJoueur()[0] == null) {
                        Main.getJeu().getTabJoueur()[0] = new Joueur(nomJoueurs[0], Color.red);

                    }
                    Main.getJeu().getTabJoueur()[0].piocheCarte(arrayDeque.poll());
                    break;
                case 1:
                    if (Main.getJeu().getTabJoueur()[1] == null) {
                        Main.getJeu().getTabJoueur()[1] = new Joueur(nomJoueurs[1], Color.blue);

                    }
                    Main.getJeu().getTabJoueur()[1].piocheCarte(arrayDeque.poll());
                    break;

                case 2:
                    if (Main.getJeu().getTabJoueur()[2] == null) {
                        Main.getJeu().getTabJoueur()[2] = new Joueur(nomJoueurs[2], Color.GREEN);

                    }
                    Main.getJeu().getTabJoueur()[2].piocheCarte(arrayDeque.poll());
                    break;
                case 3:
                    if (Main.getJeu().getTabJoueur()[3] == null) {
                        Main.getJeu().getTabJoueur()[3] = new Joueur(nomJoueurs[3], Color.yellow);

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
    }
}
