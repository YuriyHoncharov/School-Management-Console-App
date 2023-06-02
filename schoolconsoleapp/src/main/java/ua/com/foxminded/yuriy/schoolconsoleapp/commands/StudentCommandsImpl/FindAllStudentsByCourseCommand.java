package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class FindAllStudentsByCourseCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute() throws DaoException {
		System.out.println("Please enter the course name..");
		Scanner sc = new Scanner(System.in);
		String courseName = sc.nextLine();
		sc.close();
		List<Student> studentList = studentService.findAllByCourse(courseName);
		for (Student student : studentList) {
			System.out.println(student.getId() + ". " + student.getFirstName() + " " + student.getLastName());
		}
	}

	@Override
	public String name() {
		return "3";
	}

	@Override
	public String description() {
		return "Find students by course name.";
	}

}
