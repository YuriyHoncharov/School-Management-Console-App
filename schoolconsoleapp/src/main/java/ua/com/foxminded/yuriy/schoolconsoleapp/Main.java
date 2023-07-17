package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

public class Main {

	public static void main(String[] args) {
		// Initiazile data
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.initializeAndPopulateTestDatabase();
		
		// Register Commands to Console Menu
		
		Invoker.registerCommands();
		ConsoleMenu consoleMenu = new ConsoleMenu();
		consoleMenu.setCommands(Invoker.commandMap);
		
		// Setting scanner to Console Menu
		consoleMenu.setScanner(new Scanner(System.in));
		consoleMenu.run();
	}
}
