package dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import VO.VODirectory;
import entity.EDirectory;

public class DAODirectory {

	//attribute


	public Vector<EDirectory> getItems(String fileName) throws FileNotFoundException {
	Vector<EDirectory> items = new Vector<EDirectory>();
	
	File file = new File(fileName);
	Scanner scanner = new Scanner(file);
	
	while (scanner.hasNext()) {
		EDirectory vod = new EDirectory();
		vod.read(scanner);
		items.add(vod);
	}

	return items;
	}






}
