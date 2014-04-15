package testUnit;

import static org.junit.Assert.*;

import java.util.ArrayDeque;

import model.Carte;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vue.London;

public class TestCarte {
	
	ArrayDeque<Carte> d;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Carte.initDeck();
		d=London.getDeck();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	//Test si l'egalite marche correctement
	public void testEquals() {
		Carte tmp = d.peekFirst();
		Carte tmp2 = d.poll();
		
		assertSame(tmp,tmp2);
		
		
		while(d.peekFirst().getNom().equals(tmp2.getNom())){
			tmp = d.poll();
		}
		
		assertNotSame(tmp,tmp2);
	}
	
	@Test
	//Test si il y a le bon nombre de carte par categorie et donc au total
	public void testInitDeck() {
		Carte tmp;
		int countA = 0;
		int countB = 0;
		int countC = 0;
		
		while (d.isEmpty() == false){
			tmp = d.poll();
			if (tmp.getCategorie().equals("A")){
				countA++;
			}else if(tmp.getCategorie().equals("B")){
				countB++;
			}else if(tmp.getCategorie().equals("C")){
				countC++;
			}else{
				fail("Une carte est de catégorie différente de A, B ou C");
			}
		}
		
		assertEquals(25,countA);
		assertEquals(50,countB);
		assertEquals(35,countC);
	}
	
	@Test
	//Test si le deck est bien mélanger en 3 partie (A, B et C)
	public void testInitDeck2(){
		
		Carte tmpPast = d.peekFirst();
		Carte tmp = d.poll();
		int cpt = 0;
		
		while (d.isEmpty() == false){
			
			tmp = d.poll();
			
			if (!(tmp.getCategorie().equals(tmpPast.getCategorie()))){
				cpt++;
			}
			
			tmpPast = d.peekFirst(); 
		}
		assertEquals(2,cpt);
	}
	
}
