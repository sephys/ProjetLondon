/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import vue.JPPlateau;
import vue.London;

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
    private ArrayList<String> zonesAdjacentes;
    private Joueur proprietaire;
    private boolean activable;
    
    
    
    public Zone(String nom, int prix, int nbCartes, int pointsVictoire, boolean activable, boolean adjacentTamise, boolean dessousTamise, ArrayList <String> zonesAdjacentes) {
        this.nom = nom;
        this.prix = prix;
        this.nbCartes = nbCartes;
        this.pointsVictoire = pointsVictoire;
        this.adjacentTamise = adjacentTamise;
        this.dessousTamise = dessousTamise;
        this.zonesAdjacentes = zonesAdjacentes;
        this.proprietaire=null;
        this.activable = activable;
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
    
    
    public ArrayList<String> getZonesAdjacentes() {
        return zonesAdjacentes;
    }
    
    public void setZonesAdjacentes(ArrayList<String> zonesAdjacentes) {
        this.zonesAdjacentes = zonesAdjacentes;
    }
    
    public Joueur getProprietaire() {
        return proprietaire;
    }
    
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    
    @Override
    public String toString(){
        return this.nom+" [prix : "+this.prix+", nbCartes : "+this.nbCartes+", pointsVictoire : "+this.pointsVictoire+", ";
    }
    
    public String tranadToString(){
        return this.nom + " " + this.activable;
    }
    
    public boolean isActivable() {
        // TODO Auto-generated method stub
        return activable;
    }
    
    public void setActivable(boolean b) {
        // TODO Auto-generated method stub
        this.activable=b;
        
    }
    
    public static void initZone(){
        London.zones=new HashMap<String,Zone>();
        try {
            /* R�cup�ration du classeur Excel (en lecture) */
            URL uri = Joueur.class.getResource("../fichier/ZonePlateau.xls");
            Workbook workbook = Workbook.getWorkbook(new File(uri.getPath()));
            
            /* Un fichier excel est compos� de plusieurs feuilles, on r�cup�re la premi�re, celle qui nous int�resse*/
            Sheet sheet = workbook.getSheet(0);
            
            
            
            //Parcour du fichier
            for(int i=1;i<21;i++){
                
                String listZ=sheet.getCell(7,i).getContents();
                String[] t=listZ.split(";");
                ArrayList<String> tmpL = new ArrayList<String>();
                for(int j=0;j<t.length;j++){
                    tmpL.add(t[j]);
                }
                Zone tmpZ=new Zone(sheet.getCell(0,i).getContents(),
                        Integer.parseInt(sheet.getCell(1,i).getContents()),
                        Integer.parseInt(sheet.getCell(2,i).getContents()),
                        Integer.parseInt(sheet.getCell(3,i).getContents()),
                        Boolean.parseBoolean(sheet.getCell(4,i).getContents()),
                        Boolean.parseBoolean(sheet.getCell(5,i).getContents()),
                        Boolean.parseBoolean(sheet.getCell(6,i).getContents()),
                        tmpL);
                London.zones.put(sheet.getCell(0,i).getContents(),tmpZ);
            }
            activationZonesVoisines();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void activationZonesVoisines() {
        Zone City = London.zones.get("City");
        Zone Southwark = London.zones.get("Southwark & Bermondsey");
        Zone Westminster = London.zones.get("Westminster");
        for(String s : City.getZonesAdjacentes()){
            System.out.println(s);
            London.zones.get(s).setActivable(true);
        }
        for(String s : Southwark.getZonesAdjacentes()){
            London.zones.get(s).setActivable(true);
        }
        for(String s : Westminster.getZonesAdjacentes()){
            London.zones.get(s).setActivable(true);
        }       
    }
    
    
    public HashSet<String> zoneInvest(){
        HashSet <String> res=new HashSet<String>();
        Collection zone=London.zones.values();
        Zone currZ;
        for(Object curr : zone){
            currZ=(Zone)curr;
            if(currZ.isActivable()&&currZ.getProprietaire()==null){
                res.add(currZ.getNom());
            }
        }
        return res;
    }
    
    public void investir(Joueur j){
        London.getPlateau().tableauZone[London.getPlateau().indiceZone(this.getNom())].getZone().setActivable(true);
        this.setProprietaire(j);
        for(String s : zonesAdjacentes){
            London.getPlateau().tableauZone[London.getPlateau().indiceZone(s)].getZone().setActivable(true);
        }
    }
}
