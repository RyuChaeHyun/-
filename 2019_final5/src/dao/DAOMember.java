package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import entity.EMember;

public class DAOMember {

	public static boolean create(EMember dto) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement stmt = null;
		String id = dto.getId();
		String password = dto.getPassword();
		String name = dto.getName();
		String email = dto.getEmail();
		String major = dto.getMajor();
		
		File f1 = new File("data/memberlist");
		FileWriter fw1;
		
		 try {
			 fw1 = new FileWriter(f1,true);
			 fw1.write(id+ " " + password + " " + name + " " + email + " " + major +"\r");
			 
			 String sql = "INSERT INTO dev.member VALUES(?,?,?,?,?);";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, email);
			stmt.setString(5, major);
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				System.out.println("Fail:INSERT DATA");
			}
			else {
				System.out.println("SUCCESS:INSERT DATA");
			}
			
			flag = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			flag=false;
		} catch (SQLException e) {
			System.out.println(e);
			flag=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					if(stmt!=null) {
					stmt.close();
				} if(con!=null) {
					con.close();
				}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		 
		 File file2 = new File("data//" + id + "basket");
		 File file3 = new File("data//" + id + "sincheong");
		 try {
			file2.createNewFile();
			file3.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
			return flag;
		}

	public static boolean idcheck(EMember dto) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement stmt = null;
		String id = dto.getId();
		
		 try {
				String sql = "INSERT INTO dev.member VALUES(?,?,?,?,?);";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				System.out.println("Fail:INSERT DATA");
			}
			else {
				System.out.println("SUCCESS:INSERT DATA");
			}
			
			flag = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			flag=false;
		} catch (SQLException e) {
			System.out.println(e);
			flag=false;
		}finally {
			
				try {
					if(stmt!=null) {
					stmt.close();
				} if(con!=null) {
					con.close();
				}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
			return flag;
		}
	
	public Vector<EMember> getUser(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		Vector<EMember> eMembers = new Vector<EMember>();
		while(sc.hasNext()) {
			EMember eMember = new EMember();
			eMember.setId(sc.next());
			eMember.setPassword(sc.next());
			eMember.setName(sc.next());
			eMember.setEmail(sc.next());
			eMember.setMajor(sc.next());
						eMembers.add(eMember);
		}
		return eMembers;
	}
	
	public static boolean create2(EMember dto) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement stmt = null;
		String id = dto.getId();
		String password = dto.getPassword();
		String name = dto.getName();
		String email = dto.getEmail();
		String major = dto.getMajor();
		
		File f1 = new File("data/adminlist");
		FileWriter fw1;
		
		 try {
			 fw1 = new FileWriter(f1,true);
			 fw1.write(id+ " " + password + " " + name + " " + email + " " + major +"\r");
			 fw1.close();
			 String sql = "INSERT INTO dev.admin VALUES(?,?,?,?,?);";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC", "root", "lch6683!");
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, email);
			stmt.setString(5, major);
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				System.out.println("Fail:INSERT DATA");
			}
			else {
				System.out.println("SUCCESS:INSERT DATA");
			}
			
			flag = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			flag=false;
		} catch (SQLException e) {
			System.out.println(e);
			flag=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					if(stmt!=null) {
					stmt.close();
				} if(con!=null) {
					con.close();
				}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		 
			return flag;
		}
	
}
