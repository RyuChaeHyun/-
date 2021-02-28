package UI;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import VO.VODirectory;
import control.CDirectory;
import entity.EDirectory;
import main.ClientSession;

public class DirectoryList extends JList<String> {
	private static final long serialVersionUID = 1L;
	
	
//	private CDirectory cdirectory; //컨트롤 만드는 것
	private Vector<VODirectory> edirectories;
	private Vector<String> listData;
	
	public DirectoryList(ListSelectionListener listSelectionHandler) {

			this.setPreferredSize(new Dimension(100,200));
	//		this.setFont(font);//고치기
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 그리는 것  // 선택 모드 설정
			this.listData = new Vector<String>();
			this.setListData(this.listData); // 그림그리는 데에 data를(listData) 달았다
			this.setPreferredSize(new Dimension(100,200));
			this.addListSelectionListener(listSelectionHandler);
//			this.cdirectory = new CDirectory();
		
		
	}
	public String getSelectedFileName() {
		int selectedIndex = this.getSelectedIndex();
		return this.edirectories.get(selectedIndex).getHyperLink();
	}
	
	public String refresh(String fileName) throws FileNotFoundException {

		boolean check;
		Vector<String> vec = new Vector<String>();
		vec.add("data/" + fileName);
		ClientSession clientSession = new ClientSession();
		
		this.edirectories = (Vector<VODirectory>) clientSession.invoke("CDirectory", "getItems", vec);
		if(this.edirectories != null) {
			this.listData.clear();
			for(VODirectory eDirectory : edirectories) {
				this.listData.add(eDirectory.getName());

		}
				}
		this.setSelectedIndex(0);
		this.updateUI();
		return this.edirectories.get(0).getHyperLink();
	}
	public void setHorizontalAligment(int center) {
		// TODO Auto-generated method stub
		
	}
	
}
