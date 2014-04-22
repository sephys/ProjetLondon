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
 *
 * @author Joke
 */
public class JPAccueilControl implements ActionListener{
    
     String[] nomJoueurs; // tableau de noms permettant l'initialisation
    
    public JPAccueilControl()
    {
        //Initialisation
        
    }
    
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
                j1.addActionListener(new DeuxJoueurs());
                
                j2.addActionListener(new TroisJoueurs());
                
                j3.addActionListener(new QuatreJoueurs());
                
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
                            Main.getJeu().setListeJoueur(initialisationJoueur(Main.getJeu().getDeck()));
                            Main.getJeu().setEtalage(new Etalage(Joueur.getNbJoueur() + 1));
                            
                            Main.getJeu().start();
                        }
                    }
                });
                nbJoueur.add(p);
                nbJoueur.setLocationRelativeTo(null);
                nbJoueur.setVisible(true);
        
    }
    
    
    
       public TourJoueur initialisationJoueur(ArrayDeque<Carte> arrayDeque) {
        int nb = Joueur.getNbJoueur();
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