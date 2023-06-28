package ua.com.foxminded.yuriy.schoolconsoleapp.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupRepository extends JpaRepository <Group, Integer>{

}
