package UI;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import control.CInfocheck3;
import dao.DAOMember;
import entity.EMember;

public class EnterMember extends JFrame implements ActionListener, ItemListener,Runnable{

	EMember dto = new EMember();
	Choice list; 
	TextField in_id; 
	JPasswordField in_pw; 
	TextField in_name; 
	TextField in_email; 
	String in_major = "";
	Button b1;
	Button b2;
	Button b3;
	JPanel enterPanel;
	
	public EnterMember() {
		super("회원가입 Frame");
		
		init();
		start();
			
		this.setLocation(500, 200); 
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(380, 500); 
		this.setResizable(true); 
		
	}
	
	public void init() {
		// TODO Auto-generated method stub
		Panel p = new Panel();
		enterPanel = new JPanel();
		
		setLayout(new BorderLayout());
		Label id = new Label("아이디");
		Label password = new Label("비밀번호");
		Label name = new Label("이름");
		Label email = new Label("이메일");
		Label major = new Label("전공");

		b1 = new Button("저장"); 
		b2 = new Button("아이디 중복 체크");
		b3= new Button("관리자 회원가입");
		
		b1.setBackground(new Color(255,255,161));
		b1.addActionListener(this);
		b2.setBackground(new Color(255,196,255));
		b2.addActionListener(this);
		b3.setBackground(new Color(255,197,255));
		b3.addActionListener(this);
		
		in_id = new TextField(20);
		in_pw = new JPasswordField(20);
		in_name = new TextField(10);
		in_email = new TextField(30);

		list = new Choice();
		list.add("전공을선택하세요");
		list.add("경영학과");
		list.add("국제통상학과");
		list.add("수학과");
		list.add("화학공학과");
		list.add("융합소프트웨어학부");

		enterPanel.add(b1);
		enterPanel.add(b3);
		
		GridLayout g = new GridLayout(11,1);
		p.setLayout(g);

		p.add(id);
		p.add(in_id);
		p.add(b2);
		p.add(password);
		p.add(in_pw);
		p.add(name);
		p.add(in_name);
		p.add(email);
		p.add(in_email); 
		p.add(major);
		p.add(list);

		this.add(p, BorderLayout.CENTER);
		this.add(enterPanel, BorderLayout.SOUTH);

	}
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				System.exit(0);
			}
		});

	}
	public static void main(String[] args) {
		EnterMember exam = new EnterMember();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj ==b1) {
			dto.setId(in_id.getText());
			dto.setPassword(in_pw.getText());
			  dto.setName(in_name.getText());
			  dto.setEmail(in_email.getText());
			  dto.setMajor(list.getSelectedItem());

			  DAOMember.create(dto);
		}
		else if(obj == b2) {
			dto.setId(in_id.getText());
			CInfocheck3 infocheck3 = new CInfocheck3();
			infocheck3.idcheck(dto);
			
		}
		else if(obj == b3) {
			dto.setId(in_id.getText());
			dto.setPassword(in_pw.getText());
			  dto.setName(in_name.getText());
			  dto.setEmail(in_email.getText());
			  dto.setMajor(list.getSelectedItem());

			  DAOMember.create2(dto);
			  JOptionPane.showMessageDialog(null, "관리자 회원 가입에 성공하셨습니다!", "Information Message",JOptionPane.PLAIN_MESSAGE);
		}
	}

}
