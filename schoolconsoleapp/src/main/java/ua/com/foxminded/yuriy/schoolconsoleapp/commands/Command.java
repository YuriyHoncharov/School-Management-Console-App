package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

public interface Command {
	
	void execute();

	String name();

	String description();

}
