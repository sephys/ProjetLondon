package model;

import java.io.Serializable;

public class TourJoueur implements Serializable {
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
	public void finJeu(){
		TourJoueur tm=this;
		int min=1000;
		int tmpmin;
		for(int i=0;i<nbJoueur;i++){
			tmpmin=tm.getJoueur().finPartie();
			if(tmpmin<min){
				min=tmpmin;
			}
			tm=tm.getSuivant();
		}
		for(int i=0;i<nbJoueur;i++){
			tm.getJoueur().setPointPauvrete(tm.getJoueur().getPointPauvrete()-min);
			tm=tm.getSuivant();
		}
		for(int i=0;i<nbJoueur;i++){
			
			if(tm.getJoueur().getPointPauvrete()-1<=Zone.Penalite.length){
				tm.getJoueur().setPointVictoire(tm.getJoueur().getPointVictoire()+Zone.Penalite[tm.getJoueur().getPointPauvrete()]);
			}else{
				int pena=(tm.getJoueur().getPointPauvrete()-15)*3;
				tm.getJoueur().setPointVictoire(tm.getJoueur().getPointVictoire()+Zone.Penalite[9]+pena);
			}
			tm=tm.getSuivant();
		}
		
	}
	
}
