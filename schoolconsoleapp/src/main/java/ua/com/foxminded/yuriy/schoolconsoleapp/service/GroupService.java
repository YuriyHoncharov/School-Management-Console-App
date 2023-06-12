package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface GroupService {

	List<Group> findAllLessOrEqual(int courseId) throws DaoException;
	int studentsCountByGroupId (int groupId) throws DaoException;

}
