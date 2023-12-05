package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private GroupRepository groupRepository;
	
	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentsCount) {
		return groupRepository.getAllLessOrEqual(studentsCount);
	}

	@Override
	public List<Group> getAll() {
		return new ArrayList<>(groupRepository.getAll());
	}

	@Override
	public Group getById(int groupId) {
		return groupRepository.getById(groupId);
	}
}
