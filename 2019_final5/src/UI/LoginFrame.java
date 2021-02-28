package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import control.CLogin;
import main.ClientSession;

public class LoginFrame extends JFrame {
	JFrame ErrorFrame;
	JLayeredPane mainPane;
	JButton btLogin = new JButton("LOGIN");
	JTextField txID = new JTextField(15);
	JPasswordField txPass = new JPasswordField(15);
	JOptionPane aa = new JOptionPane("잘못 입력하셨습니다!");

	JLabel errorLB = new JLabel("잘못된 입력입니다.");
	JLabel lbID = new JLabel("ID");
	JLabel lbPass = new JLabel("Password");
	EtchedBorder eborder = new EtchedBorder();
	JLabel lbMe = new JLabel("수강신청 프로그램입니다! 로그인 후 화면이 이동합니다.");
	JButton btOK = new JButton("로그인");
	JButton btCancel = new JButton("ID/PW 찾기");
	JButton btnlnit = new JButton("Reset");
	JButton btnEnter = new JButton("회원가입");
	JLabel lbLog = new JLabel("Message Log");
	JTextArea taLog = new JTextArea("");

	MouseListener myBTListener = new MouseHandler();
	JPanel pan1, pan2, pan3;

	private String encodedString;
	private String encodedString2;

	public LoginFrame() {
		super("Login Frame");

		setSize(400, 300);
		this.setLocation(500, 250);
		lbMe.setBorder(eborder);
		Container c = getContentPane();
		mainPane = new JLayeredPane();
		c.setLayout(new BorderLayout());
		mainPane.setLayout(new BorderLayout());

		taLog.setEditable(false);
		c.setLayout(new GridLayout(4, 1, 50, 50));

		c.add(lbMe);

		pan1 = new JPanel();
		pan1.add(lbID);
		pan1.add(txID);
		c.add(pan1);

		pan2 = new JPanel();
		pan2.add(lbPass);
		pan2.add(txPass);
		c.add(pan2);

		pan3 = new JPanel();
		pan3.add(btOK);
		pan3.add(btCancel);
		pan3.add(btnlnit);
		pan3.add(btnEnter);
		c.add(pan3);

		btOK.addMouseListener(myBTListener);
		btCancel.addMouseListener(myBTListener);
		btnlnit.addMouseListener(myBTListener);
		btnEnter.addMouseListener(myBTListener);
		btOK.setBackground(new Color(255, 128, 0));
		btCancel.setBackground(new Color(251, 33, 13));
		btnlnit.setBackground(new Color(15, 223, 0));
		btnEnter.setBackground(new Color(236, 12, 253));

		setVisible(true);
		taLog.append("> System is initiated.\n");

	}

	private void showMessageDialog(Object object, String string) {
		// TODO Auto-generated method stub

	}

	public class MouseHandler implements MouseListener {
		private String userID;
		private String password;

		public void read(Scanner scan) {
			this.userID = scan.next();
			this.password = scan.next();

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			String userID = "";
			if (e.getSource() == btOK) {

				CLogin cLogin = new CLogin();
				try {
					String target = txID.getText();
					userID = txID.getText();
					byte[] targetBytes = target.getBytes("UTF-8");

					Encoder encoder = Base64.getEncoder();
					byte[] encodedBytes = encoder.encode(targetBytes);
					System.out.println(new String(encodedBytes));

					encodedString = encoder.encodeToString(targetBytes);
					System.out.println(encodedString);

					String target2 = txPass.getText();
					byte[] targetBytes2 = target2.getBytes("UTF-8");

					Encoder encoder2 = Base64.getEncoder();
					byte[] encodedBytes2 = encoder2.encode(targetBytes2);

					System.out.println(new String(encodedBytes2));

					encodedString2 = encoder2.encodeToString(targetBytes2);
					System.out.println(encodedString2);

				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				boolean check;
				Vector<String> vec = new Vector<String>();
				vec.add(encodedString);
				vec.add(encodedString2);
				ClientSession clientSession = new ClientSession();
				Object result = clientSession.invoke("CLogin", "authenticate", vec);

				if ((int) result == 1) {
					MainFrame mainFrame = new MainFrame(userID);
					mainFrame.setVisible(true);
					dispose();

				} else if ((int) result == 2) {
					AdminFrame adminFrame = new AdminFrame(userID);
					adminFrame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showConfirmDialog(getContentPane(), "아이디 또는 비밀번호를 다시 입력하세요.", "주의 메시지!",
							JOptionPane.YES_NO_OPTION);

				}

			} else if (e.getSource() == btnEnter) {
				EnterMember enterMember = new EnterMember();
				enterMember.setVisible(true);

			} else if (e.getSource() == btCancel) {
				IDPassword idPassword = new IDPassword();
				idPassword.setVisible(true);
			}

			else if (e.getSource() == btnlnit) {
				((Component) e.getSource()).setBackground(Color.yellow);
				txID.setText("");
				txPass.setText("");
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

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
