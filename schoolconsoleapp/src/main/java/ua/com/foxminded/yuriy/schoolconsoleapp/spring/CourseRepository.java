package ua.com.foxminded.yuriy.schoolconsoleapp.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseRepository extends JpaRepository <Course, Integer>{

}
