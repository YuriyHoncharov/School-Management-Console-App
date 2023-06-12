package ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl;

import java.util.List;
import java.util.Map;
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
		int count = sc.nextInt();
		sc.close();
		List<Group> result = groupService.findAllLessOrEqual(count);
		result.forEach((group) -> {
			try {
				System.out.println(group.getId() + ". " + group.getName() + " = "
						+ groupService.studentsCountByGroupId(group.getId()) + " students in this group.");
			} catch (DaoException e) {
				System.out.println("Error while getting group's students count!" + e.getMessage());
			}
		});
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
