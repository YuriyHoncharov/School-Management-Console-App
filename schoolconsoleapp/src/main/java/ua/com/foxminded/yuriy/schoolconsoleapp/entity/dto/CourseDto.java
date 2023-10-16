package ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto;

public class CourseDto {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
