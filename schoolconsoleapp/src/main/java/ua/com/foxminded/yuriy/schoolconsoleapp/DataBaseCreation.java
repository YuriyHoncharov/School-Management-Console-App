package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseCreation {

	public static void createDataBase() throws Exception {
		String DB_CREATION_FILEPATH = "data_base_creation.sql";

		// Database connection

		String url = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "1234";

		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();

		try (BufferedReader read = new BufferedReader(new FileReader(DB_CREATION_FILEPATH))) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = read.readLine()) != null) {
				sb.append(line);
				if (line.endsWith(";")) {
					statement.execute(line);
					sb.setLength(0);
				}
			}
		} finally {
			connection.close();
			statement.close();
		}
	}

	public static void createTables() throws Exception {

		String TABLE_CREATION_FILEPATH = "tables_creation.sql";
		String url = "jdbc:postgresql://localhost:5432/school";
		String user = "yuriy";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();

		try (BufferedReader read = new BufferedReader(new FileReader(TABLE_CREATION_FILEPATH))) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = read.readLine()) != null) {
				sb.append(line);
				if (line.endsWith(";")) {
					stmt.execute(sb.toString());
					sb.setLength(0);
				}
			}
		} finally {
			conn.close();
			stmt.close();
		}

	}

}
