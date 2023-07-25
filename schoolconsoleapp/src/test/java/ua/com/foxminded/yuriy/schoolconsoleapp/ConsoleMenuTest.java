package ua.com.foxminded.yuriy.schoolconsoleapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;

class ConsoleMenuTest {
	@Mock
	private Command mockCommand;
	@Mock
	private Scanner mockScanner;

	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream testOut = new ByteArrayOutputStream();	
	private static final String LINE = " - ";
	private final PrintStream systemOut = System.out;	
	InputStream systemIn = System.in;

	private String commandsList() {
		Invoker.registerCommands();
		Map<String, Command> commands = Invoker.commandMap;
		StringBuilder commandsList = new StringBuilder();
		for (Command command : commands.values()) {
			commandsList.append(command.name());
			commandsList.append(LINE);
			commandsList.append(command.description() + "\r\n");
		}
		return commandsList.toString();
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		System.setOut(new PrintStream(testOut));
	}

	@AfterEach
	void tearDown() throws IOException {
		System.setIn(systemIn);
		System.setOut(systemOut);		
	}

	@Test
	void run_exitCommand_ShouldPrintRightMessage() {
		String command = "exit";
		String commandsList = commandsList();
		ConsoleMenu consoleMenu = new ConsoleMenu();
		Invoker.registerCommands();

//		consoleMenu.setCommands(Invoker.commandMap);
//		consoleMenu.setScanner(mockScanner);
		when(mockScanner.nextLine()).thenReturn(command);
		consoleMenu.run();
		String expectedOutput = "Please select the command to execute..\r\n" + commandsList
				+ "Enter \"Exit\" if you want to quit.\r\n" + "Application is closed.\r\n" + "";
		assertEquals(expectedOutput, testOut.toString());
	}

	@Test
	void run_executeRightCommand_shouldExecuteCommand() {
	    String commandNumber = "1";
	    testIn = new ByteArrayInputStream(commandNumber.getBytes());
	    System.setIn(testIn);

	    Map<String, Command> commands = new HashMap<>();
	    commands.put(commandNumber, mockCommand);

	    when(mockScanner.nextLine()).thenReturn(commandNumber).thenReturn("exit");

	    ConsoleMenu consoleMenu = new ConsoleMenu();
//	    consoleMenu.setCommands(commands);
//	    consoleMenu.setScanner(mockScanner);
	    consoleMenu.run();

	    verify(mockCommand, times(1)).execute(mockScanner);
	}
	
	@Test
	void run_commandNotFound_ShouldPrintRightMessage() {
		 String commandNumber = "1";
		 String wrongNumber = "99";
	    testIn = new ByteArrayInputStream(commandNumber.getBytes());
	    System.setIn(testIn);
	    String message = "Please select the command to execute..\r\n"
	    		+ "null - null\r\n"
	    		+ "Enter \"Exit\" if you want to quit.\r\n"
	    		+ "Invalid command, please try again.\r\n"
	    		+ "Please select the command to execute..\r\n"
	    		+ "null - null\r\n"
	    		+ "Enter \"Exit\" if you want to quit.\r\n"
	    		+ "Application is closed.\r\n"
	    		+ "";

	    Map<String, Command> commands = new HashMap<>();
	    commands.put(commandNumber, mockCommand);

	    when(mockScanner.nextLine()).thenReturn(wrongNumber).thenReturn("exit");

	    ConsoleMenu consoleMenu = new ConsoleMenu();
//	    consoleMenu.setCommands(commands);
//	    consoleMenu.setScanner(mockScanner);
	    consoleMenu.run();

	    assertEquals(message, testOut.toString());
	}

}
