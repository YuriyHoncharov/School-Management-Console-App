package ua.com.foxminded.yuriy.schoolconsoleapp.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnectionUtil {

    public final static String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    public final static String user = "sa";
    public final static String password = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect: " + e.getMessage());
        }
    }
}