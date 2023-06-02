package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class ConsoleMenu {

	public static final String LINE = "-";

	public void run() throws DaoException {
		Scanner scanner = new Scanner(System.in);
		PrintStream printStream = System.out;

		Invoker.registerStudentCommands();
		Invoker.registerGroupCommands();
		Invoker.registerCourseCommands();

		Map<String, Command> studentCommands = Invoker.studentCommandMap;
		Map<String, Command> groupCommands = Invoker.groupCommandMap;
		Map<String, Command> courseCommands = Invoker.courseCommandMap;

		boolean running = true;

		while (running) {

			System.out.println("Please select the entity on which you want to perform operations:\r\n" + "1. Course\r\n"
					+ "2. Group\r\n" + "3. Student\r\n");
			int choosenEntity = scanner.nextInt();

			switch (choosenEntity) {
			case 1:
				boolean courseRunning = true;
				while (courseRunning) {
					printStream.println("Please select the operation: ");
					for (Command command : courseCommands.values()) {
						printStream.println(command.name() + LINE + command.description());
						printStream.println("Enter \"9\" to go back.\r\n" + "Enter \"0\" to exit.");

						int commandNum = scanner.nextInt();
						if (commandNum == 9) {
							courseRunning = false;
						} else if (commandNum == 0) {
							running = false;
							courseRunning = false;
						} else {
							courseCommands.get(command).execute();
						}
					}
					break;
				}

			case 2:
				boolean groupRunning = true;
				while (groupRunning) {
					printStream.println("Please select the operation: ");
					for (Command command : courseCommands.values()) {
						printStream.println(command.name() + LINE + command.description());
						printStream.println("Enter \"9\" to go back.\r\n" + "Enter \"0\" to exit.");

						int commandNum = scanner.nextInt();
						if (commandNum == 9) {
							groupRunning = false;
						} else if (commandNum == 0) {
							running = false;
							groupRunning = false;
						} else {
							groupCommands.get(command).execute();
						}
					}
					break;
				}
			case 3:
				boolean studentRunning = true;
				while (studentRunning) {
					printStream.println("Please select the operation: ");
					for (Command command : studentCommands.values()) {
						printStream.println(command.name() + LINE + command.description());
						printStream.println("Enter \"9\" to go back.\r\n" + "Enter \"0\" to exit.");

						int commandNum = scanner.nextInt();
						if (commandNum == 9) {
							studentRunning = false;
						} else if (commandNum == 0) {
							running = false;
							studentRunning = false;
						} else {
							studentCommands.get(command).execute();
						}
					}
				}
			}
		}
	}
}