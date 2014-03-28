package testUnit;

import static org.junit.Assert.*;

import java.util.Scanner;

import model.Carte;
import model.Deck;
import model.Etalage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEtalage {
	Deck d;
	Etalage et;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		d=new Deck();
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
	public void testIsFull(){
		for(int i=0;i<et.getLigne1().length;i++){
			et.addCarte(d.poll());
		}
		assertEquals(true, et.isFull(et.getLigne1()));
	}
	
	@Test
	public void testAddCarteSimple(){
		Carte tmp =d.peekFirst();
		et.addCarte(tmp);
		assertSame(tmp,et.getLigne1()[0]);
	}
	@Test
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
	public void testPiocheCarte(){
		for(int i=0;i<et.getLigne1().length+1;i++){
			et.addCarte(d.poll());
		}
		et.piocheCarte(1, 1);
		assertEquals(et.getLigne1()[1],et.piocheCarte(1, 1));
		assertEquals(et.getLigne2()[0],et.piocheCarte(2, 0));
	}
        
        public static void main(String args[]){
            
        }

}
