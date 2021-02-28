package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import VO.VODirectory;
import dao.DAODirectory;
import entity.EDirectory;

public class CDirectory {
	private DAODirectory eDirectory;
	
	public CDirectory() {
		eDirectory= new DAODirectory();
		
	}

	public Vector<VODirectory> getItems(Object object) throws FileNotFoundException {
		Vector<Object> vector = (Vector<Object>) object;
		String fileName = (String) vector.get(0);
		
		Vector<VODirectory> vec1 = new Vector<VODirectory>();
		Vector<EDirectory> getter = this.eDirectory.getItems(fileName);
		
		for(EDirectory eDirectory : getter) {
			VODirectory vODirectory = new VODirectory();
			vODirectory.setName(eDirectory.getName());
			vODirectory.setNumber(eDirectory.getNumber());
			vODirectory.setHyperLink(eDirectory.getHyperLink());
			
			vec1.add(vODirectory);
		}
		return vec1;
	}
	
	
	
}
