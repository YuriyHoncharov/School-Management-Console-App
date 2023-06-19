package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.input.InputValidator;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class AddCourseToStudentCommand implements Command {
	private CourseService courseService = new CourseServiceImpl();
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute(Scanner sc) {
		System.out.println("Please enter the student's id...");
		int studentId = InputValidator.getNextInt(sc);
		Student student = studentService.getById(studentId);
		if (student.getFirstName() == null) {
			System.out.println("Student with following ID : " + "[ " + studentId + " ] is not found.");
		} else {
			List<Course> availableCourses = courseService.availableCourses(studentId);
			System.out.println("Please enter Course ID you want to add...");
			for (int i = 0; i < availableCourses.size(); i++) {
				Course course = availableCourses.get(i);
				System.out.println((i + 1) + ". " + course.toString());
			}
			int choosenCourse = InputValidator.getNextInt(sc);
			boolean courseExist = availableCourses.stream().anyMatch(course -> course.getId() == choosenCourse);
			if (!courseExist) {
				System.out.println("This number is missing, please choose the course from the list");
			}
			Course selectedCourse = availableCourses.get(choosenCourse - 1);
			courseService.addCourse(selectedCourse, studentId);
			System.out.println("Course has been succesfuly added to the student.");
		}
	}

	@Override
	public String name() {
		return "2";
	}

	@Override
	public String description() {
		return "Add course to the student.";
	}
}
