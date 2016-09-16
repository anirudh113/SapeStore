package com.sapestore.service.impl;

import java.sql.*;

public class CDPdbConnect {
	


	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://ec2-52-39-59-106.us-west-2.compute.amazonaws.com/cdpDev?autoReconnect=true&useSSL=false";

	// Database credentials
	static final String USER = "cdpuser";
	static final String PASS = "Sapient@12";

	
	
	
	public void insertCookieId(String userId,String cookieId) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "insert into cdpDev.user_mapping (EmailId, user_mapping_id, isActive) values ('" + userId + "','" + cookieId + "',1)";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
				
	}
	
	public void insertUserDetails(String userId,String name, String phone) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			int id=123;
			sql = "insert into cdpDev.user_master (id, EmailId, First_Name, Last_Name, Phone, prefix, suffix, Organization_Name) values "
					+ "('" + id + "','" + userId + "','" + name + "','" + name + "','" + phone + "','Mr','Jr','SapeStore')";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
				
	}


}
