package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findAllByCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student add() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter student's name..");
		String name = sc.nextLine();
		System.out.println("Now please enter student's last name...");
		String lastName = sc.nextLine();
		System.out.println("And the last one, please enter the student's Group ID");
		int groupId = sc.nextInt();
		Student newStudent = new Student(name, lastName, groupId);
		System.out.println("Thank you!");
		return newStudent;
	}

	@Override
	public int deleteById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the student's ID you want to delete from database...");
		return sc.nextInt();
	}

	@Override
	public void addCourse(List<Course> courses) {
		// TODO Auto-generated method stub

	}

	@Override
	public int courseIdToDelete() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select the course ID from list...");
		int courseId = sc.nextInt();
		return courseId;
	}

	@Override
	public void deleteCourse() {
		int studentId = deleteById();
		int courseIdToDelete = courseIdToDelete();
		System.out.println();

	}

	@Override
	public void deleteCourseById() {
		// TODO Auto-generated method stub

	}

}
