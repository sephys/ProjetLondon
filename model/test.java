package model;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//création du deck
		Deck d=new Deck();
		for(Carte a: d){
			System.out.println("Categorie : "+a.getCategorie()+" Nom : "+a.getNom());
		}
		//mélange du deck;
		System.out.println(d.size());
	}
	

}

