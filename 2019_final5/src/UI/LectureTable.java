package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import VO.VOLecture;
import control.CLecture;
import entity.ELecture;
import main.ClientSession;

public class LectureTable extends JTable{

	private static final long serialVersionUID = 1L;
	
//	private CLecture cLecture;
	private DefaultTableModel tablemodel;
	private Vector<VOLecture> eLectures;
	
	public LectureTable() {
//		this.cLecture = new CLecture();
		String[] columnNames = {"강좌명", "교수명", "강의시간"};
		this.tablemodel = new DefaultTableModel(null, columnNames);
		this.setModel(this.tablemodel);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(120,600));
		this.setBackground(new Color(153, 255, 255));
		
	}
	


	public Vector<VOLecture> getSelectedLectures() {
		int indexes[]= this.getSelectedRows();
		Vector<VOLecture> temp= new Vector<VOLecture>();
		
		for(int i :indexes) {
			temp.add(this.eLectures.get(i));
		}
		return temp;
	}
		
		@SuppressWarnings("unchecked")
		public void refresh(String fileName) throws FileNotFoundException {
			boolean check;
			Vector<String> vec = new Vector<String>();
			vec.add("data/" + fileName);
			ClientSession clientSession = new ClientSession();
			this.eLectures = (Vector<VOLecture>) clientSession.invoke("CLecture", "getItems", vec);
			if(this.eLectures != null) {
				this.tablemodel.setRowCount(0);
				
				for(VOLecture eLecture : eLectures) {
					Vector<String> row = new Vector<String>();
					row.add(eLecture.getName());
					row.add(eLecture.getTeacher());
					row.add(eLecture.getTime());

					this.tablemodel.addRow(row);

			}
				
			}
			
			this.updateUI();
			
		}
			
	
		}
				
	


