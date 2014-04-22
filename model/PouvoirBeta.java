package model;

import vue.London;
import vue.Main;

public class PouvoirBeta {
    
    
    private static int specialDouble = 0; // Wren //huguenots Jewish
    private static int speChoix; //
    
    //pouvoir illimité.
    public static boolean pouvoirSchool(Joueur j, String colorJ, String colorD) {
        boolean res = false;
        int test = j.getPouvoir().get("School");
        if (colorJ.compareTo(colorD) != 0) {			//les cartes joueur sont de couleur différente
            if (test != 0) { 							//possède le pouvoir de l'école
                System.out.println("utilisation school" + j.getArgent());
                j.addArgent(-1);		//paye 1 pour que la carte "change de couleur"
                res = true;
                Main.getJeu().getInfos().maj_infos();
            }
        } else { 										//carte de même couleur
            res = true;
            System.out.println("res school" + res);
        }
        return res;
    }
    
    public static boolean pouvoirHuguenots(Joueur j){
    	boolean res=false;
    	System.out.println(j.isFinitTour());
    	int test = j.getPouvoir().get("Huguenots");
    	if(test>=1){
    		res=true;
    		j.getPouvoir().put("Huguenots",new Integer(j.getPouvoir().get("Huguenots").intValue()-1));
    		j.setPioche(2);
    	}
    	return res;
    }
    
    //pouvoir limité.
    public static boolean pouvoirWren(Joueur j) {
        boolean res = false;
        int test = j.getPouvoir().get("Wren");
        if (test != 0) { 
            res=true;//possède le pouvoir wren
            if (specialDouble == 0) {
                //si première carte 
                specialDouble = 1;				//première utilisation						//on peut utiliser le pouvoir
            } else {
                //sinon 2 carte on décremente le pouvoir wren

                j.getPouvoir().put("Wren", new Integer(j.getPouvoir().get("Wren").intValue()) - 1);
                specialDouble = 0;
                
            }
        } else {									// ne possède pas le pouvoir wren
            res = false;
        }
        // TODO Auto-generated method stub
        return res;
    }
    
    
    public static void pouvoirFireBrigade(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && (!n.getProprietaire().equals(j))) {
                n.getProprietaire().addArgent(-1);
            }
        }
    }
    
    
    public static void pouvoirFleetStreet(Joueur implique) {
        implique.addPointPauvrete(2);
    }
    
    public static void pouvoirLloydsOfLondon(Joueur j) {
        for (int i = 0; i < Main.getJeu().getTabJoueur().length; i++) {
            Main.getJeu().getTabJoueur()[i].addArgent(-2);
        }
        j.addArgent(Main.getJeu().getTabJoueur().length * 2);
    }
    
    public static void pouvoirOmnibus(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
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
        for (Zone n : Main.getJeu().getZones().values()) {
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
        for(Zone n : Main.getJeu().getZones().values()){
            if(n != null && n.getProprietaire().equals(j) && (!n.isDessousTamise())){
                j.addArgent(2);
            }
        }
    }
    
    public static void pouvoirSouthTrainStation(Joueur j){
        for(Zone n : Main.getJeu().getZones().values()){
            if(n != null && n.getProprietaire().equals(j) && n.isDessousTamise()){
                j.addArgent(2);
            }
        }
    }
    
}
