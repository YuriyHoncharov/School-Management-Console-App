package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.Objects;

public class Group {
	
	private int id;
	private String name;

	public Group(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public Group() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[Group ID: " + id + " |  Group Name : " + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
}
