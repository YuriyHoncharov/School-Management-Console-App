package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

public class Students {

	private String firstName;
	private String lastName;
	private int id;
	private int group_id;

	public Students(String firstName, String lastName, int id, int group_id) {
		
		this.id = id;
		this.group_id = group_id;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
}
