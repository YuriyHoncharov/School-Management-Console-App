package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.TestConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public class CourseDaoImplTest {

   @Mock
   private Connection mockConnection;
   @Mock
   private PreparedStatement mockStatement;

   @InjectMocks
   private CourseDaoImpl courseDao;

   @BeforeEach
   void setUp() throws SQLException {
       MockitoAnnotations.openMocks(this);

       try (MockedStatic<ConnectionUtil> mockedStatic = mockStatic(ConnectionUtil.class)) {
           when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
           mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
       }
   }

    @Test
    void addAllTest_Success() throws SQLException {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Mathematics", "Math Course", 1));
        courses.add(new Course("Biology", "Biology Course", 2));

        when(mockStatement.executeUpdate()).thenReturn(1);

        courseDao.addAll(courses);

        verify(mockConnection, times(1)).prepareStatement(SqlCourseQueries.ADD_ALL);
        verify(mockStatement, times(courses.size())).setInt(eq(1), anyInt());
        verify(mockStatement, times(courses.size())).setString(eq(2), anyString());
        verify(mockStatement, times(courses.size())).setString(eq(3), anyString());
        verify(mockStatement, times(courses.size())).executeUpdate();
    }
}