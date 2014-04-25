/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DragDropControl;
import controleur.JPnomGaucheImageDroiteControl;
import model.Carte;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;

import java.awt.*;
import java.awt.dnd.DragSource;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
                                        

/**
 *
 * @author Joke
 */
public class London implements Serializable {

    private TourJoueur lJoueur;
    private ArrayDeque<Carte> deck;
    private Etalage etalage;
    private JPMain[] tabJPMain; // tableau des étalages
    private JPChantiers[] tabJPChantiers; // tableau des chantiers
    private JPEtalage jpEtalage;
    private JPChantiers jpChantier;
    private JPMain south; // panel contenant la main des joueurs
    private JPanel central;
    private MenuDroite menudroite;
    private JPPlateau plateau;
    private Thread sound; // thread pour la musique
    private JTabbedPane panelOnglet; // panel contenant les onglets
    private JFrame acc;
    private JPInfos infos;
    private HashMap<String, Zone> zones; // Structure contenant toutes les zones
    private Joueur[] tabJoueur;
    private JFrame frame; // fenêtre principale

    // pour le drag & drop
    public static DragDropControl dndListener;
    static DragSource dragSource;

    
    // controleur
    JPnomGaucheImageDroiteControl controlJPGID;
    

    public London() {

        controlJPGID=new JPnomGaucheImageDroiteControl();
        
        // D&D
        dndListener = new DragDropControl();
        dragSource = new DragSource();

        music();
        menu();
    }

    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public  void start() {
        // permet d'avoir le même affichage sous windows et mac
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(London.class.getName()).log(Level.SEVERE, null, ex);
        }

        initTabJPMain(); // initialisation des panel contenant les mains des joueurs
        initTabJPChantier(); // initialisation des panel contenant les zones de construction des joueurs

        frame = new JFrame(); // frame contenant le jeu
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Êtes-vous sur de vouloir quitter la partie ?", "Quitter le jeu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    sound.stop();
                    
                    /*FileOutputStream out=null;
                    try {
                        out = new FileOutputStream("temp.txt");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(London.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ObjectOutputStream s = new ObjectOutputStream(out);
                    } catch (IOException ex) {
                        Logger.getLogger(London.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                   
                    
                    System.exit(0);
                }
                else
                {
                    System.out.println("non fin de jeu");
                }
                
            }
        });

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

        // panel contenant les différents onglets
        panelOnglet = new JTabbedPane();

        plateau = new JPPlateau();

        // ajout des onglet :
        panelOnglet.addTab("Plateau", new JScrollPane(plateau));
        jpEtalage = new JPEtalage();
        panelOnglet.addTab("Etalage", jpEtalage);
        jpChantier = new JPChantiers();
        tabJPChantiers[0]=jpChantier;
        panelOnglet.addTab("Chantiers", jpChantier);

        // panel central contenant le plateau et la main du joueur
        central = new JPanel(new BorderLayout());

        // south contient la main du Joueur et contient celle du premier joueur en premier
        south = tabJPMain[0];
        //tabJPMain[0] = south;

        central.add(panelOnglet, BorderLayout.CENTER);
        central.add(south, BorderLayout.SOUTH);

        frame.add(central);

        this.menudroite = new MenuDroite();
        controlJPGID.changeImage(this.getListeJoueur().getJoueur());

    
        
        frame.add(menudroite, BorderLayout.EAST);

        infos = new JPInfos(this.getTabJoueur());
        frame.add(infos, BorderLayout.WEST);

        frame.setSize(1444, 810);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // on supprime la page d'accueil
        acc.dispose();

        // informe le joueur qui joue
        JOptionPane.showMessageDialog(null, "C'est au tour de " + this.getListeJoueur().getJoueur().getNom() + " de jouer");

        this.getListeJoueur().getJoueur().setPioche(1);


    }

    // methode qui affiche le menu quand on lance l'application
    public void menu() {
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

    public void music() {

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

    public MenuDroite getMenudroite() {
        return menudroite;
    }

    public JTabbedPane getPanelOnglet() {
        return panelOnglet;
    }

    public JPChantiers[] getTabJPChantiers() {
        return tabJPChantiers;
    }

    public JPChantiers getJpChantier() {
        return jpChantier;
    }

    public JPMain getSouth() {
        return south;
    }

    public void setSouth(JPMain south) {
        this.south = south;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getCentral() {
        return central;
    }

    public void setCentral(JPanel central) {
        this.central = central;
    }

    public HashMap<String, Zone> getZones() {
        return zones;
    }

    public void setZones(HashMap<String, Zone> zones) {
        this.zones = zones;
    }
    
    


    public void setJpChantier(JPChantiers jpChantier) {
        this.jpChantier = jpChantier;
    }
    
    
    public JPPlateau getPlateau() {
        return plateau;
    }

    public JPEtalage getJpEtalage() {
        return jpEtalage;
    }

    public void setJpEtalage(JPEtalage jpEtalage) {
        this.jpEtalage = jpEtalage;
    }

    public TourJoueur getListeJoueur() {
      
        return lJoueur;
    }

    public void setListeJoueur(TourJoueur initialisationJoueur) {
        
        this.lJoueur = initialisationJoueur;
    }

    public void setEtalage(Etalage etalage) {
        // TODO Auto-generated method stub
        this.etalage = etalage;
    }

    public Etalage getEtalage() {
        return etalage;
    }

    public JPInfos getInfos() {
        return infos;
    }

    public void setInfos(JPInfos infos) {
        this.infos = infos;
    }
    
    

    public JPMain[] getTabJPMain() {
        return tabJPMain;
    }

    public void setTabJPMain(JPMain[] tabJPMain) {
        this.tabJPMain = tabJPMain;
    }

    public void initTabJPMain() {
        tabJPMain = new JPMain[TourJoueur.getNbJoueur()];
        for (int i = 0; i <TourJoueur.getNbJoueur(); i++) {
           // System.out.println("moi"+this.getListeJoueur().getJoueur());
            tabJPMain[i] = new JPMain(this.getListeJoueur().getJoueur());
            this.setListeJoueur(this.getListeJoueur().getSuivant());
        }
    }
    
    public void initTabJPChantier()
    {
       tabJPChantiers=new JPChantiers[TourJoueur.getNbJoueur()];
       for(int i=0;i<TourJoueur.getNbJoueur();i++)
       {
           tabJPChantiers[i]=new JPChantiers();
       }
       
    }

    public Joueur[] getTabJoueur() {
        return tabJoueur;
    }

    public void setTabJoueur(Joueur[] tabJoueur) {
        this.tabJoueur = tabJoueur;
    }

    public void setDeck(ArrayDeque<Carte> deck) {
        this.deck = deck;
    }

    public ArrayDeque<Carte> getDeck() {
        // TODO Auto-generated method stub
        return deck;
    }
    
    public void sauvegarder() throws IOException{
    	FileOutputStream out = new FileOutputStream("temps");
		ObjectOutputStream s = new ObjectOutputStream(out);
		
    	s.writeObject(this.getDeck());
    	s.writeObject(this.getEtalage());
    	s.writeObject(this.getPlateau());
    	s.writeObject(this.getZones());
    	
    	TourJoueur tmp = this.getListeJoueur();
    	TourJoueur next = this.getListeJoueur();
    	s.writeObject(tmp.getJoueur());
    	while(tmp != next.getSuivant()){
    		next = next.getSuivant();
    		s.writeObject(next.getJoueur());
    	}
    	s.flush();
    	
    	s.close();
    	out.close();
    }
    
    public void charger() throws IOException{
    	FileInputStream in = new FileInputStream("temps");
    	ObjectInputStream t = new ObjectInputStream(in);

    	try {
    		
    		this.setDeck((ArrayDeque<Carte>)t.readObject());
			this.setEtalage((Etalage)t.readObject());
			this.plateau = (JPPlateau)t.readObject();
			this.setZones((HashMap<String,Zone>)t.readObject());
			
			
			TourJoueur first = new TourJoueur((Joueur)t.readObject());
			TourJoueur tmp = first;
			tmp.setSuivant(tmp);
			Joueur j;
    		while((j =(Joueur)t.readObject()) != null){
    			TourJoueur current = new TourJoueur(j);
    			tmp.setSuivant(current);
    			tmp = current;
    		}
    		tmp.setSuivant(first);
    	} catch (ClassNotFoundException e) {
    		
    		//EOF
    	}

    }

}
