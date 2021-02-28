package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Entity(Model)
public class ELogin {

	private String userID;
	private String userPassword;
	Connection con;

	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM dev.member";
	String sql2 = "SELECT * FROM dev.admin";
	File f = new File("data/memberlist");
	FileWriter fw;
	
	public int authenticate(String userID, String userPassword) throws FileNotFoundException {
		int result = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();


			while (rs.next()) {
				String id = rs.getString(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String major = rs.getString(5);
				fw = new FileWriter(f, true);
//				fw.write(id + " " + pass + " " + name 
//						+ " " + email + " " + major + "\r");
				fw.close();
				
				if (userID.equals(id) && userPassword.equals(pass)) {
					result = 1;
				}
			}
			if (result==0) {
				ps = con.prepareStatement(sql2);
				rs = ps.executeQuery();

				while (rs.next()) {
					String id2 = rs.getString(1);
					String pass2 = rs.getString(2);
					String name2 = rs.getString(3);
					String email2 = rs.getString(4);
					String major2 = rs.getString(5);

					if (userID.equals(id2) && userPassword.equals(pass2)) {
						result = 2;
					} else {
						System.out.println("잘못된 사용자입니다!");
					}
				}
			}

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ELogin() {

	}

}
