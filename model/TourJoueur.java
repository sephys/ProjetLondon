package model;

public class TourJoueur {
	private static int nbJoueur=0;
	private Joueur joueur;
	private TourJoueur suivant;
	
	
	public TourJoueur(Joueur joueur) {
		nbJoueur++;
		this.joueur = joueur;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public TourJoueur getSuivant() {
		return suivant;
	}


	public void setSuivant(TourJoueur suivant) {
		this.suivant = suivant;
	}


	public static int getNbJoueur() {
		return nbJoueur;
	}


	public static void setNbJoueur(int nbJoueur) {
		TourJoueur.nbJoueur = nbJoueur;
	}
	
}
