package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import VO.VOMember;
import main.ClientSession;

public class AdminInfo extends JFrame implements TableModelListener{
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private String[] columnType = {"아이디", "비밀번호" , "이름", "이메일", "전공"};
	private JLabel studentLabel;
	private JButton jButton;
	
	private Vector<VOMember> vOMember;
	
	public AdminInfo() {
		this.setSize(500,550);
		this.setLocation(400, 50);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		this.studentLabel = new JLabel("관리자 정보");
		this.jButton = new JButton("관리자 정보 추가");
		
		this.table = new JTable();
        this.tableModel = new DefaultTableModel(null, this.columnType);
        this.table.setModel(this.tableModel);
        this.table.setVisible(true);
        
		scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.table);
        this.add(studentLabel);
        this.add(scrollPane);
        this.add(jButton);
        
        jButton.addActionListener(new ActionHandler());
        
        refresh();

}
	public void refresh() {
		boolean check;
		Vector<Object> vec = new Vector<Object>();
		vec.add("data/adminlist");

		ClientSession clientSession = new ClientSession();
		this.vOMember = (Vector<VOMember>) clientSession.invoke("CLogin", "getUsers", vec);
		if(this.vOMember != null) {
			this.tableModel.setRowCount(0);

			for(VOMember vOMembers : vOMember) {
				Vector<String> row = new Vector<String>();
				row.add(vOMembers.getId());
				row.add(vOMembers.getPassword());
				row.add(vOMembers.getName());
				row.add(vOMembers.getEmail());
				row.add(vOMembers.getMajor());

				this.tableModel.addRow(row);

			}

		}		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();
		int column = e.getColumn();
		
		TableModel model = (TableModel) e.getSource();
		String colName = model.getColumnName(column);
		String str = (String) model.getValueAt(row, column);

	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == jButton) {
				EnterMember enterMember = new EnterMember();
				enterMember.setVisible(true);
			}
		}
		
	}
}
