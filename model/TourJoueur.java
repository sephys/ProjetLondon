package model;

public class TourJoueur {
	private static int nbJoueur=0;
	private Joueur joueur;
	private TourJoueur suivant;
	private int finTour=-1;
	
	public TourJoueur(Joueur joueur) {
		//nbJoueur++;
		this.joueur = joueur;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public TourJoueur getSuivant() {
		if(this.finTour>0){
			this.finTour--;
		}
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


	public void setFinTour(int nbJoueur2) {
		// TODO Auto-generated method stub
		this.finTour=nbJoueur2;	
	}
	public int getFinTour() {
		// TODO Auto-generated method stub
		return this.finTour;	
	}
	
}
