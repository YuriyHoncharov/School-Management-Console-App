package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public class RandomDataGenerator {

	int groupCount = 10;
	int studentCount = 200;
	int maxStudentInGroup = 20;

	Random random = new Random();

	public List<Group> generateGroups() {
		int groupId = 1;
		List<Group> groupNames = new ArrayList<>();
		for (int i = 0; i < groupCount; i++) {
			char c1 = (char) (random.nextInt(26) + 'A');
			char c2 = (char) (random.nextInt(26) + 'A');
			int num1 = random.nextInt(10);
			int num2 = random.nextInt(10);
			String groupName = String.format("%c%c-%d%d", c1, c2, num1, num2);
			groupNames.add(new Group(groupName, groupId));
			groupId++;
		}
		return groupNames;
	}

	public List<Course> generateCourses() {

		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		courses.add(new Course("Physics", "Physics Course", 3));
		courses.add(new Course("Chemistry", "Chemistry Course", 4));
		courses.add(new Course("Literature", "Literature Course", 5));
		courses.add(new Course("History", "History Course", 6));
		courses.add(new Course("Computer Science", "Computer Science Course", 7));
		courses.add(new Course("Art and Design", "Art and Design Course", 8));
		courses.add(new Course("Music", "Music Course", 9));
		courses.add(new Course("Psychology", "Psychology", 10));
		return courses;
	}

	public List<Student> generateStudents() {

		List<Student> students = new ArrayList<>();

		int studentsCount = 1;
		int groupId = 1;
		List<Course> courses = generateCourses();

		String[] firstName = { "Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James",
				"Mia", "Benjamin", "Charlotte", "Jacob", "Amelia", "Michael", "Harper", "Ethan", "Evelyn", "Daniel" };
		String[] lastName = { "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore", "Clark", "Lee", "Hall",
				"Gonzalez", "Martin", "White", "King", "Allen", "Wright", "Scott", "Green", "Baker", "Adams" };
		for (int i = 0; i < studentCount; i++) {

			String name = firstName[random.nextInt(firstName.length)];
			String surname = lastName[random.nextInt(lastName.length)];

			int coursesCount = random.nextInt(3) + 1;
			Set<Course> assignedCourse = new HashSet<>();

			while (assignedCourse.size() < coursesCount) {
				Course randomCourse = courses.get(random.nextInt(courses.size()));
				assignedCourse.add(randomCourse);
			}

			if (studentsCount > maxStudentInGroup) {
				studentsCount = 1;
				groupId++;
			}
			Student student = new Student(name, surname, groupId);
			student.setCourse(new ArrayList<>(assignedCourse));
			students.add(student);

			studentsCount++;
		}
		return students;
	}
}
