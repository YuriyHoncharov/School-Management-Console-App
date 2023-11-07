package ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class GetGroupsByStudentsNumberCommand implements Command {
	
	private GroupService groupService;

	@Autowired
	public GetGroupsByStudentsNumberCommand(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public void execute(Scanner sc) {
		System.out.println("Please enter the number..");
		int count = InputValidator.getNextInt(sc);
		List<Group> result = groupService.getAllLessOrEqual(count);
		if (result.isEmpty()) {
			System.out.println("No one group has " + count + " or less students.");
		} else {
			Collections.sort(result, Comparator.comparingInt(Group::getId));
			result.forEach((group) -> {
				System.out.println("Group ID : " + group.getId() + ". " + group.getName() + " = "
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
