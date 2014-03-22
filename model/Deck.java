package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Deck extends ArrayDeque<Carte> {
	
	//Constructeur du deck qui va récuperer l'ensemble des information dans un fichier .xls
	public Deck(){
		try {
			/* Récupération du classeur Excel (en lecture) */
			Workbook workbook = Workbook.getWorkbook(new File("../ProjetLondon/src/fichier/Carte.xls"));

			/* Un fichier excel est composé de plusieurs feuilles, on récupère la première, celle qui nous intéresse*/
			Sheet sheet = workbook.getSheet(0);

			
			ArrayList<Carte> carteA= new ArrayList<Carte>(); 	//création de la première ArrayList qui va contenir les cartes de catégorieA
			ArrayList<Carte> carteB= new ArrayList<Carte>();	//création de la seconde ArrayList qui va contenir les cartes de catégorieB
			ArrayList<Carte> carteC= new ArrayList<Carte>();	//création de la dernière ArrayList qui va contenir les cartes de catégorieC
			
			//Parcour du fichier
			for(int i=2;i<78;i++){
				
				int nb=Integer.parseInt(sheet.getCell(14,i).getContents());	//on récupère le nombre de carte semblable (exemple il y a deux carte Water works dans le jeu)
				String type=sheet.getCell(15,i).getContents();				//on récupère le "type" de la carte (C : constructible N : Non Constructible)
				Carte c = null;
				for (int j=1;j<=nb;j++){									//On créer ensuite autant de carte de même nom
					switch(type){											//on switch dans le type correspondant pour construire la carte avec le bon constructeur
					case "C" :												//construction de la carte avec tout les paramètres nécessaire
						c=new Constructible(
								sheet.getCell(0,i).getContents(),
								sheet.getCell(1,i).getContents(),
								sheet.getCell(2,i).getContents(),
								Integer.parseInt(sheet.getCell(3,i).getContents()),
								new String[]{sheet.getCell(5,i).getContents(),sheet.getCell(6,i).getContents()},
								Integer.parseInt(sheet.getCell(4,i).getContents()),
								new int[]{Integer.parseInt(sheet.getCell(7,i).getContents()),Integer.parseInt(sheet.getCell(8,i).getContents()),Integer.parseInt(sheet.getCell(9,i).getContents())},
								sheet.getCell(10,i).getContents(),
								sheet.getCell(11,i).getContents(),
								Boolean.parseBoolean(sheet.getCell(12,i).getContents()),
								Boolean.parseBoolean(sheet.getCell(13,i).getContents())
								);

								break;
						case "N" :
							c=new NonConstructible(							//construction de la carte avec tout les paramètres nécessaire
									sheet.getCell(0,i).getContents(),
									sheet.getCell(1,i).getContents(),
									sheet.getCell(2,i).getContents(),
									sheet.getCell(10,i).getContents()
									);
							break;

					}
					switch(c.getCategorie()){ 								//ajout de la carte dans l'ArrayList correspondante
					case "A":
						carteA.add(c);
						break;
					case "B":
						carteB.add(c);
						break;
					case "C":
						carteC.add(c);
						break;
					}
				}
				
			}
				//création du deck final					
				int indice;
				while(!carteA.isEmpty()){ 								//tant que l'ArrayList n'est pas vite
					indice=(int) (Math.random()*carteA.size()-1);		//on détermine un indice aléatoire entre [0;carteA.size()-1]
					this.add(carteA.get(indice));						//on récupère la carte et on l'ajoute a la fin de ce deck
					carteA.remove(indice);								//on retire l'élément de la liste
				}
				while(!carteB.isEmpty()){
					indice=(int) (Math.random()*carteB.size()-1);
					this.add(carteB.get(indice));
					carteB.remove(indice);
				}
				while(!carteC.isEmpty()){
					indice=(int) (Math.random()*carteC.size()-1);
					this.add(carteC.get(indice));
					carteC.remove(indice);
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
