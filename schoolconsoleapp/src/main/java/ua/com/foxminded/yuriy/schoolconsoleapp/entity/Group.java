package ua.com.foxminded.yuriy.schoolconsoleapp.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Group {

	private int id;
	private String name;
	private static Random random = new Random();
	private Set<Integer> usedId = new HashSet<>();

	public Group(String name) {
		this.id = generateUniqueId();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private int generateUniqueId() {
		int newId = random.nextInt(100);
		while (usedId.contains(newId)) {
			newId = random.nextInt(100);
		}
		usedId.add(newId);
		return newId;
	}
	
}