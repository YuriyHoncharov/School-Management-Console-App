package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class ConsoleMenu {

	public static final String LINE = " - ";

	public void run() throws DaoException {
		Scanner scanner = new Scanner(System.in);
		PrintStream printStream = System.out;

		Invoker.registerCommands();

		Map<String, Command> commands = Invoker.commandMap;

		while (true) {
			printStream.println("Please select the command to execute..");
			for (Command command : commands.values()) {
				printStream.println(command.name() + LINE + command.description());
			}
			printStream.println("Enter \"Exit\" if you want to quit.");
			String command = scanner.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				break;
			} else if (commands.containsKey(command)) {
				commands.get(command).execute();
			} else {
				printStream.println("Invalid command, please try again.");
			}
		}
		scanner.close();
	}
}
