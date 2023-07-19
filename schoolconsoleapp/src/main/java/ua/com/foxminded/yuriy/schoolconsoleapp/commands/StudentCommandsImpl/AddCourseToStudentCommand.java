package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

public class AddCourseToStudentCommand implements Command {
	private CourseService courseService = new CourseServiceImpl();
	private StudentService studentService = new StudentServiceImpl();

	public AddCourseToStudentCommand(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;

	}

	@Override
	public void execute(Scanner sc) {

		Student student = getStudent(sc);
		if (student == null) {
			System.out.println("Student with provided ID is not found.");
		} else {
			List<Course> courses = availableCourses(student);
			int choosenCourse = InputValidator.getNextInt(sc);
			boolean courseExist = courses.stream().anyMatch(course -> course.getId() == choosenCourse);
			if (!courseExist) {
				System.out.println("This number is missing, please choose the course from the list");
			} else {
				addCourseToStudent(student, choosenCourse);
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Please enter the student's id...");
		int studentId = InputValidator.getNextInt(sc);
		return studentService.getById(studentId);

	}

	private List<Course> availableCourses(Student student) {
		List<Course> availableCourses = courseService.getAvailableCourses(student.getId());
		System.out.println("Please enter Course ID you want to add...");
		for (Course course : availableCourses) {
			System.out.println(course.toString());
		}
		return availableCourses;
	}

	private void addCourseToStudent(Student student, int choosenCourse) {
		Course selectedCourse = courseService.getById(choosenCourse);
		courseService.addToStudent(selectedCourse, student.getId());
		System.out.println("Course has been succesfuly added to the student.");
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
