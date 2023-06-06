package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface GroupDao {

	Map<Group, Integer> findAllLessOrEqual(int studentCount) throws DaoException;

}
