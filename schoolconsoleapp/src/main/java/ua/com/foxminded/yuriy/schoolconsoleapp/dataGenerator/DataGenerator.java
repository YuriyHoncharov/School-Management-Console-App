package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.reader.FileHandler;

public class DataGenerator {

	private final Connection connection;
	public final static String FILE_PATH = "src/main/resources/";
	public final static String DATA_BASE_PATH = "data_base_creation.sql";
	public final static String TABLE_PATH = "tables_creation.sql";
	public final static String DATA_BASE_FILE_PATH = FILE_PATH + DATA_BASE_PATH;
	public final static String TABLE_FILE_PATH = FILE_PATH + TABLE_PATH;

	public void createDataBase() throws SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			runSQLScript(FileHandler.readFile(DATA_BASE_FILE_PATH));
			System.out.println("Database created.");
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public void createTables() throws SQLException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			runSQLScript(FileHandler.readFile(TABLE_FILE_PATH));
			System.out.println("Tables were created.");
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public DataGenerator(Connection connection) {
		this.connection = connection;
	}

	public void runSQLScript(String SQLQuery) throws SQLException, IOException {
		PreparedStatement statement = connection.prepareStatement(SQLQuery);
		statement.execute();
	}
}
