package ua.com.foxminded.yuriy.schoolconsoleapp.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")

public class SpringConfig {

	@Value("${DB_DRIVER_CLASS}")
	private String driverClassName;

	@Value("${DB_URL}")
	private String url;

	@Value("${DB_USERNAME}")
	private String username;

	@Value("${DB_PASSWORD}")
	private String password;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public Flyway flyway() {
	    Flyway flyway = Flyway.configure()
	        .dataSource(url, username, password)
	        .locations("classpath:db/migration")
	        .baselineOnMigrate(true)
	        .baselineVersion("1.0") // Adjust this to match the version in your migration script
	        .load();

	    flyway.migrate();
	    return flyway;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
