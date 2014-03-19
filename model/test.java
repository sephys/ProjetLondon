package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
				
			/* R�cup�ration du classeur Excel (en lecture) */
			Workbook workbook = Workbook.getWorkbook(new File("F:/workspace/LectureExcel/src/fichier/Carte.xls"));
			
			/* Un fichier excel est compos� de plusieurs feuilles, on y acc�de de la mani�re suivante*/
			Sheet sheet = workbook.getSheet(0);
			
			/* On acc�de aux cellules avec la m�thode getCell(indiceColonne, indiceLigne) */
			Cell a1 = sheet.getCell(0,0); 
			
			/* On peut �galement le faire avec getCell(nomCellule) */
			Cell c5 = sheet.getCell("A2");
			
			/* On peut r�cup�rer le contenu d'une cellule en utilisant la m�thode getContents() */
			String contenuA1= a1.getContents();
			String contenuC5 = c5.getContents();
			
			//System.out.println(contenuA1);
			//System.out.println(contenuC5);
			for(int i=2;i<21;i++){
				for( int j=0;j<=12;j++){
					a1 = sheet.getCell(j,i);
					contenuA1= a1.getContents();
					System.out.print(contenuA1+" " );
				}
				System.out.println("");
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

