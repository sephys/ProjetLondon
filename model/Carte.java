package model;

public class Carte {

	private String nom;
	private String couleur;
	private String categorie;
	
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
	
	
}
