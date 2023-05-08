package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Student {

	private int id;
	private int group_id;
	private String firstName;
	private String lastName;
	private static Random random = new Random();
	private static Set<Integer> usedId = new HashSet<>();

	public Student(int groupId, String firstName, String lastName) {

		this.id = generateUniqueId();
		this.group_id = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private int generateUniqueId() {
		int newId = random.nextInt(2000);
		while (usedId.contains(newId)) {
			newId = random.nextInt(2000);
		}
		usedId.add(newId);
		return newId;
	}
}
