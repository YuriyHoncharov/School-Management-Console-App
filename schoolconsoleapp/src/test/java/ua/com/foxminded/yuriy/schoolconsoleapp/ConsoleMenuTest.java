package ua.com.foxminded.yuriy.schoolconsoleapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;

@ExtendWith(MockitoExtension.class)
class ConsoleMenuTest {
	
	@Mock
	private Command commandMock;	
	@Mock
	private Invoker invokerMock;	
	private MockedStatic<Invoker> mockedStatic;
	private final PrintStream systemOut = System.out;	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		mockedStatic = mockStatic(Invoker.class);
	}

	@AfterEach
	void tearDown() throws IOException {
		System.setOut(systemOut);
		mockedStatic.close();
	}
	
	private void runConsoleMenuWithCommand(String userInput) {
		ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(testIn);
		ConsoleMenu consoleMenu = new ConsoleMenu(invokerMock);
		consoleMenu.run();
	}

	@Test
	void run_exitCommand_ShouldPrintRightMessage() {		
		runConsoleMenuWithCommand("exit");
		verify(commandMock, times(0)).execute(any());		
	}

	@Test
	void run_executeRightCommand_shouldExecuteCommand() {	    	    
	    Map<String, Command> commandsMock = new HashMap<>();
	    commandsMock.put("1", commandMock);
	    when(invokerMock.registerCommands()).thenReturn(commandsMock);
	    doNothing().when(commandMock).execute(any());	    
	    runConsoleMenuWithCommand("1\nexit\n");
	    verify(commandMock, times(1)).execute(any());	    
	}		
}
