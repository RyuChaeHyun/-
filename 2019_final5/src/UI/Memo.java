package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Memo extends JFrame implements ActionListener {
	JMenuItem newItem, openItem, saveItem, closeItem;

	JTextArea ta = new JTextArea();
	JLabel label = new JLabel("메모를 시작하세요 > ");
	public Memo() {
		super("제목 없음 - 메모장");
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("파일");
		newItem = new JMenuItem("새로 만들기");
		openItem = new JMenuItem("열기");
		saveItem = new JMenuItem("저장");
		closeItem = new JMenuItem("종료");

		menu.add(newItem);
		menu.addSeparator();
		menu.add(openItem);
		menu.add(saveItem);
		menu.addSeparator();
		menu.add(closeItem);

		bar.add(menu);

		setJMenuBar(bar);
		this.setLayout(new FlowLayout());

		JScrollPane js = new JScrollPane();
		this.add(label);
		this.add(ta);
		this.add(js);
		this.add("CENTER", js);
		this.setSize(400,300);
		this.setLocation(400,300);
		this.setVisible(true);

		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		closeItem.addActionListener(this);

		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				int re = JOptionPane.showConfirmDialog(Memo.this, "정말 종료할까요?", "종료", JOptionPane.YES_NO_OPTION);
				if (re == JOptionPane.YES_OPTION)
					dispose();
				else
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

			}
		});
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == newItem) {
			ta.setText("");
		}
		
		else if(e.getSource() == openItem) {
			JFileChooser jc = new JFileChooser();
			if(jc.showOpenDialog(this) == jc.APPROVE_OPTION) {
				try {
					File f = jc.getSelectedFile();
					FileReader fis = new FileReader(f);
					int i = 0;
					ta.setText("");
					while((i=fis.read())!=-1) {
						ta.append(String.valueOf((char)i));
					}
			}
				catch(Exception ex) {
					
				}
			}
		}
		else if(e.getSource() == saveItem) {
			JFileChooser jc = new JFileChooser();
			if(jc.showOpenDialog(this) == jc.APPROVE_OPTION) {
				try {
					String str = ta.getText().trim();
					if(str.length() <1) {
						return;
					}
					File f = jc.getSelectedFile();
					FileWriter fw = new FileWriter(f);
					fw.write(str);
					fw.close();
				}
				catch(Exception ex) {
					}
				}
		}
		else if(e.getSource() == closeItem) {
			dispose();
		}



	}
	
}

