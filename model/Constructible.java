package model;

public class Constructible extends Carte {
	//attributs
	private int coutPose; //cout de la carte pour la poser >=0
	
	//cout pour activer une carte : 
	//[1]cout en argent >=0 ; 
	//[2]cout en carte {"cucun","choix","bleu"}
	private String [] coutActivation= new String [2];
	
	private int pointsVictoirePose; // point de victoire acquis lors de la pose de la carte >=0
	
	//gains lors de l'activation de la carte :
	//[1] gain en argent >=0
	//[2] gain en points de victoire >=0
	//[3] gain en points de pauvreté
	private int [] gainAcivation= new int[3];
	
	private String pouvoirIlli; //pouvoir persisatant de la carte
	private String pouvoirActiv;//pouvoir lors de l'activation de la carte
	private boolean aRetourne; //true si la carte est a retourné après activation sinon false
	private boolean Activable ;//true si la carte peu être activé sinon false
	
	public Constructible(String nom, String couleur, String categorie) {
		super(nom, couleur, categorie);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouerCarte() {
		// TODO Auto-generated method stub
		
	}

}
