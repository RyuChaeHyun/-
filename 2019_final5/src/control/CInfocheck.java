package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import entity.EMember;

public class CInfocheck {
	boolean flag = false;
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM dev.member";

	public void check(EMember dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			String name = dto.getName();
			String major = dto.getMajor();

			while(rs.next()) {
				String id2 = rs.getString(1);
				String name2 = rs.getString(3);
				String major2 = rs.getString(5);
				
				if(name.equals(name2) && major.equals(major2)) {
					String message = name2 + "님의 아이디는 " + id2 + "입니다!"; 
					JOptionPane.showMessageDialog(null, message, "Information Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public CInfocheck() {
		
	}

}
