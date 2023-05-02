package ua.com.foxminded.yuriy.schoolconsoleapp.reader;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLFileReader {

	private final Connection connection;	
	
	public SQLFileReader(Connection connection) {
		this.connection = connection;
	}
	
	public void runSQLFile(String filePath) throws SQLException, IOException {

		String SQLQuery = FileHandler.readFile(filePath);
		Statement statement = connection.createStatement();
		statement.execute(SQLQuery);		
	}
}
