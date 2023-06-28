package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
@Entity
public class Student {
	
	private int id;
	private int groupId;
	private String firstName;
	private String lastName;
	private List<Course> courses = new ArrayList<>();

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getId() {
		return id;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "[Student ID : " + id + ", Group ID : " + groupId + ", First Name : " + firstName + ", Last Name : "
				+ lastName + coursesToString() + "]";
	}

	public List<Course> getCourses() {
		return courses;
	}

	private String coursesToString() {

		StringBuilder sb = new StringBuilder();
		if (!courses.isEmpty()) {
			sb.append(", Courses List : ");
		}
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
