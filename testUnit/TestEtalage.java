package testUnit;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Scanner;

import model.Carte;
import model.Etalage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vue.London;

public class TestEtalage {
	ArrayDeque<Carte> d;
	Etalage et;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		d=Carte.initDeck();
		System.out.println("nbJoueur?");
		Scanner sc =new Scanner(System.in);
		int nb=sc.nextInt();
		et=new Etalage(nb);
		//sc.close();
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	//Test si isFull marche correctement
	public void testIsFull(){
		for(int i=0;i<et.getLigne1().length;i++){
			et.addCarte(d.poll());
		}
		assertTrue(et.isFull(et.getLigne1()));
	}
	
	@Test
	//Test si l'ajout d'une carte dans l'�talage marche correctement
	public void testAddCarteSimple(){
		Carte tmp =d.peekFirst();
		et.addCarte(tmp);
		assertSame(tmp,et.getLigne1()[0]);
	}
	
	@Test
	//Test si l'ajout d'une carte qui force les deux lignes de l'�talage a switch marche correctement
	public void testAddCarteSwitch(){
		
		for(int i=0;i<et.getLigne2().length;i++){
			et.addCarte(d.poll());
		}
		Carte tmp =d.peekFirst();
		for(int i=0;i<et.getLigne2().length+1;i++){
			et.addCarte(d.poll());
		}
		assertSame(tmp,et.getLigne1()[0]);
	}
	
	@Test
	//Test qu'il n'est pas possible de piocher une carte "vide" dans l'�talage
	public void testPiocheCarte() {
		try{
			et.piocherCarte(null);
			fail("Impossible de piocher une carte null dans l'�talage");
		}catch (Exception ex){
			assertTrue(true);
		}
	}
	
	@Test
	//Test qu'une carte piocher disparait de l'�talage
	public void testPiocheCarte2() {
		Carte tmp = d.peekFirst();
		et.addCarte(d.poll());
		et.piocherCarte(tmp);
		
		assertNull(et.getLigne1()[0]);
	}
	
	@Test
	//Test que l'ajout d'une carte qui force le switch des lignes vide correctement la ligne qui doit l'etre
	public void testAddCarte() {
		for(int i=0;i<et.getLigne1().length;i++){
			et.addCarte(d.poll());
			et.addCarte(d.poll());
		}
		
		et.addCarte(d.poll());
		assertNull(et.getLigne2()[0]);
		
	}
	
	/*
	@Test
	public void testPiocheCarte(){
		for(int i=0;i<et.getLigne1().length+1;i++){
			et.addCarte(d.poll());
		}
		et.piocheCarte(1, 1);
		assertEquals(et.getLigne1()[1],et.piocheCarte(1, 1));
		assertEquals(et.getLigne2()[0],et.piocheCarte(2, 0));
	}*/

}
