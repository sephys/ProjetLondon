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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


/**
 *
 * @author Joke
 */
public class London {
    private static TourJoueur lJoueur;
    private static Deck deck;
    private static Etalage etalage;
    private static JPMain[] tabJPMain;
    
    static JFrame frame; // fenêtre principale
    
    // pour le drag & drop
    static DragDrop dndListener;
    static DragSource dragSource;
    static JFrame acc;
    public static void main(String[] args)  {
        
        // D&D
        dndListener = new DragDrop();
        dragSource=new DragSource();
        
        menu();
    }
    
    // méthode qui initialise la fenêtre lorsqu'on lance une partie
    public static void start()
    {

           
           acc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           acc.dispose();
           frame=new JFrame();
          
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTabbedPane panelOnglet = new JTabbedPane();
            
            JPPlateau plateau=null;
            try
            {
               URL uri = London.class.getResource("../img/plateau.png");
               Image image = ImageIO.read(uri);
               plateau = new JPPlateau(image);
              
            }
            catch (IOException e1) {
            }
            
            
            
            // ajout d'un onglet :
            panelOnglet.addTab("Plateau",new JScrollPane(plateau));

            panelOnglet.addTab("Etalage",new JPEtalage());

          
 
            JPanel p=new JPanel(new BorderLayout());
           
            JPanel south=new JPMain(Joueur.getTabJoueur()[0]);
            south.setBackground(Color.blue);
            //south.setPreferredSize(new Dimension(1000,150));
            //south.add(new JPMain());
            p.add(panelOnglet,BorderLayout.CENTER);
            p.add(south,BorderLayout.SOUTH);
            
            frame.add(p);
            
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
        acc=new JFrame();
        
        acc.setTitle("London");
        acc.setSize(570,810);
        acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        acc.setLayout(new BorderLayout());
        
        acc.setContentPane(new JPAccueil());
        
        
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

public void initTabJPMain()
{
	tabJPMain=new JPMain[Joueur.getNbJoueur()];
	for(int i=0;i<Joueur.getNbJoueur();i++)
	{
		tabJPMain[i]=new JPMain(Joueur.getTabJoueur()[i]);
	}
}

}
