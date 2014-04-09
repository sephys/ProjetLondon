package testUnit;

import static org.junit.Assert.*;

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
		this.test=new Joueur("j1");
		Carte.initDeck();
		d=London.getDeck();
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
