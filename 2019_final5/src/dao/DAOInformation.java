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
import entity.EMember;

public class DAOInformation extends JFrame implements ActionListener{
	
	EMember dto = new EMember();
	Choice list;
	TextField in_name;
	String in_major = "";
	
	Button b1, b2;
	
	public DAOInformation() {
		super("아이디");
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
		
		Label findid = new Label("아이디 찾기");
		Label name = new Label("이름");
		Label major = new Label("전공");
		
		b1 = new Button("확인");
		b1.setBackground(new Color(255,255,161));
		b1.addActionListener(this);
		
		b2 = new Button("다시 로그인");
//		b2.setBackground(new Color(255,255,161));
		b2.addActionListener(this);
	
		in_name = new TextField(10);
		
		list = new Choice();
		list.add("전공을선택하세요");
		list.add("경영학과");
		list.add("국제통상학과");
		list.add("수학과");
		list.add("화학공학과");
		list.add("융합소프트웨어학부");
		
		GridLayout g = new GridLayout(5,1);
		p.setLayout(g);
		
		p.add(findid);
		p.add(name);
		p.add(in_name);
		p.add(major);
		p.add(list);
		
		p2.add(b1);
		p2.add(b2);
		
		this.add(p, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);

	}
	
		private void start() {
			// TODO Auto-generated method stub
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					
				}
			});
		
		
		}
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if(obj ==b1) {
				 dto.setName(in_name.getText());
				 dto.setMajor(list.getSelectedItem());
				CInfocheck cInfocheck = new CInfocheck();
				cInfocheck.check(dto);
			}
			else if(obj == b2) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		}
}
