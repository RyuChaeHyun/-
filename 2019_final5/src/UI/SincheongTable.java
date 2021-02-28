package UI;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import VO.VOLecture;
import control.CBasket;
import control.CLecture;
import entity.EBasket;
import entity.ELecture;
import main.ClientSession;

public class SincheongTable extends JTable {

//	private CLecture cBasket;
	private DefaultTableModel tablemodel;
	private Vector<VOLecture> eBaskets;
//	private CLecture cSincheong;
	private String fileName;
	String userID;
	
	public SincheongTable(ActionListener actionListener, String studentID) {
//		this.cSincheong = new CLecture();
		String[] columnNames = {"강좌명", "교수명", "강의시간"};
		this.tablemodel = new DefaultTableModel(null, columnNames);
		this.setModel(this.tablemodel);
		this.setVisible(true);
		
		userID = studentID;
		//버튼에 리스너 달기
	}
	//추가하기
	public void addSubject(String fileName, Vector<VOLecture> lectures) throws IOException {
		boolean check;
		Vector<Object> vector = new Vector<Object>();
		vector.add(fileName);
		vector.add(lectures);
		ClientSession clientSession = new ClientSession();
		clientSession.invoke("CLecture", "addSubject", vector);
		this.refresh();
		this.updateUI();

	}
	private void refresh(String string) {
		// TODO Auto-generated method stub
		
	}
	//선택된 거 얻기
	public Vector<VOLecture> getSelectedLectures(){
		int indexes[]= this.getSelectedRows();
		Vector<VOLecture> temp= new Vector<VOLecture>();
		
		for(int i :indexes) {
			temp.add(this.eBaskets.get(i));
		}
		return temp;
	

	}
	//새로고침
	public void refresh() throws FileNotFoundException {
		boolean check2;
			Vector<Object> vector2 = new Vector<Object>();
			vector2.add("data/" + userID + "sincheong");
			//
			ClientSession clientSession = new ClientSession();
			this.eBaskets = (Vector<VOLecture>) clientSession.invoke("CLecture", "getItems", vector2);
			if(this.eBaskets != null) {
				this.tablemodel.setRowCount(0);
				for(VOLecture eLecture : eBaskets) {
					Vector<String> row = new Vector<String>();
					row.add(eLecture.getName());
					row.add(eLecture.getTeacher());
					row.add(eLecture.getTime());

					this.tablemodel.addRow(row);
					this.setBackground(new Color(153, 255, 153));
				}
				
				this.updateUI();

			}
//		this.eBaskets = this.cSincheong.getItems("data/sincheong");
		
		
	}
	
	public JButton getButton() {
		return null;
	}
	//버튼 알려주기
	
}
