package ua.com.foxminded.yuriy.schoolconsoleapp.reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	
	private final String url;
	private final String username;
	private final String password;
	
	public DataBaseConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public Connection getConnection () throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
}
