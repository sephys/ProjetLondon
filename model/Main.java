package model;

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
	private ArrayList<Carte> cartesBleues;
	private ArrayList<Carte> cartesRoses;
	private ArrayList<Carte> cartesBrunes;
	private ArrayList<Carte> cartesGrises;
	
	
	/**
	 * A la création de la main, après l'instanciation des différentes listes
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
	 * La méthode piocher va prendre la première carte du Deck passé
	 * en paramètre puis l'ajouter dans la bonne liste de cartes.
	 * @param d	Deck dans lequel le joueur va piocher.
	 * @throws ColourNotFoundException	Exception déclenchée si la couleur demandée 
	 * 									n'existe pas.
	 */
	public void piocher(Deck d) throws ColourNotFoundException{
		Carte c = d.pollFirst();				// Récupération de la première carte du Deck
		String couleur = c.getCouleur();			// Récupération de la couleur de cette carte
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
