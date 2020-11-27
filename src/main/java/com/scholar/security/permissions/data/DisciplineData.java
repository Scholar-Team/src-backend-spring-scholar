package com.scholar.security.permissions.data;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scholar.dto.StudentDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.service.StudentService;
import com.scholar.service.TeacherService;

@Component
public class DisciplineData {

	@Autowired
	private AuthData data;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	public Boolean isDisciplineBelongTeacher(Set<TeacherDTO> teachers) {
		Optional<TeacherDTO> teacher = teacherService.findById(data.getPersonId());
		
		if(!teacher.isPresent())
			return false;
		
		return teachers.contains(teacher.get());
	}
	
	public Boolean isDisciplineBelongStudent(Set<StudentDTO> students) {
		Optional<StudentDTO> student = studentService.findById(data.getPersonId());
		
		if(!student.isPresent())
			return false;
				
		return students.contains(student.get());
	}
}
