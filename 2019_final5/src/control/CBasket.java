package control;

import java.io.IOException;
import java.util.Vector;

import VO.VOLecture;
import dao.DAOBasket;
import entity.ELecture;

public class CBasket {
	
	DAOBasket daoBasket;
	
	public CBasket() {
		daoBasket= new DAOBasket();
	}
	
	public void getCBasket(Object object) throws IOException {
		// TODO Auto-generated method stub
		Vector<String> vector = (Vector<String>) object;
		String get = vector.get(0);
		String userID = vector.get(1);
		
		this.daoBasket.write(get, userID);
		this.daoBasket.read(userID);
	}
	
	public void addSubject(Object object) {
		Vector<Object> vector =  (Vector<Object>) object;
		String fileName = (String) vector.get(0);
		Vector<VOLecture> selectedLectures = (Vector<VOLecture>) vector.get(1);
		String userID = (String) vector.get(2);
		
		Vector<ELecture> vector2 = new Vector<ELecture>();
		ELecture eLecture = new ELecture();
		Vector<VOLecture> voLectures = selectedLectures;
		for(VOLecture voLecture : voLectures) {
			eLecture.setNumber(voLecture.getNumber());
			eLecture.setName(voLecture.getName());
			eLecture.setTeacher(voLecture.getTeacher());
			eLecture.setScore(voLecture.getScore());
			eLecture.setTime(voLecture.getTime());

			vector2.add(eLecture);
		}
		try {
			this.daoBasket.writing(userID, vector2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
