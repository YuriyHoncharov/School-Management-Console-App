package ua.com.foxminded.yuriy.schoolconsoleapp.util;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

	@Test
	void getNextIntTest_ValidInput_ShouldReturnInt() {
		String input = "123";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scanner = new Scanner(System.in);
		int result = InputValidator.getNextInt(scanner);
		assertEquals(123, result);
	}

	@Test
	void getNextIntTest_InvalidInput_ShouldRetryUntilValidInput() {
		String input = "abc\n456";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scanner = new Scanner(System.in);
		int result = InputValidator.getNextInt(scanner);
		assertEquals(456, result);
	}

	@Test
	void getValidChoiceTest_InvalidInput_ShouldRetryUntilValidInput() {
		// Prepare
		String input = "3\n2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scanner = new Scanner(System.in);
		int result = InputValidator.getValidChoice(scanner);
		assertEquals(2, result);
	}

	@Test
	void isAlphabeticalInputTest_ValidInput_ShouldReturnInputString() {
		String input = "John";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scanner = new Scanner(System.in);
		String result = InputValidator.isAlphabeticalInput(scanner);
		assertEquals("John", result);
	}

	@Test
	void isAlphabeticalInputTest_InvalidInput_ShouldRetryUntilValidInput() {
		String input = "123\nJane";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scanner = new Scanner(System.in);
		String result = InputValidator.isAlphabeticalInput(scanner);
		assertEquals("Jane", result);
	}
}
