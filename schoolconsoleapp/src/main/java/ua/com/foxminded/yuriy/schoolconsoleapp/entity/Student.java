package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Student {

	private int id;
	private int groupId;
	private String firstName;
	private String lastName;

	public Student(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getId() {
		return id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", groupId=" + groupId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String getLastName() {
		return lastName;
	}

}
