package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.List;

public class Course {

	private int id;
	private String name;
	private String description;

	public Course(String name, String description, int id) {

		this.name = name;
		this.description = description;
		this.id = id;
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

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
