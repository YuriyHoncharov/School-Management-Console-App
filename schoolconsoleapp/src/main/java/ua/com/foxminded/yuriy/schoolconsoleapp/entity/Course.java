package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.List;

public class Course {

	private static int nextId = 1;
	private int id;
	private String name;
	private String description;
	private List<Student> studentsOfCourse;

	public Course(String name, String description) {

		this.id = nextId++;
		this.name = name;
		this.description = description;
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

	public List<Student> getStudentsOfCourse() {
		return studentsOfCourse;
	}

}
