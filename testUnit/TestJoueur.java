package testUnit;

import static org.junit.Assert.*;

import model.Carte;
import model.Deck;
import model.Joueur;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJoueur {
	Joueur test;
	Deck d;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.test=new Joueur("j1");
		d=new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPiocheCarteSimple() {
		Carte e=d.peekFirst();
		test.piocheCarte(d.poll());
		assertEquals(test.getMain().get(0),e);
		
	}
	
	@Test
	public void testPiocheCarteComplexe() {
		Joueur tmptest=new Joueur("test");
		for(int i=0;i<6;i++){
			test.piocheCarte(d.peekFirst());
			tmptest.piocheCarte(d.poll());
		}
		assertEquals(tmptest.getMain().toString(),test.getMain().toString());
		
	}
}
