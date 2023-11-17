package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id", nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "group_id")
	private int groupId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@ManyToMany
	@JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	public Student() {
	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(int id, int groupId, String firstName, String lastName) {
		this.id = id;
		this.groupId = groupId;
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
