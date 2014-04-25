package model;

import controleur.DeuxJoueurs;
import controleur.QuatreJoueurs;
import controleur.TroisJoueurs;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ArrayDeque;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import vue.JPMain;
import vue.London;
import vue.Main;

public class PouvoirBeta {
    
    private static int specialDouble = 0; // Wren //huguenots Jewish
    private static int speChoix; //
    
    //pouvoir illimité.
    public static boolean pouvoirSchool(Joueur j, Carte cD) {
        boolean res = false;
        int test = j.getPouvoir().get("School");
        if (cD.getCouleur().compareTo(j.getLastCarte().getCouleur()) != 0) {			//les cartes joueur sont de couleur différente
            if (test != 0) { 							//possède le pouvoir de l'école		
                res = true;                                                     //paye 1 pour que la carte "change de couleur"
                j.addArgent(-1);
                j.defausseMoins(cD);
                Main.getJeu().getInfos().maj_infos();
            }
        } else { 										//carte de même couleur
            res = true;
        }
        return res;
    }
    
    public static boolean pouvoirHuguenots(Joueur j) {
        boolean res = false;
        int test = j.getPouvoir().get("Huguenots");
        if (test >= 1) {
            res = true;
            j.getPouvoir().put("Huguenots", new Integer(j.getPouvoir().get("Huguenots").intValue() - 1));
            j.setPioche(2);
        }
        return res;
    }
    
    //pouvoir limité.
    public static boolean pouvoirWren(Joueur j) {
        boolean res = false;
        int test = j.getPouvoir().get("Wren");
        if (test != 0) {
            res = true;//possède le pouvoir wren
            if (specialDouble == 0) {
                //si première carte
                specialDouble = 1;				//première utilisation						//on peut utiliser le pouvoir
            } else {
                //sinon 2 carte on décremente le pouvoir wren
                
                j.getPouvoir().put("Wren", new Integer(j.getPouvoir().get("Wren").intValue()) - 1);
                specialDouble = 0;
                
            }
        } else {									// ne possède pas le pouvoir wren
            res = false;
        }
        // TODO Auto-generated method stub
        return res;
    }
    
    public static void pouvoirCoffee(Joueur J, Constructible carte) {
        JOptionPane.showMessageDialog(null, "Coffee House activé !");
        Main.getJeu().getMenudroite().getLabelInfo().setText("Choississez une carte de l'étalage");

        /*On ajoute le pouvoir dans le tableau de pouvoirs du joueur (obligatoire car on doit faire des conditions dans JBCarte)*/
        if (J.getPouvoir().get("Coffee") != null) {
            Main.getJeu().getJpChantier().getChantiers();
            J.getPouvoir().put("Coffee", new Integer(1));
        }
        // change onglet sur etélage
        Main.getJeu().getPanelOnglet().setSelectedIndex(1);
    }
    
    // done
    public static void pouvoirFireBrigade(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && (!n.getProprietaire().equals(j))) {
                // si le jouer a plus d'argent ou l'oblige à prendre un pret
                if (n.getProprietaire().getArgent() == 0) {
                    n.getProprietaire().addPret(1);
                    n.getProprietaire().addArgent(-1);
                } else {
                    n.getProprietaire().addArgent(-1);
                }
                
            }
        }
    }
    
    public static void pouvoirFleetStreet() {
        JOptionPane.showMessageDialog(null, "Fleet Street activé !");
        
        final JFrame choixJoueur = new JFrame();
     
        
        // premet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();
        
        final String nomJ1 = Main.getJeu().getListeJoueur().getSuivant().getJoueur().getNom();
        final String nomJ2 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().getNom();
        final String nomJ3 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().getNom();
        
        final JRadioButton b1 = new JRadioButton(nomJ1);
        final JRadioButton b2 = new JRadioButton(nomJ2);
        final JRadioButton b3 = new JRadioButton(nomJ3);
        
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        
        JPanel jp=new JPanel(new FlowLayout());
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);
        
        JButton p = new JButton("Ok");
        
        p.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getJoueur().addPointPauvrete(2);
                } else if (b2.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().addPointPauvrete(2);
                } else {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().addPointPauvrete(2);
                    
                }
                Main.getJeu().getInfos().maj_infos();
                
                choixJoueur.dispose();
            }
        });
        choixJoueur.setUndecorated(true);
        choixJoueur.add(new JLabel("Choisisser le joueur"),BorderLayout.NORTH);
        choixJoueur.add(p,BorderLayout.SOUTH);
        choixJoueur.add(jp);
        choixJoueur.setLocationRelativeTo(null);
        choixJoueur.pack();
        choixJoueur.setVisible(true);
        
    }
    
    public static void pouvoirLloydsOfLondon() {
        JOptionPane.showMessageDialog(null, "Lloyds of Lonfon activé !");
        for (int i = 0; i < Main.getJeu().getTabJoueur().length; i++) {
            Main.getJeu().getTabJoueur()[i].addArgent(-2);
        }
        Main.getJeu().getListeJoueur().getJoueur().addArgent(Main.getJeu().getTabJoueur().length * 2);
        Main.getJeu().getInfos().maj_infos();
        
    }
    
    public static void pouvoirOmnibus() {
        JOptionPane.showMessageDialog(null, "Omnibus activé !");
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null) {
                if (n.getProprietaire() != null) {
                    if (n.getProprietaire().equals(Main.getJeu().getListeJoueur().getJoueur())) {
                        Main.getJeu().getListeJoueur().getJoueur().addArgent(1);
                    }
                }
                
            }
            
        }
        Main.getJeu().getInfos().maj_infos();
    }
    
    public static void pouvoirPoliceForce() {
        JOptionPane.showMessageDialog(null, "Police Force activé !");

        final JFrame choixJoueur = new JFrame();
        

        // premet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();

        final String nomJ1 = Main.getJeu().getListeJoueur().getSuivant().getJoueur().getNom();
        final String nomJ2 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().getNom();
        final String nomJ3 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().getNom();

        final JRadioButton b1 = new JRadioButton(nomJ1);
        final JRadioButton b2 = new JRadioButton(nomJ2);
        final JRadioButton b3 = new JRadioButton(nomJ3);

        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        JPanel jp=new JPanel(new FlowLayout());
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);

        JButton p = new JButton("Ok");

        p.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getJoueur().addPointPauvrete(1);
                } else if (b2.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().addPointPauvrete(1);
                } else {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().addPointPauvrete(1);

                }
                Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-1);
                Main.getJeu().getInfos().maj_infos();

                choixJoueur.dispose();
            }
        });

        // enleve la possibilité de fermer la fenêtre        
        choixJoueur.setUndecorated(true);
        choixJoueur.add(new JLabel("Choisisser le joueur"),BorderLayout.NORTH);
        choixJoueur.add(jp);
        choixJoueur.add(p,BorderLayout.SOUTH);
        choixJoueur.setLocationRelativeTo(null);
        choixJoueur.pack();
        choixJoueur.setVisible(true);
    }
    
    public static void pouvoirSteamBoats() {
        JOptionPane.showMessageDialog(null, "Steamboats activé !");
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null) {
                if (n.getProprietaire() != null) {
                    if (n.getProprietaire().equals(Main.getJeu().getListeJoueur().getJoueur()) && n.isAdjacentTamise()) {

                        Main.getJeu().getListeJoueur().getJoueur().addArgent(2);
                    }
                }
            }

        }
        Main.getJeu().getInfos().maj_infos();
    }
    // Town
    public static void pouvoirTownHouse() {
        JOptionPane.showMessageDialog(null, "Town Houses activé !");
        int vp = 0;
        for (ArrayDeque<Constructible> c : Main.getJeu().getListeJoueur().getJoueur().getListeChantier()) {
            if (!c.peek().getCouleur().equals("Brun")) {
                vp++;
            }
        }
        Main.getJeu().getListeJoueur().getJoueur().addPointVictoire(vp);
        Main.getJeu().getInfos().maj_infos();

    }
    
    // Train
    public static void pouvoirNorthTrainStation() {
        JOptionPane.showMessageDialog(null, "Train Station activé !");
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null) {
                if (n.getProprietaire() != null) {
                    if (n.getProprietaire().equals(Main.getJeu().getListeJoueur().getJoueur()) && (!n.isDessousTamise())) {
                        Main.getJeu().getListeJoueur().getJoueur().addArgent(2);
                    }
                }

            }

        }
        Main.getJeu().getInfos().maj_infos();
    }
    
    public static void pouvoirSouthTrainStation(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && n.getProprietaire().equals(j) && n.isDessousTamise()) {
                j.addArgent(2);
            }
        }
    }

    //contrôle
    public static boolean peutJouer(Joueur j,Carte ca){
        boolean res=false;
        if(j.getPouvoir().get("Wren")==1||j.getPouvoir().get("School")==1){
            res=true;
        }
        if(ca.getNom().compareTo("School")==0||ca.getNom().compareTo("Coffee")==0){
            res=true;
        }
        return res;
        
    }
    

    public static void pouvoirTowerBridge(){
        JOptionPane.showMessageDialog(null, "Tower Bridge activé !");

        int fricPognonFlouzBleTunesPrunes = 0;
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        for(ArrayDeque<Constructible> c : j.getListeChantier()){
            if(c.peek().getCouleur().equals("Brun")){
                fricPognonFlouzBleTunesPrunes++;
            }
        }
        j.addArgent(fricPognonFlouzBleTunesPrunes);
    }
    

    public static void pouvoirMilbankPrison()
    {
        JOptionPane.showMessageDialog(null, "Milbank Prison activé !");
        
        final JFrame choixDefausse = new JFrame();
        
       
        //choixDefausse.setLayout(new GridLayout(4, 1));
        //choixDefausse.setSize(300, 200);
        

        // premet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();
        

        final JRadioButton b1 = new JRadioButton("1");
        final JRadioButton b2 = new JRadioButton("2");
        final JRadioButton b3 = new JRadioButton("3");
        
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        
        JPanel jp=new JPanel(new FlowLayout());
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);

       
        JButton p = new JButton("Ok");

        p.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.isSelected()) {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(1);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-1);
                    Main.getJeu().getListeJoueur().getJoueur().addPointVictoire(1);
                } else if (b2.isSelected()) {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(2);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-2);
                    Main.getJeu().getListeJoueur().getJoueur().addPointVictoire(2);
                } else {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(3);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-3);
                    Main.getJeu().getListeJoueur().getJoueur().addPointVictoire(2);

                }
                
                Main.getJeu().getInfos().maj_infos();

                choixDefausse.dispose();
            }
        });

        // enleve la possibilité de fermer la fenêtre        
        choixDefausse.setUndecorated(true);

        choixDefausse.add(new JLabel("Choisissez le nombre de cartes à defausser"),BorderLayout.NORTH);
        
        choixDefausse.add(jp,BorderLayout.CENTER);
        choixDefausse.add(p,BorderLayout.SOUTH);
        choixDefausse.setLocationRelativeTo(null);
        choixDefausse.pack();
        choixDefausse.setVisible(true);
        
    }
    
    public static void pouvoirBrixtonPrison()
    {
        JOptionPane.showMessageDialog(null, "Brixton Prison activé !");
        
        final JFrame choixDefausse = new JFrame();
        
       
        //choixDefausse.setLayout(new GridLayout(4, 1));
        //choixDefausse.setSize(300, 200);
        

        // premet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();
        

        final JRadioButton b1 = new JRadioButton("1");
        final JRadioButton b2 = new JRadioButton("2");
        final JRadioButton b3 = new JRadioButton("3");
        
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        
        JPanel jp=new JPanel(new FlowLayout());
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);

       
        JButton p = new JButton("Ok");

        p.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.isSelected()) {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(1);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-1);
                    
                } else if (b2.isSelected()) {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(2);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-2);
                   
                } else {
                    Main.getJeu().getListeJoueur().getJoueur().setDefausse(3);
                    Main.getJeu().getListeJoueur().getJoueur().addPointPauvrete(-3);
                    

                }
                
                Main.getJeu().getInfos().maj_infos();

                choixDefausse.dispose();
            }
        });

        // enleve la possibilité de fermer la fenêtre        
        choixDefausse.setUndecorated(true);

        choixDefausse.add(new JLabel("Choisissez le nombre de cartes à defausser"),BorderLayout.NORTH);
        
        choixDefausse.add(jp,BorderLayout.CENTER);
        choixDefausse.add(p,BorderLayout.SOUTH);
        choixDefausse.setLocationRelativeTo(null);
        choixDefausse.pack();
        choixDefausse.setVisible(true);
        
    }
    
    

    public static void pouvoirWorkHouse(){
        JOptionPane.showMessageDialog(null, "WorkHouse activé !");
        int paupersEnMain = 0;
        Joueur j = Main.getJeu().getListeJoueur().getJoueur();
        ArrayList<Carte> alc = j.getMain();
        for(Carte c : alc){
            if(c.getNom().equals("Paupers")){
                paupersEnMain++;
            }
        }
        final Frame f = new Frame("Pouvoir WorkHouse");
        JLabel jlab = new JLabel("Combien de Paupers voulez-vous défausser ?");
        JPanel jp = new JPanel();
        f.add(jp);
        final JComboBox jcb = new JComboBox();
        for(int i = 0; i < paupersEnMain; i++){
            jcb.addItem("" + i);
        }
        jcb.setSelectedIndex(0);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jcb.getSelectedIndex();
                supprimePaupers(i);
                f.dispose();
            }
        });
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(
                (screenSize.width-f.getWidth())/2,
                (screenSize.height-f.getHeight())/2
        );
        jp.add(jlab, BorderLayout.NORTH);
        jp.add(jcb, BorderLayout.EAST);
        jp.add(ok, BorderLayout.SOUTH);
        f.setVisible(true);
        f.pack();
    }
    
    public static void supprimePaupers(int nbASuppr){
        int[] indices = new int[nbASuppr];
        int indice = 0;
        int compteur = 0;
        Joueur courrant = Main.getJeu().getListeJoueur().getJoueur();
        ArrayList<Carte> m = courrant.getMain();
        for(Carte c : m){
            if(c.getNom().equals("Paupers")){
                indices[indice] = compteur;
                indice++;
            }
            compteur++;
        }
        
        JPMain main = Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()];
        for(int i = 0; i < indices.length; i++){
            m.remove(indices[i]);
            main.removeCarteNom("Paupers");
        }
        courrant.addArgent(nbASuppr);
        courrant.addPointPauvrete(-nbASuppr);
    }
}
