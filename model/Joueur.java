package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {

	private String nom;
	private ArrayList<Carte> main;
	private HashMap<String,Integer> pouvoir;
	private int pointVictoire;
	private int pointPauvrete;
	private int argent;
	private int nbPret; 


	public Joueur(String nom){
		this.nom=nom;
		this.main=new ArrayList <Carte>();
		this.pouvoir=new HashMap<String,Integer>();
		this.pointVictoire=0;
		this.argent= 5;
		this.pointPauvrete=5;
		this.nbPret=0;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public ArrayList<Carte> getMain() {
		return main;
	}


	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}


	public HashMap<String, Integer> getPouvoir() {
		return pouvoir;
	}


	public void setPouvoir(HashMap<String, Integer> pouvoir) {
		this.pouvoir = pouvoir;
	}


	public int getPointVictoire() {
		return pointVictoire;
	}


	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}


	public int getPointPauvrete() {
		return pointPauvrete;
	}


	public void setPointPauvrete(int pointPauvrete) {
		this.pointPauvrete = pointPauvrete;
	}


	public int getArgent() {
		return argent;
	}


	public void setArgent(int argent) {
		this.argent = argent;
	}


	public int getNbPret() {
		return nbPret;
	}


	public void setNbPret(int nbPret) {
		this.nbPret = nbPret;
	}

	public void piocheCarte(Deck d){
		Carte e =d.poll();
		if(main.isEmpty()){
			this.main.add(e);
		}else{
			Carte temp;
			int i=0;
			boolean arret=false;
			while(i<this.main.size()&&!arret){
				temp=this.main.get(i);
				if(temp.getCouleur().compareTo(e.getCouleur())==0){
					if(i<this.main.size()-1){
						this.main.add(i+1,e);
					}else{
						this.main.add(e);
					}
					arret=true;
				}
				i++;
			}
			if(!arret){
				this.main.add(e);
			}
		}
	}
}
