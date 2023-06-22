package ua.com.foxminded.yuriy.schoolconsoleapp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.FileReadException;

public class FileHandler {

	private static final String NEW_LINE = "\n";

	public static String readFile(String filePath) {

		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			return String.join(NEW_LINE, lines);
		} catch (IOException e) {
			throw new FileReadException("Failed to read file with error: " + e.getMessage());
		}
	}
}