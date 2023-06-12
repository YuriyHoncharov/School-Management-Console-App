package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class AddCourseToStudentCommand implements Command {
	CourseService courseService = new CourseServiceImpl();

	@Override
	public void execute() throws DaoException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the student's id...");
		int studentId = scanner.nextInt();
		List<Course> availableCourses = courseService.availableCourses(studentId);

		System.out.println("Please select the course to add...");

		for (int i = 0; i < availableCourses.size(); i++) {
			Course course = availableCourses.get(i);
			System.out.println((i + 1) + ". " + course.getName() + " | " + course.getDescription());
		}

		int choosenCourse = scanner.nextInt();
		scanner.close();

		if (choosenCourse - 1 >= availableCourses.size()) {
			throw new DaoException("This number is missing, please choose the course from the list");
		}
		Course selectedCourse = availableCourses.get(choosenCourse - 1);
		courseService.addCourse(selectedCourse, studentId);
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
