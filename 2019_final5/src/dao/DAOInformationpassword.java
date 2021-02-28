package dao;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import UI.LoginFrame;
import control.CInfocheck;
import control.CInfocheck2;
import entity.EMember;

public class DAOInformationpassword extends JFrame implements ActionListener{
	EMember dto = new EMember();
	Choice list;
	TextField in_name;
	String in_major = "";
	TextField in_id;
	
	Button b1, b2;
	
	public DAOInformationpassword() {
		super("��й�ȣ ã��");
		init();
		start();
		
		this.setLocation(500,200);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(380, 500); 
		this.setResizable(true); 
		
	}
		public void init() {
		
		Panel p = new Panel();
		Panel p2 = new Panel();
		
		setLayout(new BorderLayout());
		
		Label findid = new Label("��й�ȣ ã��");
		Label name = new Label("�̸�");
		Label major = new Label("����");
		Label id = new Label("���̵�");
		
		b1 = new Button("Ȯ��");
		b1.setBackground(new Color(255,255,161));
		b1.addActionListener(this);
		
		b2 = new Button("�ٽ� �α���");
//		b2.setBackground(new Color(255,255,161));
		b2.addActionListener(this);
	
		in_name = new TextField(10);
		in_id = new TextField(15);
		
		list = new Choice();
		list.add("�����������ϼ���");
		list.add("�濵�а�");
		list.add("��������а�");
		list.add("���а�");
		list.add("ȭ�а��а�");
		list.add("���ռ���Ʈ�����к�");
		
		GridLayout g = new GridLayout(7,1);
		p.setLayout(g);
		
		p.add(findid);
		p.add(name);
		p.add(in_name);
		p.add(major);
		p.add(list);
		p.add(id);
		p.add(in_id);
		
		p2.add(b1);
		p2.add(b2);
		
		this.add(p, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);

	}
	
		private void start() {
			// TODO Auto-generated method stub
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		
		
		}
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if(obj ==b1) {
				 dto.setName(in_name.getText());
				 dto.setMajor(list.getSelectedItem());
				 dto.setId(in_id.getText());
				 
				CInfocheck2 cInfocheck2 = new CInfocheck2();
				cInfocheck2.check(dto);
			}
			else if(obj == b2) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		}
}
