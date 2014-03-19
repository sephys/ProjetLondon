package model;

/**
 * La classe ColourNotFoundException est une classe fille d'Exception.
 * Celle-ci est créée lorsque la couleur d'une carte que l'on essaie
 * d'ajouter à la main d'un joueur ne correspond pas à la couleur
 * possible d'une carte du jeu {"marron", "bleue", "rose", "grise"}.
 *
 */
public class ColourNotFoundException extends Exception {

	public ColourNotFoundException(String couleur){
		System.out.println("La couleur de la carte entrée n'existe pas : " + couleur);
		System.out.println("Les seules couleurs acceptées sont : {\"marron\", \"bleue\", \"rose\", \"grise\"}");
	}
}
