package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class DeleteStudentCourseCommand implements Command {
	private CourseService courseService = new CourseServiceImpl();
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute(Scanner sc) throws DaoException {
		System.out.println("Please enter student ID..");

		while (!sc.hasNextInt()) {
			sc.next();
			System.out.println("You should enter a numeric value, please retry.");
		}
		int studentId = sc.nextInt();
		Student student = studentService.getById(studentId);
		List<Course> actualCourses = courseService.actualCourses(studentId);

		if (student.getFirstName() == null) {
			System.out.println("Student with following ID : " + "[ " + studentId + " ] is not found.");
		} else {
			for (int i = 0; i < actualCourses.size(); i++) {
				Course course = actualCourses.get(i);
				System.out.println((i + 1) + ". " + course.toString());

			}

			System.out.println("Please enter the course ID from which you wish to remove the student.");

			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("You should enter a numeric value, please retry.");
			}

			int choosenCourse = sc.nextInt();

			Optional<Course> desiredCourse = actualCourses.stream().filter(course -> course.getId() == choosenCourse)
					.findFirst();

			if (desiredCourse.isPresent()) {
				courseService.deleteCourse(studentId, choosenCourse);
				System.out.println("Course has been succesfully removed.");
			} else {
				System.out.println(
						"Unable to find the course with the provided ID. Please ensure that the ID is correct and the student is not already unenrolled from this course..");
			}
		}
	}

	@Override
	public String name() {
		return "5";
	}

	@Override
	public String description() {
		return "Deregister student from a course.";
	}

}
