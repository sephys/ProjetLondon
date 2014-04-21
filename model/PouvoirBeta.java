package model;

import vue.London;

public class PouvoirBeta {

	private static int specialDouble =0; // Wren //huguenots Jewish
	private  static int speChoix; //




	//pouvoir illimité.

	public static boolean pouvoirSchool(Joueur j,String colorJ,String colorD){
		boolean res=false;
                if(j.getPouvoir().get("School")==null){
				j.getPouvoir().put("School",new Integer(0));
			}
		int test=j.getPouvoir().get("School");
		if(colorJ.compareTo(colorD)!=0){			//les cartes joueur sont de couleur différente
			if(test!=0){ 							//possède le pouvoir de l'école
                            System.out.println("utilisation school" + j.getArgent());
                            j.addArgent(-1);		//paye 1 pour que la carte "change de couleur"
				res=true;
                                 London.getInfos().maj_infos();
			}
		}else{ 										//carte de même couleur
			res=true;
		}
		return res;
	}



	//pouvoir limité.

	public static boolean pouvoirWren(Joueur j) {
		boolean res=false;
                if(j.getPouvoir().get("Wren")==null){
				j.getPouvoir().put("Wren",new Integer(0));
			}
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




