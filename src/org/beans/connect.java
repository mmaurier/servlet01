package org.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
	private String book_name = null;
	
	public connect() {}
	
	public String getBook_name() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost","root","");
			
			String query = "SELECT book_name " +  
						    "FROM bible.books " + 
						    "WHERE book_id = 1";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
//			int result = 0;
			while (rs.next()) {
				this.book_name = rs.getString("book_name");
			}	
			conn.close(); rs.close(); st.close();	
		} catch (SQLException e) {
			System.out.println("connection failed...");
			this.book_name = "connection failed...";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

//	public String readRow(){
//		
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost","root","");
//			
//			String query = "SELECT *" +  
//						    "FROM bible.books " + 
//						    "WHERE book_id = 1";
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			int result = 0;
//			while (rs.next()) {
//				book_name = rs.getString("book_name");
//			}	
//			conn.close(); rs.close(); st.close();	
//		} catch (SQLException e) {
//			System.out.println("connection failed...");
//			book_name = "connection failed...";
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}		
//		return book_name;
//	}
}
