package ua.com.foxminded.yuriy.schoolconsoleapp.testconfig;

import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl.StudentDaoImpl;

@TestConfiguration
public class TestConfig {

	private JdbcTemplate JdbcTemplate;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testDb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setUsername("testuser");
		dataSource.setPassword("testpassword");
		return dataSource;
	}

	@Bean
	public StudentDaoImpl studentDao() {
		return new StudentDaoImpl(JdbcTemplate);
	}
}
