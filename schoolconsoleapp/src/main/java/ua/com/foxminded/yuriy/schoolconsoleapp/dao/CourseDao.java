package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseDao {

 boolean addAll(List<Course> courses) throws DaoException;

}
