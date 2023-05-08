package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public List<Course> getCoursesList() {

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

	public List<Student> generateStudent(int count) {
		List<Student> studentNames = new ArrayList<>();
		String[] firstName = { "Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James",
				"Mia", "Benjamin", "Charlotte", "Jacob", "Amelia", "Michael", "Harper", "Ethan", "Evelyn", "Daniel" };
		String[] lastName = { "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore", "Clark", "Lee", "Hall",
				"Gonzalez", "Martin", "White", "King", "Allen", "Wright", "Scott", "Green", "Baker", "Adams" };
		for (int i = 0; i < count; i++) {
			String name = firstName[random.nextInt(firstName.length)];
			String surname = lastName[random.nextInt(lastName.length)];
			
			List<Group>generatedGroups = generateGroups(20);
			
			int randomGroupId = random.nextInt(21);
			int groupId = generatedGroups.get(randomGroupId).getId();
			
			studentNames.add(new Student(groupId ,name, surname));
		}
		return studentNames;
	}
}
