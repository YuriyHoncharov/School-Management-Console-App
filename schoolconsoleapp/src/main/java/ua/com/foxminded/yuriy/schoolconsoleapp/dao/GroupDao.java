package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface GroupDao {

	List<Group> findAllLessOrEqual(int studentCount);
	int studentsCountByGroupId (int groupId);
	List<Group> getAll();
	Group getById (int groupId);

}
