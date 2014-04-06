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
	
	//Constructeur du deck qui va r�cuperer l'ensemble des information dans un fichier .xls
	public Deck(){
		try {
			/* R�cup�ration du classeur Excel (en lecture) */
			Workbook workbook = Workbook.getWorkbook(new File("../fichier/Carte.xls"));

			/* Un fichier excel est compos� de plusieurs feuilles, on r�cup�re la premi�re, celle qui nous int�resse*/
			Sheet sheet = workbook.getSheet(0);

			
			ArrayList<Carte> carteA= new ArrayList<Carte>(); 	//cr�ation de la premi�re ArrayList qui va contenir les cartes de cat�gorieA
			ArrayList<Carte> carteB= new ArrayList<Carte>();	//cr�ation de la seconde ArrayList qui va contenir les cartes de cat�gorieB
			ArrayList<Carte> carteC= new ArrayList<Carte>();	//cr�ation de la derni�re ArrayList qui va contenir les cartes de cat�gorieC
			
			//Parcour du fichier
			for(int i=2;i<78;i++){
				
				int nb=Integer.parseInt(sheet.getCell(14,i).getContents());	//on r�cup�re le nombre de carte semblable (exemple il y a deux carte Water works dans le jeu)
				String type=sheet.getCell(15,i).getContents();				//on r�cup�re le "type" de la carte (C : constructible N : Non Constructible)
				Carte c = null;
				for (int j=1;j<=nb;j++){									//On cr�er ensuite autant de carte de m�me nom
					switch(type){											//on switch dans le type correspondant pour construire la carte avec le bon constructeur
					case "C" :												//construction de la carte avec tout les param�tres n�cessaire
						c=new Constructible(
								sheet.getCell(0,i).getContents(),
								sheet.getCell(1,i).getContents(),
								sheet.getCell(2,i).getContents(),
								sheet.getCell(14,i).getContents(),
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
							c=new NonConstructible(							//construction de la carte avec tout les param�tres n�cessaire
									sheet.getCell(0,i).getContents(),
									sheet.getCell(1,i).getContents(),
									sheet.getCell(2,i).getContents(),
									sheet.getCell(14,i).getContents(),
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
				//cr�ation du deck final					
				int indice;
				while(!carteA.isEmpty()){ 								//tant que l'ArrayList n'est pas vite
					indice=(int) (Math.random()*carteA.size()-1);		//on d�termine un indice al�atoire entre [0;carteA.size()-1]
					this.add(carteA.get(indice));						//on r�cup�re la carte et on l'ajoute a la fin de ce deck
					carteA.remove(indice);								//on retire l'�l�ment de la liste
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

