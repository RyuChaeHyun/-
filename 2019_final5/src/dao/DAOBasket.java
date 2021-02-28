package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import VO.VOLecture;
import entity.EBasket;
import entity.ELecture;

public class DAOBasket {
	
	
	//¡Ÿ √ﬂ∞°
	public void write(String filewrite, String userID) throws IOException {
		FileWriter fw = new FileWriter(new File("data//" + userID + "basket"),true);
		fw.write(filewrite + "\n");
		fw.close();
	}
		
	public void read(String userID) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("data//" + userID + "basket"));
		while(scanner.hasNext()) {
			System.out.println(scanner.next());
		}
	}
	
	public void writing(String userID, Vector<ELecture> selectedLectures) throws IOException {
		FileWriter fw = new FileWriter(new File("data/" + userID + "sincheong"),true);
		for(ELecture selectedLecture:selectedLectures) {
			fw.write(selectedLecture.getNumber()+" ");
			fw.write(selectedLecture.getName()+" ");
			fw.write(selectedLecture.getTeacher()+" ");
			fw.write(selectedLecture.getScore()+" ");
			fw.write(selectedLecture.getTime()+" ");
			fw.write("\n");
		}
		fw.close();
	}


}
