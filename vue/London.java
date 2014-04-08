/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;
import model.Deck;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;

import java.awt.*;
import java.awt.dnd.DragSource;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Zone;


/**
 *
 * @author Joke
 */
public class London {
    private static TourJoueur lJoueur;
    private static Deck deck;
    private static Etalage etalage;
    private static JPMain[] tabJPMain;
    static JPanel south; // panel contenant la main des joueurs
    static JPanel central;
    
    static JFrame frame; // fenêtre principale
    
    // pour le drag & drop
    static DragDrop dndListener;
    static DragSource dragSource;
    static JFrame acc;
    public static HashMap<String, Zone> zones; // Structure contenant toutes les zones
    private static Joueur[] tabJoueur;
    public static void main(String[] args)  {
        
        // D&D
        dndListener = new DragDrop();
        dragSource=new DragSource();
        
        menu();
    }
    
    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public static void start()
    {
            
           initTabJPMain(); // initialisation des panel contenant les mains des joueurs
           
           frame=new JFrame(); // frame contenant le jeu
          
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
           // panel contenant les différents onglets
           JTabbedPane panelOnglet = new JTabbedPane();
            
           JPPlateau plateau=new JPPlateau();
            

            // ajout des onglet :
            panelOnglet.addTab("Plateau",new JScrollPane(plateau));

            panelOnglet.addTab("Etalage",new JPEtalage());

          
            // panel central contenant le plateau et la main du joueur
            central=new JPanel(new BorderLayout());
           
            // south contient la main du Joueur et contient celle du premier joueur en premier
            south=new JPMain(getTabJoueur()[0]);
            
            
            central.add(panelOnglet,BorderLayout.CENTER);
            central.add(south,BorderLayout.SOUTH);
            
            frame.add(central);
            
            MenuDroite menudroite=new MenuDroite();
            
            frame.add(menudroite,BorderLayout.EAST);
            frame.add(new JPInfos(),BorderLayout.WEST);
 
            frame.setSize(1444,810);
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            System.out.println(lJoueur);
            System.out.println(deck);
            System.out.println(etalage);
            System.out.println(plateau);

    }
    
    // methode qui affiche le menu quand on lance l'application
    public static void menu()
    {
        acc=new JFrame(); // JFrame d'accueil
        
        acc.setTitle("London");
        acc.setSize(570,810);
        acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        acc.setLayout(new BorderLayout());
        
        // intialisation de tous les elements
        acc.setContentPane(new JPAccueil());
        
        acc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        acc.dispose();
        acc.setLocationRelativeTo(null);
        acc.setVisible(true);
        
    }


	public static void setDeck(Deck deck2) {
		// TODO Auto-generated method stub
		deck=deck2;
	}

public static Deck getDeck() {
	// TODO Auto-generated method stub
	return deck;
}

public static TourJoueur getListeJoueur() {
	// TODO Auto-generated method stub
	return lJoueur;
}

public static void setListeJoueur(TourJoueur initialisationJoueur) {
	// TODO Auto-generated method stub
	lJoueur=initialisationJoueur;
}

public static void setEtalage(Etalage etalage2) {
	// TODO Auto-generated method stub
	etalage=etalage2;
}

    public static JPMain[] getTabJPMain() {
        return tabJPMain;
    }

    public static void setTabJPMain(JPMain[] tabJPMain) {
        London.tabJPMain = tabJPMain;
    }



public static void initTabJPMain()
{
	tabJPMain=new JPMain[Joueur.getNbJoueur()];
	for(int i=0;i<Joueur.getNbJoueur();i++)
	{
		tabJPMain[i]=new JPMain(getTabJoueur()[i]);
	}
}

    public static Joueur[] getTabJoueur() {
        return London.tabJoueur;
    }

    public static void setTabJoueur(Joueur[] tabJoueur) {
        London.tabJoueur = tabJoueur;
    }

}
