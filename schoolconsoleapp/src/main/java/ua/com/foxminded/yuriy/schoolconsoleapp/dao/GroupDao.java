package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupDao {

	void addAll(List<Group> groups);

	List<Group> getAllLessOrEqual(int studentCount);

	List<Group> getAll();

	Group getById(int groupId);

}
