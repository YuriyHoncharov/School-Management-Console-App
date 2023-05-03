package ua.com.foxminded.yuriy.schoolconsoleapp.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class FileHandler {

	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";

	public static String readFile(String filePath) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get(filePath));
		return String.join(NEW_LINE, lines);
	}
}
