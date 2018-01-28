package com.qbao.ai.parse.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtil {
	
	private static String datasourcepath="datasource.properties";
	static final String JDBC_DRIVER = SetSystemProperty.readValue(datasourcepath, "datasource.driverClassName");
	static final String DB_URL_STUFF = SetSystemProperty.readValue(datasourcepath, "stuff.datasource.url");
	static final String USER_STUFF = SetSystemProperty.readValue(datasourcepath, "stuff.datasource.username");
	static final String PASS_STUFF = SetSystemProperty.readValue(datasourcepath, "stuff.datasource.password");
	
	static final String DB_URL_AI = SetSystemProperty.readValue(datasourcepath, "ai.datasource.url");
	static final String USER_AI = SetSystemProperty.readValue(datasourcepath, "ai.datasource.username");
	static final String PASS_AI = SetSystemProperty.readValue(datasourcepath, "ai.datasource.password");
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public void createConnStuff() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL_STUFF, USER_STUFF, PASS_STUFF);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createConnAI() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL_AI, USER_AI, PASS_AI);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public ResultSet getResultSet(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public boolean execute(String sql) {
		boolean rest = false;
		try {
			rest = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rest;
	}
	public void addBatch(String sql) {
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int[] executeBatch() {
		int[] res = new int[0];
		try {
			res = stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public void closeConn() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
