package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Student {

	private int id;
	private int groupId;
	private String firstName;
	private String lastName;
	private List<Course>courses;
	private static Random random = new Random();
	private static Set<Integer> usedId = new HashSet<>();

	public Student(String firstName, String lastName, Integer groupId) {

		this.id = generateUniqueId();
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
		}

	public int getId() {
		return id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourse(Course course) {
		this.courses.add(course);
	}

	private int generateUniqueId() {
		int newId = random.nextInt(2000);
		while (usedId.contains(newId)) {
			newId = random.nextInt(2000);
		}
		usedId.add(newId);
		return newId;
	}
}
