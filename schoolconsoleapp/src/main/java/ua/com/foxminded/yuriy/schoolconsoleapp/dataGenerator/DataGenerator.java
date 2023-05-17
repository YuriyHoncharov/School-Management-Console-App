package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.customexception.SqlRunException;
import ua.com.foxminded.yuriy.schoolconsoleapp.reader.FileHandler;

public class DataGenerator {

	public final static String FILE_PATH = "src/main/resources/";
	public final static String DATA_BASE_PATH = "data_base_creation.sql";
	public final static String TABLE_PATH = "tables_creation.sql";
	public final static String DATA_BASE_FILE_PATH = FILE_PATH + DATA_BASE_PATH;
	public final static String TABLE_FILE_PATH = FILE_PATH + TABLE_PATH;

	public void createDataBase() {

		runSQLScript(FileHandler.readFile(DATA_BASE_FILE_PATH));
		System.out.println("Database created.");

	}

	public void createTables() {

		runSQLScript(FileHandler.readFile(TABLE_FILE_PATH));
		System.out.println("Tables were created.");

	}

	public void runSQLScript(String sqlQuery) {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			statement.execute();
		} catch (SqlRunException e) {	
			System.out.println("An error occured: " + e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
