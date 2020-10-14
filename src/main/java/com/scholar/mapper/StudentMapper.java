package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.StudentDTO;
import com.scholar.model.Student;
import com.scholar.request.StudentRequest;

@Component
public class StudentMapper extends BaseMapper<Student, StudentDTO, StudentRequest> {

	public StudentMapper() {
		super(Student.class, StudentDTO.class, StudentRequest.class);
	}
}
