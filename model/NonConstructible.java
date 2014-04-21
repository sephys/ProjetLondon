package model;

public class NonConstructible extends Carte {
	
	private String pouvoir;

	public NonConstructible(String nom, String couleur, String categorie,String path, String pouvoir) {
		super(nom, couleur, categorie,path);
		this.setPouvoir(pouvoir);
		// TODO Auto-generated constructor stub
	}


	public String getPouvoir() {
		return pouvoir;
	}

	public void setPouvoir(String pouvoir) {
		this.pouvoir = pouvoir;
	}
	
	public String toString(){
		
		//return new String(super.toString()+"\n Pouvoir : "+this.getPouvoir()+"\n");
		return super.toString();
	}


	@Override
	public void jouerCarte(Joueur currJ, int ind) {
		// TODO Auto-generated method stub
		String pouv = this.getPouvoir();
		if(pouv.compareTo("vide")!=0){
			if(currJ.getPouvoir().get(pouv)!=null){
				currJ.getPouvoir().put(pouv,new Integer(currJ.getPouvoir().get(pouv).intValue()+1));
			}else{
				currJ.getPouvoir().put(pouv,new Integer(1));
			}
		}
		if(this.getNom()=="Paupers"){
			System.out.println("Ne peut pas être joué");
		}
		currJ.getMain().remove(this);
	}

}
