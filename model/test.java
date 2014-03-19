package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
				
			/* Récupération du classeur Excel (en lecture) */
			Workbook workbook = Workbook.getWorkbook(new File("F:/workspace/ProjetLondon/src/fichier/Carte.xls"));
			
			/* Un fichier excel est composé de plusieurs feuilles, on y accède de la manière suivante*/
			Sheet sheet = workbook.getSheet(0);
			Cell a1,a2,a3;
			ArrayList<Carte> test = new ArrayList<Carte>();
			for(int i=2;i<21;i++){
				test.add(new Constructible(
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
						));
				System.out.println(test.get(i-2));
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

