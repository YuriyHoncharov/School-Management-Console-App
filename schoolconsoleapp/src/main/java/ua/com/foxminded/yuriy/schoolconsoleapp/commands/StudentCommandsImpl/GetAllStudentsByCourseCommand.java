package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;
@Component
public class GetAllStudentsByCourseCommand implements Command {
	
	private StudentService studentService = new StudentServiceImpl();
	
	@Autowired
	public GetAllStudentsByCourseCommand(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void execute(Scanner sc) {
		System.out.println("Please enter the course name..");
		String courseName = InputValidator.isAlphabeticalInput(sc);
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
