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
	JButton jButton = new JButton("���� �߰��ϱ�");
		
	Label filename = new Label("���� �̸��� �ۼ��ϼ���");
	Label lectureNumber = new Label("���� ��ȣ�� �Է��ϼ���");
	Label lecture = new Label("���� �̸��� �Է��ϼ���");
	Label professor = new Label("������ �̸��� �Է��ϼ���");
	Label score = new Label("������ �Է��ϼ���");
	Label time = new Label("���� �ð��� �Է��ϼ���");
	

	public AdminFrame(String userID) {
		super("������ ������");
		this.setSize(1050,800);
		this.setLocation(150,80);
		this.setResizable(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("�л�");
		JMenu menu2 = new JMenu("������");
		JMenu menu3 = new JMenu("����");
		
		studentInfo = new JMenuItem("�л� ����");
		editStudent= new JMenuItem("�л� �߰�");
		adminInfo = new JMenuItem("������ ����");
		exitserver = new JMenuItem("���� ����");
		
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
				int re = JOptionPane.showConfirmDialog(AdminFrame.this, "���� �����ұ��?", "����", JOptionPane.YES_NO_OPTION);
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
				
				JOptionPane.showMessageDialog(null, "���°� �߰��Ǿ����ϴ�!");
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
