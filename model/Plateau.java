package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Plateau extends HashMap<String,Zone> {

	public Plateau(){
		try {
			/* R�cup�ration du classeur Excel (en lecture) */
                        URL uri = Deck.class.getResource("../fichier/Carte.xls");
			Workbook workbook = Workbook.getWorkbook(new File(uri.getPath()));

			/* Un fichier excel est compos� de plusieurs feuilles, on r�cup�re la premi�re, celle qui nous int�resse*/
			Sheet sheet = workbook.getSheet(0);



			//Parcour du fichier
			for(int i=1;i<21;i++){

				String listZ=sheet.getCell(7,i).getContents();
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

	public HashSet<String> zoneInvest(){
		HashSet <String> res=new HashSet<String>();
		Collection zone=this.values();
		Zone currZ;
			for(Object curr : zone){
				currZ=(Zone)curr;
				if(currZ.isActivable()&&currZ.getProprietaire()==null){
					res.add(currZ.getNom());
				}
			}
		return res;
	}
}
