package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.HashMap;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl.FindGroupsByStudentsNumberCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddCourseToStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.FindAllStudentsByCourseCommand;

public class Invoker {

	public static final Map<String, Command> commandMap = new HashMap<>();

	public static void registerCommands() {
		AddStudentCommand addStudentCommand = new AddStudentCommand();
		DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand();
		FindAllStudentsByCourseCommand findAllStudentsByCourseCommand = new FindAllStudentsByCourseCommand();
		AddCourseToStudentCommand addCourseToStudentCommand = new AddCourseToStudentCommand();
		DeleteStudentCourseCommand deleteStudentCourseCommand = new DeleteStudentCourseCommand();
		FindGroupsByStudentsNumberCommand findGroupsByStudentsNumberCommand = new FindGroupsByStudentsNumberCommand();

		commandMap.put(addStudentCommand.name(), addStudentCommand);
		commandMap.put(deleteStudentCommand.name(), deleteStudentCommand);
		commandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);
		commandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);
		commandMap.put(deleteStudentCourseCommand.name(), deleteStudentCourseCommand);
		commandMap.put(findGroupsByStudentsNumberCommand.name(), findGroupsByStudentsNumberCommand);
	}

}
