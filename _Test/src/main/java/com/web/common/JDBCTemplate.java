package com.web.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	public static Connection getConnection() {
		Properties dirver = new Properties();
		Connection conn = null;
		try {
			String path = JDBCTemplate.class.getResource("/driver.properties").getPath();
			dirver.load(new FileReader(path));
			Class.forName(dirver.getProperty("driver"));
			conn = DriverManager.getConnection(dirver.getProperty("url"), dirver.getProperty("user"), dirver.getProperty("pw"));
			conn.setAutoCommit(false);
		
		} catch (Exception e) {
			e.printStackTrace();
		} return conn;
	}	
	public static void close(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement st) {
		try {
			if(st!=null&&!st.isClosed()) st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null&&!rs.isClosed()) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
