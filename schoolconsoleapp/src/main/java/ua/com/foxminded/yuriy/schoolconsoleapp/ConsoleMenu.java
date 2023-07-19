package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;

public class ConsoleMenu {

	public static final String LINE = " - ";
	private Map<String, Command> commands;
	private Scanner sc;

	public void setScanner(Scanner sc) {
		this.sc = sc;
	}

	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

	public void run() {

		PrintStream printStream = System.out;

		while (true) {
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
