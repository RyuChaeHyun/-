package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import VO.VOLecture;
import entity.ELecture;

@SuppressWarnings("serial")
public class BasketPanel extends JPanel {
	private JButton jButton;
	private BasketTable BasketSelection;
	
	
	public BasketPanel(ActionListener actionListener, String userID) {
		this.setLayout(new BorderLayout());
//		this.add(comp);
		this.jButton = new JButton("신청하기");
		this.jButton.addActionListener(actionListener);		
		
		this.BasketSelection = new BasketTable(new ActionHandler(), userID);
		this.add(new JScrollPane(this.BasketSelection), BorderLayout.CENTER);
		this.add((this.jButton), BorderLayout.SOUTH);
		jButton.setBackground(new Color(255,128,0));
	}
	
	public void addSubject(String fileName, Vector<VOLecture> selectedLectures) throws IOException {
		this.BasketSelection.addSubject(fileName, selectedLectures);
	}
	
	public void removeSubject(String fileName, Vector<VOLecture> selectedLectures) throws FileNotFoundException {
		try {
			this.BasketSelection.removeSubject(fileName, selectedLectures);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JButton getButton() {
		return this.jButton;
	}
	
	public Vector<VOLecture> getSelectedLectures() {
		
		return this.BasketSelection.getSelectedLectures();
		
		
	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}


