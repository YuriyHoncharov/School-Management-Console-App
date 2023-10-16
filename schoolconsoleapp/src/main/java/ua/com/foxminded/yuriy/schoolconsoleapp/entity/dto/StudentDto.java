package ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@Component
public class StudentDto {
	private int id;
	private int groupId;
	private String firstName;
	private String lastName;
	private List<Course> courses = new ArrayList<>();

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public StudentDto() {
	}

	public String courseToString() {

		StringBuilder sb = new StringBuilder();

		for (Course course : courses) {
			CourseDto courseDto = new CourseDto();
			courseDto.setName(course.getName());
			sb.append(courseDto.toString()).append(", ");
		}

		if (sb.length() > 0) {
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		String courseString = courseToString();
		return String.format("ID : %-3d | Name : %-20s | Group ID : %-2d | Courses : %-15s" , id,
				firstName + " " + lastName, groupId, courseString);
	}

	public List<StudentDto> studensDto(List<Student> students) {
		List<StudentDto> studentsDto = new ArrayList<>();

		for (Student st : students) {
			StudentDto student = new StudentDto();
			student.setId(st.getId());
			student.setFirstName(st.getFirstName());
			student.setLastName(st.getLastName());
			student.setGroupId(st.getGroupId());
			student.setCourses(st.getCourses());
			studentsDto.add(student);

		}
		return studentsDto;
	}
}
