/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import controleur.JPAccueilControl;
import model.Carte;
import model.Zone;

/**
 *
 * @author Joke
 */
public class Main {
    
    private static London jeu;
    
    public static void main(String[] arg) {
     jeu=new London();
     jeu.setDeck(Carte.initDeck());
     Zone.initZone();
    }

    public static London getJeu() {
        return jeu;
    }

    public static void setJeu(London jeu) {
        Main.jeu = jeu;
    }
    
    
    
}
