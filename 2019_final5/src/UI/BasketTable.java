package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import VO.VOLecture;
import control.CLecture;
import entity.ELecture;
import main.ClientSession;

public class BasketTable extends JTable{
	
//	private CLecture cBasket;
	private DefaultTableModel tablemodel;
	private Vector<VOLecture> eBaskets;
	String userID;
	
	
	public BasketTable(ActionListener actionListener, String studentID) {
		userID = studentID;
		
//		this.cBasket = new CLecture();
		String[] columnNames = {"강좌명", "교수명", "강의시간"};
		this.tablemodel = new DefaultTableModel(null,columnNames);
		this.setModel(this.tablemodel);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(400,500));
		this.setBackground(new Color(255, 204, 255));
		
		//버튼에 리스너 달기
		
		
		try {
			this.refresh(userID + "basket");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSubject(String fileName, Vector<VOLecture> selectedLectures) throws IOException {
		boolean check;
		Vector<Object> vec = new Vector<Object>();
		vec.add(fileName);
		vec.add(selectedLectures);
		ClientSession clientSession = new ClientSession();

		clientSession.invoke("CLecture", "addSubject", vec);
		this.refresh(fileName);
		this.updateUI();
		}
	
	//버튼 알려주기
	public JButton getButton() {
		return null;
	}
	
	public void removeSubject(String fileName, Vector<VOLecture> selectedLectures) throws IOException {
		boolean check;
		Vector<Object> vec = new Vector<Object>();
		vec.add(fileName);
		vec.add(selectedLectures);
		ClientSession clientSession = new ClientSession();

		Object result = clientSession.invoke("CLecture", "addSubject", vec);
		if((boolean) result) {
			this.refresh("basket");

		}
			}
	
	public void refresh(String fileName) throws FileNotFoundException {
		boolean check;
		Vector<Object> vec = new Vector<Object>();
		vec.add("data/" + fileName);
		
		ClientSession clientSession = new ClientSession();
		this.eBaskets = (Vector<VOLecture>) clientSession.invoke("CLecture", "getItems", vec);
		if(this.eBaskets != null) {
			this.tablemodel.setRowCount(0);
			
			for(VOLecture eLecture : eBaskets) {
				Vector<String> row = new Vector<String>();
				row.add(eLecture.getName());
		 		row.add(eLecture.getTeacher());
				row.add(eLecture.getTime());

				this.tablemodel.addRow(row);

		}
					
		}
	}
	
	public Vector<VOLecture> getSelectedLectures() {
		int indexes[]= this.getSelectedRows();
		Vector<VOLecture> temp= new Vector<VOLecture>();
		
		for(int i :indexes) {
			temp.add(this.eBaskets.get(i));
		}
		return temp;
	}
}

