package model;

/**
 * La classe ColourNotFoundException est une classe fille d'Exception.
 * Celle-ci est cr��e lorsque la couleur d'une carte que l'on essaie
 * d'ajouter � la main d'un joueur ne correspond pas � la couleur
 * possible d'une carte du jeu {"marron", "bleue", "rose", "grise"}.
 *
 */
public class ColourNotFoundException extends Exception {

	public ColourNotFoundException(String couleur){
		System.out.println("La couleur de la carte entr�e n'existe pas : " + couleur);
		System.out.println("Les seules couleurs accept�es sont : {\"marron\", \"bleue\", \"rose\", \"grise\"}");
	}
}
