package com.sapestore.service.impl;

import java.sql.*;

public class DbConnect {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://bangvmplsprintni05.sapient.com:3306/sapestore?zeroDateTimeBehavior=convertToNull";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "cloudera@mysql";

	public String getCookieId(String userId) {
		Connection conn = null;
		Statement stmt = null;
		String cookieid = "";
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT user_mapping_id FROM sapestore.userMapping where EmailId = '" + userId + "'";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {

				cookieid = rs.getString("user_mapping_id");

			}
			rs.close();
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
		return cookieid;
	}
	
	
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
			sql = "insert into sapestore.userMapping (EmailId, user_mapping_id, isActive) values ('" + userId + "','" + cookieId + "',1)";
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
