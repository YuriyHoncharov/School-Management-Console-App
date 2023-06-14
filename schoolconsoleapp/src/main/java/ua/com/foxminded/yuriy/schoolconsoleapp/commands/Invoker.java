package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.HashMap;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl.GetGroupsByStudentsNumberCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddCourseToStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.GetAllStudentsByCourseCommand;

public class Invoker {

	public static final Map<String, Command> commandMap = new HashMap<>();

	public static void registerCommands() {
		AddStudentCommand addStudentCommand = new AddStudentCommand();
		DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand();
		GetAllStudentsByCourseCommand findAllStudentsByCourseCommand = new GetAllStudentsByCourseCommand();
		AddCourseToStudentCommand addCourseToStudentCommand = new AddCourseToStudentCommand();
		DeleteStudentCourseCommand deleteStudentCourseCommand = new DeleteStudentCourseCommand();
		GetGroupsByStudentsNumberCommand findGroupsByStudentsNumberCommand = new GetGroupsByStudentsNumberCommand();

		commandMap.put(addStudentCommand.name(), addStudentCommand);
		commandMap.put(deleteStudentCommand.name(), deleteStudentCommand);
		commandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);
		commandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);
		commandMap.put(deleteStudentCourseCommand.name(), deleteStudentCourseCommand);
		commandMap.put(findGroupsByStudentsNumberCommand.name(), findGroupsByStudentsNumberCommand);
	}

}
