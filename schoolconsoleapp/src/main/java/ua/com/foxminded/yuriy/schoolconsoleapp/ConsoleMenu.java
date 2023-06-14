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

		PrintStream printStream = System.out;

		Invoker.registerCommands();

		Map<String, Command> commands = Invoker.commandMap;

		while (true) {
			Scanner sc = new Scanner(System.in);
			printStream.println("Please select the command to execute..");
			for (Command command : commands.values()) {
				printStream.println(command.name() + LINE + command.description());
			}
			printStream.println("Enter \"Exit\" if you want to quit.");
			String command = sc.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				sc.close();
				System.out.println("Application is closed.");
				break;
			} else if (commands.containsKey(command)) {
				commands.get(command).execute(sc);
			} else {
				printStream.println("Invalid command, please try again.");

			}
		}

	}

}
