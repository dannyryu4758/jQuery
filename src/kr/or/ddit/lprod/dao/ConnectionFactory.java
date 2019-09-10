package kr.or.ddit.lprod.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *  Factory Object[Method] Pattern
 *
 */
public class ConnectionFactory {
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.ibatis.config.SqlMapConfig");
		try {
			String driverClassName = bundle.getString("driver");
			Class.forName(driverClassName);
			url = bundle.getString("url");
			user = bundle.getString("username");
			password = bundle.getString("password");
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	static String url;
	static String user;
	static String password;
	
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
		
	}
}











