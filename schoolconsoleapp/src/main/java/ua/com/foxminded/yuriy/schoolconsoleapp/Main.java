package ua.com.foxminded.yuriy.schoolconsoleapp;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

public class Main {

	public static void main(String[] args) {
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.initializeAndPopulateTestDatabase();		
		Invoker invoker = new Invoker();
		ConsoleMenu consoleMenu = new ConsoleMenu(invoker);
		consoleMenu.run();
	}
}
