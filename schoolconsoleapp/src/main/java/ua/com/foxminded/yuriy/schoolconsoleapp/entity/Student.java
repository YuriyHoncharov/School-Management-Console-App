package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

public class Student {

	private int id;
	private int group_id;
	private String firstName;
	private String lastName;

	public Student(int id, int groupId, String firstName, String lastName) {

		this.id = id;
		this.group_id = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
