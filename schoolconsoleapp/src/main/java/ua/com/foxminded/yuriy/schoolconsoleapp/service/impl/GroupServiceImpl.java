package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl.GroupDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

public class GroupServiceImpl implements GroupService {
	private GroupDao groupDao = new GroupDaoImpl();

	@Override
	public List<Group> getAllLessOrEqual(int studentsCount) {
		return groupDao.getAllLessOrEqual(studentsCount);
	}

	@Override
	public int studentsCountByGroupId(int groupId) {
		return groupDao.studentsCountByGroupId(groupId);
	}

	@Override
	public List<Group> getAll() {
		return new ArrayList<>(groupDao.getAll());
	}

	@Override
	public Group getById(int groupId) {
		return groupDao.getById(groupId);
	}
}
