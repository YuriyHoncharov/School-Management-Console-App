package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;

@Component
public class ConsoleMenu {

	public static final String LINE = " - ";
	private Invoker invoker;
	private CustomLogger customLogger;

	@Autowired
	public ConsoleMenu(Invoker invoker, CustomLogger customLogger) {
		this.customLogger = customLogger;
		this.invoker = invoker;
	}

	public void run() {

		PrintStream printStream = System.out;
		Map<String, Command> commands = invoker.registerCommands();
		Scanner sc = new Scanner(System.in);

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
				customLogger.logInfo("User executed command number : " + command);
				commands.get(command).execute(sc);
			} else {
				printStream.println("Invalid command, please try again.");
				customLogger.logInfo("User inserted command that not exist.");
			}
		}
	}
}
