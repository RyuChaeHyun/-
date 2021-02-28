package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
import entity.ELecture;

public class DirectoryPanel extends JPanel {
	private ListSelectionListener listSelectionHandler;
	
	private DirectoryList campusList;
	private DirectoryList collegeList;
	private DirectoryList departmentList;
	private LectureTable lectureTable;
	private JButton jButton;
	private JLabel lbl;
//	private BasketTable basketTable;
	

	private EventObject event;
	private Object frame;


	public DirectoryPanel(ActionListener actionListener) {
//		this.basketTable= new BasketTable();
		lbl = new JLabel("담고자 하는 과목을 선택한 후 버튼을 클릭하세요.");
		this.jButton = new JButton("Basket에 담기");
		jButton.setBackground(new Color(204,255,000));
		this.jButton.addActionListener(actionListener);
//		this.
		
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
//		this.add(basketTable);
		this.add(jButton);
		this.refresh(null);
	}
	
	//버튼 알려주기
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


			else if(source == campusList) { //어느 object이냐
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
			
			
		int i =	JOptionPane.showConfirmDialog( getContentPane(), "선택한 정보를 옮기시겠습니까?", "주의 메시지!", JOptionPane.YES_NO_OPTION);
			
		if(i==JOptionPane.YES_OPTION) {
			addSubject(e.getSource());
		}
	
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




