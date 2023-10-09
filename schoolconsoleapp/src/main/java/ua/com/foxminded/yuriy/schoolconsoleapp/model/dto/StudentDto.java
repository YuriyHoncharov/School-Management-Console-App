package ua.com.foxminded.yuriy.schoolconsoleapp.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@Component
public class StudentDto {
	private int id;
	private int groupId;
	private String firstName;
	private String lastName;

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

	@Override
	public String toString() {
		return "Student ID : " + id + ", Group ID : " + groupId + ", First Name  : " + firstName + ", Last Name : "
				+ lastName;
	}

	public List<StudentDto> studensDto(List<Student> students) {
		List<StudentDto> studentsDto = new ArrayList<>();

		for (Student st : students) {
			StudentDto student = new StudentDto();
			student.setId(st.getId());
			student.setFirstName(st.getFirstName());
			student.setLastName(st.getLastName());
			student.setGroupId(st.getGroupId());
			studentsDto.add(student);
		}
		return studentsDto;
	}
}
