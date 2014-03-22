/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
    Set<Zone> zonesAdjacentes =  new HashSet<>() ;
    

    public Zone(String nom, int prix, int nbCartes, int pointsVictoire) {
        this.nom = nom;
        this.prix = prix;
        this.nbCartes = nbCartes;
        this.pointsVictoire = pointsVictoire;
    }
    
}
