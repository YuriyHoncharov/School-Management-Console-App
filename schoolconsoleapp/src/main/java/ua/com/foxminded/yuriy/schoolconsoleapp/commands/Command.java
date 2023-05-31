package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface Command {
	
	void execute() throws DaoException;

	String name();

	String description();

}
