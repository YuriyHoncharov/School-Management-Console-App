package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupService {

	List<Group> getAllLessOrEqual(Long studentsCount);

	List<Group> getAll();

	Optional<Group> getById(int groupId);

}
