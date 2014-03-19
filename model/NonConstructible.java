package model;

public class NonConstructible extends Carte {
	
	private String pouvoir;

	public NonConstructible(String nom, String couleur, String categorie, String pouvoir) {
		super(nom, couleur, categorie);
		this.pouvoir=pouvoir;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouerCarte() {
		// TODO Auto-generated method stub
		//a faire plus tard.
	}

}
