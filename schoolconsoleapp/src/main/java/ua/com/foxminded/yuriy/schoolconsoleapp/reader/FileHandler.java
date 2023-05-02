package ua.com.foxminded.yuriy.schoolconsoleapp.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class FileHandler {

	private static final String NEW_LINE = "\n";

	public static String readFile(String filePath) throws IOException {

		try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
			// ADD CATCH
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = read.readLine()) != null) {
				sb.append(line).append(NEW_LINE);		
			}
			return sb.toString();
		}
	}
}
