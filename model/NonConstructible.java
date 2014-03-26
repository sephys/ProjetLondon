package model;

public class NonConstructible extends Carte {
	
	private String pouvoir;

	public NonConstructible(String nom, String couleur, String categorie, String pouvoir) {
		super(nom, couleur, categorie,"");
		this.setPouvoir(pouvoir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouerCarte() {
		// TODO Auto-generated method stub
		//a faire plus tard.
	}

	public String getPouvoir() {
		return pouvoir;
	}

	public void setPouvoir(String pouvoir) {
		this.pouvoir = pouvoir;
	}

}
