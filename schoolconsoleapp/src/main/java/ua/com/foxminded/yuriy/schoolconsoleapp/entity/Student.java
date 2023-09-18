package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
	
	@Override
	public int hashCode() {
		return Objects.hash(courses, firstName, groupId, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(firstName, other.firstName)
				&& groupId == other.groupId && id == other.id && Objects.equals(lastName, other.lastName);
	}

	private int id;
	private int groupId;
	private String firstName;
	private String lastName;
	private List<Course> courses = new ArrayList<>();
	
	public Student () {}
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
