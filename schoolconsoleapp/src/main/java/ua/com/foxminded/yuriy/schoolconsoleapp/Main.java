package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import ua.com.foxminded.yuriy.schoolconsoleapp.reader.DataBaseConnection;
import ua.com.foxminded.yuriy.schoolconsoleapp.reader.SQLFileReader;

public class Main {

	public static void main(String[] args) throws Exception {

	}

	public final static String url = "jdbc:postgresql://localhost:5432/";
	public final static String user = "postgres";
	public final static String password = "1234";
	public final static String FILE_PATH = "/src/main/resources/";
	public final static String DATA_BASE_PATH = "data_base_creation.sql";
	public final static String TABLE_PATH = "tables_creation.sql";
	public final static String DATA_BASE_FILE_PATH = FILE_PATH + DATA_BASE_PATH;
	public final static String TABLE_FILE_PATH = FILE_PATH + TABLE_PATH;

	public DataBaseConnection connect = new DataBaseConnection(url, user, password);

	public void createDataBase() {

		try (Connection connection = connect.getConnection()) {
			SQLFileReader fileReader = new SQLFileReader(connection);
			fileReader.runSQLFile(DATA_BASE_FILE_PATH);
			System.out.println("Database created.");
			
		} catch (SQLException e) {
			System.out.println("Error: " + e);	
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public void createTables() {
		try (Connection connection = connect.getConnection()) {
			SQLFileReader SQLReader = new SQLFileReader(connection);
			SQLReader.runSQLFile(TABLE_FILE_PATH);
			System.out.println("Tables were created.");
		} catch (SQLException e) {
			System.out.println("Error: " + e);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
