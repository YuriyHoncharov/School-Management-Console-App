package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.Objects;

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
		return "[Course ID : " + id + " | Course Name : " + name + " | Description : " + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}
}
