package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.List;

public class Course {

	private int id;
	private String name;
	private String description;

	public Course(String name, String description) {

		this.name = name;
		this.description = description;
	}

	public void setId(int id) {
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

}
