package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface GroupService {

	List<Group> findAllLessOrEqual(int courseId) throws DaoException;

}
