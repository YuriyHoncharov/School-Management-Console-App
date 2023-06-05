package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class AddStudentCommand implements Command{
	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public void execute() throws DaoException {
		System.out.println("Enter student name..");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Enter student lastname..");
		String lastName = sc.nextLine();
		Student newStudent = new Student(name, lastName);
		studentService.add(newStudent);
	}

	@Override
	public String name() {
		return "3";
	}

	@Override
	public String description() {
		return "Add new student.";
	}

}
