package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private GroupDao groupDao;
	
	@Autowired
	public GroupServiceImpl(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

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
