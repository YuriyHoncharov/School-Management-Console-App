package ua.com.foxminded.yuriy.schoolconsoleapp;

import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

public class Main {

	public static void main(String[] args) throws Exception {
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.initializeAndPopulateTestDatabase();
		ConsoleMenu consoleMenu = new ConsoleMenu();
		consoleMenu.run();
	}
}
