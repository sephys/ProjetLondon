package model;

public abstract class Carte {

	private String nom; //nom de la carte
	private String couleur; //couleur de la carte {bleue, marron,rose ou grise}
	private String categorie; //cat�gorie de la carte {A,B ou C}
        private String path;

	//Constructor
	public Carte(String nom, String couleur, String categorie, String path) {
		this.nom = nom;
		this.couleur = couleur;
		this.categorie = categorie;
                this.path = path;
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
        public String getPath(){
            return this.path;
        }
        public void setPath(String path){
            this.path = path;
        }
}
