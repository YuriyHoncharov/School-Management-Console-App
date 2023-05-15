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
	List<Course> courses = new ArrayList<>();

	public Student(String firstName, String lastName, int id, int groupId) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.groupId = groupId;
		this.id = id;
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", groupId=" + groupId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", courses=" + coursesToString() + "]";
	}

	public String getLastName() {
		return lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	private String coursesToString() {

		StringBuilder sb = new StringBuilder();
		for (Course course : courses) {
			sb.append(course.toString()).append(", ");
		}

		if (sb.length() > 0) {
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.toString();
	}

	public void setCourse(List<Course> courses) {
		this.courses = courses;
	}
}
