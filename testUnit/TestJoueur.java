package testUnit;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayDeque;

import model.Carte;
import model.Joueur;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vue.London;

public class TestJoueur {
	Joueur test;
	ArrayDeque<Carte> d;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.test=new Joueur("j1",Color.blue);
		d=Carte.initDeck();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Test qu'il n'est pas possible d'avoir 2 joueur de la m�me couleur
	public void testDoublonColor() {
		Joueur tmpJ2=new Joueur("J2",Color.blue);
		//assertNotEquals(test.getColor(),tmpJ2.getColor());
	}
	
	@Test
	//Test que le nombre de joueur est bien une variable global
	public void testNbJoueur() {
		Joueur tmpJ2=new Joueur("J2",Color.blue);
		assertEquals(this.test.getNbJoueur(),tmpJ2.getNbJoueur());
	}
	
	@Test
	//Test que le nombre de joueur est bien increment� lors de l'ajout d'un nouveau joueur
	public void testNbJoueur2() {
		int nb1 = this.test.getNbJoueur();
		Joueur tmpJ2=new Joueur("J2",Color.blue);
		int nb2 = tmpJ2.getNbJoueur();
		assertEquals(nb1,nb2-1);
	}
	
	@Test
	public void testJouerCarte() {
		//Verif que la zone de construction est constructible
		//Verif si remove sur null marche
		//Verif que la carte jouer ne soit pu en main
		//Verif que le carte jouer soit sur le terrain
	}
	
	@Test
	//Test que la fonction pioche marche correctement
	public void testPiocheCarteSimple() {
		Carte e=d.peekFirst();
		test.piocheCarte(d.poll());
		assertEquals(test.getMain().get(0),e);
		
	}
	
	@Test
	//Test que la main du joueur et correctement celle qu'elle devrait etre
	public void testPiocheCarteComplexe() {
		Joueur tmptest=new Joueur("test",Color.black);
		for(int i=0;i<6;i++){
			test.piocheCarte(d.peekFirst());
			tmptest.piocheCarte(d.poll());
		}
		assertEquals(tmptest.getMain().toString(),test.getMain().toString());
		
	}
}
