package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.FileReadException;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.SqlRunException;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.FileHandler;

public class DataGenerator {

	public final static String FILE_PATH = "src/main/resources/";
	public final static String DATA_BASE_PATH = "data_base_creation.sql";
	public final static String TABLE_PATH = "tables_creation.sql";
	public final static String DATA_BASE_FILE_PATH = FILE_PATH + DATA_BASE_PATH;
	public final static String TABLE_FILE_PATH = FILE_PATH + TABLE_PATH;

	public void createDataBase() {

		String urlCreateDataBase = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "1234";

		try (Connection connection = DriverManager.getConnection(urlCreateDataBase, user, password)) {
			PreparedStatement statement = connection.prepareStatement(FileHandler.readFile(DATA_BASE_FILE_PATH));
			statement.execute();
		} catch (Exception e) {
			throw new ua.com.foxminded.yuriy.schoolconsoleapp.exception.ConnectException(
					"Failed to create database, connection error: " + e.getMessage());
		}
		System.out.println("Database created.");

	}

	public void createTables() throws FileReadException {

		runSQLScript(FileHandler.readFile(TABLE_FILE_PATH));
		System.out.println("Tables were created.");
	}

	public void runSQLScript(String sqlQuery) {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			statement.execute();
		} catch (SQLException e) {
			throw new SqlRunException("An error occured: " + e.getMessage());
		}
	}

	public void initializeAndPopulateTestDatabase() throws FileReadException {
		createDataBase();
		createTables();
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		randomDataGenerator.generateStudents();
		randomDataGenerator.generateGroups();
		randomDataGenerator.generateCourses();
	}
}
