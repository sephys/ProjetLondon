/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Deck;
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
	private static int testNb;
	
	public JPAccueil()
	{
		super();
		//deck
		London.setDeck(new Deck());
		Zone.initZone();
		

		// image de fond
		try {
			URL uri = JPZoom.class.getResource("../img/accueil.png"); 
			img = ImageIO.read(uri);
		} catch (IOException ex) {
			Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
		}

		// premier bouton play
		JButton play =new JButton("play");
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				// frame choix du joueur
				final JFrame nbJoueur=new JFrame();
				nbJoueur.setLayout(new GridLayout(4,1));
				nbJoueur.setSize(300,200);
				nbJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				// premet de lier les radio boutons
				ButtonGroup bg=new ButtonGroup();

				JRadioButton j1=new JRadioButton("2 joueurs");
				JRadioButton j2=new JRadioButton("3 joueurs");
				JRadioButton j3=new JRadioButton("4 joueurs");
				int testjoueur=2;
				// Ajout des listener sur les radio boutons
				j1.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						Joueur.setNbJoueur(2);
					}
				});

				j2.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						Joueur.setNbJoueur(3);
					}
				});

				j3.addActionListener(new ActionListener(){

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
				JButton p=new JButton("Jouer");
				
				p.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						nbJoueur.dispose();
						London.setListeJoueur(initialisationJoueur(London.getDeck()));
						London.setEtalage(new Etalage(London.getListeJoueur().getNbJoueur()+1));
						
						London.start();
					}
				});
				nbJoueur.add(p);
				nbJoueur.setLocationRelativeTo(null);
				nbJoueur.setVisible(true);




			}

		});
		play.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add(play);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, 589, 810, this);
	}


	private static TourJoueur initialisationJoueur(Deck d) {
		int nb=Joueur.getNbJoueur();
		London.setTabJoueur(new Joueur[nb]);
		int fin=nb*6;
		for(int i=0;i<fin;i++){
			switch(i%nb){
			case 0 :
				if(London.getTabJoueur()[0]==null){
					London.getTabJoueur()[0]=new Joueur("j1");
					
				}
				London.getTabJoueur()[0].piocheCarte(d.poll());
				break;
			case 1:
				if(London.getTabJoueur()[1]==null){
					London.getTabJoueur()[1]=new Joueur("j2");

				}
				London.getTabJoueur()[1].piocheCarte(d.poll());
				break;

			case 2:
				if(London.getTabJoueur()[2]==null){
					London.getTabJoueur()[2]=new Joueur("j3");

				}
				London.getTabJoueur()[2].piocheCarte(d.poll());
				break;
			case 3:
				if(London.getTabJoueur()[3]==null){
					London.getTabJoueur()[3]=new Joueur("j4");

				}
				London.getTabJoueur()[3].piocheCarte(d.poll());
				break;
			}
		}
		//choix hasard premier joueur
		int indice=(int) (Math.random()*(nb-1)); //borne [0.. nbjoueur-1]
		TourJoueur first = null;
		TourJoueur tmp=null;
		for(int i=0;i<nb;i++){
			//implementer structure cyclique
			TourJoueur current;
			switch((indice+i)%nb){
			case 0 :
				current=new TourJoueur(London.getTabJoueur()[0]);
				London.getTabJoueur()[0].setPlaceJoueur(i);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;
			case 1:
				current=new TourJoueur(London.getTabJoueur()[1]);
				London.getTabJoueur()[1].setPlaceJoueur(i);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;

			case 2:
				current=new TourJoueur(London.getTabJoueur()[2]);
				London.getTabJoueur()[2].setPlaceJoueur(i);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;
			case 3:
				current=new TourJoueur(London.getTabJoueur()[3]);
				London.getTabJoueur()[3].setPlaceJoueur(i);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;
			}

		}
		tmp.setSuivant(first);
		return first;
		// TODO Auto-generated method stub

	}
	
}
