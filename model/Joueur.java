package model;

import controleur.MenuDroiteControl;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import vue.JBCarte;
import vue.London;
import vue.Main;

public class Joueur implements Serializable{
    
    private String nom;
    private ArrayList<Carte> main;
    private HashMap<String, Integer> pouvoir;
    private ArrayList<ArrayDeque<Constructible>> listeChantier;
    private int pointVictoire;
    private int pointPauvrete;
    private int argent;
    private int nbPret;
    private int placeJoueur; // la place du joueur dans le cycle
    private int defausse; // savoir combien de cartes le joueur doit se défausser
    private int pioche; // savoir combien de cartes le joueur peut piocher
    // private boolean finTourPiocheCarte; // savoir si le joueur a choisi l'action 3 cartes
    private boolean finitTour; // savoir si le joueur a le droit finit son tour
    private Color color;
    //private static String piocheDefausse="pioche";   // Permet de savoir si le joueur est en train de piocher ou de defausser
    private Carte lastCarte;
    private String derniereAction;  // Jouer des cartes | Investir | Restaurer la ville | Piocher 3 cartes
    MenuDroiteControl m;
    
    
    
    
    
    public Joueur(String nom,Color color) {
        this.nom = nom;
        this.main = new ArrayList<Carte>();
        this.pouvoir = new HashMap<String, Integer>();
        this.pointVictoire = 0;
        this.argent = 5;
        this.pointPauvrete = 5;
        this.nbPret = 0;
        this.color=color;
        this.listeChantier = new ArrayList(new ArrayDeque<Constructible>());
        initialisePouvoir();
        m=new MenuDroiteControl();
        this.derniereAction = "";
    }
    
    public void initialisePouvoir(){
        pouvoir.put("Bank", 0);
        pouvoir.put("Brixton Prison", 0);
        pouvoir.put("School",0);
        pouvoir.put("Wren",0);
        pouvoir.put("Huguenots",0);
        pouvoir.put("Coffee",0);
        pouvoir.put("University", 0);
        //pouvoir.put(nom, argent);
    }
    
    public Color getColor() {
        return color;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public ArrayList<Carte> getMain() {
        return main;
    }
    
    public void setMain(ArrayList<Carte> main) {
        this.main = main;
    }
    
    public HashMap<String, Integer> getPouvoir() {
        return pouvoir;
    }
    
    public void setPouvoir(HashMap<String, Integer> pouvoir) {
        this.pouvoir = pouvoir;
    }
    
    public int getPointVictoire() {
        return pointVictoire;
    }
    
    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }
    
    public int getPointPauvrete() {
        return pointPauvrete;
    }
    
    public void setPointPauvrete(int pointPauvrete) {
        this.pointPauvrete = pointPauvrete;
    }
    
    public int getPioche() {
        return pioche;
    }
    
    public void setPioche(int pioche) {
        this.pioche = pioche;
        London.dndListener.setDragEnable(false);
        m.disableAll();
        if(Main.getJeu().getListeJoueur().getFinTour()<=0){
            Main.getJeu().getMenudroite().getPiocher().setEnabled(true);
        }
        else{
            Main.getJeu().getMenudroite().getPiocher().setEnabled(false);
            Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
        }
        //JBCarte.setDoubleClick(true);
        
        Main.getJeu().getMenudroite().getLabelInfo().setText("Vous devez piocher "+pioche+" cartes");
        //London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
        //JBCarte.setDoubleClick(true);
        Main.getJeu().getMenudroite().repaint();
        Main.getJeu().getMenudroite().revalidate();
        
    }
    
    public void piocheMoins() {
        this.pioche--;
        if (this.pioche == 0) {
            //JBCarte.setDoubleClick(false);
            if (this.finitTour) // fin du tour du joueur
            {
                London.dndListener.setDragEnable(true);
                m.setFinTour();
                
            } else // une pioche normal
            {
                m.enableAll();
                Main.getJeu().getMenudroite().getPiocher().setEnabled(false);
                Main.getJeu().getMenudroite().getFinTour().setEnabled(false);
                Main.getJeu().getMenudroite().getLabelInfo().setText("Vous devez choisir une action");
            }
        }
    }
    
    public int getDefausse() {
        return defausse;
    }
    
    public void setDefausse(int defausse) {
        this.defausse = defausse;
    }
    
    public void defausseMoins() {
        this.defausse--;
        if (this.defausse == 0 && Main.getJeu().getListeJoueur().getJoueur().isFinitTour()) {
            m.setFinTour();
        }
    }
    
    public int getArgent() {
        return argent;
    }
    
    public void setArgent(int argent) {
        this.argent = argent;
    }
    
    public int getNbPret() {
        return nbPret;
    }
    
    public void setNbPret(int nbPret) {
        this.nbPret = nbPret;
    }
    
    public ArrayList<ArrayDeque<Constructible>> getListeChantier() {
        return listeChantier;
    }
    
    public void setListeChantier(ArrayList<ArrayDeque<Constructible>> listeChantier) {
        this.listeChantier = listeChantier;
    }
    
    public int getPlaceJoueur() {
        return placeJoueur;
    }
    
    public void setPlaceJoueur(int placeJoueur) {
        this.placeJoueur = placeJoueur;
    }
    
    public void piocheCarte(Carte e) { 	//ajout de la carte dans la main
        if (main.isEmpty()) {
            this.main.add(e);			//si lamain est vide on ajoute la carte directement
        } else {
            Carte temp;
            int i = 0;
            boolean arret = false;
            while (i < this.main.size() && !arret) { //sinon on parcour la main
                temp = this.main.get(i);
                if (temp.getCouleur().compareTo(e.getCouleur()) == 0) { //d�s que l'on trouve une carte de la m�me couleur
                    if (i < this.main.size() - 1) { //si on est pas � la fin de la main, on ajoute la carte pioch� apr�s la carte courante
                        this.main.add(i + 1, e);
                    } else {
                        this.main.add(e); //sinon on ajoute la carte a la fin
                    }
                    arret = true; //on sort de la boucle
                }
                i++;
            }
            if (!arret) { //dans le cas ou on n'as trouv� aucune carte dans la main de la m�me couleur qe la carte pioch�
                this.main.add(e); //on ajoute la carte a la fin
            }
        }
    }
    
    public boolean peutInvestir(Zone z) { //verification si le joueur possde assez d'argent
        boolean res = true;
        if (this.argent < z.getPrix()) {
            res = false;
        }
        return res;
    }
    
    public void nouveauChantier() { //ajoute un nouveau chantier
        this.listeChantier.add(new ArrayDeque<Constructible>());
    }
    
    public Carte getCarteMain(int index) {
        return this.main.get(index);
    }
    
    public String toString() {
        String res;
        StringBuffer tmpRes = new StringBuffer("Joueur :");
        tmpRes.append("\n" + this.getNom());
        tmpRes.append("\n" + this.getMain());
        tmpRes.append("\n" + this.getNbPret());
        tmpRes.append("\n" + this.getArgent());
        res = new String(tmpRes);
        return res;
    }
    
    /* public boolean isFinTourPiocheCarte() {
    return finTourPiocheCarte;
    }*/
    
    public boolean isFinitTour() {
        return finitTour;
    }
    
    /* public void setFinTourPiocheCarte(boolean bool) {
    this.finTourPiocheCarte = bool;
    }*/
    
    public void setFinitTour(boolean finitTour) {
        this.finitTour = finitTour;
    }
    
    public void addPointVictoire(int pointVictoire){
        this.pointVictoire += pointVictoire;
    }
    
    public void addPointPauvrete(int pointPauvrete){
        this.pointPauvrete += pointPauvrete;
        if(this.pointPauvrete < 0){
            this.pointPauvrete = 0;
        }
    }
    
    public void addArgent(int argent){
        this.argent += argent;
    }
    
    public void addPret(int nbPret){
        this.nbPret += nbPret;
        if(pouvoir.get("Bank") >= 1){
            this.addArgent(12*nbPret);
        }else{
            this.addArgent(10*nbPret);
        }
    }
    
    public boolean payeConstruction(Carte depense) {
        boolean res = false;
        if (depense.getCouleur().compareTo(lastCarte.getCouleur()) == 0) {
            res = true; this.defausseMoins(depense);
        }
        return res;
    }
    /*   public String getPiocheDefausse(){
    return this.piocheDefausse;
    }
    
    public void setPiocheDefausse(String piocheDefausse){
    this.piocheDefausse = piocheDefausse;
    }*/
    
    public void activerCarte(Constructible actCarte){
        actCarte.activerCarte(this);
        
    }
    
    
    
    //retourne le nombre de carte d'une couleur donnée (dans la main du joueur)
    public int nb_carte_couleur(String couleur){
        int res=0;
        for(Carte carte:main){
            if(carte.getCouleur()==couleur){
                res++;
            }
        }
        return res;
    }
    
    public void setLastCarte(Constructible constructible) {
        // TODO Auto-generated method stub
        this.lastCarte=constructible;
        
    }
    
    public Carte getLastCarte() {
        return this.lastCarte;
    }
    
    public void setDerniereAction(String s){
        this.derniereAction = s;}
    public void jouerCarte(Carte defausse, Carte carteJouer, int ind) {
        carteJouer.jouerCarte(this, ind);
        this.getMain().remove(defausse);
    }
    
    public boolean jouerCarte2(Carte carteJouer, int ind) {
        boolean res = false;
        this.finitTour = true;
        
        carteJouer.jouerCarte(this, ind);
        this.lastCarte = carteJouer;
        if (carteJouer.getClass() == Constructible.class) { //si constructible
            if (PouvoirBeta.pouvoirWren(this) || "Coffee House".equals(carteJouer.getNom())) {
                this.defausse = 0;
                
            } else {				//besoin de dépenser une carte
                this.defausse = 1;
            }
        }
        this.getMain().remove(carteJouer);
        return res;
    }
    
    public String getDerniereAction(){
        return this.derniereAction;
    }
    
    public void defausseMoins(Carte defausse) {
        this.defausse--;
        this.main.remove(defausse);
        this.lastCarte = null;
        if (this.defausse == 0 && Main.getJeu().getListeJoueur().getJoueur().isFinitTour()) {
            m.setFinTour();
        }
    }
    
    /**
     * Retourne l'index du chantier ou se trouve la carte recherchée ou -1 si la carte n'existe pas
     * @param nomCarte
     * @return
     */
    public int indexCarte(String nomCarte) {
        int i=0;
        int res=-1;
        while (i < Main.getJeu().getListeJoueur().getJoueur().getListeChantier().size()) {
            if (Main.getJeu().getListeJoueur().getJoueur().getListeChantier().get(i).peekFirst().getNom().equals(nomCarte)) {
                res=i;
            }
            i++;
        }
        return res;
    }

	public int finPartie() {
		// TODO Auto-generated method stub
		
		//remboursePret
		int nbRemboursable=this.getArgent()%15;
		while(nbRemboursable-nbPret>=0&&nbPret>0){
			this.nbPret--;
			nbRemboursable--;
			this.argent-=15;
		}
		//si il reste des pret nom rembourse on prent 1PP par pret non rembourse
		while(nbPret>0){
			this.pointPauvrete+=7;
			nbPret--;
		}
		//si il reste de l'argent
		int rest=this.argent%3;
		this.pointVictoire+=rest;
		
		//si il reste des carte en main
		this.pointPauvrete+=this.getMain().size();
		return this.pointPauvrete;
	}
}
