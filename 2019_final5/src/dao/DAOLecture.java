package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;

import VO.VOLecture;
import entity.ELecture;

public class DAOLecture {
	public Vector<ELecture> getItems(String fileName) throws FileNotFoundException {
		Vector<ELecture> items = new Vector<ELecture>();

		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			ELecture vod = new ELecture();
			vod.read(scanner);
			items.add(vod);
		}

		return items;
	}

	//¡Ÿ √ﬂ∞°
	public void write(String fileName, Vector<ELecture> vec) throws IOException {
		FileWriter fw = new FileWriter(new File("data/"+fileName),true);

		for(ELecture selectedLecture:vec) {
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
