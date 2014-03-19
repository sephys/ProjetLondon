package model;
import java.io.*;
public class InitCarte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				FileInputStream fs=new FileInputStream("Classeur.xlsx");
				int test=fs.read();
				System.out.println(test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}
