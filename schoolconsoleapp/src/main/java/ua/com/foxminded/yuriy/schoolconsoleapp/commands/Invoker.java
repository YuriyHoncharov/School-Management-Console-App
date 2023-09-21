package ua.com.foxminded.yuriy.schoolconsoleapp.commands;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Invoker {	
	private Map<String, Command> commandMap = new HashMap<>();	
	
	@Autowired
	public Invoker(ApplicationContext applicationContext) {
		Map <String, Command> allCommands = applicationContext.getBeansOfType(Command.class);
		for (Command command : allCommands.values()) {
			commandMap.put(command.name(), command);
		}
	}	
	public final Map<String, Command> registerCommands() {
		return commandMap;
	}
}
