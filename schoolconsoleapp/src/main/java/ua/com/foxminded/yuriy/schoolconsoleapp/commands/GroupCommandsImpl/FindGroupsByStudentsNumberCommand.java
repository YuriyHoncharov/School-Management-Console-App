package ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.GroupServiceImpl;

public class FindGroupsByStudentsNumberCommand implements Command {
	private GroupService groupService = new GroupServiceImpl();

	@Override
	public void execute() throws DaoException {
		System.out.println("Please enter the number..");
		Scanner sc = new Scanner(System.in);
		int count = 0;
		while (!sc.hasNextInt()) {
			sc.next();
			System.out.println("You should enter a numeric value, please retry.");
		}
		count = sc.nextInt();
		List<Group> result = groupService.findAllLessOrEqual(count);
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
