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

	Random random = new Random();

	public List<Group> generateGroups(int count) {
		List<Group> groupNames = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			char c1 = (char) (random.nextInt(26) + 'A');
			char c2 = (char) (random.nextInt(26) + 'A');
			int num1 = random.nextInt(10);
			int num2 = random.nextInt(10);
			String groupName = String.format("%c%c-%d%d", c1, c2, num1, num2);
			groupNames.add(new Group(groupName));
		}
		return groupNames;
	}

	public List<Course> generateCourses() {

		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course"));
		courses.add(new Course("Biology", "Biology Course"));
		courses.add(new Course("Physics", "Physics Course"));
		courses.add(new Course("Chemistry", "Chemistry Course"));
		courses.add(new Course("Literature", "Literature Course"));
		courses.add(new Course("History", "History Course"));
		courses.add(new Course("Computer Science", "Computer Science Course"));
		courses.add(new Course("Art and Design", "Art and Design Course"));
		courses.add(new Course("Music", "Music Course"));
		courses.add(new Course("Psychology", "Psychology"));
		return courses;
	}

	public List<Student> generateStudents(int count) {
		List<Student> studentNames = new ArrayList<>();
		String[] firstName = { "Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James",
				"Mia", "Benjamin", "Charlotte", "Jacob", "Amelia", "Michael", "Harper", "Ethan", "Evelyn", "Daniel" };
		String[] lastName = { "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore", "Clark", "Lee", "Hall",
				"Gonzalez", "Martin", "White", "King", "Allen", "Wright", "Scott", "Green", "Baker", "Adams" };
		for (int i = 0; i < count; i++) {
			String name = firstName[random.nextInt(firstName.length)];
			String surname = lastName[random.nextInt(lastName.length)];
			studentNames.add(new Student(name, surname));
		}
		return studentNames;
	}

	public Group getRandomGroup(List<Group> groups) {
		return groups.stream().collect(Collectors.collectingAndThen(Collectors.toList(),
				shuffledList -> shuffledList.stream().findFirst().orElse(null)));
	}

	public void assignStudentsToGroups(List<Student> students, List<Group> groups) {

		Random random = new Random();
		Queue<Student> studentsList = new LinkedList<>(students);
		Queue<Group> groupsList = new LinkedList<>(groups);
		int studentsPerCourse = random.nextInt(21) + 10;
		
		studentsList.stream().forEach(student -> {
		
		student.setGroupId(groupsList.peek().getId());
		});
	}

	public void assignId(List<Group> groups) {
		Random random = new Random();
		Set<Integer> usedId = new HashSet<>();
		for (Group group : groups) {

			int randomId = random.nextInt(10) + 1;
			while (usedId.contains(randomId)) {
				randomId = random.nextInt(10) + 1;
			}
			group.setId(randomId);
			usedId.add(randomId);
		}
	}
}
