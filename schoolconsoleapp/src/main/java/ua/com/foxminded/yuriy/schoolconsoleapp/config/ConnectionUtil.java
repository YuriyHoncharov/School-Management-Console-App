package ua.com.foxminded.yuriy.schoolconsoleapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.com.foxminded.yuriy.schoolconsoleapp.exception.ConnectException;

public class ConnectionUtil {

	public final static String url = "jdbc:postgresql://localhost:5432/school";
	public final static String urlCreateDataBase = "jdbc:postgresql://localhost:5432/";
	public final static String user = "postgres";
	public final static String password = "1234";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new ConnectException("Failed to connect: " + e.getMessage());
		}
	}
	public static Connection getConnectionForCreate() {
		try {
			return DriverManager.getConnection(urlCreateDataBase, user, password);
		} catch (Exception e) {
			throw new ConnectException("Failed to connect: " + e.getMessage());
		}
	}
}
