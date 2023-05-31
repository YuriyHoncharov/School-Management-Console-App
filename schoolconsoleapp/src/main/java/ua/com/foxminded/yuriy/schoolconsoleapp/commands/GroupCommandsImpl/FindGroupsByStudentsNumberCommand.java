package ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class FindGroupsByStudentsNumberCommand implements Command {
	private GroupDao groupDao;

	@Override
	public void execute() throws DaoException {
		System.out.println("Please enter the number..");
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		sc.close();
		List<Group> result = groupDao.findAllLessOrEqual(count);
		for (Group group : result) {
			System.out.println(group.getId() + ". " + group.getName());
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
