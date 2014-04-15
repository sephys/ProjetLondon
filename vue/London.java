/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;

import java.awt.*;
import java.awt.dnd.DragSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.metal.MetalLookAndFeel;
import model.Zone;
import java.io.*;
//import javax.media.j3d.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Joke
 */
public class London {

    private static TourJoueur lJoueur;
    private static ArrayDeque<Carte> deck;
    private static Etalage etalage;
    private static JPMain[] tabJPMain;
    private static JPEtalage jpEtalage;
    private static JPChantiers jpChantier;
    static JPMain south; // panel contenant la main des joueurs
    static JPanel central;
    private static MenuDroite menudroite;
    private static JPPlateau plateau;
    private static Thread sound; // thread pour la musique

    static JFrame frame; // fenêtre principale

    // pour le drag & drop
    static DragDrop dndListener;
    static DragSource dragSource;
    static JFrame acc;
    static JPInfos infos;
    public static HashMap<String, Zone> zones; // Structure contenant toutes les zones
    private static Joueur[] tabJoueur;

    public static void main(String[] args) {

        // D&D
        dndListener = new DragDrop();
        dragSource = new DragSource();

        music();
        menu();
    }

    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public static void start() {
        // permet d'avoir le même affichage sous windows et mac
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(London.class.getName()).log(Level.SEVERE, null, ex);
        }

        initTabJPMain(); // initialisation des panel contenant les mains des joueurs

        frame = new JFrame(); // frame contenant le jeu
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Êtes-vous sur de vouloir quitter la partie ?", "Quitter le jeu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    sound.stop();
                    System.exit(0);
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel contenant les différents onglets
        JTabbedPane panelOnglet = new JTabbedPane();

        plateau = new JPPlateau();

        // ajout des onglet :
        panelOnglet.addTab("Plateau", new JScrollPane(plateau));
        jpEtalage = new JPEtalage();
        panelOnglet.addTab("Etalage", jpEtalage);
        jpChantier = new JPChantiers();
        panelOnglet.addTab("Chantiers", jpChantier);

        // panel central contenant le plateau et la main du joueur
        central = new JPanel(new BorderLayout());

        // south contient la main du Joueur et contient celle du premier joueur en premier
        south = new JPMain(getTabJoueur()[0]);
        tabJPMain[0] = south;

        central.add(panelOnglet, BorderLayout.CENTER);
        central.add(south, BorderLayout.SOUTH);

        frame.add(central);

        London.menudroite = new MenuDroite();

        frame.add(menudroite, BorderLayout.EAST);

        London.infos = new JPInfos(London.getTabJoueur());
        frame.add(London.infos, BorderLayout.WEST);

        frame.setSize(1444, 810);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // on supprime la page d'accueil
        acc.dispose();

        // informe le joueur qui joue
        JOptionPane.showMessageDialog(null, "C'est au tour de " + London.getListeJoueur().getJoueur().getNom() + " de jouer");

        London.getListeJoueur().getJoueur().setPioche(1);


    }

    // methode qui affiche le menu quand on lance l'application
    public static void menu() {
        acc = new JFrame(); // JFrame d'accueil
        acc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                sound.stop();
                System.exit(0);
            }
        });


        acc.setTitle("London");
        acc.setSize(570, 810);
        acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        acc.setLayout(new BorderLayout());

        // intialisation de tous les elements
        acc.setContentPane(new JPAccueil());

        acc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        acc.dispose();
        acc.setLocationRelativeTo(null);
        acc.setVisible(true);

    }

    public static void music() {

        sound = new Thread() {
            URL uri = London.class.getResource("../fichier/music.wav");

            public void run() {

                AudioPlayer MGP = AudioPlayer.player;
                AudioStream BGM;
                AudioData MD;
                ContinuousAudioDataStream loop = null;
                for (;;) {

                    try {
                        BGM = new AudioStream(new FileInputStream(uri.getPath()));//enter the sound directory and name here
                        AudioPlayer.player.start(BGM);
                        sleep(160000);// temps de la musique
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(null, e);
                    }

                }
            }
        };
        sound.start();
    }

    public static MenuDroite getMenudroite() {
        return menudroite;
    }

    public static JPPlateau getPlateau() {
        return plateau;
    }

    public static JPEtalage getJpEtalage() {
        return jpEtalage;
    }

    public static void setJpEtalage(JPEtalage jpEtalage) {
        London.jpEtalage = jpEtalage;
    }

    public static TourJoueur getListeJoueur() {
        // TODO Auto-generated method stub
        return lJoueur;
    }

    public static void setListeJoueur(TourJoueur initialisationJoueur) {
        // TODO Auto-generated method stub
        lJoueur = initialisationJoueur;
    }

    public static void setEtalage(Etalage etalage2) {
        // TODO Auto-generated method stub
        etalage = etalage2;
    }

    public static Etalage getEtalage() {
        return etalage;
    }

    public static JPMain[] getTabJPMain() {
        return tabJPMain;
    }

    public static void setTabJPMain(JPMain[] tabJPMain) {
        London.tabJPMain = tabJPMain;
    }

    public static void initTabJPMain() {
        tabJPMain = new JPMain[Joueur.getNbJoueur()];
        for (int i = 0; i < Joueur.getNbJoueur(); i++) {
            tabJPMain[i] = new JPMain(getTabJoueur()[i]);
        }
    }

    public static Joueur[] getTabJoueur() {
        return London.tabJoueur;
    }

    public static void setTabJoueur(Joueur[] tabJoueur) {
        London.tabJoueur = tabJoueur;
    }

    public static void setDeck(ArrayDeque<Carte> deck) {
        London.deck = deck;
    }

    public static ArrayDeque<Carte> getDeck() {
        // TODO Auto-generated method stub
        return deck;
    }

}
