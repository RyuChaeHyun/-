package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import VO.VOLecture;

public class DirectoryPanel2 extends JPanel{
	private ListSelectionListener listSelectionHandler;
	
	private DirectoryList campusList;
	private DirectoryList collegeList;
	private DirectoryList departmentList;
	private LectureTable lectureTable;
	private JLabel lbl;
	private JButton jButton;
	
	private EventObject event;
	private Object frame;


	public DirectoryPanel2(ActionListener actionListener) {
		lbl = new JLabel("����� �ϴ� ������ ������ �� ��ư�� Ŭ���ϼ���.");

		lectureTable = new LectureTable();
		departmentList= new DirectoryList(listSelectionHandler);
		campusList = new DirectoryList(listSelectionHandler);
		collegeList= new DirectoryList(listSelectionHandler);
		this.listSelectionHandler = new ListSelectionHandler();
		this.lectureTable = new LectureTable();
		JPanel panel = new JPanel();

		JScrollPane scrollpane = new JScrollPane();
		this.campusList = new DirectoryList(this.listSelectionHandler);
		scrollpane.setViewportView(this.campusList);
		panel.add(this.campusList);
		
		scrollpane = new JScrollPane();
		this.collegeList = new DirectoryList(this.listSelectionHandler);
		scrollpane.setViewportView(this.collegeList);
		panel.add(this.collegeList);

		scrollpane = new JScrollPane();
		this.departmentList = new DirectoryList(this.listSelectionHandler);
		scrollpane.setViewportView(this.departmentList);
		panel.add(this.departmentList);

		
		this.add(panel);
		
		this.lectureTable = new LectureTable();
		this.add(new JScrollPane(lectureTable));
		this.refresh(null);
	}
	
	//��ư �˷��ֱ�
	public JButton getButton() {
		return jButton;
	}

	public void refresh(Object source) {

		try {
			if(source == null) {
				String fileName = this.campusList.refresh("root");
				fileName = this.collegeList.refresh(fileName);
				fileName = departmentList.refresh(fileName);
				this.lectureTable.refresh(fileName);
			}


			else if(source == campusList) { //��� object�̳�
				String fileName = this.campusList.getSelectedFileName();
				fileName = this.collegeList.refresh(fileName);
				fileName = departmentList.refresh(fileName);
				this.lectureTable.refresh(fileName);

			} else if(source == collegeList) {
				String fileName = this.collegeList.getSelectedFileName();
				fileName = this.departmentList.refresh(fileName);
				this.lectureTable.refresh(fileName);

			}else if(source == departmentList) {
				String fileName = this.departmentList.getSelectedFileName();
				this.lectureTable.refresh(fileName);
				//lecture table 
			}

		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private class ListSelectionHandler implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			refresh(event.getSource());
		}
	}


	public Vector<VOLecture> getSelectedLectures() {
		
		return this.lectureTable.getSelectedLectures();
		
		
	}
	private class ActionHandler implements ActionListener{



		@Override
		public void actionPerformed(ActionEvent e) {
		}	
	}


	public void addSubject(Object source) {
		// TODO Auto-generated method stub
		
	}

	public Component getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
