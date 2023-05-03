package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Groups;

public class RandomDataGenerator {

	public List<String> generateGroups(int count) {
		List<String> groupNames = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			char c1 = (char) (random.nextInt(26) + 'A');
			char c2 = (char) (random.nextInt(26) + 'A');
			int num1 = random.nextInt(10);
			int num2 = random.nextInt(10);
			String groupName = String.format("%c%c-%d%d", c1, c2, num1, num2);
			groupNames.add(groupName);
		}
		return groupNames;
	}

	public List<String> getCoursesList() {
		List<String> tmp = new ArrayList<>(Arrays.asList("Mathematics", "Biology", "Physics", "Chemistry", "Literature",
				"History", "Computer Science", "Art and Design", "Music", "Psychology"));
		return tmp;
	}

	public List<String> getStudent(int count) {
		List<String> studentNames = new ArrayList<>();
		String[] firstName = {"Emma", "Noah", "Olivia", 
				"Liam", "Ava", "William", 
				"Sophia", "Mason", "Isabella", 
				"James", "Mia", "Benjamin", 
				"Charlotte", "Jacob", "Amelia", 
				"Michael", "Harper", "Ethan", 
				"Evelyn", "Daniel"};
		String[] lastName = {"Smith", "Johnson", "Brown", 
				"Taylor", "Miller", "Wilson", 
				"Moore", "Clark", "Lee", 
				"Hall", "Gonzalez", "Martin", 
				"White", "King", "Allen", 
				"Wright", "Scott", "Green", 
				"Baker", "Adams"};
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			String name = firstName[random.nextInt(firstName.length)];
			String surname = lastName[random.nextInt(lastName.length)];
			studentNames.add(name + " " + surname);
		}
		return studentNames;
	}
}
