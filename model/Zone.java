/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author FT
 */
public class Zone {
    
    private String nom;
    private int prix;
    private int nbCartes;
    private int pointsVictoire;
    private boolean zoneRouge;
    private boolean adjacentTamise;
    private boolean dessousTamise;
    private Set<Zone> zonesAdjacentes;

    
    public Zone(String nom, int prix, int nbCartes, int pointsVictoire, boolean zoneRouge, boolean adjacentTamise, boolean dessousTamise, String[] zonesAdjacentes) {
        this.nom = nom;
        this.prix = prix;
        this.nbCartes = nbCartes;
        this.pointsVictoire = pointsVictoire;
        this.zoneRouge = zoneRouge;
        this.adjacentTamise = adjacentTamise;
        this.dessousTamise = dessousTamise;
        this.zonesAdjacentes = new HashSet(Arrays.asList(zonesAdjacentes));
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

    public boolean isZoneRouge() {
        return zoneRouge;
    }

    public void setZoneRouge(boolean zoneRouge) {
        this.zoneRouge = zoneRouge;
    }

    public boolean isAdjacentTamise() {
        return adjacentTamise;
    }

    public void setAdjacentTamise(boolean adjacentTamise) {
        this.adjacentTamise = adjacentTamise;
    }

    public boolean isDessousTamise() {
        return dessousTamise;
    }

    public void setDessousTamise(boolean dessousTamise) {
        this.dessousTamise = dessousTamise;
    }

    public Set<Zone> getZonesAdjacentes() {
        return zonesAdjacentes;
    }

    public void setZonesAdjacentes(Set<Zone> zonesAdjacentes) {
        this.zonesAdjacentes = zonesAdjacentes;
    }
    
    @Override
    public String toString(){
        return this.nom+" [prix : "+this.prix+", nbCartes : "+this.nbCartes+", pointsVictoire : "+this.pointsVictoire+", ";
    }
 
}
