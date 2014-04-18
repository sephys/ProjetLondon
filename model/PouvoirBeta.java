package model;

public class PouvoirBeta {

	private static int specialDouble =0; // Wren //huguenots Jewish
	private  static int speChoix; //




	//pouvoir illimité.

	public static boolean pouvoirSchool(Joueur j,String colorJ,String colorD){
		boolean res=false;
		int test=j.getPouvoir().get("School");
		if(colorJ.compareTo(colorD)!=0){			//les cartes joueur sont de couleur différente
			if(test!=0){ 							//possède le pouvoir de l'école
				j.setArgent(j.getArgent()-1);		//paye 1 pour que la carte "change de couleur"
				res=true;
			}
		}else{ 										//carte de même couleur
			res=true;
		}
		return res;
	}



	//pouvoir limité.

	public static boolean pouvoirWren(Joueur j) {
		boolean res=false;
		int test=j.getPouvoir().get("Wren");
		if(test!=0){ 							//possède le pouvoir wren
			if(specialDouble==0){				//si première carte 
				specialDouble=1;				//première utilisation
				res=true;						//on peut utiliser le pouvoir
			}else{								//sinon 2 carte on décremente le pouvoir wren
				j.getPouvoir().put("Wren",new Integer(j.getPouvoir().get("Wren").intValue())-1);
			}
		}else{									// ne possède pas le pouvoir wren
			res=false;
		}
		// TODO Auto-generated method stub
		return false;
	}
}




