package ua.com.foxminded.yuriy.schoolconsoleapp.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

	public static int getNextInt(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("You should enter a numeric value, please retry.");
			sc.nextLine();
		}
		return Integer.parseInt(sc.nextLine());
	}

	public static int getValidChoice(Scanner sc) {
		String input = sc.nextLine();
		while (!input.equals("1") && !input.equals("2")) {
			System.out.println("Invalid input. Please enter either 1 or 2:");
			input = sc.nextLine();
		}
		return Integer.parseInt(input);
	}
	
	public static String isAlphabeticalInput (Scanner sc) {
		Pattern namePattern = Pattern.compile("[A-Za-z]+");
		String input = sc.nextLine();
		while (!namePattern.matcher(input).matches()) {
			System.out.println("Please enter a valid name using alpahbetic characters.");
			input = sc.nextLine();
		}
		return input;
	}
}
