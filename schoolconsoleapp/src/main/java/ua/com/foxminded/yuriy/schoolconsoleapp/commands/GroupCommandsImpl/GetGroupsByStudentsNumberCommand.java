package ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.input.InputValidator;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.GroupServiceImpl;

public class GetGroupsByStudentsNumberCommand implements Command {
	private GroupService groupService = new GroupServiceImpl();
	
	@Override
	public void execute(Scanner sc) {
		System.out.println("Please enter the number..");
		int count = 0;
		count = InputValidator.getNextInt(sc);
		List<Group> result = groupService.getAllLessOrEqual(count);
		if (result.isEmpty()) {
			System.out.println("No one group has " + count + " or less students.");
		} else {
			result.forEach((group) -> {
				System.out.println(group.getId() + ". " + group.getName() + " = "
						+ groupService.studentsCountByGroupId(group.getId()) + " students in this group.");
			});
		}
	}

	@Override
	public String name() {
		return "1";
	}

	@Override
	public String description() {
		return "Find groups with the amount of students you enter";
	}
}
