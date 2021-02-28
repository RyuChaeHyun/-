package UI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import VO.VOLecture;
import entity.ELecture;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private DirectoryPanel departmentSelection;
	private BasketTable BasketSelection;
	private SincheongTable sincheongSelection;
	private JButton jButton, jjButton;
	private ActionListener actionListener;
	private BasketPanel basketPanel;
	private SinCheongPanel sinCheongPanel;
	
	String studentID;
	
	JButton sin, cheong;
	
	public MainFrame(String userID) {
		studentID = userID;
		this.sincheongSelection=new SincheongTable(new ActionHandler(), studentID);
		this.setLocation(50,50); // 바깥에 있으면 안되고 내부에 있어야 한다.
		this.setSize(1430,730); //속성
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("수강신청");
		GridLayout gls = new GridLayout(1,3);
		this.setLayout(gls);
		
		this.departmentSelection = new DirectoryPanel(new ActionHandler());
		this.sincheongSelection = new SincheongTable(new ActionHandler(), studentID);
		this.sinCheongPanel = new SinCheongPanel(studentID);
		this.add(this.departmentSelection);
		this.BasketPanel();
		this.add(this.sinCheongPanel);
		this.setBackground(Color.yellow);
//		this.add(this.sincheongSelection);

	}		
	public void BasketPanel() {
		this.basketPanel = new BasketPanel(new ActionHandler(), studentID);
		
		this.add(new JScrollPane(this.basketPanel));
		
		try {
			this.sincheongSelection.refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SinCheongPanel() {
		this.sincheongSelection = new SincheongTable(new ActionHandler(), studentID);
		this.add(new JScrollPane(this.sinCheongPanel));
		
	}
	
	public JButton getButton() {
		return jButton;
	}
	
	
	private void addSubject(Object source) {
		if(source==this.departmentSelection.getButton()){
			try {
				Vector<VOLecture> lectures= departmentSelection.getSelectedLectures();
				basketPanel.addSubject(studentID + "basket",lectures);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(source==this.basketPanel.getButton()) {
			try {
				Vector<VOLecture> lectures= basketPanel.getSelectedLectures();
				sinCheongPanel.addSubject(studentID + "sincheong",lectures);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(source==this.getButton()) {
			try {
				Vector<VOLecture> lectures= sincheongSelection.getSelectedLectures();
				basketPanel.addSubject(studentID + "basket",lectures);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jButton) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.mju.ac.kr"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		int i =	JOptionPane.showConfirmDialog( getContentPane(), "선택한 정보를 옮기시겠습니까?", "주의 메시지!", JOptionPane.YES_NO_OPTION);
			
		if(i==JOptionPane.YES_OPTION) {
			addSubject(e.getSource());
		}
	
		}	
	}
	

	

}
