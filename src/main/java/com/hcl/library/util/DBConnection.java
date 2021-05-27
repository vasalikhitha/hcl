package com.hcl.library.util;

import java.sql.*;

public class DBConnection {
	private final static String url = "jdbc:mysql://localhost:3306/library";
	private final static String user = "root";
	private final static String password = "1234";
	
	public DBConnection() {
		
	}
	private static Connection con=null;
	public static Connection getConnection() {
		
		try {
			if(con==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url,user,password);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
}
