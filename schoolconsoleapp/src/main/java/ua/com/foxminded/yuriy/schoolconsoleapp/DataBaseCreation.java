package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseCreation {

	public static void DataBaseCreation() throws Exception {
		String DB_CREATION_FILEPATH = "data_base_creation.sql";
		String TABLE_CREATION_FILEPATH = "tables_creation.sql";

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
}
