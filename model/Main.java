package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * La classe Main représentera la main du joueur, à savoir les cartes
 * qu'il aura en sa possession.
 *
 */
public class Main {

	/**
	 * Pour un tri plus facile par couleur dans la main du joueur, sa main
	 * a été décomposées en 4 listes de cartes de couleurs différentes.
	 */
	private ArrayList<Carte> main;


	/**
	 * A la création de la main, après l'instanciation des différentes listes
	 * de cartes, le joueur va piocher 6 cartes dans le Deck. 
	 * @param d	Deck dans lequel le joueur va piocher 6 cartes.
	 */
	public Main(){
		this.main = new ArrayList<Carte>();
	}

	public void trierMainCouleur(){
		String couleurF;
		String couleurC;
		Carte temp;
		boolean arret=false;
		int indC;
		for(int indF=0;indF<main.size()-2;indF++){
			couleurF= main.get(indF).getCouleur();
			arret=false;
			indC=indF+1;
			while(!arret){
				couleurC= main.get(indC).getCouleur();
				if(couleurC.compareTo(couleurF)==0){
					if(indC>indF+1){
						temp=main.get(indF+1);
						main.add(indF+1,main.get(indC));
						main.add(indC,temp);
					}else{
						arret=true;
					}
				}
				if(indC==main.size()-1){
					arret=true;
				}
			}
		}
	}
	public void addCarte(Carte e){
		this.main.add(e);
		this.trierMainCouleur();
	}
}
