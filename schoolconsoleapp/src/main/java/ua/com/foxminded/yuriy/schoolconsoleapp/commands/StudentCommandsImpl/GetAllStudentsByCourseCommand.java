package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class GetAllStudentsByCourseCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute(Scanner sc) {
		System.out.println("Please enter the course name..");
		String courseName = sc.nextLine();
		List<Student> studentList = studentService.getAllByCourse(courseName);
		for (Student student : studentList) {
			System.out.println(student.getId() + ". " + student.getFirstName() + " " + student.getLastName());
		}
	}

	@Override
	public String name() {
		return "6";
	}

	@Override
	public String description() {
		return "Find students by course name.";
	}
}
