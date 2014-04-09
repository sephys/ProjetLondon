package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import model.Carte;
import model.Constructible;
import model.Etalage;
import model.Joueur;
import model.TourJoueur;
import model.Zone;

public class testInit {
	private static TourJoueur lJoueur;
	private static Etalage defausse;

	public static void main(String[] args) {
		/*
		// TODO Auto-generated method stub
		//crÃ©ation du deck
		deck=new Deck();
		//crÃ©ation de toutes les zones
		//initialisation structure cyclique joueur;
		lJoueur =initialisationJoueur(deck);
		//initialisation Etalage
		defausse=new Etalage(lJoueur.getNbJoueur()+1);
		Scanner sc=new Scanner(System.in);
		boolean finJeu=false;
		System.out.println("le jeu va commencer");
		System.out.println(lJoueur.getJoueur().getNom()+ "vous êtes le premier a jouer");
		
		while(!finJeu){
			Joueur currJ=lJoueur.getJoueur();
			System.out.println(currJ.getNom());
			currJ.piocheCarte(deck.poll());
			menu();
			int choix=sc.nextInt();
			switch(choix){
			case 1:
				affichageZoneDispo();
				System.out.println("Saisir le nom de la zone");
				String z=sc.nextLine();
				z=sc.nextLine();
				boolean annuler=false;
				System.out.println(plateau.keySet());
				while(!currJ.acheterZone(z,plateau)&&!annuler){
					System.out.println("Vous ne possedez pas assez d'argent");
					System.out.println("Faire un prêt?");
					String a=sc.nextLine();
					switch(a){
					case "oui" :
						currJ.emprunt(10);
						break;
					case "non" :
						annuler=true;
						break;
					}
				}
				if (annuler){
					System.out.println("vous n'avez pas investi");
				}else{
					System.out.println("investissement réussi");
					System.out.println(currJ);
				}

				break;
			case 2:
				System.out.println(currJ.getMain());
				boolean jouer=true;
				while(jouer){
					System.out.println("Quelle carte voulez vous jouez");
					int jCarte=sc.nextInt();
					Carte tmpCarte=currJ.getCarteMain(jCarte);
					if(tmpCarte.getClass().equals(Constructible.class)){
						afficherChantier();
						System.out.println("sur quelle chantier?");
						jCarte=sc.nextInt();
						tmpCarte.jouerCarte(currJ,jCarte);
					}else{
						tmpCarte.jouerCarte(currJ, jCarte);
					}
				}
				
				break;
			case 3:
				break;
			case 4:
				int i=0;
				while(i<3){
					System.out.println("Dans quelle zone voulez vous piochez une carte?");
					System.out.println("1 : Etalage");
					System.out.println("2 : Deck");
					int choixPioche=sc.nextInt();
					boolean annule=false;
					switch(choixPioche){
						case 1 :
							annule=defausse.isEmpty();
							if(!annule){
								System.out.println(defausse);
								System.out.println("choix carte");
								int lCarte=sc.nextInt();
								int cCarte=sc.nextInt();
								currJ.piocheCarte(defausse.piocheCarte(lCarte,cCarte));
							}else{
								System.out.println("il n'y a aucune carte dans l'etalage");
							}
							break;
						case 2 :
							currJ.piocheCarte(deck.poll());
							break;
					}
					if(!annule){
						i++;
					}
					
				}
				break;
			}
			lJoueur=finirTour(lJoueur);
		}

	}

	private static void afficherChantier() {
		// TODO Auto-generated method stub
		
	}

	private static TourJoueur finirTour(TourJoueur lJoueur2) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Joueur tmpJ=lJoueur2.getJoueur();
		while(tmpJ.getMain().size()>9){
			System.out.println("Vous ne devez pas avoir plus de 9 cartes dans votre main");
			System.out.println("Quelle carte voulez vous jeter dans l'étalage");
			System.out.println(tmpJ.getMain());
			int lCarte=sc.nextInt();
			Carte cJeter=tmpJ.getMain().get(lCarte);
			tmpJ.getMain().remove(cJeter);
			defausse.addCarte(cJeter);
		}
		return lJoueur2.getSuivant();
	}

	private static TourJoueur initialisationJoueur(Deck d) {
		System.out.println("Combien de joueur ?");
		Scanner sc= new Scanner(System.in);
		int nb=sc.nextInt();
		Joueur [] t= new Joueur[nb];
		String nom="toto";
		int fin=nb*6;
		for(int i=0;i<fin;i++){
			switch(i%nb){
			case 0 :
				if(t [0]==null){
					System.out.println("Entrer le nom du premier Joueur");
					nom=sc.next();
					t[0]=new Joueur(nom);
				}
				t[0].piocheCarte(d.poll());
				break;
			case 1:
				if(t [1]==null){
					System.out.println("Entrer le nom du second Joueur");
					nom=sc.next();
					t[1]=new Joueur(nom);

				}
				t[1].piocheCarte(d.poll());
				break;

			case 2:
				if(t [2]==null){
					System.out.println("Entrer le nom du troisiï¿½me Joueur");
					nom=sc.next();
					t[2]=new Joueur(nom);

				}
				t[2].piocheCarte(d.poll());
				break;
			case 3:
				if(t [3]==null){
					System.out.println("Entrer le nom du dernier Joueur");
					nom=sc.next();
					t[3]=new Joueur(nom);

				}
				t[3].piocheCarte(d.poll());
				break;
			}
		}
		//choix hasard premier joueur
		int indice=(int) (Math.random()*(nb-1)); //borne [0.. nbjoueur-1]
		System.out.println(indice);
		TourJoueur first = null;
		TourJoueur tmp=null;
		for(int i=0;i<nb;i++){
			//implementer structure cyclique
			TourJoueur current;
			switch((indice+i)%nb){
			case 0 :
				current=new TourJoueur(t[0]);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;
			case 1:
				current=new TourJoueur(t[1]);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;

			case 2:
				current=new TourJoueur(t[2]);
				if(tmp!=null){
					tmp.setSuivant(current);
				}else{
					first=current;
				}
				tmp=current;
				break;
			case 3:
				current=new TourJoueur(t[3]);
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

	private static void menu(){
		System.out.println("1 : Investir");
		System.out.println("2 : Construire");
		System.out.println("3 : Restaurer");
		System.out.println("4 : Piocher");
		System.out.println("Que voulez vous faire?");
	}

	private static void affichageZoneDispo(){
		HashSet<String> zDispo=plateau.zoneInvest();
		for(String tmp : zDispo){
			System.out.println(plateau.get(tmp));
		}*/
	}
}
