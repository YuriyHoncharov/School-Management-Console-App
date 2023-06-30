package ua.com.foxminded.yuriy.schoolconsoleapp.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.yuriy.schoolconsoleapp.exception.FileReadException;

class FileHandlerTest {

	@Test
	void readFile_ValidFilePath_ShouldReturnFileContent() {

		String filePath = "src/test/resources/testfile.txt";
		String expectedContent = "Hello World!;\nHello World!;\nHello World!;";
		String actualContent = FileHandler.readFile(filePath);

		assertEquals(expectedContent, actualContent);
	}
	
	@Test
	void readFile_InvalidFilePath_ShouldThrowExcpetion() {
		String filePath = "src/test/resources/uncorrecttestfile.txt";
		assertThrows(FileReadException.class, () -> FileHandler.readFile(filePath));
	}
}
