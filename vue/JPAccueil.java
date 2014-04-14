/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import model.Zone;

/**
 *
 * @author Joke
 */
public class JPAccueil extends JPanel {

    private Image img; // image de fond
    private boolean lancer = false; // savoir si on peut démarrer le jeu
    private String[] nomJoueurs; // tableau de noms permettant l'initialisation

    public JPAccueil() {
        super();
        this.setLayout(null);

        //deck
        Carte.initDeck();
        Zone.initZone();

        // image de fond
        try {
            URL uri = JPZoom.class.getResource("../img/accueil.png");
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }

        // --- BOUTON DEBUG A SUPPRIMER A LA FIN 
        JButton jbDebug = new JButton("Debug");
        jbDebug.addActionListener(new ActionListener() {

            @Override
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
        });

        // --- FIN BOUTON DEBUG A SUPPRIMER A LA FIN 
        JButton regle = new JButton("Règles");
        regle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // fichier règles
                URL uri = JPAccueil.class.getResource("../fichier/regle.pdf");
                try {
                    Desktop.getDesktop().open(new File(uri.getPath()));
                } catch (IOException ex) {
                    Logger.getLogger(JPAccueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        JButton play = new JButton("Jouer");
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // frame choix du joueur
                final JFrame nbJoueur = new JFrame();
                nbJoueur.setLayout(new GridLayout(4, 1));
                nbJoueur.setSize(300, 200);
                nbJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                // premet de lier les radio boutons
                ButtonGroup bg = new ButtonGroup();

                JRadioButton j1 = new JRadioButton("2 joueurs");
                JRadioButton j2 = new JRadioButton("3 joueurs");
                JRadioButton j3 = new JRadioButton("4 joueurs");
                int testjoueur = 2;
                // Ajout des listener sur les radio boutons
                j1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Joueur.setNbJoueur(2);
                    }
                });

                j2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Joueur.setNbJoueur(3);
                    }
                });

                j3.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Joueur.setNbJoueur(4);
                    }
                });

                bg.add(j1);
                bg.add(j2);
                bg.add(j3);

                nbJoueur.add(j1);
                nbJoueur.add(j2);
                nbJoueur.add(j3);

                // deuxième bouton jouer
                JButton p = new JButton("Jouer");

                p.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nbJoueur.dispose();

                        // affichage des nom des joueurs
                        nomJoueurs = new String[Joueur.getNbJoueur()];
                        int i;
                        for (i = 0; i < Joueur.getNbJoueur(); i++) {
                            String nom = JOptionPane.showInputDialog("Nom du joueur " + (i + 1));
                            // on a appuyer sur la croix ou cancel
                            if (nom == null) {
                                break;
                            }
                            nomJoueurs[i] = nom;
                        }
                        System.out.println("i : " + i);
                        System.out.println("nb joeuur " + Joueur.getNbJoueur());
                        if (i == Joueur.getNbJoueur()) // on a bien rentrer tous les noms
                        {
                            London.setListeJoueur(initialisationJoueur(London.getDeck()));
                            London.setEtalage(new Etalage(Joueur.getNbJoueur() + 1));

                            London.start();
                        }
                    }
                });
                nbJoueur.add(p);
                nbJoueur.setLocationRelativeTo(null);
                nbJoueur.setVisible(true);

            }

        });

        play.setBounds(243, 365, 115, 20);
        jbDebug.setBounds(243, 405, 115, 20);
        regle.setBounds(243,445,115,20);
        this.add(play);
        this.add(jbDebug);
        this.add(regle);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 589, 810, this);
    }

    private TourJoueur initialisationJoueur(ArrayDeque<Carte> arrayDeque) {
        int nb = Joueur.getNbJoueur();
        London.setTabJoueur(new Joueur[nb]);
        int fin = nb * 6;
        for (int i = 0; i < fin; i++) {
            switch (i % nb) {
                case 0:
                    if (London.getTabJoueur()[0] == null) {
                        London.getTabJoueur()[0] = new Joueur(nomJoueurs[0]);

                    }
                    London.getTabJoueur()[0].piocheCarte(arrayDeque.poll());
                    break;
                case 1:
                    if (London.getTabJoueur()[1] == null) {
                        London.getTabJoueur()[1] = new Joueur(nomJoueurs[1]);

                    }
                    London.getTabJoueur()[1].piocheCarte(arrayDeque.poll());
                    break;

                case 2:
                    if (London.getTabJoueur()[2] == null) {
                        London.getTabJoueur()[2] = new Joueur(nomJoueurs[2]);

                    }
                    London.getTabJoueur()[2].piocheCarte(arrayDeque.poll());
                    break;
                case 3:
                    if (London.getTabJoueur()[3] == null) {
                        London.getTabJoueur()[3] = new Joueur(nomJoueurs[3]);

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
