/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author FT
 */
public class Zone {
    
    private String nom;
    private int prix;
    private int nbCartes;
    private int pointsVictoire;

    public Zone(String nom, int prix, int nbCartes, int pointsVictoire) {
        this.nom = nom;
        this.prix = prix;
        this.nbCartes = nbCartes;
        this.pointsVictoire = pointsVictoire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbCartes() {
        return nbCartes;
    }

    public void setNbCartes(int nbCartes) {
        this.nbCartes = nbCartes;
    }

    public int getPointsVictoire() {
        return pointsVictoire;
    }

    public void setPointsVictoire(int pointsVictoire) {
        this.pointsVictoire = pointsVictoire;
    }
    
}
