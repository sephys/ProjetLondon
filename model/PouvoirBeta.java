package model;

import vue.London;

public class PouvoirBeta {
    
    private static int specialDouble = 0; // Wren //huguenots Jewish
    private static int speChoix; //
    
    //pouvoir illimité.
    public static boolean pouvoirSchool(Joueur j, String colorJ, String colorD) {
        boolean res = false;
        
        if (j.getPouvoir().get("School") == null) {
            j.getPouvoir().put("School", new Integer(0));
        }
        int test = j.getPouvoir().get("School");
        if (colorJ.compareTo(colorD) != 0) {			//les cartes joueur sont de couleur différente
            if (test != 0) { 							//possède le pouvoir de l'école
                System.out.println("utilisation school" + j.getArgent());
                j.addArgent(-1);		//paye 1 pour que la carte "change de couleur"
                res = true;
                London.getInfos().maj_infos();
            }
        } else { 										//carte de même couleur
            res = true;
        }
        return res;
    }
    
    //pouvoir limité.
    public static boolean pouvoirWren(Joueur j) {
        boolean res = false;
        if (j.getPouvoir().get("Wren") == null) {
            j.getPouvoir().put("Wren", new Integer(0));
        }
        int test = j.getPouvoir().get("Wren");
        if (test != 0) { 							//possède le pouvoir wren
            if (specialDouble == 0) {				//si première carte
                specialDouble = 1;				//première utilisation
                res = true;						//on peut utiliser le pouvoir
            } else {								//sinon 2 carte on décremente le pouvoir wren
                j.getPouvoir().put("Wren", new Integer(j.getPouvoir().get("Wren").intValue()) - 1);
            }
        } else {									// ne possède pas le pouvoir wren
            res = false;
        }
        // TODO Auto-generated method stub
        return res;
    }
    
    public static void pouvoirFireBrigade(Joueur j) {
        for (Zone n : London.zones.values()) {
            if (n != null && (!n.getProprietaire().equals(j))) {
                n.getProprietaire().addArgent(-1);
            }
        }
    }
    
    public static void pouvoirFleetStreet(Joueur implique) {
        implique.addPointPauvrete(2);
    }
    
    public static void pouvoirLloydsOfLondon(Joueur j) {
        for (int i = 0; i < London.getTabJoueur().length; i++) {
            London.getTabJoueur()[i].addArgent(-2);
        }
        j.addArgent(London.getTabJoueur().length * 2);
    }
    
    public static void pouvoirOmnibus(Joueur j) {
        for (Zone n : London.zones.values()) {
            if (n != null && n.getProprietaire().equals(j)) {
                j.addArgent(1);
            }
        }
    }
    
    public void pouvoirPoliceForce(Joueur donneur, Joueur donne) {
        donneur.addPointPauvrete(-1);
        donne.addPointPauvrete(1);
    }
    
    public void pouvoirSteamBoats(Joueur j) {
        for (Zone n : London.zones.values()) {
            if (n != null && n.getProprietaire().equals(j) && n.isAdjacentTamise()) {
                j.addArgent(2);
            }
        }
    }
    
    public void pouvoirTownHouse(Joueur j) {
        int argent = 0;
        for (Carte c : j.getMain()) {
            if (!c.getCouleur().equals("marron")) {
                argent++;
            }
        }
        j.addArgent(argent);
    }
    
    public static void pouvoirNorthTrainStation(Joueur j){
        for(Zone n : London.zones.values()){
            if(n != null && n.getProprietaire().equals(j) && (!n.isDessousTamise())){
                j.addArgent(2);
            }
        }
    }
    
    public static void pouvoirSouthTrainStation(Joueur j){
        for(Zone n : London.zones.values()){
            if(n != null && n.getProprietaire().equals(j) && n.isDessousTamise()){
                j.addArgent(2);
            }
        }
    }
    
    
}
