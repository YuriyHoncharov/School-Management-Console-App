package ua.com.foxminded.yuriy.schoolconsoleapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.ConnectException;

public class ConnectionUtil {

	public final static String url = "jdbc:postgresql://localhost:5432/school";
	public final static String user = "postgres";
	public final static String password = "1234";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new ConnectException("Failed to connect: " + e.getMessage());
		}
	}
}
