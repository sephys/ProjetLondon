package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class test {
	
	private Deck deck;
	private TourJoueur lJoueur;
	private Etalage defausse;
	private HashMap <String,Zone> plateau;
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		//création du deck
		Deck d=new Deck();
		//création de toutes les zones
		Plateau plateau=new Plateau();
		//initialisation structure cyclique joueur;
		TourJoueur tj =initialisationJoueur(d);
		//initialisation Etalage
		Etalage etalage=new Etalage(tj.getNbJoueur()+1);
		

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
					System.out.println("Entrer le nom du troisi�me Joueur");
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
		for(int i=0;i<nb;i++){
			ArrayList<Carte> temp=t[i].getMain();
			for(Carte a : temp){
				System.out.print(a.getCouleur()+", ");
			}
			System.out.println("");
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


}

