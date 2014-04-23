package model;

public class Constructible extends Carte {
	//attributs
	private int coutPose; //cout de la carte pour la poser >=0

	//cout pour activer une carte : 
	//[1]cout en argent >=0 ; 
	//[2]cout en carte {"Aucun","choix","bleu"}
	private String [] coutActivation= new String [2];

	private int pointsVictoirePose; // point de victoire acquis lors de la pose de la carte >=0

	//gains lors de l'activation de la carte :
	//[1] gain en argent >=0
	//[2] gain en points de victoire >=0
	//[3] gain en points de pauvret�
	private int [] gainAcivation= new int[3];

	private String pouvoirIlli; //pouvoir persisatant de la carte
	private String pouvoirActiv;//pouvoir lors de l'activation de la carte
	private boolean aRetourne; //true si la carte est a retourn� apr�s activation sinon false
	private boolean activable ;//true si la carte peu �tre activ� sinon false

	public Constructible(String nom, String couleur, String categorie) {
		super(nom, couleur, categorie,"");

		// TODO Auto-generated constructor stub
	}

	public Constructible(String nom, String couleur, String categorie,String path,
			int coutPose, String[] coutActivation, int pointsVictoirePose,
			int[] gainAcivation, String pouvoirIlli, String pouvoirActiv,
			boolean aRetourne, boolean activable) {
		super(nom, couleur, categorie,path);
		this.coutPose = coutPose;
		this.coutActivation = coutActivation;
		this.pointsVictoirePose = pointsVictoirePose;
		this.gainAcivation = gainAcivation;
		this.pouvoirIlli = pouvoirIlli;
		this.pouvoirActiv = pouvoirActiv;
		this.aRetourne = aRetourne;
		this.activable = activable;
	}

	public int getCoutPose() {
		return coutPose;
	}

	public void setCoutPose(int coutPose) {
		this.coutPose = coutPose;
	}

	public String[] getCoutActivation() {
		return coutActivation;
	}

	public void setCoutActivation(String[] coutActivation) {
		this.coutActivation = coutActivation;
	}

	public int getPointsVictoirePose() {
		return pointsVictoirePose;
	}

	public void setPointsVictoirePose(int pointsVictoirePose) {
		this.pointsVictoirePose = pointsVictoirePose;
	}

	public int[] getGainAcivation() {
		return gainAcivation;
	}

	public void setGainAcivation(int[] gainAcivation) {
		this.gainAcivation = gainAcivation;
	}

	public String getPouvoirIlli() {
		return pouvoirIlli;
	}

	public void setPouvoirIlli(String pouvoirIlli) {
		this.pouvoirIlli = pouvoirIlli;
	}

	public String getPouvoirActiv() {
		return pouvoirActiv;
	}

	public void setPouvoirActiv(String pouvoirActiv) {
		this.pouvoirActiv = pouvoirActiv;
	}

	public boolean isARetourne() {
		return aRetourne;
	}

	public void setARetourne(boolean aRetourne) {
		this.aRetourne = aRetourne;
	}

	public boolean isActivable() {
		return activable;
	}

	public void setActivable(boolean activable) {
		this.activable = activable;
	}


	public String toString(){
		StringBuffer tmpStr=new StringBuffer(super.toString());
		//tmpStr.append("\n Cout : "+this.getCoutPose());
		//tmpStr.append("\n Pouvoir : "+this.getPouvoirIlli()+"\n");
		return new String(tmpStr);
	}

	@Override
	public void jouerCarte(Joueur currJ,int ind) { 
		// TODO Auto-generated method stub
		String pouv = this.getPouvoirIlli();
		if(pouv.compareTo("vide")!=0){
			if(currJ.getPouvoir().get(pouv)!=null){
				currJ.getPouvoir().put(pouv,new Integer(currJ.getPouvoir().get(pouv).intValue()+1));
			}else{
				currJ.getPouvoir().put(pouv,new Integer(1));
			}
		}
		currJ.setArgent(currJ.getArgent()-this.getCoutPose());
		currJ.setPointVictoire(currJ.getPointVictoire()+this.getPointsVictoirePose());

		Constructible sousCarte=currJ.getListeChantier().get(ind).peekFirst();
		if(sousCarte!=null){
                    
			String pouvoir=sousCarte.getPouvoirIlli();
			if(currJ.getPouvoir().get(pouvoir)!=null){
				currJ.getPouvoir().put(pouvoir,new Integer(currJ.getPouvoir().get(pouvoir).intValue()-1));
			}
		}else{
			currJ.getListeChantier().get(ind).add(this);
		}

		currJ.getListeChantier().get(ind).add(this);
		currJ.getMain().remove(this);
	}

	public void activerCarte(Joueur currJ){
		if(this.activable){
                    // MODIFIER CODE POUR LES POUVOIRS DANDHUY IF ELSE
			currJ.setArgent(currJ.getArgent()-Integer.parseInt(this.coutActivation[0]));
			String coutCarte=this.coutActivation[1];
			if(coutCarte.compareTo("aucun")!=0){
				if(coutCarte.compareTo("choix")==0){
					currJ.setDefausse(1);
				}else{
					currJ.setLastCarte(this);
					currJ.setDefausse(1);
				}
			}
			currJ.setArgent(currJ.getArgent()+this.gainAcivation[0]);
			currJ.setPointVictoire(currJ.getPointVictoire()+this.gainAcivation[1]);
			currJ.setPointPauvrete(currJ.getPointPauvrete()+this.gainAcivation[2]);
                        
                        // Pouvoirs
                        if(this.pouvoirActiv=="Fire")
                        {
                            PouvoirBeta.pouvoirFireBrigade(currJ);
                        }
                        else if(this.pouvoirActiv=="Street")
                        {
                            PouvoirBeta.pouvoirFleetStreet();
                        }
                        else if(this.pouvoirActiv=="Omnibus")
                        {
                            PouvoirBeta.pouvoirOmnibus();
                        }
                        else if(this.pouvoirActiv=="Lloyds")
                        {
                            PouvoirBeta.pouvoirLloydsOfLondon();
                        }
                        else if(this.pouvoirActiv=="Cofee")
                        {
                           // PouvoirBeta.
                        }
                        
                        
                        
			if(this.aRetourne){
				this.activable=false;
			}
		}
                
                
	}
}
