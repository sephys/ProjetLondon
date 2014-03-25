package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class test {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		//cr�ation du deck
		Deck d=new Deck();
		for(Carte a: d){
			System.out.println("Categorie : "+a.getCategorie()+" Nom : "+a.getNom());
		}
		//m�lange du deck;
		System.out.println(d.size());

                Etalage e = new Etalage(5);
                e.addCarte(d.poll());
                for(int i=0;i<5;i++){
                    System.out.println("bla");
                    if(e.getLigne1()[i]!=null){
                        System.out.println(e.getLigne1()[i]);
                    }
                }

		//Initialisation des zones
                
		/*String zonesAdjacentes[] = { "Hackey", "Bethnal Green", "Southwark", "St. Pancras", "St. Marylebone", "Islington" };
		Zone z = new Zone("City", 8, 4, 6, true, true, false, zonesAdjacentes);
		System.out.println("Zone :");
		System.out.println(z);	*/
		/*TourJoueur tj =initialisationJeu(d);*/
	}
            
	private static TourJoueur initialisationJeu(Deck d) {
		System.out.println("Combien de joueur ?");
		Scanner sc= new Scanner(System.in);
		int nb=sc.nextInt();
		Joueur [] t= new Joueur[nb];
		String nom="toto";
		int fin=nb*6;
		System.out.println(fin);
		for(int i=0;i<fin;i++){
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
					System.out.println("Entrer le nom du second Joueur");
					nom=sc.next();
					t[1]=new Joueur(nom);

				}
				t[1].piocheCarte(d);
				break;

			case 2:
				if(t [2]==null){
					System.out.println("Entrer le nom du troisi�me Joueur");
					nom=sc.next();
					t[2]=new Joueur(nom);

				}
				t[2].piocheCarte(d);
				break;
			case 3:
				if(t [3]==null){
					System.out.println("Entrer le nom du dernier Joueur");
					nom=sc.next();
					t[3]=new Joueur(nom);

				}
				t[3].piocheCarte(d);
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

