package model;

public class TourJoueur {
	
	private Joueur joueur;
	private TourJoueur suivant;
	
	
	public TourJoueur(Joueur joueur) {
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

}
