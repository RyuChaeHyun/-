package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import entity.EMember;

public class CInfocheck3 {

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM dev.member";

	public void idcheck(EMember dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			String id = dto.getId();
			boolean flag = false;

			while(rs.next()) {
				String id2 = rs.getString(1);
				
				if(id.equals(id2)) {
					JOptionPane.showMessageDialog(
							null, "이미 사용중인 아이디가 존재합니다!", "Information Message", JOptionPane.PLAIN_MESSAGE);
					flag = true;
					
				}
			}
			if(!flag) {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다!", "Information Message", JOptionPane.PLAIN_MESSAGE);

			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CInfocheck3() {
		
	}
}
