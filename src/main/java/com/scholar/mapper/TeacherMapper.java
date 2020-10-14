package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.TeacherDTO;
import com.scholar.model.Teacher;
import com.scholar.request.TeacherRequest;

@Component
public class TeacherMapper extends BaseMapper<Teacher, TeacherDTO, TeacherRequest> {

	public TeacherMapper() {
		super(Teacher.class, TeacherDTO.class, TeacherRequest.class);
	}
}
