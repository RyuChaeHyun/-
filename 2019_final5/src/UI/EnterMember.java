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
		super("ȸ������ Frame");
		
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
		Label id = new Label("���̵�");
		Label password = new Label("��й�ȣ");
		Label name = new Label("�̸�");
		Label email = new Label("�̸���");
		Label major = new Label("����");

		b1 = new Button("����"); 
		b2 = new Button("���̵� �ߺ� üũ");
		b3= new Button("������ ȸ������");
		
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
		list.add("�����������ϼ���");
		list.add("�濵�а�");
		list.add("��������а�");
		list.add("���а�");
		list.add("ȭ�а��а�");
		list.add("���ռ���Ʈ�����к�");

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
			  JOptionPane.showMessageDialog(null, "������ ȸ�� ���Կ� �����ϼ̽��ϴ�!", "Information Message",JOptionPane.PLAIN_MESSAGE);
		}
	}

}
