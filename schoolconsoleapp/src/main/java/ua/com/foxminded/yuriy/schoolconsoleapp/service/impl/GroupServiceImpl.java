package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private GroupRepository groupRepository;
	
	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<Group> getAllLessOrEqual(Long studentsCount) {
		return groupRepository.getAllLessOrEqual(studentsCount);
	}

	@Override
	public List<Group> getAll() {
		return new ArrayList<>(groupRepository.findAll());
	}

	@Override
	public Group getById(int groupId) {
		Optional<Group>optionalGroup = groupRepository.findById(groupId);
		return optionalGroup.orElse(null);
	}
}
