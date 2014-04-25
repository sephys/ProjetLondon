package testUnit;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;

import model.Carte;
import model.Constructible;
import model.Joueur;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestConstructible {
	private Joueur j;
	private ArrayDeque <Carte> d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		j=new Joueur ("toto", Color.BLUE);
		d= Carte.initDeck();
		for (int i=0;i<5;i++){
			j.nouveauChantier();
			Carte tmp=d.poll();
			if(tmp.getClass()==Constructible.class){
				j.piocheCarte(tmp);
			}else{
				i--;
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		j=null;
		d=null;
	}


	@Test
	public void jouerCartePointVictoire(){
		int expected=0;
		int i=0;
		ArrayList<Carte> tmpM=(ArrayList<Carte>) j.getMain().clone();
		for(Carte tmp :tmpM){
			expected+=((Constructible)tmp).getPointsVictoirePose();
			j.jouerCarte2(tmp, i);
			i++;
		}
		assertEquals(expected,j.getPointVictoire());
	} 
	@Test
	public void jouerCarteArgent(){
		int expected=5;
		int i=0;
		ArrayList<Carte> tmpM=(ArrayList<Carte>) j.getMain().clone();
		for(Carte tmp :tmpM){
			if((expected-((Constructible)tmp).getCoutPose())<0){
				j.addPret(1);
				expected+=10;
			}
			expected-=((Constructible)tmp).getCoutPose();
			j.jouerCarte2(tmp, i);
			i++;
		}
		assertEquals(expected,j.getArgent());
	} 

	@Test
	public void testAjouterPretSimple(){
		int expected=15;
		j.addPret(1);
		assertEquals(expected,j.getArgent());
	}

	@Test
	public void testAjoutPretPouvoir(){
		int expected=17;
		j.getPouvoir().put("Bank",new Integer(1));
		j.addPret(1);
		assertEquals(expected,j.getArgent());
	}

	@Test
	public void testActivationArgent(){
		int expected=5;
		int i=0;
		ArrayList<Carte> tmpM=(ArrayList<Carte>) j.getMain().clone();
		for(Carte tmp :tmpM){
			j.getListeChantier().get(i).add((Constructible)tmp);
			i++;
		}
		i=0;
		ArrayList<ArrayDeque> tmpD=(ArrayList<ArrayDeque>) j.getListeChantier().clone();
		for(ArrayDeque tmp :tmpD){
			Constructible tmpC=j.getListeChantier().get(i).peekFirst();
			if(tmpC!=null){
				if(tmpC.getNom().compareTo("Coffee House")!=0){
					tmpC.activerCarte(j);
				}
				expected-=Integer.parseInt(tmpC.getCoutActivation()[0]);
				expected+=tmpC.getGainAcivation()[0];
			}
			i++;
		}
		assertEquals(expected,j.getArgent());
	}

	@Test
	public void testActivationVictoire(){
		int expected=0;
		System.out.println(j.getPointVictoire());
		int i=0;
		ArrayList<Carte> tmpM=(ArrayList<Carte>) j.getMain().clone();
		for(Carte tmp :tmpM){
			j.getListeChantier().get(i).add((Constructible)tmp);
			i++;
		}
		i=0;
		ArrayList<ArrayDeque> tmpD=(ArrayList<ArrayDeque>) j.getListeChantier().clone();
		System.out.println(tmpD); 
		for(ArrayDeque tmp :tmpD){
			Constructible tmpC=j.getListeChantier().get(i).peekFirst();
			if(tmpC!=null){
				if(tmpC.getNom().compareTo("Coffee House")!=0){
					tmpC.activerCarte(j);
				}
				expected+=tmpC.getGainAcivation()[1];
			}
			i++;
		}
		assertEquals(expected,j.getArgent());
		
	}
}
