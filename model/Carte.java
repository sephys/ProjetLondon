package model;

public class Carte {
    
    private String nom;
    private String couleur;
    private String categorie;
    private String path;
    
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
    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path = path;
    }
    
    
}
