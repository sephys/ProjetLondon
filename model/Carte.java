package model;

public abstract class Carte {

	private String nom; //nom de la carte
	private String couleur; //couleur de la carte {bleu,marron,rose ou grise}
	private String categorie; //catégorie de la carte {A,B ou C}

	//Constructor
	public Carte(String nom, String couleur, String categorie) {
		this.nom = nom;
		this.couleur = couleur;
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public abstract void jouerCarte();

	public String toString(){
		return new String(this.nom);
	}
}
