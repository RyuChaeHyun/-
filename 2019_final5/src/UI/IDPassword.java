package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.DAOInformation;
import dao.DAOInformationpassword;

public class IDPassword extends JFrame implements ActionListener{
	

	JButton b1 = new JButton("���̵� ã��");
	FlowLayout fl = new FlowLayout();
	Label a = new Label("���̵� Ȥ�� ��й�ȣ�� �����̽��ϱ�?");
	JButton b2 = new JButton("��й�ȣ ã��");
	JButton b3 = new JButton("��й�ȣ �缳��");
	
	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	
	public IDPassword() {
		super("���̵�/��й�ȣ ã��-1");
		this.setLocation(550,270);
		this.setSize(350,200);
		this.setLayout(new GridLayout());
		this.setResizable(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setBackground(new Color(255,255,144));
		b2.setBackground(new Color(224,255,128));
		b3.setBackground(new Color(255,196,255));
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		this.add(p, BorderLayout.CENTER);
		GridLayout g = new GridLayout(3,1);
		p.setLayout(g);

		
}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			DAOInformation dao = new DAOInformation();
			dao.setVisible(true);

		}
		else if(obj == b2) {
			DAOInformationpassword dao2 = new DAOInformationpassword();
			dao2.setVisible(true);
		}
		else if(obj == b3) {
			
		}
	}
	
}