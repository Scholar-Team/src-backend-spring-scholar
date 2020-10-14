package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Teacher;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.TeacherRequest;

@Service
public class TeacherService extends BaseService<Teacher, TeacherDTO, TeacherRequest> {

	private TeacherRepository repository;
	
	@Autowired
	public TeacherService(TeacherRepository repository, TeacherMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
