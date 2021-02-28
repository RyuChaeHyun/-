package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import VO.VOLecture;
import dao.DAOLecture;
import entity.ELecture;

public class CLecture {
private DAOLecture eLecture;
	
	public CLecture() {
		this.eLecture= new DAOLecture();
		
	}

	public Vector<VOLecture> getItems(Object object) throws FileNotFoundException {
		Vector<Object> vector2 = (Vector<Object>) object;
		String fileName = (String) vector2.get(0);
		
		Vector<ELecture> vec1 = this.eLecture.getItems(fileName);
		Vector<VOLecture> returns = new Vector<VOLecture>();
		
		for(ELecture eLecture : vec1) {
			VOLecture vOLecture = new VOLecture();
			vOLecture.setNumber(eLecture.getNumber());
			vOLecture.setName(eLecture.getName());
			vOLecture.setTeacher(eLecture.getTeacher());
			vOLecture.setScore(eLecture.getScore());
			vOLecture.setTime(eLecture.getTime());
			
			returns.add(vOLecture);
		}
		
		return returns;
	}
	
	@SuppressWarnings("unchecked")
	public void addSubject(Object object) throws IOException {
		Vector<Object> vector =(Vector<Object>) object;
		String fileName = (String) vector.get(0);
		Vector<VOLecture> selectedLectures = (Vector<VOLecture>) vector.get(1);
		
		Vector<ELecture> vec = new Vector<ELecture>();
		Vector<VOLecture> voLectures = selectedLectures;
		
		for(VOLecture voLecture:voLectures) {
			ELecture eLecture = new ELecture();

			eLecture.setNumber(voLecture.getNumber());
			eLecture.setName(voLecture.getName());
			eLecture.setTeacher(voLecture.getTeacher());
			eLecture.setScore(voLecture.getScore());
			eLecture.setTime(voLecture.getTime());
			
			vec.add(eLecture);
			
		}
		this.eLecture.write(fileName, vec);
	}
	

}
