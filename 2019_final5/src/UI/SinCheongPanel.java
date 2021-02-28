package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import UI.BasketPanel.ActionHandler;
import UI.LoginFrame.MouseHandler;
import VO.VOLecture;
import entity.EBasket;
import entity.ELecture;

public class SinCheongPanel extends JPanel {
	private JButton j2Button, j3Button;
	private JButton btn2;
	private JButton memobutton;
	
	private SincheongTable SinCheongSelection;
	private ActionHandler actionHandler;
	private SincheongTable sincheongTable;
	private MouseListener mouseListener;
	private Component btn;
	
	String userID;

	
	public SinCheongPanel(String studentID) {
		
		userID = studentID;

		this.j2Button = new JButton("홈페이지로 가기");
		this.j2Button.addMouseListener(new MouseHandler());
		
		this.j3Button = new JButton("창닫기");
		this.j3Button.addMouseListener(new MouseHandler());
		
		
		this.SinCheongSelection = new SincheongTable(actionHandler, userID);
		this.add(new JScrollPane(this.SinCheongSelection));
		this.add(this.j2Button);
		
		this.memobutton = new JButton("메모하기");
		this.memobutton.addMouseListener(new MouseHandler());
		
		
		this.refresh();
		
		ImageIcon normalIcon = new ImageIcon("C:\\Users\\lch01\\OneDrive\\바탕 화면\\2019\\절차적 사고와 프로그래밍\\사진\\flat-2126879_960_720.png");
		ImageIcon rolloverIcon = new ImageIcon("C:\\Users\\lch01\\OneDrive\\바탕 화면\\2019\\절차적 사고와 프로그래밍\\사진\\click.jpg");
		ImageIcon pressedIcon = new ImageIcon("C:\\Users\\lch01\\OneDrive\\바탕 화면\\2019\\절차적 사고와 프로그래밍\\사진\\icon_process02_off.gif");
		
		btn2 = new JButton(normalIcon);
		this.btn2.addMouseListener(new MouseHandler());

		btn2.setPressedIcon(pressedIcon);
		btn2.setRolloverIcon(rolloverIcon);
		
//		this.add(new JScrollPane(this.sincheongTable), BorderLayout.CENTER);
//		this.add((this.jButton), BorderLayout.SOUTH);
//		this.add((this.btn), BorderLayout.SOUTH);
		this.add(btn2);
		this.btn2.setSize(150,150);
		this.add(memobutton);
		this.memobutton.setSize(50,50);
		this.setSize(150,150);
		this.setVisible(true);
		
	}
	
	
	
	public void addSubject(String fileName, Vector<VOLecture> lectures) throws IOException {
		this.SinCheongSelection.addSubject(fileName, lectures);
	}
	
	
		

	public void refresh(){
		try {
			this.SinCheongSelection.refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == j2Button) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.mju.ac.kr"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if(e.getSource()==btn2) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.naver.com"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if(e.getSource()==j3Button) {
				dispose();
			}
			else if(e.getSource()==memobutton) {
				Memo memo = new Memo();
				memo.setVisible(true);
				
			}
			
		}

		

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	}
	

	

