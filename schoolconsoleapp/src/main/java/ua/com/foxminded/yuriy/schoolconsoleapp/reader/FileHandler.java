package ua.com.foxminded.yuriy.schoolconsoleapp.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {

	private static final String NEW_LINE = "\n";
	
	public static String readFile(String filePath) {

		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			return String.join(NEW_LINE, lines);
		} catch (IOException e) {
			System.out.println("Error while reading file: " + e);		
			return null; // have to solve this part, waiting for your answer =)
		}
	}
}
