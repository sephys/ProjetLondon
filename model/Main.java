package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * La classe Main repr�sentera la main du joueur, � savoir les cartes
 * qu'il aura en sa possession.
 *
 */
public class Main {

	/**
	 * Pour un tri plus facile par couleur dans la main du joueur, sa main
	 * a �t� d�compos�es en 4 listes de cartes de couleurs diff�rentes.
	 */
	private ArrayList<Carte> cartesBleues;
	private ArrayList<Carte> cartesRoses;
	private ArrayList<Carte> cartesBrunes;
	private ArrayList<Carte> cartesGrises;
	
	
	/**
	 * A la cr�ation de la main, apr�s l'instanciation des diff�rentes listes
	 * de cartes, le joueur va piocher 6 cartes dans le Deck. 
	 * @param d	Deck dans lequel le joueur va piocher 6 cartes.
	 */
	public Main(Deck d){
		this.cartesBleues = new ArrayList<Carte>();
		this.cartesRoses = new ArrayList<Carte>();
		this.cartesBrunes = new ArrayList<Carte>();
		this.cartesGrises = new ArrayList<Carte>();
		for(int i = 0; i < 6; i++){
			try {
				piocher(d);
			} catch (ColourNotFoundException e) { 
				e.printStackTrace();
			}
		}
	}

	/**
	 * La m�thode piocher va prendre la premi�re carte du Deck pass�
	 * en param�tre puis l'ajouter dans la bonne liste de cartes.
	 * @param d	Deck dans lequel le joueur va piocher.
	 * @throws ColourNotFoundException	Exception d�clench�e si la couleur demand�e 
	 * 									n'existe pas.
	 */
	public void piocher(Deck d) throws ColourNotFoundException{
		Carte c = d.pollFirst();				// R�cup�ration de la premi�re carte du Deck
		String couleur = c.getCouleur();			// R�cup�ration de la couleur de cette carte
		if(couleur.compareTo("bleue") == 0){			// Ajout dans la bonne liste
			cartesBleues.add(c);
		}else{
			if(couleur.compareTo("rose") == 0){
				cartesBleues.add(c);
			}else{
				if(couleur.compareTo("marron") == 0){
					cartesBleues.add(c);
				}else{
					if(couleur.compareTo("grise") == 0){
						cartesBleues.add(c);
					}else{				// En cas de couleur non conforme
						throw new ColourNotFoundException(couleur);	
					}
				}
			}
		}
	}
}
