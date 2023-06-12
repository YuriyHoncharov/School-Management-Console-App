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

public class DeleteStudentCourseCommand implements Command {
	CourseService courseService = new CourseServiceImpl();

	@Override
	public void execute() throws DaoException {
		System.out.println("Please enter student ID..");
		Scanner sc = new Scanner(System.in);
		int studentId = sc.nextInt();
		List<Course> actualCourses = courseService.actualCourses(studentId);

		for (int i = 0; i < actualCourses.size(); i++) {
			Course course = actualCourses.get(i);
			System.out.println(
					(i + 1) + ". " + course.getName() + " | " + course.getDescription() + " | ID : " + course.getId());
		}

		System.out.println("Please enter the course ID from which you wish to remove the student.");

		int choosenCourse = sc.nextInt();
		sc.close();

		Course desiredCourse = null;
		for (Course course : actualCourses) {
			if (course.getId() == choosenCourse) {
				desiredCourse = course;
				break;
			}
		}

		if (desiredCourse != null) {
			courseService.deleteCourse(studentId,choosenCourse);
			System.out.println("Course has been succesfully removed.");
		} else {
			System.out.println(
					"Unable to find the course with the provided ID. Please ensure that the ID is correct and the student is not already unenrolled from this course..");
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
