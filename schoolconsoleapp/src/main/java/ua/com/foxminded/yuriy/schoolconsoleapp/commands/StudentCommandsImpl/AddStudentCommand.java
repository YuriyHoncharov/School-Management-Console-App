package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class AddStudentCommand implements Command{
	private StudentDao studentDao;

	@Override
	public void execute() throws DaoException {
		System.out.println("Enter student name..");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Enter student lastname..");
		String lastName = sc.nextLine();
		Student newStudent = new Student(name, lastName);
		studentDao.add(newStudent);
	}

	@Override
	public String name() {
		return "1";
	}

	@Override
	public String description() {
		return "Add new student.";
	}

}
