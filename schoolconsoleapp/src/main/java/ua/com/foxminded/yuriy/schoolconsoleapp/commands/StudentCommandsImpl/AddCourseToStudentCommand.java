package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

public class AddCourseToStudentCommand implements Command {
	private CourseService courseService = new CourseServiceImpl();
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute(Scanner sc) {

		Student student = getStudent(sc);
		if (student.getFirstName() == null) {
			System.out.println("Student with provided ID is not found.");
		} else {
			List<Course> courses = chooseCourse(sc, student);
			int choosenCourse = InputValidator.getNextInt(sc);
			boolean courseExist = courses.stream().anyMatch(course -> course.getId() == choosenCourse);
			if (!courseExist) {
				System.out.println("This number is missing, please choose the course from the list");
			} else {
				addCourse(student, choosenCourse);
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Please enter the student's id...");
		int studentId = InputValidator.getNextInt(sc);
		Student student = studentService.getById(studentId);
		return student;
	}

	private List<Course> chooseCourse(Scanner sc, Student student) {
		List<Course> availableCourses = courseService.availableCourses(student.getId());
		System.out.println("Please enter Course ID you want to add...");
		for (Course course : availableCourses) {
			System.out.println(course.toString());
		}
		return availableCourses;
	}

	private void addCourse(Student student, int choosenCourse) {
		Course selectedCourse = courseService.getById(choosenCourse);
		courseService.addCourse(selectedCourse, student.getId());
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
