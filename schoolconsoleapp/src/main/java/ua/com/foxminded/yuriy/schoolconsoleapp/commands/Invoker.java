package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.HashMap;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl.GetGroupsByStudentsNumberCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddCourseToStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.GetAllStudentsByCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;

public class Invoker {

	public static final Map<String, Command> commandMap = new HashMap<>();

	public static void registerCommands() {
		CourseService courseService = new CourseServiceImpl();
		StudentService studentService = new StudentServiceImpl();
		GroupService groupService = new GroupServiceImpl();
		
		AddStudentCommand addStudentCommand = new AddStudentCommand(studentService, groupService);
		DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(studentService);
		GetAllStudentsByCourseCommand findAllStudentsByCourseCommand = new GetAllStudentsByCourseCommand(studentService);
		AddCourseToStudentCommand addCourseToStudentCommand = new AddCourseToStudentCommand(courseService, studentService);
		DeleteStudentCourseCommand deleteStudentCourseCommand = new DeleteStudentCourseCommand(courseService, studentService);
		GetGroupsByStudentsNumberCommand findGroupsByStudentsNumberCommand = new GetGroupsByStudentsNumberCommand(groupService);

		commandMap.put(addStudentCommand.name(), addStudentCommand);
		commandMap.put(deleteStudentCommand.name(), deleteStudentCommand);
		commandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);
		commandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);
		commandMap.put(deleteStudentCourseCommand.name(), deleteStudentCourseCommand);
		commandMap.put(findGroupsByStudentsNumberCommand.name(), findGroupsByStudentsNumberCommand);
	}
}
