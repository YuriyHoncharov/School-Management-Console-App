package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

public class Group {
	
	private int id;
	private String name;

	public Group(String name, int id) {
		this.name = name;
		this.id = id;
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
}
