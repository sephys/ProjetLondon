package model;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//cr�ation du deck
		Deck d=new Deck();
		for(Carte a: d){
			System.out.println("Categorie : "+a.getCategorie()+" Nom : "+a.getNom());
		}
		//m�lange du deck;
		System.out.println(d.size());
	}
	

}

