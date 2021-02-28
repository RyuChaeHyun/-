package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LecturePanel extends JPanel{
	private LectureTable lectureTable= new LectureTable();
	
	public LecturePanel() {
		this.add(this.lectureTable);
		
		JScrollPane scrollpane = new JScrollPane();
		this.lectureTable = new LectureTable();
		scrollpane.setViewportView(this.lectureTable);
		this.add(scrollpane);		
	}
}
