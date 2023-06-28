package ua.com.foxminded.yuriy.schoolconsoleapp.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentRepository extends JpaRepository <Student, Integer>{

}
