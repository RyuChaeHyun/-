package UI;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import VO.VOLecture;
import main.ClientSession;

public class AdminFrame extends JFrame implements ActionListener {
	private Vector<VOLecture> voLecture;

	JMenuItem studentInfo, editStudent, adminInfo, exitserver;
	DirectoryPanel2 departmentSelection = new DirectoryPanel2(null);

	TextField in_filename;
	TextField in_lectureNumber;
	TextField in_lecture;
	TextField in_professor;
	TextField in_score;
	TextField in_time;
	JButton jButton = new JButton("강좌 추가하기");
		
	Label filename = new Label("파일 이름을 작성하세요");
	Label lectureNumber = new Label("강좌 번호를 입력하세요");
	Label lecture = new Label("강좌 이름을 입력하세요");
	Label professor = new Label("교수님 이름을 입력하세요");
	Label score = new Label("학점를 입력하세요");
	Label time = new Label("강좌 시간를 입력하세요");
	

	public AdminFrame(String userID) {
		super("관리자 페이지");
		this.setSize(1050,800);
		this.setLocation(150,80);
		this.setResizable(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("학생");
		JMenu menu2 = new JMenu("관리자");
		JMenu menu3 = new JMenu("서버");
		
		studentInfo = new JMenuItem("학생 정보");
		editStudent= new JMenuItem("학생 추가");
		adminInfo = new JMenuItem("관리자 정보");
		exitserver = new JMenuItem("서버 종료");
		
		menu.add(studentInfo);
		menu.add(editStudent);
		
		menu2.add(adminInfo);
		
		menu3.add(exitserver);
		
		bar.add(menu);
		bar.add(menu2);
		bar.add(menu3);
		
		setJMenuBar(bar);
		
		Panel p = new Panel();
		in_filename = new TextField(10);
		in_lectureNumber = new TextField(10);
		in_lecture = new TextField(20);
		in_professor = new TextField(10);
		in_score = new TextField(10);
		in_time = new TextField(45);
		jButton.addActionListener(this);

		p.add(filename);
		p.add(in_filename);
		
		p.add(lectureNumber);
		p.add(in_lectureNumber);
		
		p.add(lecture);
		p.add(in_lecture);

		p.add(professor);
		p.add(in_professor);
				
		p.add(score);
		p.add(in_score);
		
		p.add(time);
		p.add(in_time);
		p.add(jButton);
		
		
		GridLayout g = new GridLayout(13,1);
		p.setLayout(g);
		this.add(this.departmentSelection);
		this.add(p);
		
		GridLayout gls = new GridLayout(1,2);
		this.setLayout(gls);
		
		studentInfo.addActionListener(this);
		editStudent.addActionListener(this);
		adminInfo.addActionListener(this);
		exitserver.addActionListener(this);
		jButton.addActionListener(this);
	
		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				int re = JOptionPane.showConfirmDialog(AdminFrame.this, "정말 종료할까요?", "종료", JOptionPane.YES_NO_OPTION);
				if (re == JOptionPane.YES_OPTION)
					dispose();
				else
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

			}
		});
	}
	

	public void refresh() {
		
		String filename2 = in_filename.getText();

		boolean check;
		Vector<Object> vec = new Vector<Object>();
		vec.add("data/" + filename2);

//		ClientSession clientSession = new ClientSession();
//		this.voLecture = (Vector<VOLecture>) clientSession.invoke("CLogin", "getUsers", vec);
//		if(this.voLecture != null) {
//				for(VOLecture voLectures : voLecture) {
//				Vector<String> row = new Vector<String>();
//				row.add(voLectures.getNumber());
//				row.add(voLectures.getName());
//				row.add(voLectures.getTeacher());
//				row.add(voLectures.getScore());
//				row.add(voLectures.getTime());
//
//				this.tableModel.addRow(row);
//
//			}
//
//		}		

	}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == studentInfo) {
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setVisible(true);
			}
			else if(e.getSource() == editStudent) {
				StudentInfo2 studentInfo2 = new StudentInfo2();
				studentInfo2.setVisible(true);

			}
			else if(e.getSource() == adminInfo) {
				AdminInfo adminInfo = new AdminInfo();
				adminInfo.setVisible(true);
			}
			else if(e.getSource() == exitserver) {
				ClientSession client = new ClientSession();
				client.invoke("Quit", "None", "None");
			}
			else if(e.getSource() == jButton) {
				
				JOptionPane.showMessageDialog(null, "강좌가 추가되었습니다!");
				String filename2 = in_filename.getText();
				String lectureNumber2 = in_lectureNumber.getText();
				String lecture2 = in_lecture.getText();
				String professor2 = in_professor.getText();
				String score2 = in_score.getText();
				String time2 = in_time.getText();
				
				File f1 = new File("data/" + filename2);
				FileWriter fw1;
				try {
					fw1 = new FileWriter(f1,true);
					fw1.write(lectureNumber2 + " " + lectureNumber2 + " "
							 + lecture2 + " " + professor2 + " " + score2 + " " + time2 + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					
				}

			}
			
		
	}

	
}
