package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private int id;
	private String name;
	private String description;
	private List<Student> students;

	public Course(String name, String description, int id) {

		this.name = name;
		this.description = description;
		this.id = id;
		this.students = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public void addStudents(Student student) {
		this.students.add(student);
	}

	public List<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
