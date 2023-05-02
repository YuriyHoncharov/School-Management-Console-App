package ua.com.foxminded.yuriy.schoolconsoleapp.entity;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Groups {
	
	private String name;
	
	public Groups(String name) {
		this.name = name;
	}
		
	public List<Groups>generateGroups(int count){
		List<Groups> groups = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			char c1 = (char) (random.nextInt(26) + 'A');
			char c2 = (char) (random.nextInt(26) + 'A');
			int num1 = random.nextInt(10);
			int num2 = random.nextInt(10);			
			String groupName = String.format("%c%c-%d%d", c1, c2, num1, num2);
			Groups group = new Groups(groupName);
			groups.add(group);
		}
		return groups;
	}		
}