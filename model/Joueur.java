package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {

	private String nom;
	private ArrayList<Carte> main;
	private HashMap<String,Integer> pouvoir;
	private ArrayList<ArrayDeque<Constructible>>listeChantier;
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
		this.listeChantier=new ArrayList(new ArrayDeque<Constructible>());
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

	public void piocheCarte(Carte e){ 	//ajout de la carte dans la main
		if(main.isEmpty()){ 
			this.main.add(e);			//si lamain est vide on ajoute la carte directement
		}else{
			Carte temp;					
			int i=0;
			boolean arret=false;
			while(i<this.main.size()&&!arret){ //sinon on parcour la main
				temp=this.main.get(i);
				if(temp.getCouleur().compareTo(e.getCouleur())==0){ //d�s que l'on trouve une carte de la m�me couleur
					if(i<this.main.size()-1){ //si on est pas � la fin de la main, on ajoute la carte pioch� apr�s la carte courante
						this.main.add(i+1,e);
					}else{
						this.main.add(e); //sinon on ajoute la carte a la fin
					}
					arret=true; //on sort de la boucle
				}
				i++;
			}
			if(!arret){ //dans le cas ou on n'as trouv� aucune carte dans la main de la m�me couleur qe la carte pioch�
				this.main.add(e); //on ajoute la carte a la fin
			}
		}
	}

	public boolean peutInvestir(Zone z){ //verification si le joueur possde assez d'argent
		boolean res=true;
		if(this.argent<z.getPrix()){
			res=false;
		}
		return res;
	}

	public boolean acheterZone(String zo, Plateau plateau) { // a vérifier petit pb
		// TODO Auto-generated method stub
		Zone z=plateau.get(zo);
		boolean res=peutInvestir(z);
		if(res){
			z.setProprietaire(this);
			this.setArgent(this.getArgent()-z.getPrix());
			this.setPointVictoire(this.getPointVictoire()+z.getPointsVictoire());
			ArrayList <String> zoneAdjacente=z.getZonesAdjacentes();
			System.out.println("Liste zone"+zoneAdjacente);
			for(String tmpZ : zoneAdjacente){
				try{
					plateau.get(tmpZ).setActivable(true);
				}catch(NullPointerException e){
					System.out.println("zone inexistante");
				}
			}
		}
		return res;
	}

	public void nouveauChantier(){ //ajoute un nouveau chantier
		this.listeChantier.add(new ArrayDeque<Constructible>());
	}
	
	
 	public void emprunt(int i) {
		// TODO Auto-generated method stub
		this.setNbPret(i%10);
		this.setArgent(this.getArgent()+i);
	}

 	public Carte getCarteMain(int index){
 		return this.main.get(index);
 	}
	public String toString(){
		String res;
		StringBuffer tmpRes=new StringBuffer("Joueur :");
		tmpRes.append("\n"+this.getNom());
		tmpRes.append("\n"+this.getMain());
		tmpRes.append("\n"+this.getNbPret());
		tmpRes.append("\n"+this.getArgent());
		res=new String(tmpRes);
		return res;
	}

}

