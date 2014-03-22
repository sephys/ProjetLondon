package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//création du deck
		Deck d=new Deck();
		for(Carte a: d){
			System.out.println("Categorie : "+a.getCategorie()+" Nom : "+a.getNom());
		}
		//mélange du deck;
		System.out.println(d.size());
		initialisationJeu(d);
	}

	private static LinkedList<Joueur> initialisationJeu(Deck d) {
		System.out.println("Combien de joueur ?");
		Scanner sc= new Scanner(System.in);
		int nb=sc.nextInt();
		Joueur [] t= new Joueur[nb];
		String nom="toto";
		Carte tmpCarte;
		for(int i=0;i<nb*6;i++){
			tmpCarte=d.poll();
			switch(i%nb){
			case 0 :
				if(t [0]==null){
					System.out.println("Entrer le nom du premier Joueur");
					nom=sc.next();
					t[0]=new Joueur(nom);

				}
				t[0].piocheCarte(d);
				break;
			case 1:
				if(t [1]==null){
					System.out.println("Entrer le nom du premier Joueur");
					nom=sc.next();
					t[1]=new Joueur(nom);

				}
				t[1].piocheCarte(d);
				break;

			case 2:
				if(t [2]==null){
					System.out.println("Entrer le nom du premier Joueur");
					nom=sc.next();
					t[1]=new Joueur(nom);

				}
				t[2].piocheCarte(d);
				break;
			case 3:
				if(t [3]==null){
					System.out.println("Entrer le nom du premier Joueur");
					nom=sc.next();
					t[3]=new Joueur(nom);

				}
				t[3].piocheCarte(d);
				break;
			}
		}
		LinkedList <Joueur> lJoueur = new LinkedList<Joueur>();
		//choix hasard premier joueur
		int indice=(int) (Math.random()*(nb-1)); //borne [0.. nbjoueur-1]
		for(int i=0;i<nb;i++){
			//implementer structure cyclique
		}
		return null;
		// TODO Auto-generated method stub

	}


}

