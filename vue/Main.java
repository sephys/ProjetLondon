/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

/**
 *
 * @author Joke
 */
public class Main {
    
    private static London jeu;
    
    public static void main(String[] arg) {
     jeu=new London();
    }

    public static London getJeu() {
        return jeu;
    }

    public static void setJeu(London jeu) {
        Main.jeu = jeu;
    }
    
    
    
}
