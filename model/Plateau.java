package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Plateau extends HashMap<String,Zone> {

	public Plateau(){
		try {
			/* R�cup�ration du classeur Excel (en lecture) */
			Workbook workbook = Workbook.getWorkbook(new File("../ProjetLondon/src/fichier/ZonePlateau.xls"));

			/* Un fichier excel est compos� de plusieurs feuilles, on r�cup�re la premi�re, celle qui nous int�resse*/
			Sheet sheet = workbook.getSheet(0);



			//Parcour du fichier
			for(int i=1;i<21;i++){

				String listZ=sheet.getCell(6,i).getContents();
				String[] t=listZ.split(";");
				ArrayList<String> tmpL = new ArrayList<String>();
				for(int j=0;j<t.length;j++){
					tmpL.add(t[j]);
				}
				Zone tmpZ=new Zone(sheet.getCell(0,i).getContents(),
									Integer.parseInt(sheet.getCell(1,i).getContents()),
									Integer.parseInt(sheet.getCell(2,i).getContents()),
									Integer.parseInt(sheet.getCell(3,i).getContents()),
									Boolean.parseBoolean(sheet.getCell(4,i).getContents()),
									Boolean.parseBoolean(sheet.getCell(5,i).getContents()),
									Boolean.parseBoolean(sheet.getCell(6,i).getContents()),
									tmpL);
				this.put(sheet.getCell(0,i).getContents(),tmpZ);
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
